package com.company.frame.mainServer.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.apache.log4j.Logger;


/**
 * Created by zy on 17-7-23.
 */
public class HttpServer {
    private static Logger logger = Logger.getLogger(HttpServer.class);
    private int port;

    public HttpServer(int port){
        this.port = port;
    }

    public void start() {
        logger.debug("http:"+port+"startup===========");
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(workerGroup, bossGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG,4096)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new HttpResponseEncoder());
                        channel.pipeline().addLast(new HttpRequestDecoder());
                        channel.pipeline().addLast(new HttpClientCodec());
                        channel.pipeline().addLast(new HttpObjectAggregator(65536));
                        channel.pipeline().addLast(new HttpOutboundHandler());
                        channel.pipeline().addLast(new HttpInboundHandler());
                    }
                });

            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error(port+"\t",e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("http server is over...............");
        }

    }
}


