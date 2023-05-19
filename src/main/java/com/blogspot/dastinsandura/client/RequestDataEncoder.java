package com.blogspot.dastinsandura.client;

import com.blogspot.dastinsandura.server.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
        System.out.println("RequestDataEncoder encode method");
        out.writeInt(msg.getQuantity());
        out.writeInt(msg.getProductName().length());
        out.writeCharSequence(msg.getProductName(), StandardCharsets.UTF_8);
    }
}
