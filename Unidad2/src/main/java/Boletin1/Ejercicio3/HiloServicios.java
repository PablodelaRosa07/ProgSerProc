package Boletin1.Ejercicio3;

class HiloServicios implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) { // Limitamos a 3 iteraciones
                System.out.println("SERVICIOS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo SERVICIOS interrumpido.");
        }
    }
}