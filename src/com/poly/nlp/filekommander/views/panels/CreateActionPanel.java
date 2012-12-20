package com.poly.nlp.filekommander.views.panels;

import java.util.HashMap;
import javax.swing.JLabel;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.file.actions.CallAction;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import org.apache.log4j.Logger;
import java.awt.event.ActionEvent;

public class CreateActionPanel extends AbstractFileFolderPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreateModel createModel;
	private static final Logger log = Logger.getLogger(CreateActionPanel.class);

	@Override
	public void acceptButtonClickAction(ActionEvent e) {
		 createModel = (CreateModel) CallAction.callAction(createModel);
		 log.info("Updating panel after run");
		 updatePanelDataAfterAction();		
		 log.info("Done updating panel after run");

	}

	/**
	 * @return the createModel
	 */
	public CreateModel getCreateModel() {
		return createModel;
	}

	/**
	 * @param createModel the createModel to set
	 */
	public void setCreateModel(CreateModel createModel) {
		this.createModel = createModel;
	}

	@Override
	public void updatePanelData(GenericActionModel createModel) {
		if (createModel instanceof CreateModel) {
			this.createModel = (CreateModel) createModel;
			updatePanelData();
		} else {
			log.error("Wrong class");
		}		
	}

	@Override
	public void updatePanelData() {
		setActionString("");
		getFileListPanel().removeAll();
		HashMap<String,String> fileListData = this.createModel.getFileListData();
		HashMap<String,String> folderListData = this.createModel.getFolderListData();
		if (fileListData.size() > 1 || folderListData.size()>1) {
			setActionString("The following files/folders will be created");
		} else if (fileListData.size() == 0 && folderListData.size()==0) {
			setActionString("");
		} else {
			setActionString("The following file/folder will be created");
		}

		getActionLabelFile().setText(getActionString());

		for (String file : fileListData.keySet()) {
			JLabel jLabel = createDefaultObjectLabel(file, FileKommander.FILE);
			getFileListPanel().add(jLabel);
		}
		for (String folder : folderListData.keySet()) {
			JLabel jLabel = createDefaultObjectLabel(folder,
					FileKommander.DIRECTORY);
			getFileListPanel().add(jLabel);
		}
		if (fileListData.size() == 0 &&(folderListData.size() == 0)) {
			getFilepanel().setVisible(false);
		} else {
			getFilepanel().setVisible(true); 
		}
		this.paintAll(this.getGraphics());		
	}

	@Override
	public void updatePanelDataAfterAction() {

		if(!this.createModel.isModelRun()){
			log.error("Action has not yet been run");
		}
		
		setActionString("");
		getFileListPanel().removeAll();
		HashMap<String,String> fileListData = this.createModel.getFileListData();
		HashMap<String,String> folderListData = this.createModel.getFolderListData();
		
		getActionLabelFile().setText(getActionString());
		for (String file : fileListData.keySet()) {
			JLabel jLabel = createObjectLabelAfterAction(file, FileKommander.FILE,fileListData.get(file));
			fileListPanel.add(jLabel);
		}
		for (String folder : folderListData.keySet()) {
			String error =folderListData.get(folder);
			JLabel jLabel = createObjectLabelAfterAction(folder, FileKommander.DIRECTORY,error);
			fileListPanel.add(jLabel);
		}
		if (fileListData.size() == 0 && folderListData.size()==0) {
			getActionLabelFile().setText("");
			getFilepanel().setVisible(false);
		} else {
			getActionLabelFile().setText("Created the following files/folders");
			getFilepanel().setVisible(true);
		}
		this.repaint();		
	}
}
