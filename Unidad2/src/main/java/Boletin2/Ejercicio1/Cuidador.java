package Boletin2.Ejercicio1;

public class Cuidador extends Thread {
    private final Protectora protectora;

    public Cuidador(String nombre, Protectora protectora) {
        super(nombre);
        this.protectora = protectora;
    }

    @Override
    public void run() {
        int prioridadActual = this.getPriority();
        
        System.out.println("Iniciando " + this.getName() + " con prioridad: " + prioridadActual);
        
        protectora.alimentarAnimal(this.getName(), prioridadActual);
    }
}