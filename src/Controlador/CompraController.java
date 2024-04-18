/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import Modelo.Compra;
import Modelo.DatabaseManager;
import Modelo.Vehiculo;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author armi8
 */
public class CompraController {

    public boolean realizarCompra(int idUsuario, int idVehiculo, String formaPago) {
        String sql = "INSERT INTO Compras (id_usuario, id_vehiculo, forma_pago, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection(); PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVehiculo);
            stmt.setString(3, formaPago);
            stmt.setString(4, "Enviado");
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Compra> obtenerHistorialComprasPorUsuario(int usuarioId) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT c.*, v.* FROM Compras c JOIN Vehiculos v ON c.id_vehiculo = v.id_vehiculo WHERE c.id_usuario = ? ORDER BY c.fecha_compra DESC";
        try (Connection conn = DatabaseManager.getInstance().getConnection(); PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("id_vehiculo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getBigDecimal("precio"),
                        rs.getString("tipo")
                );
                Compra compra = new Compra(
                        rs.getInt("id_compra"),
                        rs.getInt("id_usuario"),
                        vehiculo,
                        rs.getTimestamp("fecha_compra").toLocalDateTime(),
                        rs.getString("forma_pago"),
                        rs.getString("estado")
                );
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
    
    public List<Compra> obtenerTodasLasCompras() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT c.*, v.marca, v.modelo, v.ano, v.precio, v.tipo FROM Compras c " +
                     "JOIN Vehiculos v ON c.id_vehiculo = v.id_vehiculo";
        
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("id_vehiculo"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getBigDecimal("precio"),
                    rs.getString("tipo")
                );
                Compra compra = new Compra(
                    rs.getInt("id_compra"),
                    rs.getInt("id_usuario"),
                    vehiculo,
                    rs.getTimestamp("fecha_compra").toLocalDateTime(),
                    rs.getString("forma_pago"),
                    rs.getString("estado")
                );
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
    
    public boolean actualizarEstadoCompra(int idCompra, String nuevoEstado) {
        String sql = "UPDATE Compras SET estado = ? WHERE id_compra = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idCompra);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
