package com.zdy.chat.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPSever1 {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(8800);
		System.out.println("服务器即将启动，等待客户端的连接");
		byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
		int count = 0;
		while (true) {
			DatagramPacket packet = new DatagramPacket(data, data.length);
			socket.receive(packet);
			// 此方法在接收到数据报之前会一直阻塞
			// 4.读取数据
			count++;
			System.out.println("客户端数量" + count);
			UDPServerThread UDPThread = new UDPServerThread(socket, packet, data);
			UDPThread.start();
		}
	}
}
