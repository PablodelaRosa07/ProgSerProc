package Boletin2.Ejercicio2;

public class ResultadoFichero {
    private String nombreFichero;
    private long numCaracteres;

    public ResultadoFichero(String nombreFichero, long numCaracteres) {
        this.nombreFichero = nombreFichero;
        this.numCaracteres = numCaracteres;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public long getNumCaracteres() {
        return numCaracteres;
    }
}