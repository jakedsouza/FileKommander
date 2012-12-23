package com.poly.nlp.filekommander.views;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.JMenuItem;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.poly.nlp.filekommander.FileKommanderRun;

public class FileKommanderMenu extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 */
	OptionsFileKommander optionsFileKommander ;

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
		JMenuItem optionsMenuItem = new JMenuItem("Options");
		optionsMenuItem.setBorderPainted(true);
		optionsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayOptions(e);		
			}
		});
		fileMenu.add(optionsMenuItem);
		fileMenu.add(aboutMenuItem);		
	}

	protected void displayOptions(ActionEvent e){
		optionsFileKommander = new OptionsFileKommander();
		optionsFileKommander.setLocationRelativeTo(null);
		loadOptionsFile();
		optionsFileKommander.setVisible(true);		
	}
	
	private void loadOptionsFile(){
		optionsFileKommander.getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			saveAndDispose();
			}
		});
		PropertiesConfiguration configuration = FileKommanderRun.getConfiguration();
		Iterator<String> keys = configuration.getKeys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = configuration.getString(key);
			setLabelText(key, value);
		}
		optionsFileKommander.paintAll(optionsFileKommander.getGraphics());
	}
	
	protected void saveAndDispose() {
		PropertiesConfiguration configuration = FileKommanderRun.getConfiguration() ;
		
		Component[] components = optionsFileKommander.getContentPanel().getComponents() ;
		
		for (int i = 0; i < components.length; i++) {
		//	if(//components[i].)
			String key ="";
			String value = "";
			if(components[i] instanceof JLabel){
				JLabel jLabel = (JLabel) components[i];
				key = jLabel.getText();
			}
			if(components[i] instanceof JTextField){
				JTextField jTextField= (JTextField) components[i];
				value = jTextField.getText();
			}
			configuration.addProperty(key, value);
			System.out.println(components[i]);
		}
		try {
			configuration.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileKommanderRun.setConfiguration(configuration);
		try {
			FileKommanderRun.loadConfigurationFromFile("/filekommander.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setLabelText(String key , String value){
		JLabel jLabel = new JLabel(key);
		JTextField textField = new JTextField(value);
		jLabel.setLabelFor(textField);
		optionsFileKommander.getContentPanel().add(jLabel);
		optionsFileKommander.getContentPanel().add(textField);
	}
	
	protected void displayAbout(ActionEvent e) {
		AboutFileKommander aboutFileKommander = new AboutFileKommander();
		aboutFileKommander.setLocationRelativeTo(null);
		aboutFileKommander.setVisible(true);		
	}

}
