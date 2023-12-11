package ServerSide;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start {//IT FINALLY WORKS!!!
    public static void main(String[] args)
    {
        SetPassword frame1=new SetPassword();
        frame1.setSize(400,80);
        frame1.setLocation(500,300);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }
}