package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import view.Eigenschaft;
import view.GraphenEigenschaft;
import view.MainFrame;

public class Matrix {

	private ArrayList<Eigenschaft> eigenschaften = new ArrayList<Eigenschaft>();

	private MainFrame mainFrame;
	private int numberOfKnoten;
	private int[][] adjazenzMatrix;
	private int[][] wegMatrix;
	private int[][] weg;
	private int[][] weg1;
	private int[][] weg2;
	private int[][] wegBloeckeMatrix;
	
	private int[][] tempWegMatrix;
	private int[][] distanzMatrix;
	private int[][] adjazenzMatrix3;
	private int[][] adjazenzMatrix4;
	private int[][] adjazenzMatrix5;
	private int[][] adjazenzMatrix6;
	private int[][] adjazenzMatrix7;
	private int[][] adjazenzMatrix8;
	private int[][] adjazenzMatrix9;
	private int[][] adjazenzMatrix10;
	private int[][] temp;
	private int anzahlDerKomponenten;
	private List<Integer> isolierteKnoten;
	private List<Integer> artikulationen;
	boolean zusammenhaengend;
	
	private List<String> brueckenList;
	
	private List<String> bloeckeEineOderKeineArtikulation;
	
	private boolean wegFertig;
	private boolean eulersch;

	//////////// CONSTRUCTOR ////////////////////////////////////
	public Matrix(MainFrame mainFrame, int numberOfKnoten) {

		this.mainFrame = mainFrame;
		this.numberOfKnoten = numberOfKnoten;

		createAdjazenzMatrix();
		createWegMatrix();
		createDistanzMatrix();

		Eigenschaft e1 = new GraphenEigenschaft("Anzahl der Knoten: ", 0);
		einfugen(e1);
		Eigenschaft e2 = new GraphenEigenschaft("Anzahl der Kanten: ", 0);
		einfugen(e2);
		Eigenschaft e3 = new GraphenEigenschaft("Zusammenhängend: ", false);
		einfugen(e3);
		Eigenschaft e4 = new GraphenEigenschaft("Radius: ");
		einfugen(e4);
		Eigenschaft e5 = new GraphenEigenschaft("Durchmesser: ");
		einfugen(e5);
		Eigenschaft e6 = new GraphenEigenschaft("Zentrum: ", "");
		einfugen(e6);
		Eigenschaft e7 = new GraphenEigenschaft("Anzahl der Komponenten: ", numberOfKnoten);
		einfugen(e7);
		Eigenschaft e8 = new GraphenEigenschaft("Komponenten: ");
		einfugen(e8);
		Eigenschaft e9 = new GraphenEigenschaft("Artikulationen: ");
		einfugen(e9);
		Eigenschaft e10 = new GraphenEigenschaft("Brücken: ");
		einfugen(e10);
		Eigenschaft e11 = new GraphenEigenschaft("Anzahl der Blöcke: ", numberOfKnoten);
		einfugen(e11);
		Eigenschaft e12 = new GraphenEigenschaft("Blöcke: ");
		einfugen(e12);
		Eigenschaft e13 = new GraphenEigenschaft("Eulersch: ", false);
		einfugen(e13);
		
		
		isolierteKnoten = new ArrayList<Integer>();
		artikulationen = new ArrayList<Integer>();
		brueckenList = new ArrayList<String>();
		bloeckeEineOderKeineArtikulation = new ArrayList<String>();
		
		
		////wegFertig = false;

		///////////// AUSLAGERN !!!!! //////////////////////////////////////
		weg2 = new int[numberOfKnoten][numberOfKnoten];

		int z;
		int s;
		/// System.out.println("MAINFRAME - buttons[z][s] ausgeben(): ");
		z = 0;
		while (z < numberOfKnoten) {
			s = 0;
			while (s < numberOfKnoten) {
				if (z == s) {
					weg2[z][s] = 1;
				} else {
					weg2[z][s] = 0;
				}
				s++;
			}
			System.out.println();
			z++;
		}
		matrixAusgeben3(weg2, "weg2 Construktor");

	}

	////////// 1 CREATE ADJAZENZ MATRIX ////////////////////////////////////
	public void createAdjazenzMatrix() {

		adjazenzMatrix = new int[numberOfKnoten][numberOfKnoten];
	}

	////////// 2 POPULATE ADJAZENZ MATRIX //////////////////////////
	public void populateAdjazenzMatrix(int z, int s, int wert) {

		adjazenzMatrix[z - 1][s - 1] = wert;
	}

	////////// 3 CREATE WEG MATRIX //////////////////////////////////////
	public void createWegMatrix() {

		wegMatrix = new int[numberOfKnoten][numberOfKnoten];
	}

	////////// 4 POPULATE WEG MATRIX ///////////////////////////////////
	public void populateWegMatrix(int z, int s, int wert) {

		/// Arrays.fill(wegMatrix, null);
		if (z == s) {
			wegMatrix[z - 1][s - 1] = 1;
		} else {
			wegMatrix[z - 1][s - 1] = wert;
		}
	}

	////////// 5 CREATE DISTANZ MATRIX //////////////////////////////////
	public void createDistanzMatrix() {

		distanzMatrix = new int[numberOfKnoten][numberOfKnoten];
	}

	/////////// 6 POPULATE DISTANZ MATRIX ///////////////////////////////
	public void populateDistanzMatrix(int z, int s, int wert) {
		if (z == s) {
			distanzMatrix[z - 1][s - 1] = 0;
		} else {
			distanzMatrix[z - 1][s - 1] = wert;
		}
	}

	////////// 7 MATRIX AUSGEBEN ////////////////////////////////////////
	public void matrixAusgeben() {
		int z;
		int s;
		System.out.println("MATRIX - matrixAusgeben() - adjazenzMatrix : ");
		z = 0;
		while (z < numberOfKnoten) {
			s = 0;
			while (s < numberOfKnoten) {
				System.out.print(String.valueOf(adjazenzMatrix[z][s]) + "\t");
				s++;
			}
			System.out.println();
			z++;
		}
		int zeile;
		int spalte;
		System.out.println("MATRIX - matrixAusgeben() - wegMatrix : ");
		zeile = 0;
		while (zeile < numberOfKnoten) {
			spalte = 0;
			while (spalte < numberOfKnoten) {
				System.out.print(String.valueOf(wegMatrix[zeile][spalte]) + "\t");
				spalte++;
			}
			System.out.println();
			zeile++;
		}
		int ze;
		int spa;
		System.out.println("MATRIX - matrixAusgeben() - distanzMatrix : ");
		ze = 0;
		while (ze < numberOfKnoten) {
			spa = 0;
			while (spa < numberOfKnoten) {
				System.out.print(String.valueOf(distanzMatrix[ze][spa]) + "\t");
				spa++;
			}
			System.out.println();
			ze++;
		}

		/// System.out.println("Knoten: " + String.valueOf(numberOfKnoten));
	}

	////////// 8 MATRIX AUSGEBEN2 ////////////////////////////////////////
	public void matrixAusgeben2(int[][] m, int potega) {
		int z;
		int s;
		System.out.println("Potenzmatrix^" + potega + " :");
		z = 0;
		while (z < m.length) {
			s = 0;
			while (s < m[0].length) {
				System.out.print(String.valueOf(m[z][s]) + "\t");
				s++;
			}
			System.out.println();
			z++;
		}
	}

	///////// 9 MATRIX AUSGEBEN3 ////////////////////////////////////////////////
	public void matrixAusgeben3(int[][] m, String info) {
		int z;
		int s;
		System.out.println("Matrix^" + info + " :");
		z = 0;
		while (z < m.length) {
			s = 0;
			while (s < m[0].length) {
				System.out.print(String.valueOf(m[z][s]) + "\t");
				s++;
			}
			System.out.println();
			z++;
		}
	}

	///////// 10 MULTIPLYADJAZENZ ////////////////////////////////
	public void multiplyAdjazenz(int numberOfKnoten, boolean updateGui) {
		
		System.out.println(" ==============================================================");
		System.out.println(" ------------------POCZATEK MNOZENIA---------------------------");
		int[][] temp = new int[numberOfKnoten][numberOfKnoten];
		int[][] mResult = null;
		int powtorzenia = numberOfKnoten - 1;
		int potega = 2;
		////////// TEST PERFORMANCE ////////////////////77
		
		if (powtorzenia < 2) {

			matrixAusgeben();
			compareAdjAndWegMatrices(adjazenzMatrix, updateGui);
			comparePotenzAndDistanzMatrices(adjazenzMatrix, 1);
			matrixAusgeben();
		} else {
			///wegFertig = wegMatrixFertig();
			while (potega <= powtorzenia) {
				
				if (potega == 2) {

					int m1ColLength = adjazenzMatrix[0].length; // m1 columns
																// length
					int m2RowLength = adjazenzMatrix.length; // m2 rows length
					int mRRowLength = adjazenzMatrix.length; // m result rows
																// length
					int mRColLength = adjazenzMatrix[0].length; // m result
																// columns
																// length

					mResult = new int[mRRowLength][mRColLength];

					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult[i][j] += adjazenzMatrix[i][k] * adjazenzMatrix[k][j];
								temp[i][j] += adjazenzMatrix[i][k] * adjazenzMatrix[k][j];
							}
						}
					}
					System.out.println("Mnozenie matrix - Potega:" + potega);
					matrixAusgeben2(mResult, potega);
					potega++;
				} else {
					int m1ColLength = temp[0].length; // m1 columns length
					int m2RowLength = adjazenzMatrix.length; // m2 rows length

					int mRRowLength = temp.length; // m result rows length
					int mRColLength = adjazenzMatrix[0].length; // m result
																// columns
																// length

					mResult = new int[mRRowLength][mRColLength];
					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult[i][j] += temp[i][k] * adjazenzMatrix[k][j];
							}
						}
					}
					temp = mResult;
					System.out.println("Mnozenie matrix - Potega:" + potega);
					matrixAusgeben2(mResult, potega);
					potega++;
				}
				if (updateGui == true) {
					
					compareAdjAndWegMatrices(temp, updateGui);
					
					comparePotenzAndDistanzMatrices(temp, potega - 1);
				}

				///wegFertig = wegMatrixFertig();
				System.out.println("WEG FERTIG: " + wegMatrixFertig() );
			}

			
			temp = new int[0][0];
		}
		System.out.println(" =============== KONIEC MNOZENIA ========================");
	}
		/// findeArtikulationen();
	
	
	///////// 11 WEG MATRIX FERTIG /////////////////////////////
	public boolean wegMatrixFertig () {
		
		boolean fertig = true;
		int z;
		int s;
		
		z = 0;
		while (z < wegMatrix.length) {
			s = 0;
			while (s < wegMatrix[0].length) {
				if (wegMatrix[z][s] == 0 ) {
					fertig = false;
				}
				s++;
			}
			System.out.println();
			z++;
		}
		return fertig;
	}	

	////////// 12 READ WEG MATRIX //////////////////////////////
	public int readWegMatrix(int zeile, int spalte) {
		int z;
		int s;
		int wert = -999;

		z = 0;
		while (z <= wegMatrix.length) {
			s = 0;
			while (s <= wegMatrix[0].length) {
				if (zeile == z && spalte == s) {
					wert = wegMatrix[zeile][spalte];
					System.out.println("Reading weg matrix");
				}
			}
			System.out.println();
			z++;
		}
		return wert;
	}

	////////// 13 READ ADJAZENZ MATRIX 2 /////////////////////////////
	public void readAdjazenzMatrix2() {

		int z;
		int s;
		int anzahlDerKanten = 0;
		zusammenhaengend = true;
		int min;
		int minZeile;
		int max;
		int maxZeile;
		int[] zentrum;
		String centrum;

		zentrum = new int[15];
		/// test
		centrum = "";
		min = 999;
		minZeile = -1;
		max = 0;
		maxZeile = -1;
		/// System.out.println("MATRIX - readAdjazenzMatrix2() - adjazenzMatrix
		/// : ");
		z = 0;
		while (z < numberOfKnoten) {
			s = 0;
			int temporaMax = 0;
			int temporaMaxZeile = -1;
			int temporaMin = 999;
			int temporaMinZeile = -1;

	
			while (s < numberOfKnoten) {
				/// System.out.println("Petla: distanz");
				/// System.out.print(String.valueOf(distanzMatrix[z][s])+ "\t");

				/// ---Anzahl der Kanten ---///
				if (adjazenzMatrix[z][s] == 1) {
					anzahlDerKanten++;
				
				}
				/// ---Zusammenhängend---///
				if (z != s && distanzMatrix[z][s] == 0) {
					zusammenhaengend = false;
				}
				/// ---Zentrum---///
				if (distanzMatrix[z][s] > temporaMax) {
					temporaMax = distanzMatrix[z][s];
				}

				s++;
			}

			
			

			if (temporaMax > max) {
				max = temporaMax;
				maxZeile = z;
				/// centrum= "*" + mainFrame.getLiterka(z);
			}
			if (temporaMax == max) {
				max = temporaMax;
				maxZeile = z;
				/// centrum+= " **" + mainFrame.getLiterka(z);
			}

			if (temporaMax == min) {
				min = temporaMax;
				centrum += "," + mainFrame.getLiterka(z);
			}
			if (temporaMax < min) {
				min = temporaMax;
				centrum = mainFrame.getLiterka(z);
			}
			/// System.out.println("Max: "+ max + "Centrum: " +
			/// centrum.toString());

			System.out.println();
			z++;
		}
		/// System.out.println("Anzahl der Kanten: " + anzahlDerKanten/2);
		eigenschaften.get(1).setWynik(anzahlDerKanten / 2);
		//// System.out.println("Zusammenhängend: " + zusammenhaengend + " Max:"
		//// + max + "max Zeile:" + maxZeile + " min:"+min + " minZeile:" +
		//// minZeile +
		/// "Centrum: " + centrum.toString()) ;
		updateZusammenhaengend(-1, zusammenhaengend);
		if (zusammenhaengend == true) {
			updateEigenschaft(3, min);
			updateEigenschaft(4, max);
			updateEigenschaft2(5, centrum.toString());
			/// eulersch();
		} else {
			updateEigenschaft(3, 0);
			updateEigenschaft(4, 0);
			updateEigenschaft2(5, "");
			
			////// update Eulersch
			updateZusammenhaengend2(-1, false);
			/// test
			/// centrum = null;
		}
		///System.out.println("EULERSCH: " + eulersch);
		///eulersch = false;
		eulersch();

	}
	
	/////////// 14 EULERSCH //////////////////////////////////////
	public void eulersch () {	
		System.out.println("  .................................... EULERSCH IN PROGRESS");
			int z;
			int s;
		
			eulersch = false;
			
		if (zusammenhaengend == true) {
			System.out.println("EULERSCH: " + zusammenhaengend);
			eulersch = true;
			int knoteUngeradeGrad = 0;
			z=0;
			while (z < adjazenzMatrix.length && eulersch==true) {
				s = 0;
				int tempGrad = 0;
				int anzEins = 0;
				
				while (s < adjazenzMatrix[0].length) {
					
					if (adjazenzMatrix[z][s]==1) {
					anzEins++;
 					tempGrad++;
					}
					
					s++;
				}
				////System.out.println("KNOTE: " + z + "GRAD" + tempGrad );
				if (tempGrad%2!=0) {
					System.out.println("WYNIK MODULO: Z:" + z + "MODULO: "+ tempGrad%2);
					knoteUngeradeGrad++;
					eulersch = false;
				}
				////System.out.println("ZEILE: " + z + " ANZAHL KNOTEN UNGERADE GRAD" + knoteUngeradeGrad);
				
				System.out.println();
				z++;
			}
			
			updateZusammenhaengend2(-1, eulersch);
		}
		
		System.out.println("EULERSCH ZUSAMMENHAENGEND: " + zusammenhaengend);
			
		/**if (knoteUngeradeGrad!= 0) {
			eulersch = false;
		}
		else if (knoteUngeradeGrad==0){
			eulersch = true;
			updateZusammenhaengend2(-1, true);
		}}
		else {
			updateZusammenhaengend2(-1, false);
		}
		*/
		
	}

	////////// 15 FINDE ARTIKULATIONEN /////////////////////////////////
	public void findeArtikulationen() {

		artikulationen.clear();
		System.out.println("findeArtikulationen() START!!!");

		int[][] aMatrix = new int[numberOfKnoten][numberOfKnoten];

		int z;
		int s;
		int anzahlDerBloecke;

		///List<Integer> artikulationen = new ArrayList<Integer>();
		
		z = 1;
		String artykulacja = "";
		weg = new int[numberOfKnoten][numberOfKnoten];
		/// weg = weg2;
		for (int i = 0; i < numberOfKnoten; i++)
			for (int j = 0; j < numberOfKnoten; j++) {
				if (i == j) {
					weg[i][j] = 1;
				} else {
					weg[i][j] = 0;
				}
			}

		matrixAusgeben3(weg, "FINDE ART POCZATEK: WEGMATRIX");

		anzahlDerBloecke = anzahlDerKomponenten;
		////// ??? <=
		while (z <= aMatrix.length) {

			for (int i = 0; i < adjazenzMatrix.length; i++)
				for (int j = 0; j < adjazenzMatrix[i].length; j++)
					aMatrix[i][j] = adjazenzMatrix[i][j];
			/// if (i!=j) {
			/// weg[i][j] = adjazenzMatrix[i][j];
			/// } else {
			/// weg[i][j] = 1;
			/// }
			/// }

			///matrixAusgeben3(weg, "WEGMATRIX PO SKOPIOWANIU Z ADJACENZ: ");
			///////// ////////////////////////////////

			s = 1;
			int grad = 0;
			while (s <= aMatrix[0].length) {

				if (aMatrix[z - 1][s - 1] == 1) {
					grad++;
				}

				/// matrixAusgeben3 (aMatrix, " !!!!!!!!!!!!! ARTYKULACJE ");

				if (aMatrix[z - 1][s - 1] == 1) {

					aMatrix[z - 1][s - 1] = 0;
					aMatrix[s - 1][z - 1] = 0;
				}

				s++;
			}
			////////// MOZE TUTAJ ???////////////////////
			for (int i = 0; i < aMatrix.length; i++)
				for (int j = 0; j < aMatrix[i].length; j++) {
					if (i != j) {
						weg[i][j] = aMatrix[i][j];
					} else {
						weg[i][j] = 1;
					}
				}

			///matrixAusgeben3(aMatrix, "MODYFIED: " + (z - 1));

			//// matrixAusgeben3 (aMatrix, "SZUKANIE ARTYKULACJI");
			/// aMatrix = adjazenzMatrix;

			/// matrixAusgeben3(adjazenzMatrix, " A = A");
			/// matrixAusgeben3( aMatrix, " artykulacje");
			multiplyAdjazenz2(aMatrix);

			if (grad >= 2) {

				if (anzahlDerKomponenten2(weg) > anzahlDerKomponenten + 1) {
					System.out.println("ARTYKULACJA: " + (z - 1) + "grad: " + grad + " Anzahl Der Komponentnen: "
							+ anzahlDerKomponenten2(weg) + "Anz Komp des G: " + anzahlDerKomponenten);
					
					

					artykulacja = artykulacja + mainFrame.getLiterka(z - 1) + ",";
					artikulationen.add(z-1);
					anzahlDerBloecke = anzahlDerBloecke -1 + (anzahlDerKomponenten2(weg) - anzahlDerKomponenten);
					
					
					
				}
			}

			/// int tempAnzKomp = anzahlDerKomponenten2 (tempWegMatrix);
			/// if (tempAnzKomp > anzahlDerKomponenten) {
			/// artikulationen.add(z-1);

			/// }
			/// aMatrix = new int [0][0];
			/// matrixAusgeben3 (aMatrix, "aMatrix = ADJAZENZ");
			System.out.println();
			z++;
			/// eigenschaften.get(8).setSwynik(artykulacja.toString());
		}

		/// matrixAusgeben3(adjazenzMatrix, "ADJA; ");
		System.out.println("Artykulacje: " + artykulacja.toString());
		updateEigenschaft2(8, artykulacja);
		updateEigenschaft(10, anzahlDerBloecke);

		/// eigenschaften.get(8).setSwynik(artykulacja.toString());
		artykulacja = "";
		weg = new int[0][0];
		bloeckeAusgeben();
	}

	////////// 16 FINDE BRUECKEN ///////////////////////////////////////////////////
	public void findeBruecken() {
		brueckenList.clear();


		System.out.println("findeBruecken() START!!!");

		int[][] bMatrix = new int[numberOfKnoten][numberOfKnoten];

		int z;
		int s;

		//// List<Integer> artikulationen = new ArrayList<Integer>();

		z = 0;
		String bruecken = "";
		weg1 = new int[numberOfKnoten][numberOfKnoten];
		/// weg = weg2;
		for (int i = 0; i < numberOfKnoten; i++)
			for (int j = 0; j < numberOfKnoten; j++) {
				if (i == j) {
					weg1[i][j] = 1;
				} else {
					weg1[i][j] = 0;
				}
			}

		matrixAusgeben3(weg1, "FINDE BRÜCKEN POCZATEK: WEGMATRIX");

		while (z < bMatrix.length - 1) {

			/// matrixAusgeben3(weg1, "WEGMATRIX PO SKOPIOWANIU Z ADJACENZ: ");
			///////// ////////////////////////////////

			s = 0;

			while (s < bMatrix[0].length - 1) {

				/// matrixAusgeben3 (aMatrix, " !!!!!!!!!!!!! ARTYKULACJE ");
				if (s >= z) {

					for (int i = 0; i < adjazenzMatrix.length; i++)
						for (int j = 0; j < adjazenzMatrix[i].length; j++)
							bMatrix[i][j] = adjazenzMatrix[i][j];

					matrixAusgeben3(bMatrix, "FINDE BRUECKEN PETLA bMATRIX = ADJAZENZ");

					if (bMatrix[z][s + 1] == 1) {

						bMatrix[z][s + 1] = 0;
						bMatrix[s + 1][z] = 0;

						matrixAusgeben3(bMatrix, "FINDE BRUECEN PETLA bMATRIX"+ " ZEILE: " + z + " SPALETE: " + (s+1) + " <<----------------------------------");

						for (int i = 0; i < bMatrix.length; i++)
							for (int j = 0; j < bMatrix[i].length; j++) {
								if (i != j) {
									weg1[i][j] = bMatrix[i][j];
								} else {
									weg1[i][j] = 1;
								}
							}

						matrixAusgeben3(weg1, "FINDE BRUCEKN PETLA WEG1!");
						multiplyAdjazenz3(bMatrix);

						if (anzahlDerKomponenten2(weg1) > anzahlDerKomponenten) {
							System.out.println("BRUCKEN: " + (z) + (s) + " Anzahl Der Komponentnen: "
									+ anzahlDerKomponenten2(weg1) + "Anz Komp des G: " + anzahlDerKomponenten);
							bruecken = bruecken + mainFrame.getLiterka(z) + mainFrame.getLiterka(s+1) + ",";
							StringBuilder sb = new StringBuilder ();
							sb.append(z);
							sb.append(s+1);
							brueckenList.add(sb.toString());
						}

					}

				}

				s++;
			}
			////////// ////////////////////

			/// matrixAusgeben3(bMatrix, "MODYFIED: " +(z-1) );

			/// if (grad>=2) {

			//////// DO KOREKTY

			System.out.println();
			z++;

		}

		System.out.println("Bruecken: " + bruecken.toString());
		/// updateEigenschaft2 (8, artykulacja);
		updateEigenschaft2(9, bruecken);
		/// DO KORREKTY
		bruecken = "";
		weg1 = new int[0][0];
		for (int x=0; x<brueckenList.size(); x++)
		    System.out.println("Bruecken: " +brueckenList.get(x));
		
		
	}
	
	///////// 17 BLÖCKE AUSGEBEN ////////////////////////////////////////
	public void bloeckeAusgeben () {
		bloeckeEineOderKeineArtikulation.clear();
		
		int k;
		int[][] aBloeckeMatrix = new int [numberOfKnoten][numberOfKnoten];
		wegBloeckeMatrix = new int [numberOfKnoten][numberOfKnoten];
		
		for (int i = 0; i < numberOfKnoten; i++)
			for (int j = 0; j < numberOfKnoten; j++) {
				if (i == j) {
					wegBloeckeMatrix[i][j] = 1;
				} else {
					wegBloeckeMatrix[i][j] = 0;
				}
			}
		
		for (int i = 0; i < adjazenzMatrix.length; i++)
			for (int j = 0; j < adjazenzMatrix[i].length; j++)
				aBloeckeMatrix[i][j] = adjazenzMatrix[i][j];
		
		for (int i = 0; i < aBloeckeMatrix.length; i++)
			for (int j = 0; j < aBloeckeMatrix[i].length; j++) {
				if (i != j) {
					wegBloeckeMatrix[i][j] = aBloeckeMatrix[i][j];
				} else {
				wegBloeckeMatrix[i][j] = 1;
				}
			}
		
		
		k = 0;
		
		
		/////////// USUONIECIE MOSTOW ///////////////////////////////////////////////////////
		for (int x=0; x<brueckenList.size(); x++) {
		    ///z = brueckenList.get(x).charAt(0);
			///s = brueckenList.get(x).charAt(1);
			String w = brueckenList.get(x);
			System.out.println("Z: " +brueckenList.get(x).charAt(0) + "W: " + w + " S: " + brueckenList.get(x).charAt(1));
			
			String zeile = w.substring(0, 1);
			String spalte = w.substring(1,2);
			
			int firstDigitZeile = Integer.parseInt(zeile);
			System.out.println("W: " + w);
			System.out.println(firstDigitZeile);
			int secondDigitSpalte = Integer.parseInt(spalte);
			System.out.println(secondDigitSpalte);
			
			for (int i = 0; i < aBloeckeMatrix.length; i++)
				for (int j = 0; j < aBloeckeMatrix[i].length; j++)
				{
					aBloeckeMatrix[firstDigitZeile][secondDigitSpalte] = 0;
					aBloeckeMatrix [secondDigitSpalte][firstDigitZeile] = 0;
				}
			
		}
		////////////// SPRAWDZIC JUTRO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//////////// PYTANIE CZY TRZEBA USUWAC ISOLIERTE ART Y ////////////////////////////////////////
		
		///matrixAusgeben3(aBloeckeMatrix, "USUNIETO MOSTY------------------------------------------------------");
		
		for (int i = 0; i < aBloeckeMatrix.length; i++)
			for (int j = 0; j < aBloeckeMatrix[i].length; j++) {
				if (i != j) {
					wegBloeckeMatrix[i][j] = aBloeckeMatrix[i][j];
				} else {
				wegBloeckeMatrix[i][j] = 1;
				}
			}

		///matrixAusgeben3( aBloeckeMatrix, "BLOOOOOOOOOOOOOOOOOOOOOOOKIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII aMatrix");    
		
		multiplyAdjazenz4Bloecke (aBloeckeMatrix);
		
	
		//////////// //////////////////////////7
		SortedSet<String> s1 = new TreeSet<String>();
		int z = 0;
	
		while (z < wegBloeckeMatrix.length) {
			String wegBloeckeZeile = "";
			int s = 0;
			while (s < wegBloeckeMatrix[0].length) {
				wegBloeckeZeile += wegBloeckeMatrix[z][s];

				s++;
			}
				
			int counter = 0;
			for( int i=0; i<wegBloeckeZeile.length(); i++ ) {
			    if( wegBloeckeZeile.charAt(i) == '1' ) {
			        counter++;
			    } 
			}
				    
			if (counter>1) {	    
				s1.add(wegBloeckeZeile);
			}
				
			System.out.println();
			z++;
		}
		
		System.out.println("ROZMIAR S1: " + s1.size());
		
		
		////////// SPRAWDZIC CZY W BLOKACH JEST MAX JEDNA ARTYKULACJA ///////////
		System.out.println("--------------EULERSCH: " + eulersch );
		
		System.out.println("ROZMIAR ZBIORU ARTYKULACJI PRZED ITERACJA BLOCKOW " + artikulationen.size());

		/**
		Iterator it2 = s1.iterator();
		int zeilencheck;
		int artInbloecke; 
		
		
		zeilencheck = 0;
		while (it2.hasNext()) {
			zeilencheck++;
			
			/// WITHOUT ITERATOR???
			/// http://stackoverflow.com/questions/12455737/how-to-iterate-over-a-set-hashset-without-a-iterator
			int knotenczek = 0;
			////artInbloecke = 0;
			Object element = it2.next(); {
					///artInbloecke = 0;

					String e = element.toString();
					System.out.println("ITARACJA BLOKOW " + e );
					
					int iloscjedynek;
					int ilosczer;
					
					int artykulacja = -1;
					artInbloecke = 0;
					iloscjedynek = 0;
					ilosczer = 0;
					for (int i=0; i<e.length(); i++) {
						knotenczek++;
						///artInbloecke = 0;
						String artInbloeckeString = "";
						
						System.out.println("LITEROWANIE BLOKU: index " + i + " wartosc "  +  e.charAt(i));
						if (e.charAt(i) == '1') {
							iloscjedynek++;
							
							if (knoteISarti (i) == true) {
								System.out.println("ARTYKULACJA  W PUNKCIE: index: " + i);
								artInbloecke++;
								artykulacja = i;
							}
 							System.out.println("----------------");
							///artInbloecke++;
						} else {
							ilosczer++;
						System.out.println("NO");
						System.out.println("----------------");
						}
						
						/**
						if (e.charAt(i)== 1) {
							int a = i;
							
							for (int x=0; x<artikulationen.size(); x++){
								System.out.println(" ART: " + x + "INDEX BLOKU: " + a);
								if ( artikulationen.get(x)== a) {
									System.out.println(" ART: " + x + "INDEX BLOKU: " + a);
									artInbloecke++;
									artInbloeckeString =  artInbloeckeString + x;
								}
							
						}}
						if (artInbloecke == 1) {
							System.out.println("BLOK BEZ ARTYKULACJI" + e.toString() + " ARTY; " + artInbloeckeString.toString());
						}
						else {
							System.out.println("BLOK z ART >1: " + e.toString() + " A: " + artInbloeckeString.toString());
						}
						
						
					}
					
					if (artInbloecke == 0) {
						bloeckeEineOderKeineArtikulation.add(e.toString());
						
					}
					
					else if (artInbloecke == 1) {
						System.out.println("BLOCK " + e.toString() + " LICZBA ART: " + artInbloecke);
						bloeckeEineOderKeineArtikulation.add(e.toString());
						if (artykulacja != -1) {
							removeArtZarray(artykulacja);
							System.out.println("ROZMIAR ZBIORU ARTYKULACJI PO ITERACJI" + artikulationen.size());
							
						}
						
					} else {
						System.out.println("BLOCK Z >1 ARTYKULACJA " + e.toString() + " LICZBA ART: " + artInbloecke);
						
						
					}
					
						
					
					
					
					System.out.println("KONIEC FOR" );
					System.out.println(" 1: " + iloscjedynek + " 0: " + ilosczer);
					System.out.println(" FINAL LICZBA ARTYKULACJI W BLOKU: "+ artInbloecke);
				}
			/// NIE MA DOSTEPU //////////////////////////
			///System.out.println(" 1: " + iloscjedynek + " 0: " + ilosczer);
				
				System.out.println(" FINAL LICZBA ARTYKULACJI W BLOKU: "+ artInbloecke + " KNOTENCZEK: " + knotenczek + " ZEILENCZEK: " + zeilencheck);
			}
		
		
		
		
		///System.out.println("Dostep do artykulacji");
			
		///for (int x=0; x<artikulationen.size(); x++){
		///	 System.out.println("ART: " + artikulationen.get(x).toString());
		///	 int a = artikulationen.get(x);
		///}
		/**
		while (artikulationen.size()>0) {
			for (int x=0; x<artikulationen.size(); x++) {
				int index = 0;
				index  = x;
				
				int arti = artikulationen.get(x);
				
				
				
				
				
				
				removeArtikulation(arti);
				aBloeckeMatrix = removeFromAbloeckeMatrix(aBloeckeMatrix, arti);
				matrixAusgeben3 (aBloeckeMatrix, "USUNIECIE "+ arti + "ART Z ABLOECKE MATRIX");
				
			}
			
			
		}
		
		System.out.println(artikulationen.size());
	*/
			
	}
	
	
	
	///////////// 18 REMOVE ART Z ARRAY //////////////////////////////
	public void removeArtZarray (int art) {
		
		while (artikulationen.size()>0) {
			for (int x=0; x<artikulationen.size(); x++) {
				if (artikulationen.get(x) == art) {
					artikulationen.remove(x);
				}
					
				}
	}}
	
	/////////// 19 IST KNOTE IN BLOECKE EINE ARTIKULATION /////////
	public boolean knoteISarti (int k) {
		
	boolean btemp = false;
		
	for (int x=0; x<artikulationen.size(); x++){
	
		if ( artikulationen.get(x)== k) {
			btemp = true;
	
		}
		
	}
	return btemp;
	}
	
	
	
	///////// 20 REMOVE ARTIKULATION FROM ABLOEKEMATRIX //////////
	public int[][] removeFromAbloeckeMatrix (int [][] aBloeckeMatrix, int arti) {
		
		int z;
		int s;
		
		z=0;
		while (z < aBloeckeMatrix.length) {
			s = 0;
			while (s < aBloeckeMatrix[0].length) {
				if (z == arti) {
					aBloeckeMatrix[z][s] = 0;

					}
				else if (s == arti) {
					aBloeckeMatrix[z][s] = 0;
				
				}

				//////// ANALIZUJ TU
				/// else {
				/// weg[z][s] = 1;
				/// }

				s++;
			}
			System.out.println();
			z++;
		}
		
		return aBloeckeMatrix;
		
	}
	
	
	///////// 21 REMOVE ARTIKULATION FROM ARRAY LIST /////////
	public void removeArtikulation (int a) {
		
		for (int x=0; x<artikulationen.size(); x++){
			if (artikulationen.get(x)== a) {
				artikulationen.remove(x);
			}}
	}
		
		///////////// POROWNYWANIE SETOW 
		///for (int x=0; x<artikulationen.size(); x++)
		///    System.out.println("Bruecken: " +brueckenList.get(x));
		
	/////////// KNOTE AUSCHALTEN /////////////////
	
	
	
	
	
	
	//////////// 
	//////////// 22 MULTIPLY ADJAZENZ 4 BLOECKE  /////////////////////////////////////////////
	public void multiplyAdjazenz4Bloecke(int[][] aBloeckeMatrix) {

		int[][] temp4 = new int[numberOfKnoten][numberOfKnoten];
		int[][] mResult4 = null;
		int powtorzenia = numberOfKnoten - 1;
		int potega = 2;

		if (powtorzenia < 2) {

			////System.out.println("MULTIPLY ADJAZENZ 3 powtorzenia<2 +  potega: " + potega);
			calculateWegMatrixBloecke(aBloeckeMatrix);

			//// comparePotenzAndDistanzMatrices(adjazenzMatrix, 1);
		} else {

			while (potega <= powtorzenia) {

				if (potega == 2) {

					int m1ColLength = aBloeckeMatrix[0].length; // m1 columns length
					int m2RowLength = aBloeckeMatrix.length; // m2 rows length
					int mRRowLength = aBloeckeMatrix.length; // m result rows length
					int mRColLength = aBloeckeMatrix[0].length; // m result columns
															// length

					mResult4 = new int[mRRowLength][mRColLength];

					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult4[i][j] += aBloeckeMatrix[i][k] * aBloeckeMatrix[k][j];

								temp4[i][j] += aBloeckeMatrix[i][k] * aBloeckeMatrix[k][j];
							}
						}
					}
					///System.out.println("MULTIPLY ADJAZENZ2 IF (POTEGA ==2) Mnozenie matrix - Potega:" + potega);
					////matrixAusgeben2(mResult2, potega);
					potega++;
				} else {
					int m1ColLength = temp4[0].length; // m1 columns length
					int m2RowLength = aBloeckeMatrix.length; // m2 rows length

					int mRRowLength = temp4.length; // m result rows length
					int mRColLength = aBloeckeMatrix[0].length; // m result columns
															// length

					mResult4 = new int[mRRowLength][mRColLength];
					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult4[i][j] += temp4[i][k] * aBloeckeMatrix[k][j];
							}
						}
					}
					temp4 = mResult4;
					////System.out.println("MULTIPLY ADJAZENZ2 powtorzenia>2 Mnozenie matrix - Potega:" + potega);
					///matrixAusgeben2(mResult2, potega);
					potega++;
				}
				////matrixAusgeben3(temp2, "MULTIPLY ADJAZENZ2 koniec petli: ");
				/// if (updateGui == true) {
				calculateWegMatrixBloecke(temp4);
				/// comparePotenzAndDistanzMatrices(temp, potega-1);
				/// }
			}
			temp4 = new int[0][0];
		}
	}
	
	
	////////// 23 CALCULATE WEGMATRIX BLOECKE //////////////////////////
	public void calculateWegMatrixBloecke(int[][] temp4) {

		/// matrixAusgeben3 (weg2, "CALCULATEWEGMATRIX () WEG2 POCZATEK");

		int z;
		int s;

		z = 0;
		while (z < temp4.length) {
			s = 0;
			while (s < temp4[0].length) {
				if (z != s) {
					if (temp4[z][s] != 0 && wegBloeckeMatrix[z][s] == 0) {

						wegBloeckeMatrix[z][s] = 1;

					}
				}

				//////// ANALIZUJ TU
				/// else {
				/// weg[z][s] = 1;
				/// }

				s++;
			}
			System.out.println();
			z++;
		}
		/// weg2 = new int [0][0];
		matrixAusgeben3(wegBloeckeMatrix, "CALCULATE WEGMATRIX () BLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOKIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");

		/// for(int i=0; i<weg.length; i++)
		/// for(int j=0; j<weg[i].length; j++)
		/// weg2[i][j]=weg[i][j];

	}

	//// System.out.println("FINDE ARTIKULATIONEN");
	/// System.out.println("Artykulacje: " + artikulationen.toString());
	/// **/

	//////////// 24 MULTIPLY ADJAZENZ 3 /////////////////////////////////////////////
	public void multiplyAdjazenz3(int[][] aMatrix) {

		int[][] temp2 = new int[numberOfKnoten][numberOfKnoten];
		int[][] mResult2 = null;
		int powtorzenia = numberOfKnoten - 1;
		int potega = 2;

		if (powtorzenia < 2) {

			System.out.println("MULTIPLY ADJAZENZ 3 powtorzenia<2 +  potega: " + potega);
			calculateWegMatrix(aMatrix);

			//// comparePotenzAndDistanzMatrices(adjazenzMatrix, 1);
		} else {

			while (potega <= powtorzenia) {

				if (potega == 2) {

					int m1ColLength = aMatrix[0].length; // m1 columns length
					int m2RowLength = aMatrix.length; // m2 rows length
					int mRRowLength = aMatrix.length; // m result rows length
					int mRColLength = aMatrix[0].length; // m result columns
															// length

					mResult2 = new int[mRRowLength][mRColLength];

					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult2[i][j] += aMatrix[i][k] * aMatrix[k][j];

								temp2[i][j] += aMatrix[i][k] * aMatrix[k][j];
							}
						}
					}
					///System.out.println("MULTIPLY ADJAZENZ2 IF (POTEGA ==2) Mnozenie matrix - Potega:" + potega);
					///matrixAusgeben2(mResult2, potega);
					potega++;
				} else {
					int m1ColLength = temp2[0].length; // m1 columns length
					int m2RowLength = aMatrix.length; // m2 rows length

					int mRRowLength = temp2.length; // m result rows length
					int mRColLength = aMatrix[0].length; // m result columns
															// length

					mResult2 = new int[mRRowLength][mRColLength];
					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult2[i][j] += temp2[i][k] * aMatrix[k][j];
							}
						}
					}
					temp2 = mResult2;
					///System.out.println("MULTIPLY ADJAZENZ2 powtorzenia>2 Mnozenie matrix - Potega:" + potega);
					///matrixAusgeben2(mResult2, potega);
					potega++;
				}
				///matrixAusgeben3(temp2, "MULTIPLY ADJAZENZ2 koniec petli: ");
				/// if (updateGui == true) {
				calculateWegMatrix2(temp2);
				/// comparePotenzAndDistanzMatrices(temp, potega-1);
				/// }
			}
			temp2 = new int[0][0];
		}
	}

	//////////// 25 MULTIPLY ADJAZENZ 2 /////////////////////////////////////////////
	public void multiplyAdjazenz2(int[][] aMatrix) {

		int[][] temp2 = new int[numberOfKnoten][numberOfKnoten];
		int[][] mResult2 = null;
		int powtorzenia = numberOfKnoten - 1;
		int potega = 2;

		if (powtorzenia < 2) {

			///System.out.println("MULTIPLY ADJAZENZ 2 powtorzenia<2 +  potega: " + potega);
			calculateWegMatrix(aMatrix);

			//// comparePotenzAndDistanzMatrices(adjazenzMatrix, 1);
		} else {

			while (potega <= powtorzenia) {

				if (potega == 2) {

					int m1ColLength = aMatrix[0].length; // m1 columns length
					int m2RowLength = aMatrix.length; // m2 rows length
					int mRRowLength = aMatrix.length; // m result rows length
					int mRColLength = aMatrix[0].length; // m result columns
															// length

					mResult2 = new int[mRRowLength][mRColLength];

					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult2[i][j] += aMatrix[i][k] * aMatrix[k][j];

								temp2[i][j] += aMatrix[i][k] * aMatrix[k][j];
							}
						}
					}
					///System.out.println("MULTIPLY ADJAZENZ2 IF (POTEGA ==2) Mnozenie matrix - Potega:" + potega);
					///matrixAusgeben2(mResult2, potega);
					potega++;
				} else {
					int m1ColLength = temp2[0].length; // m1 columns length
					int m2RowLength = aMatrix.length; // m2 rows length

					int mRRowLength = temp2.length; // m result rows length
					int mRColLength = aMatrix[0].length; // m result columns
															// length

					mResult2 = new int[mRRowLength][mRColLength];
					for (int i = 0; i < mRRowLength; i++) { // rows from m1
						for (int j = 0; j < mRColLength; j++) { // columns from
																// m2
							for (int k = 0; k < m1ColLength; k++) { // columns
																	// from m1
								mResult2[i][j] += temp2[i][k] * aMatrix[k][j];
							}
						}
					}
					temp2 = mResult2;
					///System.out.println("MULTIPLY ADJAZENZ2 powtorzenia>2 Mnozenie matrix - Potega:" + potega);
					///matrixAusgeben2(mResult2, potega);
					potega++;
				}
				////matrixAusgeben3(temp2, "MULTIPLY ADJAZENZ2 koniec petli: ");
				/// if (updateGui == true) {
				calculateWegMatrix(temp2);
				/// comparePotenzAndDistanzMatrices(temp, potega-1);
				/// }
			}
			temp2 = new int[0][0];
		}
	}

	////////// 26 CALCULATE WEGMATRIX2 //////////////////////////
	public void calculateWegMatrix2(int[][] temp2) {

		/// matrixAusgeben3 (weg2, "CALCULATEWEGMATRIX () WEG2 POCZATEK");
		int z;
		int s;
		z = 0;
		while (z < temp2.length) {
			s = 0;
			while (s < temp2[0].length) {
				if (z != s) {
					if (temp2[z][s] != 0 && weg1[z][s] == 0) {

						weg1[z][s] = 1;

					}
				}


				s++;
			}
			System.out.println();
			z++;
		}
		/// weg2 = new int [0][0];
		///matrixAusgeben3(weg1, "CALCULATE WEGMATRIX () KONIEC PETLI: ");

		/// for(int i=0; i<weg.length; i++)
		/// for(int j=0; j<weg[i].length; j++)
		/// weg2[i][j]=weg[i][j];

	}

	////////// 27 CALCULATE WEGMATRIX //////////////////////////
	public void calculateWegMatrix(int[][] temp2) {

		/// matrixAusgeben3 (weg2, "CALCULATEWEGMATRIX () WEG2 POCZATEK");

		int z;
		int s;

		z = 0;
		while (z < temp2.length) {
			s = 0;
			while (s < temp2[0].length) {
				if (z != s) {
					if (temp2[z][s] != 0 && weg[z][s] == 0) {

						weg[z][s] = 1;

					}
				}

				//////// ANALIZUJ TU
				/// else {
				/// weg[z][s] = 1;
				/// }

				s++;
			}
			System.out.println();
			z++;
		}
		/// weg2 = new int [0][0];
		matrixAusgeben3(weg, "CALCULATE WEGMATRIX () KONIEC PETLI: ");

		/// for(int i=0; i<weg.length; i++)
		/// for(int j=0; j<weg[i].length; j++)
		/// weg2[i][j]=weg[i][j];

	}

	////////// 28 COMPARE ADJ AND WEG MATRICES /////////////////
	public void compareAdjAndWegMatrices(int[][] temp, boolean updateGui) {

		int z;
		int s;
		int[][] weg = new int[numberOfKnoten][numberOfKnoten];
		weg = wegMatrix;
		z = 0;
		while (z < temp.length) {
			s = 0;
			while (s < temp[0].length) {
				if (z != s) {
					if (temp[z][s] != 0 && weg[z][s] == 0) {
						weg[z][s] = 1;

					}
				}
				s++;
			}
			System.out.println();
			z++;
		}
		matrixAusgeben3(wegMatrix, "COMPARE ADJ AND WEG:");
		///System.out.println("MATRIX - compare():");
		///matrixAusgeben();
		if (updateGui == true) {
			mainFrame.wegMatrixButtonsUpdate(weg);
			anzahlDerKomponenten();
		}
		///wegMatrixFertig();
	}

	////////// 29 COMPARE POTENZ AND DISTANZ MATRICES /////////////////
	public void comparePotenzAndDistanzMatrices(int[][] temp, int potenz) {

		int z;
		int s;
		int[][] dis = new int[numberOfKnoten][numberOfKnoten];
		dis = distanzMatrix;
		z = 0;
		while (z < temp.length) {
			s = 0;
			while (s < temp[0].length) {
				if (z != s) {
					if (temp[z][s] != 0 && dis[z][s] == 0) {
						dis[z][s] = potenz;
					}
				}
				s++;
			}
			System.out.println();
			z++;
		}
		/// matrixAusgeben2(dis, 8888);
		//// ??????????????????????????????????????????????
		distanzMatrix = dis;
		//// System.out.println("MATRIX - compare():");
		matrixAusgeben();
		readAdjazenzMatrix2();
		
		mainFrame.distanzMatrixButtonsUpdate(dis);
		mainFrame.ubersichtUpdate();
	}

	//////// 30 ANZAHL DER KOMPONENTEN /////////////////////////
	public void anzahlDerKomponenten() {
		int z;
		int s;
		int anz1knoteKomp = 0;
		String wegZeile;
		int[][] weg1 = new int[numberOfKnoten][numberOfKnoten];
		SortedSet<String> set = new TreeSet<String>();

		weg1 = wegMatrix;
		z = 0;
		wegZeile = "";
		while (z < weg1.length) {
			s = 0;
			while (s < weg1[0].length) {
				wegZeile += weg1[z][s];

				s++;
			}
			if (wegZeile.contains("1")) {
				set.add(wegZeile);
			} else {
				anz1knoteKomp++;
			}

			wegZeile = "";
			System.out.println();
			z++;
		}

		String word = "1";
		StringBuilder sb;
		sb = new StringBuilder();
		int k;

		Iterator it = set.iterator();
		while (it.hasNext()) {

			Object element = it.next();
			/// System.out.println(element.toString());
			String komponent = "";
			for (int i = -1; (i = ((String) element).indexOf(word, i + 1)) != -1;) {
				komponent += mainFrame.getLiterka(i);
			}
			/// System.out.println(komponent.toString());

			System.out.println();
			sb.append("[");
			sb.append(komponent.toString()).append("] ");

		}

		updateEigenschaft2(7, sb.toString());
		anzahlDerKomponenten = set.size();
		updateEigenschaft(6, set.size());
		/// System.out.println("SET SIZE: " + set.size()+ "Anz 000000: " +
		/// anz1knoteKomp);
		/// System.out.println(set.toString());
	}
	
	
	////////// 31 FINDE ISOLIERTE KNOTEN ///////////////////////
	public void findeIsolierteKnoten () {
		
		
		int z;
		int s;
		int grad = 0;
		
		z = 0;
		///wegZeile = "";
		while (z < wegMatrix.length) {
			s = 0;
			while (s < wegMatrix[0].length) {

				if ( wegMatrix [z][s] == 1) {
					grad++;
					
				}
				
				s++;
			}
			if (grad == 1) {
				isolierteKnoten.add(z);
			}
			grad = 0;
			System.out.println();
			z++;
		}
		
		for (int x=0; x<isolierteKnoten.size(); x++)
		    System.out.println("ISOLIERTE KNOTE: " +isolierteKnoten.get(x));
 				
		/// isolierteKnoten.clear();
		
		
	}
	

	////////// 32 ANZAHL DER KOMPONENTEN 2 ////////////////////
	public int anzahlDerKomponenten2(int[][] wMatrix) {
		int z;
		int s;
		int anz1knoteKomp = 0;
		String wegZeile;
		int[][] weg1 = new int[numberOfKnoten][numberOfKnoten];
		SortedSet<String> set1 = new TreeSet<String>();
		weg1 = wMatrix;
		z = 0;
		wegZeile = "";
		while (z < weg1.length) {
			s = 0;
			while (s < weg1[0].length) {
				wegZeile += weg1[z][s];

				s++;
			}
			set1.add(wegZeile);

			wegZeile = "";
			System.out.println();
			z++;
		}
		return set1.size();
	}

	////////// 33 GET WEGMATRIX ///////////////////////////////
	public int[][] getWegMatrix() {
		return wegMatrix;
	}

	////////// 34 COPY MATRIX //////////////////////////////////
	private int[][] copy(int[][] old, int[][] target) {
		int z;
		int s;

		z = 1;
		while (z <= old.length) {
			s = 1;
			while (s <= old[0].length) {
				target[s][z] = old[s][z];
				s++;
			}
			System.out.println();
			z++;
		}

		System.out.println("Matrix has been successfully copied");
		return target;
	}

	///////// 35 MILTIPLYADJAZENZ2////////////////////////////////
	public void multiplyAdjazenz2(int[][] a, int[][] b) {

		int powtorzenia = 0;

		int m1ColLength = a[0].length; // m1 columns length
		int m2RowLength = adjazenzMatrix.length; // m2 rows length

		int mRRowLength = a.length; // m result rows length
		int mRColLength = adjazenzMatrix[0].length; // m result columns length

		int[][] mResult = new int[mRRowLength][mRColLength];
		for (int i = 0; i < mRRowLength; i++) { // rows from m1
			for (int j = 0; j < mRColLength; j++) { // columns from m2
				for (int k = 0; k < m1ColLength; k++) { // columns from m1
					mResult[i][j] += adjazenzMatrix[i][k] * adjazenzMatrix[k][j];
				}
			}
		}
		matrixAusgeben2(mResult, powtorzenia);
	}

	////////// 36 GET DATA ////////////////////////////////////////
	public Vector<Vector<String>> getData() {
		Vector<Vector<String>> data = new Vector<>();

		for (Eigenschaft e : eigenschaften) {
			data.add(e.getDaten());
		}
		return data;
	}

	///////// 37 GET TITEL //////////////////////////////////////
	public Vector<String> getTitel() {
		Vector<String> titel = new Vector<>();
		titel.add("Eigenschaften des Graphen: ");
		titel.add("Details: ");

		return titel;
	}

	///// 38 EINFUGEN //////////////////////////////////////////
	public void einfugen(Eigenschaft e) {
		if (e != null && !eigenschaften.contains(e)) {
			eigenschaften.add(e);
		}
	}

	////// 39 UPDATE ANZAHL DER KNOTEN ////////////////////
	public void updateAnzahlDerKnoten(int numberOfKnoten) {
		eigenschaften.get(0).setWynik(numberOfKnoten);
	}

	////// 40 UPDATE ZUSAMMENHÄNGEND ////////////////////
	public void updateZusammenhaengend(int x, boolean jein) {
		eigenschaften.get(2).setZusammenhaengend(x, jein);
	}
	
	/////// 41 UPDATE ZUSAMMENHENGEND2 //////////////////////////////////////
	public void updateZusammenhaengend2(int x, boolean jein) {
		eigenschaften.get(12).setZusammenhaengend(x, jein);
	}

	///// 42 UPDATE EIGENSCHAFT /////////////////////////
	public void updateEigenschaft(int x, int wert) {
		eigenschaften.get(x).setWynik(wert);
	}
	
	///// 43 UPDATE EIGENSCHAFT2 /////////////////////////
	public void updateEigenschaft2(int x, String wert) {
		eigenschaften.get(x).setSwynik(wert);
	}

}
