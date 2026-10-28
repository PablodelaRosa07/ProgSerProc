package Simulacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Provincias {
	public static void main(String[] args) throws IOException {
		
		Provincias provincias = new Provincias();
		
		String nombreFichero = args[0];		
		String provinciaEscrita = provincias.leerProvincia();
		String ficheroSalida = provinciaEscrita + ".txt";
		
		
		try {
			int totalPedidos = provincias.crearFichero(nombreFichero, ficheroSalida, provinciaEscrita);
			System.out.println(provinciaEscrita + " : " + totalPedidos);

		} catch (IOException e) {
            System.out.println("Error al procesar el fichero: " + e.getMessage());
        }
	}
	
	private String leerProvincia() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce provincia: ");
		String provincia = sc.nextLine();
		
		return provincia;
	}
	
	public int crearFichero(String nombreFichero, String ficheroSalida, String provinciaEscrita) throws IOException {
		
		Scanner sc = null;
		int contador = 0;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida));
				BufferedReader br = new BufferedReader(new FileReader(nombreFichero))){

			String lineaLeida;
			
			
			while ((lineaLeida = br.readLine()) != null) {
				if (lineaLeida.contains(provinciaEscrita)) {
					bw.write(lineaLeida);					
					bw.newLine();
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
