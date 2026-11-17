package Boletin2.Ejercicio2;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContadorFicherosParalelo {
	
	public static void main(String[] args) {
        ContadorFicherosParalelo instancia = new ContadorFicherosParalelo();
        instancia.ejecutarPrograma();
    }
	

	public void obtenerFicheros(File directorio, List<File> listaFicheros) {
        File[] ficheros = directorio.listFiles();
        if (ficheros != null) {
            for (int i = 0; i < ficheros.length; i++) {
                File file = ficheros[i];
                if (file.isDirectory()) {
                    obtenerFicheros(file, listaFicheros);
                } else if (file.isFile()) {
                    listaFicheros.add(file);
                }
            }
        }
    }

    public void ejecutarPrograma() {
        File carpetaResources = new File("resources");

        if (!carpetaResources.exists() || !carpetaResources.isDirectory()) {
            System.err.println("La carpeta 'resources' no existe o no es un directorio válido.");
        } else {
            List<File> listaFicheros = new ArrayList<File>();
            obtenerFicheros(carpetaResources, listaFicheros);

            int nThreads = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);

            List<Future<ResultadoFichero>> futuros = new ArrayList<Future<ResultadoFichero>>();

            for (int i = 0; i < listaFicheros.size(); i++) {
                final File ficheroActual = listaFicheros.get(i);

                Callable<ResultadoFichero> tarea = new Callable<ResultadoFichero>() {
                    @Override
                    public ResultadoFichero call() throws Exception {
                        byte[] bytes = Files.readAllBytes(ficheroActual.toPath());
                        String contenido = new String(bytes, StandardCharsets.UTF_8);
                        long caracteres = contenido.length();
                        return new ResultadoFichero(ficheroActual.getName(), caracteres);
                    }
                };

                futuros.add(executor.submit(tarea));
            }

            executor.shutdown();

            System.out.println("Resultados del recuento de caracteres en paralelo");
            for (int i = 0; i < futuros.size(); i++) {
                Future<ResultadoFichero> futuro = futuros.get(i);
                try {
                    ResultadoFichero res = futuro.get();
                    System.out.println("Fichero: " + res.getNombreFichero() + " -> " + res.getNumCaracteres() + " caracteres.");
                } catch (InterruptedException e) {
                    System.err.println("Hilo interrumpido: " + e.getMessage());
                } catch (ExecutionException e) {
                    System.err.println("Error al procesar el resultado de un fichero: " + e.getMessage());
                }
            }
        }
    }

    
}
