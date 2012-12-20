package com.poly.nlp.filekommander.views.panels;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.file.actions.CallAction;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.RenameModel;

public class RenameActionPanel extends AbstractFileFolderPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RenameModel renameModel;
	private static final Logger log = Logger.getLogger(CreateActionPanel.class);

	@Override
	public void acceptButtonClickAction(ActionEvent e) {
		renameModel = (RenameModel) CallAction.callAction(renameModel);
		log.info("Updating panel after run");
		updatePanelDataAfterAction();
		log.info("Done updating panel after run");
	}

	@Override
	public void updatePanelData(GenericActionModel renameModel) {
		if (renameModel instanceof RenameModel) {
			this.renameModel = (RenameModel) renameModel;
			updatePanelData();
		} else {
			log.error("Wrong class");
		}
	}

	@Override
	public void updatePanelData() {
		setActionString("");
		getFileListPanel().removeAll();
		String oldFileName = this.renameModel.getOldFileName();
		String newFileName = this.renameModel.getNewFileName();
		if (oldFileName.equals("") || newFileName.equals("")) {
			getFilepanel().setVisible(false);
			log.error("Old or new Name is empty");
		}
		setActionString(oldFileName + " will be renamed to " + newFileName);
		getActionLabelFile().setText(getActionString());
		if (this.renameModel.getType() == FileKommander.FILE) {
			JLabel jLabel = createDefaultObjectLabel(oldFileName,
					FileKommander.FILE);
			getFileListPanel().add(jLabel);

			JLabel jLabel2 = createDefaultObjectLabel(newFileName,
					FileKommander.FILE);
			getFileListPanel().add(jLabel2);

		} else if (this.renameModel.getType() == FileKommander.DIRECTORY) {
			JLabel jLabel = createDefaultObjectLabel(oldFileName,
					FileKommander.DIRECTORY);
			getFileListPanel().add(jLabel);

			JLabel jLabel2 = createDefaultObjectLabel(newFileName,
					FileKommander.DIRECTORY);
			getFileListPanel().add(jLabel2);
		}
		this.paintAll(this.getGraphics());
	}

	@Override
	public void updatePanelDataAfterAction() {
		if (!this.renameModel.isModelRun()) {
			log.error("Action has not yet been run");
		}
		setActionString("");
		getFileListPanel().removeAll();
		String oldFileName = this.renameModel.getOldFileName();
		String newFileName = this.renameModel.getNewFileName();
		if (this.renameModel.getErrorMessage().equals("")) {
			setActionString("Rename was successfull");
			JLabel jLabel = createObjectLabelAfterAction(oldFileName,
					this.renameModel.getType(),
					this.renameModel.getErrorMessage());
			fileListPanel.add(jLabel);
			JLabel jLabel2 = createObjectLabelAfterAction(newFileName,
					this.renameModel.getType(),
					this.renameModel.getErrorMessage());
			fileListPanel.add(jLabel2);
		} else {
			setActionString(this.renameModel.getErrorMessage());
			JLabel jLabel = createObjectLabelAfterAction(oldFileName,
					this.renameModel.getType(),
					this.renameModel.getErrorMessage());
			fileListPanel.add(jLabel);
			JLabel jLabel2 = createObjectLabelAfterAction(newFileName,
					this.renameModel.getType(),
					this.renameModel.getErrorMessage());
			fileListPanel.add(jLabel2);
		}
		getActionLabelFile().setText(getActionString());

		this.repaint();
	}

	/**
	 * @return the renameModel
	 */
	public RenameModel getRenameModel() {
		return renameModel;
	}

	/**
	 * @param renameModel
	 *            the renameModel to set
	 */
	public void setRenameModel(RenameModel renameModel) {
		this.renameModel = renameModel;
	}

}
