package com.poly.nlp.filekommander.views;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;

public  class FileKommanderGUIV2 {

	private JFrame frmFileKommander;
	private JTextField inputTextFld;
	private JLabel outputLbl;
	private JProgressBar progressBar;
	private JButton clearBtn;
	private JButton runBtn;
	private JLabel errorLbl;
	private JPanel outputPanel;
	private JLabel acceptActionBtn;
	private JLabel rejectActionBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileKommanderGUIV2 window = new FileKommanderGUIV2();
					window.frmFileKommander.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileKommanderGUIV2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

    	frmFileKommander = new JFrame();
    	
		frmFileKommander.setTitle("File Kommander");
		frmFileKommander.setResizable(false);
		frmFileKommander.setBounds(100, 100, 485, 320);
		frmFileKommander.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFileKommander.getContentPane().setLayout(null);
		
		inputTextFld = new JTextField();
		inputTextFld.setBounds(6, 12, 302, 26);
		frmFileKommander.getContentPane().add(inputTextFld);
		inputTextFld.setColumns(10);
		
		clearBtn = new JButton("Clear");
		clearBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetLayout();
			}
		});
		clearBtn.setBounds(385, 12, 84, 26);
		frmFileKommander.getContentPane().add(clearBtn);
		
		runBtn = new JButton("Run");
	
		runBtn.setBounds(318, 12, 63, 26);
		frmFileKommander.getContentPane().add(runBtn);
		
		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setValue(0);
		progressBar.setBounds(6, 49, 463, 10);
		frmFileKommander.getContentPane().add(progressBar);
		
		outputPanel = new JPanel();
		outputPanel.setBounds(6, 83, 467, 198);
		frmFileKommander.getContentPane().add(outputPanel);
				outputPanel.setLayout(null);
		
				outputLbl = new JLabel("asdfasdfasdfasdf");
				outputLbl.setForeground(new Color(30, 144, 255));
				outputLbl.setVerticalAlignment(SwingConstants.TOP);
				outputLbl.setBounds(0, 25, 467, 125);
				outputPanel.add(outputLbl);
				
				errorLbl = new JLabel("asdfasdfadsf");
				errorLbl.setVerticalTextPosition(SwingConstants.TOP);
				errorLbl.setHorizontalAlignment(SwingConstants.LEFT);
				errorLbl.setForeground(new Color(255, 69, 0));
				errorLbl.setBounds(0, 151, 467, 61);
				outputPanel.add(errorLbl);
				BorderFactory.createLineBorder(Color.BLUE);
				acceptActionBtn = new JLabel("");
				acceptActionBtn.setVisible(false);
				acceptActionBtn.setIcon(new ImageIcon(FileKommanderGUIV2.class.getResource("/com/poly/nlp/filekommander/views/icon/accept.png")));
				acceptActionBtn.setBounds(6, 60, 18, 16);
				frmFileKommander.getContentPane().add(acceptActionBtn);
				
				rejectActionBtn = new JLabel("");
				rejectActionBtn.setVisible(false);
				
				rejectActionBtn.setIcon(new ImageIcon(FileKommanderGUIV2.class.getResource("/com/poly/nlp/filekommander/views/icon/reject.png")));
				rejectActionBtn.setBounds(34, 60, 16, 16);
				frmFileKommander.getContentPane().add(rejectActionBtn);
		
	}
	
	public void resetLayout(){
		inputTextFld.setText("");
		outputLbl.setText("");
		errorLbl.setText("");
		progressBar.setValue(0);
		progressBar.setVisible(false);
		rejectActionBtn.setVisible(false);
		acceptActionBtn.setVisible(false);
		
	}

	public void displayErrorMessage(String message){
		this.errorLbl.setText(message);
		System.out.println(message);
	}
	
	public void clearErrorMessage(){
		this.errorLbl.setText("");
	}
	
	public JLabel getOutputLbl() {
		return outputLbl;
	}
	public synchronized JProgressBar getProgressBar() {
		return progressBar;
	}
	public JTextField getInputTextFld() {
		return inputTextFld;
	}
	public JButton getMicBtn() {
		return clearBtn;
	}
	
	public JButton getRunBtn() {
		return runBtn;
	}
	public JFrame getFrmFileKommander() {
		return frmFileKommander;
	}
	public JLabel getErrorLbl() {
		return errorLbl;
	}
	public JPanel getOutputPanel() {
		return outputPanel;
	}
	
	public JLabel getAcceptActionBtn() {
		return acceptActionBtn;
	}
	public JLabel getRejectActionBtn() {
		return rejectActionBtn;
	}
}
