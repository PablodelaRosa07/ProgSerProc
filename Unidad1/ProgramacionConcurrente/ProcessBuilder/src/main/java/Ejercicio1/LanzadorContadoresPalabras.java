package Ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanzadorContadoresPalabras {

    public static void main(String[] args) {
        if (args.length >= 1) {
            String ficheroEntrada = args[0];
            compila();
            ejecuta(ficheroEntrada);
        }
    }

    public static void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "ProcesadorFichero.java");
            pb.inheritIO();
            Process proceso = pb.start();
            
            int codigoRetorno = proceso.waitFor();
            if (codigoRetorno == 0) {
                System.out.println("Compilación realizada con éxito.");
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public static void ejecuta(String ficheroEntrada) {
        String[] palabras = { "servicio", "hilo", "proceso", "multihilo", "concurrencia" };
        List<Process> listaProcesos = new ArrayList<>();

        System.out.println("Lanzando procesos hijos para las 5 palabras...");

        for (String palabra : palabras) {
            String ficheroResultado = palabra + ".txt";
            try {
                ProcessBuilder pb = new ProcessBuilder("java", "Ejercicio1.ProcesadorFichero", ficheroEntrada, palabra, ficheroResultado);
                Process proceso = pb.start();
                listaProcesos.add(proceso);
                
                System.out.println("Proceso lanzado para: '" + palabra + "'");
            } catch (IOException e) {
            }
        }

        for (int i = 0; i < listaProcesos.size(); i++) {
            try {
                listaProcesos.get(i).waitFor();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("Todos los subprocesos han finalizado. Leyendo resultados...");

        int totalGeneral = 0;
        int[] conteos = new int[palabras.length];

        for (int i = 0; i < palabras.length; i++) {
            String ficheroResultado = palabras[i] + ".txt";
            Scanner sc = null;
            try {
                sc = new Scanner(new File(ficheroResultado));
                if (sc.hasNextInt()) {
                    conteos[i] = sc.nextInt();
                    totalGeneral += conteos[i];
                }
            } catch (IOException e) {
            } finally {
                if (sc != null) {
                    sc.close();
                }
            }
        }

        System.out.println("Todas las palabras aparecen un total de " + totalGeneral + " veces");

        System.out.print("Aparecen ");
        for (int i = 0; i < palabras.length; i++) {
            System.out.print(conteos[i] + " veces la palabra " + palabras[i]);
            if (i < palabras.length - 1) {
                System.out.print(", ");
            } else {
                System.out.print(".");
            }
        }
        System.out.println();
    }
}