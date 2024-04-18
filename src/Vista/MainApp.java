/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author armi8
 */
public class MainApp extends JFrame {
   private CardLayout cardLayout;
   private JPanel cardPanel;

   public MainApp() {
       setTitle("FideMotor Platform");
       setSize(800, 600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       inicializarComponentes();
   }

   private void inicializarComponentes() {
       cardLayout = new CardLayout();  
       cardPanel = new JPanel(cardLayout);  

       
       cardPanel.add(new InicioPanel(this), "Inicio");
       cardPanel.add(new RegistroUsuarioPanel(this), "Registro");
       cardPanel.add(new InicioSesionPanel(this), "Login");
       cardPanel.add(new VisualizacionVehiculosPanel(this), "Ver Vehículos");
       cardPanel.add(new CompraVehiculoPanel(this), "CompraVehiculo");
       cardPanel.add(new MenuPrincipalPanel(this), "MenuPrincipal");

       add(cardPanel);  
       cardLayout.show(cardPanel, "Inicio");  
   }

   public void showHistorialCompras() {
       if (null == cardPanel.getClientProperty("HistorialCompras")) {
           HistorialComprasPanel historialPanel = new HistorialComprasPanel(this);
           cardPanel.add(historialPanel, "HistorialCompras");
           cardPanel.putClientProperty("HistorialCompras", true);
       }
       cambiarPanel("HistorialCompras");
   }

   public void showEditarUsuario() {
       if (null == cardPanel.getClientProperty("EditarUsuario")) {
           EditarUsuarioPanel editarPanel = new EditarUsuarioPanel(this);
           cardPanel.add(editarPanel, "EditarUsuario");
           cardPanel.putClientProperty("EditarUsuario", true);
       }
       cambiarPanel("EditarUsuario");
   }

   public void cambiarPanel(String nombrePanel) {
       cardLayout.show(cardPanel, nombrePanel);
   }

   public static void main(String[] args) {
       EventQueue.invokeLater(() -> new MainApp().setVisible(true));
   }
}
