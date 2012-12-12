package com.poly.nlp.filekommander.views;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;

import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

public class EmptyActionPanel extends GenericPanel {

	private static final long serialVersionUID = 1L;
    private CreateModel createModel ;
	private String actionStringFile;
	private String actionStringFolder;

	/**
	 * Create the panel.
	 */
	public EmptyActionPanel() {
	}

	@Override
	public void updatePanelData(GenericActionModel actionModel) {		
	}

	@Override
	public void updatePanelData() {		
	}
	
}
