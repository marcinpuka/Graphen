package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Matrix;

public class Uebersicht extends JInternalFrame {

	private DefaultTableModel model;
	private JTable table;
	private Matrix matrix;
	private JScrollPane scroll;
	
	
	////////// CONSTRUCTOR ////////////////////////
	public Uebersicht (Matrix matrix) {
		this.matrix = matrix;
		initBasics();
		initAndAdd();

	}
	
	////////// INIT BASICS ////////////////////////
	private void initBasics () {
		setTitle("Eigenschaften: ");
		setSize(600, 400);
		setLocation(0, 0);
		setVisible(true);
	}
	
	////////// INIT AND ADD ///////////////////////
	private void initAndAdd () {
		
		model = new DefaultTableModel();
		table = new JTable (model);
		scroll = new JScrollPane (table);
		add(scroll);
	}
	
	///////// UPDATE //////////////////////////////////
	public void update() {
		
		model.setDataVector(matrix.getData(), matrix.getTitel());
		
		setVisible(true);
	}
	
	///////// GET AUSWAHL ////////////////////////////
	public int [] getAuswahl() {
		return table.getSelectedRows();
	}
	
}
