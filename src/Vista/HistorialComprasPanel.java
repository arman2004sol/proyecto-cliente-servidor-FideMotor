/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.CompraController;
import Modelo.Compra;
import Modelo.Usuario;
import Modelo.UsuarioActual;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author armi8
 */
public class HistorialComprasPanel extends JPanel {
    private JList<String> listHistorial;
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private CompraController compraController = new CompraController();
    private JButton btnRegresar;
    private MainApp mainApp; 

    public HistorialComprasPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        listHistorial = new JList<>(modeloLista);
        listHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listHistorial);
        add(scrollPane, BorderLayout.CENTER);

        btnRegresar = new JButton("Regresar al Menú Principal");
        btnRegresar.addActionListener(e -> mainApp.cambiarPanel("MenuPrincipal"));
        add(btnRegresar, BorderLayout.SOUTH);

        cargarHistorialCompras();
    }

    private void cargarHistorialCompras() {
        Usuario usuario = UsuarioActual.getInstance().getUsuario();
        if (usuario != null) {
            int usuarioId = usuario.getIdUsuario();
            List<Compra> compras = compraController.obtenerHistorialComprasPorUsuario(usuarioId);
            for (Compra compra : compras) {
                modeloLista.addElement("Compra de " + compra.getVehiculo().getModelo() + 
                                        " - Estado: " + compra.getEstado() + 
                                        " - Fecha: " + compra.getFechaCompra().toString());
            }
        } else {
           
            modeloLista.addElement("Por favor, inicie sesión para ver el historial de compras.");
        }
    }
}


