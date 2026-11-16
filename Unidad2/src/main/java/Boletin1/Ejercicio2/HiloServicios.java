package Boletin1.Ejercicio2;

class HiloServicios implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("SERVICIOS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo SERVICIOS interrumpido.");
        }
    }
}