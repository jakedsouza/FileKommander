package com.poly.nlp.filekommander.views.models;

import java.util.ArrayList;

import com.poly.nlp.filekommander.FileKommander;

public class CreateModel implements GenericActionModel{
	
	private ArrayList<String> fileListData;
	private ArrayList<String> folderListData;
	// private ArrayList<File> folderListData;

	public CreateModel(){
		this.fileListData = new ArrayList<String>() ;
		this.folderListData = new ArrayList<String>();
	}
	
	public void add(String name, int type) {
		if(type == FileKommander.DIRECTORY){
			this.folderListData.add(name);			
		}else if(type == FileKommander.FILE){
			this.fileListData.add(name);
		}		
	}

	/**
	 * @return the fileListData
	 */
	public ArrayList<String> getFileListData() {
		return fileListData;
	}

	/**
	 * @param fileListData the fileListData to set
	 */
	public void setFileListData(ArrayList<String> fileListData) {
		this.fileListData = fileListData;
	}

	/**
	 * @return the folderListData
	 */
	public ArrayList<String> getFolderListData() {
		return folderListData;
	}

	/**
	 * @param folderListData the folderListData to set
	 */
	public void setFolderListData(ArrayList<String> folderListData) {
		this.folderListData = folderListData;
	}

}
