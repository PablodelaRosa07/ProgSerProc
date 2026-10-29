package Simulacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class LanzadorProvincias {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, introduce la ruta del fichero de entrada como argumento.");
            return;
        }

        String ficheroEntrada = args[0];
        LanzadorProvincias lanzador = new LanzadorProvincias();

        lanzador.compila();
        lanzador.ejecuta(ficheroEntrada);
    }
    
    public void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "-d", ".", "src/main/java/Simulacion/Provincias.java");
            pb.inheritIO(); 
            Process proceso = pb.start();
            
            int codigoSalida = proceso.waitFor();

            if (codigoSalida == 0) {
                System.out.println("Clase compilada con éxito.\n");
            } else {
                System.out.println("Error durante la compilación. Revisa el código de la clase hija.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar compilar: " + e.getMessage());
        }
    }
    
    public void ejecuta(String ficheroEntrada) {
        String[] provinciasAndalucia = {"Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla"};
        List<Process> procesos = new ArrayList<>();
        int totalPedidosGlobal = 0;

        try {
            for (String provincia : provinciasAndalucia) {
                ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "Simulacion.Provincias", ficheroEntrada);                
                Process proceso = pb.start();

                // Usamos try-with-resources para escribir y cerrar el stream inmediatamente.
                // Esto le envía la señal EOF al hijo para que deje de esperar y empiece a procesar.
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                    bw.write(provincia);
                    bw.newLine();
                    bw.flush();
                }

                procesos.add(proceso);
                System.out.println("Subproceso lanzado para: " + provincia);
            }

            System.out.println("Esperando a que todos los subprocesos finalicen.");

            // Bucle para esperar y leer los resultados de cada hijo
            for (int i = 0; i < procesos.size(); i++) {
                Process proceso = procesos.get(i);
                
                try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        // Filtramos para ignorar textos de consola y capturar solo el formato "Provincia : X"
                        if (linea.contains(":") && !linea.contains("Introduce")) {
                            System.out.println(linea); 
                            
                            String[] partes = linea.split(":");
                            if (partes.length > 1) {
                                try {
                                    int pedidosProvincia = Integer.parseInt(partes[1].trim());
                                    totalPedidosGlobal += pedidosProvincia;
                                } catch (NumberFormatException e) {
                                    // Ignorar si no es un número
                                }
                            }
                        }
                    }
                }
                
                proceso.waitFor();
            }

            // 3. Imprimimos el resultado total global solicitado
            System.out.println(" Nº total de Pedidos : " + totalPedidosGlobal);
            System.out.println("Todos los subprocesos han terminado correctamente");

        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrió un error durante la ejecución de los subprocesos: " + e.getMessage());
        }
    }
}