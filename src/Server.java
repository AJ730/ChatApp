import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<String> userNames = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();


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

