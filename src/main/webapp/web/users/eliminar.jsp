<%@page import="com.mjallerp.appwebjavaservletjsp.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getRequestDispatcher("/web/users/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    User user = (User) request.getSession().getAttribute("user.buscar");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <center>
            <h1>Eliminar Usuario</h1>
            <hr/>
            <form action="../../user?accion=buscar&redir=eliminar" method="post">
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
                        <th style="text-align: right">Password:</th>
                        <td style="text-align: left">
                            <%= (user != null) ? "********" : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <td style="text-align: left">
                            <%= (user != null) ? user.getName() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Rol:</th>
                        <td style="text-align: left">
                            <%= (user != null) ? user.getRole() : "" %>
                        </td>
                    </tr>
                </table>
            </form>
            <hr/>
            <%
                if (user != null) {
            %>
            <form action="../../user?accion=eliminar" method="post">
                <input type="hidden" name="id" value="<%= user.getId() %>">
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
            <% request.getSession().setAttribute("user.buscar", null); %>
        </center>
    </body>
</html>
