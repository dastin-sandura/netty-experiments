package com.blogspot.dastinsandura.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        RequestData data = new RequestData();
        data.setQuantity(in.readInt());
        int strLen = in.readInt();
        data.setProductName(in.readCharSequence(strLen, StandardCharsets.UTF_8).toString());
        out.add(data);

    }
}
