package com.poly.nlp.filekommander;

import gate.util.GateException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	
	
	
	public static void main(String[] args) throws MalformedURLException, GateException, ConfigurationException {
	
		// Load properties file 
//		log.info("Reading Properties File");		
//		Configuration config = null ;
//		try {
//			config = new PropertiesConfiguration("FileKommander.properties");
//		} catch (ConfigurationException e) {
//			log.error("Error reading properties file", e);
//		}
//		
//		String [] pr_list = config.getStringArray("PR_LIST");
//		System.out.println();

	}

}
