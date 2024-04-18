/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.VehiculoController;
import Modelo.Vehiculo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author armi8
 */
public class GestionarInventarioPanel extends JPanel {
    private JTable table;
    private VehiculoController vehiculoController;
    private JTextField txtMarca, txtModelo, txtAno, txtPrecio, txtTipo;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnRegresar;
    private MainAppBusiness mainAppBusiness; 

    public GestionarInventarioPanel(MainAppBusiness mainAppBusiness) {
        this.mainAppBusiness = mainAppBusiness;
        setLayout(new BorderLayout());
        vehiculoController = new VehiculoController();
        initializeUI();
    }

    private void initializeUI() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        txtMarca = new JTextField();
        txtModelo = new JTextField();
        txtAno = new JTextField();
        txtPrecio = new JTextField();
        txtTipo = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar seleccionado");
        btnEliminar = new JButton("Eliminar seleccionado");
        btnRegresar = new JButton("Regresar al Menú Principal");

        formPanel.add(new JLabel("Marca:"));
        formPanel.add(txtMarca);
        formPanel.add(new JLabel("Modelo:"));
        formPanel.add(txtModelo);
        formPanel.add(new JLabel("Año:"));
        formPanel.add(txtAno);
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(txtPrecio);
        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(txtTipo);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnRegresar);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setupTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarVehiculo());
        btnActualizar.addActionListener(e -> actualizarVehiculo());
        btnEliminar.addActionListener(e -> eliminarVehiculo());
        btnRegresar.addActionListener(e -> mainAppBusiness.cambiarPanel("MenuPrincipalBusiness"));
    }

    private void setupTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Marca");
        model.addColumn("Modelo");
        model.addColumn("Año");
        model.addColumn("Precio");
        model.addColumn("Tipo");
        table.setModel(model);
        loadVehiculos();
    }

    private void loadVehiculos() {
        List<Vehiculo> vehiculos = vehiculoController.listarVehiculosDisponibles();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Vehiculo v : vehiculos) {
            model.addRow(new Object[]{v.getIdVehiculo(), v.getMarca(), v.getModelo(), v.getAno(), v.getPrecio().toString(), v.getTipo()});
        }
    }

    private void agregarVehiculo() {
        try {
            Vehiculo vehiculo = new Vehiculo(
                0,
                txtMarca.getText(),
                txtModelo.getText(),
                Integer.parseInt(txtAno.getText()),
                new BigDecimal(txtPrecio.getText()),
                txtTipo.getText()
            );
            if (vehiculoController.agregarVehiculo(vehiculo)) {
                JOptionPane.showMessageDialog(this, "Vehículo agregado correctamente");
                loadVehiculos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar vehículo");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, verifica que los datos numéricos sean correctos.");
        }
    }

    private void actualizarVehiculo() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
        int idVehiculo = (int) table.getValueAt(selectedRow, 0);
        Vehiculo vehiculo = new Vehiculo(
            idVehiculo,
            txtMarca.getText(),
            txtModelo.getText(),
            Integer.parseInt(txtAno.getText()),
            new BigDecimal(txtPrecio.getText()),
            txtTipo.getText()
        );
        if (vehiculoController.actualizarVehiculo(vehiculo)) {
            JOptionPane.showMessageDialog(this, "Vehículo actualizado correctamente");
            loadVehiculos(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar vehículo. Asegúrate de que los datos sean correctos.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un vehículo para actualizar.");
    }
}
    

    private void eliminarVehiculo() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idVehiculo = (int) table.getValueAt(selectedRow, 0);
            if (vehiculoController.eliminarVehiculo(idVehiculo)) {
                JOptionPane.showMessageDialog(this, "Vehículo eliminado correctamente");
                loadVehiculos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar vehículo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo para eliminar.");
        }
    }
}
