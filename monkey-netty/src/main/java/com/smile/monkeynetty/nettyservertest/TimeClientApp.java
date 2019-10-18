package com.smile.monkeynetty.nettyservertest;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName TimeClientApp
 * @Author kris
 * @Date 2019/10/15
 **/
public class TimeClientApp {
    public static void main(String[] args) throws Exception {
        // ip
        String host = "127.0.0.1";
        // 端口
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 与ServerBootstrap类似
            Bootstrap b = new Bootstrap();
            // 客户端不需要boss worker
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            // 客户端的socketChannel没有父亲
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // POJO
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // 启动客户端，客户端用connect连接
            ChannelFuture f = b.connect(host, port).sync();

            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
