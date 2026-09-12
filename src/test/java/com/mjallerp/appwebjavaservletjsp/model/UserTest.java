package com.mjallerp.appwebjavaservletjsp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    @Test
    void testValoresPorDefecto() {
        User user = new User();
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }

    @Test
    void testGettersYSetters() {
        User user = new User();
        user.setId("1001");
        user.setName("Ana Perez");
        user.setPassword("secreta");
        user.setRole("Administrador");

        assertEquals("1001", user.getId());
        assertEquals("Ana Perez", user.getName());
        assertEquals("secreta", user.getPassword());
        assertEquals("Administrador", user.getRole());
    }
}
