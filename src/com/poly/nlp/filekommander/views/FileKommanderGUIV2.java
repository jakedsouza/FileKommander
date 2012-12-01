package com.poly.nlp.filekommander.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class FileKommanderGUIV2 {

	private JFrame frmFileKommander;
	private JTextField inputTextFld;
	private JLabel outputLbl;
	private JProgressBar progressBar;
	private JButton micBtn;
	private JButton runBtn;

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
		inputTextFld.setBounds(6, 12, 324, 26);
		frmFileKommander.getContentPane().add(inputTextFld);
		inputTextFld.setColumns(10);
		
		micBtn = new JButton("Mic");
		micBtn.setBounds(342, 12, 56, 26);
		frmFileKommander.getContentPane().add(micBtn);
		
		runBtn = new JButton("Run");
	
		runBtn.setBounds(410, 12, 63, 26);
		frmFileKommander.getContentPane().add(runBtn);
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBounds(6, 49, 463, 9);
		frmFileKommander.getContentPane().add(progressBar);

		outputLbl = new JLabel("");
		outputLbl.setBounds(6, 82, 467, 171);
		frmFileKommander.getContentPane().add(outputLbl);
		
	}

	public void displayErrorMessage(String message){
		// TODO 
	}
	
	public void clearErrorMessage(){
		// TODO 
	}
	
	public JLabel getOutputLbl() {
		return outputLbl;
	}
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public JTextField getInputTextFld() {
		return inputTextFld;
	}
	public JButton getMicBtn() {
		return micBtn;
	}
	
	public JButton getRunBtn() {
		return runBtn;
	}
	public JFrame getFrmFileKommander() {
		return frmFileKommander;
	}
}
