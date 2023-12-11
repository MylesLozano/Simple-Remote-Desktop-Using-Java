package ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Authentication extends JFrame implements ActionListener {
    private Socket cSocket=null;
    DataOutputStream passchk=null;
    DataInputStream verification=null;
    String verify="";
    JButton submit;
    JPanel panel;
    JLabel label, label1;
    String width="", height="";
    JTextField text1;
    Authentication(Socket cSocket) {
        label1=new JLabel();
        label1.setText("Password");
        text1=new JTextField(15);
        this.cSocket=cSocket;
        label1=new JLabel();
        label1.setText("");
        this.setLayout(new BorderLayout());
        submit=new JButton("submit");
        panel=new JPanel(new GridLayout(2,1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label1);
        panel.add(submit);
        add(panel, BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("Login form");
    }
    public void actionPerformed(ActionEvent e) {
        String value1=text1.getText();
        try{
            passchk = new DataOutputStream(cSocket.getOutputStream());
            verification = new DataInputStream(cSocket.getInputStream());
            passchk.writeUTF(value1);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(verify.equals("valid"))
        {
            try {
                width = verification.readUTF();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                height=verification.readUTF();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            CreateFrame abc=new CreateFrame(cSocket,width,height);
            dispose();
        } else {
            System.out.println("please enter valid password");
            JOptionPane.showMessageDialog(this,"password is incorrect","Error",JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
}