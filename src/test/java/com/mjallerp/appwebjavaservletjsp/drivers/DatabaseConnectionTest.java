package com.mjallerp.appwebjavaservletjsp.drivers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseConnectionTest {

    private DatabaseConnection database;

    @AfterEach
    void tearDown() {
        if (database != null) {
            database.desconectar();
            database = null;
        }
    }

    @Test
    void testConectarDejaConexionAbierta() throws Exception {
        database = new DatabaseConnection();

        assertNotNull(database.getConnection());
        assertFalse(database.getConnection().isClosed());
    }

    @Test
    void testCrearSentenciaYConsultar() throws Exception {
        database = new DatabaseConnection();

        PreparedStatement sentence = database.crearSentencia("SELECT 1 AS valor");
        ResultSet resultado = database.consultar(sentence);

        assertTrue(resultado.next());
        assertEquals(1, resultado.getInt("valor"));
    }

    @Test
    void testActualizarConTablaTemporal() throws Exception {
        database = new DatabaseConnection();

        database.actualizar(database.crearSentencia("CREATE TEMP TABLE prueba_tmp (id INTEGER)"));
        int filas = database.actualizar(database.crearSentencia("INSERT INTO prueba_tmp (id) VALUES (7)"));
        assertEquals(1, filas);

        ResultSet resultado = database.consultar(database.crearSentencia("SELECT id FROM prueba_tmp"));
        assertTrue(resultado.next());
        assertEquals(7, resultado.getInt("id"));
    }

    @Test
    void testDesconectarNoLanzaExcepcion() throws Exception {
        database = new DatabaseConnection();

        assertDoesNotThrow(() -> database.desconectar());
    }
}
