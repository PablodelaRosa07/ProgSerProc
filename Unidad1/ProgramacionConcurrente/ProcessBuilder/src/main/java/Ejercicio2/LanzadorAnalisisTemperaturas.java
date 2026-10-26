package Ejercicio2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanzadorAnalisisTemperaturas {

	public static void main(String[] args) {
		
	}
	
	
	public static void compila() throws InterruptedException {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "AnalizadorTemperatura.java");
            pb.inheritIO();
            Process proceso = pb.start();
            
            int codigoRetorno = proceso.waitFor();
            if (codigoRetorno == 0) {
                System.out.println("Compilación realizada con éxito.");
            }
        } catch (IOException e) {
            System.err.println("Error de Entrada/Salida al intentar compilar: " + e.getMessage());
        }
    }
	
	
}
