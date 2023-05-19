package com.blogspot.dastinsandura.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, Integer> productCostMap = new HashMap<>() {{
        put("Banana", 3);
        put("Papaya", 9);
    }};
    private ByteBuf tmp;
    private final int BUFFER_CAPACITY = 8;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("Handler added.");
        tmp = ctx.alloc().buffer(BUFFER_CAPACITY);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Handler removed.");
        tmp.release();
        tmp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        RequestData requestData = (RequestData) msg;
        ResponseData responseData = new ResponseData();
        responseData.setOrderCost(requestData.getQuantity() * productCostMap.get(requestData.getProductName()));
        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
        System.out.println(requestData);
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf message = (ByteBuf) msg;
//        tmp.writeBytes(message);
//        message.release();
//        if (tmp.readableBytes() >= BUFFER_CAPACITY) {
//            //request processing
//            RequestData requestData = new RequestData();
//            requestData.setQuantity(tmp.readInt());
//            requestData.setProductName(tmp.readCharSequence(4, StandardCharsets.UTF_8).toString());
//
//            ResponseData responseData = new ResponseData();
//            Integer integer = productCostMap.get(requestData.getProductName());
//            if (integer == null) {
//                System.err.println("There is no cost for product: " + requestData.getProductName());
//                integer = 1;
//            }
//            responseData.setOrderCost(requestData.getQuantity() * integer);
//            ChannelFuture future = ctx.writeAndFlush(responseData);
//            future.addListener(ChannelFutureListener.CLOSE);
//        }
//    }

}
