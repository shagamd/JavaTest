import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jstev
 */
public class ConexionMsSql {

    private static final String jdbcURL = "jdbc:sqlserver://SHAGA:1433;databasename=pruebas;encrypt=true;trustServerCertificate=true;";
    private static final String user = "shaga";
    private static final String password = "sepirot96.";

    private Connection conexion;

    public ConexionMsSql() throws SQLException {
        conexion = DriverManager.getConnection(jdbcURL, user, password);
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
        conexion = null;
    }

    public Connection getConexion() {
        return this.conexion;
    }

}
