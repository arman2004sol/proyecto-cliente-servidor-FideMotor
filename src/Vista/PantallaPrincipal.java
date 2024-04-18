/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author armi8
 */
public class PantallaPrincipal extends JFrame {

    public PantallaPrincipal() {
        super("FideMotor Selection");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); 

        JButton btnApp = new JButton("FideMotor App");
        JButton btnBusiness = new JButton("FideMotor Business");

        btnApp.addActionListener(this::openFideMotorApp);
        btnBusiness.addActionListener(this::openFideMotorBusiness);

        buttonPanel.add(btnApp);
        buttonPanel.add(btnBusiness);

        this.add(buttonPanel);
    }

    private void openFideMotorApp(ActionEvent event) {
        System.out.println("Launching FideMotor App...");
        MainApp mainApp = new MainApp();
        mainApp.setVisible(true);
    }

    private void openFideMotorBusiness(ActionEvent event) {
        System.out.println("Launching FideMotor Business...");
        MainAppBusiness mainAppBusiness = new MainAppBusiness();
        mainAppBusiness.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new PantallaPrincipal().setVisible(true));
    }
}
