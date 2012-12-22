package com.poly.nlp.filekommander.file.actions;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.nio.file.FileSystem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;

public class FileActionUtils {
	private static final Logger log = Logger.getLogger(FileActionUtils.class);
	private static String workingDirectory;

	/**
	 * @return the workingDirectory
	 */
	public static String getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * @param workingDirectory
	 *            the workingDirectory to set
	 */
	public static void setWorkingDirectory(String workingDirectory) {
		FileActionUtils.workingDirectory = workingDirectory;
	}

	/**
	 * Creates a new File and returns the error message
	 * 
	 * @param name
	 * @return
	 */
	public static String createFile(String name) {
		String errorMsg = "";
		name = name.replaceAll("^[.\\\\/:*?\"<>|]?[\\\\/:*?\"<>|]*", "");
		if (name.length() == 0) {
			errorMsg = "File named " + name
					+ " is of zero length after removing special characters";
		} else {
			File file = new File(workingDirectory + name);
			if (file.exists()) {
				errorMsg = "File : " + name + " already exists";
				log.error(errorMsg);
			} else {
				try {
					File parent = file.getParentFile();
					if (!parent.exists()) {
						try {
							FileUtils.forceMkdir(parent);
						} catch (Exception e) {
							errorMsg = "Could not create the parent folder for the file";
							e.printStackTrace();
							return errorMsg;
						}
					}
					file.createNewFile();
					log.info("file created successfully.");
				} catch (IOException e) {
					errorMsg = "Error creating File " + e.getMessage();
					e.printStackTrace();
				}
			}
		}
		return errorMsg;
	}

	/**
	 * Creates a new Folder and returns the error message
	 * 
	 * @param name
	 * @return
	 */
	public static String createFolder(String name) {
		String errorMsg = "";
		name = name.replaceAll("^[.\\\\/:*?\"<>|]?[\\\\/:*?\"<>|]*", "");
		try {
			Boolean success = new File(workingDirectory + name).mkdirs();
			if (!success) {
				errorMsg = "Could not create folder : " + name;
				log.error(errorMsg);
			} else {
				log.info("Folder created successfully");
			}
		} catch (Exception e) {
			errorMsg = "Could not create folder : " + e.getMessage();
			e.printStackTrace();
		}
		return errorMsg;
	}

	// private static String create(String name, int type) {
	// String errorMsg = "";
	// name.replaceAll("^[.\\\\/:*?\"<>|]?[\\\\/:*?\"<>|]*", "");
	// try {
	// if (name.length() == 0) {
	// errorMsg = "File named "
	// + name
	// + " is of zero length after removing special characters";
	// }else if (type == FileKommander.FILE) {
	// File file = new File(workingDirectory + name);
	// if (file.exists()) {
	// errorMsg = "File : " + name + " already exists";
	// log.error(errorMsg);
	// } else {
	// file.createNewFile();
	// log.info("file created successfully.");
	// }
	// } else if (type == FileKommander.DIRECTORY) {
	// Boolean success = new File(workingDirectory+ name).mkdirs();
	// if (!success) {
	// errorMsg = "Could not create folder : " + name;
	// log.error(errorMsg);
	// } else {
	// log.info("Folder created successfully");
	// }
	// }
	//
	// } catch (Exception e) {
	// log.error(e);
	// }
	// return errorMsg;
	// }

	public static String deleteFile(String fileName) {
		String errorMsg = "";
		File file = new File(workingDirectory + fileName);
		if (!file.exists()) {
			errorMsg = "File " + fileName
					+ " does not exist so can not delete ";
		} else {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				errorMsg = "Could not delete file " + e.getMessage();
				e.printStackTrace();
			}
		}
		return errorMsg;
	}

	public static String deleteFolder(String folderName) {
		String errorMsg = "";
		File file = new File(workingDirectory + folderName);
		if (!file.exists()) {
			errorMsg = "Folder " + folderName
					+ " does not exist so can not delete ";
		} else {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				errorMsg = "Could not delete folder " + e.getMessage();
				e.printStackTrace();
			}
		}
		return errorMsg;
	}

	// private static String delete(String name, int type) {
	// String errorMsg = "";
	//
	// log.info("name of object is" + name);
	// log.info("type of object is" + type);
	// try {
	// if (type == FileKommander.FILE) {
	// File file = new File(workingDirectory + name);
	// if (file.exists()) {
	// Boolean success = file.delete();
	// if (!success) {
	// errorMsg = "Could not delete the file " + name;
	// log.info(errorMsg);
	// } else {
	// log.info("deleted file successfully");
	// }
	// } else {
	// errorMsg = "This file " + name +" doesn't exist. Hence can't be deleted";
	// log.info(errorMsg);
	// }
	//
	// } else if (type == FileKommander.DIRECTORY) {
	// File directory = new File(workingDirectory+ name);
	//
	// if (directory.isDirectory()) {
	// // if directory is empty
	// if (directory.list().length == 0) {
	// directory.delete();
	// System.out.println("directory deleted successfully");
	// } else {
	// String files[] = directory.list();
	// for (String temp : files) {
	// // construct the file structure
	// File fileDelete = new File(directory, temp);
	//
	// // recursive delete
	// fileDelete.delete();
	// }
	//
	// // check the directory again, if empty then delete it
	// if (directory.list().length == 0) {
	// directory.delete();
	// System.out
	// .println("directory deleted successfully "
	// + directory.getAbsolutePath());
	// }
	// }
	//
	// }
	// }
	// // System.out.println("error is .."+errorMsg);
	// } catch (Exception e) {
	// errorMsg = e.getLocalizedMessage();
	// e.printStackTrace();
	// }
	// return errorMsg;
	// }

	public static String exists(String name) {
		String message = "";

		System.out.println("name of object is" + name);
		try {
			File file = new File(workingDirectory + name);
			if (file.exists()) {
				message = "<html>" + name + " exists at <u>"
						+ file.getAbsolutePath() + "</u></html>";
				System.out.println("File/Folder exists!");
			} else {
				message = "File/Folder " + name + " doesn't exist!";
				System.out.println(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public static String open(String name) {
		String message = "";
		if (name.equals("") || name.equals("")) {
			message = "File Name is empty";
			return message;
		}
		// String workingDirectory =
		// FileKommanderRun.getKommander().getWorkingDirectory();
		// String workingDirectory = "testDir/";
		File file = new File(workingDirectory + name);
		if (!file.exists()) {
			message = "File " + name + " does not exist in the directory ";
			// FileKommanderRun.getGuiv2().displayErrorMessage(message);
			return message;
		}
		try {
			if (OSDetector.isWindows()) {
				System.out.println(file.getAbsolutePath());
				String[] command = new String[] { "rundll32.exe",
						"url.dll,FileProtocolHandler", file.getAbsolutePath() };
				System.out.println(Arrays.toString(command));
				Process process = Runtime.getRuntime().exec(command);
				message = "File " + name + " opened successfully";
				return message;
			} else if (OSDetector.isLinux() || OSDetector.isMac()) {
				Runtime.getRuntime()
						.exec(new String[] { "/usr/bin/open",
								file.getAbsolutePath() });
				message = "Opened successfully";
				return message;
			} else {
				// Unknown OS, try with desktop
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
					message = "Opened successfully";
					return message;
				} else {
					message = "Unsuccessfull open";
					return message;
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			message = "Unable to open";
			return message;
		}
	}

	public static String rename(String oldName, String newName) {
		String errorMsg = "";
		if (oldName.equals(null) || oldName.equals("") || newName.equals(null)
				|| newName.equals("")) {
			errorMsg = "Rename action failed as the new or old names were empty";
			// FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return errorMsg;
		}
		// String workingDirectory =
		// FileKommanderRun.getKommander().getWorkingDirectory();
		// File (or directory) with old name
		File file = new File(workingDirectory + oldName);

		if (!file.exists()) {
			errorMsg = "File " + oldName + "doesnot exist in the directory ";
			// FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return errorMsg;
		}

		// File (or directory) with new name
		File file2 = new File(workingDirectory + newName);
		if (file2.exists()) {
			errorMsg = "File " + newName + " exists in the directory ";
			FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
			return errorMsg;
		}
		// Rename file (or directory)
		boolean success = file.renameTo(file2);
		if (!success) {
			errorMsg = "Error renaming file";
			return errorMsg;
			// FileKommanderRun.getGuiv2().displayErrorMessage(errorMsg);
		} else {
			System.out.println("Rename successful");
		}
		return errorMsg;

	}

	// public static void insert(String phraseToBeInserted, String
	// existingPhrase,
	// String position, String fileName) throws IOException {
	// String errorMsg = "";
	// File file = new File("testDir/" + fileName);
	// if (file.exists()) {
	// BufferedReader reader = new BufferedReader(new FileReader(file));
	// // new BufferedReader(new FileReader(file))/;
	// String line = "";
	// while ((line = reader.readLine()) != null) {
	// Pattern pattern = Pattern.compile(existingPhrase,
	// Pattern.CASE_INSENSITIVE);
	// // pattern.
	// Matcher matcher = pattern.matcher(line);
	// // Check all occurrences
	// while (matcher.find()) {
	// /*
	// * switch(location){
	// *
	// * case "before": break; case "after": break; case "":
	// * break; default break; }
	// */
	// System.out.print("Start index: " + matcher.start());
	// System.out.print(" End index: " + matcher.end() + " ");
	// System.out.println(matcher.group());
	// }
	//
	// }
	//
	// } else {
	// errorMsg = "File named " + fileName
	// + " doesn't exist. Do you want to continue ?";
	// System.out.println(errorMsg);
	// }
	// }
	//
	// public static void remove(String phraseToBeRemoved, String position,
	// String fileName) {
	//
	// }
	//
	// public static void replace(String phraseToBeInserted,
	// String existingPhrase, String position, String fileName) {
	//
	// }
	public static String insert(String phraseToBeInserted,
			String existingPhrase, String position, String repetition,
			String fileName) {
		File file = new File(workingDirectory + fileName);
		String message = "";
		int count = 0; // TODO
		if (file.exists()) {

			String contents;
			try {
				contents = FileUtils.readFileToString(file);
			} catch (Exception e) {
				message = "Error reading the file ";
				e.printStackTrace();
				return message;
			}
			String newString = "";
			Pattern p = Pattern.compile(existingPhrase);
			Matcher m = p.matcher(contents);
			if (m.find()) {
				if (position.contains("before")) {
					newString = phraseToBeInserted + " " + existingPhrase;
					if (repetition.contains("first")) {
						contents = contents.replaceFirst(existingPhrase,
								newString);
					} else if (repetition.contains("last")) { // contents =
																// contents.replaceFirst(existingPhrase,
																// newString);
						newString = existingPhrase + " " + phraseToBeInserted;
						String contentsRev = StringUtils.reverse(contents);
						contentsRev = StringUtils.replaceOnce(contentsRev,
								existingPhrase, newString);
						contents = StringUtils.reverse(contentsRev);
						// contents = contents.substring(0,
						// contents.lastIndexOf(existingPhrase) + 1)
						// +" " + newString;
					} else if (repetition.contains("every")
							|| repetition.contains("all")) {
						contents = contents.replace(existingPhrase, newString);
					} else {
						contents = contents.replace(existingPhrase, newString);
					}
				} else if (position.contains("after")) {
					newString = existingPhrase + " " + phraseToBeInserted;
					if (repetition.contains("every")
							|| repetition.contains("all"))
						contents = contents.replace(existingPhrase, newString);
					if (repetition.contains("first"))
						contents = contents.replaceFirst(existingPhrase,
								newString);
					if (repetition.contains("last")) {
						// contents = contents.substring(0,
						// contents.lastIndexOf(existingPhrase)) + newString;
						newString = phraseToBeInserted + " " + existingPhrase;
						String contentsRev = StringUtils.reverse(contents);
						contentsRev = StringUtils.replaceOnce(contentsRev,
								existingPhrase, newString);
						contents = StringUtils.reverse(contentsRev);
					}
				} else if (position.contains("beginning")
						|| position.contains("start")) {
					contents = phraseToBeInserted + " " + contents;
				} else if (position.contains("end")) {
					contents = contents + " " + phraseToBeInserted;
				}
				// log.info(contents);
				try {
					FileUtils.writeStringToFile(file, contents);
				} catch (IOException e) {
					message = " Error writing to file ";
					e.printStackTrace();
				}
				message = "Inserted phrase " + phraseToBeInserted + " "
						+ position + " " + repetition + " occurence of word "
						+ existingPhrase;
			} else {
				message = "The phrase " + existingPhrase
						+ " cant be found in the file";
			}
		} else {
			message = "This file doesnt exists";
		}
		return message;
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
		File f = new File(workingDirectory + parentObjectName);
		String line = "";
		int count = 0;
		if (f.exists()) {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				count += StringUtils.countMatches(line, wordToBeCounted);
			}
			br.close();
		}
	}

	public static void count(String parentObjectName, int parentObjectType,
			String type) throws IOException {
		File f = new File(workingDirectory + parentObjectName);
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

	public static String remove(String phraseToBeRemoved, String repetition,
			String fileName) {
		String message = "";
		File file = new File(workingDirectory + fileName);
		String contents = "";

		if (file.exists()) {
			try {
				contents = FileUtils.readFileToString(file);
			} catch (Exception e) {
				message = "Error reading the file ";
				e.printStackTrace();
				return message;
			}
			Pattern p = Pattern.compile(phraseToBeRemoved);
			Matcher m = p.matcher(contents);
			String newString = "";
			if (m.find()) {
				if (repetition.contains("first")) {
					contents = contents.replaceFirst(phraseToBeRemoved, "");
				} else if (repetition.contains("last")) {
					String contentsRev = StringUtils.reverse(contents);
					contentsRev = contentsRev.replaceFirst(phraseToBeRemoved,
							"");
					contents = StringUtils.reverse(contentsRev);
//					contents = contents.substring(0,
//							contents.lastIndexOf(phraseToBeRemoved) + 1)
//							+ "";
				} else if (repetition.contains("every")
						|| repetition.contains("all")) {
					contents = contents.replace(phraseToBeRemoved, "");
				}
				message = "Removed " + repetition + " string " + phraseToBeRemoved + " successfully " ;

			} else {
				message = "The phrase " + phraseToBeRemoved
						+ " cant be found in the file " + fileName;
			}

		} else {
			message = "File " + fileName + " does not exists";
		}

		// log.info(contents);
		try {
			FileUtils.writeStringToFile(file, contents);
		} catch (IOException e) {
			message = " Error writing to file ";
			e.printStackTrace();
		}

		return message;
	}

	public static String replace(String phraseToBeInserted,
			String existingPhrase, String repetition, String fileName) {

		String message = "";
		File file = new File(workingDirectory + fileName);
		String contents = "";

		if (file.exists()) {
			try {
				contents = FileUtils.readFileToString(file);
			} catch (Exception e) {
				message = "Error reading the file ";
				e.printStackTrace();
				return message;
			}
			Pattern p = Pattern.compile(existingPhrase);
			Matcher m = p.matcher(contents);
			String newString = "";
			if (m.find()) {
				if (repetition.contains("first"))
					contents = contents.replaceFirst(existingPhrase,
							phraseToBeInserted);
				else if (repetition.contains("last"))
				// contents = contents.replaceFirst(existingPhrase, newString);
				{
					String contentsRev = StringUtils.reverse(contents);
					contentsRev = contentsRev.replaceFirst(existingPhrase,
							phraseToBeInserted);
					contents = StringUtils.reverse(contentsRev);
					// contents = contents.substring(0,
					// contents.lastIndexOf(existingPhrase) + 1) +
					// phraseToBeInserted;
				} else if (repetition.contains("every")
						|| repetition.contains("all"))
					contents = contents.replace(existingPhrase,
							phraseToBeInserted);
				message = "Replaced " + repetition + " string "
						+ existingPhrase + " with " + phraseToBeInserted
						+ " successfully ";
			} else {
				message = "The phrase " + phraseToBeInserted
						+ " cant be found in the file " + fileName;
			}

		} else {
			message = "File " + fileName + " does not exists";
		}

		// log.info(contents);
		try {
			FileUtils.writeStringToFile(file, contents);
		} catch (IOException e) {
			message = " Error writing to file ";
			e.printStackTrace();
		}

		return message;
	}

	public static int countWords(String fileName) {
		File f = new File(workingDirectory + fileName);
		int numWords = 0;
		if (f.exists()) {
			try {
				FileReader fr;
				fr = new FileReader(f);

				BufferedReader br = new BufferedReader(fr);
				StreamTokenizer stz = new StreamTokenizer(br);
				int index = 0;

				while (index != StreamTokenizer.TT_EOF) {
					index = stz.nextToken();
					numWords++;
				}

				log.info("no. of words in file = " + numWords);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				log.info("Some error reading the file");
			}
		} else {
			log.info("This file doesn't exists.");
		}
		return numWords;
	}

	public static ArrayList<String> listFiles(String folderName,
			ArrayList<String> list) {
		// f = new File("testDir/"+f);
		File f = new File(workingDirectory + folderName);
		if (f.isDirectory()) {
			File[] subFiles = f.listFiles();
			for (File file : subFiles) {
				if (file.isFile()) {
					list.add(file.getName());
				} else {
					list = listFiles(file.getName(), list);
				}
			}
		} else {
			log.info("only files in the folder can be listed.");
		}
		return list;
	}

	public static String lastModified(String parentObjectName) {
		File f = new File(workingDirectory + parentObjectName);
		String dateString = "";
		if (f.isFile()) {
			long datetime = f.lastModified();
			Date d = new Date(datetime);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			dateString = sdf.format(d);
			log.info("The file " + parentObjectName + " was last modified on: "
					+ dateString);
		}
		return "The file " + parentObjectName + " was last modified on: "
				+ dateString;
	}

	// to count a specific word
	public static int countSpecificWord(String parentObjectName,
			String wordToBeCounted) {
		File f = new File(workingDirectory + parentObjectName);
		String line = "";
		int count = 0;

		try {
			if (f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				while ((line = br.readLine()) != null) {
					count += StringUtils.countMatches(line, wordToBeCounted);
				}
				br.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("the no of times the word " + wordToBeCounted + " appears is "
				+ count);
		return count;
	}

	// public static void sizeOf(String parentObjectName) {
	// File f = new File(workingDirectory + parentObjectName);
	// if (f.exists()) {
	// long b = f.length();
	// printSize(b);
	// if (f.isDirectory()) {
	// long bd = sizeofDirectory(f);
	// printSize(bd);
	// }
	// } else {
	// log.info("File doesn't exists.");
	// }
	//
	// }
	// finds the size of the file or folder
	public static String sizeOf(String parentObjectName) {
		File f;
		long b, bd;
		if (parentObjectName.equals(workingDirectory))
			f = new File(parentObjectName);
		else
			f = new File(workingDirectory + parentObjectName);
		if (f.exists()) {
			if (f.isDirectory()) {
				bd = sizeofDirectory(f);
				return printSize(bd);
			} else {
				b = f.length();
				return printSize(b);
			}

		} else {
			return ("File doesn't exists.");
		}
		// return printSize(bd);

	}

	// function to print the size of the file either in bytes, KB, MB or GB
	private static String printSize(long b) {
		long k = b / 1024;
		long m = k / 1024;
		long g = m / 1024;
		if (b < 1024) {
			return ("The size of the file is " + b + " Bytes");
		} else if (k < 1024) {
			return ("The size of the file is " + k + " KB");
		} else if (m < 1024) {
			return ("The size of the file is " + m + " MB");
		} else {
			return ("The size of the file is " + g + " GB");
		}
	}

	// find the size of the folder.
	static long sizeofDirectory(File f) {
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

	// count
	public static int countFiles(String parentObjectName) {
		File f;
		if (parentObjectName.equals(workingDirectory)) {
			f = new File(parentObjectName);
		} else {
			f = new File(workingDirectory + parentObjectName);
		}
		int c = countSubFiles(f);
		log.info("count is" + c);
		return c;
	}

	private static int countSubFiles(File f) {
		int count = 0;
		int countDirectory = 0;
		int countAll = 0;
		for (File file : f.listFiles()) {
			if (file.isFile()) {
				count++;
			} else if (file.isDirectory()) {
				countDirectory += countSubFiles(file);
			}
		}
		return count++;

	}

}
