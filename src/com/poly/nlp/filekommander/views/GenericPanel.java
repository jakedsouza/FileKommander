package com.poly.nlp.filekommander.views;

import javax.swing.JPanel;

import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;

public abstract class GenericPanel  extends JPanel{
	
	
	public abstract void updatePanelData(GenericActionModel actionModel) ;	
	public abstract void updatePanelData();

}
