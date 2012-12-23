package com.poly.nlp.filekommander.file.actions;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;
import gate.util.OffsetComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.ExistsModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.OpenModel;
import com.poly.nlp.filekommander.views.models.PhraseOperationModel;
import com.poly.nlp.filekommander.views.models.RenameModel;
import com.poly.nlp.filekommander.views.models.StatsModel;

/**
 * 
 * @author neha , jake
 * 
 */
public class AnalyseAction {
	private static final Logger log = Logger.getLogger(AnalyseAction.class);

	public static GenericActionModel analyseAction(String actionType,
			Annotation annotation) {
		if (actionType == null)
			return null;
		GenericActionModel returnModel = null;

		switch (actionType) {
		case "close":
			// AnalyseCloseAction(annotation);
			break;
		case "create":
			returnModel = analyseCreateAction(annotation);
			break;
		case "delete":
			returnModel = analyseDeleteAction(annotation);
			break;
		case "exist":
			returnModel = analyseExistsAction(annotation);
			break;
		case "insert":
			returnModel = analysePhraseAction(annotation, actionType);
			break;
		case "open":
			returnModel = analyseOpenAction(annotation);
			break;
		case "remove":
			returnModel = analysePhraseAction(annotation, actionType);
			break;
		case "rename":
			returnModel = analyseRenameAction(annotation);
			break;
		case "replace":
			returnModel = analysePhraseAction(annotation, actionType);
			break;
		case "stats":
			returnModel = analyseStatsAction(annotation, actionType);
			break;
		default:
			break;
		}
		return returnModel;

	}

	private static GenericActionModel analyseCreateAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		CreateModel createModel = new CreateModel();
		if (fileNamesList != null) {
			for (String fileName : fileNamesList) {
				createModel.add(fileName, FileKommander.FILE);
			}
		}
		if (directoryNamesList != null) {
			for (String folderName : directoryNamesList) {
				createModel.add(folderName, FileKommander.DIRECTORY);
			}
		}
		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (!fileNamesList.contains(quotedName)
						&& !directoryNamesList.contains(quotedName))
					createModel.add(quotedName, FileKommander.DIRECTORY);
			}
		}
		log.info("Files to be Created : " + fileNamesList);
		log.info("Directories to be Created : " + directoryNamesList);
		log.info("Files to be Created : " + quotedObjectNamesList);
		return createModel;
	}

	private static GenericActionModel analyseDeleteAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		DeleteModel deleteModel = new DeleteModel();
		if (fileNamesList != null) {
			for (String fileName : fileNamesList) {
				deleteModel.add(fileName, FileKommander.FILE);
			}
		}
		if (directoryNamesList != null) {
			for (String folderName : directoryNamesList) {
				deleteModel.add(folderName, FileKommander.DIRECTORY);
			}
		}
		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (!fileNamesList.contains(quotedName)
						&& !directoryNamesList.contains(quotedName))
					deleteModel.add(quotedName, FileKommander.DIRECTORY);
			}
		}
		log.info("Files to be deleted : " + fileNamesList);
		log.info("Directories to be Created : " + directoryNamesList);
		log.info("Directories to be Created : " + quotedObjectNamesList);
		return deleteModel;
	}

	private static GenericActionModel analyseExistsAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		ExistsModel existsModel = new ExistsModel();
		if (fileNamesList != null) {
			for (String fileName : fileNamesList) {
				existsModel.add(fileName, FileKommander.FILE);
			}
		}
		if (directoryNamesList != null) {
			for (String folderName : directoryNamesList) {
				existsModel.add(folderName, FileKommander.DIRECTORY);
			}
		}
		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (!fileNamesList.contains(quotedName)
						&& !directoryNamesList.contains(quotedName))
					existsModel.add(quotedName, FileKommander.DIRECTORY);
			}
		}

		return existsModel;

	}

	private static GenericActionModel analysePhraseAction(
			Annotation annotation, String actionType) {

		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		ArrayList<String> phraseList = getObjectNameFromAnnotation(featureMap,
				"phraseName");
		PhraseOperationModel phraseOperationModel = new PhraseOperationModel();
		if (actionType.equalsIgnoreCase("insert"))
			phraseOperationModel.setOperationType(FileKommander.INSERT);
		else if (actionType.equalsIgnoreCase("replace"))
			phraseOperationModel.setOperationType(FileKommander.REPLACE);
		else if (actionType.equalsIgnoreCase("remove"))
			phraseOperationModel.setOperationType(FileKommander.REMOVE);
		for (String fileName : fileNamesList) {
			phraseOperationModel.add(fileName);
		}
		// phraseOperationModel.setFileListData(fileNamesList);
		ArrayList<String> repList = getObjectNameFromAnnotation(featureMap,
				"repitition");
		if (!repList.isEmpty()) {
			phraseOperationModel.setRepetition(repList.get(0));
		} else {
			phraseOperationModel.setRepetition("every");
		}

		// phraseOperationModel.set
		ArrayList<String> posList = getObjectNameFromAnnotation(featureMap,
				"position");
		if (!posList.isEmpty()) {
			phraseOperationModel.setPosition(posList.get(0));
		} else {
			phraseOperationModel.setPosition("after");
		}
		// String newPhrase = "";
		// String oldPhrase = "";

		// phraseOperationModel.setRepetition(repetition)

		ArrayList<String> phrases = getObjectNameFromAnnotation(featureMap,
				"quotedObject");
		if (phrases.size() <= 2) {
			if(phraseOperationModel.getOperationType() == FileKommander.INSERT ) {
			phraseOperationModel.setNewPhrase(phrases.get(0));
			phraseOperationModel.setOldPhrase(phrases.get(1));
			}else if(phraseOperationModel.getOperationType() == FileKommander.REMOVE){
			//	phraseOperationModel.setNewPhrase(phrases.get(1));
				phraseOperationModel.setOldPhrase(phrases.get(0));
			}else if(phraseOperationModel.getOperationType() == FileKommander.REPLACE){
				phraseOperationModel.setNewPhrase(phrases.get(1));
				phraseOperationModel.setOldPhrase(phrases.get(0));
			}
		} 
//		else if (phrases.size() == 1) {
//			phraseOperationModel.setNewPhrase(phrases.get(0));
//			phraseOperationModel.setOldPhrase("\\w");
//		}
		else {
			FileKommanderRun.getGuiv2().displayErrorMessage(
					"Multiple phrases found. Not sure what to do.");
			return null;
		}
		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (phraseList.contains(quotedName)) {
					if (phraseOperationModel.getNewPhrase().equals("")) {
						phraseOperationModel.setNewPhrase(quotedName);
					}
				} else {
					if (phraseOperationModel.getOldPhrase().equals("")) {
						phraseOperationModel.setOldPhrase(quotedName);
					}
				}
			}
		}
		return phraseOperationModel;
	}

	private static GenericActionModel analyseInsertAction(Annotation annotation) {

		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		ArrayList<String> phraseList = getObjectNameFromAnnotation(featureMap,
				"phraseName");
		PhraseOperationModel phraseOperationModel = new PhraseOperationModel();
		phraseOperationModel.setOperationType(FileKommander.INSERT);

		phraseOperationModel.setRepetition(getObjectNameFromAnnotation(
				featureMap, "position").get(0));
		// phraseOperationModel.set
		String position = phraseOperationModel.getRepetition();
		// String newPhrase = "";
		// String oldPhrase = "";

		// phraseOperationModel.setRepetition(repetition)

		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (phraseList.contains(quotedName))
					phraseOperationModel.setNewPhrase(quotedName);
				else
					phraseOperationModel.setOldPhrase(quotedName);
			}
		}

		return phraseOperationModel;

	}

	private static GenericActionModel analyseOpenAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		OpenModel openModel = new OpenModel();
		if (fileNamesList != null) {
			for (String fileName : fileNamesList) {
				openModel.add(fileName, FileKommander.FILE);
			}
		}
		if (directoryNamesList != null) {
			for (String folderName : directoryNamesList) {
				openModel.add(folderName, FileKommander.DIRECTORY);
			}
		}
		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (!fileNamesList.contains(quotedName)
						&& !directoryNamesList.contains(quotedName))
					openModel.add(quotedName, FileKommander.DIRECTORY);
			}
		}

		return openModel;

	}

	private static GenericActionModel analyseRemoveAction(Annotation annotation) {
		// TODO
		return null;

	}

	private static GenericActionModel analyseRenameAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		String content = (String) featureMap.get("content");

		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		fileNamesList.addAll(getObjectNameFromAnnotation(featureMap,
				"directoryName"));
		fileNamesList.addAll(getObjectNameFromAnnotation(featureMap,
				"quotedObject"));
		// ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
		// featureMap, "directoryName");
		// ArrayList<String> quotedObjectNamesList =
		// getObjectNameFromAnnotation(
		// featureMap, "quotedObject");
		RenameModel renameModel = new RenameModel();
		if (fileNamesList.size() == 2) {
			renameModel.setOldFileName(fileNamesList.get(0));
			renameModel.setNewFileName(fileNamesList.get(1));
			renameModel.setType(0);
			renameModel.setModelRun(false);
		} else {
			FileKommanderRun
					.getGuiv2()
					.displayErrorMessage(
							"Multiple file/folder names found , unsure which to rename to which");
			return null;
		}
		return renameModel;
	}

	private static GenericActionModel analyseReplaceAction(Annotation annotation) {
		// TODO
		return null;

	}

	private static GenericActionModel analyseStatsAction(Annotation annotation,
			String actionType) {
		StatsModel statsModel = new StatsModel();
		FeatureMap featureMap = annotation.getFeatures();
		ArrayList<String> actionList = getObjectNameFromAnnotation(featureMap,
				"actions");
		String action = "";
		if (!actionList.isEmpty()) {
			action = actionList.get(0);
		} else {
			log.error("No stat action found");
		}

		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		if (fileNamesList != null) {
			for (String fileName : fileNamesList) {
				statsModel.add(fileName, FileKommander.FILE);
			}
		}
		if (directoryNamesList != null) {
			for (String folderName : directoryNamesList) {
				statsModel.add(folderName, FileKommander.DIRECTORY);
			}
		}
		if (quotedObjectNamesList != null) {
			for (String quotedName : quotedObjectNamesList) {
				if (!fileNamesList.contains(quotedName)
						&& !directoryNamesList.contains(quotedName))
					statsModel.add(quotedName, FileKommander.DIRECTORY);
			}
		}
		log.info("Files to be Created : " + fileNamesList);
		log.info("Directories to be Created : " + directoryNamesList);
		log.info("Files to be Created : " + quotedObjectNamesList);
		if (action.equals("count") || action.contains("count")) {
			statsModel.setStatsType(FileKommander.COUNT);

		} else if (action.equals("size") || action.contains("size")) {
			statsModel.setStatsType(FileKommander.SIZEOF);
		} else if (action.equals("list") || action.contains("list")) {
			statsModel.setStatsType(FileKommander.LIST_FILES);
		} else if (action.equals("modified") || action.contains("modified")) {
			statsModel.setStatsType(FileKommander.LAST_MODIFIED);
		}else if (action.equals("list") || action.contains("list")) {
			statsModel.setStatsType(FileKommander.LIST_FILES);
		}
		return statsModel;
	}

	public static ArrayList<String> getObjectNameFromAnnotation(
			FeatureMap featureMap, String key) {
		ArrayList<String> outputList = null;
		if (featureMap.containsKey(key)) {
			outputList = new ArrayList<String>();
			AnnotationSet objectNames = (AnnotationSet) featureMap.get(key);
			ArrayList<Annotation> objectList = new ArrayList<Annotation>(
					objectNames);
			Collections.sort(objectList, new OffsetComparator());
			Iterator<Annotation> iterator = objectList.iterator();
			while (iterator.hasNext()) {
				Annotation object = iterator.next();
				if (object == null) {
					// node =objectNames.nextNode(node) ;
				} else {
					FeatureMap featureMap2 = object.getFeatures();
					String objectName = (String) featureMap2.get("string");
					objectName = objectName.replaceAll("\"", "");
					outputList.add(objectName);
					// node =objectNames.nextNode(node) ;
				}
			}

			// for (Annotation object : objectNames) {
			// FeatureMap featureMap2 = object.getFeatures();
			// String objectName = (String) featureMap2.get("string");
			// objectName = objectName.replaceAll("\"", "");
			// outputList.add(objectName);
			// }
		} else {
			return new ArrayList<String>();
		}

		return outputList.isEmpty() ? new ArrayList<String>() : outputList;

	}

}
