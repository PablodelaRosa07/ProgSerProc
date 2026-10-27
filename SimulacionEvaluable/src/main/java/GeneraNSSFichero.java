
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GeneraNSSFichero {
	public static void main(String[] args) throws IOException {

		String nombreFichero = args[0];
		
		crearFichero(nombreFichero);
	}
	
	public static int crearFichero(String nombreFichero) throws IOException {
		Scanner sc = null;
		int contador = 0;
		try {
			sc = new Scanner(nombreFichero);
			FileWriter ficheroResultado = new FileWriter("NSSs.txt");
			
			while (sc.hasNextLine()) {
                String nssLeido = sc.nextLine();
                contador++;
                if (nssLeido.startsWith("AN")) {
                	ficheroResultado.write(nssLeido);                	
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
