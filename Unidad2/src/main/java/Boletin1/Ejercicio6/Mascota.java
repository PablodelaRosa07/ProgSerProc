package Boletin1.Ejercicio6;

public class Mascota implements Runnable {
    private String nombre;
    private int vecesComida;

    public Mascota(String nombre) {
        this.nombre = nombre;
        this.vecesComida = 0;
    }

    public void comer() {
        vecesComida++;
        System.out.println("La mascota " + nombre + " ha comido. (Comida nº " + vecesComida + ")");
        
        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            System.out.println("El hilo de " + nombre + " fue interrumpido.");
        }
        
    }

    @Override
    public void run() {
        comer();
    }

    public int getVecesComida() {
        return vecesComida;
    }
}
