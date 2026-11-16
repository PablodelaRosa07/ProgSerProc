package Ejercicio8;

class HiloProcesos extends Thread {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("PROCESOS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hilo PROCESOS interrumpido.");
        }
    }
}