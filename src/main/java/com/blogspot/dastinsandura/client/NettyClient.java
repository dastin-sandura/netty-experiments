package com.blogspot.dastinsandura.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //Compared to the NettyServer, we use Bootstrap class instead of ServerBootstrap
            Bootstrap b = new Bootstrap();
            //in case of NettyClient, only one EventLoopGroup is enough. It will be used as a boss and worker group
            b.group(workerGroup);
            //Compared to NettyServer we are using an implementation of SocketChannel instead of ServerSocketChannel
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE,true); //SO_KEEPALIVE means that when the connection is idle for some time, TCP auto sends a keepalive probe to the remote peer
            // there is no use of childOption() because client has no parent socket
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new ResponseDataDecoder(),
                            new RequestDataEncoder()
                            ,new ClientHandler()
                    );
                }
            });
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
        }

    }
}
