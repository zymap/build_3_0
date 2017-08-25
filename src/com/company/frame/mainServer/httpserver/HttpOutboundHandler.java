package com.company.frame.mainServer.httpserver;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


/**
 * Created by zy on 17-7-23.
 */
public class HttpOutboundHandler extends ChannelOutboundHandlerAdapter {
    static {
            PropertyConfigurator.configure("./Logger/log4j.propertites");
    }
    private static Logger logger = Logger.getLogger(HttpOutboundHandler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String string = (String) msg;
        logger.info("String:"+string+"\tctx:"+ctx+"\tmsg:"+msg);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,OK,Unpooled.wrappedBuffer(string.getBytes()));
        response.headers().set(CONTENT_TYPE,"text/json");
        response.headers().setInt(CONTENT_LENGTH,response.content().readableBytes());
        ctx.write(response);
        ctx.flush();
        logger.info("http already send");
    }
}
