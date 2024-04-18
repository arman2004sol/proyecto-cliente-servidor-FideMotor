package Modelo;


import Modelo.DatabaseManager;
import Modelo.Vehiculo;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author armi8
 */
   public class VehiculoDAO {
    public List<Vehiculo> obtenerVehiculosDisponibles() {
    List<Vehiculo> vehiculos = new ArrayList<>();
    String sql = "SELECT * FROM Vehiculos";
    try (Connection conn = DatabaseManager.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
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
    
    
}
