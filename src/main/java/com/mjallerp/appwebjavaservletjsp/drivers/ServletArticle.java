package com.mjallerp.appwebjavaservletjsp.drivers;

import com.mjallerp.appwebjavaservletjsp.model.Article;
import com.mjallerp.appwebjavaservletjsp.model.CRUDArticle;
import com.mjallerp.appwebjavaservletjsp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ServletArticle", urlPatterns = {"/article"})
public class ServletArticle extends HttpServlet {

    private static double parseDouble(String valor) {
        if (valor == null || valor.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(valor);
    }

    private static int parseInt(String valor) {
        if (valor == null || valor.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(valor);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String accion = request.getParameter("accion");
            if (accion.equals("agregar")) {
                CRUDArticle crudArticle = new CRUDArticle();
                crudArticle.getArticle().setId(request.getParameter("id"));
                crudArticle.getArticle().setMarca(request.getParameter("marca"));
                crudArticle.getArticle().setPrecioVenta(parseDouble(request.getParameter("precioVenta")));
                crudArticle.getArticle().setPrecioCompra(parseDouble(request.getParameter("precioCompra")));
                crudArticle.getArticle().setModelo(request.getParameter("modelo"));
                crudArticle.getArticle().setProveedor(request.getParameter("proveedor"));
                crudArticle.getArticle().setTienda(request.getParameter("tienda"));
                crudArticle.getArticle().setCantidad(parseInt(request.getParameter("cantidad")));
                crudArticle.getArticle().setDescripcion(request.getParameter("descripcion"));
                crudArticle.getArticle().setCategoria(request.getParameter("categoria"));
                User usuario = (User) request.getSession().getAttribute("user.login");
                crudArticle.getArticle().setUserId(usuario != null ? usuario.getId() : null);
                crudArticle.agregarArticulo();
                response.sendRedirect("web/article/agregarArticulo.jsp?mensaje=Articulo " + request.getParameter("id") + " Agregado al Sistema");
            }
            else if (accion.equals("buscar")){
                Article article = CRUDArticle.consultarArticulo(request.getParameter("id"));
                request.getSession().setAttribute("article.buscar", article);
                String redireccion = request.getParameter("redir");
                if ("eliminar".equals(redireccion)) {
                    response.sendRedirect("web/article/eliminarArticulo.jsp");
                } else if ("modificar".equals(redireccion)) {
                    response.sendRedirect("web/article/modificarArticulo.jsp");
                } else {
                    response.sendRedirect("web/article/buscarArticulo.jsp");
                }
            }
            else if (accion.equals("modificar")) {
                CRUDArticle crudArticle = new CRUDArticle();
                crudArticle.getArticle().setId(request.getParameter("id"));
                crudArticle.getArticle().setMarca(request.getParameter("marca"));
                crudArticle.getArticle().setPrecioVenta(parseDouble(request.getParameter("precioVenta")));
                crudArticle.getArticle().setPrecioCompra(parseDouble(request.getParameter("precioCompra")));
                crudArticle.getArticle().setModelo(request.getParameter("modelo"));
                crudArticle.getArticle().setProveedor(request.getParameter("proveedor"));
                crudArticle.getArticle().setTienda(request.getParameter("tienda"));
                crudArticle.getArticle().setCantidad(parseInt(request.getParameter("cantidad")));
                crudArticle.getArticle().setDescripcion(request.getParameter("descripcion"));
                crudArticle.getArticle().setCategoria(request.getParameter("categoria"));
                crudArticle.modificarArticulo();
                response.sendRedirect("web/article/modificarArticulo.jsp?mensaje=Articulo " + request.getParameter("id") + " Modificado en el Sistema");
            }
            else if (accion.equals("eliminar")) {
                CRUDArticle crudArticle = new CRUDArticle();
                crudArticle.getArticle().setId(request.getParameter("id"));
                crudArticle.eliminarArticulo();
                response.sendRedirect("web/article/eliminarArticulo.jsp?mensaje=Articulo " + request.getParameter("id") + " Eliminado del Sistema");
            }
            else if (accion.equals("listartodo")) {
                Article[] listado = CRUDArticle.listarTodosLosArticulos();
                request.getSession().setAttribute("article.listar", listado);
                response.sendRedirect("web/article/listarArticulo.jsp");
            }
            else if (accion.equals("buscarPorMarca")) {
                Article[] listado = CRUDArticle.buscarPorMarca(request.getParameter("marca"));
                request.getSession().setAttribute("article.listar", listado);
                response.sendRedirect("web/article/listarArticulo.jsp");
            }
            else if (accion.equals("buscarPorCategoria")) {
                Article[] listado = CRUDArticle.buscarPorCategoria(request.getParameter("categoria"));
                request.getSession().setAttribute("article.listar", listado);
                response.sendRedirect("web/article/listarArticulo.jsp");
            }
            else {
                response.sendRedirect("web/article/mensajeArticulo.jsp?mensaje=La Accion Solicitada no es Correcta");
            }
        } catch (Exception error) {
            response.sendRedirect("web/article/mensajeArticulo.jsp?mensaje=" + error.getMessage());
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
