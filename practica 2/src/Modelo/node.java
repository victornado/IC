package Modelo;

import java.util.HashSet;
import java.util.Set;

public class node {
	private String nombre;
	private Set<node>nodos;
	
	public node(String n) {
		nombre=n;
		nodos= new HashSet<node>();
	}
	public void insertarNodo(node aux) {
		nodos.add(aux);
	}
	public String getNombre() {
		return nombre;
	}
}
