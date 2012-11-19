/**
 * 
 */
package com.poly.nlp.filekommander.file.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.gate.GateBuilder;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;

/**
 * @author jake
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
	
}
