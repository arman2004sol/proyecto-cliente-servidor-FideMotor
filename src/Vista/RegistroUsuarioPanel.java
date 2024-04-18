/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.UsuarioController;
import Modelo.Usuario;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RegistroUsuarioPanel extends JPanel {
   
    private JTextField txtNombre = new JTextField();
    private JTextField txtApellidos = new JTextField();
    private JTextField txtIdentificacion = new JTextField();
    private JTextField txtTelefono = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private JTextField txtDireccion = new JTextField();
    private JTextField txtSexo = new JTextField();
    private JTextField txtFechaNacimiento = new JTextField(); 
    private JButton btnRegistrar = new JButton("Registrar");
    private UsuarioController usuarioController = new UsuarioController();
    private MainApp mainApp; 

   
    public RegistroUsuarioPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(9, 2, 5, 5)); 
        addComponents();
        addActionListeners();
    }

    private void addComponents() {
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellidos:"));
        add(txtApellidos);
        add(new JLabel("Identificación:"));
        add(txtIdentificacion);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Sexo (M/F):"));
        add(txtSexo);
        add(new JLabel("Fecha de Nacimiento (yyyy-mm-dd):")); 
        add(txtFechaNacimiento);
        add(btnRegistrar);
    }

    private void addActionListeners() {
        btnRegistrar.addActionListener((ActionEvent e) -> {
            try {
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = sdf.parse(txtFechaNacimiento.getText());

                Usuario usuario = new Usuario(
                        0, 
                        txtNombre.getText(),
                        txtApellidos.getText(),
                        txtIdentificacion.getText(),
                        txtTelefono.getText(),
                        new String(txtPassword.getPassword()),
                        txtDireccion.getText(),
                        txtSexo.getText().charAt(0),
                        fechaNacimiento, 
                        "cliente" 
                );
                boolean registrado = usuarioController.registrarUsuario(usuario);
                if (registrado) {
                    JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente. Por favor, inicie sesión.");
                    mainApp.cambiarPanel("Login"); 
                } else {
                    JOptionPane.showMessageDialog(this, "Error registrando usuario.");
                }
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(this, "Error en el formato de la fecha de nacimiento. Utilice el formato yyyy-mm-dd.");
            }
        });
    }
}
