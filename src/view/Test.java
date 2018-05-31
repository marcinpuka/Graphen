package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;

public class Test {
	
	private JToggleButton [][] buttons = null;
	private int numberOfKnoten = 4;
	
	public Test () {
		
		buttons = new JToggleButton [5][5];
		create();
	}
	
	
	private void create () {
		int i;
		int j;
		
		i=0;
		while (i<=numberOfKnoten-1) {
				j=0;
				while (j<=numberOfKnoten-1) {
					
					buttons [i][j] = new JToggleButton ("0");
					System.out.println("i:" + i + " j:" + j + " Butt: " + buttons[i][j].toString());
					
					j++;
					}				
				i++;
				}
	}
	
///// MAIN /////
	public static void main(String[] args) {
		new Test();
	}

}
