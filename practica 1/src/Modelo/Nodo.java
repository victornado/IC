package Modelo;

public class Nodo implements Comparable<Nodo> {

	// private int i;
//	private int j;
	private int id;
	private Integer coste;
	private boolean descartado;

	public Nodo() {
		this.coste = 10000;
		this.descartado = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coste;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (coste != other.coste)
			return false;
		return true;
	}

//	public int getI() {
//		return i;
//	}
//
//	public void setI(int i) {
//		this.i = i;
//	}
//
//	public int getJ() {
//		return j;
//	}
//
//	public void setJ(int j) {
//		this.j = j;
//	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Integer getCoste() {
		return coste;
	}

	public void setCoste(Integer coste) {
		this.coste = coste;
	}

	public void setObstaculo() {
		this.descartado = true;
	}

	public boolean isObstaculo() {
		return descartado;
	}

	@Override
	public int compareTo(Nodo o) {
		return coste.compareTo(o.getCoste());
	}

}
