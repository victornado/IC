package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Tabla {
	private String[][] tabla;
	private Map<String, Double> veces, positivos;// numero de veces que se repite un string
	private List<HashSet<String>> tipos;// numero de diferentes string que hay en una columna

	private String[] nombres;
	private int cont;

	public Tabla(int i, int j) {
		this.cont = 0;
		tipos = new ArrayList<HashSet<String>>();
		for (int x = 0; x < i; x++) {
			tipos.add(new HashSet<String>());
		}
		tabla = new String[j][i];
		nombres = new String[i];
		veces = new HashMap<String, Double>();
		positivos = new HashMap<String, Double>();
	}

	public void meterNombres(String datos, String separador) {
		String[] aux = datos.split(separador);
		int j = 0;
		for (String string : aux) {
			nombres[j] = string;
			j++;
		}
	}

	public int buscar(int columna, String dato) {
		int cont = 0;
		for (int i = 0; i < tabla.length; i++) {
			if (tabla[i][columna].equals(dato))
				cont++;
		}
		return cont;
	}

	public void meterFila(String datos, String separador, String filtro, int c) {
		String[] aux = datos.split(separador);
		if (filtro == null || aux[c].equals(filtro)) {
			
			int j = 0;
			for (String string : aux) {
				Double dato = veces.get(string);
				tipos.get(j).add(string);
				if (dato != null) {// ya se habia insertado
					veces.remove(string);
					veces.put(string, dato + 1);
				} else {
					veces.put(string, 1.0);
				}

				if (aux[aux.length - 1].equals("si")) {
					Double pos = positivos.get(string);
					if (pos != null) {// ya se habia insertado
						positivos.remove(string);
						positivos.put(string, pos + 1);
					} else {
						positivos.put(string, 1.0);
					}
				}
				tabla[cont][j] = string;
				j++;
			}
			cont++;
		}
	}

	public String[][] getTabla() {
		return tabla;
	}

	public Map<String, Double> getVeces() {
		return veces;
	}

	public List<HashSet<String>> getTipos() {
		return tipos;
	}

	public Map<String, Double> getPositivos() {
		return positivos;
	}

	public String[] getNombres() {
		return nombres;
	}
}
