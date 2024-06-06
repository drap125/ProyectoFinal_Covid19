
package Clases;

public class NodoArbol {
    long dato;
    String nombre, departamento, municipio, cantidad, primerFvacuna, segundaFvacuna, tercerarFvacuna, lugarVacunacion;
    NodoArbol HijoIzquierdo;
    NodoArbol HijoDerecho;
    
    public NodoArbol(long d, String nombre, String departamento, String municipio, String cantidad, String primerFvacuna, String segundaFvacuna, String tercerarFvacuna, String lugarVacunacion){
        this.dato = d;
        this.HijoIzquierdo = null;
        this.HijoDerecho = null;
        this.nombre = nombre;
        this.departamento = departamento; 
        this.municipio = municipio;
        this.cantidad = cantidad;
        this.primerFvacuna = primerFvacuna; 
        this.segundaFvacuna = segundaFvacuna;
        this.tercerarFvacuna = tercerarFvacuna;
        this.lugarVacunacion = lugarVacunacion;
    }
    
    
    public String textoGraphviz() {
    StringBuilder texto = new StringBuilder();

    if (HijoIzquierdo != null) {
        texto.append(dato).append(" -> ").append(HijoIzquierdo.dato).append(";\n");
        texto.append(HijoIzquierdo.textoGraphviz());
    }
    
    if (HijoDerecho != null) {
        texto.append(dato).append(" -> ").append(HijoDerecho.dato).append(";\n");
        texto.append(HijoDerecho.textoGraphviz());
    }

    return texto.toString();
}

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrimerFvacuna() {
        return primerFvacuna;
    }

    public void setPrimerFvacuna(String primerFvacuna) {
        this.primerFvacuna = primerFvacuna;
    }

    public String getSegundaFvacuna() {
        return segundaFvacuna;
    }

    public void setSegundaFvacuna(String segundaFvacuna) {
        this.segundaFvacuna = segundaFvacuna;
    }

    public String getTercerarFvacuna() {
        return tercerarFvacuna;
    }

    public void setTercerarFvacuna(String tercerarFvacuna) {
        this.tercerarFvacuna = tercerarFvacuna;
    }

    public String getLugarVacunacion() {
        return lugarVacunacion;
    }

    public void setLugarVacunacion(String lugarVacunacion) {
        this.lugarVacunacion = lugarVacunacion;
    }

       
  
}
