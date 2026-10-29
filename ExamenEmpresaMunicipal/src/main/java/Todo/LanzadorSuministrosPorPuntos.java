package Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanzadorSuministrosPorPuntos {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		LanzadorSuministrosPorPuntos lanzador = new LanzadorSuministrosPorPuntos();
		String nombreFichero = args[0];
		
		lanzador.compila();
		lanzador.ejecuta(nombreFichero);
	}
	
	public void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "-d", ".", "src/main/java/Todo/LanzadorSuministrosPorPuntos.java");
            pb.inheritIO(); 
            Process proceso = pb.start();
            
            int codigoSalida = proceso.waitFor(); 

            if (codigoSalida == 0) {
                System.out.println("Clase compilada con éxito.");
            } else {
                System.out.println("Error durante la compilación. Revisa el código de la clase hija.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar compilar: " + e.getMessage());
        }
    }
	
	public void ejecuta(String nombreFichero) throws FileNotFoundException, InterruptedException {
		List<String> listaLineas = new ArrayList<String>();
		List<Process> procesos = new ArrayList<Process>();
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File(nombreFichero));
			
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				listaLineas.add(linea);
			
			}
		} catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
		
		
		try {
            for (String linea : listaLineas) {
                ProcessBuilder pb = new ProcessBuilder("java","src/main/java/Todo/ProcesadorSuministrosPorPuntos", nombreFichero, linea);                
                Process proceso = pb.start(); 

                procesos.add(proceso);
                
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                    bw.write(linea);
                    bw.newLine();
                }
                
                
                
            }
            System.out.println("Todos los subprocesos terminados.");
            
		} catch (IOException e) {
            System.out.println("Ocurrió un error durante la ejecución de los subprocesos: " + e.getMessage());
        }
		
		
		
		}
	
}
