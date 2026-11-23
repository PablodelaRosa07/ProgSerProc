package Boletin4.Ejercicio4;

public class Bandeja {
    private static final int CAPACIDAD_MAXIMA = 3;
    private int platosDisponibles;

    public Bandeja() {
        this.platosDisponibles = 0;
    }

    public synchronized void depositarPlato(int numeroPlato, String nombreCocinero) {
        while (platosDisponibles >= CAPACIDAD_MAXIMA) {
            System.out.println("[ESPERA - BANDEJA LLENA] " + nombreCocinero
                    + " ha terminado el plato #" + numeroPlato
                    + ", pero hay " + platosDisponibles + " platos. Esperando hueco...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[INTERRUPCIÓN] " + nombreCocinero + " interrumpido mientras esperaba.");
            }
        }

        platosDisponibles = platosDisponibles + 1;
        System.out.println("[DEPÓSITO] " + nombreCocinero + " deja el plato #" + numeroPlato
                + " en la bandeja. Platos en bandeja: " + platosDisponibles + "/" + CAPACIDAD_MAXIMA);
        notifyAll();
    }

    public synchronized int retirarPlato(String nombreComensal) {
        int platoTomado = 0;

        while (platosDisponibles <= 0) {
            System.out.println("[ESPERA - BANDEJA VACÍA] " + nombreComensal
                    + " quiere un plato, pero no hay ninguno. Esperando al cocinero...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[INTERRUPCIÓN] " + nombreComensal + " interrumpido mientras esperaba.");
            }
        }

        platosDisponibles = platosDisponibles - 1;
        platoTomado = platosDisponibles + 1;
        System.out.println("[RETIRADA] " + nombreComensal + " retira un plato de la bandeja."
                + " Platos restantes en bandeja: " + platosDisponibles + "/" + CAPACIDAD_MAXIMA);
        notifyAll();

        return platoTomado;
    }
}