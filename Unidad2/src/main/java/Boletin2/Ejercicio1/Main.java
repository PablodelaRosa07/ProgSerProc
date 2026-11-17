package Boletin2.Ejercicio1;

public class Main {
    public static void main(String[] args) {
        Protectora protectora = new Protectora(50);
        int totalCuidadores = 100;

        for (int i = 0; i < totalCuidadores; i++) {
            Cuidador cuidador = new Cuidador("Cuidador-" + i, protectora);

            int prioridad = (i / 10) + 1;
            
            cuidador.setPriority(prioridad);
            
            cuidador.start();
        }
    }
}