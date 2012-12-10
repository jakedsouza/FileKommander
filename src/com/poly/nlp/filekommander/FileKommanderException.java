/**
 * 
 */
package com.poly.nlp.filekommander;

import org.apache.log4j.Logger;


/**
 * @author neha , jake
 *
 */
public class FileKommanderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FileKommanderException.class);

	
	FileKommanderException(String message){	
		log.error(message);
	}
	
}
