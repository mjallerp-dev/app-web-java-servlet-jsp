package com.mjallerp.appwebjavaservletjsp.model;

public class Article {
    private String id;
    private String marca;
    private double precioVenta;
    private double precioCompra;
    private double iva;
    private String modelo;
    private String proveedor;
    private String tienda;
    private int cantidad;
    private String descripcion;
    private String categoria;
    private String userId;

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getIva() {
        return iva;
    }

    public String getModelo() {
        return modelo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getTienda() {
        return tienda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
