package Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Encuentro {
    private String nombre;
    private LocalDate fecha;
    private int dificultad;
    private List<String> enemigos;

    public Encuentro(String nombre, LocalDate fecha, int dificultad, List<String> enemigos) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.enemigos = enemigos;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public List<String> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(List<String> enemigos) {
		this.enemigos = enemigos;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encuentro other = (Encuentro) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Encuentro [nombre=" + nombre + ", fecha=" + fecha + ", dificultad=" + dificultad + ", enemigos="
				+ enemigos + "]";
	}

    
    
    
}