package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.Matrix;

public class MainFrame extends JFrame {
	
	
	private Matrix matrix;
	private Uebersicht uebersicht;
	private JMenuBar menuBar;
	private JMenu data;
	private JComboBox combo;
	private JPanel panelNorth, panelNorthTop, panelNorthBottom, panelCentral, panelCentralTop, panelCentralny, panelSrodek, 
	panelMatrix, panelWegMatrix, panelWegMatrixTop, panelWegMatrixBottom, panelDistanzMatrix, panelDistanzMatrixTop, panelDistanzMatrixBottom;
	
	
	private JLabel knotenLabel, adjazenzLabel, wegLabel, distanzLabel;
	private JButton btnOk;
	private JButton btnReset;
	private JButton btnTutaj;
	private Border thinBorder;
	private JToggleButton [][] buttons;
	private JToggleButton [][] buttons1;
	private JToggleButton [][] buttons2;
	private String [] literki;
	///private JToggleButton tbtn1;
	private int numberOfKnoten;

	///// CONSTRUCTOR /////////
	public MainFrame() {
		initBasics();
		initComponents();
		addComponents();
		addListeners();

		setVisible(true);
	}

	///// INIT BASICS /////
	private void initBasics() {
		setTitle("PUKA Graphen");
		///setSize(1500, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	///// INIT COMPONENTS ////////
	private void initComponents() {
		menuBar = new JMenuBar();
		data = new JMenu("Data");
		
		knotenLabel = new JLabel("Graphengröße");
		
		combo = new JComboBox();
		combo.addItem("");
		combo.addItem("1");
		combo.addItem("2");
		combo.addItem("3");
		combo.addItem("4");
		combo.addItem("5");
		combo.addItem("6");
		combo.addItem("7");
		combo.addItem("8");
		combo.addItem("9");
		combo.addItem("10");
		combo.addItem("11");
		combo.addItem("12");
		combo.addItem("13");
		combo.addItem("14");
		combo.addItem("15");
		combo.setEditable(false);
		
		btnOk = new JButton("OK");
		btnReset = new JButton("Reset");
		btnTutaj = new JButton("Tutaj");
		
		
		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(2,1));
		panelNorthTop = new JPanel();
		panelNorthTop.setLayout(new FlowLayout());
		panelNorthBottom = new JPanel();
		panelNorthBottom.setLayout(new FlowLayout());
		
		panelCentral = new JPanel();	
		panelCentralTop = new JPanel();
		panelCentralny = new JPanel();
		
		panelMatrix = new JPanel();
		panelMatrix.setLayout(new GridLayout(1,2));
		
		panelWegMatrix = new JPanel();
		panelWegMatrixTop = new JPanel();
		panelWegMatrixBottom = new JPanel();
		
		
		panelDistanzMatrix = new JPanel();
		panelDistanzMatrixTop = new JPanel();
		panelDistanzMatrixBottom = new JPanel();
		
		
		panelSrodek = new JPanel();
		panelSrodek.setLayout(new GridLayout(3,1));
		
		literki = new String [16];
		literki[0]="A";
		literki[1]= "B";
		literki[2]= "C";
		literki[3]= "D";
		literki[4]= "E";
		literki[5]= "F";
		literki[6]= "G";
		literki[7]= "H";
		literki[8]= "I";
		literki[9]= "J";
		literki[10]= "K";
		literki[11]= "L";
		literki[12]= "M";
		literki[13]= "N";
		literki[14]= "O";
		literki[15]="P";	
		

	
	}

	///// ADD COMPONETS //////////
	private void addComponents() {
		menuBar.add(data);
		setJMenuBar(menuBar);
		
		panelNorthTop.add(knotenLabel);
		panelNorthTop.add(combo);
		panelNorthBottom.add(btnOk);
		panelNorthBottom.add(btnReset);
		panelNorth.add(panelNorthTop);
		panelNorth.add(panelNorthBottom);
		LineBorder lineBorder = (LineBorder)BorderFactory.createLineBorder(Color.black);
	    panelNorth.setBorder(lineBorder);
		
		add(panelNorth,BorderLayout.NORTH);
		add(panelSrodek, BorderLayout.CENTER);
		

		
	}
	
	///// ADD LISTENERS ///////////
	private void addListeners() {
		combo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          ///System.out.println("Selected index=" + combo.getSelectedIndex()
	          ///    + " Selected item=" + combo.getSelectedItem());
	        }
	      });
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combo.setEnabled(true);
				combo.setSelectedIndex(0);
				panelCentral.setVisible(false);
				
				btnOk.setEnabled(true);
				removeButtons();
			}			
		});
		btnOk.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				numberOfKnoten = combo.getSelectedIndex();
				
				combo.setEnabled(false);
				createButtons(numberOfKnoten);
				///System.out.println(String.valueOf(numberOfKnoten));

			}			
		});			
	}
	
	////// HANDLER METHODS ////////////////////
	//----------Create buttons---------------//
	public void createButtons (int numberOfKnoten) {
		
		//--- check if number of knoten is greate than zero
		int infinity = 236;
		if (numberOfKnoten == 0) {
			JOptionPane.showMessageDialog(this, "Bitte die Anzahl der Knoten auswählen!", "Error", JOptionPane.ERROR_MESSAGE);	
			combo.setEnabled(true);
			btnOk.setEnabled(true);
		}
		else {
			//--- create following instances: Matrix, Uebersicht 
			matrix = new Matrix (this, numberOfKnoten);
			this.matrix = matrix;	
			uebersicht = new Uebersicht(matrix);
			
		//--- create array "buttons" for Adjazenz Matrix 	
		buttons = new JToggleButton [numberOfKnoten+1][numberOfKnoten+1];
		panelCentralTop.setLayout(new FlowLayout());
		adjazenzLabel = new JLabel("Adjazenzmatrix");
		panelCentralTop.add(adjazenzLabel);
		panelCentral.setLayout(new GridLayout(numberOfKnoten+1, numberOfKnoten+1));
		
		//--- create array "buttons1" for Weg Matrix 
		buttons1 = new JToggleButton [numberOfKnoten+1][numberOfKnoten+1];
		panelWegMatrixTop.setLayout(new FlowLayout());
		wegLabel = new JLabel("Wegmatrix");
		panelWegMatrixTop.add(wegLabel);
		panelWegMatrixBottom.setLayout(new GridLayout(numberOfKnoten+1, numberOfKnoten+1));
		
		//--- create array "buttons2" for DistanzMatrix
		buttons2 = new JToggleButton [numberOfKnoten+1][numberOfKnoten+1];
		panelDistanzMatrixTop.setLayout(new FlowLayout());
		distanzLabel = new JLabel("Distanzmatrix");
		panelDistanzMatrixTop.add(distanzLabel);
		panelDistanzMatrixBottom.setLayout(new GridLayout(numberOfKnoten+1, numberOfKnoten+1));
		
		//--- Borders----------------------------------------------------------------// 
		LineBorder lineBorder = (LineBorder)BorderFactory.createLineBorder(Color.black);  
	    panelCentralny.setBorder(lineBorder);
	    panelWegMatrix.setBorder(lineBorder);
	    panelDistanzMatrix.setBorder(lineBorder);
		panelCentral.setVisible(true);
		/// combo.setEnabled(false);
	
		//--- create buttons and disabled buttons for knoten and disabled buttons for knoten description
			int i;
			int j;	
			i=0;
			while (i<=numberOfKnoten) {
					j=0;
					while (j<=numberOfKnoten) {
						if (i==0 && j==0) {
							buttons [i][j] = new JToggleButton ("");
							buttons [i][j].setEnabled(false);
							panelCentral.add(buttons [i][j]);
							buttons1 [i][j] = new JToggleButton ("");
							buttons1 [i][j].setEnabled(false);
							panelWegMatrixBottom.add(buttons1[i][j]);
							buttons2 [i][j] = new JToggleButton ("");
							buttons2 [i][j].setEnabled(false);
							panelDistanzMatrixBottom.add(buttons2[i][j]);
							j++;
						}
						
						else if (i==0&&j!=0) {
							buttons [i][j] = new JToggleButton (literki[j-1]);
							buttons [i][j].setEnabled(false);
							panelCentral.add(buttons [i][j]);
							buttons1 [i][j] = new JToggleButton (literki[j-1]);
							buttons1 [i][j].setEnabled(false);
							panelWegMatrixBottom.add(buttons1[i][j]);
							buttons2 [i][j] = new JToggleButton (literki[j-1]);
							buttons2 [i][j].setEnabled(false);
							panelDistanzMatrixBottom.add(buttons2[i][j]);
							j++;
						}
						else if (j==0&&i!=0) {
							buttons [i][j] = new JToggleButton (literki[i-1]);
							buttons [i][j].setEnabled(false);
							panelCentral.add(buttons [i][j]);
							buttons1 [i][j] = new JToggleButton (literki[i-1]);
							buttons1 [i][j].setEnabled(false);
							panelWegMatrixBottom.add(buttons1[i][j]);
							buttons2 [i][j] = new JToggleButton (literki[i-1]);
							buttons2 [i][j].setEnabled(false);
							panelDistanzMatrixBottom.add(buttons2[i][j]);
							j++;
						}
						else {
						buttons [i][j] = new JToggleButton ("0");
						buttons1 [i][j] = new JToggleButton ("0");
						buttons2 [i][j] = new JToggleButton (Character.toString('\u221e'));
												
						if (i == j) {
							buttons [i][j].setSelected(true);
							buttons [i][j].setEnabled(false);
							buttons1 [i][j].setText("1");
							buttons1 [i][j].setSelected(true);
							buttons1 [i][j].setEnabled(false);
							buttons2 [i][j].setText("0");
							buttons2 [i][j].setSelected(true);
							buttons2 [i][j].setEnabled(false);
							
						}
						panelCentral.add(buttons [i][j]);
						panelWegMatrixBottom.add(buttons1[i][j]);
						panelDistanzMatrixBottom.add(buttons2[i][j]);
						
						buttons[i][j].putClientProperty("wiersz", i);
						buttons[i][j].putClientProperty("kolumna", j);
						///buttons1[i][j].putClientProperty("wiersz", i);
						///buttons1[i][j].putClientProperty("kolumna", j);
						///??????????????????????????
	
						//--- add ActionListener to buttons of Adjazenz Matrix
						buttons[i][j].addActionListener(new ActionListener () {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								JToggleButton btn = (JToggleButton) e.getSource();
								
								if (btn.isSelected()==true) {
									btn.setText("1");
									int y = (int) btn.getClientProperty("wiersz");
									int x = (int) btn.getClientProperty("kolumna");
									buttons[x][y].setSelected(true);
									///buttons1[x][y].setSelected(true);
									///buttons1[y][x].setSelected(true);
									buttons[x][y].setText("1");
									///buttons1[x][y].setText("1");
									///buttons1[y][x].setText("1");
									ausgeben(numberOfKnoten);
								
									///matrix.sendWegMatrix();
									//wegMatrixButtonsUpdate();
									
								} else {
									btn.setText("0");
									int y = (int) btn.getClientProperty("wiersz");
									int x = (int) btn.getClientProperty("kolumna");
									buttons[x][y].setSelected(false);
									///buttons1[x][y].setSelected(false);
									///buttons1[y][x].setSelected(false);
									buttons[x][y].setSelected(false);
									buttons[x][y].setText("0");
									///buttons1[x][y].setText("0");
									///buttons1[y][x].setText("0");
									ausgeben(numberOfKnoten);
								
									///matrix.sendWegMatrix();
									/// wegMatrixButtonsUpdate();

									
									///System.out.println("Weg matrix buttons update complete!");
								
								}
								
								matrix.multiplyAdjazenz(numberOfKnoten, true);
								matrix.findeBruecken();
								matrix.findeArtikulationen();
								///matrix.bloeckeAusgeben();	
								uebersicht.update();
								///testWeg();
								
							}
							
						});
						j++;
						}}				
					i++;
					}
			btnOk.setEnabled(false);
			////ausgeben(numberOfKnoten);
			///testWeg();
			///////////////////////////////////////<---------------------------- }
			
			
			
		/*
		buttons1 = new JToggleButton[numberOfKnoten+1][numberOfKnoten+1];
		
		panelWegMatrixTop.setLayout(new FlowLayout());
		wegLabel = new JLabel ("Wegmatrix");
		panelWegMatrixTop.add(wegLabel);
		panelWegMatrixBottom.setLayout(new GridLayout(numberOfKnoten+1, numberOfKnoten+1));
		
		int u;
		int w;	
		u=0;
		while (u<=numberOfKnoten) {
				w=0;
				while (w<=numberOfKnoten) {
					if (u==0 && w==0) {
						buttons1 [u][w] = new JToggleButton ("");
						buttons1 [u][w].setEnabled(false);
						panelWegMatrixBottom.add(buttons1 [u][w]);
						w++;
					}
					
					else if (u==0&&w!=0) {
						buttons1 [u][w] = new JToggleButton (literki[w-1]);
						buttons1 [u][w].setEnabled(false);
						panelWegMatrixBottom.add(buttons1 [u][w]);
						w++;
					}
					else if (w==0&&u!=0) {
						buttons1 [u][w] = new JToggleButton (literki[u-1]);
						buttons1 [u][w].setEnabled(false);
						panelWegMatrixBottom.add(buttons1 [u][w]);
						w++;
					}
					else {
					buttons1 [u][w] = new JToggleButton ("0");
					
					if (u == w) {
						buttons1 [u][w].setSelected(true);
						buttons1 [u][w].setEnabled(false);
					}
					panelWegMatrixBottom.add(buttons1 [u][w]);
					}
				w++;
		}u++;}
		
		*/

		panelCentralny.setLayout(new BorderLayout());
		panelCentralny.add(panelCentralTop, BorderLayout.NORTH);
		panelCentralny.add(panelCentral, BorderLayout.CENTER);
		
		panelWegMatrix.setLayout(new BorderLayout());
		panelWegMatrix.add(panelWegMatrixTop, BorderLayout.NORTH);
		panelWegMatrix.add(panelWegMatrixBottom, BorderLayout.CENTER);
		
		panelDistanzMatrix.setLayout(new BorderLayout());
		panelDistanzMatrix.add(panelDistanzMatrixTop, BorderLayout.NORTH);
		panelDistanzMatrix.add(panelDistanzMatrixBottom, BorderLayout.CENTER);
		

			
		panelSrodek.add(panelCentralny);
		panelMatrix.add(panelWegMatrix);
		panelMatrix.add(panelDistanzMatrix);
		panelSrodek.add(panelMatrix);
		
		ausgeben(numberOfKnoten);
			
		matrix.updateAnzahlDerKnoten(numberOfKnoten);
		matrix.anzahlDerKomponenten();
		matrix.findeIsolierteKnoten();
		///matrix.bloeckeAusgeben();
		
		matrix.readAdjazenzMatrix2();
		///matrix.eulersch();
		panelSrodek.add(uebersicht);

		uebersicht.update();
		
		///////// FINDE ART ////////////////
		///matrix.findeArtikulationen();
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		/// ausgeben(numberOfKnoten);
				
	}}
	
	
	//-------set Text --------------------------------//
	public void setButtons1Text (int z, int s, String t) 
	{
		buttons1[z][s].setText(t);
	}
	
	//-------Weg Matrix Button Update------------------//
	public void wegMatrixButtonsUpdate (int [][] kaka) {
		int z;
		int s;
		int [][]x = new int [numberOfKnoten][numberOfKnoten];
		x = kaka;
		///matrix.matrixAusgeben2(x, 777);
		z = 0;
		///System.out.println("WegMAtrix Buttons Update!");
		while (z<x.length) {
			s=0;
			while (s<x[0].length) {
				////System.out.print("z:" + z + " s:" + s + " Value: " + String.valueOf(x [z][s]));
				int wartosc = x[z][s];
			buttons1[z+1][s+1].setText(String.valueOf(wartosc));
			if (wartosc == 1) {
				buttons1[z+1][s+1].setSelected(true);				
			} else {
				buttons1[z+1][s+1].setSelected(false);
			}
				///buttons1[z+1][s+1].setText("9");
				s++;
			}
			System.out.println();
			z++;
		}
	}
	
	
	//-------Remove buttons ---------------------------//
	private void removeButtons () {
		System.out.println("Buttons array: " + Arrays.toString(buttons));
		panelCentralTop.removeAll();
		panelCentralTop.repaint();
		panelCentralTop.revalidate();
		
		panelCentral.removeAll();
		panelCentral.repaint();
		panelCentral.revalidate();
		
		panelCentralny.removeAll();
		panelCentralny.repaint();
		panelCentral.revalidate();
		
		panelWegMatrixTop.removeAll();
		panelWegMatrixTop.repaint();
		panelWegMatrixTop.revalidate();
		
		panelWegMatrixBottom.removeAll();
		panelWegMatrixBottom.repaint();
		panelWegMatrixBottom.revalidate();
		
		panelWegMatrix.removeAll();
		panelWegMatrix.repaint();
		panelWegMatrix.revalidate();
		
		panelDistanzMatrixTop.removeAll();
		panelDistanzMatrixTop.repaint();
		panelDistanzMatrixTop.revalidate();
		
		panelDistanzMatrixBottom.removeAll();
		panelDistanzMatrixBottom.repaint();
		panelDistanzMatrixBottom.revalidate();
		
		panelWegMatrix.removeAll();
		panelWegMatrix.repaint();
		panelWegMatrix.revalidate();
		
		panelMatrix.removeAll();
		panelMatrix.repaint();
		panelMatrix.revalidate();	
		
		panelSrodek.removeAll();
		panelSrodek.repaint();
		panelSrodek.revalidate();
		
		buttons = new JToggleButton [0][0];
	}
	//----ausgeben ---------------------------------------//
	private void ausgeben (int numberOfKnoten) {
		int z;
		int s;
		///System.out.println("MAINFRAME - buttons[z][s] ausgeben(): ");
		z = 1;
		while (z<=numberOfKnoten) {
			s=1;
			while (s<=numberOfKnoten) {
				////System.out.print(buttons[z][s].getText()+ "\t");
				matrix.populateAdjazenzMatrix(z, s, Integer.valueOf(buttons[z][s].getText()));
				matrix.populateWegMatrix(z, s, Integer.valueOf(buttons[z][s].getText()));
				matrix.populateDistanzMatrix(z, s, Integer.valueOf(buttons[z][s].getText()));
				s++;
			}
			System.out.println();
			z++;
		}
		// --- matrix ausgeben
		matrix.matrixAusgeben();


	}

	///// MAIN /////
	public static void main(String[] args) {
		new MainFrame();
	}

	//-------Weg Matrix Button Update------------------//
	public void distanzMatrixButtonsUpdate(int[][] dis) {
		int z;
		int s;
		int [][]x = new int [numberOfKnoten][numberOfKnoten];
		x = dis;
		////matrix.matrixAusgeben2(x, 888);
		z = 0;
		///System.out.println("WegMAtrix Buttons Update!");
		while (z<x.length) {
			s=0;
			while (s<x[0].length) {
				////System.out.print("z:" + z + " s:" + s + " Value: " + String.valueOf(x [z][s]));
				int wartosc = x[z][s];
				if ( z==s) {
					buttons2[z+1][s+1].setText(String.valueOf(0));
				} else {
					buttons2[z+1][s+1].setText(String.valueOf(wartosc));
					if (wartosc !=0) {
						buttons2[z+1][s+1].setSelected(true);				
					} else if (wartosc == 0){
						buttons2[z+1][s+1].setText(Character.toString('\u221e'));
						buttons2[z+1][s+1].setSelected(false);
					} 					
				}				
				s++;
			}
			System.out.println();
			z++;
		}
	}
	
	//////// GET LITERKI ///////////////////
	public String getLiterka (int x) {
		return literki[x].toString();
	}
	
	///////// ubersichtUpdate //////////////
	public void ubersichtUpdate () {
		uebersicht.update();
		
	}
	
	public void testWeg () {
		int [][] testAdjMatrix = new int [5][5];
		
		testAdjMatrix[0][0] = 0;
		testAdjMatrix[0][1] = 1;
		testAdjMatrix[0][2] = 0;
		testAdjMatrix[0][3] = 0;
		testAdjMatrix[0][4] = 0;
		
		testAdjMatrix[1][0] = 1;
		testAdjMatrix[1][1] = 0;
		testAdjMatrix[1][2] = 0;
		testAdjMatrix[1][3] = 1;
		testAdjMatrix[1][4] = 0;
		
		testAdjMatrix[2][0] = 0;
		testAdjMatrix[2][1] = 0;
		testAdjMatrix[2][2] = 0;
		testAdjMatrix[2][3] = 1;
		testAdjMatrix[2][4] = 0;
		
		testAdjMatrix[3][0] = 0;
		testAdjMatrix[3][1] = 1;
		testAdjMatrix[3][2] = 1;
		testAdjMatrix[3][3] = 0;
		testAdjMatrix[3][4] = 0;
		
		testAdjMatrix[4][0] = 0;
		testAdjMatrix[4][1] = 0;
		testAdjMatrix[4][2] = 0;
		testAdjMatrix[4][3] = 0;
		testAdjMatrix[4][4] = 0;
	
		matrix.multiplyAdjazenz2(testAdjMatrix);
		
	}

}
