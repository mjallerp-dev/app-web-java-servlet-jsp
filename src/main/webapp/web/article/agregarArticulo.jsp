<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (request.getSession().getAttribute("user.login") == null) {
        request.getServletContext().getRequestDispatcher("/web/users/login.jsp")
                .forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Articulo al Sistema</title>
    </head>
    <body>
        <center>
            <h1>Agregar Articulo</h1>
            <hr>
            <form action="../../article?accion=agregar" method="post">
                <table>
                    <tr>
                        <th style="text-align: right">ID:</th>
                        <th><input type="text" name="id"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Marca:</th>
                        <th><input type="text" name="marca"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Precio Venta:</th>
                        <th><input type="number" step="0.01" name="precioVenta"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Precio Compra:</th>
                        <th><input type="number" step="0.01" name="precioCompra"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">IVA:</th>
                        <th><input type="number" step="0.01" name="iva"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Modelo:</th>
                        <th><input type="text" name="modelo"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Proveedor:</th>
                        <th><input type="text" name="proveedor"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Tienda:</th>
                        <th><input type="text" name="tienda"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Cantidad:</th>
                        <th><input type="number" name="cantidad"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Descripcion:</th>
                        <th><input type="text" name="descripcion"></th>
                    </tr>
                    <tr>
                        <th style="text-align: right">Categoria:</th>
                        <th><input type="text" name="categoria"></th>
                    </tr>
                    <tr>
                        <th><input type="submit" value="Entrar"></th>
                        <th><input type="reset" name="Restablecer"></th>
                        <th>
                            <input type="button" value="Volver al Menu"
                                   onclick="window.location.href='../../index.jsp'">
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
