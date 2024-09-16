package ir.xenoncommunity.socket;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.ObjectOutputStream;
import java.io.OutputStream;

@UtilityClass
public class SocketUtil {
    @SneakyThrows
    public void send(final OutputStream osIn, final byte[] toWrite){
       osIn.write(toWrite);
    }
    @SneakyThrows
    public void send(final OutputStream osIn, final Object toWrite){
        new ObjectOutputStream(osIn).writeObject(toWrite);
    }
    @SneakyThrows
    public void sendHeader(final OutputStream osIn, final String contentType){
        osIn.write(("HTTP/1.0 200 OK\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "\r\n").getBytes());
    }
}
