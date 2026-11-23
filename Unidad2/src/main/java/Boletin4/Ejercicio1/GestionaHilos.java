package Boletin4.Ejercicio1;

public class GestionaHilos {

	public static void main(String[] args) {
	
        ContadorVocalesHilos contador = new ContadorVocalesHilos();
        String textoPrueba = "Programacion concurrente con hilos en Java y sincronizacion.";

        
        System.out.println("Texto: " + textoPrueba);

        
        char[] vocales = {'a', 'e', 'i', 'o', 'u'};
        HiloContadorVocal[] hilos = new HiloContadorVocal[vocales.length];

        int i = 0;
        while (i < vocales.length) {
            hilos[i] = new HiloContadorVocal(textoPrueba, vocales[i], contador);
            hilos[i].start();
            i = i + 1;
        }

        int j = 0;
        while (j < hilos.length) {
            try {
                hilos[j].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            j = j + 1;
        }
        
        int total = contador.getVocalesTotales();
        System.out.println("Total de vocales: " + total);    
        }
	
}
