package Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class ProcesadorSuministrosPorPunto {
	public static void main(String[] args) {
		ProcesadorSuministrosPorPunto pspp = new ProcesadorSuministrosPorPunto();
		String nombreFichero = args[0];
		String ficheroSalida = "FicheroSalida.txt";

		
		String lineaEscrita = pspp.devuelveResultado();
		
		try {
			pspp.mostrarResultado(lineaEscrita, nombreFichero, ficheroSalida);

		} catch (IOException e) {
            System.out.println("Error al procesar el fichero: " + e.getMessage());
        }
	}
	
		
	public String devuelveResultado() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce: ");
		String linea = sc.nextLine();
		
		return linea;
	}
	
	public void mostrarResultado(String lineaEscrita, String nombreFichero, String ficheroSalida) throws FileNotFoundException, IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))){
			String lineaLeida;
			
			while ((lineaLeida = br.readLine()) != null) {
                if (lineaLeida.contains(";")) {
                    System.out.println(lineaLeida); 
                    int recarga = 0;
                    String[] partes = lineaLeida.split(";");
                    if (partes.length > 1) {
                        try {
                            for (int j = 1; j < partes.length; j++) {
								Integer partesNumero = Integer.parseInt(partes[j]);
								recarga += partesNumero;
							}
                           
                        } catch (NumberFormatException e) {
                            // Ignorar si no es un número
                        }
		}
			
		if (partes.length > 1) {
            try {
                for (int j = 1; j < partes.length; j++) {
					Integer partesNumero = Integer.parseInt(partes[j]);
					recarga += partesNumero;
				}
               
            } catch (NumberFormatException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            } 
		
		}
		double precioTotal = recarga*0.30;
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida));
				BufferedReader reader = new BufferedReader(new FileReader(nombreFichero))){

			String linea;
			
			
			while ((linea = reader.readLine()) != null) {					
					bw.write(linea);					
					bw.newLine();				
			}
			bw.write("TOTAL facturado: " +(precioTotal));
			
				
		 
        }
                }
			}
		}
	}
}


