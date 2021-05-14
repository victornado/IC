package Utils;

import java.util.ArrayList;

public class element {
	private Double d1, d2, d3, d4;
	private String clase;

	public element(Double d1, Double d2, Double d3, Double d4, String clase) {
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.clase = clase;
	}

	public element(Double d1, Double d2, Double d3, Double d4) {
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.clase = null;
	}

	public String getClase() {
		return clase;
	}

	public ArrayList<Double> getElement() {
		ArrayList<Double> sol = new ArrayList<Double>();
		sol.add(d1);
		sol.add(d2);
		sol.add(d3);
		sol.add(d4);
		return sol;
	}
}
