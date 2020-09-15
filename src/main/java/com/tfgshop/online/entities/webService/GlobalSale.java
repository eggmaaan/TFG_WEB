package com.tfgshop.online.entities.webService;

/*
El ambito puede ser Un Producto, almacen, ano...
 */

public class GlobalSale {

    private String ambito;
    private int unidades;
    private double beneficios;

    public GlobalSale(String ambito, int unidades, double beneficios) {
        this.ambito = ambito;
        this.unidades = unidades;
        this.beneficios = beneficios;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(int beneficios) {
        this.beneficios = beneficios;
    }
}
