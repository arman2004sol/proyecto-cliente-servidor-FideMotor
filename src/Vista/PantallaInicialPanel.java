/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author armi8
 */
public class PantallaInicialPanel extends JPanel {
    private MainApp mainApp;

    public PantallaInicialPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridLayout(2, 1, 10, 10)); 

        JButton btnFideMotorApp = new JButton("FideMotorApp");
        JButton btnFideMotorBusiness = new JButton("FideMotorBusiness");

        btnFideMotorApp.addActionListener(this::accionFideMotorApp);
        btnFideMotorBusiness.addActionListener(this::accionFideMotorBusiness);

        add(btnFideMotorApp);
        add(btnFideMotorBusiness);
    }

    private void accionFideMotorApp(ActionEvent e) {
        mainApp.cambiarPanel("InicioPanel"); 
    }

    private void accionFideMotorBusiness(ActionEvent e) {
        mainApp.cambiarPanel("InicioPanelBusiness"); 
    }
}
