package Boletin4.Ejercicio4;

public class Cocinero implements Runnable {
    private static final int TIEMPO_PREPARACION_MS = 5000;
    private final Bandeja bandeja;
    private final String nombre;
    private final int totalPlatos;

    public Cocinero(Bandeja bandeja, String nombre, int totalPlatos) {
        this.bandeja = bandeja;
        this.nombre = nombre;
        this.totalPlatos = totalPlatos;
    }

    @Override
    public void run() {
        System.out.println("[INICIO] " + nombre + " comienza su jornada (" + totalPlatos + " platos por preparar).");

        for (int plato = 1; plato <= totalPlatos; plato++) {
            System.out.println("[PREPARANDO] " + nombre + " está preparando el plato #" + plato + " (5 s)...");
            try {
                Thread.sleep(TIEMPO_PREPARACION_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[INTERRUPCIÓN] " + nombre + " interrumpido preparando el plato #" + plato);
            }

            bandeja.depositarPlato(plato, nombre);
        }

        System.out.println("[FIN] " + nombre + " ha completado su jornada y preparado los " + totalPlatos + " platos.");
    }
}