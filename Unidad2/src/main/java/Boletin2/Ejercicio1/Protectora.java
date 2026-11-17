package Boletin2.Ejercicio1;

public class Protectora {
    private int comidaDisponible;

    public Protectora(int comidaInicial) {
        this.comidaDisponible = comidaInicial;
    }

    public synchronized void alimentarAnimal(String nombreCuidador, int prioridad) {
        if (comidaDisponible > 0) {
            comidaDisponible--;
            System.out.println(nombreCuidador + " [Prioridad: " + prioridad + "] ha alimentado a un animal. Comida restante: " + comidaDisponible);
        } else {
            System.out.println(nombreCuidador + " [Prioridad: " + prioridad + "] intentó alimentar, pero no queda comida.");
        }
    }
}