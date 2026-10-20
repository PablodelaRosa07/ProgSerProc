package Ejercicio1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcesadorFichero {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ProcesadorFichero procesadorFichero = new ProcesadorFichero();
		List<String> listaPalabras = new ArrayList<>();
		
	    String nombreFicheroEntrada = "src/resources/Fichero.txt";
	    String palabra = "servicio";
	    
	    procesadorFichero.contarPalabras(nombreFicheroEntrada, palabra, listaPalabras);
	    
	}
	
	
	public List<String> contarPalabras(String nombreFichero, String palabra, List<String> listaPalabras) throws FileNotFoundException{
		Scanner sc = null;
		try {
            FileReader fichero = new FileReader(nombreFichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String palabras = sc.next();
                listaPalabras.add(palabra);
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
        return listaPalabras;
	}


}
