<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getRequestDispatcher("/web/users/login.jsp").forward(request,response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu de la Aplicacion</title>
    </head>
    <body>
        <center>
            <h2>Menu de la Aplicación</h2>
            <hr/>
            <table border="0">
                <tbody>
                    <tr>
                        <th><a href="web/users/agregar.jsp">Agregar</a></th>
                    </tr>
                    <tr>
                        <td><a href="web/users/buscar.jsp">Buscar</a></td>
                    </tr>
                    <tr>
                        <td><a href="web/users/modificar.jsp">Modificar</a></td>
                    </tr>
                    <tr>
                        <td><a href="web/users/eliminar.jsp">Eliminar</a></td>
                    </tr>
                    <tr>
                        <td><a href="user?accion=listartodo">Listar</a></td>
                    </tr>
                    <tr>
                        <td><a href="user?accion=salir">Salir</a></td>
                    </tr>
                </tbody>
            </table>
            <hr/>
        </center>
    </body>
</html>
