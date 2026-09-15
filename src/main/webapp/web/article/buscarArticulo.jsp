<%@page import="com.mjallerp.appwebjavaservletjsp.model.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getServletContext().getRequestDispatcher("/web/users/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    Article article = (Article) request.getSession().getAttribute("article.buscar");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar Articulo</title>
    </head>
    <body>
        <center>
            <h1>Buscar Articulo</h1>
            <hr/>
            <form action="../../article?accion=buscar&redir=buscar" method="post">
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
                                   onclick="window.location.href='../../index.jsp'"/>
                        </th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Marca:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getMarca() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Precio Venta:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getPrecioVenta() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Precio Compra:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getPrecioCompra() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">IVA:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getIva() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Modelo:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getModelo() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Proveedor:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getProveedor() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Tienda:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getTienda() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Cantidad:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getCantidad() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Descripcion:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getDescripcion() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Categoria:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getCategoria() : "" %>
                        </td>
                    </tr>
                </table>
            </form>
            <hr/>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
            <% request.getSession().setAttribute("article.buscar", null); %>
        </center>
    </body>
</html>
