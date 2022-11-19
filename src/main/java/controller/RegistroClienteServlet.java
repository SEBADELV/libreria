package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.cliente;
import model.data.DBGenerator;
import model.data.gestorLibreria.ClienteDAO;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegistroClienteServlet", value = "/registroCliente")
public class RegistroClienteServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("ClientesBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //La respuesta que le vamos a devolver, va a ser la dirección del archivo JSP registroUsuario.jsp
        RequestDispatcher respuesta = req.getRequestDispatcher("/agregarClientes.jsp");
        //envía la respuesta
        respuesta.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if(req.getParameter("edad").length()!=0 && req.getParameter("nombre").length()!=0  &&
                req.getParameter("rut").length()!=0){
            String nombre = req.getParameter("nombre");
            String correo = req.getParameter("correo");
            String rut = req.getParameter("rut");
            cliente cliente = new cliente(nombre, correo, rut);
            try {
                if(agregarCliente(cliente)){
                    req.setAttribute("usuario",cliente);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }
    private boolean agregarCliente(cliente cliente) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("UsuariosBD");
        List<cliente> clientes= ClienteDAO.obtenerCliente(query,"rut",cliente.getRut());
        if(clientes.size()!=0){
            return false;
        }
        else{
            ClienteDAO.agregarCliente(query,cliente);
            return true;
        }
    }
}