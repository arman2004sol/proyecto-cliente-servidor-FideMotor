/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DatabaseManager;
import Modelo.Usuario;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author armi8
 */
public class UsuarioController {
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombre, apellidos, identificacion, telefono, password, direccion, sexo, fecha_nacimiento, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getIdentificacion());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, usuario.getDireccion());
            stmt.setString(7, String.valueOf(usuario.getSexo()));
            stmt.setDate(8, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            stmt.setString(9, usuario.getTipoUsuario());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario autenticarUsuario(String identificacion, String password) {
    String sql = "SELECT * FROM Usuarios WHERE identificacion = ? AND password = ?";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
        stmt.setString(1, identificacion);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("apellidos"),
                rs.getString("identificacion"),
                rs.getString("telefono"),
                rs.getString("password"),
                rs.getString("direccion"),
                rs.getString("sexo").charAt(0),
                rs.getDate("fecha_nacimiento"),
                rs.getString("tipo_usuario")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
     public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nombre = ?, apellidos = ?, telefono = ?, direccion = ? WHERE id_usuario = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getTelefono());
            stmt.setString(4, usuario.getDireccion());
            stmt.setInt(5, usuario.getIdUsuario());
            
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
