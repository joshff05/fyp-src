package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.hexgame;

public class game_Controller {
	public static int turnNumber = 1;
	public static boolean winner = false;
	//public static String ecoType;

// add whil to seperate thread to make it so it can be run. 
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				
				JFrame frame = new JFrame("economic choice");
				frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				
				final JComboBox<String> economicType = new JComboBox<String>();
				economicType.addItem("neo-liberal");
				economicType.addItem("peace-building");
				economicType.setSize(100, 10);
				
				
				JButton start = new JButton("start game");
				start.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String ecoType = (String) economicType.getItemAt(economicType.getSelectedIndex());
						
						if (ecoType != null){
							hexgame game = new hexgame(ecoType);
						}
						
					}
				});
				
				frame.setLayout(new BorderLayout());
				
				frame.add(economicType,BorderLayout.CENTER);
				frame.add(start,BorderLayout.SOUTH);
				frame.setSize(400,100);
				frame.setResizable(true);
				frame.setLocationRelativeTo( null );
				frame.setVisible(true);
				
			}
		});
	}



}
