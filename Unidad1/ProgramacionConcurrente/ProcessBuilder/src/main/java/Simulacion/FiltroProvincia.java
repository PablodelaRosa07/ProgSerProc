package Simulacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FiltroProvincia {
    
    public static void main(String[] args) {
		String provincia = args[0];
        String ficheroEntrada = args[1];
        String ficheroSalida = provincia + ".txt";

        try (BufferedReader lector = new BufferedReader(new FileReader(ficheroEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroSalida))) {

            String linea;
            int contadorLineas = 0;

            while ((linea = lector.readLine()) != null) {
                if (linea.contains(provincia)) {
                    escritor.write(linea);
                    escritor.newLine();
                    contadorLineas++;
                }
            }

            System.out.println("Proceso hijo finalizado: Se han extraído " + contadorLineas + " líneas para la provincia de " + provincia + " en el fichero '" + ficheroSalida + "'.");

        } catch (IOException e) {
            System.err.println("Error de E/S al procesar la provincia " + provincia + ": " + e.getMessage());
            System.exit(2);
        }
	}
    
}
