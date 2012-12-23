package com.poly.nlp.filekommander.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

import java.awt.GridLayout;

public class OptionsFileKommander extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(OptionsFileKommander.class);

	/**
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 */
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public OptionsFileKommander() {
		log.info("AboutFileKommander constructor") ;
		setTitle("FileKommander Options");
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 238);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(10, 10, 0, 0));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 239, 444, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("Save");
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			    // add(button);
			    // pack();
			    // setVisible(true);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	/**
	 * @return
	 */
	public JPanel getContentPanel() {
		return contentPanel;
	}
	/**
	 * @return
	 */
	public JButton getOkButton() {
		return okButton;
	}
}
