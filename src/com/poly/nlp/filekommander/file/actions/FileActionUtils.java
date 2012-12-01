/**
 * 
 */
package com.poly.nlp.filekommander.file.actions;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;

/**
 * @author jake , neha
 * 
 */
public class FileActionUtils {
	private static final Logger log = Logger.getLogger(FileActionUtils.class);

	public static void CloseAction() {
		log.info("CloseAction action called");
	}

	public static void CreateAction() {
		log.info("CreateAction action called");
	}

	public static void DeleteAction() {
		log.info("DeleteAction action called");
	}

	public static void ExistsAction() {
		log.info("ExistsAction action called");
	}

	public static void InsertAction() {
		log.info("InsertAction action called");
	}

	public static void OpenAction() {
		log.info("OpenAction action called");
	}

	public static void RemoveAction() {
		log.info("RemoveAction action called");
	}

	public static void RenameAction() {
		log.info("RenameAction action called");
	}

	public static void ReplaceAction() {
		log.info("ReplaceAction action called");
	}

	public static void StatsAction() {
		log.info("StatsAction action called");
	}

	public static void callAction(String actionType, Annotation annotation) {
		if(actionType == null )
			return;
		FeatureMap featureMap = annotation.getFeatures();
		AnnotationSet directoryName =  (AnnotationSet)featureMap.get("directoryName");
		AnnotationSet fileName =  (AnnotationSet)featureMap.get("fileName");
		AnnotationSet phraseName =  (AnnotationSet)featureMap.get("phraseName");
		AnnotationSet quotedObject =  (AnnotationSet)featureMap.get("quotedObject");
		String content =  (String)featureMap.get("content");		
		
		
		
		switch (actionType) {
		case "close":
			CloseAction();
			break;
		case "create":
			CreateAction();
			break;
		case "delete":
			DeleteAction();
			break;
		case "exists":
			ExistsAction();
			break;
		case "insert":
			InsertAction();
			break;
		case "open":
			OpenAction();
			break;
		case "remove":
			RemoveAction();
			break;
		case "rename":
			RenameAction();
			break;
		case "replace":
			ReplaceAction();
			break;
		case "stats":
			StatsAction();
			break;
		default:
			break;
		}
		
		
	}
	
	public static void create(String name , int type){
		// if type == directory create directory else create file , if file name is null 
		// or has illegal characters , display error message working directory in FileKommander.getWotkingdir 
		// TODO get type from FileKommander.FILE/DIRECTORY ;
	}
	
	public static void delete(String name , int type){
		
	}
	
	public static void exists(String name , int type){
		
	}
	
	public static boolean open(String name){
		String errorMsg = "";
		if(name.equals("") || name.equals("")){
			errorMsg = "File Name is empty";
			return false;
		}
		// String workingDirectory = FileKommanderRun.getKommander().getWorkingDirectory();
	    String workingDirectory = "testDir/";
		File file = new File(name);
		 if(!file.exists()){
		    	errorMsg = "File " + name + " doesnot exist in the directory " ;
		    	FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
		    	return false;
		    }
		try
	    {
	        if (OSDetector.isWindows())
	        {
	        	System.out.println(file.getAbsolutePath());
	        	String [] command = new String[]{"rundll32.exe", "url.dll,FileProtocolHandler",file.getAbsolutePath()};
	            System.out.println(Arrays.toString(command));
	        	Runtime.getRuntime().exec(command);
	            return true;
	        } else if (OSDetector.isLinux() || OSDetector.isMac())
	        {
	            Runtime.getRuntime().exec(new String[]{"/usr/bin/open",
	                                                   file.getAbsolutePath()});
	            return true;
	        } else
	        {
	            // Unknown OS, try with desktop
	            if (Desktop.isDesktopSupported())
	            {
	                Desktop.getDesktop().open(file);
	                return true;
	            }
	            else
	            {
	                return false;
	            }
	        }
	    } catch (Exception e)
	    {
	        e.printStackTrace(System.err);
	        return false;
	    }
	}
	
	public static void rename(String oldName, String newName){
		String errorMsg = "";
		if(oldName.equals(null) || oldName.equals("") || newName.equals(null) || newName.equals("")){
			errorMsg = "Rename action failed as the new or old names were empty" ; 
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return ;
		}
		// String workingDirectory = FileKommanderRun.getKommander().getWorkingDirectory();
		String workingDirectory = "testDir/";
		// File (or directory) with old name
	    File file = new File(workingDirectory + oldName);
	    
	    if(!file.exists()){
	    	errorMsg = "File " + oldName+ "doesnot exist in the directory " ;
	    	FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
	    	return ;
	    }
	    
	    // File (or directory) with new name
	    File file2 = new File(workingDirectory + newName);
	    if(file2.exists()) {
	    	errorMsg = "File " + newName + " exists in the directory " ;
	    	FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
	    	return ;
	    }
	    // Rename file (or directory)
	    boolean success = file.renameTo(file2);
	    if (!success) {
	    	errorMsg = "Error renaming file" ;
	    	FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
	    }else{
	    	System.out.println("Rename successfull");
	    }

		
	}
		
	
}
