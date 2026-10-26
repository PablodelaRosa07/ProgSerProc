package Ejercicio1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcesadorFichero {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
        String nombreFichero = args[0];
        String palabra = args[1];
        
        contarPalabra(nombreFichero, palabra);
	    
	}
	
	public static void contarPalabra(String nombreFichero, String palabra) throws FileNotFoundException {
		Scanner sc = null;
		int contador = 0;
		try {
            FileReader fichero = new FileReader(nombreFichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String palabraLeida = sc.next();
                if (palabraLeida.equalsIgnoreCase(palabra)) {
                	contador++;
                }
                
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
		System.out.println("La palabra '" +palabra+ "' aparece " +contador+ " veces.");
		
	}


		
	}
