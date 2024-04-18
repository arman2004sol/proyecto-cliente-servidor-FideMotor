/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.UsuarioActual;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author armi8
 */
public class LogoutPanel extends JPanel {
    private JButton btnLogout;

    public LogoutPanel() {
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(this::logout);
        add(btnLogout);
    }

    private void logout(ActionEvent e) {
        UsuarioActual.getInstance().setUsuario(null); 
        MainApp mainApp = (MainApp) SwingUtilities.getWindowAncestor(this);
        mainApp.cambiarPanel("Login"); 
    }
}
