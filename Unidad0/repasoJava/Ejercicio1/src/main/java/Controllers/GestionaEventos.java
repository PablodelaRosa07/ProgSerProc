package Controllers;

import java.util.Scanner;

import Exceptions.CrafterException;
import Models.Estado;
import Models.Evento;
import Repository.EventosRepository;

public class GestionaEventos {
	
	public static void main(String[] args) throws CrafterException {
		GestionaEventos gestionaEventos = new GestionaEventos();
		
		EventosRepository repo = new EventosRepository(null);
				
		Evento evento1 = new Evento(0, "EventoSevilla", null, 1000, 1500, Estado.PENDIENTE);
		Evento evento2 = new Evento(0, "EventoSevilla", null, 1000, 1500, Estado.PENDIENTE);
		
		repo.getListaEventos().add(evento1);
		repo.getListaEventos().add(evento2);
		
		gestionaEventos.modificaEstado(evento1, Estado.PROGRAMADO);
		gestionaEventos.modificaEstado(evento2, Estado.FINALIZADO);

		System.out.println(repo.getListaEventos());
		}
	
	public void modificaEstado(Evento evento, Estado nuevoEstado) throws CrafterException {
		
		Estado estadoActual = evento.getEstado();
		
		if (estadoActual.equals(Estado.PENDIENTE) && !nuevoEstado.equals(Estado.FINALIZADO)) {
			evento.setEstado(nuevoEstado);
		}
		if (estadoActual.equals(Estado.APLAZADO) && nuevoEstado.equals(Estado.PROGRAMADO)) {
			evento.setEstado(nuevoEstado);
		}
		
		
		else {
			throw new CrafterException("No es posible pasar al estado: " +nuevoEstado);
		}
		
		
	}
	
	
}
