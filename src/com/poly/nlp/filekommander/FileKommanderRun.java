/**
 * 
 */
package com.poly.nlp.filekommander;

import gate.util.GateException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;

import com.poly.nlp.filekommander.views.SWTWindow;

/**
 * @author jake
 *
 */
public class FileKommanderRun {
	private static FileKommander kommander;
	static SWTWindow window;
	
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

		window = new SWTWindow();
		window.open();
		String text = ""; 
		String oldText = "";
		while (true ) {			
			if(!text.equals(oldText)){

			}
			oldText = text ;
		}
		
		
		
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

}
