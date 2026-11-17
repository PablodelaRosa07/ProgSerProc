package Boletin1.Ejercicio8;

import java.util.Scanner;

public class PrincipalHilos {
    public static void main(String[] args) {
        HiloServicios hilo1 = new HiloServicios();
        HiloProcesos hilo2 = new HiloProcesos();

        hilo1.start();
        hilo2.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); 

        hilo1.interrupt();
        hilo2.interrupt();

        System.out.println("Se ha pulsado Enter. Orden de interrupción enviada.");
    }
}