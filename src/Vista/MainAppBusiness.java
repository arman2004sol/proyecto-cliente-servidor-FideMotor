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
public class MainAppBusiness extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainAppBusiness() {
        setTitle("FideMotor Business");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

       
        cardPanel.add(new InicioPanelBusiness(this), "InicioBusiness");
        cardPanel.add(new LoginBusiness(this), "LoginBusiness");
        cardPanel.add(new RegistroBusiness(this), "RegistroBusiness");

        cardPanel.add(new MenuPrincipalBusiness(this), "MenuPrincipalBusiness");
        cardPanel.add(new VisualizarComprasPanel(this), "VisualizarComprasPanel");
        cardPanel.add(new GestionarComprasPanel(this), "GestionarComprasPanel");
        cardPanel.add(new GestionarInventarioPanel(this), "GestionarInventarioPanel");

        add(cardPanel);
        cardLayout.show(cardPanel, "InicioBusiness"); 
    }

    public void cambiarPanel(String nombrePanel) {
        cardLayout.show(cardPanel, nombrePanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainAppBusiness().setVisible(true));
    }
}
