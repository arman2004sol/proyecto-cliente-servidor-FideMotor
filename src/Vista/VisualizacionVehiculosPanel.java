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
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author armi8
 */
public class VisualizacionVehiculosPanel extends JPanel {
    private JList<Vehiculo> listVehiculos;
    private DefaultListModel<Vehiculo> modeloLista = new DefaultListModel<>();
    private VehiculoController vehiculoController = new VehiculoController();
    private JButton btnComprar, btnRegresar;

    public VisualizacionVehiculosPanel(MainApp mainApp) {
        setLayout(new BorderLayout());
        listVehiculos = new JList<>(modeloLista);
        listVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cargarVehiculos();

        btnComprar = new JButton("Comprar Vehículo");
        btnRegresar = new JButton("Regresar al Menú Principal");
        btnComprar.addActionListener(e -> comprarVehiculo());
        btnRegresar.addActionListener(e -> mainApp.cambiarPanel("MenuPrincipal"));

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(btnComprar, BorderLayout.WEST);
        southPanel.add(btnRegresar, BorderLayout.EAST);
        
        add(new JScrollPane(listVehiculos), BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }
      
    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoController.listarVehiculosDisponibles();
        vehiculos.forEach(modeloLista::addElement);
    }
    
    private void comprarVehiculo() {
        Vehiculo seleccionado = listVehiculos.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un vehículo para comprar.");
            return;
        }

        
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea proceder con la compra del vehículo seleccionado?",
                "Confirmar Compra",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            MainApp mainApp = (MainApp) SwingUtilities.getWindowAncestor(this);
            mainApp.cambiarPanel("CompraVehiculo");  
        }
    }
}
