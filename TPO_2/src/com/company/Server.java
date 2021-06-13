package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    private IProtocol protocol;
    private ByteBuffer byteBuffer;
    int port;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Server(int port, IProtocol protocol) throws IOException {
        this.port = port;
        this.protocol = protocol;
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        byteBuffer = ByteBuffer.allocate(36);
    }

    public void handlingOperations() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    System.out.println("Accepted");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    continue;
                }
                if (key.isReadable() && key.isWritable()) {
                    byteBuffer.clear();
                    System.out.println("readable");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    String request = new String(byteBuffer.array(), 0, byteBuffer.limit());
                    String response = protocol.request(request);
                    byteBuffer.clear();
                    byteBuffer.put(response.getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    socketChannel.close();

                    break;
                }
                if (key.isWritable()) {
                    System.out.println("writable");
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        if (args[0].equals("Echo")) {
            Echo echo = new Echo();
            Server server = new Server(55555, echo);
            server.handlingOperations();
        }
        if (args[0].equals("Add")) {
            Add add = new Add();
            Server server = new Server(5555, add);
            server.handlingOperations();
        }
    }
}
