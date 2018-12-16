package com.zh.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * 客户端
 */

public class UDPClient {
    public static void main(String[] args) throws IOException {
        /*
         * 向服务器端发送请求
         */
        //1.定义服务器的地址，端口号，数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 6666;
        byte[] data = "用户名：zh;密码:456".getBytes();
        //2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
        //3.创建DatagramSocket用于数据的传输
        DatagramSocket socket = new DatagramSocket();
        //4.向服务器端发送数据报
        socket.send(packet);
        /*
         * 接收服务器端响应的数据
         */
        //1.创建数据报，用于接收服务器端的信息
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2,data.length);
        //2.接收服务器端的信息
        socket.receive(packet2);//在接收到响应之前，此方法会一直处于阻塞状态
        //3.读取信息
        String reply = new String(data2,0,packet.getLength());
        //4.输出信息
        System.out.println("我是客户端，服务器端说:"+reply);
        //5.关闭资源
        socket.close();

    }

}
