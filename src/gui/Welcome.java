package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JWindow {

    private static final String LOGO_PATH = "/Users/efrainhurtadorodriguez/Desktop/Universidad /Materias de 4to semestre/Tópicos Avanzados de Programación/Tareas/Log In/resources/totoro.png";

    public void showSplash(){

        JPanel content = (JPanel) getContentPane();

        // Configuramos el diseño de la ventana de inicio
        int width = 1000;
        int height = 700;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Se configura el contenido de la ventana de inicio
        JLabel label = new JLabel(new ImageIcon(LOGO_PATH));
        label.setOpaque(false);
        content.add(label, BorderLayout.CENTER);

        // Creamos el efecto de transición gradual
        float startOpacity = 0.0f;
        float endOpacity = 1.0f;
        //long duration = 2000;
        long startTime = System.currentTimeMillis();

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long now = System.currentTimeMillis();
                long elapsedTime = now - startTime;
                float fraction = (float) elapsedTime; //duration
                if (fraction > 1.0f){
                    fraction = 1.0f;
                    ((Timer) e.getSource()).stop();
                }
                float opacity = startOpacity + fraction * (endOpacity - startOpacity);
                setOpacity(opacity);
            }
        });
        timer.setRepeats(true);
        timer.start();

        // Configuramos la ventana para cerrarse en 5 segundos
        Timer Closetimer = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        LogIn log = new LogIn();
                        log.setVisible(true);
                    }
                });
            }
        });
        Closetimer.setRepeats(false);
        Closetimer.start();

        // Configuramos la visibilidad dela ventana de inicio
        setOpacity(0.0f);
        setBackground(new Color(0,0,0,0));
        setVisible(true);

        // Creamos el efecto difuminado
        for (float opacity = 0.0f; opacity <= 1.0f; opacity += 0.01f){
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
            setOpacity(opacity);


        }
    }

}
