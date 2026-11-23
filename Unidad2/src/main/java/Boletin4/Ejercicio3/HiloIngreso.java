package Boletin4.Ejercicio3;

public class HiloIngreso extends Thread {
    private Cuenta cuenta;
    private int numOperaciones;

    public HiloIngreso(Cuenta cuenta, String nombre, int numOperaciones) {
        super(nombre);
        this.cuenta = cuenta;
        this.numOperaciones = numOperaciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < numOperaciones; i++) {
            int aleatorio = ((int) (Math.random() * 500 + 1));
            cuenta.ingresarDinero(aleatorio, getName());
            try {
                Thread.sleep(300); // Pausa breve para favorecer la alternancia entre hilos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}