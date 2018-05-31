package view;

import java.util.Vector;

public abstract class Eigenschaft {
	
	private String name;
	private int wynik;
	private boolean zusammenhaengend;
	private String swynik;
	



	///////// CONSTRUCTOR /////////////////////
	public Eigenschaft(String name, int wynik) {
		super();
		this.name = name;
		this.wynik = wynik;
	}
	
	public Eigenschaft(String name, int wynik, boolean zusammenhaengend) {
		super();
		this.name = name;
		this.wynik = wynik;
		this.zusammenhaengend = zusammenhaengend;
	}

	public Eigenschaft(String name) {
		super();
		this.name = name;
	}

	public Eigenschaft(String name, String swynik) {
		this.name = name;
		this.swynik = swynik;
	}

	//////// GETTER & SETTER ///////////////////
	public String getSwynik() {
		return swynik;
	}

	public void setSwynik(String swynik) {
		this.swynik = swynik;
	}

	public boolean isZusammenhaengend() {
		return zusammenhaengend;
	}
	
	public int getWynik() {
		return wynik;
	}


	public void setWynik(int wynik) {
		this.wynik = wynik;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	/////////// WYNIK ///////////////////////////
	public abstract int wynik ();
	
	//////// TO STRING /////////////////////

	
	///////// GET DATEN /////////////////////
	public Vector<String> getDaten () {
		Vector<String> daten = new Vector<>();
		daten.add(name);
		if (wynik == -1) {
			///System.out.println("wynik getDaten" + wynik);
			daten.add(String.valueOf(zusammenhaengend));
		}
		//////// wprowadzilem 
		else if (swynik!= null) {
			daten.add(swynik);
		}
		else {
			///System.out.println("wynik getDaten" + wynik);
			daten.add(String.valueOf(wynik));
		}

		return daten;
	}

	public void setWynik(int i, boolean zusammenhaengend) {
		this.wynik = wynik;
		this.zusammenhaengend = zusammenhaengend;
	}

	public void setZusammenhaengend(boolean jein) {
		this.zusammenhaengend = zusammenhaengend;
		
	}

	public void setZusammenhaengend(int x, boolean jein) {
		this.wynik = x;
		this.zusammenhaengend = jein;
	}

}
