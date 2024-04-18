/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author armi8
 */
public class MenuPrincipalBusiness extends JPanel {
    private MainAppBusiness mainAppBusiness;

    public MenuPrincipalBusiness(MainAppBusiness mainAppBusiness) {
        this.mainAppBusiness = mainAppBusiness;
        setLayout(new GridLayout(3, 1, 10, 10));  
        JButton btnVerTodasCompras = new JButton("Visualizar todas las compras");
        JButton btnGestionarEstados = new JButton("Gestionar estados de compras");
        JButton btnEditarInventario = new JButton("Editar inventario de vehículos");

        btnVerTodasCompras.addActionListener(e -> mainAppBusiness.cambiarPanel("VisualizarComprasPanel"));
        btnGestionarEstados.addActionListener(e -> mainAppBusiness.cambiarPanel("GestionarComprasPanel"));
        btnEditarInventario.addActionListener(e -> mainAppBusiness.cambiarPanel("GestionarInventarioPanel"));

        add(btnVerTodasCompras);
        add(btnGestionarEstados);
        add(btnEditarInventario);
    }
}
