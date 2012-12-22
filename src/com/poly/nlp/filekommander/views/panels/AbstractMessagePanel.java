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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class AbstractMessagePanel extends GenericPanel {

	private static final long serialVersionUID = 1L;
	private String actionString;
	private JPanel commandPanel;
	private static final Logger log = Logger
			.getLogger(AbstractMessagePanel.class);
	private JButton btnClear;
	private JLabel actionLabel;
	private JPanel messagePanel;

	/**
	 * Create the panel.
	 */
	public AbstractMessagePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setCommandPanel(new JPanel());
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
		
		setActionLabel(new JLabel(""));
		springLayout.putConstraint(SpringLayout.NORTH, actionLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, actionLabel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, actionLabel, 0, SpringLayout.EAST, commandPanel);
		add(getActionLabel());
				
		messagePanel = new JPanel();
		messagePanel.setAutoscrolls(true);
				springLayout.putConstraint(SpringLayout.SOUTH, actionLabel, -15, SpringLayout.NORTH, messagePanel);
				springLayout.putConstraint(SpringLayout.NORTH, messagePanel, 39, SpringLayout.NORTH, this);
				springLayout.putConstraint(SpringLayout.WEST, messagePanel, 0, SpringLayout.WEST, commandPanel);
				springLayout.putConstraint(SpringLayout.SOUTH, messagePanel, -6, SpringLayout.NORTH, commandPanel);
				springLayout.putConstraint(SpringLayout.EAST, messagePanel, 440, SpringLayout.WEST, this);
				add(messagePanel);
				messagePanel.setLayout(new GridLayout(1, 0, 0, 0));
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resetPanel();
					}
				});					
	}

	protected JPanel createDefaultMessageRow(String [] row){
		JPanel jPanel = new JPanel();
		
		for (String data : row) {
			JLabel jLabel = new JLabel(data);
		
			jPanel.add(jLabel);
		}
		//jPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		return jPanel;
		
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
		this.messagePanel = messagePanel ;
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

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		messagePanel.setLayout(null);

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
	public JLabel getActionLabel() {
		return actionLabel;
	}

	/**
	 * @param actionLabel the actionLabel to set
	 */
	public void setActionLabel(JLabel actionLabel) {
		this.actionLabel = actionLabel;
	}
	
}
