package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class AlgoritmoA {

	public static final int costediagonal = 15;
	public static final int costerecto = 10;
	private Nodo[][] tablero;
	private Set<Nodo> listaAbierta;
	private HashMap<Integer, Nodo> listaCerrada;
	private Pair ini;
	private List<Pair> metas;
	private List<Nodo> solucion;

	public AlgoritmoA(int N, int M) {
		this.tablero = new Nodo[N][M];
		this.listaAbierta = new TreeSet<Nodo>();
		this.listaCerrada = new HashMap<Integer, Nodo>();
		this.ini = null;
		this.metas = new ArrayList<Pair>();
		solucion = new ArrayList<Nodo>();
		generarIds();
	}

	public boolean crearInicio(int i, int j) {
		if (comprobarNodo(i, j)) {
			ini = new Pair(i, j);
			return true;
		}
		return false;
	}

	public boolean crearFin(int i, int j) {
		if (comprobarNodo(i, j)) {
			metas.add(new Pair(i, j));
			return true;
		}
		return false;
	}

	private boolean ComprobarDisponibilidad(int i, int j) {
		boolean ok = true;
		if (!metas.isEmpty()) {
			for (int cont = 0; i < metas.size() && ok; i++) {
				if (i == metas.get(cont).getI()) {
					if (j == metas.get(cont).getJ()) {
						ok = false;
					}
				}
				cont++;
			}
		}
		if (ini != null) {
			if (i == ini.getI()) {
				if (j == ini.getJ()) {
					ok = false;
				}
			}
		}
		return ok;
	}

	public boolean meterObstaculos(int i, int j) {
		if (comprobarNodo(i, j) && !tablero[i][j].isZonaPeligrosa() && ComprobarDisponibilidad(i, j)) {
			tablero[i][j].setObstaculo();
			listaCerrada.put(tablero[i][j].getId(), tablero[i][j]);
			return true;
		} else {
			return false;
		}
	}

	public boolean meterZonaPeligrosa(int i, int j) {
		if (comprobarNodo(i, j) && !tablero[i][j].isObstaculo() && ComprobarDisponibilidad(i, j)) {
			tablero[i][j].setZonaPeligrosa(true);
			return true;
		} else {
			return false;
		}
	}

	public boolean crearWaypoint(int i, int j) {
		if (comprobarNodo(i, j) && ComprobarDisponibilidad(i, j)) {
			metas.add(new Pair(i, j));
			return true;
		} else {
			return false;
		}
	}

	private void generarIds() {
		int id = 1;
		for (int i = 0; i < tablero.length; ++i) {
			for (int j = 0; j < tablero[i].length; ++j) {
				tablero[i][j] = new Nodo();
				tablero[i][j].setId(id);
				tablero[i][j].setI(i);
				tablero[i][j].setJ(j);
				id++;
			}
		}
	}

	private void resetearListaCerrada() {
		List<Integer> aux = new ArrayList<Integer>();
		for (Entry<Integer, Nodo> entry : listaCerrada.entrySet()) {
			int key = entry.getKey();
			Nodo value = entry.getValue();
			if (!value.isObstaculo()) {
				aux.add(key);
			}
		}
		for (int i = 0; i < aux.size(); i++) {
			listaCerrada.remove(aux.get(i));
		}
	}

	private void meterSolucion() {

		Nodo aux = tablero[metas.get(metas.size() - 1).getI()][metas.get(metas.size() - 1).getJ()];
		while (aux != tablero[ini.getI()][ini.getJ()]) {

			if (aux != tablero[ini.getI()][ini.getJ()]) {
				solucion.add(aux);
			}

			aux = aux.getAnterior();

		}
		for (int i = 0; i < tablero.length; ++i) {
			for (int j = 0; j < tablero[i].length; ++j) {
				if (i == metas.get(metas.size() - 1).getI()) {
					if (j == metas.get(metas.size() - 1).getJ())
						;
					else
						tablero[i][j].reset();
				} else
					tablero[i][j].reset();
			}
		}
	}

	public boolean generarSolucion() {
		boolean ok = true;
		Pair firstIni = ini;
		for (int i = metas.size() - 1; i >= 0 && ok; i--) {
			ok = resuelveCaso();
			if (ok) {
				meterSolucion();
				resetearListaCerrada();
				if (!listaAbierta.isEmpty()) {
					Nodo aux = listaAbierta.iterator().next();
					this.listaAbierta = new TreeSet<Nodo>();
					listaAbierta.add(aux);
				} else {
					this.listaAbierta = new TreeSet<Nodo>();
				}

				ini = metas.get(i);

				if (i != 0)
					metas.remove(i);
			}

		}
		ini = firstIni;
		return ok;
	}

	public boolean resuelveCaso() {
		boolean encontrado = false;
		if (ini.equals(metas.get(metas.size() - 1))) {
			listaCerrada.put(tablero[ini.getI()][ini.getJ()].getId(), tablero[ini.getI()][ini.getJ()]);
			encontrado = true;
		} else {
			if (listaAbierta.isEmpty()) {
				calcularCoste(ini.getI(), ini.getJ(), metas.get(metas.size() - 1).getI(),
						metas.get(metas.size() - 1).getJ(), 0);
				listaAbierta.add(tablero[ini.getI()][ini.getJ()]); // metemos el nodo inicial a la lista abierta para
			}
			// empezar a calcular

			while (!encontrado && !listaAbierta.isEmpty()) {
				Iterator<Nodo> iterator = listaAbierta.iterator();
				if (iterator.hasNext()) {
					Nodo aux = iterator.next();

					if (aux == tablero[metas.get(metas.size() - 1).getI()][metas.get(metas.size() - 1).getJ()])
						encontrado = true;
					else {
						iterator.remove();
						listaCerrada.put(aux.getId(), aux);
						calcularAdyacentes(aux);
					}
				}

			}
		}
		return encontrado;
	}

	public List<Nodo> sol() {
		return solucion;
	}

	private boolean comprobarNodo(int i, int j) {
		if (i < tablero.length && i >= 0) {
			if (j < tablero[i].length && j >= 0)
				return true;
		}
		return false;
	}

	private void calcularCoste(int i, int j, int n, int m, double d) {
		double f, g, h;
		// No estamos en diagonal
		if (i - n == 0 || j - m == 0) {
			g = costerecto;
		} else {
			g = costediagonal;
		}
		h = Math.abs(i - metas.get(metas.size() - 1).getI()) + Math.abs(j - metas.get(metas.size() - 1).getJ());
		f = g + h + d;
		if (tablero[i][j].isZonaPeligrosa()) {
			f = f + 0.2 * f;
		}
		if (f < tablero[i][j].getCoste()) {
			tablero[i][j].setAnterior(tablero[n][m]);
			tablero[i][j].setCoste(f);

		}
	}

	private void calcularAdyacentes(Nodo nodo) {
		int n = nodo.getI();
		int m = nodo.getJ();
		for (int i = n - 1; i <= n + 1; i++) {
			for (int j = m - 1; j <= m + 1; j++) {
				if (comprobarNodo(i, j) && !tablero[i][j].isObstaculo()
						&& !listaCerrada.containsKey(tablero[i][j].getId())) {

					calcularCoste(i, j, n, m, nodo.getCoste());
					listaAbierta.add(tablero[i][j]);
				}
			}
		}
	}

}
