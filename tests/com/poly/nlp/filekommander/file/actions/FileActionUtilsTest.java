/**
 * 
 */
package com.poly.nlp.filekommander.file.actions;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jake
 *
 */
public class FileActionUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.poly.nlp.filekommander.file.actions.FileActionUtils#printSize(long)}.
	 */
	@Test
	public void testPrintSize() {
	//	FileActionUtils. ;
		
	}
	
	@Test
	public void testSizeOfDirectory() {
		File f = new File("E:\\1");
		long answer = 1443 ;
		long output = FileActionUtils.sizeofDirectory(f);
		System.out.println(output);
		assertEquals(answer, output, 0);	
		
	}

}
