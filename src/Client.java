import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

    static JFrame chatWindow = new JFrame("Chat Application");
    static JTextArea chatArea = new JTextArea(22, 45);
    static JTextField textField = new JTextField(45);
    static JLabel blankLabel = new JLabel("       ");
    static JButton sendButton = new JButton("Send");
    static BufferedReader in;
    static PrintWriter out;
    static JLabel nameLabel = new JLabel("        ");


    Client() {

        chatWindow.setLayout(new FlowLayout());
        chatWindow.add(nameLabel);
        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel);
        chatWindow.add(textField);
        chatWindow.add(sendButton);

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475, 500);
        chatWindow.setVisible(true);
        chatWindow.setResizable(false);

        textField.setEditable(false);
        chatArea.setEditable(false);

        sendButton.addActionListener(new Listener());
        textField.addActionListener(new Listener());

    }

    public static void main(String[] args) throws Exception {

        Client client = new Client();
        client.startChat();


    }

    void startChat() throws Exception {

        String ipAddress = JOptionPane.showInputDialog(
                chatWindow,
                "Enter IP Address",
                "IP Address Required!!",
                JOptionPane.PLAIN_MESSAGE);

        Socket soc = new Socket(ipAddress, 9000);
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);

        while (true) {
            String str = in.readLine();
            if (str.equals("NAMEREQUIRED")) {
                String name = JOptionPane.showInputDialog(
                        chatWindow,
                        "Enter a unique name :",
                        "Name Required!",
                        JOptionPane.PLAIN_MESSAGE);

                out.println(name);

            } else if (str.equals("NAMEALREADYEXISTS")) {
                String name = JOptionPane.showInputDialog(
                        chatWindow,
                        "Enter another name :",
                        "Name Required!",
                        JOptionPane.WARNING_MESSAGE);

                out.println(name);
            } else if (str.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
                System.out.println(str);
                nameLabel.setText("You are logged in as: " + str.substring(12));
            } else {
                chatArea.append(str + "\n");
            }


        }
    }
}




