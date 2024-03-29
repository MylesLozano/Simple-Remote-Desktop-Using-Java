package ClientSide;

import javax.swing.JOptionPane;
import java.net.Socket;


public class Start {
    static String port = "6969";

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Please enter Server IP: ");
        new Start().initialize(ip, Integer.parseInt(port));
    }

    public void initialize(String ip, int port) {
        try {
            Socket sc = new Socket(ip, port);
            JOptionPane.showMessageDialog(null,"Connecting to the server");
            Authentication frame1 = new Authentication(sc);
            frame1.setSize(350, 150);  // Set a reasonable size
            frame1.setLocationRelativeTo(null);  // Center the frame on the screen
            frame1.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
