package Boletin1.Ejercicio1;

public class PrincipalHilos {
    public static void main(String[] args) {
        HiloServicios hilo1 = new HiloServicios();
        HiloProcesos hilo2 = new HiloProcesos();

        hilo1.start();
        hilo2.start();
    }
}