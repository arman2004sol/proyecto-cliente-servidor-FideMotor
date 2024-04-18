/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.CompraController;
import Modelo.Compra;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author armi8
 */
public class GestionarComprasPanel extends JPanel {
    private JTable table;
    private CompraController compraController;
    private JButton btnRegresar;
    private MainAppBusiness mainAppBusiness;

    public GestionarComprasPanel(MainAppBusiness mainAppBusiness) {
        this.mainAppBusiness = mainAppBusiness;
        setLayout(new BorderLayout());
        compraController = new CompraController();
        table = new JTable();
        configureTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Actualizar Estados");
        btnActualizar.addActionListener(e -> actualizarEstados());
        add(btnActualizar, BorderLayout.NORTH);

        btnRegresar = new JButton("Regresar al Menú Principal");
        btnRegresar.addActionListener(e -> mainAppBusiness.cambiarPanel("MenuPrincipalBusiness"));
        add(btnRegresar, BorderLayout.SOUTH);
    }

    private void configureTable() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer editable solo la columna de estado
                return column == 4;
            }
        };
        model.addColumn("ID Compra");
        model.addColumn("Cliente ID");
        model.addColumn("Vehículo");
        model.addColumn("Fecha");
        model.addColumn("Estado");
        table.setModel(model);
        loadCompras();
    }

    private void loadCompras() {
        List<Compra> compras = compraController.obtenerTodasLasCompras();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Compra compra : compras) {
            model.addRow(new Object[]{
                compra.getIdCompra(),
                compra.getIdUsuario(),
                compra.getVehiculo().getModelo(),
                compra.getFechaCompra().toString(),
                compra.getEstado()
            });
        }
    }

    private void actualizarEstados() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        boolean allUpdatesSuccessful = true;
        for (int i = 0; i < model.getRowCount(); i++) {
            int idCompra = (Integer) model.getValueAt(i, 0);
            String nuevoEstado = (String) model.getValueAt(i, 4);
            if (!compraController.actualizarEstadoCompra(idCompra, nuevoEstado)) {
                allUpdatesSuccessful = false;
                JOptionPane.showMessageDialog(this, "Error al actualizar el estado de la compra ID: " + idCompra, "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                break; 
            }
        }
        if (allUpdatesSuccessful) {
            JOptionPane.showMessageDialog(this, "Todos los estados se han actualizado correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        loadCompras(); 
    }
}
