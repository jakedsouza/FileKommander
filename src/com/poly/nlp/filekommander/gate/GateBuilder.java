/**
 * 
 */
package com.poly.nlp.filekommander.gate;

import gate.Factory;
import gate.Gate;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;

/**
 * @author jake
 * 
 */
public class GateBuilder {

	private final String fileSeparator = System.getProperty("file.separator");
	public static String newline = ("\n");
	private static final Logger log = Logger.getLogger(GateBuilder.class);

	private String GATE_HOME;
	private URI ANNIE_HOME ;
	private String ANNIE_CLASS = "gate.creole.SerialAnalyserController";
	
	private SerialAnalyserController annieController;
	private ArrayList<ProcessingResource> prList;
	private ArrayList<String> prListString;
	public GateBuilder() throws GateException, MalformedURLException {

		setup();
	}
	

	public void setup() throws GateException, MalformedURLException {
		if (this.GATE_HOME == null) {
			log.warn("GATE_HOME is not set trying to find GATE_HOME in sysenv");
			this.GATE_HOME = System.getenv("GATE_HOME");
			if (this.GATE_HOME == null) {
				log.error("GATE_HOME can not be found will exit ");
				throw new RuntimeException("GATE_HOME not set");
			}

		}
		
		this.ANNIE_HOME = new File(GATE_HOME+ fileSeparator
				+ "plugins" + fileSeparator + "ANNIE").toURI();

		System.setProperty("gate.home", GATE_HOME);
		System.setProperty("gate.site.config", System.getProperty("gate.home")
				+ fileSeparator + "gate.xml");
		System.setProperty("gate.user.config",
				System.getProperty("gate.site.config"));

		
		
		log.info("Initialising GATE");
		Gate.init();
		log.info("Done initialising GATE");
		
		// register plugin directories
		Gate.getCreoleRegister().registerDirectories(ANNIE_HOME.toURL());
		
		// start annie
		
		annieController = (SerialAnalyserController) Factory
				.createResource(ANNIE_CLASS, Factory
						.newFeatureMap(), Factory.newFeatureMap(), "ANNIE_"
						+ Gate.genSym());	
		
		for (String pr : prListString) {
			
		}
		
	}

}
