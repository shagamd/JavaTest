import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author steven.munoz
 */
public class Conexion {

    private Connection conexion;

    private String url = "jdbc:postgresql://192.168.1.35:5432/compunetcalidad";

    public Conexion() throws SQLException {
        this.conexion = DriverManager.getConnection(url, "postgres", "pr4y2ct4s3gn4");
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (this.conexion != null && !this.conexion.isClosed()) {
            this.conexion.close();
        }
        this.conexion = null;
    }

}
