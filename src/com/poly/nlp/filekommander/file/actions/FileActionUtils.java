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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;

import gate.Annotation;
import gate.AnnotationSet;
import gate.FeatureMap;

/**
 * @author jake , neha
 * 
 */
public class FileActionUtils {
	private static final Logger log = Logger.getLogger(FileActionUtils.class);

	public static GenericActionModel AnalyseCreateAction(Annotation annotation) {
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
				if(!fileNamesList.contains(quotedName) && !directoryNamesList.contains(quotedName))
				createModel.add(quotedName, FileKommander.DIRECTORY);
			}
		}
		log.info("Files to be Created : " + fileNamesList);
		log.info("Directories to be Created : " + directoryNamesList);
		log.info("Files to be Created : " + quotedObjectNamesList);
		return createModel;
	}

	public static GenericActionModel AnalyseDeleteAction(Annotation annotation) {
		DeleteModel deleteModel = new DeleteModel();
		FeatureMap featureMap = annotation.getFeatures();
		String content = (String) featureMap.get("content");
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");
		for (String fileName : quotedObjectNamesList) {
			deleteModel.add(fileName, FileKommander.FILE);
		}
		for (String fileName : fileNamesList) {
			deleteModel.add(fileName, FileKommander.FILE);
		}
		for (String folderName : directoryNamesList) {
			deleteModel.add(folderName, FileKommander.DIRECTORY);
		}
		log.info("Files to be Deleted : " + fileNamesList);
		log.info("Directories to be Deleted : " + directoryNamesList);
		log.info("Files to be Deleted : " + quotedObjectNamesList);
		return deleteModel;
	}

	public static GenericActionModel AnalyseExistsAction(Annotation annotation) {
		return null;

	}

	public static GenericActionModel AnalyseInsertAction(Annotation annotation) {
		return null;

	}

	public static GenericActionModel AnalyseOpenAction(Annotation annotation) {
		return null;

	}

	public static GenericActionModel AnalyseRemoveAction(Annotation annotation) {
		return null;

	}

	public static GenericActionModel AnalyseRenameAction(Annotation annotation) {
		FeatureMap featureMap = annotation.getFeatures();
		String content = (String) featureMap.get("content");
		ArrayList<String> fileNamesList = getObjectNameFromAnnotation(
				featureMap, "fileName");
		ArrayList<String> directoryNamesList = getObjectNameFromAnnotation(
				featureMap, "directoryName");
		ArrayList<String> quotedObjectNamesList = getObjectNameFromAnnotation(
				featureMap, "quotedObject");

		boolean isRenameFile = (fileNamesList.size() == 2)
				|| (quotedObjectNamesList.size() == 2)
				|| (fileNamesList.size() + quotedObjectNamesList.size() == 2);
		if (isRenameFile) {

			log.info("");
		} else {

		}
		return null;

	}

	public static GenericActionModel AnalyseReplaceAction(Annotation annotation) {
		return null;

	}

	public static GenericActionModel AnalyseStatsAction(Annotation annotation) {
		return null;

	}

	public static void CreateAction(Annotation annotation) {
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
			returnModel = AnalyseCreateAction(annotation);
			break;
		case "delete":
			returnModel = AnalyseDeleteAction(annotation);
			break;
		case "exists":
			returnModel = AnalyseExistsAction(annotation);
			break;
		case "insert":
			returnModel = AnalyseInsertAction(annotation);
			break;
		case "open":
			returnModel = AnalyseOpenAction(annotation);
			break;
		case "remove":
			returnModel = AnalyseRemoveAction(annotation);
			break;
		case "rename":
			returnModel = AnalyseRenameAction(annotation);
			break;
		case "replace":
			returnModel = AnalyseReplaceAction(annotation);
			break;
		case "stats":
			returnModel = AnalyseStatsAction(annotation);
			break;
		default:
			break;
		}
		return returnModel;

	}

	public static void callAction(String actionType, Annotation annotation) {
		if (actionType == null)
			return;
		FeatureMap featureMap = annotation.getFeatures();
		AnnotationSet directoryName = (AnnotationSet) featureMap
				.get("directoryName");
		AnnotationSet fileName = (AnnotationSet) featureMap.get("fileName");
		AnnotationSet phraseName = (AnnotationSet) featureMap.get("phraseName");
		AnnotationSet quotedObject = (AnnotationSet) featureMap
				.get("quotedObject");
		String content = (String) featureMap.get("content");

		switch (actionType) {
		case "close":
			// CloseAction();
			break;
		case "create":
			CreateAction(annotation);
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

	public static void create(String name, int type) {
		// if type == directory create directory else create file , if file name
		// is null
		// or has illegal characters , display error message working directory
		// in FileKommander.getWotkingdir
		// TODO get type from FileKommander.FILE/DIRECTORY ;
		String errorMsg = "";

		System.out.println("name of object is" + name);
		System.out.println("type of object is" + type);
		try {
			if (type == FileKommander.FILE) {
				File file = new File("testDir/" + name);
				if (file.exists()) {
					errorMsg = "File named " + name
							+ " already exists. Do you want to continue ?";
					System.out.println(errorMsg);
				} else {
					file.createNewFile();
					System.out.println("file created successfully.");
				}

			} else if (type == FileKommander.DIRECTORY) {
				Boolean success = new File("testDir/" + name).mkdirs();
				if (!success) {
					errorMsg = "Could not create folder" + name;
					System.out.println(errorMsg);
				} else {
					System.out.println("folder");
				}
			}
			// System.out.println("error is .."+errorMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void delete(String name, int type) {
		String errorMsg = "";

		System.out.println("name of object is" + name);
		System.out.println("type of object is" + type);
		try {
			if (type == FileKommander.FILE) {
				File file = new File("testDir/" + name);
				if (file.exists()) {
					Boolean success = file.delete();
					if (!success) {
						errorMsg = "Could not delete the file " + name;
						System.out.println(errorMsg);
					} else {
						System.out.println("deleted file successfully");
					}
				} else {
					errorMsg = "This file doesn't exist. Hence can't be deleted";
					System.out.println(errorMsg);
				}

			} else if (type == FileKommander.DIRECTORY) {
				File directory = new File("testDir/" + name);

				if (directory.isDirectory()) {
					// if directory is empty
					if (directory.list().length == 0) {
						directory.delete();
						System.out.println("directory deleted successfully");
					} else {
						String files[] = directory.list();
						for (String temp : files) {
							// construct the file structure
							File fileDelete = new File(directory, temp);

							// recursive delete
							fileDelete.delete();
						}

						// check the directory again, if empty then delete it
						if (directory.list().length == 0) {
							directory.delete();
							System.out
									.println("directory deleted successfully "
											+ directory.getAbsolutePath());
						}
					}

				}
			}
			// System.out.println("error is .."+errorMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exists(String name) {
		String errorMsg = "";

		System.out.println("name of object is" + name);
		try {
			File file = new File("testDir/" + name);
			if (file.exists()) {
				System.out.println("File/Folder exists!");
			} else {
				errorMsg = "File/Folder " + name + " doesn't exist!";
				System.out.println(errorMsg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean open(String name) {
		String errorMsg = "";
		if (name.equals("") || name.equals("")) {
			errorMsg = "File Name is empty";
			return false;
		}
		// String workingDirectory =
		// FileKommanderRun.getKommander().getWorkingDirectory();
		String workingDirectory = "testDir/";
		File file = new File(name);
		if (!file.exists()) {
			errorMsg = "File " + name + " doesnot exist in the directory ";
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return false;
		}
		try {
			if (OSDetector.isWindows()) {
				System.out.println(file.getAbsolutePath());
				String[] command = new String[] { "rundll32.exe",
						"url.dll,FileProtocolHandler", file.getAbsolutePath() };
				System.out.println(Arrays.toString(command));
				Runtime.getRuntime().exec(command);
				return true;
			} else if (OSDetector.isLinux() || OSDetector.isMac()) {
				Runtime.getRuntime()
						.exec(new String[] { "/usr/bin/open",
								file.getAbsolutePath() });
				return true;
			} else {
				// Unknown OS, try with desktop
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
	}

	public static void rename(String oldName, String newName) {
		String errorMsg = "";
		if (oldName.equals(null) || oldName.equals("") || newName.equals(null)
				|| newName.equals("")) {
			errorMsg = "Rename action failed as the new or old names were empty";
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return;
		}
		// String workingDirectory =
		// FileKommanderRun.getKommander().getWorkingDirectory();
		String workingDirectory = "testDir/";
		// File (or directory) with old name
		File file = new File(workingDirectory + oldName);

		if (!file.exists()) {
			errorMsg = "File " + oldName + "doesnot exist in the directory ";
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return;
		}

		// File (or directory) with new name
		File file2 = new File(workingDirectory + newName);
		if (file2.exists()) {
			errorMsg = "File " + newName + " exists in the directory ";
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return;
		}
		// Rename file (or directory)
		boolean success = file.renameTo(file2);
		if (!success) {
			errorMsg = "Error renaming file";
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
		} else {
			System.out.println("Rename successful");
		}

	}

	public static void insert(String phraseToBeInserted, String existingPhrase,
			String position, String fileName) throws IOException {
		String errorMsg = "";
		File file = new File("testDir/" + fileName);
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			// new BufferedReader(new FileReader(file))/;
			String line = "";
			while ((line = reader.readLine()) != null) {
				Pattern pattern = Pattern.compile(existingPhrase,
						Pattern.CASE_INSENSITIVE);
				// pattern.
				Matcher matcher = pattern.matcher(line);
				// Check all occurrences
				while (matcher.find()) {
					/*
					 * switch(location){
					 * 
					 * case "before": break; case "after": break; case "":
					 * break; default break; }
					 */
					System.out.print("Start index: " + matcher.start());
					System.out.print(" End index: " + matcher.end() + " ");
					System.out.println(matcher.group());
				}

			}

		} else {
			errorMsg = "File named " + fileName
					+ " doesn't exist. Do you want to continue ?";
			System.out.println(errorMsg);
		}
	}

	public static void remove(String phraseToBeRemoved, String position,
			String fileName) {

	}

	public static void replace(String phraseToBeInserted,
			String existingPhrase, String position, String fileName) {

	}

	// parentObjectName = name of the file/folder
	// parentObjectType = file or folder- 0,1
	public static void stats(String parentObjectName, int statsType,
			int parentObjectType) throws IOException {

		if (statsType == FileKommander.COUNT) {
			if (parentObjectType == FileKommander.FILE) {
				count(parentObjectName, parentObjectType, "words");

			} else if (parentObjectType == FileKommander.DIRECTORY) {
				count(parentObjectName, parentObjectType, "files");
			}
		} else if (statsType == FileKommander.SIZEOF) {
			sizeOf(parentObjectName);
		}

	}

	// to count a specific word
	public static void stats(String parentObjectName, String wordToBeCounted)
			throws IOException {

		count(parentObjectName, wordToBeCounted);

	}

	public static void count(String parentObjectName, String wordToBeCounted)
			throws IOException {
		File f = new File("testDir/" + parentObjectName);
		String line = "";
		int count = 0;
		if (f.exists()) {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				count += StringUtils.countMatches(line, wordToBeCounted);
			}
		}
	}

	private static void sizeOf(String parentObjectName) {
		File f = new File("testDir/" + parentObjectName);
		if (f.exists()) {
			long b = f.length();
			printSize(b);
			if (f.isDirectory()) {
				long bd = sizeofDirectory(f);
				printSize(bd);
			}
		} else {
			log.info("File doesn't exists.");
		}

	}

	public static void printSize(long b) {
		long k = b / 1024;
		long m = k / 1024;
		long g = m / 1024;
		if (b < 1024) {
			log.info("The size of the file is " + b + " Bytes");
		} else if (k < 1024) {
			log.info("The size of the file is " + k + " KB");
		} else if (m < 1024) {
			log.info("The size of the file is " + m + " MB");
		} else {
			log.info("The size of the file is " + g + " GB");
		}
	}

	public static long sizeofDirectory(File f) {
		long size = 0;
		File[] subFiles = f.listFiles();
		for (File file : subFiles) {
			if (file.isFile()) {
				size += file.length();
			} else {
				size += sizeofDirectory(file);
			}
		}
		return size;
	}

	public static void count(String parentObjectName, int parentObjectType,
			String type) throws IOException {
		File f = new File("testDir/" + parentObjectName);
		if (type.equals("words")) {
			if (f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				StreamTokenizer stz = new StreamTokenizer(br);
				int index = 0;
				int numWords = 0;

				while (index != StreamTokenizer.TT_EOF) {
					index = stz.nextToken();
					numWords++;
				}

				log.info("no. of words in file = " + numWords);
			} else {
				log.info("This file doesn't exists.");
			}
		} else if (type.equals("files")) {
			int count = 0;
			int countDirectory = 0;
			int countAll = 0;
			for (File file : f.listFiles()) {
				if (file.isFile()) {
					count++;
				} else if (file.isDirectory()) {
					countDirectory++;
				}
			}
			countAll = count + countDirectory;
			log.info("Number of files: " + count);
			log.info("Number of folders:" + countDirectory);
			log.info("Number of files and folders: " + countAll);
		}
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
				outputList.add(objectName);
			}
		} else {
			return null;
		}

		return outputList.isEmpty() ? null : outputList;

	}

}
