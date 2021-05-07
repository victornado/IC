package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ID3 {
	private Tabla tabla;
	private List<Pair> meritos, ganancia;
	private Boolean[] done;
	private node arbol;
	private Queue<node> queue;
	private int columnas;
	
	public ID3(int columnas, int filas) throws FileNotFoundException, IOException {
		tabla = new Tabla(columnas, filas);
		done = new Boolean[columnas - 1];
		this.columnas = columnas;
		queue = new LinkedList<node>();
		cogerDatos(filas, null, 0);
		cogerNombres(columnas);
		meritos = new ArrayList<Pair>(columnas);
		ganancia = new ArrayList<Pair>(columnas);
		calculaMeritos();
		CalculaGanancia();
		

		int columna = elegirNodo();
		String nombres[] = tabla.getNombres();
		arbol = new node(nombres[columna]);
		queue.add(arbol);
		hacerArbol(columna);
		recalcularTabla(columna);
	}

	private void recalcularTabla(int columnaPrincipal) throws FileNotFoundException, IOException {
		Tabla principal = tabla;
		int columna = columnaPrincipal;
		while (!queue.isEmpty()) {
			node actual = queue.poll();
			int cantidad = principal.buscar(columnaPrincipal, actual.getNombre());
			tabla = new Tabla(columnas, cantidad);
			cogerDatos(principal.getTabla().length, actual.getNombre(), columna);
			meritos = new ArrayList<Pair>(columnas);
			ganancia = new ArrayList<Pair>(columnas);
			calculaMeritos();
			CalculaGanancia();
			Queue<node> q2 = new LinkedList<node>();
			q2=queue;
			int c = elegirNodo();
			queue =new LinkedList<node>();
			queue.add(actual);
			hacerArbol(c);
			queue=q2;
		}
	}

	private void hacerArbol(int columna) {

		Iterator<String> it = tabla.getTipos().get(columna).iterator();
		Queue<node> q2 = new LinkedList<node>();
		while (!queue.isEmpty()) {
			node nodo = queue.poll();
			it = tabla.getTipos().get(columna).iterator();

			for (int cont = 0; cont < tabla.getTipos().get(columna).size(); cont++) {
				String s = it.next();

				if (entropia(s) != 0.0) {
					node aux = new node(s);
					q2.add(aux);
					nodo.insertarNodo(aux);
				} else {
					node aux = new node(s);
					nodo.insertarNodo(aux);
					if ( tabla.getPositivos().get(s) == null) {
						node no = new node("no");
						aux.insertarNodo(no);
					} else {
						node si = new node("si");
						aux.insertarNodo(si);
					}
				}
			}
		}

		queue = q2;

	}

	private int elegirNodo() {
		Double min = 123123.0;
		int it = -1;
		for (int i = 0; i < meritos.size(); i++) {
			if (meritos.get(i).getJ() < min && done[i] == null) {
				min = meritos.get(i).getJ();
				it = meritos.get(i).getI();
			}
		}
		if (it != -1)
			done[it] = true;

		return it;
	}

	private void cogerNombres(int columnas) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/AtributosJuego.txt"));
		String fila = br.readLine();
		tabla.meterNombres(fila, ",");
	}

	private void cogerDatos(int filas, String filtro, int c) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/juego.txt"));

		for (int i = 0; i < filas; i++) {
			String fila = br.readLine();
			tabla.meterFila(fila, ",", filtro, c);
		}
	}

	private void calculaMeritos() {
		String[][] tabla1 = tabla.getTabla();
		for (int i = 0; i < columnas-1; i++) {
			if (done[i]==null) {
				// N
				int N = tabla1.length;

				// a
				double[] a = new double[tabla.getTipos().get(i).size()];
				String[] s = new String[tabla.getTipos().get(i).size()];
				tabla.getTipos().get(i).toArray(s);
				for (int j = 0; j < s.length; j++) {
					String string = s[j];
					a[j] = tabla.getVeces().get(string);
				}

				// r
				double[] r = new double[a.length];
				for (int j = 0; j < s.length; j++) {
					r[j] = a[j] / N;
				}

				// merito
				double p, n;
				double sol = 0.0;
				for (int k = 0; k < s.length; k++) {
					sol += entropia(s[k]) * r[k];
				}
				meritos.add(new Pair(i,sol));
			}
		}
	}

	private double log(double num, double base) {
		if (num != 0)
			return (Math.log(num) / Math.log(base));
		else
			return 0.0;
	}

	private double entropia(String s) {
		if(tabla.getPositivos().get(s)!=null) {
		Double p = tabla.getPositivos().get(s) / tabla.getVeces().get(s);
		Double n = (tabla.getVeces().get(s) - tabla.getPositivos().get(s)) / tabla.getVeces().get(s);
		return (-log(p, 2) * (p) - n * log(n, 2));
		}
		else return 0;
	}

	private void CalculaGanancia() {
		Double p = tabla.getPositivos().get("si") / tabla.getTabla().length;
		Double n = (tabla.getTabla().length - tabla.getPositivos().get("si")) / tabla.getTabla().length;

		Double EGeneral = (-log(p, 2) * (p) - n * log(n, 2));
		for (Pair d : meritos) {
			ganancia.add(new Pair(d.getI(),EGeneral - d.getJ()));
		}

	}
}
