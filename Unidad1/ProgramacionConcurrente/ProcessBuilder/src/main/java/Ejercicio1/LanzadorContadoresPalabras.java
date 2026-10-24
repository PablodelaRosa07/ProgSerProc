package Ejercicio1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanzadorContadoresPalabras {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
	    String nombreFicheroEntrada = "src/main/resources/Fichero.txt";
	    String[] palabras= { "servicio", "hilo", "proceso", "multihilo", "concurrencia" };
	    List<String> listaPalabras = new ArrayList<>();
	    compila();
        ejecuta(nombreFicheroEntrada);
	    
	}

	
	public static List<String> contarPalabras(String nombreFichero, String palabra, List<String> listaPalabras) throws FileNotFoundException {
		Scanner sc = null;
		try {
            FileReader fichero = new FileReader(nombreFichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String palabras = sc.next();
                listaPalabras.add(palabras);
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
        return listaPalabras;
	}
	
	public static void contarYGuardar(List<String> listaPalabras, String palabraBuscada, String ficheroResultado) throws IOException {
		int contador = 0;
		
		for (String palabra : listaPalabras) {
			if (palabra.equalsIgnoreCase(palabraBuscada)) {
				contador++;
			}
		}	
	}
	
	
	public static void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "LanzadorContadoresPalabras.java");
            pb.inheritIO();
            Process proceso = pb.start();
            
            int codigoRetorno = proceso.waitFor();
            if (codigoRetorno == 0) {
                System.out.println("[Lanzador] Compilación realizada con éxito.");
            }
        } catch (IOException | InterruptedException e) {
        }
    }
	
	public static void ejecuta(String ficheroEntrada) {
        String[] palabras = { "servicio", "hilo", "proceso", "multihilo", "concurrencia" };
        List<Process> listaProcesos = new ArrayList<>();

        System.out.println("[Lanzador] Lanzando procesos hijos para las 5 palabras...");

        for (String palabra : palabras) {
            String ficheroResultado = palabra + ".txt";
            try {
                ProcessBuilder pb = new ProcessBuilder("java", "Ejercicio1.ProcesadorFichero", ficheroEntrada, palabra, ficheroResultado);
                pb.inheritIO();
                
                Process proceso = pb.start();
                listaProcesos.add(proceso);
                
                System.out.println("Proceso lanzado para: '" + palabra + "' (Guardando en " + ficheroResultado + ")");
            } catch (IOException e) {
            }
        }

        for (int i = 0; i < listaProcesos.size(); i++) {
            try {
                listaProcesos.get(i).waitFor();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("[Lanzador] Todos los procesos hijos han finalizado su ejecución.");
    }
}

