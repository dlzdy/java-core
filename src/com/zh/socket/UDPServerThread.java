
package com.zh.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * 链接：https://www.imooc.com/article/16708
 * 服务器线程处理类
 */
public class UDPServerThread extends Thread {
	/*
	 * 线程执行的操作，响应客户端的请求
	 */
	DatagramSocket socket = null;

	public UDPServerThread(DatagramSocket socket) {
		this.socket = socket;
	}

	public void run() {
		/*
		 * 服务器端接受客户端的数据
		 */
		byte[] data = new byte[1024];// 指定用于接受数据报的大小
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);// 接受到数据之前该方法处于阻塞状态
			String info = new String(data, 0, packet.getLength());
			System.out.println("我是服务器,客户端说:" + info);
			/*
			 * 服务器端向客户端进行响应
			 */
			// 1.定义客户端的地址，端口号，数据
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			byte[] data2 = "欢迎您!".getBytes();
			// 2.创建数据报，包含相应信息
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
			// 3.响应客户端
			socket.send(packet2);
			// 注意这里不能关闭socket，因为在UDPServer类的while是死循环，无法重新创建socket,所以这里不能关闭socket,否则无法进行下一个客户端的监听
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
