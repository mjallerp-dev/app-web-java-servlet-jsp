<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getServletContext().getRequestDispatcher("web/users/login.jsp")
                .forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Usuario al Sistema</title>
    </head>
    <body>
        <center>
            <h1>Agregar Usuario</h1>
            <hr>
            <form action="../../user?accion=agregar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <th><input type="text" name="id"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Password:</th>
                        <th><input type="password" name="password"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Nombre:</th>
                        <th><input type="text" name="name"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Role:</th>
                        <th>
                            <select name="role">
                                <option value="Administrador">Administrador</option>
                                <option value="Cliente">Cliente</option>
                            </select>
                        </th>
                    </tr>
                    <tr>
                        <th><input type="submit" value="Entrar"></th>
                        <th><input type="reset" name="Restablecer"></th>
                        <th>
                            <input type="button" value="Volver al Menu"
                                   onclick="window.location.href='<%= request.getContextPath() %>/index.jsp'">
                        </th>
                    </tr>
                </table>
            </form>
            <hr>
                <p style="color:#FF0000;">
                    <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : "" %>
                </p>
        </center>
</body>
</html>
