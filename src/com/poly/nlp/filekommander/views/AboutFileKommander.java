package com.poly.nlp.filekommander.views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.log4j.Logger;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutFileKommander extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AboutFileKommander.class);

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutFileKommander() {
		log.info("AboutFileKommander constructor") ;
		setTitle("About FileKommander");
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("FileKommander 0.1");
			lblNewLabel.setBounds(171, 11, 91, 14);
			contentPanel.add(lblNewLabel);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 34, 434, 2);
		contentPanel.add(separator);
		
		JLabel lblSourceCode = new JLabel("Source Code");
		lblSourceCode.setBounds(10, 47, 80, 14);
		contentPanel.add(lblSourceCode);
		
		JLabel lblNewLabel_1 = new JLabel("http://goo.gl/1KDHI");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://goo.gl/1KDHI")) ;
				} catch (IOException e1) {
					log.error(e1);
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					log.error(e1);
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(100, 47, 162, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblDevelopers = new JLabel("Developers");
		lblDevelopers.setBounds(10, 189, 67, 14);
		contentPanel.add(lblDevelopers);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 173, 434, 2);
		contentPanel.add(separator_1);
		
		JLabel lblNehaMoharir = new JLabel("Neha Moharir");
		lblNehaMoharir.setBounds(84, 189, 89, 14);
		contentPanel.add(lblNehaMoharir);
		
		JLabel lblNewLabel_2 = new JLabel("nehadm[at]gmail[dot]com");
		lblNewLabel_2.setBounds(183, 189, 150, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblJakeDsouza = new JLabel("Jake Dsouza");
		lblJakeDsouza.setBounds(84, 214, 89, 14);
		contentPanel.add(lblJakeDsouza);
		
		JLabel lblJakedsouzaatgmaildotcom = new JLabel("jakedsouza88[at]gmail[dot]com");
		lblJakedsouzaatgmaildotcom.setBounds(183, 214, 237, 14);
		contentPanel.add(lblJakedsouzaatgmaildotcom);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 72, 434, 2);
		contentPanel.add(separator_2);
		
		JLabel lblNewLabel_3 = new JLabel("Libraries used");
		lblNewLabel_3.setBounds(10, 83, 146, 14);
		contentPanel.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			    // add(button);
			    // pack();
			    // setVisible(true);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
