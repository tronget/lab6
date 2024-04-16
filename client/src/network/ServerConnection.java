package network;

import utility.ConsoleWriter;
import utility.Program;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerConnection implements Connectable {
    private final DatagramSocket datagramSocket;
    private final String hostname;
    private static final int PORT = 8200;

    public ServerConnection(String hostname, DatagramSocket datagramSocket) {
        this.hostname = hostname;
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void connect() {
        try {
            if (datagramSocket.isConnected()) {
                System.out.println("Client already connected to Server.");
                return;
            }
            datagramSocket.connect(InetAddress.getByName(hostname), PORT);
        } catch (UnknownHostException e) {
            ConsoleWriter.getInstance().alert("Не удалось установить подключение!");
            System.out.println(e.getMessage());

            Program.getInstance().stop();
        }
    }
}
