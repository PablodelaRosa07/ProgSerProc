package Todo;

import java.io.File;


import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CalculadorTamanoDirectorio {
	
	private static final Logger logger = LogManager.getLogger(CalculadorTamanoDirectorio.class);

	
	
	public static void main(String[] args) {
	    CalculadorTamanoDirectorio calculador = new CalculadorTamanoDirectorio();
	    
	    String ruta = calculador.introducirRuta();
	    File archivo = new File(ruta);
	    
	    double bytesTotales = calculador.calcularTamano(ruta);
	    calculador.formatearTamano(bytesTotales);
	    }
	
	
	
	
	
	public String introducirRuta() {
		Scanner sc = new Scanner(System.in);
		logger.info("Introduce la ruta: ");
		String archivo = sc.next();
		File directorio = new File(archivo);
		
		if (directorio.exists()) {
			logger.info("Entrando en la ruta.");
		}
		else {
			logger.info("No existe la ruta.");
		}
		return archivo;
	}
	
	
	public double calcularTamano(String directorio) {
        double totalBytes = 0;
        File archivo = new File(directorio);

        File[] ficheros = archivo.listFiles();
        if (ficheros != null) {
            for (File fichero : ficheros) {
                if (fichero.isFile()) {
                    totalBytes += fichero.length();
                } 
                else if (fichero.isDirectory()) {
                    totalBytes += calcularTamano(fichero.toString()); 
                }
            }
        }

        return totalBytes;
    }
	
	
	
	public void formatearTamano(double bytes) {
	    double kb = bytes / 1024.0;
	    double mb = kb / 1024.0;
	    logger.info("MB: " +mb+ " | KB: " +kb+ " | B: " +bytes);

	}
}
