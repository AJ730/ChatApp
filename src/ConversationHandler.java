import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConversationHandler extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;


    public ConversationHandler(Socket socket) throws IOException {
        this.socket = socket;

    }

    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
