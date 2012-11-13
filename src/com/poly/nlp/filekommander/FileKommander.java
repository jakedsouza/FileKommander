package com.poly.nlp.filekommander;

import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.beanutils.BeanDeclaration;
import org.apache.commons.configuration.beanutils.BeanHelper;
import org.apache.commons.configuration.beanutils.XMLBeanDeclaration;
import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.gate.GateBuilder;
import com.poly.nlp.filekommander.gate.ProcessingResource;

public class FileKommander {
	
	private static final Logger log = Logger.getLogger(FileKommander.class);
	private final String fileSeparator = System.getProperty("file.separator");
	public static String newline =("\n");

	
	
	
	public static void main(String[] args) throws GateException, ConfigurationException, IOException {
	
		// Load properties file 
		log.info("Reading Properties File");		
		Configuration config = null ;
		try {
			config = new PropertiesConfiguration("FileKommander.properties");
		} catch (ConfigurationException e) {
			log.error("Error reading properties file", e);
		}
		
		String gateHome = config.getString("gateHome");
		String gazetteerFilePath = config.getString("gazetteerFilePath");
		String japeFilePath = config.getString("japeFilePath");
		
		
		GateBuilder gateBuilder = new GateBuilder(gateHome,gazetteerFilePath,japeFilePath);
		gateBuilder.setup();
		gateBuilder.loadANNIE();
		gateBuilder.loadAllProcessingResources();
		SerialAnalyserController a = gateBuilder.getAnnieController();

		File file = new File("language resources/corpus/GoodCorp.txt") ;
		Document doc = Factory.newDocument(getContents(file));
		Corpus corpus =Factory.newCorpus("BatchProcessApp Corpus");;
		corpus.add(doc);
		a.setCorpus(corpus);
		a.execute();
		corpus.clear();  
	
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

}