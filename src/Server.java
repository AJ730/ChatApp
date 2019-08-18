import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("Waiting for clients ...");
        ServerSocket ss = new ServerSocket(9000);
        while (true) {
            Socket soc = ss.accept();
            System.out.println("connection established");
            ConversationHandler handler = new ConversationHandler(soc);
            handler.start();
        }
    }
}

