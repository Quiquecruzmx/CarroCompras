package Practica2;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class beanArticulos implements Serializable {
    int id;
    String nombre;
    String descripcion;
    String marca;
    float precio;
    int existencias;
    ImageIcon foto_producto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public ImageIcon getFoto_producto() {
        return foto_producto;
    }

    public void setFoto_producto(ImageIcon foto_producto) {
        this.foto_producto = foto_producto;
    }

   
}
