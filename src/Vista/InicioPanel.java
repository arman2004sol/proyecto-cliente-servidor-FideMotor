/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author armi8
 */
public class InicioPanel extends JPanel {
    private MainApp mainApp;

    public InicioPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(3, 1));  

        JButton btnRegistrar = new JButton("Registrar Usuario");
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnSalir = new JButton("Salir del Programa"); 

        btnRegistrar.addActionListener(this::accionRegistrar);
        btnIniciarSesion.addActionListener(this::accionIniciarSesion);
        btnSalir.addActionListener(e -> System.exit(0)); 

        add(btnRegistrar);
        add(btnIniciarSesion);
        add(btnSalir);
    }

    private void accionRegistrar(ActionEvent e) {
        mainApp.cambiarPanel("Registro");
    }

    private void accionIniciarSesion(ActionEvent e) {
        mainApp.cambiarPanel("Login");
    }
}
