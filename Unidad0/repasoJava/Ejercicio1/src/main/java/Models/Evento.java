package Models;

import java.time.LocalDate;
import java.util.Objects;

import Exceptions.CrafterException;

public abstract class Evento {
	
	private int id;
	private String nombre;
	private LocalDate fechaEvento;
	private int numEntradasVendidas;
	private int capacidadMaxAsistentes;
	private Estado estado;
	
	public Evento(int id, String nombre, LocalDate fechaEvento, int numEntradasVendidas, int capacidadMaxAsistentes, Estado estado) throws CrafterException {super();
		this.id = id;
		if (numEntradasVendidas <= capacidadMaxAsistentes && capacidadMaxAsistentes > 0) {
			this.nombre = nombre;
			this.fechaEvento = fechaEvento;
			this.numEntradasVendidas = numEntradasVendidas;
			this.capacidadMaxAsistentes = capacidadMaxAsistentes;
			this.estado = estado;
		}
		
		else {
			throw new CrafterException("No es posible construir un evento con esos datos");
		}	
		
	}
	
	
	
	public double getPorcentajeOcupacion() {
	    return (this.numEntradasVendidas * 100.0) / this.capacidadMaxAsistentes;
	}

	public double getPorcentajeOcupacion(int numeroEntradas) throws CrafterException {
	    int totalEntradas = this.numEntradasVendidas + numeroEntradas;

	    if (totalEntradas > this.capacidadMaxAsistentes || numeroEntradas < 0) {
	        throw new CrafterException("El número de entradas supera la capacidad máxima del evento.");
	    }

	    return (totalEntradas * 100.0) / this.capacidadMaxAsistentes;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDate fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public int getNumEntradasVendidas() {
		return numEntradasVendidas;
	}

	public void setNumEntradasVendidas(int numEntradasVendidas) {
		this.numEntradasVendidas = numEntradasVendidas;
	}

	public int getCapacidadMaxAsistentes() {
		return capacidadMaxAsistentes;
	}

	public void setCapacidadMaxAsistentes(int capacidadMaxAsistentes) {
		this.capacidadMaxAsistentes = capacidadMaxAsistentes;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	public abstract double calcularCosteBase();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", fechaEvento=" + fechaEvento + ", numEntradasVendidas="
				+ numEntradasVendidas + ", capacidadMaxAsistentes=" + capacidadMaxAsistentes + ", estado=" + estado
				+ "]";
	}
	
	
	
	


	
	
	
	
	
	
}
