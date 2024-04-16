package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class ClientBinder {
    private final DatagramChannel datagramChannel;
    private static final int PORT = 8200;

    public ClientBinder(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }

    public void bind() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(PORT);
            if (datagramChannel.isConnected()) {
                System.out.println("Server already bind Client.");
                return;
            }
            datagramChannel.bind(socketAddress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
