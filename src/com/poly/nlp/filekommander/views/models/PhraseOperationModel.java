package com.poly.nlp.filekommander.views.models;

import java.util.HashMap;

public class PhraseOperationModel implements GenericActionModel{
	
	private HashMap<String, String> fileListData;
	private int operationType;
	private String repetition ; 
	private String phrase ; 
	
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

	/**
	 * @return the phrase
	 */
	public String getPhrase() {
		return phrase;
	}

	/**
	 * @param phrase the phrase to set
	 */
	public void setPhrase(String phrase) {
		this.phrase = phrase;
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
	
}
