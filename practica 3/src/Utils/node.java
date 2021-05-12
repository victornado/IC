package Utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class node {
	private String nombre;
	private Set<node> nodos;

	public node(String n) {
		nombre = n;
		nodos = new HashSet<node>();
	}

	public void insertarNodo(node aux) {
		nodos.add(aux);
	}

	public String getNombre() {
		return nombre;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder(50);
		print(buffer, "", "");
		return buffer.toString();
	}

	private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
		buffer.append(prefix);
		buffer.append(nombre);
		buffer.append('\n');
		for (Iterator<node> it = nodos.iterator(); it.hasNext();) {
			node next = it.next();
			if (it.hasNext()) {
				next.print(buffer, childrenPrefix + "|--- ", childrenPrefix + "|   ");
			} else {
				next.print(buffer, childrenPrefix + "|__ ", childrenPrefix + "    ");
			}
		}
	}

}