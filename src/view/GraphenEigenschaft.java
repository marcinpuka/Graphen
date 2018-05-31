package view;

import java.util.Vector;

public class GraphenEigenschaft extends Eigenschaft{
	
	private boolean jein;
	
	///////// CONSTRUCTORS /////////////////////
	public GraphenEigenschaft(String name, int wynik) {
		super(name, wynik);
		// TODO Auto-generated constructor stub
	}
	public GraphenEigenschaft(String name, boolean jein) {
		super(name);
		this.jein = jein;
	}
	
	public GraphenEigenschaft(String name, int wynik, boolean jein) {
		super(name, wynik);
		this.jein = jein;
	}
	
	public GraphenEigenschaft(String string) {
		super(string);
	}
	
	public GraphenEigenschaft(String string, String swynik) {
		super(string, swynik);
	}

	//////// GETTER & SETTER ///////////////////
	public boolean isJein() {
		return jein;
	}
	public void setJein(boolean jein) {
		this.jein = jein;
	}
	
	///////// WYNIK ////////////////////////////
	@Override
	public int wynik() {
		// TODO Auto-generated method stub
		return 0;
	}

	////////// GET DATEN /////////////////////////////////////////////
	public Vector<String> getDaten () {
		Vector<String> daten = super.getDaten();

		return daten;
	}
	
	
}
