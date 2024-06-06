package Clases;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArbolBinario {
    private static ArbolBinario instancia;
    public NodoArbol raiz, nodoEncontrado;  
    public EncriptarArchivo archivoBBT= new EncriptarArchivo();
    
    public ArbolBinario (){
        raiz = null;
    }
    
    
    public static ArbolBinario getInstance() {
        if (instancia == null) {
            instancia = new ArbolBinario();
        }
        return instancia;
    }
    
    
    
    //Metodo para insertar un metodo en el arbol
    public void AgregarNodo(long d, String nombre, String departamento, String municipio, String cantidad, String primerFvacuna, String segundaFvacuna, String tercerarFvacuna, String lugarVacunacion){
        NodoArbol nuevo = new NodoArbol(d, nombre, departamento, municipio, cantidad, primerFvacuna, segundaFvacuna, tercerarFvacuna, lugarVacunacion);
        if(raiz == null){
        raiz = nuevo;
        }else{
        NodoArbol auxiliar = raiz;
        NodoArbol padre;
        
        while(true){
        padre = auxiliar;
        
        if(d <auxiliar.dato){
             auxiliar = auxiliar.HijoIzquierdo;
             if(auxiliar == null){
                    padre.HijoIzquierdo = nuevo;
                    return;
                    }
        }else{
            auxiliar = auxiliar.HijoDerecho;
                if(auxiliar == null){
                    padre.HijoDerecho = nuevo;
                    return;
                    }
                }
            }
        }
    }
    
    public boolean EstaVacio(){
        return raiz ==null;
    }
    
    public void InOrden(NodoArbol r, StringBuilder dat){
        if(r!=null){
            InOrden(r.HijoIzquierdo, dat);
            dat.append(r.dato).append(" ").append(r.nombre).append(" ").append(r.departamento).append(" ").append(r.municipio).append(" ").append(r.cantidad).append(" ").append(r.primerFvacuna).append(" ").append(r.segundaFvacuna).append(" ").append(r.tercerarFvacuna).append(" ").append(r.lugarVacunacion).append("\n");
            InOrden(r.HijoDerecho, dat);
        }
    }
    
    public void PreOrden(NodoArbol r, StringBuilder dat){
         if(r!=null){
            dat.append(r.dato).append(" ").append(r.nombre).append(" ").append(r.departamento).append(" ").append(r.municipio).append(" ").append(r.cantidad).append(" ").append(r.primerFvacuna).append(" ").append(r.segundaFvacuna).append(" ").append(r.tercerarFvacuna).append(" ").append(r.lugarVacunacion).append("\n");
            PreOrden(r.HijoIzquierdo, dat);
            PreOrden(r.HijoDerecho, dat);
        }
    }
    
     public void PostOrden(NodoArbol r, StringBuilder dat){
         if(r!=null){
            PostOrden(r.HijoIzquierdo, dat);
            PostOrden(r.HijoDerecho, dat);
            dat.append(r.dato).append(" ").append(r.nombre).append(" ").append(r.departamento).append(" ").append(r.municipio).append(" ").append(r.cantidad).append(" ").append(r.primerFvacuna).append(" ").append(r.segundaFvacuna).append(" ").append(r.tercerarFvacuna).append(" ").append(r.lugarVacunacion).append("\n");
        }
    }
    
     //Metodo para buscar un Nodo en el Arbol
     
    public NodoArbol BuscarNodo(long d){
        NodoArbol aux = raiz;
    
    while(aux.dato != d){
         if(d<aux.dato){
             aux = aux.HijoIzquierdo;
         }else{
             aux = aux.HijoDerecho;
         }if(aux==null){
             return null;
         }
     }
       nodoEncontrado = aux;
        return aux;
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
     
     //Metodo para eliminar un Nodo del Arbol
     
     public boolean EliminarNodo(long d){
         NodoArbol auxiliar = raiz;
         NodoArbol padre =raiz;
         boolean EsHijoIzq = true;
         
         while(auxiliar.dato != d){
             padre = auxiliar; 
             if(d<auxiliar.dato){
             EsHijoIzq = true;
             auxiliar = auxiliar.HijoIzquierdo;
             }else{
                 EsHijoIzq = false;
                  auxiliar = auxiliar.HijoDerecho;
             }
             if(auxiliar == null){
                 return false;
             }
         }  //fin while
         
         if(auxiliar.HijoIzquierdo   == null && auxiliar.HijoDerecho == null){
                if(auxiliar == raiz){
                    raiz = null; //El nodo a eliminar es la raiz y apuntamos a null
                }else if(EsHijoIzq){
                    padre.HijoIzquierdo = null;
                }else{
                    padre.HijoDerecho = null;
                }
         }else if(auxiliar.HijoDerecho == null){
             if(auxiliar == raiz){
                 raiz = auxiliar.HijoIzquierdo;
             }else if(EsHijoIzq){
             padre.HijoIzquierdo = auxiliar.HijoIzquierdo;
             }else{
                 padre.HijoDerecho = auxiliar.HijoIzquierdo;
             }
         }else if(auxiliar.HijoIzquierdo == null){
             if(auxiliar==raiz){
             raiz = auxiliar.HijoDerecho;
             }else if(EsHijoIzq){
                 padre.HijoIzquierdo = auxiliar.HijoDerecho;
             }else{
                 padre.HijoDerecho = auxiliar.HijoDerecho;
                 //puede que sea auxiliar.HijoIzquierdo
             }
        }else{
             NodoArbol reemplazo = ObtenerNodoReemplazoI(auxiliar);
             if(auxiliar == raiz){
             raiz = reemplazo;
             }else if(EsHijoIzq){
                 padre.HijoIzquierdo = reemplazo;
             }else{
                 padre.HijoDerecho = reemplazo;
             }
             reemplazo.HijoIzquierdo = auxiliar.HijoIzquierdo;
         }
         return true;
    }
     
    public NodoArbol ObtenerNodoReemplazoI(NodoArbol nodoreemp){
            NodoArbol reemplazopadre = nodoreemp;
            NodoArbol reemplazo = nodoreemp;
            NodoArbol auxiliar = nodoreemp.HijoDerecho;
            
            while(auxiliar != null){
            reemplazopadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.HijoIzquierdo;
            }
            
            if(reemplazo != nodoreemp.HijoDerecho){
                reemplazopadre.HijoIzquierdo = reemplazo.HijoDerecho;
                reemplazo.HijoDerecho = nodoreemp.HijoDerecho;
            }
            
            System.out.println("El Nodo remplazado es: "+ reemplazo.dato);
            return reemplazo;
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
        escribirArchivo("archivoN.dot", codigoDot);
        ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng", "-o", "arbl.png", "archivoN.dot");
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
    
    public void cargarArchivoBBT(){
        archivoBBT.crearArchivo(nodoEncontrado);    
    }
    
}
