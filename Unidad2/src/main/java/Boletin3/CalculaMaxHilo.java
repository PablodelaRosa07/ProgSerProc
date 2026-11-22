package Boletin3;

public class CalculaMaxHilo implements Runnable {
	private int[] tabla;
	private int inicio;
	private int fin;
	private int maximoTramo;

	public CalculaMaxHilo() {
	}

	public CalculaMaxHilo(int[] tabla, int inicio, int fin) {
		this.tabla = tabla;
		this.inicio = inicio;
		this.fin = fin;
		this.maximoTramo = 0;
	}

	private int calculaMax() {
		int maxTramoLocal = tabla[inicio];
		for (int i = inicio; i <= fin; i++) {
			if (tabla[i] > maxTramoLocal) {
				maxTramoLocal = tabla[i];
			}
		}
		System.out.println("Maximo del rango " + inicio + " - " + fin + ": " + maxTramoLocal);
		return maxTramoLocal;
	}

	@Override
	public void run() {
		maximoTramo = calculaMax();
	}

	public int[] getTabla() {
		return tabla;
	}

	public void setTabla(int[] tabla) {
		this.tabla = tabla;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getMaximoTramo() {
		return maximoTramo;
	}

	public void setMaximoTramo(int maximoTramo) {
		this.maximoTramo = maximoTramo;
	}
}