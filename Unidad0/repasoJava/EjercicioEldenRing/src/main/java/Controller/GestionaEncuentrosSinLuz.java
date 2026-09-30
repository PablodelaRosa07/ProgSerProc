package Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import Exceptions.EldenException;
import Models.Encuentro;
import Models.RegistroEldenRing;
import Repository.SinLuz;

public class GestionaEncuentrosSinLuz {
    public static void main(String[] args) {
        RegistroEldenRing registro = new RegistroEldenRing();

        SinLuz ardyn = new SinLuz("Ardyn");
        SinLuz selene = new SinLuz("Selene");
        SinLuz kael = new SinLuz("Kael");

        registro.agregarSinLuz(ardyn);
        registro.agregarSinLuz(selene);
        registro.agregarSinLuz(kael);

        // Creación de los 7 Encuentros (eldenring.txt)
        Encuentro e1 = new Encuentro("Asalto al Bastión Carmesí", LocalDate.of(2025, 3, 10), 8, List.of("Caballero Carmesí", "Tirano de Ceniza"));
        Encuentro e2 = new Encuentro("Emboscada en el Bosque Umbrío", LocalDate.of(2025, 3, 14), 5, List.of("Lobo Siniestro", "Bandido espectral"));
        Encuentro e3 = new Encuentro("Duelo en la Cripta Helada", LocalDate.of(2025, 3, 18), 7, List.of("Espectro del Hielo", "Mago congelado"));
        Encuentro e4 = new Encuentro("Resistencia en la Torre Abandonada", LocalDate.of(2025, 3, 20), 6, List.of("Arquero maldito", "Guardián de piedra"));
        Encuentro e5 = new Encuentro("Invasión en la Villa Marchita", LocalDate.of(2025, 3, 23), 9, List.of("Gigante marchito", "Portador del Plomo"));
        Encuentro e6 = new Encuentro("Caza en el Lago Sombrío", LocalDate.of(2025, 3, 25), 4, List.of("Serpiente negra", "Sombra anfibia"));
        Encuentro e7 = new Encuentro("Asalto final al Nexo del Caos", LocalDate.of(2025, 3, 30), 10, List.of("Señor del Caos", "Centinela oscuro", "Eco ardiente"));

        try {
            registro.agregaEncuentro(e1, ardyn.getIdentificador());
            registro.agregaEncuentro(e2, ardyn.getIdentificador());
            registro.agregaEncuentro(e3, selene.getIdentificador());
            registro.agregaEncuentro(e4, selene.getIdentificador());
            registro.agregaEncuentro(e5, kael.getIdentificador());
            registro.agregaEncuentro(e6, kael.getIdentificador());
            registro.agregaEncuentro(e7, kael.getIdentificador());
        } catch (EldenException e) {
            System.out.println(e.getMessage());
        }

        for (SinLuz sinLuz : registro.getRegistro()) {
            System.out.println(sinLuz + " -> Encuentros: " + sinLuz.getEncuentros());
        }

        try {
            registro.agregaEncuentro(e1, 999); 
        } catch (EldenException e) {
            System.out.println("Excepción capturada con éxito: " + e.getMessage());
        }

        try {
            System.out.println("ANTES de actualizar:");
            System.out.println(ardyn.getEncuentros());

            Encuentro e1Modificado = new Encuentro("Asalto al Bastión Carmesí", LocalDate.of(2025, 3, 10), 10, List.of("Caballero Carmesí", "Tirano de Ceniza", "Jefe Supremo"));
            registro.agregaEncuentro(e1Modificado, ardyn.getIdentificador());

            System.out.println("DESPUÉS de actualizar:");
            System.out.println(ardyn.getEncuentros());
        } catch (EldenException e) {
            System.err.println(e.getMessage());
        }

        Set<SinLuz> dificiles = registro.getSinLuzConDificultadMayorQue(6);
        for (SinLuz sinLuz : dificiles) {
            System.out.println(sinLuz.getNombre() + " tiene encuentros de dificultad mayor a 6.");
        }
    }
}