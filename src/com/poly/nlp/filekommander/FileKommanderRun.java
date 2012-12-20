/**
 * 
 */
package com.poly.nlp.filekommander;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;
import gate.util.GateException;
import java.awt.EventQueue;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.file.actions.AnalyseAction;
import com.poly.nlp.filekommander.file.actions.FileActionUtils;
import com.poly.nlp.filekommander.views.FileKommanderGUIV2;
import com.poly.nlp.filekommander.views.models.GenericActionModel;

/**
 * @author jake
 * 
 */
public class FileKommanderRun {
	private static FileKommander kommander;
	private static FileKommanderGUIV2 guiv2;
	private static PropertiesConfiguration configuration;
	private static final Logger log = Logger.getLogger(FileKommanderRun.class);

	/**
	 * @param args
	 * @throws ConfigurationException
	 * @throws GateException
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws ConfigurationException,
			MalformedURLException, GateException {
		// create a new commander object
		kommander = new FileKommander();
		// configuration = new Con
		configuration = loadConfigurationFromFile("/com/poly/nlp/filekommander/resources/FileKommander.properties");
		kommander.setGateHome(configuration.getString("gateHome"));
		kommander.setGazetteerFilePath(configuration
				.getString("gazetteerFilePath"));
		kommander.setJapeFilePath(configuration.getString("japeFilePath"));
		kommander.setWorkingDirectory(configuration
				.getString("workingDirectory"));
		FileActionUtils.setWorkingDirectory(configuration
				.getString("workingDirectory"));
		kommander.initGATE();
		kommander.setAnnie(kommander.initANNIE());
		File workingDir = new File(kommander.getWorkingDirectory());
		boolean success = false;
		if (!workingDir.exists()) {
			success = workingDir.mkdirs();
		}
		if (!success) {
			log.error("Working dir is not set correctly");
			// System.exit(-1);
		}
		// SwingLogAppender swingLogAppender =
		// new SwingLogAppender(
		// new PatternLayout("[%d{HH:mm:ss,SSS}][%p][%t] %l %m%n"));
		// swingLogAppender.setThreshold(Level.ALL);
		// Logger.getRootLogger().addAppender(swingLogAppender);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiv2 = new FileKommanderGUIV2();
					guiv2.getFrmFileKommander().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * 
	 */
	public static void analyseTextInput() {
		// showProgress();
		String text = guiv2.getInputTextFld().getText();
		// guiv2.getOutputLbl().setText("You Pressed - " + text);
		kommander.setUserInputText(text);
		AnnotationSet allAnnotations = kommander.analyseText(text);
		// kommander.run();
		if (allAnnotations == null || allAnnotations.isEmpty()) {
			log.warn("NO data received");
			return;
		}
		for (Annotation annotation : allAnnotations) {
			FeatureMap featureMap = annotation.getFeatures();
			AnnotationSet actionsAnnotation = (AnnotationSet) featureMap
					.get("actions");
			if (actionsAnnotation == null || actionsAnnotation.isEmpty()) {
				log.warn("NO actions received");
				return;
			}
			for (Annotation annot2 : actionsAnnotation) {
				FeatureMap featureMap2 = annot2.getFeatures();
				String actionType = (String) featureMap2.get("minorType");

				// For each action analyse the annotatin to get the annotation
				// model
				GenericActionModel actionModel = AnalyseAction.analyseAction(
						actionType, annotation);
				if (actionModel == null) {
					return;
				} else {
					guiv2.updateInformation(actionModel);
				}
			}

		}
	}

	/**
	 * @return the kommander
	 */
	public static FileKommander getKommander() {
		return kommander;
	}

	/**
	 * @param kommander
	 *            the kommander to set
	 */
	public static void setKommander(FileKommander kommander) {
		FileKommanderRun.kommander = kommander;
	}

	/**
	 * @return the guiv2
	 */
	public static FileKommanderGUIV2 getGuiv2() {
		return guiv2;
	}

	/**
	 * @param guiv2
	 *            the guiv2 to set
	 */
	public static void setGuiv2(FileKommanderGUIV2 guiv2) {
		FileKommanderRun.guiv2 = guiv2;
	}

	/**
	 * @return the configuration
	 */
	public static PropertiesConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration
	 *            the configuration to set
	 */
	public static void setConfiguration(PropertiesConfiguration configuration) {
		FileKommanderRun.configuration = configuration;
	}

	public static PropertiesConfiguration loadConfigurationFromFile(
			String configFileName) throws ConfigurationException {
		if (configFileName == null || configFileName == "") {
			throw new ConfigurationException("Config file name is empty");
		}
		log.info("Reading Properties File" + configFileName);
		PropertiesConfiguration config = null;
		try {
			URL configurl = FileKommanderRun.class.getResource(configFileName);
			// File file = new File("/filekommander.properties");
			// if(file.exists()){
			config = new PropertiesConfiguration(configurl);
			// }else{
			// config = new PropertiesConfiguration(configurl);
			// config.save(file);
			// config.setFile(file);

			// }

			// config.setAutoSave(true);
		} catch (ConfigurationException e) {
			log.error("Error reading properties file", e);
		}
		return config;
	}

	public static void writeConfigurationToFile(
			PropertiesConfiguration configuration) {

	}

	public static synchronized void showProgress() {
		MySwingWorker worker = new MySwingWorker();
		worker.execute();
	}

}

class MySwingWorker extends SwingWorker<Integer, String> {

	protected Integer doInBackground() throws Exception {
		JProgressBar progressBar = FileKommanderRun.getGuiv2().getProgressBar();
		// JLabel rejectActionButton = FileKommanderRun.getGuiv2()
		// .getRejectActionBtn();
		// JLabel acceptActionButton = FileKommanderRun.getGuiv2()
		// .getAcceptActionBtn();
		progressBar.setVisible(true);
		// rejectActionButton.setVisible(true);
		// acceptActionButton.setVisible(true);

		for (int i = 0; i <= 100; i++) {
			progressBar.setValue(i);
			Thread.sleep(50);
		}
		progressBar.setVisible(false);
		// rejectActionButton.setVisible(false);
		// acceptActionButton.setVisible(false);
		progressBar.setValue(0);

		return null;
	}
}
