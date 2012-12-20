package com.poly.nlp.filekommander.file.actions;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.RenameModel;
import com.poly.nlp.filekommander.views.models.StatsModel;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class CallAction {
	private static final Logger log = Logger.getLogger(CallAction.class);

	public static GenericActionModel callAction(GenericActionModel actionModel) {
		log.info("Checking which action to call in callAction function ");
		GenericActionModel newActionModel = null;
		if (actionModel == null)
			return null;

		if (actionModel instanceof CreateModel) {
			newActionModel = createAction((CreateModel) actionModel);
		} else if (actionModel instanceof DeleteModel) {
			newActionModel =	deleteAction((DeleteModel) actionModel);
		}  else if (actionModel instanceof RenameModel) {
			newActionModel =	renameAction((RenameModel) actionModel);
		}else if(actionModel instanceof StatsModel){
			newActionModel =	statsAction((StatsModel) actionModel);
		}else {
			existsAction();
			insertAction();
			openAction();
			removeAction();
		// renameAction();
			replaceAction();
		//	statsAction();

		}
		return newActionModel;
	}

	private static GenericActionModel createAction(CreateModel createModel) {
		log.info("CreateAction action called for model" + createModel);
		HashMap<String, String> fileListData = createModel.getFileListData();
		HashMap<String, String> folderListData = createModel
				.getFolderListData();

		for (String fileName : fileListData.keySet()) {
			String message = FileActionUtils.createFile(fileName);
			fileListData.put(fileName, message);
		}
		for (String folderName : folderListData.keySet()) {
			String message = FileActionUtils.createFolder(folderName);
			folderListData.put(folderName, message);
		}
		createModel.setModelRun(true);
		createModel.setFileListData(fileListData);
		createModel.setFolderListData(folderListData);
		return createModel;
	}

	private static DeleteModel deleteAction(DeleteModel deleteModel) {
		log.info("DeleteAction action called for model" + deleteModel);
		HashMap<String, String> fileListData = deleteModel.getFileListData();
		HashMap<String, String> folderListData = deleteModel
				.getFolderListData();

		for (String fileName : fileListData.keySet()) {
			String message = FileActionUtils.deleteFile(fileName);
			fileListData.put(fileName, message);
		}
		for (String folderName : folderListData.keySet()) {
			String message = FileActionUtils.deleteFolder(folderName);
			folderListData.put(folderName, message);
		}
		deleteModel.setModelRun(true);
		deleteModel.setFileListData(fileListData);
		deleteModel.setFolderListData(folderListData);
		return deleteModel	;
	}

	public static void existsAction() {
		log.info("ExistsAction action called");
	}

	public static void insertAction() {
		log.info("InsertAction action called");
	}

	public static void openAction() {
		log.info("OpenAction action called");
	}

	public static void removeAction() {
		log.info("RemoveAction action called");
	}

	private static GenericActionModel renameAction(RenameModel renameModel) {
		log.info("RenameAction action called");
		String oldFileName = renameModel.getOldFileName();
		String newFileName = renameModel.getNewFileName();
	   String message = FileActionUtils.rename(oldFileName, newFileName);
		renameModel.setErrorMessage(message);
		renameModel.setModelRun(true);
		return renameModel;
	}

	public static void replaceAction() {
		log.info("ReplaceAction action called");
	}

	public static GenericActionModel statsAction(StatsModel statsModel) {
		log.info("StatsAction action called"); 
		//iterate over every file/folder and find the stats type and call the corr. function 
		HashMap<String, String> fileListData = statsModel.getFileListData();
		HashMap<String, String> folderListData = statsModel.getFolderListData();

		for (String fileName : fileListData.keySet()) {
			if(statsModel.getStatsType() == FileKommander.COUNT){
				int count = FileActionUtils.countWords(fileName);
				String message = "Count of all the words is"+count;
				fileListData.put(fileName, message);
			}else if(statsModel.getStatsType() == FileKommander.COUNT_WORD){
		//		int count = countSpecificWord(fileName, word); //where is dis word passed from ?? how??
		//		String message = "The word "+word+" appears "+count+" times";
		//		fileListData.put(fileName, message);
			} else if(statsModel.getStatsType() == FileKommander.LAST_MODIFIED){
				String message = FileActionUtils.lastModified(fileName);
				fileListData.put(fileName, message);
			} else if(statsModel.getStatsType() == FileKommander.SIZEOF){
				String message = FileActionUtils.sizeOf(fileName);
				fileListData.put(fileName, message);
			}
		}
		for (String folderName : folderListData.keySet()) {
			if(statsModel.getStatsType() == FileKommander.COUNT){
				int count = FileActionUtils.countFiles(folderName);
				String message = "Count of all the files in the folder "+folderName+" is"+count;
				fileListData.put(folderName, message);
			} else if(statsModel.getStatsType() == FileKommander.LIST_FILES){
				ArrayList<String> l = new ArrayList<String>();
				ArrayList<String> list = FileActionUtils.listFiles(folderName, l);
				java.util.ListIterator<String> ls = list.listIterator();
				StringBuffer sb = new StringBuffer("");
				while(ls.hasNext()){
					System.out.println(ls.next());
					sb.append(ls.next()+"</br>");
				}
				folderListData.put(folderName, sb.toString());
			} else if(statsModel.getStatsType() == FileKommander.SIZEOF){
				String message = FileActionUtils.sizeOf(folderName);
				folderListData.put(folderName, message);
			}
		}
		statsModel.setModelRun(true);
		statsModel.setFileListData(fileListData);
		statsModel.setFolderListData(folderListData);
		return statsModel;
	}

}
