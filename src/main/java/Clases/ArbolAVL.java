
package Clases;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArbolAVL {
    
     private static ArbolAVL instancia;
    public NodoAVL raiz, nodoEncontrado;
    public EncriptarArchivo archivoAVL= new EncriptarArchivo();

    public boolean estaVacioElArbol() {
        return raiz == null;
    }
    
    public static ArbolAVL getInstance() {
        if (instancia == null) {
            instancia = new ArbolAVL();
        }
        return instancia;
    }
    
    // Método para insertar datos
    public void insertarNodo(long dpiN, String nombre, String departamento, String municipio, String cantidad, String primerFvacuna, String segundaFvacuna, String tercerarFvacuna, String lugarVacunacion){
        NodoAVL nuevo = new NodoAVL(dpiN, nombre, departamento, municipio, cantidad, primerFvacuna, segundaFvacuna, tercerarFvacuna, lugarVacunacion);
        if(raiz == null){
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz);
        }
    }
    
    // Obtener el factor de equilibrio
    public int obtenerFE(NodoAVL x){
        if(x == null){
            return -1;
        } else {
            return x.fe;
        }
    }
   
    // Rotacion Simple Izquierda
    public NodoAVL rotacionIzquierda(NodoAVL c){
        NodoAVL auxiliar = c.hijoIzquierdo;
        c.hijoIzquierdo = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho = c;
        
        // Actualizar altura
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho)) + 1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), c.fe) + 1;
        
        return auxiliar;
    }
   
    // Rotación Derecha
    public NodoAVL rotacionDerecha(NodoAVL c){
        NodoAVL auxiliar = c.hijoDerecho;
        c.hijoDerecho = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo = c;
        
        // Actualizar altura
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho)) + 1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoDerecho), c.fe) + 1;
        
        return auxiliar;
    }
    
    // Rotación Doble a la Izquierda
    public NodoAVL rotacionDobleIzquierda(NodoAVL c){
        c.hijoIzquierdo = rotacionDerecha(c.hijoIzquierdo);
        return rotacionIzquierda(c);
    }
    
    // Rotación Doble a la Derecha
    public NodoAVL rotacionDobleDerecha(NodoAVL c){
        c.hijoDerecho = rotacionIzquierda(c.hijoDerecho);
        return rotacionDerecha(c);
    }
    
    // Método para insertar AVL
    public NodoAVL insertarAVL(NodoAVL nuevo, NodoAVL subAr){
        NodoAVL nuevoPadre = subAr;
        
        if(nuevo.dpi < subAr.dpi){
            if(subAr.hijoIzquierdo == null){
                subAr.hijoIzquierdo = nuevo;
            } else {
                subAr.hijoIzquierdo = insertarAVL(nuevo, subAr.hijoIzquierdo);
                
                // Verificar balance y aplicar rotaciones si es necesario
                if(obtenerFE(subAr.hijoIzquierdo) - obtenerFE(subAr.hijoDerecho) == 2){
                    if(nuevo.dpi < subAr.hijoIzquierdo.dpi){
                        nuevoPadre = rotacionIzquierda(subAr);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        } else if(nuevo.dpi > subAr.dpi){
            if(subAr.hijoDerecho == null){
                subAr.hijoDerecho = nuevo;
            } else {
                subAr.hijoDerecho = insertarAVL(nuevo, subAr.hijoDerecho);
                
                // Verificar balance y aplicar rotaciones si es necesario
                if(obtenerFE(subAr.hijoDerecho) - obtenerFE(subAr.hijoIzquierdo) == 2){
                    if(nuevo.dpi > subAr.hijoDerecho.dpi){
                        nuevoPadre = rotacionDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        } else {
            System.out.println("Nodo duplicado");
        }
        
        // Actualizar la altura
        subAr.fe = Math.max(obtenerFE(subAr.hijoIzquierdo), obtenerFE(subAr.hijoDerecho)) + 1;
        
        return nuevoPadre;
    }
    
    // Recorridos
   
    public void Enorden(NodoAVL r, StringBuilder dat){
        if(r!=null){
            Enorden(r.hijoIzquierdo, dat);
            dat.append(r.dpi).append(" ").append(r.nombre).append(" ").append(r.departamento).append(" ").append(r.municipio).append(" ").append(r.cantidad).append(" ").append(r.primerFvacuna).append(" ").append(r.segundaFvacuna).append(" ").append(r.tercerarFvacuna).append(" ").append(r.lugarVacunacion).append("\n");
            Enorden(r.hijoDerecho, dat);   
        }   
    }
    
    public void preorden(NodoAVL r, StringBuilder dat){
         if(r!=null){
            dat.append(r.dpi).append(" ").append(r.nombre).append(" ").append(r.departamento).append(" ").append(r.municipio).append(" ").append(r.cantidad).append(" ").append(r.primerFvacuna).append(" ").append(r.segundaFvacuna).append(" ").append(r.tercerarFvacuna).append(" ").append(r.lugarVacunacion).append("\n");
            preorden(r.hijoIzquierdo, dat);
            preorden(r.hijoDerecho, dat);
        }
    }
    
     public void PostOrden(NodoAVL r, StringBuilder dat){
         if(r!=null){
            PostOrden(r.hijoIzquierdo, dat);
            PostOrden(r.hijoDerecho, dat);
            dat.append(r.dpi).append(" ").append(r.nombre).append(" ").append(r.departamento).append(" ").append(r.municipio).append(" ").append(r.cantidad).append(" ").append(r.primerFvacuna).append(" ").append(r.segundaFvacuna).append(" ").append(r.tercerarFvacuna).append(" ").append(r.lugarVacunacion).append("\n");
        }
    }
    
    
    
    // Método para eliminar un nodo
public NodoAVL eliminarNodo(NodoAVL raiz, long dpi) {
    if (raiz == null)
        return raiz;

    if (dpi < raiz.dpi)
        raiz.hijoIzquierdo = eliminarNodo(raiz.hijoIzquierdo, dpi);
    else if (dpi > raiz.dpi)
        raiz.hijoDerecho = eliminarNodo(raiz.hijoDerecho, dpi);
    else {
        if (raiz.hijoIzquierdo == null || raiz.hijoDerecho == null) {
            NodoAVL temp = null;
            if (temp == raiz.hijoIzquierdo)
                temp = raiz.hijoDerecho;
            else
                temp = raiz.hijoIzquierdo;

            if (temp == null) {
                temp = raiz;
                raiz = null;
            } else
                raiz = temp;
        } else {
            NodoAVL temp = obtenerNodoMinimo(raiz.hijoDerecho);
            raiz.dpi = temp.dpi;
            raiz.hijoDerecho = eliminarNodo(raiz.hijoDerecho, temp.dpi);
        }
    }

    if (raiz == null)
        return raiz;

    // Actualizar la altura
    raiz.fe = Math.max(obtenerFE(raiz.hijoIzquierdo), obtenerFE(raiz.hijoDerecho)) + 1;

    // Verificar el balance y realizar rotaciones si es necesario
    int balance = obtenerFE(raiz.hijoIzquierdo) - obtenerFE(raiz.hijoDerecho);
    if (balance > 1 && obtenerFE(raiz.hijoIzquierdo.hijoIzquierdo) - obtenerFE(raiz.hijoIzquierdo.hijoDerecho) >= 0)
        return rotacionIzquierda(raiz);

    if (balance > 1 && obtenerFE(raiz.hijoIzquierdo.hijoIzquierdo) - obtenerFE(raiz.hijoIzquierdo.hijoDerecho) < 0) {
        raiz.hijoIzquierdo = rotacionDerecha(raiz.hijoIzquierdo);
        return rotacionIzquierda(raiz);
    }

    if (balance < -1 && obtenerFE(raiz.hijoDerecho.hijoDerecho) - obtenerFE(raiz.hijoDerecho.hijoIzquierdo) >= 0)
        return rotacionDerecha(raiz);

    if (balance < -1 && obtenerFE(raiz.hijoDerecho.hijoDerecho) - obtenerFE(raiz.hijoDerecho.hijoIzquierdo) < 0) {
        raiz.hijoDerecho = rotacionIzquierda(raiz.hijoDerecho);
        return rotacionDerecha(raiz);
    }
    return raiz;
}


// Método para obtener el nodo mínimo (el más a la izquierda)
public NodoAVL obtenerNodoMinimo(NodoAVL nodo) {
    NodoAVL actual = nodo;
    while (actual.hijoIzquierdo != null)
        actual = actual.hijoIzquierdo;
    return actual;
}
   
    
// Buscar Nodo
     public NodoAVL buscar(long dpi, NodoAVL r) {
        if (raiz == null) {
            nodoEncontrado = null;
            return null;
        } else if (r.dpi == dpi) {
            nodoEncontrado = r; // Guardar el nodo encontrado
            return r;
        } else if (r.dpi < dpi) {
            return buscar(dpi, r.hijoDerecho);
        } else {
            return buscar(dpi, r.hijoIzquierdo);
        }
    }
    
    public void modificar(String departamento, String municipio, String cantidad, String primerFvacuna, String segundaFvacuna, String tercerarFvacuna, String lugarVacunacion){
        nodoEncontrado.setDepartamento(departamento);
        nodoEncontrado.setMunicipio(municipio);
        nodoEncontrado.setCantidad(cantidad);
        nodoEncontrado.setPrimerFvacuna(primerFvacuna);
        nodoEncontrado.setSegundaFvacuna(segundaFvacuna); 
        nodoEncontrado.setTercerarFvacuna(tercerarFvacuna);
        nodoEncontrado.setLugarVacunacion(lugarVacunacion);
    }
    
  public String obtenerCodigoGraphviz() {
    StringBuilder texto = new StringBuilder();
    texto.append("digraph G {\n");
    texto.append("     node [shape = circle, style = filled, fillcolor = \"#EEEEE\", color = \"#EEEEE\", width = 0.5, height = 0.5];\n");
    texto.append("     edge [color = \"#31CEF0\"];\n\n");

    if (raiz != null) {
        texto.append(raiz.textoGraphviz());
    }
    texto.append("}\n");
    return texto.toString();
    }
    
  private void escribirArchivo(String ruta, String contenido){
        FileWriter fichero = null;
        PrintWriter pw = null;
    
        try{
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    

  public void dibujarGraphviz() {
    try {
        String codigoDot = obtenerCodigoGraphviz();
        System.out.println("Contenido del archivo DOT:");
        System.out.println(codigoDot); // Imprimir el contenido del archivo DOT
        escribirArchivo("archivoAVL.dot", codigoDot);
        ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng", "-o", "arblAVL.png", "archivoAVL.dot");
        proceso.redirectErrorStream(true);
        Process p = proceso.start();
        
        // Capturar la salida del proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        
        int exitVal = p.waitFor();
        if (exitVal != 0) {
            System.err.println("Error al generar la imagen PNG. Código de salida: " + exitVal);
        } else {
            System.out.println("Imagen PNG generada correctamente.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void setRaiz(NodoAVL raiz) {
        this.raiz = raiz;
    }
    
    
    
  public void cargarArchivoAVL(){
    archivoAVL.crearArchivoAVL(nodoEncontrado);    
  }
}

    