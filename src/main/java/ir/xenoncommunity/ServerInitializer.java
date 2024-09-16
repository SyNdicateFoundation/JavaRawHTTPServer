package ir.xenoncommunity;

import ir.xenoncommunity.socket.HTTPServer;
import ir.xenoncommunity.util.TaskManager;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;


@Getter
@Setter
public class ServerInitializer {
    public static ServerInitializer instance ;
    private String[] args;
    private Logger logger;
    private TaskManager taskManager;
    public ServerInitializer(String[] args){
        instance = this;
        this.args = args;
        this.logger = LogManager.getLogger(this.getClass().getSimpleName());
        this.taskManager = new TaskManager();
    }
    public void init() {
        logger.info("Starting JavaRawHTTPServer...");
        try{
            final InetAddress inetAddress = new InetSocketAddress("0.0.0.0", 2008).getAddress();
            HTTPServer.startHTTPServer(new ServerSocket(2008, 50, inetAddress));
        }catch(final Exception e){
            getLogger().error(e.getMessage());
        }
    }
}
