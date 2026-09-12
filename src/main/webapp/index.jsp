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
            <table border="0">
                <tbody>
                    <tr>

                        <th><h1><a href="web/users/agregar.jsp">Agregar</a></h1></th>

                    </tr>
                    <tr>

                        <td><h1><a href="web/users/buscar.jsp">Buscar</a></h1></td>

                    </tr>
                    <tr>

                        <td><h1><a href="web/users/modificar.jsp">Modificar</a></h1></td>

                    </tr>
                    <tr>

                        <td><h1><a href="web/users/eliminar.jsp">Eliminar</a></h1></td>

                    </tr>
                    <tr>

                        <td><h1><a href="user?accion=listartodo">Listar</a></h1></td>

                    </tr>
                    <tr>

                        <td><h1><a href="user?accion=salir">Salir</a></h1></td>

                    </tr>
                </tbody>
            </table>
        </center>
    </body>
</html>
