package Boletin1.Ejercicio3;

class HiloProcesos implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) { // Limitamos a 3 iteraciones para que el hilo pueda terminar
                System.out.println("PROCESOS");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo PROCESOS interrumpido.");
        }
    }
}
