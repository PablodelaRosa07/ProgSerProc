package Boletin1.Ejercicio1;

class HiloServicios extends Thread {
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

