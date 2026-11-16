package Boletin1.Ejercicio6;

public class GestionaMascotas {
    public static void main(String[] args) {
        Mascota lulu = new Mascota("Lulú");
        
        int numCuidadores = 100;
        Thread[] cuidadores = new Thread[numCuidadores];

        for (int i = 0; i < numCuidadores; i++) {
            cuidadores[i] = new Thread(lulu, "Cuidador-" + (i + 1));
            cuidadores[i].start();
        }

        for (int i = 0; i < numCuidadores; i++) {
            try {
                cuidadores[i].join(); 
            } catch (InterruptedException e) {
                System.out.println("El hilo principal fue interrumpido durante el join.");
            }
        }

        System.out.println("Número total de veces que ha comido " + lulu.getVecesComida() + " veces.");
    }
}