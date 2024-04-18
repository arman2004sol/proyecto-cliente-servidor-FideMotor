/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.VehiculoController;
import Modelo.Vehiculo;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import Controlador.CompraController;
import Modelo.Usuario;
import Modelo.UsuarioActual;
import java.awt.GridLayout;

/**
 *
 * @author armi8
 */
public class CompraVehiculoPanel extends JPanel {
   private JList<Vehiculo> listVehiculos;
    private DefaultListModel<Vehiculo> modeloLista = new DefaultListModel<>();
    private VehiculoController vehiculoController = new VehiculoController();
    private CompraController compraController = new CompraController();
    private JComboBox<String> cbMetodoPago;
    private JTextField txtNumeroTarjeta;
    private JButton btnComprar, btnRegresar;
    private MainApp mainApp; 

    public CompraVehiculoPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        listVehiculos = new JList<>(modeloLista);
        listVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cargarVehiculos();

        cbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta de Crédito"});
        cbMetodoPago.addActionListener(e -> toggleTarjetaInput());

        txtNumeroTarjeta = new JTextField(16);
        txtNumeroTarjeta.setVisible(false);

        btnComprar = new JButton("Comprar Vehículo");
        btnComprar.addActionListener(e -> realizarCompra());

        btnRegresar = new JButton("Regresar al Menú Principal");
        btnRegresar.addActionListener(e -> mainApp.cambiarPanel("MenuPrincipal"));

        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.add(btnComprar);
        southPanel.add(btnRegresar);

        add(new JScrollPane(listVehiculos), BorderLayout.CENTER);
        add(cbMetodoPago, BorderLayout.NORTH);
        add(txtNumeroTarjeta, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoController.listarVehiculosDisponibles();
        vehiculos.forEach(modeloLista::addElement);
    }

    private void toggleTarjetaInput() {
    txtNumeroTarjeta.setVisible(cbMetodoPago.getSelectedItem().equals("Tarjeta de Crédito"));
    revalidate();
    repaint();
}

    private void realizarCompra() {
    Vehiculo vehiculo = listVehiculos.getSelectedValue();
    String metodoPago = (String) cbMetodoPago.getSelectedItem();
    if (vehiculo == null) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un vehículo para comprar.", "Error de Compra", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (metodoPago.equals("Tarjeta de Crédito") && !validarTarjeta(txtNumeroTarjeta.getText())) {
        JOptionPane.showMessageDialog(this, "Número de tarjeta inválido. Solo se admiten VISA o Mastercard.", "Error de Pago", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Usuario usuario = UsuarioActual.getInstance().getUsuario();
    if (usuario == null) {
        JOptionPane.showMessageDialog(this, "No hay usuario logueado.", "Error de Sesión", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    int idUsuario = usuario.getIdUsuario();
    boolean exito = compraController.realizarCompra(idUsuario, vehiculo.getIdVehiculo(), metodoPago);

    if (exito) {
        JOptionPane.showMessageDialog(this, "¡Felicidades por la compra de su nuevo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "!", "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Error al procesar la compra.", "Error de Compra", JOptionPane.ERROR_MESSAGE);
    }
}

 private boolean validarTarjeta(String numero) {
    
    String numeroLimpio = numero.replaceAll("[\\s-]", "");
    return numeroLimpio.matches("^(4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$");
}
}
