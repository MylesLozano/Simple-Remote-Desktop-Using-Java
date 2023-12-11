package ClientSide;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;

public class ReceivingScreen extends Thread {
    private ObjectInputStream cObjectInputStream = null;
    private JPanel cPanel = null;
    private boolean continueLoop = true;
    InputStream oin = null;
    Image image1 = null;

    public ReceivingScreen(InputStream in, JPanel p) {
        oin = in;
        cPanel = p;
        start();
    }

    public void run() {
        try {
            while (true) {
                byte[] lengthBytes = new byte[4];
                oin.read(lengthBytes, 0, 4);

                int length = ((lengthBytes[0] & 0xFF) << 24) |
                        ((lengthBytes[1] & 0xFF) << 16) |
                        ((lengthBytes[2] & 0xFF) << 8) |
                        (lengthBytes[3] & 0xFF);

                byte[] imageBytes = new byte[length];
                int bytesRead = 0;

                while (bytesRead < length) {
                    bytesRead += oin.read(imageBytes, bytesRead, length - bytesRead);
                }

                image1 = ImageIO.read(new ByteArrayInputStream(imageBytes));
                image1 = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);

                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}