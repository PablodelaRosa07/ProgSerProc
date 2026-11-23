package Boletin4.Ejercicio1;

public class HiloContadorVocal extends Thread {
	private ContadorVocalesHilos cont;
    private String texto;
    private char vocalObjetivo;
    private int numVocales;

    public HiloContadorVocal(String texto, char vocalObjetivo, ContadorVocalesHilos c) {
        this.texto = texto;
        this.vocalObjetivo = vocalObjetivo;
        this.cont = c;
    }

    @Override
    public void run() {
        int indice = 0;

        while (indice < texto.length()) {
            char caracterActual = Character.toLowerCase(texto.charAt(indice));
            if (caracterActual == vocalObjetivo) {
            	numVocales = numVocales + 1;
            }
            indice = indice + 1;
        }

        cont.sumarVocales(numVocales);
    }
    
}