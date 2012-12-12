package com.poly.nlp.filekommander.views;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;

import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

public class CreateActionPanel extends GenericPanel {

	private static final long serialVersionUID = 1L;
    private CreateModel createModel ;
	private String actionString;
//	private ArrayList<String> fileListData;
//	private ArrayList<String> folderListData;
	private JLabel actionLabelFile;
	private JPanel filepanel;
	private JPanel fileListPanel;
	private JPanel commandPanel;
	private JButton btnAccept;
	private JButton btnReject;
	private static final Logger log = Logger.getLogger(FileKommander.class);

	/**
	 * Create the panel.
	 */
	public CreateActionPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		filepanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, filepanel, 0, SpringLayout.NORTH, this);
		filepanel.setAutoscrolls(true);
		springLayout.putConstraint(SpringLayout.WEST, filepanel, 0,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, filepanel, 0,
				SpringLayout.EAST, this);
		add(filepanel);
		filepanel.setLayout(null);

		actionLabelFile = new JLabel("");
		actionLabelFile.setBounds(10, 0, 440, 20);
		springLayout.putConstraint(SpringLayout.NORTH, actionLabelFile, 0,
				SpringLayout.NORTH, filepanel);
		springLayout.putConstraint(SpringLayout.WEST, actionLabelFile, 20,
				SpringLayout.EAST, filepanel);
		springLayout.putConstraint(SpringLayout.EAST, actionLabelFile, 2432,
				SpringLayout.WEST, this);
		filepanel.add(actionLabelFile);
		actionLabelFile.setForeground(new Color(102, 102, 102));
		
		fileListPanel = new JPanel();
		fileListPanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) fileListPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		fileListPanel.setBackground(new Color(255, 255, 204));
		fileListPanel.setAutoscrolls(true);
		fileListPanel.setBounds(10, 29, 440, 133);
		filepanel.add(fileListPanel);
				
				commandPanel = new JPanel();
				springLayout.putConstraint(SpringLayout.SOUTH, filepanel, -6, SpringLayout.NORTH, commandPanel);
				springLayout.putConstraint(SpringLayout.WEST, commandPanel, 0, SpringLayout.WEST, this);
				springLayout.putConstraint(SpringLayout.SOUTH, commandPanel, 0, SpringLayout.SOUTH, this);
				springLayout.putConstraint(SpringLayout.EAST, commandPanel, 0, SpringLayout.EAST, this);
				springLayout.putConstraint(SpringLayout.NORTH, commandPanel, -40, SpringLayout.SOUTH, this);
				add(commandPanel);
				
				btnAccept = new JButton("");
				btnAccept.setFocusPainted(false);
				btnAccept.setMargin(new Insets(0, 0, 0, 0));
				btnAccept.setContentAreaFilled(false);
				btnAccept.setBorderPainted(true);
				btnAccept.setOpaque(true);
				btnAccept.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/accept.png")));
				commandPanel.add(btnAccept);
				
				btnReject = new JButton("");
				btnReject.setFocusPainted(false);
				btnReject.setMargin(new Insets(0, 0, 0, 0));
				btnReject.setContentAreaFilled(false);
				btnReject.setBorderPainted(true);
				btnReject.setOpaque(true);
				btnReject.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/reject.png")));
				commandPanel.add(btnReject);
	}

	public void updatePanelData(GenericActionModel createModel) {
		if(createModel instanceof CreateModel){
		this.createModel = (CreateModel)createModel;	
		updatePanelData();
		}else{
			log.error("Wrong class");
		}
	}

	public void updatePanelData() {
		// folderListPanel.removeAll();
		fileListPanel.removeAll();
		ArrayList<String> fileListData = this.createModel.getFileListData();
		ArrayList<String> folderListData= this.createModel.getFolderListData();
		if (fileListData.size() > 1) {
			actionString = "The following files/folders will be created";
		} else if (fileListData.size() == 0) {
			actionString = "";
		} else {
			actionString = "The following file/folder will be created";
		}
	
		actionLabelFile.setText(actionString);
		
		for (String file : fileListData) {
			JLabel jLabel = createDefaultObjectLabel(file,FileKommander.FILE);
			fileListPanel.add(jLabel);
		}
		for (String folder : folderListData) {
			JLabel jLabel = createDefaultObjectLabel(folder,FileKommander.DIRECTORY);
			fileListPanel.add(jLabel);
		}
	//	folderListLabel.setText("<html>"+folderListData.toString()+"</html>");
	//	fileListlabel.setText("<html>"+fileListData.toString()+"<html>");
		if(fileListData == null || fileListData.size() == 0 ){
			filepanel.setVisible(false);
		}else{
			filepanel.setVisible(true);
		}
		//this.repaint() ;
		//this.paintAll(this.getGraphics());

	}
	
	private JLabel createDefaultObjectLabel(String labelText,int type){
		JLabel lblNewLabel = new JLabel(labelText);
		//lblNewLabel.setBorder(new LineBorder(new Color(135, 206, 235)));
		lblNewLabel.setForeground(new Color(0, 0, 0));
	//	lblNewLabel.setOpaque(true);
	//	lblNewLabel.setBackground(new Color(176, 224, 230));
		if(type == FileKommander.FILE){
		lblNewLabel.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/file.png")));
		}else if(type == FileKommander.DIRECTORY){
			lblNewLabel.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/folder.png")));	
		}
//		JLabel lblNewLabel = new JLabel(labelText);
//		lblNewLabel.setForeground(new Color(0, 153, 204));
//		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 153, 51)));		
		return lblNewLabel;		
	}	

	public JLabel getActionLabelFile() {
		return actionLabelFile;
	}

	public JPanel getFilepanel() {
		return filepanel;
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
	public JPanel getFileListPanel() {
		return fileListPanel;
	}
	
	public JButton getBtnAccept() {
		return btnAccept;
	}
	public JButton getBtnReject() {
		return btnReject;
	}
}
