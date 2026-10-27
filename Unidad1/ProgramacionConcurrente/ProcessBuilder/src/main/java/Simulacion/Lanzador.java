package Simulacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

    public static void main(String[] args) {
    	String ficheroEntrada = args[0];
        String[] provinciasAndalucia = {
            "Sevilla", "Málaga", "Cádiz", "Granada", 
            "Córdoba", "Jaén", "Huelva", "Almería"
        };

        List<Process> listaProcesos = new ArrayList<>();

        System.out.println("Generando ficheros para Andalucía");

        for (String provincia : provinciasAndalucia) {
            try {

                ProcessBuilder pb = new ProcessBuilder("java", "Simulacion.FiltroProvincia", provincia, ficheroEntrada);
                
                pb.inheritIO();
                
                Process proceso = pb.start();
                listaProcesos.add(proceso);
                
                System.out.println("[Padre] Subproceso lanzado para la provincia de: " + provincia);

            } catch (IOException e) {
                System.err.println("[Error] No se pudo iniciar el proceso para " + provincia + ": " + e.getMessage());
            }
        }

        System.out.println("[Padre] Esperando a que finalicen todos los subprocesos...");
        
        for (int i = 0; i < listaProcesos.size(); i++) {
            try {
                Process procesoHijo = listaProcesos.get(i);
                int codigoSalida = procesoHijo.waitFor(); 
                
                System.out.println("[Padre] Hijo " + (i + 1) + " (" + provinciasAndalucia[i] + ") terminó con código de salida: " + codigoSalida);
                
            } catch (InterruptedException e) {
                System.err.println("[Error] El proceso padre fue interrumpido mientras esperaba.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("TODOS LOS PROCESOS HIJOS HAN FINALIZADO. Ficheros generados con éxito.");
    }
}