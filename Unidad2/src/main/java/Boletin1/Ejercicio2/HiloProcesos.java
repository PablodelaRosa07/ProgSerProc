package Boletin1.Ejercicio2;

class HiloProcesos implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("PROCESOS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo PROCESOS interrumpido.");
        }
    }
}
