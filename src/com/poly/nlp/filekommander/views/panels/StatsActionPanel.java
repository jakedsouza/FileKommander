package com.poly.nlp.filekommander.views.panels;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.cmp.CAKeyUpdAnnContent;import com.poly.nlp.filekommander.file.actions.CallAction;


import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.StatsModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class StatsActionPanel extends AbstractMessagePanel {

	private static final long serialVersionUID = 1L;
    private StatsModel statsModel ;
	private static final Logger log = Logger.getLogger(StatsActionPanel.class);

	/**
	 * Create the panel.
	 */
	public StatsActionPanel() {
		super();
	//	getTable().setBounds(0, 0, 430, 204);
//		SpringLayout springLayout = new SpringLayout();
//		setLayout(springLayout);
//
//		messagePanel = new JPanel();
//		springLayout.putConstraint(SpringLayout.NORTH, messagePanel, 0, SpringLayout.NORTH, this);
//		springLayout.putConstraint(SpringLayout.EAST, messagePanel, 0,
//				SpringLayout.EAST, this);
//		messagePanel.setAutoscrolls(true);
//		add(messagePanel);
//		messagePanel.setLayout(null);
//			commandPanel = new JPanel();
//			springLayout.putConstraint(SpringLayout.WEST, messagePanel, 0, SpringLayout.WEST, commandPanel);
//			springLayout.putConstraint(SpringLayout.SOUTH, messagePanel, -6, SpringLayout.NORTH, commandPanel);
//				
//				table = new JTable();
//				table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//				table.setBounds(386, 217, -324, -173);
//				messagePanel.add(table);
//				springLayout.putConstraint(SpringLayout.WEST, commandPanel, 0, SpringLayout.WEST, this);
//				springLayout.putConstraint(SpringLayout.SOUTH, commandPanel, 0, SpringLayout.SOUTH, this);
//				springLayout.putConstraint(SpringLayout.EAST, commandPanel, 0, SpringLayout.EAST, this);
//				springLayout.putConstraint(SpringLayout.NORTH, commandPanel, -40, SpringLayout.SOUTH, this);
//				add(commandPanel);
//				
//				btnReject = new JButton("");
//				btnReject.setFocusPainted(false);
//				btnReject.setMargin(new Insets(0, 0, 0, 0));
//				btnReject.setContentAreaFilled(false);
//				btnReject.setBorderPainted(true);
//				btnReject.setOpaque(true);
//				btnReject.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/reject.png")));
//				commandPanel.add(btnReject);
	}

	public void updatePanelData(GenericActionModel statsModel) {
		if(statsModel instanceof StatsModel){
		this.statsModel = (StatsModel)statsModel;	
		updatePanelData();
		}else{
			log.error("Wrong class");
		}
	}

	public void updatePanelData() {
		getMessagePanel().removeAll();
		statsModel = (StatsModel) CallAction.callAction(statsModel);
		HashMap<String, String> fileListData = this.statsModel.getFileListData();
		HashMap<String, String> folderListData= this.statsModel.getFolderListData();
		
		if (fileListData.size() > 1) {
			setActionString("The details for the following files/folders will be shown");
		} else if (fileListData.size() == 0) {
			setActionString("");
		} else {
			setActionString("The details for the following file/folder will be shown");
		}
		getActionLabel().setText(getActionString());
		//getActionLabel().setText("");
	 //  DefaultTableModel model = getTableModel();    
	    //getTable().setModel(model);
		//setTable(new javax.swing.JTable(model));     
		//getTable().setModel(new DefaultTableModel());    
		//model.setRowCount(2);
		
		Set<String> col = fileListData.keySet();
		Iterator<String> iterator = col.iterator();
		
		while(iterator.hasNext())
		{	
			String key = iterator.next();
			String value= fileListData.get(key);
			
			String [] data = new String[2];
			data[0] = key;
			data[1]= value;
			getMessagePanel().add(createDefaultMessageRow(data));
		//	model.addRow(data); //Adds Row to JTable
		}		
		
		Set<String> folder = folderListData.keySet();
		Iterator<String> iterator2 = folder.iterator();
		
		while(iterator2.hasNext())
		{	
			String key = iterator2.next();
			String value= folderListData.get(key);
			
			String [] data = new String[2];
			data[0] = key;
			data[1]= value;
			getMessagePanel().add(createDefaultMessageRow(data));
		//	model.addRow(data); //Adds Row to JTable
		}
		//setTableModel(model);
	//	getTable().paint(getTable().getGraphics());
// getTable().setModel(model);
//		if(fileListData == null && fileListData.size() == 0 || folderListData == null && folderListData.size() == 0 ){
//			getMessagePanel().setVisible(false);
//		}else{
//			getMessagePanel().setVisible(true);
//		}
this.paint(this.getGraphics());
	}

	@Override
	public void updatePanelDataAfterAction() {
		// TODO Auto-generated method stub
		
	}
	
//	private JLabel createDefaultObjectLabel(String labelText,int type){
//		JLabel lblNewLabel = new JLabel(labelText);
//		//lblNewLabel.setBorder(new LineBorder(new Color(135, 206, 235)));
//		lblNewLabel.setForeground(new Color(0, 0, 0));
//	//	lblNewLabel.setOpaque(true);
//	//	lblNewLabel.setBackground(new Color(176, 224, 230));
//		if(type == FileKommander.FILE){
//		lblNewLabel.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/file.png")));
//		}else if(type == FileKommander.DIRECTORY){
//			lblNewLabel.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/folder.png")));	
//		}
////		JLabel lblNewLabel = new JLabel(labelText);
////		lblNewLabel.setForeground(new Color(0, 153, 204));
////		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 153, 51)));		
//		return lblNewLabel;		
//	}	

//	public JButton getBtnReject() {
//		return btnReject;
//	}



}
