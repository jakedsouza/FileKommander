package com.poly.nlp.filekommander.views.models;

/**
 * Model to hold information about create action .
 * 
 * @author jake
 * 
 */
public class RenameModel implements GenericActionModel {
	// Is create action run
	/**
	 */
	private boolean isModelRun;
	/**
	 */
	private String oldFileName;
	/**
	 */
	private String newFileName;
	/**
	 */
	private String errorMessage;
	/**
	 */
	private int type ;

	/**
	 * Create an empty model
	 */
	public RenameModel() {
		oldFileName = "";
		newFileName = "";
		errorMessage = "";
		this.isModelRun = false;
	}

	/**
	 * @return  the isModelRun
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

	/**
	 * @return  the oldFileName
	 */
	public String getOldFileName() {
		return oldFileName;
	}

	/**
	 * @param oldFileName  the oldFileName to set
	 */
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	/**
	 * @return  the newFileName
	 */
	public String getNewFileName() {
		return newFileName;
	}

	/**
	 * @param newFileName  the newFileName to set
	 */
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	/**
	 * @return  the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage  the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return  the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type  the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
