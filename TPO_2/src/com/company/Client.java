package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    IProtocol protocol;
    private SocketChannel channel;
    int port ;
    ByteBuffer byteBuffer = ByteBuffer.allocate(36);

    public Client(int port,IProtocol protocol) throws Exception {
        this.port = port;
        this.protocol = protocol;
        try{
            channel = SocketChannel.open();
            channel.configureBlocking(true);
            SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(),port);
            channel.connect(address);
            System.out.println("Connecting....");
            while(!channel.finishConnect()){
                System.out.println("Established");
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host" + InetAddress.getLocalHost());
        }catch (Exception ex){
            ex.printStackTrace();
        }
            System.out.println("\nConnected");
        send();
        read();
    }
    public void read() throws IOException {
        byteBuffer.clear();
        while(true){
            int readBytes = channel.read(byteBuffer);
            byteBuffer.flip();
            if(readBytes == 0){
                System.err.println("no data to read");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            else if(readBytes == -1){
                System.err.println("Channel closed at server side or read not possible");
                break;
            }else {
                String response = new String(byteBuffer.array(), 0, byteBuffer.limit());
                System.out.println("response: " + response);
                break;
            }
        }
    }
    public void send() throws IOException {
        byteBuffer.clear();
        byteBuffer.put(protocol.getMessg().getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);

    }


    public static void main(String[] args) throws Exception{
        if(args[0].equals("Echo")) {
            Echo echo = new Echo(args[1]);
            Client client = new Client(55555,echo);
        }
        if(args[0].equals("Add")){
            Add add = new Add(args[1]);
            Client client = new Client(5555,add);
        }
    }
}
