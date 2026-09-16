package com.mjallerp.appwebjavaservletjsp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArticleTest {

    @Test
    void testValoresPorDefecto() {
        Article article = new Article();
        assertNull(article.getId());
        assertNull(article.getMarca());
        assertEquals(0.0, article.getPrecioVenta(), 0.001);
        assertEquals(0.0, article.getPrecioCompra(), 0.001);
        assertEquals(0.0, article.getIva(), 0.001);
        assertNull(article.getModelo());
        assertNull(article.getProveedor());
        assertNull(article.getTienda());
        assertEquals(0, article.getCantidad());
        assertNull(article.getDescripcion());
        assertNull(article.getCategoria());
        assertNull(article.getUserId());
    }

    @Test
    void testGettersYSetters() {
        Article article = new Article();
        article.setId("2001");
        article.setMarca("Samsung");
        article.setPrecioVenta(1500.0);
        article.setPrecioCompra(1200.0);
        article.setIva(285.0);
        article.setModelo("Galaxy S24");
        article.setProveedor("Proveedor SA");
        article.setTienda("Tienda Centro");
        article.setCantidad(3);
        article.setDescripcion("Telefono de prueba");
        article.setCategoria("Celulares");
        article.setUserId("1001");

        assertEquals("2001", article.getId());
        assertEquals("Samsung", article.getMarca());
        assertEquals(1500.0, article.getPrecioVenta(), 0.001);
        assertEquals(1200.0, article.getPrecioCompra(), 0.001);
        assertEquals(285.0, article.getIva(), 0.001);
        assertEquals("Galaxy S24", article.getModelo());
        assertEquals("Proveedor SA", article.getProveedor());
        assertEquals("Tienda Centro", article.getTienda());
        assertEquals(3, article.getCantidad());
        assertEquals("Telefono de prueba", article.getDescripcion());
        assertEquals("Celulares", article.getCategoria());
        assertEquals("1001", article.getUserId());
    }
}
