package com.example.proyecto;

public class Cliente
{
    private String rut;
    private String nombre;
    private Integer deuda;

    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }



    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }



    public Integer getDeuda() {
        return deuda;
    }
    public void setDeuda(Integer deuda) {

        this.deuda = deuda;
    }
}
