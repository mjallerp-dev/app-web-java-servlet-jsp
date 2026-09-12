<%@page import="com.mjallerp.appwebjavaservletjsp.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getServletContext().getRequestDispatcher("/web/users/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    User user = (User) request.getSession().getAttribute("user.buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Usuario</title>
    </head>
    <body>
        <center>
            <h1>Modificar Usuario</h1>
            <hr/>
            <form action="../../user?accion=buscar&redir=modificar" method="post">
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
                    </tr>
                </table>
            </form>
            <hr/>
            <%
                if (user != null) {
            %>
            <form action="../../user?accion=modificar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <td>
                            <input type="text" name="id"
                                   value="<%= (user != null) ? user.getId() : "" %>"
                                   readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Password:</th>
                        <td>
                            <input type="password" name="password"
                                   value="<%= (user != null) ? user.getPassword() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <td>
                            <input type="text" name="name"
                                   value="<%= (user != null) ? user.getName() : "" %>"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align: right">Rol:</th>
                        <th>
                            <select name="role">
                                <option value="Administrador"
                                    <%= (user != null && user.getRole().equals("Administrador"))
                                            ? "selected" : "" %>>
                                    Administrador
                                </option>
                                <option value="Cliente"
                                    <%= (user != null && user.getRole().equals("Cliente"))
                                            ? "selected" : "" %>>
                                    Cliente
                                </option>
                            </select>
                        </th>
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
            <% request.getSession().setAttribute("user.buscar", null); %>
        </center>
    </body>
</html>
