import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneradorHacienda {

    public static void main(String[] args) {
    	GeneradorHacienda generadorHacienda = new GeneradorHacienda();
        String nombreFichero = args[0];

        generadorHacienda.compila();
        generadorHacienda.ejecuta(nombreFichero);
        }
    

    public void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "C:\\Users\\prosg\\Desktop\\Modulos\\2ºDAM\\ProgSerProc\\SimulacionEvaluable\\src\\main\\java\\GeneraNSSFichero.java");
            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();
            
            if (codigoSalida == 0) {
                System.out.println("Compilación del hijo realizada con éxito.");
            } else {
                System.err.println("Error al compilar el fichero del proceso hijo.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Excepción durante la compilación: " + e.getMessage());
        }
    }

    public void ejecuta(String nombreFichero) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "C:\\Users\\prosg\\Desktop\\Modulos\\2ºDAM\\ProgSerProc\\SimulacionEvaluable\\src\\main\\java\\GeneraNSSFichero", nombreFichero);
            Process proceso = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String lineaLeida = reader.readLine();
            
            int codigoSalida = proceso.waitFor();

            if (codigoSalida == 0 && lineaLeida != null) {
                String[] partes = lineaLeida.trim().split(" ");
                int lineasNSS = Integer.parseInt(partes[0]);
                int lineasTotales = Integer.parseInt(partes[1]);

                double porcentaje = 0.0;
                if (lineasTotales > 0) {
                    porcentaje = (double) lineasNSS / lineasTotales * 100.0;
                }

                System.out.println("Resultados del tratamiento de Hacienda");
                System.out.println("Líneas con NSS: " + lineasNSS);
                System.out.println("Líneas totales procesadas: " + lineasTotales);
                System.out.printf("Porcentaje de personas con NSS: " +porcentaje);
            } else {
                System.err.println("El proceso hijo no finalizó correctamente.");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al gestionar la ejecución del proceso: " + e.getMessage());
        }
    }
}