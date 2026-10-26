package Ejercicio2;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AnalizadorTemperatura {
	
	public static void main(String[] args) throws IOException {
		String nombreFichero = args[0];
		String valor = args[1];
		
		int contador = calcularDias(nombreFichero, valor);
		crearExtension(contador, valor);
	}
	
	public static int calcularDias(String nombreFichero, String valor) throws FileNotFoundException {
		Scanner sc = null;
		int contador = 0;
		Integer numero = Integer.parseInt(valor);
		try {
            FileReader fichero = new FileReader(nombreFichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String numLeido = sc.next();
                int numEnteroLeido = Integer.parseInt(numLeido); 
                if (numEnteroLeido >= numero) {
                	contador++;
                }
                
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
		System.out.println("Hay " +contador+ " temperaturas igual o por encima de " +valor);
		return contador;
	}
	
	public static void crearExtension(int contador, String valor) throws IOException {
		Integer numero = Integer.parseInt(valor);
		String nombreFicheroUmbral = valor+".txt";
		try (BufferedWriter escritura = new BufferedWriter(new FileWriter(valor))){
			escritura.write("Días que superan el umbral '"+valor+"':"  +contador);
			System.out.println("Fichero creado con éxito: " + nombreFicheroUmbral);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
	}
}
