<%@page import="com.mjallerp.appwebjavaservletjsp.model.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getRequestDispatcher("/web/users/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    Article article = (Article) request.getSession().getAttribute("article.buscar");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Articulo</title>
    </head>
    <body>
        <center>
            <h1>Eliminar Articulo</h1>
            <hr/>
            <form action="../../article?accion=buscar&redir=eliminar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <td>
                            <input type="text" name="id"/>
                        </td>
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
                    <tr>
                        <th style="text-align: right">Marca:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getMarca() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Modelo:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getModelo() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Categoria:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getCategoria() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Cantidad:</th>
                        <td style="text-align: left">
                            <%= (article != null) ? article.getCantidad() : "" %>
                        </td>
                    </tr>
                </table>
            </form>
            <hr/>
            <%
                if (article != null) {
            %>
            <form action="../../article?accion=eliminar" method="post">
                <input type="hidden" name="id" value="<%= article.getId() %>">
                <table>
                    <tr>
                        <td>
                            <input type="submit" value="Eliminar">
                        </td>
                    </tr>
                </table>
            </form>
            <%
                }
            %>
            <p style="color:#FF0000;">
                <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
            </p>
            <% request.getSession().setAttribute("article.buscar", null); %>
        </center>
    </body>
</html>
