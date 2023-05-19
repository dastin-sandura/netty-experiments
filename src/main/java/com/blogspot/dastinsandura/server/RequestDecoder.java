package com.blogspot.dastinsandura.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * The biggest difference between ReplayingDecoder and ByteToMessageDecoder
 * is that ReplayingDecoder allows you to implement the decode() and decodeLast()
 * methods just like all required bytes were received already,
 * rather than checking the availability of the required bytes.
 *
 * This is why there is no code, which checks if we received enough data about the RequestData
 */
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
