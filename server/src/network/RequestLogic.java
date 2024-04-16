package network;

import shared.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class RequestLogic {
    private final DatagramChannel channel;
    private SocketAddress clientAddress;

    public RequestLogic(DatagramChannel channel) {
        this.channel = channel;
    }

    public SocketAddress getClientAddress() {
        return clientAddress;
    }

    public Request receive() {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        return getRequest(buffer);
    }

    public Request receive(int capacity) {
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        return getRequest(buffer);
    }

    private Request getRequest(ByteBuffer buffer) {
        Request request;
        try {
            clientAddress = channel.receive(buffer);
            if (buffer.array()[0] == 0) {
                return null;
            }
            request = (Request) deserializeBytes(buffer.array());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при считывании данных с клиента.");
            throw new RuntimeException(e);
        }
        return request;
    }


    private Object deserializeBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        bis.close();
        return obj;
    }
}
