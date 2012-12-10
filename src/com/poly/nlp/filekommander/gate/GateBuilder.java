/**
 * 
 */
package com.poly.nlp.filekommander.gate;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.ProcessingResource;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.creole.Transducer;
import gate.creole.gazetteer.DefaultGazetteer;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * @author jake , neha
 * 
 */
public class GateBuilder {

	private final String fileSeparator = System.getProperty("file.separator");
	public static String newline = ("\n");
	private static final Logger log = Logger.getLogger(GateBuilder.class);

	private String gateHome;
	private URI annieHome;
	private String japeFilePath;
	private String gazetteerFilePath;

	private String ANNIE_CLASS = "gate.creole.SerialAnalyserController";

	private ArrayList<ProcessingResource> prList;
	private SerialAnalyserController annieController;

	public GateBuilder() {
		
	}

	public GateBuilder(String gateHome, String gazetteerFilePath,
			String japeFilePath) {
		this.prList = new ArrayList<>();
		this.gateHome = gateHome;
		this.gazetteerFilePath = gazetteerFilePath;
		this.japeFilePath = japeFilePath;

		if (this.gateHome == null) {
			log.warn("GATE_HOME is not set trying to find GATE_HOME in sysenv");
			this.gateHome = System.getenv("GATE_HOME");
			if (this.gateHome == null) {
				log.error("GATE_HOME can not be found will exit ");
				throw new RuntimeException("GATE_HOME not set");
			}

		}

		this.annieHome = new File(this.gateHome + fileSeparator + "plugins"
				+ fileSeparator + "ANNIE").toURI();
		System.setProperty("gate.home", this.gateHome);
		System.setProperty("gate.site.config", System.getProperty("gate.home")
				+ fileSeparator + "gate.xml");
		System.setProperty("gate.user.config",
				System.getProperty("gate.site.config"));
	}

	public void setup() throws GateException, MalformedURLException {
		log.info("Initialising GATE");
		Gate.init();
		log.info("Done initialising GATE");

		// register plugin directories
		Gate.getCreoleRegister().registerDirectories(annieHome.toURL());

	}

	public void loadANNIE() throws ResourceInstantiationException {
		annieController = (SerialAnalyserController) Factory.createResource(
				ANNIE_CLASS, Factory.newFeatureMap(), Factory.newFeatureMap(),
				"ANNIE_" + Gate.genSym());

		return;
	}

	public void loadAllProcessingResources()
			throws ResourceInstantiationException, MalformedURLException {

		FeatureMap params = Factory.newFeatureMap();
		params.put("keepOriginalMarkupsAS", true);
		ProcessingResource documentReserPR = (ProcessingResource) Factory
				.createResource("gate.creole.annotdelete.AnnotationDeletePR",
						params);
		prList.add(documentReserPR);

		params = Factory.newFeatureMap();
		ProcessingResource simpleTokeniser = (ProcessingResource) Factory
				.createResource("gate.creole.tokeniser.SimpleTokeniser", params);
		prList.add(simpleTokeniser);

		params = Factory.newFeatureMap();
		ProcessingResource sentenceSplitter = (ProcessingResource) Factory
				.createResource("gate.creole.splitter.SentenceSplitter", params);
		prList.add(sentenceSplitter);

		params = Factory.newFeatureMap();
		params.put("baseSentenceAnnotationType", "Sentence");
		params.put("baseTokenAnnotationType", "Token");
		params.put("outputAnnotationType", "Token");
		ProcessingResource pos = (ProcessingResource) Factory.createResource(
				"gate.creole.POSTagger", params);
		prList.add(pos);

		params = Factory.newFeatureMap();
		// URL u=new
		// File("ie/deri/nlp/LanguageResources/gazetteers/mylists.def").toURL();
		params.put("listsURL", new File(gazetteerFilePath).toURI().toURL());
		params.put("caseSensitive",false);
		DefaultGazetteer gazetteer = (DefaultGazetteer) Factory.createResource(
				"gate.creole.gazetteer.DefaultGazetteer", params);
		prList.add(gazetteer);

		params = Factory.newFeatureMap();
		params.put("grammarURL", new File(japeFilePath).toURI().toURL());
		Transducer relationExtractionTransducer = (Transducer) Factory
				.createResource("gate.creole.Transducer", params);
		prList.add(relationExtractionTransducer);
		if (annieController == null) {
			log.warn("Annie is not loaded");
		} else {
			for (Iterator<ProcessingResource> iterator = prList.iterator(); iterator
					.hasNext();) {

				annieController.add(iterator.next());

			}
		}

	}

	/**
	 * @return the gateHome
	 */
	public String getGateHome() {
		return gateHome;
	}

	/**
	 * @param gateHome
	 *            the gateHome to set
	 */
	public void setGateHome(String gateHome) {
		this.gateHome = gateHome;
	}

	/**
	 * @return the japeFilePath
	 */
	public String getJapeFilePath() {
		return japeFilePath;
	}

	/**
	 * @param japeFilePath
	 *            the japeFilePath to set
	 */
	public void setJapeFilePath(String japeFilePath) {
		this.japeFilePath = japeFilePath;
	}

	/**
	 * @return the gazetteerFilePath
	 */
	public String getGazetteerFilePath() {
		return gazetteerFilePath;
	}

	/**
	 * @param gazetteerFilePath
	 *            the gazetteerFilePath to set
	 */
	public void setGazetteerFilePath(String gazetteerFilePath) {
		this.gazetteerFilePath = gazetteerFilePath;
	}

	/**
	 * @return the annieController
	 */
	public SerialAnalyserController getAnnieController() {
		return annieController;
	}

	/**
	 * @param annieController
	 *            the annieController to set
	 */
	public void setAnnieController(SerialAnalyserController annieController) {
		this.annieController = annieController;
	}

}
