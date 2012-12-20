package com.poly.nlp.filekommander.views.models;

import java.util.HashMap;

public class PhraseOperationModel implements GenericActionModel{
	
	private HashMap<String, String> fileListData;
	private int operationType;
	
	public PhraseOperationModel(){
		this.fileListData = new HashMap<String, String>() ;
	}
	
	public void add(String name) {
		this.fileListData.put(name, "");	
	}

	/**
	 * @return the fileListData
	 */
	public HashMap<String, String> getFileListData() {
		return fileListData;
	}

	/**
	 * @param fileListData the fileListData to set
	 */
	public void setFileListData(HashMap<String, String> fileListData) {
		this.fileListData = fileListData;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
	
}
