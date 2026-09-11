package com.mjallerp.appwebjavaservletjsp.drivers;

import com.mjallerp.appwebjavaservletjsp.model.CRUDUser;
import com.mjallerp.appwebjavaservletjsp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ServletUser", urlPatterns = {"/user"})
public class ServletUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String accion = request.getParameter("accion");
            if (accion.equals("agregar")) {
                CRUDUser crudUser = new CRUDUser();
                crudUser.getUser().setId(request.getParameter("id"));
                crudUser.getUser().setName(request.getParameter("name"));
                crudUser.getUser().setPassword(request.getParameter("password"));
                crudUser.getUser().setRole(request.getParameter("role"));
                crudUser.agregarUsuario();
                response.sendRedirect("web/users/agregar.jsp?mensaje=Usuario " + request.getParameter("id") + " Agregado al Sistema");
            }
            else if (accion.equals("buscar")){
                User user = CRUDUser.consultarUsuario(request.getParameter("id"));
                request.getSession().setAttribute("user.buscar", user);
                String redireccion = request.getParameter("redir");
                if ("eliminar".equals(redireccion)) {
                    response.sendRedirect("web/users/eliminar.jsp");
                } else if ("modificar".equals(redireccion)) {
                    response.sendRedirect("web/users/modificar.jsp");
                } else {
                    response.sendRedirect("web/users/buscar.jsp");
                }
            }
            else if (accion.equals("modificar")) {
                CRUDUser crudUser = new CRUDUser();
                crudUser.getUser().setId(request.getParameter("id"));
                crudUser.getUser().setName(request.getParameter("name"));
                crudUser.getUser().setPassword(request.getParameter("password"));
                crudUser.getUser().setRole(request.getParameter("role"));
                crudUser.modificarUsuario();
                response.sendRedirect("web/users/modificar.jsp?mensaje=Usuario " + request.getParameter("id") + " Modificado en el Sistema");
            }
            else if (accion.equals("eliminar")) {
                CRUDUser crudUser = new CRUDUser();
                crudUser.getUser().setId(request.getParameter("id"));
                crudUser.eliminarUsuario();
                response.sendRedirect("web/users/eliminar.jsp?mensaje=Usuario " + request.getParameter("id") + " Eliminado del Sistema");
            }
            else if (accion.equals("listartodo")) {
                User[] listado = CRUDUser.listarTodosLosUsuarios();
                request.getSession().setAttribute("user.listar", listado);
                response.sendRedirect("web/users/listar.jsp");
            }
            else if (accion.equals("login")){
                User user = CRUDUser.iniciarsesion(request.getParameter("id"), request.getParameter("password"));
                request.getSession().setAttribute("user.login", user);
                response.sendRedirect("index.jsp?mensaje=Bienvenido al Sistema");
            }
            else if (accion.equals("salir")){
                request.getSession().setAttribute("user.login", null);
                request.getSession().invalidate();
                response.sendRedirect("index.jsp?mensaje=Sesion Cerrada");
            }
            else {
                response.sendRedirect("web/users/mensaje.jsp?mensaje=La Accion Solicitada no es Correcta");
            }
        } catch (Exception error) {
            response.sendRedirect("web/users/mensaje.jsp?mensaje=" + error.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
