package Boletin1.Ejercicio1;

class HiloProcesos extends Thread {
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
