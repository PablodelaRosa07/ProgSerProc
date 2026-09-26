package Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Models.Conversacion;
import Models.TipoAgente;

public class RepositorioConversaciones {
    private List<Conversacion> conversaciones;

    public RepositorioConversaciones() {
        this.conversaciones = new ArrayList<>();
    }

    public void agregaConversacion(TipoAgente tipo, String pregunta, String respuesta) {
        Conversacion nueva = new Conversacion(respuesta, tipo, pregunta, respuesta, null, 0);
        this.conversaciones.add(nueva);
    }

    public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws NoSuchElementException {
        for (Conversacion c : conversaciones) {
            if (c.getFechaConversacion().equals(fecha) && c.getTipoAgente() == tipo && c.getPregunta().equalsIgnoreCase(pregunta)) {
                return c;
            }
        }
        throw new NoSuchElementException("No existe ninguna conversación para los datos especificados.");
    }

    public boolean contieneConversacion(Conversacion conversacion) {
        return conversaciones.contains(conversacion);
    }

    public void eliminaConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws NoSuchElementException {
        Conversacion aEliminar = getConversacion(fecha, tipo, pregunta);
        conversaciones.remove(aEliminar);
    }

    public void incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta, double valoracion) throws NoSuchElementException {
        Conversacion c = getConversacion(fecha, tipo, pregunta);
        c.incrementarValoracion();
    }

    public List<Conversacion> getTodas() {
        return conversaciones;
    }
}