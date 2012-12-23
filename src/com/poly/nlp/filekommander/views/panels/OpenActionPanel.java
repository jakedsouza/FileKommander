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
import com.poly.nlp.filekommander.views.models.ExistsModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.OpenModel;
import com.poly.nlp.filekommander.views.models.StatsModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class OpenActionPanel extends AbstractMessagePanel {

	private static final long serialVersionUID = 1L;
    /**
	 */
    private OpenModel model ;
	private static final Logger log = Logger.getLogger(OpenActionPanel.class);

	/**
	 * Create the panel.
	 */
	public OpenActionPanel() {
		super();	
	}

	public void updatePanelData(GenericActionModel model) {
		if(model instanceof OpenModel){
		this.model = (OpenModel)model;	
		updatePanelData();
		}else{
			log.error("Wrong class");
		}
	}

	public void updatePanelData() {
		getMessagePanel().removeAll();
		model = (OpenModel) CallAction.callAction(model);
		HashMap<String, String> fileListData = this.model.getFileListData();
		HashMap<String, String> folderListData= this.model.getFolderListData();
		
		if (fileListData.size() > 1) {
			setActionString("The details for the following files/folders will be shown");
		} else if (fileListData.size() == 0) {
			setActionString("");
		} else {
			setActionString("The details for the following file/folder will be shown");
		}
		getActionLabel().setText(getActionString());

		
		Set<String> col = fileListData.keySet();
		Iterator<String> iterator = col.iterator();
		
		while(iterator.hasNext())
		{	
			String key = iterator.next();
			String value= fileListData.get(key);
			
			String [] data = new String[2];
			data[0] = key;
			data[0]= value;
			getMessagePanel().add(createDefaultMessageRow(data));
		}		
		
		Set<String> folder = folderListData.keySet();
		Iterator<String> iterator2 = folder.iterator();
		
		while(iterator2.hasNext())
		{	
			String key = iterator2.next();
			String value= folderListData.get(key);
			
			String [] data = new String[2];
			data[0] = key;
			data[0]= value;
			getMessagePanel().add(createDefaultMessageRow(data));
		}
		this.paintAll(this.getGraphics());		
	}

	@Override
	public void updatePanelDataAfterAction() {
		// TODO Auto-generated method stub
		
	}



}
