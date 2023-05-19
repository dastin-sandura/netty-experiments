package com.blogspot.dastinsandura.client;

import com.blogspot.dastinsandura.server.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ResponseDataDecoder decode method");
        ResponseData data = new ResponseData();
        data.setOrderCost(in.readInt());
        out.add(data);
    }
}
