package com.poly.nlp.filekommander.file.actions;

import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory.ConfigurationBuilder;

public class FileKommanderConfiguration {

	private static Configuration configuration ;
	private static String configFileName ;
	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		Properties properties = new Properties();
		ConfigurationBuilder builder = new ConfigurationBuilder();
		//builder.
		return configuration;
	}
	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	/**
	 * @return the configFileName
	 */
	public String getConfigFileName() {
		return configFileName;
	}
	/**
	 * @param configFileName the configFileName to set
	 */
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	
	public static void LoadConfiguration(){
		
	}
	
}
