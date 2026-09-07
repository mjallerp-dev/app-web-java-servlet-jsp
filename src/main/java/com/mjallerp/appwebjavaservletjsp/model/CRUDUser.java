package com.mjallerp.appwebjavaservletjsp.model;

import com.mjallerp.appwebjavaservletjsp.drivers.DatabaseConnection;

import java.sql.PreparedStatement;

public class CRUDUser {

    private UserModel user;
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
                    + "br/>Explicación: " + error.getMessage());
        } finally {
            database.desconectar();
        }
    }
}
