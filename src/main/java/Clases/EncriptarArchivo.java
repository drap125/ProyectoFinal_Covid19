
package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EncriptarArchivo {
    
    
    private File miArchivoBBT;
    private File miArchivoAVL;
    
    public EncriptarArchivo() {
        miArchivoBBT = new File("archivoBBT.txt");
        miArchivoAVL = new File("archivoAVL.txt");
        
        try {
            if (!miArchivoBBT.exists()) {
                miArchivoBBT.createNewFile();
            }
            if (!miArchivoAVL.exists()) {
                miArchivoAVL.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public File getMiArchivoBBT() {
        return miArchivoBBT;
    }
    
    public File getMiArchivoAVL() {
        return miArchivoAVL;
    }
  
    
       public void crearArchivo(NodoArbol nodo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(miArchivoBBT, true))) {
            String linea = nodo.dato + "/" + nodo.nombre + "/" + nodo.departamento + "/" + nodo.municipio + "/" + nodo.cantidad + "/" + nodo.primerFvacuna + "/" + nodo.segundaFvacuna + "/" + nodo.tercerarFvacuna + "/" + nodo.lugarVacunacion;
            writer.write(linea);
            writer.newLine(); // Añadir nueva línea para cada entrada
            System.out.println("Datos inscritos en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
       public void crearArchivoAVL(NodoAVL nodo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(miArchivoAVL, true))) {
            String linea = nodo.dpi + "/" + nodo.nombre + "/" + nodo.departamento + "/" + nodo.municipio + "/" + nodo.cantidad + "/" + nodo.primerFvacuna + "/" + nodo.segundaFvacuna + "/" + nodo.tercerarFvacuna + "/" + nodo.lugarVacunacion;
            writer.write(linea);
            writer.newLine(); // Añadir nueva línea para cada entrada
            System.out.println("Datos inscritos en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    public void cargar(ArbolBinario arbolito){
        try{
            FileReader archivo = new FileReader("archivoBBT.txt");
            BufferedReader lectura = new BufferedReader(archivo);
            
            String linea;
            
            while((linea = lectura.readLine()) != null){
            String[] fila = linea.split("/");
                if(fila.length == 9){
                             
                    long dpi = Long.parseLong(fila[0]);
                    arbolito.AgregarNodo(dpi, fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7], fila[8]);
              
                }
            }
            JOptionPane.showMessageDialog(null, "Inscripcción Correcta");
        }catch(IOException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Inscripcion Fallida");
            
        }
        
    }
    
    
     
    public void cargarAVL(ArbolAVL arbolitoAVL){
        try{
            FileReader archivo = new FileReader("archivoBBT.txt");
            BufferedReader lectura = new BufferedReader(archivo);
            
            String linea;
            
            while((linea = lectura.readLine()) != null){
            String[] fila = linea.split("/");
                if(fila.length == 9){
                             
                    long dpi = Long.parseLong(fila[0]);
                    arbolitoAVL.insertarNodo(dpi, fila[1], fila[2], fila[3], fila[4], fila[5], fila[6], fila[7], fila[8]);
              
                }
            }
            JOptionPane.showMessageDialog(null, "Inscripcción Correcta");
        }catch(IOException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Inscripcion Fallida");
            
        }
        
    }
    
    
    
    
  public static String encrip(String texto) {
    StringBuilder resultado = new StringBuilder();

    for (int i = 0; i < texto.length(); i++) {
        char caracter = texto.charAt(i);
        // Si el caracter es una letra, lo encriptamos
        if (Character.isLetter(caracter)) {
            // Si es una letra mayúscula, aplicamos el desplazamiento
            if (Character.isUpperCase(caracter)) {
                caracter = (char) ((caracter - 'A' + 4) % 26 + 'A');
            } else { // Si es una letra minúscula
                caracter = (char) ((caracter - 'a' + 4) % 26 + 'a');
            }
        } else if (Character.isDigit(caracter)) { // Si el caracter es un número, lo encriptamos
            // Aplicamos el desplazamiento para los números
            caracter = (char) ((caracter - '0' + 4) % 10 + '0');
        }
        resultado.append(caracter);
    }

    return resultado.toString();
}

public static String desincrip(String texto) {
    StringBuilder resultado = new StringBuilder();

    for (int i = 0; i < texto.length(); i++) {
        char caracter = texto.charAt(i);
        // Si el caracter es una letra, lo desencriptamos
        if (Character.isLetter(caracter)) {
            // Si es una letra mayúscula, aplicamos el desplazamiento inverso
            if (Character.isUpperCase(caracter)) {
                caracter = (char) ((caracter - 'A' - 4 + 26) % 26 + 'A');
            } else { // Si es una letra minúscula
                caracter = (char) ((caracter - 'a' - 4 + 26) % 26 + 'a');
            }
        } else if (Character.isDigit(caracter)) { // Si el caracter es un número, lo desencriptamos
            // Aplicamos el desplazamiento inverso para los números
            caracter = (char) ((caracter - '0' - 4 + 10) % 10 + '0');
        }
        resultado.append(caracter);
    }

    return resultado.toString();
}
    


    public static  void encriptarArchivo(File archivo) throws IOException {
        Path path = Paths.get(archivo.getAbsolutePath());
        String contenido = new String(Files.readAllBytes(path));
        String contenidoEncriptado = encrip(contenido);
        Files.write(path, contenidoEncriptado.getBytes());
    }
    

    public void encriptarTodos(NodoArbol nodo) {
        if (nodo != null) {
            nodo.dato =Long.parseLong(encrip(Long.toString(nodo.dato)));
            nodo.nombre = encrip(nodo.nombre);
            nodo.departamento = encrip(nodo.departamento);
            nodo.municipio = encrip(nodo.municipio);
            nodo.cantidad = encrip(nodo.cantidad);
            nodo.primerFvacuna = encrip(nodo.primerFvacuna);
            nodo.segundaFvacuna = encrip(nodo.segundaFvacuna);
            nodo.tercerarFvacuna = encrip(nodo.tercerarFvacuna);
            nodo.lugarVacunacion = encrip(nodo.lugarVacunacion);
            encriptarTodos(nodo.HijoIzquierdo);
            encriptarTodos(nodo.HijoDerecho);
        }
    }
    
    public void desencriptarArchivo(File archivo) throws IOException {
        Path path = Paths.get(archivo.getAbsolutePath());
        String contenido = new String(Files.readAllBytes(path));
        String contenidoDesencriptado = desincrip(contenido);
        Files.write(path, contenidoDesencriptado.getBytes());
    } 
    
    
    public void desencriptarTodos(NodoArbol nodo) {
        if (nodo != null) {
            
            nodo.dato = Long.parseLong(desincrip(Long.toString(nodo.dato)));
            nodo.nombre = desincrip(nodo.nombre);
            nodo.departamento = desincrip(nodo.departamento);
            nodo.municipio = desincrip(nodo.municipio);
            nodo.cantidad = desincrip(nodo.cantidad);
            nodo.primerFvacuna = desincrip(nodo.primerFvacuna);
            nodo.segundaFvacuna = desincrip(nodo.segundaFvacuna);
            nodo.tercerarFvacuna = desincrip(nodo.tercerarFvacuna);
            nodo.lugarVacunacion = desincrip(nodo.lugarVacunacion);
            desencriptarTodos(nodo.HijoIzquierdo);
            desencriptarTodos(nodo.HijoDerecho);
        }
    }
    

    //-------------------------CODIGO DE AVL--------------------------------
    
    
    public void encriptarAVL(NodoAVL nodo) {
        if (nodo != null) {
            nodo.dpi =Long.parseLong(encrip(Long.toString(nodo.dpi)));
            nodo.nombre = encrip(nodo.nombre);
            nodo.departamento = encrip(nodo.departamento);
            nodo.municipio = encrip(nodo.municipio);
            nodo.cantidad = encrip(nodo.cantidad);
            nodo.primerFvacuna = encrip(nodo.primerFvacuna);
            nodo.segundaFvacuna = encrip(nodo.segundaFvacuna);
            nodo.tercerarFvacuna = encrip(nodo.tercerarFvacuna);
            nodo.lugarVacunacion = encrip(nodo.lugarVacunacion);
            encriptarAVL(nodo.hijoIzquierdo);
            encriptarAVL(nodo.hijoDerecho);
        }
    }
    
    
    public void desencriptarAVL(NodoAVL nodo) {
        if (nodo != null) {
            
            nodo.dpi = Long.parseLong(desincrip(Long.toString(nodo.dpi)));
            nodo.nombre = desincrip(nodo.nombre);
            nodo.departamento = desincrip(nodo.departamento);
            nodo.municipio = desincrip(nodo.municipio);
            nodo.cantidad = desincrip(nodo.cantidad);
            nodo.primerFvacuna = desincrip(nodo.primerFvacuna);
            nodo.segundaFvacuna = desincrip(nodo.segundaFvacuna);
            nodo.tercerarFvacuna = desincrip(nodo.tercerarFvacuna);
            nodo.lugarVacunacion = desincrip(nodo.lugarVacunacion);
            desencriptarAVL(nodo.hijoIzquierdo);
            desencriptarAVL(nodo.hijoDerecho);
        }
    }


}
