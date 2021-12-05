package cn.winstone.study.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类，接收服务端响应信息
 *
 * @author Winstone
 * @date 2021/12/4 - 6:33 下午
 */
public class NioClientHandler implements Runnable{
	private Selector selector;

	public NioClientHandler(Selector selector) {
		this.selector = selector;
	}

	@Override
	public void run() {
		try {
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

					// 如果是可读事件
					if (selectionKey.isReadable()) {
						readHandler(selectionKey, selector);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e.getMessage());
		}

	}

	private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		StringBuilder response = new StringBuilder();
		while (socketChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			response.append(StandardCharsets.UTF_8.decode(byteBuffer));
		}

		socketChannel.register(selector, SelectionKey.OP_READ);

		if (response.length() > 0) {
			System.out.println("=>" + response);
		}
	}

}
