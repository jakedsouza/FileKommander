package com.poly.nlp.filekommander.views;

import gate.swing.JMenuButton;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class FileKommanderMenu extends JMenuBar{

	/**
	 * Create the panel.
	 */
	public FileKommanderMenu() {
		
		JMenu fileMenu = new JMenu("File");
		add(fileMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAbout(e);
			}
		});
		fileMenu.add(aboutMenuItem);		
	}

	protected void displayAbout(ActionEvent e) {
		AboutFileKommander aboutFileKommander = new AboutFileKommander();
		aboutFileKommander.setLocationRelativeTo(null);
		aboutFileKommander.setVisible(true);		
	}

}
