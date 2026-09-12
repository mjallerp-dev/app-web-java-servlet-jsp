<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesion</title>
</head>
<body>
<center>
    <h1>Iniciar Sesion</h1>
    <%
        String mensaje = request.getParameter("mensaje");
        if (mensaje != null && !mensaje.isEmpty()) {
    %>
    <h3><%= mensaje %></h3>
    <%
        }
    %>
    <form action="../../user?accion=login" method="post">
        <table border="0">
            <tr>
                <td>ID:</td>
                <td><input type="text" name="id" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Entrar">
                    <input type="reset" value="Restablecer">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
