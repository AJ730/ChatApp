import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ConversationHandler extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;

    public ConversationHandler(Socket socket) throws IOException {
        this.socket = socket;
    }


    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            int count = 0;
            while (true) {
                if (count > 0) {
                    out.println("NAMEALREADYEXISTS");
                } else {
                    out.println("NAMEREQUIRED");
                }
                name = in.readLine();

                if (name == null) {
                    return;
                }

                if (!Server.userNames.contains(name)) {
                    Server.userNames.add(name);
                    break;
                }

                count++;

            }
            out.println("NAMEACCEPTED");
            Server.printWriters.add(out);



        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
