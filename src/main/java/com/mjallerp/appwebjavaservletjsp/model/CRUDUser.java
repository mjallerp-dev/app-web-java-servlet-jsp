package com.mjallerp.appwebjavaservletjsp.model;

import com.mjallerp.appwebjavaservletjsp.drivers.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUDUser {

    private User user;
    private DatabaseConnection database;

    public void agregarUsuario() throws Exception {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new Exception("El ID del usuario es necesario");
        }

        String sqlInsert = "INSERT INTO users "
                + "(id, name, email, password) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement sentenceSQL = database.crearSentencia(sqlInsert);

            sentenceSQL.setString(1, user.getId());
            sentenceSQL.setString(2, user.getName());
            sentenceSQL.setString(3, user.getPassword());
            sentenceSQL.setString(4, user.getRole());

            database.actualizar(sentenceSQL);
        }
        catch (Exception error) {
            throw new Exception("Error al agregar usuario: " + user.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }

    public void modificarUsuario() throws Exception {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new Exception("El ID del usuario es necesario");
        }

        String sqlUpdate = "UPDATE users "
                + "SET password=?, name=?, role=? "
                + "WHERE id =?";

        try {
            PreparedStatement sentenceSQL = database.crearSentencia(sqlUpdate);

            sentenceSQL.setString(1, user.getId());
            sentenceSQL.setString(2, user.getName());
            sentenceSQL.setString(3, user.getPassword());
            sentenceSQL.setString(4, user.getRole());

            database.actualizar(sentenceSQL);
        }
        catch (Exception error) {
            throw new Exception("Error al actualizar el usuario: " + user.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }

    public void eliminarUsuario() throws Exception {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new Exception("El ID del usuario es necesario");
        }

        String sqlDelete = "DELETE FROM users "
                + "WHERE id = ? ";

        try {
            PreparedStatement sentenceSQL = database.crearSentencia(sqlDelete);

            sentenceSQL.setString(1, user.getId());

            database.actualizar(sentenceSQL);
        }
        catch (Exception error) {
            throw new Exception("Error al eliminar el usuario: " + user.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }

    public static User iniciarsesion(String id, String password) throws Exception {
        if (id==null || id.isEmpty() || password==null || password.isEmpty()){
            throw new Exception("El ID y la contraseña del usuario son necesarios");
        }
        User user; DatabaseConnection database = null;

        String sqlSelect = "SELECT * FROM users "
                + "WHERE id = ? AND password = ?";

        try {
            database = new DatabaseConnection();
            PreparedStatement sentenceSQL = database.crearSentencia(sqlSelect);

            sentenceSQL.setString(1, id);
            sentenceSQL.setString(2, password);

            ResultSet resultado = database.consultar(sentenceSQL);
            if (resultado.next()) {
                user = new User();
                user.setId(resultado.getString("id"));
                user.setName(resultado.getString("name"));
                user.setPassword(resultado.getString("password"));
                user.setRole(resultado.getString("role"));
                return user;
            } else {
                throw new Exception("Error al consultar el usuario " + id + "<br/>Explicación: ");
            }
        }
        catch (Exception error) {
            throw new Exception(error.getMessage()+" El ID o la contraseña son incorrectos.");
        } finally {
            if (database != null) {
                database.desconectar();
            }
        }
    }

    public static User consultarUsuario(String id) throws Exception {
        if (id==null || id.isEmpty()){
            throw new Exception("El ID del usuario es necesario");
        }
        User user; DatabaseConnection database = null;

        String sqlSelect = "SELECT * FROM users "
                + "WHERE id = ?";

        try {
            database = new DatabaseConnection();
            PreparedStatement sentenceSQL = database.crearSentencia(sqlSelect);

            sentenceSQL.setString(1, id);

            ResultSet resultado = database.consultar(sentenceSQL);
            if (resultado.next()) {
                user = new User();
                user.setId(resultado.getString("id"));
                user.setName(resultado.getString("name"));
                user.setPassword(resultado.getString("password"));
                user.setRole(resultado.getString("role"));
                return user;
            } else {
                throw new Exception("Error al consultar el usuario " + id + "<br/>Explicación: ");
            }
        }
        catch (Exception error) {
            throw new Exception(error.getMessage()+" El usuario No existe en la BD.");
        } finally {
            if (database != null) {
                database.desconectar();
            }
        }
    }

    public static User[] listarTodosLosUsuarios() throws Exception {
        User user; DatabaseConnection database = null;

        String sqlSelect = "SELECT * FROM users";
        try {

            database = new DatabaseConnection();
            PreparedStatement sentenceSQL = database.crearSentencia(sqlSelect);

            ResultSet resultado = database.consultar(sentenceSQL);
            resultado.last();
            User[] listado = new User[resultado.getRow()];
            resultado.beforeFirst();
            while (resultado.next()) {
                user = new User();
                user.setId(resultado.getString("id"));
                user.setName(resultado.getString("name"));
                user.setPassword(resultado.getString("password"));
                user.setRole(resultado.getString("role"));
                listado[resultado.getRow()] = user;
            } if(listado.length <= 0) {
                throw new Exception("Error al listar los usuarios "
                    + "<br/>Explicación: ");
            }
            return listado;
        } catch (Exception error) {
            throw new Exception(error.getMessage()+" No hay usuarios registrados.");
        } finally {
            if (database != null) {
                database.desconectar();
            }
        }
    }
}
