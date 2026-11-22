package Boletin3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionaCalculaMaxHilo {

	public static void main(String[] args) {
		int tamTabla = 10000;
		int numHilos = 10;

		if (args.length >= 1) {
			tamTabla = Integer.parseInt(args[0]);
		}
		if (args.length >= 2) {
			numHilos = Integer.parseInt(args[1]);
		}

		long tComienzo = System.currentTimeMillis();

		GestionaCalculaMaxHilo gestor = new GestionaCalculaMaxHilo();
		int[] tabla = gestor.construyeTablaNotas(tamTabla);

		List<Thread> hilos = new ArrayList<Thread>();
		List<CalculaMaxHilo> objetosHilos = new ArrayList<CalculaMaxHilo>();

		try {
			for (int i = 0; i < numHilos; i++) {
				int[] limites = gestor.calcularLimitesHilo(tamTabla, numHilos, i);
				int inicio = limites[0];
				int fin = limites[1];

				CalculaMaxHilo tarea = new CalculaMaxHilo(tabla, inicio, fin);
				objetosHilos.add(tarea);

				Thread hilo = new Thread(tarea);
				hilos.add(hilo);
			}

			for (Thread h : hilos) {
				h.start();
			}

			for (Thread h : hilos) {
				h.join();
			}

			int maximoGlobal = gestor.obtenerMaximoGlobal(objetosHilos);
			System.out.println("Calificacion maxima global de los ultimos 10 años: " + maximoGlobal);

			long tFin = System.currentTimeMillis();
			long tiempoTotal = tFin - tComienzo;
			System.out.println("El proceso total ha tardado = " + tiempoTotal + " mseg");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	int[] construyeTablaNotas(int tamanyo) {
		int[] tabla = new int[tamanyo];
		Random r = new Random();
		for (int i = 0; i < tabla.length; i++) {
			int aleatorio = r.nextInt(500) + 1;
			tabla[i] = aleatorio;
		}
		return tabla;
	}

	int[] calcularLimitesHilo(int tamTabla, int numHilos, int indiceHilo) {
		int rango = tamTabla / numHilos;
		int inicio = indiceHilo * rango;
		int fin = 0;

		if (indiceHilo == numHilos - 1) {
			fin = tamTabla - 1;
		} else {
			fin = inicio + rango - 1;
		}

		int[] limites = new int[2];
		limites[0] = inicio;
		limites[1] = fin;
		return limites;
	}

	int obtenerMaximoGlobal(List<CalculaMaxHilo> objetosHilos) {
		int maximoGlobal = 0;
		for (int i = 0; i < objetosHilos.size(); i++) {
			CalculaMaxHilo actual = objetosHilos.get(i);
			if (i == 0) {
				maximoGlobal = actual.getMaximoTramo();
			} else if (actual.getMaximoTramo() > maximoGlobal) {
				maximoGlobal = actual.getMaximoTramo();
			}
		}
		return maximoGlobal;
	}
}