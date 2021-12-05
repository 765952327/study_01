package cn.winstone.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 服务端
 *
 * @author Winstone
 * @date 2021/12/4 - 5:43 下午
 */
public class NioServer{

	/**
	 * 启动
	 */
	public void start() throws IOException {
		/**
		 * 1. 创建Selector
		 */
		Selector selector = Selector.open();
		/**
		 * 2. 通过ServerSocketChannel创建Channel通道
		 */
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		/**
		 * 3. 为Channel通道绑定监听端口
		 */
		serverSocketChannel.bind(new InetSocketAddress(8081));
		/**
		 * 4. 设置Channel为非阻塞模式
		 */
		serverSocketChannel.configureBlocking(false);
		/**
		 * 5.将Channel注册到Selector上
		 */
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务器启动成功！");
		/**
		 * 6.循环等待新的接入链接
		 */
		for (; ; ) {
			int readyChannels = selector.select();
			if (readyChannels == 0) continue;
			// 获取可用的Channel集合
			Set<SelectionKey> selectionKeys = selector.selectedKeys();

			Iterator iterator = selectionKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				// 移除Set中的当前selectionKey
				iterator.remove();

				/**
				 * 7.根据就绪状态，调用对应方法处理业务逻辑
				 */
				// 如果是接入事件
				if (selectionKey.isAcceptable()) {
					acceptHandler(serverSocketChannel, selector);
				}


				// 如果是可读事件
				if (selectionKey.isReadable()) {
					readHandler(selectionKey, selector);
				}
			}
		}
	}

	private void acceptHandler(ServerSocketChannel channel, Selector selector) throws IOException {
		SocketChannel socketChannel = channel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);

		socketChannel.write(StandardCharsets.UTF_8.encode("欢迎来到聊天室"));
	}

	private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		StringBuilder request = new StringBuilder();
		while (socketChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			request.append(StandardCharsets.UTF_8.decode(byteBuffer));
		}

		socketChannel.register(selector, SelectionKey.OP_READ);

		if (request.length() > 0) {
			System.out.println(socketChannel.getRemoteAddress() + "::" + request);
			broadCast(selector, socketChannel, request.toString());
		}
	}

	/**
	 * 广播给其他客户端
	 */
	private void broadCast(Selector selector, SocketChannel source, String request) {
		/**
		 * 获取所有已经接入的客户端Channel
		 */
		Set<SelectionKey> selectionKeySet = selector.keys();

		selectionKeySet.forEach(selectionKey -> {
			Channel targetChannel = selectionKey.channel();

			if (targetChannel instanceof SocketChannel
					&& targetChannel != source
			) {
				try {
					((SocketChannel) targetChannel).write(StandardCharsets.UTF_8.encode(request));
				} catch (IOException e) {
					System.err.println("[ERROR]:" + e.getMessage());
				}
			}
		});

		/**
		 * 循环向Channel广播信息
		 */

	}

	public static void main(String[] args) {
		try {
			new NioServer().start();
		} catch (IOException e) {
			System.err.println("[ERROR]:" + e.getMessage());
		}
	}
}
