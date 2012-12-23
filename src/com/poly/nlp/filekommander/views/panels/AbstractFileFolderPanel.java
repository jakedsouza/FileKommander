package com.poly.nlp.filekommander.views.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import com.poly.nlp.filekommander.FileKommander;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class AbstractFileFolderPanel extends GenericPanel {

	private static final long serialVersionUID = 1L;
	/**
	 */
	private String actionString;
	/**
	 */
	private JLabel actionLabelFile;
	/**
	 */
	private JPanel filepanel;
	/**
	 */
	protected JPanel fileListPanel;
	/**
	 */
	private JPanel commandPanel;
	/**
	 */
	private JButton btnAccept;
	/**
	 */
	private JButton btnReject;
	private static final Logger log = Logger.getLogger(AbstractFileFolderPanel.class);

	/**
	 * Create the panel.
	 */
	public AbstractFileFolderPanel() {
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
		actionLabelFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		actionLabelFile.setBounds(10, 0, 440, 20);
		springLayout.putConstraint(SpringLayout.NORTH, actionLabelFile, 0,
				SpringLayout.NORTH, filepanel);
		springLayout.putConstraint(SpringLayout.WEST, actionLabelFile, 20,
				SpringLayout.EAST, filepanel);
		springLayout.putConstraint(SpringLayout.EAST, actionLabelFile, 2432,
				SpringLayout.WEST, this);
		filepanel.add(actionLabelFile);
		actionLabelFile.setForeground(Color.BLACK);

		fileListPanel = new JPanel();
		fileListPanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) fileListPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		fileListPanel.setBackground(new Color(255, 255, 204));
		fileListPanel.setAutoscrolls(true);
		fileListPanel.setBounds(10, 29, 440, 133);
		filepanel.add(fileListPanel);

		commandPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) commandPanel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(7);
		springLayout.putConstraint(SpringLayout.SOUTH, filepanel, -6, SpringLayout.NORTH, commandPanel);
		springLayout.putConstraint(SpringLayout.WEST, commandPanel, 0,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, commandPanel, 0,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, commandPanel, 0,
				SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, commandPanel, -40,
				SpringLayout.SOUTH, this);
		add(commandPanel);

		btnAccept = new JButton("");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 acceptButtonClickAction(e);
			}
		});
		btnAccept.setFocusPainted(false);
		btnAccept.setMargin(new Insets(0, 0, 0, 0));
		btnAccept.setContentAreaFilled(false);
		btnAccept.setBorderPainted(true);
		btnAccept.setOpaque(true);
		btnAccept
		.setIcon(new ImageIcon(
				AbstractFileFolderPanel.class
				.getResource("/com/poly/nlp/filekommander/views/icon/accept.png")));
		commandPanel.add(btnAccept);

		btnReject = new JButton("");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPanel();
			}
		});
		btnReject.setFocusPainted(false);
		btnReject.setMargin(new Insets(0, 0, 0, 0));
		btnReject.setContentAreaFilled(false);
		btnReject.setBorderPainted(true);
		btnReject.setOpaque(true);
		btnReject
		.setIcon(new ImageIcon(
				AbstractFileFolderPanel.class
				.getResource("/com/poly/nlp/filekommander/views/icon/reject.png")));
		commandPanel.add(btnReject);
	}

	public abstract void acceptButtonClickAction(ActionEvent e);
	
	public abstract void updatePanelData(GenericActionModel createModel) ;

	public abstract void updatePanelData();
	
	/**
	 * updates the create panel after the action is run
	 */
	public abstract void updatePanelDataAfterAction();

	protected JLabel createObjectLabelAfterAction(String labelText, int type,String error) {
		JLabel lblNewLabel = new JLabel(labelText);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		if(error.equals("") || error == null){
			lblNewLabel.setForeground(Color.GREEN);
			lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,1));
		}else{
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.RED,1));
			lblNewLabel.setToolTipText(error);
		}
		
		if (type == FileKommander.FILE) {
			lblNewLabel
			.setIcon(new ImageIcon(
					AbstractFileFolderPanel.class
					.getResource("/com/poly/nlp/filekommander/views/icon/file.png")));
		} else if (type == FileKommander.DIRECTORY) {
			lblNewLabel
			.setIcon(new ImageIcon(
					AbstractFileFolderPanel.class
					.getResource("/com/poly/nlp/filekommander/views/icon/folder.png")));
		}
		return lblNewLabel;
	}
	
	protected JLabel createDefaultObjectLabel(String labelText, int type) {
		JLabel lblNewLabel = new JLabel(labelText);
		lblNewLabel.setForeground(new Color(0, 0, 0));

		if (type == FileKommander.FILE) {
			lblNewLabel
			.setIcon(new ImageIcon(
					AbstractFileFolderPanel.class
					.getResource("/com/poly/nlp/filekommander/views/icon/file.png")));
		} else if (type == FileKommander.DIRECTORY) {
			lblNewLabel
			.setIcon(new ImageIcon(
					AbstractFileFolderPanel.class
					.getResource("/com/poly/nlp/filekommander/views/icon/folder.png")));
		}
		return lblNewLabel;
	}

	public void resetPanel(){		
		this.updatePanelData();
		FileKommanderRun.getGuiv2().reset();
	}
	/**
	 * @return
	 */
	public JLabel getActionLabelFile() {
		return actionLabelFile;
	}

	/**
	 * @return
	 */
	public JPanel getFilepanel() {
		return filepanel;
	}

	/**
	 * @return
	 */
	public JPanel getFileListPanel() {
		return fileListPanel;
	}

	/**
	 * @return
	 */
	public JButton getBtnAccept() {
		return btnAccept;
	}

	/**
	 * @return
	 */
	public JButton getBtnReject() {
		return btnReject;
	}

	/**
	 * @return  the actionString
	 */
	public String getActionString() {
		return actionString;
	}

	/**
	 * @param actionString  the actionString to set
	 */
	public void setActionString(String actionString) {
		this.actionString = actionString;
	}
}
