package com.poly.nlp.filekommander.views.panels;

import java.util.HashMap;

import javax.swing.JLabel;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.file.actions.CallAction;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import org.apache.log4j.Logger;
import java.awt.event.ActionEvent;

public class DeleteActionPanel extends AbstractFileFolderPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeleteModel deleteModel;
	private static final Logger log = Logger.getLogger(DeleteActionPanel.class);

	@Override
	public void acceptButtonClickAction(ActionEvent e) {
		deleteModel = (DeleteModel) CallAction.callAction(deleteModel);
		 log.info("Updating panel after run");
		 updatePanelDataAfterAction();		
		 log.info("Done updating panel after run");

	}
	@Override
	public void updatePanelData(GenericActionModel deleteModel) {
		if (deleteModel instanceof DeleteModel) {
			this.deleteModel= (DeleteModel) deleteModel;
			updatePanelData();
		} else {
			log.error("Wrong class");
		}		
	}

	@Override
	public void updatePanelData() {
		setActionString("");
		getFileListPanel().removeAll();
		HashMap<String,String> fileListData = this.deleteModel.getFileListData();
		HashMap<String,String> folderListData = this.deleteModel.getFolderListData();
		if (fileListData.size() > 1 || folderListData.size()>1) {
			setActionString("The following files/folders will be deleted");
		} else if (fileListData.size() == 0 && folderListData.size()==0) {
			setActionString("");
		} else {
			setActionString("The following file/folder will be deleted");
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

		if(!this.deleteModel.isModelRun()){
			log.error("Action has not yet been run");
		}
		
		setActionString("");
		getFileListPanel().removeAll();
		HashMap<String,String> fileListData = this.deleteModel.getFileListData();
		HashMap<String,String> folderListData = this.deleteModel.getFolderListData();
		
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
