package com.company.frame.mainServer.longconnection;

import com.company.frame.Config.ConfigInit;
import com.company.util.Redis.Redis;
import com.company.util.ThreadUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zy on 17-7-19.
 */
public class LongConnServer {
    static {
        PropertyConfigurator.configure("./Logger/log4j.propertites");
    }
    private static Logger logger = Logger.getLogger(ChannelWriter.class);
//    private Lock lock = new ReentrantLock();

    public LongConnServer(){

    }

    public static void ConnListen(){
        ThreadUtil.Run(new Runnable() {
            @Override
            public void run() {
                new Redis().zsubscribe(new Listener(),ConfigInit.UPPERCHANNEL);

            }
        },"ConnListen");
        logger.debug("------------------------ConnListen----------------------------");
    }



    public void start(int port){
        logger.info("LongConnserver start"+port);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(workerGroup,bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelWriter());
                            socketChannel.pipeline().addLast(new ChannelReader());
                        }
                    });

            ChannelFuture future = serverBootstrap.bind(port).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("LongConnServer.......",e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("longconnserver over");
        }

    }

    public void sendMessage(String clientid, String message) {
//        lock.lock();

        ChannelHandlerContext ctx = ChannelMap.getCliet(clientid);
        ctx.write(message);
//        ctx.flush();
        logger.info("clientid:"+clientid+"|message:"+message+"|size"+ChannelMap.getLength());
//        lock.unlock();
    }
}
