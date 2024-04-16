package network;

import shared.Request;
import utility.ConsoleWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RequestLogic {
    DatagramSocket socket;

    public RequestLogic(DatagramSocket socket) {
        this.socket = socket;
    }

    public void send(Request request) {
        byte[] bytes = serializeObj(request);
        InetAddress address = socket.getInetAddress();
        int port = socket.getPort();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            ConsoleWriter.getInstance().alert("Ошибка при отправке данных серверу.");
            throw new RuntimeException(e);
        }
    }

    private byte[] serializeObj(Object obj) {
        byte[] bytes;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.flush();
            oos.writeObject(obj);
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}
