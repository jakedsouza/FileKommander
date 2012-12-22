package com.poly.nlp.filekommander.views.models;

import java.util.HashMap;

public class PhraseOperationModel implements GenericActionModel{
	
	private HashMap<String, String> fileListData;
	private int operationType;
	private String repetition ; 
	private String position; 

	private String newPhrase ;
	private String oldPhrase ;
	
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

	public String getNewPhrase() {
		return newPhrase;
	}

	public void setNewPhrase(String newPhrase) {
		this.newPhrase = newPhrase;
	}

	public String getOldPhrase() {
		return oldPhrase;
	}

	public void setOldPhrase(String oldPhrase) {
		this.oldPhrase = oldPhrase;
	}

	/**
	 * @return the repetition
	 */
	public String getRepetition() {
		return repetition;
	}

	/**
	 * @param repetition the repetition to set
	 */
	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
}
