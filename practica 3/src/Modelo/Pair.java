package Modelo;

public class Pair {
	private int i;
	private Double j;

	public Pair(int i, Double j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public Double getJ() {
		return j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(Double j) {
		this.j = j;
	}

	public boolean equals(Pair o) {
		if ((this.i == o.getI()) && (this.j == o.getJ()))
			return true;
		else
			return false;
	}

}
