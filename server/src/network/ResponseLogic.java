package network;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ResponseLogic {
    private final DatagramChannel channel;

    public ResponseLogic(DatagramChannel channel) {
        this.channel = channel;
    }

    public void send(SocketAddress clientAddress, String message) {
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        try {
            channel.send(buffer, clientAddress);
        } catch (IOException e) {
            System.out.println("Ошибка при отправке данных клиенту.");
            throw new RuntimeException(e);
        }
    }
}
