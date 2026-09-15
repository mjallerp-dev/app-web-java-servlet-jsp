<%@page import="com.mjallerp.appwebjavaservletjsp.model.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getServletContext().getRequestDispatcher("/web/users/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    Article article = (Article) request.getSession().getAttribute("article.buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Articulo</title>
    </head>
    <body>
        <center>
            <h1>Modificar Articulo</h1>
            <hr/>
            <form action="../../article?accion=buscar&redir=modificar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <td><input type="text" name="id"/></td>
                    </tr>
                    <tr>
                        <th>
                            <input type="submit" value="Buscar"/>
                        </th>
                        <th>
                            <input type="reset" value="Limpiar"/>
                        </th>
                        <th>
                            <input type="button" value="Volver al Menu"
                                   onclick="window.location.href='<%= request.getContextPath() %>/index.jsp'"/>
                        </th>
                    </tr>
                </table>
            </form>
            <hr/>
            <%
                if (article != null) {
            %>
            <form action="../../article?accion=modificar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <td>
                            <input type="text" name="id"
                                   value="<%= (article != null) ? article.getId() : "" %>"
                                   readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Marca:</th>
                        <td>
                            <input type="text" name="marca"
                                   value="<%= (article != null) ? article.getMarca() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Precio Venta:</th>
                        <td>
                            <input type="number" step="0.01" name="precioVenta"
                                   value="<%= (article != null) ? article.getPrecioVenta() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Precio Compra:</th>
                        <td>
                            <input type="number" step="0.01" name="precioCompra"
                                   value="<%= (article != null) ? article.getPrecioCompra() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Modelo:</th>
                        <td>
                            <input type="text" name="modelo"
                                   value="<%= (article != null) ? article.getModelo() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Proveedor:</th>
                        <td>
                            <input type="text" name="proveedor"
                                   value="<%= (article != null) ? article.getProveedor() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Tienda:</th>
                        <td>
                            <input type="text" name="tienda"
                                   value="<%= (article != null) ? article.getTienda() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Cantidad:</th>
                        <td>
                            <input type="number" name="cantidad"
                                   value="<%= (article != null) ? article.getCantidad() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Descripcion:</th>
                        <td>
                            <input type="text" name="descripcion"
                                   value="<%= (article != null) ? article.getDescripcion() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Categoria:</th>
                        <td>
                            <input type="text" name="categoria"
                                   value="<%= (article != null) ? article.getCategoria() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <input type="submit" value="Modificar"/>
                        </th>
                        <th>
                            <input type="reset" value="Limpiar"/>
                        </th>
                    </tr>
                </table>
            </form>
            <%
                }
            %>
            <hr/>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
            <% request.getSession().setAttribute("article.buscar", null); %>
        </center>
    </body>
</html>
