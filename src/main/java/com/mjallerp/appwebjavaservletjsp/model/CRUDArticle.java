package com.mjallerp.appwebjavaservletjsp.model;

import com.mjallerp.appwebjavaservletjsp.drivers.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUDArticle {

    private Article article;
    private DatabaseConnection database;

    public CRUDArticle() throws Exception {
        article = new Article();
        database = new DatabaseConnection();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public DatabaseConnection getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConnection database) {
        this.database = database;
    }

    public void agregarArticulo() throws Exception {
        if (article.getId() == null || article.getId().isEmpty()) {
            throw new Exception("El ID del articulo es necesario");
        }

        article.setIva(article.getPrecioVenta() * 0.19);

        String sqlInsert = "INSERT INTO article "
                + "(id, user_id, marca, precio_venta, precio_compra, iva, modelo, proveedor, tienda, cantidad, descripcion, categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement sentenceSQL = database.crearSentencia(sqlInsert);

            sentenceSQL.setString(1, article.getId());
            sentenceSQL.setString(2, article.getUserId());
            sentenceSQL.setString(3, article.getMarca());
            sentenceSQL.setDouble(4, article.getPrecioVenta());
            sentenceSQL.setDouble(5, article.getPrecioCompra());
            sentenceSQL.setDouble(6, article.getIva());
            sentenceSQL.setString(7, article.getModelo());
            sentenceSQL.setString(8, article.getProveedor());
            sentenceSQL.setString(9, article.getTienda());
            sentenceSQL.setInt(10, article.getCantidad());
            sentenceSQL.setString(11, article.getDescripcion());
            sentenceSQL.setString(12, article.getCategoria());

            database.actualizar(sentenceSQL);
        }
        catch (Exception error) {
            throw new Exception("Error al agregar articulo: " + article.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }

    public void modificarArticulo() throws Exception {
        if (article.getId() == null || article.getId().isEmpty()) {
            throw new Exception("El ID del articulo es necesario");
        }

        article.setIva(article.getPrecioVenta() * 0.19);

        String sqlUpdate = "UPDATE article "
                + "SET marca=?, precio_venta=?, precio_compra=?, iva=?, modelo=?, proveedor=?, tienda=?, cantidad=?, descripcion=?, categoria=? "
                + "WHERE id =?";

        try {
            PreparedStatement sentenceSQL = database.crearSentencia(sqlUpdate);

            sentenceSQL.setString(1, article.getMarca());
            sentenceSQL.setDouble(2, article.getPrecioVenta());
            sentenceSQL.setDouble(3, article.getPrecioCompra());
            sentenceSQL.setDouble(4, article.getIva());
            sentenceSQL.setString(5, article.getModelo());
            sentenceSQL.setString(6, article.getProveedor());
            sentenceSQL.setString(7, article.getTienda());
            sentenceSQL.setInt(8, article.getCantidad());
            sentenceSQL.setString(9, article.getDescripcion());
            sentenceSQL.setString(10, article.getCategoria());
            sentenceSQL.setString(11, article.getId());

            database.actualizar(sentenceSQL);
        }
        catch (Exception error) {
            throw new Exception("Error al actualizar el articulo: " + article.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }

    public void eliminarArticulo() throws Exception {
        if (article.getId() == null || article.getId().isEmpty()) {
            throw new Exception("El ID del articulo es necesario");
        }

        String sqlDelete = "DELETE FROM article "
                + "WHERE id = ? ";

        try {
            PreparedStatement sentenceSQL = database.crearSentencia(sqlDelete);

            sentenceSQL.setString(1, article.getId());

            database.actualizar(sentenceSQL);
        }
        catch (Exception error) {
            throw new Exception("Error al eliminar el articulo: " + article.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }

    public static Article consultarArticulo(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("El ID del articulo es necesario");
        }
        Article article; DatabaseConnection database = null;

        String sqlSelect = "SELECT * FROM article "
                + "WHERE id = ?";

        try {
            database = new DatabaseConnection();
            PreparedStatement sentenceSQL = database.crearSentencia(sqlSelect);

            sentenceSQL.setString(1, id);

            ResultSet resultado = database.consultar(sentenceSQL);
            if (resultado.next() == true) {
                article = new Article();
                article.setId(resultado.getString("id"));
                article.setUserId(resultado.getString("user_id"));
                article.setMarca(resultado.getString("marca"));
                article.setPrecioVenta(resultado.getDouble("precio_venta"));
                article.setPrecioCompra(resultado.getDouble("precio_compra"));
                article.setIva(resultado.getDouble("iva"));
                article.setModelo(resultado.getString("modelo"));
                article.setProveedor(resultado.getString("proveedor"));
                article.setTienda(resultado.getString("tienda"));
                article.setCantidad(resultado.getInt("cantidad"));
                article.setDescripcion(resultado.getString("descripcion"));
                article.setCategoria(resultado.getString("categoria"));
                return article;
            } else {
                throw new Exception("Error al consultar el articulo " + id + "<br/>Explicación: ");
            }
        }
        catch (Exception error) {
            throw new Exception(error.getMessage()+" El articulo No existe en la BD.");
        } finally {
            if (database != null) {
                database.desconectar();
            }
        }
    }

    public static Article[] listarTodosLosArticulos() throws Exception {
        Article article; DatabaseConnection database = null;

        String sqlSelect = "SELECT * FROM article";
        try {

            database = new DatabaseConnection();
            PreparedStatement sentenceSQL = database.crearSentencia(sqlSelect);

            ResultSet resultado = database.consultar(sentenceSQL);
            resultado.last();
            int totalFilas = resultado.getRow();
            if (totalFilas <= 0) {
                throw new Exception("Error al listar los articulos "
                    + "<br/>Explicación: ");
            }
            Article[] listado = new Article[totalFilas];
            resultado.beforeFirst();
            int indice = 0;
            while (resultado.next()) {
                article = new Article();
                article.setId(resultado.getString("id"));
                article.setUserId(resultado.getString("user_id"));
                article.setMarca(resultado.getString("marca"));
                article.setPrecioVenta(resultado.getDouble("precio_venta"));
                article.setPrecioCompra(resultado.getDouble("precio_compra"));
                article.setIva(resultado.getDouble("iva"));
                article.setModelo(resultado.getString("modelo"));
                article.setProveedor(resultado.getString("proveedor"));
                article.setTienda(resultado.getString("tienda"));
                article.setCantidad(resultado.getInt("cantidad"));
                article.setDescripcion(resultado.getString("descripcion"));
                article.setCategoria(resultado.getString("categoria"));
                listado[indice] = article;
                indice++;
            }
            return listado;
        } catch (Exception error) {
            throw new Exception(error.getMessage()+" No hay articulos registrados.");
        } finally {
            if (database != null) {
                database.desconectar();
            }
        }
    }
}
