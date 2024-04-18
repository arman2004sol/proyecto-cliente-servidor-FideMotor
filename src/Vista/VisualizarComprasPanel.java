/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.CompraController;
import Modelo.Compra;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author armi8
 */
public class VisualizarComprasPanel extends JPanel {
    private JTable table;
    private CompraController controller;
    private JButton btnRegresar;
    private MainAppBusiness mainAppBusiness; 

    public VisualizarComprasPanel(MainAppBusiness mainAppBusiness) {
        this.mainAppBusiness = mainAppBusiness;
        setLayout(new BorderLayout());
        controller = new CompraController();
        table = new JTable();
        configureTable();
        loadCompras();
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRegresar = new JButton("Regresar al Menú Principal");
        btnRegresar.addActionListener(e -> mainAppBusiness.cambiarPanel("MenuPrincipalBusiness"));
        add(btnRegresar, BorderLayout.SOUTH);
    }

    private void configureTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Compra");
        model.addColumn("Cliente ID");
        model.addColumn("Vehículo");
        model.addColumn("Estado");
        model.addColumn("Fecha");
        model.addColumn("Forma de Pago");
        table.setModel(model);
    }

    private void loadCompras() {
        List<Compra> compras = controller.obtenerTodasLasCompras(); 
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (Compra compra : compras) {
            model.addRow(new Object[]{
                compra.getIdCompra(),
                compra.getIdUsuario(),
                compra.getVehiculo().getModelo() + " - " + compra.getVehiculo().getMarca() + " (" + compra.getVehiculo().getAno() + ")",
                compra.getEstado(),
                compra.getFechaCompra().toString(),
                compra.getFormaPago()
            });
        }
    }
}
