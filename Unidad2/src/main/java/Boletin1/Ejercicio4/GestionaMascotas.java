package Boletin1.Ejercicio4;

public class GestionaMascotas {
    public static void main(String[] args) {
        Mascota lulu = new Mascota("Lulú");

        Thread cuidador1 = new Thread(lulu, "Cuidador-1");
        Thread cuidador2 = new Thread(lulu, "Cuidador-2");
        Thread cuidador3 = new Thread(lulu, "Cuidador-3");
        Thread cuidador4 = new Thread(lulu, "Cuidador-4");
        Thread cuidador5 = new Thread(lulu, "Cuidador-5");

        cuidador1.start();
        cuidador2.start();
        cuidador3.start();
        cuidador4.start();
        cuidador5.start();

        try {
            cuidador1.join();
            cuidador2.join();
            cuidador3.join();
            cuidador4.join();
            cuidador5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nValor final del contador de veces que ha comido Lulú: " + lulu.getVecesComida());
    }
}
