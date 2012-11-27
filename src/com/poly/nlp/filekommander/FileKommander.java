package com.poly.nlp.filekommander;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.annotation.ImmutableAnnotationSetImpl;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.beanutils.BeanDeclaration;
import org.apache.commons.configuration.beanutils.BeanHelper;
import org.apache.commons.configuration.beanutils.XMLBeanDeclaration;
import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.file.actions.FileActionUtils;
import com.poly.nlp.filekommander.gate.GateBuilder;
import com.poly.nlp.filekommander.gate.ProcessingResource;

public class FileKommander implements Runnable{	
	private static final Logger log = Logger.getLogger(FileKommander.class);
	private final String fileSeparator = System.getProperty("file.separator");
	public static String newline =("\n");	
	private String gateHome ;
	private String gazetteerFilePath ;
	private String japeFilePath ; 
	private SerialAnalyserController annie; 
	private GateBuilder gateBuilder;
	private String userInputText ;
	/**
	 * @return the gateHome
	 */
	public String getGateHome() {
		return gateHome;
	}

	/**
	 * @param gateHome the gateHome to set
	 */
	public void setGateHome(String gateHome) {
		this.gateHome = gateHome;
	}

	/**
	 * @return the gazetteerFilePath
	 */
	public String getGazetteerFilePath() {
		return gazetteerFilePath;
	}

	/**
	 * @param gazetteerFilePath the gazetteerFilePath to set
	 */
	public void setGazetteerFilePath(String gazetteerFilePath) {
		this.gazetteerFilePath = gazetteerFilePath;
	}

	/**
	 * @return the japeFilePath
	 */
	public String getJapeFilePath() {
		return japeFilePath;
	}

	/**
	 * @param japeFilePath the japeFilePath to set
	 */
	public void setJapeFilePath(String japeFilePath) {
		this.japeFilePath = japeFilePath;
	}

	/**
	 * @return the gateBuilder
	 */
	public GateBuilder getGateBuilder() {
		return gateBuilder;
	}

	/**
	 * @param gateBuilder the gateBuilder to set
	 */
	public void setGateBuilder(GateBuilder gateBuilder) {
		this.gateBuilder = gateBuilder;
	}

	/**
	 * @return the annie
	 */
	public SerialAnalyserController getAnnie() {
		return annie;
	}

	/**
	 * @param annie the annie to set
	 */
	public void setAnnie(SerialAnalyserController annie) {
		this.annie = annie;
	}

//	public static void main1(String[] args) throws GateException, ConfigurationException, IOException {
//		// Read args to run in cli mode or in GUI mode 
//		// If CLI - Run CLI CODE 
//		// ELSE IF GUI Run GUI Code if no args run GUI . 
//		//BOth the codes must be independent and s
//		// SHOULD CAll the same Functions it
//		
//		// Load properties file 		
////		FileKommander kommander = new FileKommander(); 
////		Configuration config  = kommander.loadConfig("FileKommander.properties");
////		
////		kommander.setGateHome(config.getString("gateHome"));
////		kommander.setGazetteerFilePath(config.getString("gazetteerFilePath"));
////		kommander.setJapeFilePath(config.getString("japeFilePath"));
////		
//		
////		kommander.gateBuilder = kommander.initGATE(kommander.getGateHome(),kommander.getGazetteerFilePath(),kommander.getJapeFilePath());
//		
////		kommander.annie = kommander.initANNIE();
//
//		File file = new File("language resources/corpus/GoodCorp.txt") ;
//		Document doc = Factory.newDocument(getContents(file));
//		Corpus corpus =Factory.newCorpus("BatchProcessApp Corpus");;
//		corpus.add(doc);
////		kommander.annie.setCorpus(corpus);
////		kommander.annie.execute();
//		AnnotationSet defaultAnnotSet = doc.getAnnotations();	
//		AnnotationSet all = defaultAnnotSet.get("all");
//		for (Annotation annotation : all) {
//			FeatureMap featureMap = annotation.getFeatures();
//			AnnotationSet actionsAnnotation =  (AnnotationSet)featureMap.get("actions");
//		for(Annotation annot2 : actionsAnnotation){
//			FeatureMap featureMap2 = annot2.getFeatures();
//			String actionType = (String)featureMap2.get("minorType");
//			FileActionUtils.callAction(actionType,annotation);			
//		}
//		
//		}
//		
//		
//		corpus.clear();  
//	
//	}
	
	public void cliMode(){
		
	}	
	public void guiMode(){
		
	}
	
	public  Configuration loadConfig(String configFileName) throws ConfigurationException{
		if(configFileName == null || configFileName == ""){
			throw new ConfigurationException("Config file name is empty");
		}
		log.info("Reading Properties File" + configFileName);		
		Configuration config = null ;
		try {
			config = new PropertiesConfiguration(configFileName);
		} catch (ConfigurationException e) {
			log.error("Error reading properties file", e);
		}
		return config;
	}
	
	public GateBuilder initGATE() throws MalformedURLException, GateException{
		GateBuilder gateBuilder = new GateBuilder(this.gateHome,this.gazetteerFilePath,this.japeFilePath);
		gateBuilder.setup();
		this.gateBuilder = gateBuilder;
		return gateBuilder; 
	}
	
	public SerialAnalyserController initANNIE() throws ResourceInstantiationException, MalformedURLException{
		if(this.getGateBuilder() == null){
			throw new RuntimeException("Gate must be initialised");
		}
		this.getGateBuilder().loadANNIE();
		this.getGateBuilder().loadAllProcessingResources();
		return  this.getGateBuilder().getAnnieController();
		
	}
	
	public String getData(){
		return null ;
	}
	
	public String getDataMIC(){
		return null ;
	}
	
	public String getDataTextBox(){
		return null;
	}
	
	public String getDataCLI(){
		return null;
	}
	
	public void printMessage(){
		// Print to log , gui , cli 
	}
	
	public void executeAction(){
		
	}
	
	public static String getContents(File aFile) {
		// ...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		// declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			input = new BufferedReader(new FileReader(aFile));
			String line = null; // not declared within while loop
			/*
			 * readLine is a bit quirky : it returns the content of a line MINUS
			 * the newline. it returns null only for the END of the stream. it
			 * returns an empty String if two newlines appear in a row.
			 */
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

	/**
	 * @return the userInputText
	 */
	public String getUserInputText() {
		return userInputText;
	}

	/**
	 * @param userInputText the userInputText to set
	 */
	public void setUserInputText(String userInputText) {
		this.userInputText = userInputText;
	}

	@Override
	public void run() {
		Thread thread = new  Thread(this);		
		System.out.println(this.userInputText);
		Document doc = null ;
		Corpus corpus = null ;
		try {
			 doc = Factory.newDocument(this.userInputText);
				corpus=Factory.newCorpus("BatchProcessApp Corpus");;

		} catch (ResourceInstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		corpus.add(doc);
		this.annie.setCorpus(corpus);
		try {
			this.annie.execute();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnnotationSet defaultAnnotSet = doc.getAnnotations();	
		AnnotationSet all = defaultAnnotSet.get("all");
		for (Annotation annotation : all) {
			FeatureMap featureMap = annotation.getFeatures();
			AnnotationSet actionsAnnotation =  (AnnotationSet)featureMap.get("actions");
		for(Annotation annot2 : actionsAnnotation){
			FeatureMap featureMap2 = annot2.getFeatures();
			String actionType = (String)featureMap2.get("minorType");
			FileActionUtils.callAction(actionType,annotation);			
		}
		
		}
		
		
		corpus.clear();  

}
}
