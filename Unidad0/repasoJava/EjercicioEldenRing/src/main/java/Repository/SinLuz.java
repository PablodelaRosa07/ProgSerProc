package Repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import Models.Encuentro;

public class SinLuz implements Comparable<SinLuz> {
    private static int contadorId = 1;

    private int identificador;
    private String nombre;
    private Set<Encuentro> encuentros;

    public SinLuz(String nombre) {
        this.identificador = contadorId++;
        this.nombre = nombre;
        this.encuentros = new HashSet<>();
    }

    

    public static int getContadorId() {
		return contadorId;
	}


	public static void setContadorId(int contadorId) {
		SinLuz.contadorId = contadorId;
	}


	public int getIdentificador() {
		return identificador;
	}


	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Set<Encuentro> getEncuentros() {
		return encuentros;
	}


	public void setEncuentros(Set<Encuentro> encuentros) {
		this.encuentros = encuentros;
	}


	public void agregaOActualizaEncuentro(Encuentro e) {
        encuentros.remove(e);
        encuentros.add(e);
    }
	
	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinLuz sinLuz = (SinLuz) o;
        return identificador == sinLuz.identificador;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public int compareTo(SinLuz otro) {
        int comp = this.nombre.compareToIgnoreCase(otro.nombre);
        if (comp == 0) {
            return Integer.compare(this.identificador, otro.identificador);
        }
        return comp;
    }



	@Override
	public String toString() {
		return "SinLuz [identificador=" + identificador + ", nombre=" + nombre + ", encuentros=" + encuentros + "]";
	}

    
}