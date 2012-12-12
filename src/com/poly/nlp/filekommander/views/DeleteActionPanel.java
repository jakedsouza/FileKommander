package com.poly.nlp.filekommander.views;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;

import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

public class DeleteActionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private DeleteModel deleteModel ;
	private String actionStringFile;
	private String actionStringFolder;
//	private ArrayList<String> fileListData;
//	private ArrayList<String> folderListData;
	private JLabel actionLabelFile;
	private JLabel folderListLabel;
	private JLabel actionLabelFolder;
	private JLabel fileListlabel;
	private JPanel filepanel;
	private JPanel folderPanel;

	/**
	 * Create the panel.
	 */
	public DeleteActionPanel() {
		setBackground(new Color(255, 255, 204));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		filepanel = new JPanel();
		filepanel.setBackground(new Color(255, 255, 204));
		springLayout.putConstraint(SpringLayout.NORTH, filepanel, 0,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, filepanel, 0,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, filepanel, -95,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, filepanel, 0,
				SpringLayout.EAST, this);
		add(filepanel);

		folderPanel = new JPanel();
		folderPanel.setBackground(new Color(255, 255, 204));
		springLayout.putConstraint(SpringLayout.NORTH, folderPanel, 0,
				SpringLayout.SOUTH, filepanel);
		springLayout.putConstraint(SpringLayout.WEST, folderPanel, 0,
				SpringLayout.WEST, filepanel);
		springLayout.putConstraint(SpringLayout.SOUTH, folderPanel, 0,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, folderPanel, 0,
				SpringLayout.EAST, filepanel);
		SpringLayout sl_filepanel = new SpringLayout();
		filepanel.setLayout(sl_filepanel);

		actionLabelFile = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, actionLabelFile, 0,
				SpringLayout.NORTH, filepanel);
		springLayout.putConstraint(SpringLayout.WEST, actionLabelFile, 20,
				SpringLayout.EAST, filepanel);
		springLayout.putConstraint(SpringLayout.EAST, actionLabelFile, 2432,
				SpringLayout.WEST, this);
		filepanel.add(actionLabelFile);
		actionLabelFile.setForeground(new Color(102, 102, 102));

		fileListlabel = new JLabel("");
		sl_filepanel.putConstraint(SpringLayout.WEST, actionLabelFile, 0,
				SpringLayout.WEST, fileListlabel);
		sl_filepanel.putConstraint(SpringLayout.EAST, actionLabelFile, 0,
				SpringLayout.EAST, fileListlabel);
		springLayout.putConstraint(SpringLayout.SOUTH, actionLabelFile, -6,
				SpringLayout.NORTH, fileListlabel);
		fileListlabel.setVerticalAlignment(SwingConstants.TOP);
		sl_filepanel.putConstraint(SpringLayout.SOUTH, fileListlabel, -10,
				SpringLayout.SOUTH, filepanel);
		sl_filepanel.putConstraint(SpringLayout.EAST, fileListlabel, -10,
				SpringLayout.EAST, filepanel);
		sl_filepanel.putConstraint(SpringLayout.NORTH, fileListlabel, 20,
				SpringLayout.NORTH, filepanel);
		sl_filepanel.putConstraint(SpringLayout.WEST, fileListlabel, 10,
				SpringLayout.WEST, filepanel);
		filepanel.add(fileListlabel);
		fileListlabel.setForeground(new Color(0, 153, 204));
		springLayout.putConstraint(SpringLayout.WEST, fileListlabel, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, fileListlabel, 450,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, fileListlabel, 6,
				SpringLayout.SOUTH, actionLabelFile);
		add(folderPanel);
		SpringLayout sl_folderPanel = new SpringLayout();
		folderPanel.setLayout(sl_folderPanel);

		actionLabelFolder = new JLabel("");
		sl_folderPanel.putConstraint(SpringLayout.NORTH, actionLabelFolder, 5,
				SpringLayout.NORTH, folderPanel);
		sl_folderPanel.putConstraint(SpringLayout.WEST, actionLabelFolder, 10,
				SpringLayout.WEST, folderPanel);
		sl_folderPanel.putConstraint(SpringLayout.EAST, actionLabelFolder, 450,
				SpringLayout.WEST, folderPanel);
		folderPanel.add(actionLabelFolder);
		actionLabelFolder.setForeground(new Color(102, 102, 102));
		springLayout.putConstraint(SpringLayout.WEST, actionLabelFolder, 0,
				SpringLayout.WEST, actionLabelFile);
		springLayout.putConstraint(SpringLayout.EAST, actionLabelFolder, 0,
				SpringLayout.EAST, actionLabelFile);
		springLayout.putConstraint(SpringLayout.SOUTH, fileListlabel, -6,
				SpringLayout.NORTH, actionLabelFolder);

		folderListLabel = new JLabel("");
		sl_folderPanel.putConstraint(SpringLayout.NORTH, folderListLabel, 6,
				SpringLayout.SOUTH, actionLabelFolder);
		sl_folderPanel.putConstraint(SpringLayout.WEST, folderListLabel, 10,
				SpringLayout.WEST, folderPanel);
		sl_folderPanel.putConstraint(SpringLayout.SOUTH, folderListLabel, -10,
				SpringLayout.SOUTH, folderPanel);
		sl_folderPanel.putConstraint(SpringLayout.EAST, folderListLabel, 0,
				SpringLayout.EAST, actionLabelFolder);
		folderPanel.add(folderListLabel);
		springLayout.putConstraint(SpringLayout.NORTH, folderListLabel, 118,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, folderListLabel, -10,
				SpringLayout.SOUTH, this);
		folderListLabel.setForeground(new Color(0, 153, 204));
		springLayout.putConstraint(SpringLayout.WEST, folderListLabel, 0,
				SpringLayout.WEST, actionLabelFile);
		springLayout.putConstraint(SpringLayout.EAST, folderListLabel, 0,
				SpringLayout.EAST, actionLabelFile);
		springLayout.putConstraint(SpringLayout.SOUTH, actionLabelFolder, -6,
				SpringLayout.NORTH, folderListLabel);

	}

	public void updatePanelData(DeleteModel deleteModel) {
		this.deleteModel = deleteModel;	
		updatePanelData();
	}

	public void updatePanelData() {
		ArrayList<String> fileListData = this.deleteModel.getFileListData();
		ArrayList<String> folderListData= this.deleteModel.getFolderListData();
		if (fileListData.size() > 1) {
			actionStringFile = "The following files will be created";
		} else if (fileListData.size() == 0) {
			actionStringFile = "";
		} else {
			actionStringFile = "The following file will be created";
		}
		if (folderListData.size() > 1) {
			actionStringFile = "The following folders will be created";
		} else if (folderListData.size() == 0) {
			actionStringFile = "";
		} else {
			actionStringFile = "The following folders will be created";
		}

		actionLabelFile.setText(actionStringFile);
		actionLabelFolder.setText(actionStringFolder);
		folderListLabel.setText(folderListLabel.toString());
		fileListlabel.setText(fileListData.toString());
		if(fileListData == null || fileListData.size() == 0 ){
			filepanel.setVisible(false);
		}
		if(folderListData == null || folderListData.size() == 0 ){
			folderPanel.setVisible(false);
		}
		this.repaint() ;
		//this.paintAll(this.getGraphics());

	}

	/**
	 * @return the actionString
	 */
	public String getActionStringFile() {
		return actionStringFile;
	}

	/**
	 * @param actionString
	 *            the actionString to set
	 */
	public void setActionStringFile(String actionStringFile) {
		this.actionStringFile = actionStringFile;
	}

	/**
	 * @return the actionStringFolder
	 */
	public String getActionStringFolder() {
		return actionStringFolder;
	}

	/**
	 * @param actionStringFolder
	 *            the actionStringFolder to set
	 */
	public void setActionStringFolder(String actionStringFolder) {
		this.actionStringFolder = actionStringFolder;
	}

	

	public JLabel getActionLabelFile() {
		return actionLabelFile;
	}

	public JLabel getFolderListLabel() {
		return folderListLabel;
	}

	public JLabel getActionLabelFolder() {
		return actionLabelFolder;
	}

	public JLabel getFileListlabel() {
		return fileListlabel;
	}
	public JPanel getFilepanel() {
		return filepanel;
	}
	public JPanel getFolderPanel() {
		return folderPanel;
	}

	/**
	 * @return the createModel
	 */
	public DeleteModel getDeleteModel() {
		return deleteModel;
	}

	/**
	 * @param createModel the createModel to set
	 */
	public void setDeleteModel(DeleteModel deleteModel) {
		this.deleteModel = deleteModel;
	}
}
