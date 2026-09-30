package Models;

import java.util.Set;
import java.util.TreeSet;
import Repository.SinLuz;
import Exceptions.EldenException;

public class RegistroEldenRing {
    private Set<SinLuz> registro;

    public RegistroEldenRing() {
        this.registro = new TreeSet<>();
    }

    public void agregarSinLuz(SinLuz sinLuz) {
        registro.add(sinLuz);
    }

    public SinLuz getSinLuz(int identificador) throws EldenException {
        for (SinLuz sinLuz : registro) {
            if (sinLuz.getIdentificador() == identificador) {
                return sinLuz;
            }
        }
        throw new EldenException("No existe el SinLuz con el id:" + identificador);
    }

    public void agregaEncuentro(Encuentro encuentro, int idSinLuz) throws EldenException {
        SinLuz sinLuz = getSinLuz(idSinLuz);
        sinLuz.agregaOActualizaEncuentro(encuentro);
    }

    public Set<SinLuz> getSinLuzConDificultadMayorQue(int dificultad) {
        Set<SinLuz> resultado = new TreeSet<>();

        for (SinLuz sinLuz : registro) {
            boolean superadificultad = false;

            for (Encuentro encuentro : sinLuz.getEncuentros()) {
                if (encuentro.getDificultad() > dificultad) {
                    superadificultad = true;
                }
            }

            if (superadificultad) {
                resultado.add(sinLuz);
            }
        }

        return resultado;
    }

    public Set<SinLuz> getRegistro() {
        return registro;
    }
}