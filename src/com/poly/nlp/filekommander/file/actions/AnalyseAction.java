package com.poly.nlp.filekommander.file.actions;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.RenameModel;
import com.poly.nlp.filekommander.views.models.StatsModel;

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
		case "exists":
			returnModel = analyseExistsAction(annotation);
			break;
		case "insert":
			returnModel = analyseInsertAction(annotation);
			break;
		case "open":
			returnModel = analyseOpenAction(annotation);
			break;
		case "remove":
			returnModel = analyseRemoveAction(annotation);
			break;
		case "rename":
			returnModel = analyseRenameAction(annotation);
			break;
		case "replace":
			returnModel = analyseReplaceAction(annotation);
			break;
		case "stats":
			returnModel = analyseStatsAction(annotation,actionType);
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
		//TODO
		return null;

	}

	private static GenericActionModel analyseInsertAction(Annotation annotation) {
		//TODO
		return null;

	}

	private static GenericActionModel analyseOpenAction(Annotation annotation) {
		//TODO
		return null;

	}

	private static GenericActionModel analyseRemoveAction(Annotation annotation) {
		//TODO
		return null;

	}

	private static GenericActionModel analyseRenameAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		String content = (String) featureMap.get("content");
		
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		fileNamesList.addAll(getObjectNameFromAnnotation(
				featureMap, "directoryName"));
		fileNamesList.addAll(getObjectNameFromAnnotation(
				featureMap, "quotedObject"));
//		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
//				featureMap, "directoryName");
//		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
//				featureMap, "quotedObject");
		RenameModel renameModel = new RenameModel();
		if(fileNamesList.size() == 2){
			renameModel.setOldFileName(fileNamesList.get(0));
			renameModel.setNewFileName(fileNamesList.get(1));
			renameModel.setType(0);
			renameModel.setModelRun(false);
		}else{
			FileKommanderRun.getGuiv2().displayErrorMessage("Multiple file/folder names found , unsure which to rename to which");
			return null ;
		}
		return renameModel;
	}

	private static GenericActionModel analyseReplaceAction(Annotation annotation) {
		//TODO
		return null;

	}

	private static GenericActionModel analyseStatsAction(Annotation annotation, String actionType) {
//		StatsModel statsModel = new StatsModel();
//        FeatureMap featureMap = annotation.getFeatures();
//        AnnotationSet actions = (AnnotationSet) featureMap.get("actions");
//        Annotation action  = null ;
//        Iterator iterator = actions.iterator() ;
//        String statType = "" ; 
//        while(iterator.hasNext()){
//        	action = (Annotation) iterator.next() ; 
//        	FeatureMap featureMap2 = action.getFeatures() ;
//        	if(featureMap2.get("minorType").equals("stats")){
//        		statType  = ((String) featureMap2.get("string")).toLowerCase(); 
//        	}
//        }
////        String action = ((String)((Annotation)featureMap.get("actions")).getFeatures().get("string")).toLowerCase();
//        ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
//                        featureMap, "fileName");
//        ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
//                        featureMap, "directoryName");
//        ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
//                        featureMap, "quotedObject");
//        if (fileNamesList != null) {
//                for (String fileName : fileNamesList) {
//                        statsModel.add(fileName, FileKommander.FILE);
//                }
//        }
//        if (directoryNamesList != null) {
//                for (String folderName : directoryNamesList) {
//                        statsModel.add(folderName, FileKommander.DIRECTORY);
//                }
//        }
//        if (quotedObjectNamesList != null) {
//                for (String quotedName : quotedObjectNamesList) {
//                        if (!fileNamesList.contains(quotedName)
//                                        && !directoryNamesList.contains(quotedName))
//                                statsModel.add(quotedName, FileKommander.DIRECTORY);
//                }
//        }
//        log.info("Files to be analysed : " + fileNamesList);
//        log.info("Directories to be analysed: " + directoryNamesList);
//        log.info("Files to be abalysed : " + quotedObjectNamesList);
//
//
//        if(statType.equals("count") || statType.contains("count") ){
//                statsModel.setStatsType(FileKommander.COUNT);
//               
//        }else if(statType.equals("size") || statType.contains("size") ){
//                statsModel.setStatsType(FileKommander.SIZEOF);
//        }else if(statType.equals("list") || statType.contains("list") ){
//                statsModel.setStatsType(FileKommander.LIST_FILES);
//        }else if(statType.equals("modified") || statType.contains("modified") ){
//                statsModel.setStatsType(FileKommander.LAST_MODIFIED);
//        }
//        return statsModel;

		StatsModel statsModel = new StatsModel();
		FeatureMap featureMap = annotation.getFeatures();
		String action = ((String)((Annotation)featureMap.get("action")).getFeatures().get("string")).toLowerCase();
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
	//	String actionType= ((String)action.getFeatures().get("string")).toLowerCase();

		if(action.equals("count") || action.contains("count") ){
			statsModel.setStatsType(FileKommander.COUNT);
			
		}else if(action.equals("size") || action.contains("size") ){
			statsModel.setStatsType(FileKommander.SIZEOF);
		}else if(action.equals("list") || action.contains("list") ){
			statsModel.setStatsType(FileKommander.LIST_FILES);
		}else if(action.equals("modified") || action.contains("modified") ){
			statsModel.setStatsType(FileKommander.LAST_MODIFIED);
		}
		return statsModel;
	}

	public static ArrayList<String> getObjectNameFromAnnotation(
			FeatureMap featureMap, String key) {
		ArrayList<String> outputList = null;
		if (featureMap.containsKey(key)) {
			outputList = new ArrayList<String>();
			AnnotationSet objectNames = (AnnotationSet) featureMap.get(key);
			for (Annotation object : objectNames) {
				FeatureMap featureMap2 = object.getFeatures();
				String objectName = (String) featureMap2.get("string");
				objectName = objectName.replaceAll("\"", "");
				outputList.add(objectName);
			}
		} else {
			return new ArrayList<String>();
		}

		return outputList.isEmpty() ? new ArrayList<String>() : outputList;

	}


}
