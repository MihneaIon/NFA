package tema4;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class AFN {

	private Vector<String> Q;
	private String sigma;
	private String stareInitiala;
	private Vector<String> stariFinale;
	private Vector<Tranzitii> delta;

	public AFN(Vector<String> Q, String sigma, String streInitiala, Vector<String> QFinale,
			Vector<Tranzitii> delta) {
		super();
		this.Q = Q;
		this.sigma = sigma;
		this.stareInitiala = streInitiala;
		this.stariFinale = QFinale;
		this.delta = delta;
	}

	public AFN() {
		Q = new Vector<>();
		sigma = null;
		stariFinale = new Vector<>();
		stareInitiala = null;
		delta = new Vector<>();
	}

	public String getsigma() {
		return sigma;
	}

	public void setsigma(String sigma) {
		this.sigma = sigma;
	}

	public Vector<String> getQ() {
		return Q;
	}

	public void setQ(Vector<String> Q) {
		this.Q = Q;
	}

	public Vector<String> getQFinale() {
		return stariFinale;
	}

	public void setQFinale(Vector<String> QFinale) {
		this.stariFinale = QFinale;
	}

	public String getStreInitiala() {
		return stareInitiala;
	}

	public void setStreInitiala(String streInitiala) {
		this.stareInitiala = streInitiala;
	}

	public Vector<Tranzitii> getDelta() {
		return delta;
	}

	public void setDelta(Vector<Tranzitii> delta) {
		this.delta = delta;
	}

	public void citire() {
		try {
			String linie;
			File myFile = new File("src/AFN.txt");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(myFile);
			linie = scanner.nextLine();
			String[] splitRand = linie.split(",");
			for (int i = 0; i < splitRand.length; i++) {
				Q.add(splitRand[i]);
			}
			sigma = scanner.nextLine();
			stareInitiala = scanner.nextLine();
			linie = scanner.nextLine();
			splitRand = linie.split(",");
			for (int i = 0; i < splitRand.length; i++) {
				stariFinale.add(splitRand[i]);
			}
			myFile = new File("src/tranzitii.txt");
			scanner = new Scanner(myFile);
			while (scanner.hasNextLine()) {
				linie = scanner.nextLine();
				String[] split = linie.split(",");
				Tranzitii aux = new Tranzitii(split[0], split[2], split[1]);
				delta.add(aux);
			}
		} catch (Exception e) {

			System.out.println("\nError: " + e.getMessage());
			Q = null;
			Q = null;
			stareInitiala = null;
			stariFinale = null;
			delta = null;
		}
	}

	public void afisare() {
		System.out.println("AFN-ul :" + sigma + stareInitiala);
		for (String state : Q)
			System.out.println(state);
		for (String state : stariFinale)
			System.out.println(state);
		for (Tranzitii aux : delta)
			System.out.println(aux);
	}

	public Vector<String> selectareStare(Vector<String> aux, String sir) {
		Vector<String> stariposibile = new Vector<>();

		for(String stare: aux) {
			for(Tranzitii tranzitie : delta)
			{
				if(tranzitie.getStanga().equals(stare)&& tranzitie.getSimbol().equals(sir))
					stariposibile.add(tranzitie.getDreapta());
			}
		}
			
		return stariposibile;
	}

	public void start() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInsert the word: ");
		String sir = scanner.nextLine();
		if (sir.matches("lambda") == true) {
			if (stariFinale.contains(stareInitiala))
				System.out.println("\nAutomatul accepta cuvantul lambda.");
			else
				System.out.println("\nAutomatul nu accepta cuvantul lambda.");
		} else {
			Vector<String> stareCurenta = new Vector<>();
			stareCurenta.add(stareInitiala);
			boolean ok = false;
			if (verificCuvantIntrodus(sir)) {
				while (ok == false && sir.matches("") == false) {
					stareCurenta = selectareStare(stareCurenta, sir.charAt(0) + "");
					if (stareCurenta.size() == 0)
						ok = true;
					else
						sir = sir.replaceFirst(sir.charAt(0) + "", "");
				}
				if (sir.equals("") == false)
					System.out.println("\n S-a depistat un blocaj!");
				else if (verificareStareFinala(stareCurenta) == false)
					System.out.println(" Automatul nu accepta cuvantul.");
				else
					System.out.println(" Automatul accepta cuvantul.");
			} else
				System.out.println("The iserted word is wrong ! ");
		}
	}

	public boolean verificCuvantIntrodus(String aux) {
		if (aux.matches("lambda")) {
			return true;
		}
		for (int i = 0; i < aux.length(); i++) {
			if (sigma.indexOf(aux.charAt(i)) == -1)
				return false;
		}
		return true;
	}

	public boolean verificareStareFinala(Vector<String> stari) {
		for (String aux : stari) {
			if (stariFinale.indexOf(aux) != -1)
				return true;
		}
		return false;
	}

	public boolean validAFN() {
		if (Q.indexOf(stareInitiala) == -1)
			return false;
		for (String state : stariFinale) {
			if (Q.indexOf(state) == -1)
				return false;
		}
		if (Q.size() == 0 || stariFinale.size() == 0)
			return false;
		for (Tranzitii transition : delta) {
			if (Q.indexOf(transition.getStanga()) == -1)
				return false;
			if (Q.indexOf(transition.getDreapta()) == -1)
				return false;
			if (sigma.indexOf(transition.getSimbol()) == -1)
				return false;
		}
		return true;
	}
}
