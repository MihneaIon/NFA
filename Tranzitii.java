package tema4;

public class Tranzitii {
	private String stanga;
	private String dreapta;
	private String simbol;

	public String getStanga() {
		return stanga;
	}

	public void setStanga(String stanga) {
		this.stanga = stanga;
	}

	public String getDreapta() {
		return dreapta;
	}

	public void setDreapta(String dreapta) {
		this.dreapta = dreapta;
	}

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}

	@Override
	public String toString() {

		return "(" + stanga + ", " + simbol + ", " + dreapta + ")";
	}

	public Tranzitii(String stanga, String dreapta, String simbol) {
		super();
		this.stanga = stanga;
		this.dreapta = dreapta;
		this.simbol = simbol;
	}
	
	public Tranzitii() {
		super();
		this.stanga = null;
		this.dreapta = null;
		this.simbol = null;
	}
}
