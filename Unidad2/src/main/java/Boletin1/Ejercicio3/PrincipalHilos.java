package Boletin1.Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class PrincipalHilos {
    public static void main(String[] args) {
        Runnable tareaServicios = new HiloServicios();
        Runnable tareaProcesos = new HiloProcesos();

        Thread hilo1 = new Thread(tareaServicios, "Hilo-Servicios");
        Thread hilo2 = new Thread(tareaProcesos, "Hilo-Procesos");

        List<Thread.State> estadosHilo1 = new ArrayList<>();
        List<Thread.State> estadosHilo2 = new ArrayList<>();

        estadosHilo1.add(hilo1.getState());
        estadosHilo2.add(hilo2.getState());

        hilo1.start();
        hilo2.start();

        while (hilo1.isAlive() || hilo2.isAlive()) {
            Thread.State estado1 = hilo1.getState();
            if (estadosHilo1.get(estadosHilo1.size() - 1) != estado1) {
                estadosHilo1.add(estado1);
            }

            Thread.State estado2 = hilo2.getState();
            if (estadosHilo2.get(estadosHilo2.size() - 1) != estado2) {
                estadosHilo2.add(estado2);
            }

            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        estadosHilo1.add(hilo1.getState());
        estadosHilo2.add(hilo2.getState());


        System.out.println("Estados de Hilo Servicios: " + estadosHilo1);
        System.out.println("Estados de Hilo Procesos:  " + estadosHilo2);
    }
}