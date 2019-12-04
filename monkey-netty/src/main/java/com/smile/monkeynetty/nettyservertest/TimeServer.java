package com.smile.monkeynetty.nettyservertest;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @ClassName TimeServer
 * @Author kris
 * @Date 2019/10/21
 **/
public class TimeServer {

    public void bind(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workLoopGroup = new NioEventLoopGroup();
    }
}
