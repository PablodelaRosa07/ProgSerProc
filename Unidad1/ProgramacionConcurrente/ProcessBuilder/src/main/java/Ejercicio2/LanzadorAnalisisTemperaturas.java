package Ejercicio2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanzadorAnalisisTemperaturas {

	public static void main(String[] args) throws InterruptedException {
		if (args.length >= 1) {
			String ficheroEntrada = args[0];
			compila();
			ejecuta(ficheroEntrada);
		} else {
			System.err.println("Debes proporcionar el fichero de entrada como argumento.");
		}
	}
	
	public static void compila() throws InterruptedException {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "AnalizadorTemperatura.java");
            pb.inheritIO();
            Process proceso = pb.start();
            
            int codigoRetorno = proceso.waitFor();
            if (codigoRetorno == 0) {
                System.out.println("Compilación realizada con éxito.\n");
            }
        } catch (IOException e) {
            System.err.println("Error de Entrada/Salida al intentar compilar: " + e.getMessage());
        }
    }
	
	public static void ejecuta(String ficheroEntrada) {
        int[] umbrales = {10, 20, 25, 30, 35};
        List<Process> listaProcesos = new ArrayList<>();
        int[] conteos = new int[umbrales.length];

        System.out.println("Lanzando procesos hijos para los 5 umbrales...\n");
        String classpathActual = System.getProperty("java.class.path");

        for (int i = 0; i < umbrales.length; i++) {
            int numero = umbrales[i];
            try {
                String cadena = Integer.toString(numero);
                
                ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpathActual, "Ejercicio2.AnalizadorTemperatura", ficheroEntrada, cadena
                );
                
                Process proceso = pb.start();
                listaProcesos.add(proceso);
                
            } catch (IOException e) {
                System.err.println("Error al intentar ejecutar el proceso " + numero + ": " + e.getMessage());
            }
        }

        // 2. Esperamos a que terminen y gestionamos si han tenido éxito o error
        boolean hayErrores = false;
        for (int i = 0; i < listaProcesos.size(); i++) {
            Process proceso = listaProcesos.get(i);
            int numero = umbrales[i];
            try {
                int exitCode = proceso.waitFor(); // Esperamos a que finalice el hijo
                
                if (exitCode != 0) {
                    hayErrores = true;
                    // El hijo falló: leemos la excepción de su flujo de errores (getErrorStream)
                    Scanner scError = new Scanner(proceso.getErrorStream());
                    while (scError.hasNextLine()) {
                        System.err.println("[PADRE] Excepción propagada del hijo (umbral " + numero + "): " + scError.nextLine());
                    }
                    scError.close();
                } else {
                    // El hijo tuvo éxito: leemos su resultado numérico normal
                    Scanner scHijo = new Scanner(proceso.getInputStream());
                    if (scHijo.hasNextInt()) {
                        conteos[i] = scHijo.nextInt();
                    }
                    scHijo.close();
                }
            } catch (InterruptedException e) {
                System.err.println("El proceso fue interrumpido.");
            }
        }

        // 3. Si todo fue bien, mostramos los resultados sumados
        if (!hayErrores) {
            System.out.println("Resultados obtenidos de los subprocesos:");
            for (int i = 0; i < umbrales.length; i++) {
                System.out.println("Umbral " + umbrales[i] + ": " + conteos[i] + " temperaturas");
            }
        }

        System.out.println("\nEjecución finalizada.");
    }
	
	
	// ficheroQueNoExista.txt 
}