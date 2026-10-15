package Models;


public class ResultadoComparacion {

    private String nombreArchivo;
    private EstadoComparacion estado;

    public ResultadoComparacion(String nombreArchivo, EstadoComparacion estado) {
        this.nombreArchivo = nombreArchivo;
        this.estado = estado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public EstadoComparacion getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Archivo: " + nombreArchivo + " -> Estado: " + estado;
    }
}
