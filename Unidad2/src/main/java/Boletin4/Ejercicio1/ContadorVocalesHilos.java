package Boletin4.Ejercicio1;

public class ContadorVocalesHilos {

    private int vocalesTotales;

    public ContadorVocalesHilos() {
        this.vocalesTotales = 0;
    }

    public synchronized void sumarVocales(int cantidad) {
        this.vocalesTotales = this.vocalesTotales + cantidad;
    }

    public synchronized int getVocalesTotales() {
        return this.vocalesTotales;
    }
    
}
