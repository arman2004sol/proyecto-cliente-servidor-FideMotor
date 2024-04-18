/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DatabaseManager;
import Modelo.Vehiculo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;




/**
 *
 * @author armi8
 */
public class VehiculoController {
    public List<Vehiculo> listarVehiculosDisponibles() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculos";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehiculos.add(new Vehiculo(
                        rs.getInt("id_vehiculo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getBigDecimal("precio"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculos (marca, modelo, ano, precio, tipo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getMarca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setInt(3, vehiculo.getAno());
            stmt.setBigDecimal(4, vehiculo.getPrecio());
            stmt.setString(5, vehiculo.getTipo());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarVehiculo(Vehiculo vehiculo) {
    String sql = "UPDATE Vehiculos SET marca = ?, modelo = ?, ano = ?, precio = ?, tipo = ? WHERE id_vehiculo = ?";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
        stmt.setString(1, vehiculo.getMarca());
        stmt.setString(2, vehiculo.getModelo());
        stmt.setInt(3, vehiculo.getAno());
        stmt.setBigDecimal(4, vehiculo.getPrecio());
        stmt.setString(5, vehiculo.getTipo());
        stmt.setInt(6, vehiculo.getIdVehiculo());

        int result = stmt.executeUpdate();
        return result > 0; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean eliminarVehiculo(int idVehiculo) {
        String sql = "DELETE FROM Vehiculos WHERE id_vehiculo = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            stmt.setInt(1, idVehiculo);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean comprarVehiculo(int idUsuario, int idVehiculo, String formaPago) {
    String sql = "INSERT INTO Compras (id_usuario, id_vehiculo, forma_pago, estado) VALUES (?, ?, ?, 'enviado')";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idVehiculo);
        stmt.setString(3, formaPago);
        
        int result = stmt.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
}
