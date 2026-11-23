package Boletin4.Ejercicio4;

public class Comensal implements Runnable {
    private static final int TIEMPO_COMER_MS = 3000;
    private final Bandeja bandeja;
    private final String nombre;
    private final int platosAConsumir;

    public Comensal(Bandeja bandeja, String nombre, int platosAConsumir) {
        this.bandeja = bandeja;
        this.nombre = nombre;
        this.platosAConsumir = platosAConsumir;
    }

    @Override
    public void run() {
        System.out.println("[LLEGADA] " + nombre + " entra al restaurante (comerá " + platosAConsumir + " platos).");

        for (int i = 1; i <= platosAConsumir; i++) {
            bandeja.retirarPlato(nombre);

            System.out.println("[COMIENDO] " + nombre + " está comiendo su plato " + i + "/" + platosAConsumir + " (3 s)...");
            try {
                Thread.sleep(TIEMPO_COMER_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[INTERRUPCIÓN] " + nombre + " interrumpido mientras comía.");
            }
        }

        System.out.println("[SALIDA] " + nombre + " ha terminado sus " + platosAConsumir + " platos y abandona el restaurante.");
    }
}