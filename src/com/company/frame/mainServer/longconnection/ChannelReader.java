package com.company.frame.mainServer.longconnection;

import com.company.frame.mainServer.httpserver.Message;
import com.company.frame.mainServer.transmitter.Swap;
import com.company.frame.mainServer.transmitter.SwapHashmap;
import com.company.pojo.Value;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.WriterAppender;

//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * Created by zy on 17-7-22.
 */
public class ChannelReader extends ChannelInboundHandlerAdapter{
    static {
        PropertyConfigurator.configure("./Logger/log4j.propertites");
    }
    private static Logger logger = Logger.getLogger(ChannelReader.class);
    private final static String LOGIN = "LOGIN";
    private final static String MESSAGE = "MESSAGE";
    private final static String HEARTBEAT = "HEARTBEAT";


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String resultStr = new String(bytes);
        logger.info("resultString:"+resultStr);

        String head = Message.getHead(resultStr);
//        String body = Message.getBody(resultStr);
//        logger.info(head + "/" + body);

        switch (head) {
            case LOGIN:
                logger.info(LOGIN+"\t"+"head:"+head);
                String body = Message.getBody(resultStr);
                if (body != null && !body.equals("")) {
                    ChannelMap.add(body, ctx);
                    String str = "";
                    ctx.write(str);
                    logger.debug("LOGIN WRITE OVER");
                }
                logger.info("body:"+body);
                break;
            case HEARTBEAT:
                logger.info("HEARTBEAT head:"+head);
                ctx.write("HEA");
                break;
            case MESSAGE:
                logger.info(MESSAGE+"head:"+head);
                String res = Message.getBody(resultStr);
                String var[] = res.split("#");
                Value value = SwapHashmap.getValue(var[0]);
                value.getSwap().setMessage(var[0],var[1]+var[2]);
                ctx.write("");
                logger.info("body"+res);
                break;
        }
    }
}
