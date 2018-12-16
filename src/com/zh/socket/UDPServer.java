package com.zh.socket;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * 服务器端，基于UDP的用户登录
 */

public class UDPServer {
    public static void main(String[] args) throws IOException{
        /*
         * 服务器端接受客户端的数据
         */
        DatagramSocket socket = new DatagramSocket(6666);
        System.out.println("***服务器端即将启动,等待客户端连接***");
        while(true){
            UDPServerThread st = new UDPServerThread(socket);
            st.start();
        }

    }

}
