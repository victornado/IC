package Modelo;

public class Pair {
	private int i;
	private int j;

	public Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public boolean equals(Pair o) {
		if ((this.i == o.getI()) && (this.j == o.getJ()))
			return true;
		else
			return false;
	}

}
