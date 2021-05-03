package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Tabla {
	private String[][] tabla;
	private Map<String, Integer> veces;
	private List<HashSet<String>> tipos;
	
	private String[] nombres;
	private int cont;

	public Tabla(int i, int j) {
		this.cont = 0;
		tipos=new ArrayList<HashSet<String>>();
		for(int x=0;x<i;x++) {
			tipos.add(new HashSet<String>());
		}
		tabla = new String[j][i];
		nombres = new String[i];
		veces = new HashMap<String, Integer>();
	}

	public void meterNombres(String datos, String separador) {
		String[] aux = datos.split(separador);
		int j = 0;
		for (String string : aux) {
			nombres[j] = string;
			j++;
		}
	}

	public void meterFila(String datos, String separador) {
		String[] aux = datos.split(separador);
		int j = 0;
		for (String string : aux) {
			Integer dato = veces.get(string);
			tipos.get(j).add(string);
			if (dato != null) {// ya se habia insertado
				veces.remove(string);
				veces.put(string, dato + 1);
			} else {
				veces.put(string, 1);
			}
			tabla[cont][j] = string;
			j++;
		}
		cont++;
	}

	public String[][] getTabla() {
		return tabla;
	}

	public Map<String, Integer> getVeces() {
		return veces;
	}
}
