/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.UsuarioActual;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author armi8
 */
public class MenuPrincipalPanel extends JPanel {
    private JButton btnVerVehiculos;
    private JButton btnRealizarCompra;
    private JButton btnEditarDatos;
    private JButton btnVerCompras;
    private JButton btnSalirSesion;
    private MainApp mainApp; 

    public MenuPrincipalPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(5, 1, 10, 10));  

        btnVerVehiculos = new JButton("Visualizar vehículos");
        btnRealizarCompra = new JButton("Realizar compra");
        btnEditarDatos = new JButton("Editar datos");
        btnVerCompras = new JButton("Visualizar compras realizadas");
        btnSalirSesion = new JButton("Salir de la sesión");

        btnVerVehiculos.addActionListener(e -> mainApp.cambiarPanel("Ver Vehículos"));
        btnRealizarCompra.addActionListener(e -> mainApp.cambiarPanel("CompraVehiculo"));
        btnEditarDatos.addActionListener(e -> mainApp.showEditarUsuario()); 
        btnVerCompras.addActionListener(e -> mainApp.showHistorialCompras()); 
        btnSalirSesion.addActionListener(e -> {
            UsuarioActual.getInstance().setUsuario(null);
            mainApp.cambiarPanel("Login");
        });

        add(btnVerVehiculos);
        add(btnRealizarCompra);
        add(btnEditarDatos);
        add(btnVerCompras);
        add(btnSalirSesion);
    }
}