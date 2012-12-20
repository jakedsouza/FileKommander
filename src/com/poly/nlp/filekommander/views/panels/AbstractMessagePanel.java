package com.poly.nlp.filekommander.views.panels;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import org.apache.log4j.Logger;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

public abstract class AbstractMessagePanel extends GenericPanel {

	private static final long serialVersionUID = 1L;
	private String actionString;
	private JPanel messagePanel;
	private JPanel commandPanel;
	private static final Logger log = Logger
			.getLogger(AbstractMessagePanel.class);
	private JTable table;
	private JButton btnClear;

	/**
	 * Create the panel.
	 */
	public AbstractMessagePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		setMessagePanel(new JPanel());
		springLayout.putConstraint(SpringLayout.NORTH, getMessagePanel(), 0,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, getMessagePanel(), 0,
				SpringLayout.EAST, this);
		getMessagePanel().setAutoscrolls(true);
		add(getMessagePanel());
		getMessagePanel().setLayout(null);
		setCommandPanel(new JPanel());
		springLayout.putConstraint(SpringLayout.SOUTH, getMessagePanel(), -6,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, getCommandPanel(), 0,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, getCommandPanel(), 0,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, getCommandPanel(), 0,
				SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, getCommandPanel(), -40,
				SpringLayout.SOUTH, this);
		add(getCommandPanel());
		{
			setBtnClear(new JButton("Clear"));
			commandPanel.add(getBtnClear());
		}

		setTable(new JTable());
		getTable().setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		getTable().setBounds(386, 217, -324, -173);
		getMessagePanel().add(getTable());

	}

	private JLabel createDefaultObjectLabel(String labelText, int type) {
		JLabel lblNewLabel = new JLabel(labelText);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		if (type == FileKommander.FILE) {
			lblNewLabel
					.setIcon(new ImageIcon(
							CreateActionPanel.class
									.getResource("/com/poly/nlp/filekommander/views/icon/file.png")));
		} else if (type == FileKommander.DIRECTORY) {
			lblNewLabel
					.setIcon(new ImageIcon(
							CreateActionPanel.class
									.getResource("/com/poly/nlp/filekommander/views/icon/folder.png")));
		}
		return lblNewLabel;
	}

	public void resetPanel() {
		this.updatePanelData();
		FileKommanderRun.getGuiv2().reset();
	}

	/**
	 * @param actionString
	 *            the actionString to set
	 */
	public void setActionString(String actionString) {
		this.actionString = actionString;
	}

	/**
	 * @return the actionString
	 */
	public String getActionString() {
		return actionString;
	}

	public JPanel getMessagePanel() {
		return messagePanel;
	}
	/**
	 * @param messagePanel the messagePanel to set
	 */
	public void setMessagePanel(JPanel messagePanel) {
		this.messagePanel = messagePanel;
	}

	public JPanel getCommandPanel() {
		return commandPanel;
	}

	/**
	 * @param commandPanel the commandPanel to set
	 */
	public void setCommandPanel(JPanel commandPanel) {
		this.commandPanel = commandPanel;
	}

	public JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the btnClear
	 */
	public JButton getBtnClear() {
		return btnClear;
	}

	/**
	 * @param btnClear the btnClear to set
	 */
	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
	}

	public abstract void updatePanelData(GenericActionModel model);

	public abstract void updatePanelData();

	public abstract void updatePanelDataAfterAction();



}
