package com.mjallerp.appwebjavaservletjsp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CRUDUserTest {

    private static final String NAME = "Usuario Prueba";
    private static final String PASSWORD = "clave123";
    private static final String ROLE = "Cliente";

    private String id;

    @BeforeEach
    void setUp() throws Exception {
        id = "test_" + UUID.randomUUID().toString().substring(0, 8);

        CRUDUser crud = new CRUDUser();
        crud.getUser().setId(id);
        crud.getUser().setName(NAME);
        crud.getUser().setPassword(PASSWORD);
        crud.getUser().setRole(ROLE);
        crud.agregarUsuario();
    }

    @AfterEach
    void tearDown() throws Exception {
        CRUDUser crud = new CRUDUser();
        crud.getUser().setId(id);
        crud.eliminarUsuario();
    }

    @Test
    void testConsultarUsuarioAgregado() throws Exception {
        User user = CRUDUser.consultarUsuario(id);

        assertEquals(id, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(ROLE, user.getRole());
    }

    @Test
    void testIniciarSesionCorrecto() throws Exception {
        User user = CRUDUser.iniciarsesion(id, PASSWORD);

        assertEquals(id, user.getId());
        assertEquals(NAME, user.getName());
    }

    @Test
    void testIniciarSesionIncorrecto() {
        assertThrows(Exception.class, () -> CRUDUser.iniciarsesion(id, "claveIncorrecta"));
    }

    @Test
    void testListarIncluyeUsuarioDePrueba() throws Exception {
        User[] listado = CRUDUser.listarTodosLosUsuarios();

        boolean encontrado = false;
        for (User user : listado) {
            if (id.equals(user.getId())) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
    }

    @Test
    void testModificarUsuario() throws Exception {
        CRUDUser crud = new CRUDUser();
        crud.getUser().setId(id);
        crud.getUser().setName("Nombre Modificado");
        crud.getUser().setPassword("nuevaClave");
        crud.getUser().setRole("Administrador");
        crud.modificarUsuario();

        User user = CRUDUser.consultarUsuario(id);
        assertEquals("Nombre Modificado", user.getName());
        assertEquals("nuevaClave", user.getPassword());
        assertEquals("Administrador", user.getRole());
    }

    @Test
    void testEliminarUsuario() throws Exception {
        CRUDUser crud = new CRUDUser();
        crud.getUser().setId(id);
        crud.eliminarUsuario();

        assertThrows(Exception.class, () -> CRUDUser.consultarUsuario(id));
    }

    @Test
    void testAgregarSinIdLanzaExcepcion() throws Exception {
        CRUDUser crud = new CRUDUser();
        crud.getUser().setId("");

        assertThrows(Exception.class, () -> crud.agregarUsuario());
    }

    @Test
    void testConsultarSinIdLanzaExcepcion() {
        assertThrows(Exception.class, () -> CRUDUser.consultarUsuario(""));
    }
}
