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
	    if (args.length >= 3) {
	        String nombreFicheroEntrada = args[0];
	        String palabra = args[1];
	        String nombreFicheroResultado = args[2];
	        
	        List<String> listaPalabras = new ArrayList<>();
	        obtenerListaPalabras(nombreFicheroEntrada, listaPalabras);
	        contarYGuardar(listaPalabras, palabra, nombreFicheroResultado);
	    }
	}
	
	public static void obtenerListaPalabras(String nombreFichero, List<String> listaPalabras) throws FileNotFoundException {
		Scanner sc = null;
		try {
            FileReader fichero = new FileReader(nombreFichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String palabraLeida = sc.next();
                listaPalabras.add(palabraLeida);
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
	}

	public static void contarYGuardar(List<String> listaPalabras, String palabraBuscada, String ficheroResultado) throws IOException {
		int contador = 0;
		
		for (String p : listaPalabras) {
			if (p.equalsIgnoreCase(palabraBuscada)) {
				contador++;
			}
		}

		
	}
}