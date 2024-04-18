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
import javax.swing.JTextField;

/**
 *
 * @author armi8
 */
public class EditarUsuarioPanel extends JPanel {
    private JTextField txtNombre, txtApellidos, txtTelefono, txtDireccion;
    private JButton btnGuardarCambios, btnRegresar;
    private UsuarioController usuarioController;
    private MainApp mainApp; 

    public EditarUsuarioPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        this.usuarioController = new UsuarioController();
        setLayout(new GridLayout(6, 2, 5, 5));
        
        txtNombre = new JTextField();
        txtApellidos = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();
        btnGuardarCambios = new JButton("Guardar Cambios");
        btnRegresar = new JButton("Regresar al Menú Principal");

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellidos:"));
        add(txtApellidos);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(btnGuardarCambios);
        add(btnRegresar);

        btnGuardarCambios.addActionListener(this::guardarCambios);
        btnRegresar.addActionListener(e -> mainApp.cambiarPanel("MenuPrincipal"));

        cargarDatosUsuario();
    }

    private void cargarDatosUsuario() {
        Usuario usuario = UsuarioActual.getInstance().getUsuario();
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellidos.setText(usuario.getApellidos());
            txtTelefono.setText(usuario.getTelefono());
            txtDireccion.setText(usuario.getDireccion());
        }
    }

    private void guardarCambios(ActionEvent e) {
        Usuario usuario = UsuarioActual.getInstance().getUsuario();
        usuario.setNombre(txtNombre.getText());
        usuario.setApellidos(txtApellidos.getText());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setDireccion(txtDireccion.getText());
        
        boolean result = usuarioController.actualizarUsuario(usuario);
        if (result) {
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos.");
        }
    }
}
