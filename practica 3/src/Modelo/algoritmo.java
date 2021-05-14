package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.element;

public abstract class algoritmo {
	protected List<element> lista;
	protected element ejemplo;
	protected int numElementos;

	public algoritmo(File entrada, File ejemplo) throws FileNotFoundException, IOException {
		lista = new ArrayList<element>();
		numElementos=0;
		cogerEntrada(entrada);
		cogerEjemplo(ejemplo);
	}

	private void cogerEntrada(File entrada) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(entrada.getAbsolutePath()));
		while (br.ready()) {
			numElementos++;
			String fila = br.readLine();
			String[] elementoAux = fila.split(",");
			lista.add(new element(Double.parseDouble(elementoAux[0]), Double.parseDouble(elementoAux[1]),
					Double.parseDouble(elementoAux[2]), Double.parseDouble(elementoAux[3]), elementoAux[4]));
		}
	}

	private void cogerEjemplo(File entrada) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(entrada.getAbsolutePath()));
		String fila = br.readLine();
		String[] elementoAux = fila.split(",");
		ejemplo = new element(Double.parseDouble(elementoAux[0]), Double.parseDouble(elementoAux[1]),
				Double.parseDouble(elementoAux[2]), Double.parseDouble(elementoAux[3]), elementoAux[4]);

	}
	
	public abstract String resolver();
}
