package Boletin4.Ejercicio3;

public class Cuenta {
    private int saldo;

    public Cuenta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void ingresarDinero(int cantidad, String nombreHilo) {
        saldo += cantidad;
        System.out.println("[INGRESO] " + nombreHilo + " ha ingresado " + cantidad + "€. Saldo actual: " + saldo + "€");
        // Avisamos a los hilos que estén esperando para retirar dinero
        notifyAll();
    }

    public synchronized void retirarDinero(int cantidad, String nombreHilo) {
        int intentos = 0;
        boolean operacionRealizada = false;

        // Mientras no haya saldo suficiente, el hilo espera (con un límite de reintentos para evitar bloqueos infinitos si no hay más ingresos)
        while (saldo < cantidad && intentos < 3) {
            System.out.println("[AVISO] " + nombreHilo + " quiere retirar " + cantidad + "€, pero solo hay " + saldo + "€. Esperando ingreso...");
            try {
                wait(1000); // Espera hasta 1 segundo a que haya un ingreso
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            intentos++;
        }

        if (saldo >= cantidad) {
            saldo -= cantidad;
            operacionRealizada = true;
            System.out.println("[REINTEGRO] " + nombreHilo + " ha retirado " + cantidad + "€. Saldo actual: " + saldo + "€");
        } else {
            System.out.println("[ERROR] " + nombreHilo + " no pudo retirar " + cantidad + "€ por saldo insuficiente. Saldo actual: " + saldo + "€");
        }
    }
}
