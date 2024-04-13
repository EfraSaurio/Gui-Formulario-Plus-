import gui.Welcome;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
        @Override
            public void run() {
                Welcome welcome = new Welcome();
                welcome.showSplash();
            }
        });



    }
}