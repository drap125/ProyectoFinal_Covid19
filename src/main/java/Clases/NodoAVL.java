
package Clases;

public class NodoAVL {
    Long dpi;
    String nombre, departamento, municipio, cantidad, primerFvacuna, segundaFvacuna, tercerarFvacuna, lugarVacunacion;
    int fe;
    NodoAVL hijoIzquierdo;
    NodoAVL hijoDerecho;
   
    public NodoAVL(long dpiN, String nombre, String departamento, String municipio, String cantidad, String primerFvacuna, String segundaFvacuna, String tercerarFvacuna, String lugarVacunacion){
        this.dpi = dpiN;
        this.nombre = nombre;
        this.departamento = departamento; 
        this.municipio = municipio;
        this.cantidad = cantidad;
        this.primerFvacuna = primerFvacuna; 
        this.segundaFvacuna = segundaFvacuna;
        this.tercerarFvacuna = tercerarFvacuna;
        this.lugarVacunacion = lugarVacunacion;
        this.fe=0;       
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
    
    
    
    public String textoGraphviz() {
    StringBuilder texto = new StringBuilder();

    if (hijoIzquierdo != null) {
        texto.append(dpi).append(" -> ").append(hijoIzquierdo.dpi).append(";\n");
        texto.append(hijoIzquierdo.textoGraphviz());
    }
    
    if (hijoDerecho != null) {
        texto.append(dpi).append(" -> ").append(hijoDerecho.dpi).append(";\n");
        texto.append(hijoDerecho.textoGraphviz());
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
