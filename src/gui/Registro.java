package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Registro extends JFrame implements ActionListener {

    private JTextField txtUsuarioRegistro;
    private JTextField passwordFieldRegistro;

    public Registro(){

        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);

        // Titulo
        JLabel titulo = new JLabel("Sign Up");
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
        txtUsuarioRegistro = new JTextField(20);
        txtUsuarioRegistro.setFont(new Font("Comfortaa", Font.BOLD, 20));
        txtUsuarioRegistro.setBounds(200, 110, 200, 25);
        add(txtUsuarioRegistro);

        // Contraseña
        JLabel contrasena = new JLabel("Contraseña:");
        contrasena.setFont(new Font("Comfortaa", Font.BOLD, 20));
        contrasena.setForeground(Color.WHITE);
        contrasena.setBounds(30, 160, 170, 25);
        add(contrasena);

        // Texto contraseña
        passwordFieldRegistro = new JTextField(20);
        passwordFieldRegistro.setFont(new Font("Comfortaa",Font.BOLD, 20));
        passwordFieldRegistro.setBounds(200, 160, 200, 25);
        add(passwordFieldRegistro);

        // Botón registrar
        JButton botonRegistar = new JButton("Registrarse");
        botonRegistar.setFont(new Font("Comfortaa", Font.BOLD, 10));
        botonRegistar.setBounds(170, 210, 150, 30);
        botonRegistar.addActionListener(this);
        add(botonRegistar);
    }

    public void actionPerformed(ActionEvent e){
        String nombreUsuarioRegistro = txtUsuarioRegistro.getText();
        String contraseñaRegistro = passwordFieldRegistro.getText();

        if (nombreUsuarioRegistro.isEmpty() || contraseñaRegistro.isEmpty()){
            JOptionPane.showMessageDialog(this, "Ingrese nombre de usuario y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contraseñaEncriptadaRegistro = LogIn.hashPassword(contraseñaRegistro);

        try {
            registrarUsuario(nombreUsuarioRegistro, contraseñaEncriptadaRegistro);
            JOptionPane.showMessageDialog(this, "¡Usuario registrado correctamente!");
            this.dispose(); // cerrar ventana de registro
        } catch (IOException ex){
            JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void registrarUsuario(String nombreUsuario, String contraseñaEncriptada) throws IOException{

        //Obtener el directorio de recursos
        Path resourceDirectory = Path.of("resources");

        // Crea la ruta del archivo de registro
        Path registrationFilePath = resourceDirectory.resolve("usuarios.txt");

        // abrir el archivo de usuarios en modo append (añadir)
        FileWriter fileWriter = new FileWriter(registrationFilePath.toFile(), true );

        // Escribir la línea con el nombre de usuario y contraseña encriptada
        fileWriter.write(nombreUsuario + ":" + contraseñaEncriptada + "\n");

        // Cerrar el archivo
        fileWriter.close();
    }

}
