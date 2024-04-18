/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.UsuarioController;
import Modelo.Usuario;
import Modelo.UsuarioActual;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author armi8
 */
public class LoginBusiness extends JPanel {
    private JTextField txtIdentificacion = new JTextField(20);
    private JPasswordField txtPassword = new JPasswordField(20);
    private JButton btnIniciarSesion = new JButton("Iniciar Sesión");
    private JButton btnRegresar = new JButton("Regresar al Inicio");
    private MainAppBusiness mainAppBusiness;

    public LoginBusiness(MainAppBusiness mainAppBusiness) {
        this.mainAppBusiness = mainAppBusiness;
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Identificación:"));
        add(txtIdentificacion);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(btnIniciarSesion);
        add(btnRegresar);

        btnIniciarSesion.addActionListener(this::accionIniciarSesion);
        btnRegresar.addActionListener(e -> mainAppBusiness.cambiarPanel("InicioBusiness"));
    }

    private void accionIniciarSesion(ActionEvent e) {
        String identificacion = txtIdentificacion.getText();
        String password = new String(txtPassword.getPassword());
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.autenticarUsuario(identificacion, password);
        if (usuario != null) {
            UsuarioActual.getInstance().setUsuario(usuario);
            
            mainAppBusiness.cambiarPanel("MenuPrincipalBusiness");
        } else {
            JOptionPane.showMessageDialog(this, "Identificación o contraseña incorrecta.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}

