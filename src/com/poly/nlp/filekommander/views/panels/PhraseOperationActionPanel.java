package com.poly.nlp.filekommander.views.panels;

import java.awt.Color;
import java.awt.Insets;
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

import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.PhraseOperationModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class PhraseOperationActionPanel extends AbstractMessagePanel {

	private static final long serialVersionUID = 1L;
    private PhraseOperationModel model ;	
	private static final Logger log = Logger.getLogger(PhraseOperationActionPanel.class);

	/**
	 * Create the panel.
	 */
	public PhraseOperationActionPanel() {
		super();
//		SpringLayout springLayout = new SpringLayout();
//		setLayout(springLayout);
//
//		setMessagePanel(new JPanel());
//		springLayout.putConstraint(SpringLayout.NORTH, getMessagePanel(), 0, SpringLayout.NORTH, this);
//		springLayout.putConstraint(SpringLayout.EAST, getMessagePanel(), 0,
//				SpringLayout.EAST, this);
//		getMessagePanel().setAutoscrolls(true);
//		add(getMessagePanel());
//		getMessagePanel().setLayout(null);
//			setCommandPanel(new JPanel());
//			springLayout.putConstraint(SpringLayout.WEST, getMessagePanel(), 0, SpringLayout.WEST, getCommandPanel());
//			springLayout.putConstraint(SpringLayout.SOUTH, getMessagePanel(), -6, SpringLayout.NORTH, getCommandPanel());
//				
//				setTable(new JTable());
//				getTable().setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//				getTable().setBounds(386, 217, -324, -173);
//				getMessagePanel().add(getTable());
//				springLayout.putConstraint(SpringLayout.WEST, getCommandPanel(), 0, SpringLayout.WEST, this);
//				springLayout.putConstraint(SpringLayout.SOUTH, getCommandPanel(), 0, SpringLayout.SOUTH, this);
//				springLayout.putConstraint(SpringLayout.EAST, getCommandPanel(), 0, SpringLayout.EAST, this);
//				springLayout.putConstraint(SpringLayout.NORTH, getCommandPanel(), -40, SpringLayout.SOUTH, this);
//				add(getCommandPanel());
//				
//				setBtnClear(new JButton(""));
//				getBtnClear().setFocusPainted(false);
//				getBtnClear().setMargin(new Insets(0, 0, 0, 0));
//				getBtnClear().setContentAreaFilled(false);
//				getBtnClear().setBorderPainted(true);
//				getBtnClear().setOpaque(true);
//				getBtnClear().setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/reject.png")));
//				getCommandPanel().add(getBtnClear());
	}

	public void updatePanelData(GenericActionModel model) {
		if(model instanceof PhraseOperationModel){
		this.model = (PhraseOperationModel)model;	
		updatePanelData();
		}else{
			log.error("Wrong class");
		}
	}

	public void updatePanelData() {
		getMessagePanel().removeAll();
		HashMap<String, String> fileListData = this.model.getFileListData();
		
		if (fileListData.size() > 1) {
			setActionString("The details for the following files/folders will be shown");
		} else if (fileListData.size() == 0) {
			setActionString("");
		} else {
			setActionString("The details for the following file/folder will be shown");
		}
		// model done twice TODO remove 
		DefaultTableModel model = new DefaultTableModel();     
		setTable(new JTable(model));     
		//getTable().setModel(new DefaultTableModel());    
		
		Set<String> keySet = fileListData.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		while(iterator.hasNext())
		{	
			String key = iterator.next();
			String value= fileListData.get(key);
			
			String [] data = new String[2];
			data[0] = key;
			data[1]= value;
			model.addRow(data); //Adds Row to JTable
		}
		getMessagePanel().setVisible(true);
	}
	
//	private JLabel createDefaultObjectLabel(String labelText,int type){
//		JLabel lblNewLabel = new JLabel(labelText);
//		lblNewLabel.setForeground(new Color(0, 0, 0));
//		lblNewLabel.setIcon(new ImageIcon(CreateActionPanel.class.getResource("/com/poly/nlp/filekommander/views/icon/file.png")));
//		return lblNewLabel;		
//	}	


	@Override
	public void updatePanelDataAfterAction() {
		// TODO Auto-generated method stub
		
	}

}
