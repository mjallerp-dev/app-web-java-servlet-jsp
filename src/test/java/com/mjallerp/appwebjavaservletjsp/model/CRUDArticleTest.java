package com.mjallerp.appwebjavaservletjsp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CRUDArticleTest {

    private static final String MARCA = "Marca Prueba";
    private static final String MODELO = "Modelo X";
    private static final String PROVEEDOR = "Proveedor Prueba";
    private static final String TIENDA = "Tienda Prueba";
    private static final String DESCRIPCION = "Articulo de prueba";
    private static final String CATEGORIA = "Tecnologia";
    private static final double PRECIO_VENTA = 100000.0;
    private static final double PRECIO_COMPRA = 80000.0;
    private static final int CANTIDAD = 5;

    private String id;

    @BeforeEach
    void setUp() throws Exception {
        id = "test_" + UUID.randomUUID().toString().substring(0, 8);

        CRUDArticle crud = new CRUDArticle();
        crud.getArticle().setId(id);
        crud.getArticle().setMarca(MARCA);
        crud.getArticle().setPrecioVenta(PRECIO_VENTA);
        crud.getArticle().setPrecioCompra(PRECIO_COMPRA);
        crud.getArticle().setModelo(MODELO);
        crud.getArticle().setProveedor(PROVEEDOR);
        crud.getArticle().setTienda(TIENDA);
        crud.getArticle().setCantidad(CANTIDAD);
        crud.getArticle().setDescripcion(DESCRIPCION);
        crud.getArticle().setCategoria(CATEGORIA);
        crud.agregarArticulo();
    }

    @AfterEach
    void tearDown() throws Exception {
        CRUDArticle crud = new CRUDArticle();
        crud.getArticle().setId(id);
        crud.eliminarArticulo();
    }

    @Test
    void testConsultarArticuloAgregado() throws Exception {
        Article article = CRUDArticle.consultarArticulo(id);

        assertEquals(id, article.getId());
        assertEquals(MARCA, article.getMarca());
        assertEquals(PRECIO_VENTA, article.getPrecioVenta(), 0.001);
        assertEquals(PRECIO_COMPRA, article.getPrecioCompra(), 0.001);
        assertEquals(MODELO, article.getModelo());
        assertEquals(PROVEEDOR, article.getProveedor());
        assertEquals(TIENDA, article.getTienda());
        assertEquals(CANTIDAD, article.getCantidad());
        assertEquals(DESCRIPCION, article.getDescripcion());
        assertEquals(CATEGORIA, article.getCategoria());
    }

    @Test
    void testIvaCalculadoAlAgregar() throws Exception {
        Article article = CRUDArticle.consultarArticulo(id);

        assertEquals(PRECIO_VENTA * 0.19, article.getIva(), 0.001);
    }

    @Test
    void testListarIncluyeArticuloDePrueba() throws Exception {
        Article[] listado = CRUDArticle.listarTodosLosArticulos();

        boolean encontrado = false;
        for (Article article : listado) {
            if (id.equals(article.getId())) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
    }

    @Test
    void testModificarArticulo() throws Exception {
        CRUDArticle crud = new CRUDArticle();
        crud.getArticle().setId(id);
        crud.getArticle().setMarca("Marca Modificada");
        crud.getArticle().setPrecioVenta(150000.0);
        crud.getArticle().setPrecioCompra(120000.0);
        crud.getArticle().setModelo("Modelo Y");
        crud.getArticle().setProveedor("Proveedor Nuevo");
        crud.getArticle().setTienda("Tienda Nueva");
        crud.getArticle().setCantidad(10);
        crud.getArticle().setDescripcion("Descripcion modificada");
        crud.getArticle().setCategoria("Hogar");
        crud.modificarArticulo();

        Article article = CRUDArticle.consultarArticulo(id);
        assertEquals("Marca Modificada", article.getMarca());
        assertEquals(150000.0, article.getPrecioVenta(), 0.001);
        assertEquals("Hogar", article.getCategoria());
        assertEquals(10, article.getCantidad());
    }

    @Test
    void testEliminarArticulo() throws Exception {
        CRUDArticle crud = new CRUDArticle();
        crud.getArticle().setId(id);
        crud.eliminarArticulo();

        assertThrows(Exception.class, () -> CRUDArticle.consultarArticulo(id));
    }

    @Test
    void testAgregarSinIdLanzaExcepcion() throws Exception {
        CRUDArticle crud = new CRUDArticle();
        crud.getArticle().setId("");

        assertThrows(Exception.class, () -> crud.agregarArticulo());
    }

    @Test
    void testConsultarSinIdLanzaExcepcion() {
        assertThrows(Exception.class, () -> CRUDArticle.consultarArticulo(""));
    }
}
