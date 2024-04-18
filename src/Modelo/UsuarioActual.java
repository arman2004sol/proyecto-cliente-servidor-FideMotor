/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author armi8
 */
public class UsuarioActual {
    private static UsuarioActual instance;
    private Usuario usuario;

    private UsuarioActual() {}

    public static UsuarioActual getInstance() {
        if (instance == null) {
            instance = new UsuarioActual();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
