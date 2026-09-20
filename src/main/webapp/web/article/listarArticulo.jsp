<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@page import="com.mjallerp.appwebjavaservletjsp.model.Article"%>
<%
    Article[] listado = (Article[]) session.getAttribute("article.listar");
    String mensaje = null;
    if (listado == null || listado.length <= 0) {
        mensaje = "Resultado: 0 Articulos encontrados en el Sistema";
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
        <h1>Todos los Articulos Agregados al Sistema</h1>
        <h4>CONSULTAR ARTICULO POR MARCA</h4>
        <form action="<%= request.getContextPath() %>/article" method="get">
            <input type="hidden" name="accion" value="buscarPorMarca">
            <label>Marca:</label>
            <input type="text" name="marca" required>
            <button type="submit">BUSCAR</button>
        </form>
        <h4>CONSULTAR ARTICULO POR CATEGORIA</h4>
        <form action="<%= request.getContextPath() %>/article" method="get">
            <input type="hidden" name="accion" value="buscarPorCategoria">
            <label>Categoria:</label>
            <input type="text" name="categoria" required>
            <button type="submit">BUSCAR</button>
        </form>
        <h4>LISTA DE ARTICULOS</h4>
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
                    <th>Marca</th>
                    <th>Precio Venta</th>
                    <th>Precio Compra</th>
                    <th>IVA</th>
                    <th>Modelo</th>
                    <th>Proveedor</th>
                    <th>Tienda</th>
                    <th>Cantidad</th>
                    <th>Descripcion</th>
                    <th>Categoria</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int contador = 0;
                    for (Article article : listado) {
                        contador = contador + 1;
                %>
                <tr>
                    <td><%= contador%></td>
                    <td><%= article.getId()%></td>
                    <td><%= article.getMarca()%></td>
                    <td><%= article.getPrecioVenta()%></td>
                    <td><%= article.getPrecioCompra()%></td>
                    <td><%= article.getIva()%></td>
                    <td><%= article.getModelo()%></td>
                    <td><%= article.getProveedor()%></td>
                    <td><%= article.getTienda()%></td>
                    <td><%= article.getCantidad()%></td>
                    <td><%= article.getDescripcion()%></td>
                    <td><%= article.getCategoria()%></td>
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
