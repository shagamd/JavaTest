import com.mycompany.pruebas.application.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author steven.munoz
 */
public class Peticiones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    JsonObjectBuilder jobRespuesta = Json.createObjectBuilder();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        try {
            Conexion con = new Conexion();
            int accion = Integer.parseInt(request.getParameter("accion"));
            switch (accion) {
                case 1:
                    obtenerUsuarios(con);
                    break;
                default:
                    jobRespuesta.add("estado", true);
                    jobRespuesta.add("msg", "No se recibio peticion valida");
                    break;
            }
            con.cerrarConexion();
        } catch (Exception e) {
            jobRespuesta.add("error", e.getMessage());
            jobRespuesta.add("estado", false);
        } finally {
            out.print(jobRespuesta.build());
            out.flush();
        }
    }

    protected void obtenerUsuarios(Conexion con) throws SQLException {
        JsonArrayBuilder jar = Json.createArrayBuilder();
        String sql = "select tipousuario.descripcion, login, nombres, apellidos, identificacion, emailcontacto, direccion "
                + "from usuario join tipousuario using(idtipousuario) join persona using(idpersona) limit 10;";
        try ( PreparedStatement statement = con.getConexion().prepareStatement(sql)) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                JsonObjectBuilder job = Json.createObjectBuilder();
                job.add("descripcion", res.getString("descripcion"));
                job.add("login", res.getString("login"));
                job.add("nombres", res.getString("nombres"));
                job.add("apellidos", res.getString("apellidos"));
                job.add("identificacion", res.getString("identificacion"));
                job.add("emailcontacto", res.getString("emailcontacto"));
                job.add("direccion", res.getString("direccion"));
                jar.add(job);
            }
        }
        jobRespuesta.add("data", jar);
        jobRespuesta.add("estado", true);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
