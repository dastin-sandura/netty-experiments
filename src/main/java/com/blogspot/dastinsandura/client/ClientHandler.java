package com.blogspot.dastinsandura.client;

import com.blogspot.dastinsandura.server.RequestData;
import com.blogspot.dastinsandura.server.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData msg = new RequestData();
        msg.setProductName("Papaya");
        msg.setQuantity(100);
        ChannelFuture future  = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead method received message " +  (ResponseData)msg);
        ctx.close();
    }
}
