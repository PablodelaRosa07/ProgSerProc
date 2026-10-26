package Ejercicio2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class AnalizadorTemperatura {
	
	public static void main(String[] args) {
		if (args.length >= 2) {
			String nombreFichero = args[0];
			String valor = args[1];
			
			try {
				int contador = calcularDias(nombreFichero, valor);
				System.out.println(contador);
			} catch (FileNotFoundException e) {
				// Propagamos el error al proceso padre a través de System.err
				System.err.println("Error en el proceso hijo: El fichero '" + nombreFichero + "' no existe.");
				System.exit(1); // Indicamos que ha habido un error
			}
		}
	}
	
	public static int calcularDias(String nombreFichero, String valor) throws FileNotFoundException {
		Scanner sc = null;
		int contador = 0;
		int numero = Integer.parseInt(valor);
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
		return contador;
	}
}