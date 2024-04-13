package gui;

import javax.swing.*;
import java.awt.*;

public class GhibliFrame extends JFrame{

    public GhibliFrame(){
        JFrame frame = new JFrame("Ghibli");

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GhibliPanel ghibliPanel = new GhibliPanel();
        frame.getContentPane().add(ghibliPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Siguiente");
        nextButton.setFont(new Font("Comfortaa", Font.BOLD, 15));
        nextButton.addActionListener(e -> ghibliPanel.nextImage());
        frame.getContentPane().add(nextButton, BorderLayout.EAST);

        JButton previousButton = new JButton("Anterior");
        previousButton.setFont(new Font("Comfortaa", Font.BOLD, 15));
        previousButton.addActionListener(e -> ghibliPanel.previousImage());
        frame.getContentPane().add(previousButton, BorderLayout.WEST);

        frame.setVisible(true);
    }

}
