package Boletin4.Ejercicio3;

public class CajeroAutomatico {
    private Cuenta cuenta;

    public CajeroAutomatico(int saldoInicial) {
        this.cuenta = new Cuenta(saldoInicial);
    }

    public void iniciarSimulacion() {
        System.out.println("=== INICIANDO CAJERO AUTOMÁTICO ===");
        System.out.println("Saldo inicial de la cuenta: " + cuenta.getSaldo() + "€\n");

        // Creamos 2 hilos de ingreso y 2 de retirada (5 operaciones cada uno)
        HiloIngreso ingreso1 = new HiloIngreso(cuenta, "Hilo-Ingreso-1", 5);
        HiloIngreso ingreso2 = new HiloIngreso(cuenta, "Hilo-Ingreso-2", 5);
        HiloRetirada retirada1 = new HiloRetirada(cuenta, "Hilo-Retirada-1", 5);
        HiloRetirada retirada2 = new HiloRetirada(cuenta, "Hilo-Retirada-2", 5);

        // Arrancamos los hilos intercalándolos
        ingreso1.start();
        retirada1.start();
        ingreso2.start();
        retirada2.start();

        // Esperamos a que todos los hilos terminen antes de mostrar el saldo final
        try {
            ingreso1.join();
            retirada1.join();
            ingreso2.join();
            retirada2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n=== OPERACIONES FINALIZADAS ===");
        System.out.println("Saldo final en la cuenta: " + cuenta.getSaldo() + "€");
    }

    public static void main(String[] args) {
        // Instanciamos la clase principal pasándole el saldo inicial (por ejemplo, 500€)
        CajeroAutomatico cajero = new CajeroAutomatico(500);
        cajero.iniciarSimulacion();
    }
}