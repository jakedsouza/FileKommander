/**
 * 
 */
package com.poly.nlp.filekommander.file.actions;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.StatsModel;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;

/**
 * @author jake , neha
 * 
 */
public class FileActionUtilsaa {
	private static final Logger log = Logger.getLogger(FileActionUtils.class);
	private static String workingDirectory ;
//	private static GenericActionModel analyseCreateAction(Annotation annotation) {
//		FeatureMap featureMap = annotation.getFeatures();
//		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
//				featureMap, "fileName");
//		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
//				featureMap, "directoryName");
//		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
//				featureMap, "quotedObject");
//		CreateModel createModel = new CreateModel();
//		if (fileNamesList != null) {
//			for (String fileName : fileNamesList) {
//				createModel.add(fileName, FileKommander.FILE);
//			}
//		}
//		if (directoryNamesList != null) {
//			for (String folderName : directoryNamesList) {
//				createModel.add(folderName, FileKommander.DIRECTORY);
//			}
//		}
//		if (quotedObjectNamesList != null) {
//			for (String quotedName : quotedObjectNamesList) {
//				if (!fileNamesList.contains(quotedName)
//						&& !directoryNamesList.contains(quotedName))
//					createModel.add(quotedName, FileKommander.DIRECTORY);
//			}
//		}
//		log.info("Files to be Created : " + fileNamesList);
//		log.info("Directories to be Created : " + directoryNamesList);
//		log.info("Files to be Created : " + quotedObjectNamesList);
//		return createModel;
//	}
//
//	private static GenericActionModel analyseDeleteAction(Annotation annotation) {
//		FeatureMap featureMap = annotation.getFeatures();
//		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
//				featureMap, "fileName");
//		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
//				featureMap, "directoryName");
//		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
//				featureMap, "quotedObject");
//		DeleteModel deleteModel = new DeleteModel();
//		if (fileNamesList != null) {
//			for (String fileName : fileNamesList) {
//				deleteModel.add(fileName, FileKommander.FILE);
//			}
//		}
//		if (directoryNamesList != null) {
//			for (String folderName : directoryNamesList) {
//				deleteModel.add(folderName, FileKommander.DIRECTORY);
//			}
//		}
//		if (quotedObjectNamesList != null) {
//			for (String quotedName : quotedObjectNamesList) {
//				if (!fileNamesList.contains(quotedName)
//						&& !directoryNamesList.contains(quotedName))
//					deleteModel.add(quotedName, FileKommander.DIRECTORY);
//			}
//		}
//		log.info("Files to be deleted : " + fileNamesList);
//		log.info("Directories to be Created : " + directoryNamesList);
//		log.info("Directories to be Created : " + quotedObjectNamesList);
//		return deleteModel;
//	}
//
//	private static GenericActionModel analyseExistsAction(Annotation annotation) {
//		//TODO
//		return null;
//
//	}
//
//	private static GenericActionModel analyseInsertAction(Annotation annotation) {
//		//TODO
//		return null;
//
//	}
//
//	private static GenericActionModel analyseOpenAction(Annotation annotation) {
//		//TODO
//		return null;
//
//	}
//
//	private static GenericActionModel analyseRemoveAction(Annotation annotation) {
//		//TODO
//		return null;
//
//	}
//
//	private static GenericActionModel analyseRenameAction(Annotation annotation) {
//		FeatureMap featureMap = annotation.getFeatures();
//		String content = (String) featureMap.get("content");
//		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
//				featureMap, "fileName");
//		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
//				featureMap, "directoryName");
//		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
//				featureMap, "quotedObject");
//
//		boolean isRenameFile = (fileNamesList.size() == 2)
//				|| (quotedObjectNamesList.size() == 2)
//				|| (fileNamesList.size() + quotedObjectNamesList.size() == 2);
//		if (isRenameFile) {
//
//			log.info("");
//		} else {
//
//		}
//		return null;
//		//TODO
//	}
//
//	private static GenericActionModel analyseReplaceAction(Annotation annotation) {
//		//TODO
//		return null;
//
//	}
//
//	private static GenericActionModel analyseStatsAction(Annotation annotation, String actionType) {
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
//
//	}

//	private static GenericActionModel createAction(CreateModel createModel) {
//		log.info("CreateAction action called for model" + createModel);
//		HashMap<String, String> fileListData = createModel.getFileListData();
//		HashMap<String, String> folderListData = createModel
//				.getFolderListData();
//
//		for (String fileName : fileListData.keySet()) {
//			String message = create(fileName, FileKommander.FILE);
//			fileListData.put(fileName, message);
//		}
//		for (String folderName : folderListData.keySet()) {
//			String message = create(folderName, FileKommander.DIRECTORY);
//			folderListData.put(folderName, message);
//		}
//		createModel.setModelRun(true);
//		createModel.setFileListData(fileListData);
//		createModel.setFolderListData(folderListData);
//		return createModel;
//	}
//
//	private static DeleteModel deleteAction(DeleteModel deleteModel) {
//		log.info("DeleteAction action called for model" + deleteModel);
//		HashMap<String, String> fileListData = deleteModel.getFileListData();
//		HashMap<String, String> folderListData = deleteModel
//				.getFolderListData();
//
//		for (String fileName : fileListData.keySet()) {
//			String message = create(fileName, FileKommander.FILE);
//			fileListData.put(fileName, message);
//		}
//		for (String folderName : folderListData.keySet()) {
//			String message = create(folderName, FileKommander.DIRECTORY);
//			folderListData.put(folderName, message);
//		}
//		deleteModel.setModelRun(true);
//		deleteModel.setFileListData(fileListData);
//		deleteModel.setFolderListData(folderListData);
//		return deleteModel	;
//	}
//
//	public static void existsAction() {
//		log.info("ExistsAction action called");
//	}
//
//	public static void insertAction() {
//		log.info("InsertAction action called");
//	}
//
//	public static void openAction() {
//		log.info("OpenAction action called");
//	}
//
//	public static void removeAction() {
//		log.info("RemoveAction action called");
//	}
//
//	public static void renameAction() {
//		log.info("RenameAction action called");
//	}
//
//	public static void replaceAction() {
//		log.info("ReplaceAction action called");
//	}
//
//	public static GenericActionModel statsAction(StatsModel actionModel) {
//		log.info("StatsAction action called");
//		return actionModel;
//	}

//	public static GenericActionModel analyseAction(String actionType,
//			Annotation annotation) {
//		if (actionType == null)
//			return null;
//		GenericActionModel returnModel = null;
//
//		switch (actionType) {
//		case "close":
//			// AnalyseCloseAction(annotation);
//			break;
//		case "create":
//			returnModel = analyseCreateAction(annotation);
//			break;
//		case "delete":
//			returnModel = analyseDeleteAction(annotation);
//			break;
//		case "exists":
//			returnModel = analyseExistsAction(annotation);
//			break;
//		case "insert":
//			returnModel = analyseInsertAction(annotation);
//			break;
//		case "open":
//			returnModel = analyseOpenAction(annotation);
//			break;
//		case "remove":
//			returnModel = analyseRemoveAction(annotation);
//			break;
//		case "rename":
//			returnModel = analyseRenameAction(annotation);
//			break;
//		case "replace":
//			returnModel = analyseReplaceAction(annotation);
//			break;
//		case "stats":
//			returnModel = analyseStatsAction(annotation,actionType);
//			break;
//		default:
//			break;
//		}
//		return returnModel;
//
//	}

//	public static GenericActionModel callAction(GenericActionModel actionModel) {
//		log.info("Checking which action to call in callAction function ");
//		GenericActionModel newActionModel = null;
//		if (actionModel == null)
//			return null;
//
//		if (actionModel instanceof CreateModel) {
//			newActionModel = createAction((CreateModel) actionModel);
//		} else if (actionModel instanceof DeleteModel) {
//			newActionModel =	deleteAction((DeleteModel) actionModel);
//		} else if(actionModel instanceof StatsModel){
//			newActionModel =	statsAction((StatsModel) actionModel);
//		}else {
//			existsAction();
//			insertAction();
//			openAction();
//			removeAction();
//			renameAction();
//			replaceAction();
//		//	statsAction();
//
//		}
//		return newActionModel;
//	}

//	private static String create(String name, int type) {
//		String errorMsg = "";
//		name.replaceAll("^[.\\\\/:*?\"<>|]?[\\\\/:*?\"<>|]*", "");
//		try {
//			if (name.length() == 0) {
//				errorMsg = "File named "
//						+ name
//						+ " is of zero length after removing special characters";
//			}else if (type == FileKommander.FILE) {
//				File file = new File(workingDirectory + name);
//				if (file.exists()) {
//					errorMsg = "File : " + name + " already exists";
//					log.error(errorMsg);
//				} else {
//					file.createNewFile();
//					log.info("file created successfully.");
//				}
//			} else if (type == FileKommander.DIRECTORY) {
//				Boolean success = new File(workingDirectory+ name).mkdirs();
//				if (!success) {
//					errorMsg = "Could not create folder : " + name;
//					log.error(errorMsg);
//				} else {
//					log.info("Folder created successfully");
//				}
//			}
//
//		} catch (Exception e) {
//			log.error(e);
//		}
//		return errorMsg;
//	}
//
//	private static String delete(String name, int type) {
//		String errorMsg = "";
//
//		log.info("name of object is" + name);
//		log.info("type of object is" + type);
//		try {
//			if (type == FileKommander.FILE) {
//				File file = new File(workingDirectory + name);
//				if (file.exists()) {
//					Boolean success = file.delete();
//					if (!success) {
//						errorMsg = "Could not delete the file " + name;
//						log.info(errorMsg);
//					} else {
//						log.info("deleted file successfully");
//					}
//				} else {
//					errorMsg = "This file " + name +" doesn't exist. Hence can't be deleted";
//					log.info(errorMsg);
//				}
//
//			} else if (type == FileKommander.DIRECTORY) {
//				File directory = new File(workingDirectory+ name);
//
//				if (directory.isDirectory()) {
//					// if directory is empty
//					if (directory.list().length == 0) {
//						directory.delete();
//						System.out.println("directory deleted successfully");
//					} else {
//						String files[] = directory.list();
//						for (String temp : files) {
//							// construct the file structure
//							File fileDelete = new File(directory, temp);
//
//							// recursive delete
//							fileDelete.delete();
//						}
//
//						// check the directory again, if empty then delete it
//						if (directory.list().length == 0) {
//							directory.delete();
//							System.out
//									.println("directory deleted successfully "
//											+ directory.getAbsolutePath());
//						}
//					}
//
//				}
//			}
//			// System.out.println("error is .."+errorMsg);
//		} catch (Exception e) {
//			errorMsg = e.getLocalizedMessage();
//			e.printStackTrace();
//		}
//		return errorMsg;
//	}
//
//	public static void exists(String name) {
//		String errorMsg = "";
//
//		System.out.println("name of object is" + name);
//		try {
//			File file = new File("testDir/" + name);
//			if (file.exists()) {
//				System.out.println("File/Folder exists!");
//			} else {
//				errorMsg = "File/Folder " + name + " doesn't exist!";
//				System.out.println(errorMsg);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static boolean open(String name) {
//		String errorMsg = "";
//		if (name.equals("") || name.equals("")) {
//			errorMsg = "File Name is empty";
//			return false;
//		}
//		// String workingDirectory =
//		// FileKommanderRun.getKommander().getWorkingDirectory();
//		String workingDirectory = "testDir/";
//		File file = new File(name);
//		if (!file.exists()) {
//			errorMsg = "File " + name + " doesnot exist in the directory ";
//			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
//			return false;
//		}
//		try {
//			if (OSDetector.isWindows()) {
//				System.out.println(file.getAbsolutePath());
//				String[] command = new String[] { "rundll32.exe",
//						"url.dll,FileProtocolHandler", file.getAbsolutePath() };
//				System.out.println(Arrays.toString(command));
//				Runtime.getRuntime().exec(command);
//				return true;
//			} else if (OSDetector.isLinux() || OSDetector.isMac()) {
//				Runtime.getRuntime()
//						.exec(new String[] { "/usr/bin/open",
//								file.getAbsolutePath() });
//				return true;
//			} else {
//				// Unknown OS, try with desktop
//				if (Desktop.isDesktopSupported()) {
//					Desktop.getDesktop().open(file);
//					return true;
//				} else {
//					return false;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace(System.err);
//			return false;
//		}
//	}
//
//	public static void rename(String oldName, String newName) {
//		String errorMsg = "";
//		if (oldName.equals(null) || oldName.equals("") || newName.equals(null)
//				|| newName.equals("")) {
//			errorMsg = "Rename action failed as the new or old names were empty";
//			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
//			return;
//		}
//		// String workingDirectory =
//		// FileKommanderRun.getKommander().getWorkingDirectory();
//		String workingDirectory = "testDir/";
//		// File (or directory) with old name
//		File file = new File(workingDirectory + oldName);
//
//		if (!file.exists()) {
//			errorMsg = "File " + oldName + "doesnot exist in the directory ";
//			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
//			return;
//		}
//
//		// File (or directory) with new name
//		File file2 = new File(workingDirectory + newName);
//		if (file2.exists()) {
//			errorMsg = "File " + newName + " exists in the directory ";
//			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
//			return;
//		}
//		// Rename file (or directory)
//		boolean success = file.renameTo(file2);
//		if (!success) {
//			errorMsg = "Error renaming file";
//			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
//		} else {
//			System.out.println("Rename successful");
//		}
//
//	}
//
//	public static void insert(String phraseToBeInserted, String existingPhrase,
//			String position, String fileName) throws IOException {
//		String errorMsg = "";
//		File file = new File("testDir/" + fileName);
//		if (file.exists()) {
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//			// new BufferedReader(new FileReader(file))/;
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				Pattern pattern = Pattern.compile(existingPhrase,
//						Pattern.CASE_INSENSITIVE);
//				// pattern.
//				Matcher matcher = pattern.matcher(line);
//				// Check all occurrences
//				while (matcher.find()) {
//					/*
//					 * switch(location){
//					 * 
//					 * case "before": break; case "after": break; case "":
//					 * break; default break; }
//					 */
//					System.out.print("Start index: " + matcher.start());
//					System.out.print(" End index: " + matcher.end() + " ");
//					System.out.println(matcher.group());
//				}
//
//			}
//
//		} else {
//			errorMsg = "File named " + fileName
//					+ " doesn't exist. Do you want to continue ?";
//			System.out.println(errorMsg);
//		}
//	}
//
//	public static void remove(String phraseToBeRemoved, String position,
//			String fileName) {
//
//	}
//
//	public static void replace(String phraseToBeInserted,
//			String existingPhrase, String position, String fileName) {
//
//	}
//
//	// parentObjectName = name of the file/folder
//	// parentObjectType = file or folder- 0,1
//	public static void stats(String parentObjectName, int statsType,
//			int parentObjectType) throws IOException {
//
//		if (statsType == FileKommander.COUNT) {
//			if (parentObjectType == FileKommander.FILE) {
//				count(parentObjectName, parentObjectType, "words");
//
//			} else if (parentObjectType == FileKommander.DIRECTORY) {
//				count(parentObjectName, parentObjectType, "files");
//			}
//		} else if (statsType == FileKommander.SIZEOF) {
//			sizeOf(parentObjectName);
//		}
//
//	}
//
//	// to count a specific word
//	public static void stats(String parentObjectName, String wordToBeCounted)
//			throws IOException {
//
//		count(parentObjectName, wordToBeCounted);
//
//	}
//
//	public static void count(String parentObjectName, String wordToBeCounted)
//			throws IOException {
//		File f = new File("testDir/" + parentObjectName);
//		String line = "";
//		int count = 0;
//		if (f.exists()) {
//			FileReader fr = new FileReader(f);
//			BufferedReader br = new BufferedReader(fr);
//			while ((line = br.readLine()) != null) {
//				count += StringUtils.countMatches(line, wordToBeCounted);
//			}
//		}
//	}
//
//	private static void sizeOf(String parentObjectName) {
//		File f = new File("testDir/" + parentObjectName);
//		if (f.exists()) {
//			long b = f.length();
//			printSize(b);
//			if (f.isDirectory()) {
//				long bd = sizeofDirectory(f);
//				printSize(bd);
//			}
//		} else {
//			log.info("File doesn't exists.");
//		}
//
//	}
//
//	public static void printSize(long b) {
//		long k = b / 1024;
//		long m = k / 1024;
//		long g = m / 1024;
//		if (b < 1024) {
//			log.info("The size of the file is " + b + " Bytes");
//		} else if (k < 1024) {
//			log.info("The size of the file is " + k + " KB");
//		} else if (m < 1024) {
//			log.info("The size of the file is " + m + " MB");
//		} else {
//			log.info("The size of the file is " + g + " GB");
//		}
//	}
//
//	public static long sizeofDirectory(File f) {
//		long size = 0;
//		File[] subFiles = f.listFiles();
//		for (File file : subFiles) {
//			if (file.isFile()) {
//				size += file.length();
//			} else {
//				size += sizeofDirectory(file);
//			}
//		}
//		return size;
//	}
//
//	public static void count(String parentObjectName, int parentObjectType,
//			String type) throws IOException {
//		File f = new File("testDir/" + parentObjectName);
//		if (type.equals("words")) {
//			if (f.exists()) {
//				FileReader fr = new FileReader(f);
//				BufferedReader br = new BufferedReader(fr);
//				StreamTokenizer stz = new StreamTokenizer(br);
//				int index = 0;
//				int numWords = 0;
//
//				while (index != StreamTokenizer.TT_EOF) {
//					index = stz.nextToken();
//					numWords++;
//				}
//
//				log.info("no. of words in file = " + numWords);
//			} else {
//				log.info("This file doesn't exists.");
//			}
//		} else if (type.equals("files")) {
//			int count = 0;
//			int countDirectory = 0;
//			int countAll = 0;
//			for (File file : f.listFiles()) {
//				if (file.isFile()) {
//					count++;
//				} else if (file.isDirectory()) {
//					countDirectory++;
//				}
//			}
//			countAll = count + countDirectory;
//			log.info("Number of files: " + count);
//			log.info("Number of folders:" + countDirectory);
//			log.info("Number of files and folders: " + countAll);
//		}
//	}

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
