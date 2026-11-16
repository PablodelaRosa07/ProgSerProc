package Ejercicio8;

class HiloServicios extends Thread {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("SERVICIOS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hilo SERVICIOS interrumpido.");
        }
    }
}

