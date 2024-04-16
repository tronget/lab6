package network;

import org.apache.commons.lang3.ArrayUtils;
import utility.ConsoleWriter;
import utility.Program;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ResponseLogic {
    DatagramSocket socket;

    public ResponseLogic(DatagramSocket socket) {
        this.socket = socket;
    }

    public String receive() {
        byte[] data = new byte[1024 * 1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        return getData(packet, data);
    }

    private String getData(DatagramPacket packet, byte[] data) {
        try {
            socket.receive(packet);
            data = packet.getData();
            data = ArrayUtils.toPrimitive(Arrays.stream(ArrayUtils.toObject(data)).filter(el -> el != 0).toArray(Byte[]::new));
            return new String(data);
        } catch (IOException e) {
            ConsoleWriter.getInstance().alert("Ошибка при получении данных с сервера.");
            return null;
        }
    }
}
