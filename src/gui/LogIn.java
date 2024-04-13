package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class LogIn extends JFrame implements ActionListener {

    private static final String ARCHIVO_USUARIO = "/Users/efrainhurtadorodriguez/Desktop/Universidad /Materias de 4to semestre/Tópicos Avanzados de Programación/Tareas/Log In/resources/usuarios.txt";
    private static final String DELIMITADOR = ":";
    private JTextField txtUsuario;
    private JPasswordField passwordField;

    public LogIn(){

        // Diseño de la ventana
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);

        // Titulo
        JLabel titulo = new JLabel("Log In");
        titulo.setFont(new Font("Comfortaa", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(207,30, 200, 25);
        add(titulo);

        // Usuario
        JLabel usuario = new JLabel("Usuario:");
        usuario.setFont(new Font("Comfortaa", Font.BOLD, 20));
        usuario.setForeground(Color.WHITE);
        usuario.setBounds(30, 110, 100, 25);
        add(usuario);

        // Texto usuario
        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Comfortaa", Font.BOLD, 20));
        txtUsuario.setBounds(200, 110, 200, 25);
        add(txtUsuario);

        // Contraseña
        JLabel contrasena = new JLabel("Contraseña:");
        contrasena.setFont(new Font("Comfortaa", Font.BOLD, 20));
        contrasena.setForeground(Color.WHITE);
        contrasena.setBounds(30, 160, 170, 25);
        add(contrasena);

        // Texto contraseña
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Comfortaa", Font.BOLD, 20));
        passwordField.setBounds(200, 160, 200, 25);
        add(passwordField);

        // Botón iniciar sesión
        JButton iniciarSesion = new JButton("Iniciar sesión");
        iniciarSesion.setFont(new Font("Comfortaa", Font.BOLD, 10));
        iniciarSesion.setBounds(170, 210, 150, 30);
        iniciarSesion.addActionListener(this);
        add(iniciarSesion);

        // Botón registrarse
        JButton registrarButton = new JButton("Registrarse");
        registrarButton.setFont(new Font("Comfortaa", Font.BOLD, 10));
        registrarButton.setBounds(325, 210, 150, 30);
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registro().setVisible(true);
            }
        });
        add(registrarButton);

        // Cargamos la fuente
        try {
            Font comfortafont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/efrainhurtadorodriguez/Desktop/Universidad /Materias de 4to semestre/Tópicos Avanzados de Programación/Tareas/Log In/resources/Comfortaa-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(comfortafont);
        } catch (IOException | FontFormatException e){
            e.printStackTrace();
        }

    }

    public static String hashPassword(String contraseña){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(contraseña.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes){
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, String> leerUsuarios() throws IOException{
        HashMap<String, String> usuarios = new HashMap<>();

        FileReader fileReader = new FileReader(ARCHIVO_USUARIO);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNext()){
            String linea = scanner.nextLine();
            String[] partes = linea.split(DELIMITADOR);

            String nombreUsuario = partes[0];
            String contraseñaEncriptada = partes[1];

            usuarios.put(nombreUsuario, contraseñaEncriptada);
        }

        fileReader.close();
        return usuarios;
    }

    private static boolean login(String nombreUsuario, String contraseñaEncriptada) throws IOException {
        HashMap<String, String> usuarios = leerUsuarios();

        if (usuarios.containsKey(nombreUsuario)){
            String contraseñaAlmacenada = usuarios.get(nombreUsuario);
            return contraseñaAlmacenada.equals(contraseñaEncriptada);
        }

        return false;
    }

    public void actionPerformed(ActionEvent e){
        String nombreUsuario = txtUsuario.getText();
        String contraseña = new String(passwordField.getPassword());

        if (nombreUsuario.isEmpty() || contraseña.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ingrese nombre de usuario y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contraseñaEncriptada = hashPassword(contraseña);
        try {
            if (login(nombreUsuario, contraseñaEncriptada)){
                JOptionPane.showMessageDialog(this, "¡Inicio de sesión correcto!");

                GhibliFrame ghibliFrame = new GhibliFrame();
                ghibliFrame.setVisible(true);

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
