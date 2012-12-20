package com.poly.nlp.filekommander.views.models;

import java.util.HashMap;

import com.poly.nlp.filekommander.FileKommander;

/**
 * Model to hold information about create action . 
 *  
 * @author jake
 *
 */
public class CreateModel implements GenericActionModel {
	// Is create action run 
	private boolean isModelRun;
	
	// list of files as key and error message if any as value
	private HashMap<String, String> fileListData;
	// list of folders as key and error message if any as value
	private HashMap<String, String> folderListData;

	/**
	 * Create an empty model
	 */
	public CreateModel() {
		this.fileListData = new HashMap<String, String>();
		this.folderListData = new HashMap<String, String>();
		this.isModelRun = false ;
	}

	/**
	 * Add a new file/folder to the model
	 * @param name
	 * @param type
	 */
	public void add(String name, int type) {
		if (type == FileKommander.DIRECTORY) {
			this.folderListData.put(name, "");
		} else if (type == FileKommander.FILE) {
			this.fileListData.put(name, "");
		}
	}

	/**
	 * @return the fileListData
	 */
	public HashMap<String, String> getFileListData() {
		return fileListData;
	}

	/**
	 * @param fileListData
	 *            the fileListData to set
	 */
	public void setFileListData(HashMap<String, String> fileListData) {
		this.fileListData = fileListData;
	}

	/**
	 * @return the folderListData
	 */
	public HashMap<String, String> getFolderListData() {
		return folderListData;
	}

	/**
	 * @param folderListData
	 *            the folderListData to set
	 */
	public void setFolderListData(HashMap<String, String> folderListData) {
		this.folderListData = folderListData;
	}

	/**
	 * @return the isModelRun
	 */
	public boolean isModelRun() {
		return isModelRun;
	}

	/**
	 * @param isModelRun
	 *            the isModelRun to set
	 */
	public void setModelRun(boolean isModelRun) {
		this.isModelRun = isModelRun;
	}

}
