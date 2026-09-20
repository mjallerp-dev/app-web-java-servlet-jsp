<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@page import="com.mjallerp.appwebjavaservletjsp.model.User"%>
<%
    User[] listado = (User[]) session.getAttribute("user.listar");
    String mensaje = null;
    if (listado == null || listado.length <= 0) {
        mensaje = "Resultado: 0 Usuarios encontrados en el Sistema";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Todos los Usuarios Agregados al Sistema</h1>
        <h4>CONSULTAR USUARIO POR NOMBRE</h4>
        <form action="<%= request.getContextPath() %>/user" method="get">
            <input type="hidden" name="accion" value="buscarPorNombre">
            <label>Nombre:</label>
            <input type="text" name="name" required>
            <button type="submit">BUSCAR</button>
        </form>
        <h4>CONSULTAR USUARIO POR ROL</h4>
        <form action="<%= request.getContextPath() %>/user" method="get">
            <input type="hidden" name="accion" value="buscarPorRole">
            <label>Rol:</label>
            <select name="role" required>
                <option value="Administrador">Administrador</option>
                <option value="Cliente">Cliente</option>
            </select>
            <button type="submit">BUSCAR</button>
        </form>
        <h4>LISTA DE USUARIOS</h4>
        <hr>
        <%
            if (mensaje != null) {
                out.print(mensaje);
            } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Item</th>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Rol</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int contador = 0;
                    for (User user : listado) {
                        contador = contador + 1;
                %>
                <tr>
                    <td><%= contador%></td>
                    <td><%= user.getId()%></td>
                    <td><%= user.getName()%></td>
                    <td><%= user.getRole()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
        <hr>
        <input type="button" value="Volver al Menu"
               onclick="window.location.href='<%= request.getContextPath() %>/index.jsp'">
    </center>
    </body>
</html>
