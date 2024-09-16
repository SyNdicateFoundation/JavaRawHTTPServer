package ir.xenoncommunity.socket;

import ir.xenoncommunity.ServerInitializer;
import ir.xenoncommunity.mapping.FileMapping;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class HTTPServer {
    public void startHTTPServer(final ServerSocket socketServerIn) {
        ServerInitializer.instance.getTaskManager().repeatingTask(() -> {
            try {
                @Cleanup final Socket socket = socketServerIn.accept();
                @Cleanup final OutputStream os = socket.getOutputStream();
                @Cleanup final InputStream is = socket.getInputStream();
                @Cleanup final BufferedReader bf = new BufferedReader(new InputStreamReader(is));

                final String[] request = bf.readLine().split(" ");
                if (request.length < 2) return;

                ServerInitializer.instance.getLogger().info("Connection Handled! Request: " + Arrays.toString(request));

                final String filePath = FileMapping.fileMaps.get(request[1]);
                final String contentType = FileMapping.types.get(request[1]);

                final Path file = Paths.get(filePath);

                if (!Files.exists(file) || contentType == null) {
                    ServerInitializer.instance.getLogger().warn("Client requesting unhandled file!");
                    SocketUtil.send(os, "HTTP/1.0 404 Not Found\r\n\r\n".getBytes());
                    return;
                }

                SocketUtil.sendHeader(os, contentType);
                SocketUtil.send(os, Files.readAllBytes(file));
            } catch (final Exception e) {
                ServerInitializer.instance.getLogger().error(e.getMessage());
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }
}
