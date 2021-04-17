package Modelo;

public class Nodo implements Comparable<Nodo> {

	private int i;
	private int j;
	private int id;
	private double coste;
	private boolean descartado, zonaPeligrosa;
	private Nodo anterior;

	public Nodo() {
		this.coste = 10000;
		this.descartado = false;
		zonaPeligrosa = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + coste);
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
		if (id != other.getId())
			return false;

		return true;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double d) {
		this.coste = d;
	}

	public void setObstaculo() {
		this.descartado = true;
	}

	public boolean isObstaculo() {
		return descartado;
	}

	@Override
	public int compareTo(Nodo o) {
		if (id == o.getId())
			return 0;
		else if (coste == o.getCoste())
			return -1;
		else if (coste < o.getCoste())
			return -1;
		else
			return 1;
	}

	public Nodo getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}

	public boolean isZonaPeligrosa() {
		return zonaPeligrosa;
	}

	public void setZonaPeligrosa(boolean zonaPeligrosa) {
		this.zonaPeligrosa = zonaPeligrosa;
	}

	public String toString() {
		return "[" + i + "," + j + "] ";
	}

	public void reset() {
		this.coste = 10000;
		anterior = null;
	}

}
