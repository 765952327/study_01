package cn.winstone.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Winstone
 * @date 2021/12/4 - 6:07 下午
 */
public class NioClient{
	public String name;

	public void start() throws IOException {
		/**
		 * 1、链接服务器
		 */
		SocketChannel socketChannel = SocketChannel.open(
				new InetSocketAddress(8081)
		);

		/**
		 * 3. 接受服务器响应
		 */
		Selector selector = Selector.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
		new Thread(new NioClientHandler(selector)).start();

		/**
		 * 2、向服务器发送数据
		 */
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String request = scanner.nextLine();
			if (request != null && request.length() > 0) {
				socketChannel.write(StandardCharsets.UTF_8.encode("[" + name + "]:" + request));
			}
		}


	}

	public static void main(String[] args) {

		try {
			NioClient nioClient = new NioClient();
			nioClient.name = args[0];
			nioClient.start();
		} catch (IOException e) {
			System.err.println("[ERROR] try again later");
		}
	}


}
