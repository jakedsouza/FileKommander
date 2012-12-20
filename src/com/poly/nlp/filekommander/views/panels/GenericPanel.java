package com.poly.nlp.filekommander.views.panels;

import javax.swing.JPanel;

import com.poly.nlp.filekommander.views.models.GenericActionModel;

public abstract class GenericPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Update the panel before action is taken using the given action model
	 * 
	 * @param actionModel
	 */
	public abstract void updatePanelData(GenericActionModel actionModel);

	/**
	 * Update the panel before action is taken
	 */
	public abstract void updatePanelData();

	/**
	 * updates the create panel after the action is run
	 */
	public abstract void updatePanelDataAfterAction();

	/**
	 * Resets the panel to default state
	 */
	public abstract void resetPanel();
}
