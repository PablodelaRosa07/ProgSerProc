package Boletin4.Ejercicio4;

public class Restaurante {
    public static void main(String[] args) {
        Bandeja bandeja = new Bandeja();

        Cocinero cocineroTask = new Cocinero(bandeja, "Cocinero-1", 12);
        Comensal comensal1Task = new Comensal(bandeja, "Comensal-1", 4);
        Comensal comensal2Task = new Comensal(bandeja, "Comensal-2", 4);
        Comensal comensal3Task = new Comensal(bandeja, "Comensal-3", 4);

        Thread hiloCocinero = new Thread(cocineroTask, "Hilo-Cocinero");
        Thread hiloComensal1 = new Thread(comensal1Task, "Hilo-Comensal-1");
        Thread hiloComensal2 = new Thread(comensal2Task, "Hilo-Comensal-2");
        Thread hiloComensal3 = new Thread(comensal3Task, "Hilo-Comensal-3");

        System.out.println("=== APERTURA DEL RESTAURANTE ===");
        hiloCocinero.start();
        hiloComensal1.start();
        hiloComensal2.start();
        hiloComensal3.start();

        try {
            hiloCocinero.join();
            hiloComensal1.join();
            hiloComensal2.join();
            hiloComensal3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[ERROR] Hilo principal interrumpido.");
        }

        System.out.println("=== CIERRE DEL RESTAURANTE ===");
    }
}
