package Boletin1.Ejercicio2;

public class PrincipalHilos {
    public static void main(String[] args) {
        // Creamos las instancias de las tareas Runnable
        Runnable tareaServicios = new HiloServicios();
        Runnable tareaProcesos = new HiloProcesos();

        // Envolvemos los Runnable en objetos Thread para poder ejecutarlos
        Thread hilo1 = new Thread(tareaServicios);
        Thread hilo2 = new Thread(tareaProcesos);

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
    }
}