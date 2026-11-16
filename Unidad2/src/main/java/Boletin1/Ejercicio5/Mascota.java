package Boletin1.Ejercicio5;

public class Mascota implements Runnable {
    private String nombre;
    private int vecesComida;

    public Mascota(String nombre) {
        this.nombre = nombre;
        this.vecesComida = 0;
    }

    public void comer() {
        vecesComida++;
        
        System.out.println("La mascota " + nombre + " ha comenzado a comer. (Comida nº " + vecesComida + ")");
        
        try {
            Thread.sleep(vecesComida * 1000L);
        } catch (InterruptedException e) {
            System.out.println("El hilo de " + nombre + " fue interrumpido.");
        }
        
        System.out.println("La mascota " + nombre + " ha terminado de comer.");
    }

    @Override
    public void run() {
        comer();
    }

    public int getVecesComida() {
        return vecesComida;
    }
}