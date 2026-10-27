import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GeneraNSSFichero {
    public static void main(String[] args) {
        GeneraNSSFichero generaNSSFichero = new GeneraNSSFichero();
        if (args.length == 0) {
            System.err.println("Debe proporcionar el nombre del fichero como argumento.");
        } else {
            String nombreFichero = args[0];
            
            try {
                // Llamamos a la función que procesa y nos devuelve un array con [contadorNSS, contadorTotal]
                int[] resultados = generaNSSFichero.crearFichero(nombreFichero);
                int contadorNSS = resultados[0];
                int contadorTotal = resultados[1];
                
                // Imprimimos ÚNICAMENTE los números separados por espacio para que el padre los lea bien
                System.out.println(contadorNSS + " " + contadorTotal);
                
            } catch (IOException e) {
                System.err.println("Error al procesar el fichero: " + e.getMessage());
            }
        }
    }
    
    public int[] crearFichero(String nombreFichero) throws IOException {
        int contadorNSS = 0;
        int contadorTotal = 0;
        Scanner sc = null;
        BufferedWriter ficheroResultado = null;
        
        try {
            sc = new Scanner(new File(nombreFichero));
            ficheroResultado = new BufferedWriter(new FileWriter("NSSs.txt"));
            
            while (sc.hasNextLine()) {
                String nssLeido = sc.nextLine();
                contadorTotal++; // Cuenta todas las líneas del fichero
                
                if (nssLeido.startsWith("AN")) {
                    contadorNSS++; // Cuenta solo las que empiezan por AN
                    ficheroResultado.write(nssLeido);
                    ficheroResultado.newLine();
                } 
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
            if (ficheroResultado != null) {
                ficheroResultado.close();
            }
        }
        
        // Devolvemos ambos contadores empaquetados en un array
        return new int[] { contadorNSS, contadorTotal };
    }
}