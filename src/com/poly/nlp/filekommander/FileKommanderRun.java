/**
 * 
 */
package com.poly.nlp.filekommander;

import gate.util.GateException;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

import javax.swing.SwingWorker;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;

import com.poly.nlp.filekommander.views.FileKommanderGUIV2;

/**
 * @author jake
 *
 */
public class FileKommanderRun {
	private static FileKommander kommander;
	static FileKommanderGUIV2 guiv2 ;
	
	/**
	 * @param args
	 * @throws ConfigurationException 
	 * @throws GateException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws ConfigurationException, MalformedURLException, GateException {
		
		// create a new commander object
		kommander = new FileKommander(); 
		Configuration config  = kommander.loadConfig("FileKommander.properties");
		kommander.setGateHome(config.getString("gateHome"));
		kommander.setGazetteerFilePath(config.getString("gazetteerFilePath"));
		kommander.setJapeFilePath(config.getString("japeFilePath"));
		
		kommander.initGATE();
		kommander.setAnnie(kommander.initANNIE());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiv2 = new FileKommanderGUIV2();
					guiv2.getFrmFileKommander().setVisible(true);
					guiv2.getRunBtn().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							analyseTextInput();
						}
					});
					guiv2.getInputTextFld().addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
						    int key = e.getKeyCode();
					        if (key == java.awt.event.KeyEvent.VK_ENTER) {
					        	analyseTextInput();					    
					        }

						}
					});
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		

		
	}

	/**
	 * 
	 */
	public static void analyseTextInput(){
		showProgress();
		String text = guiv2.getInputTextFld().getText() ;
		guiv2.getOutputLbl().setText("You Pressed - " + text);
		kommander.setUserInputText(text);
		kommander.run();
	}
	
	/**
	 * @return the kommander
	 */
	public static FileKommander getKommander() {
		return kommander;
	}

	/**
	 * @param kommander the kommander to set
	 */
	public static void setKommander(FileKommander kommander) {
		FileKommanderRun.kommander = kommander;
	}
	
	public static void showProgress(){
		MySwingWorker worker = new MySwingWorker();
		worker.execute();
	}


}

class MySwingWorker extends SwingWorker<Integer, String> {

	protected Integer doInBackground() throws Exception {
		for (int i=0; i<=100; i++) {
			  
	   FileKommanderRun.guiv2.getProgressBar().setValue(i);
       Thread.sleep(50);
        
}
return null;
}
} 

