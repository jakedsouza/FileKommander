package com.poly.nlp.filekommander.views;

import java.awt.EventQueue;

import javax.sound.sampled.AudioFileFormat;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.ImageIcon;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import com.poly.nlp.filekommander.FileKommanderRun;
import com.poly.nlp.filekommander.views.models.CreateModel;
import com.poly.nlp.filekommander.views.models.DeleteModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JToggleButton;

import sun.util.logging.resources.logging;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class FileKommanderGUIV2 {

	private JFrame frmFileKommander;
	private JTextField inputTextFld;
	private JProgressBar progressBar;
	private JButton runBtn;
	private JPanel informatinDisplayPanel;

	private String CREATEPANEL = "CREATEPANEL";
	private String DELETEPANEL = "DELETEPANEL";
	private String EMPTYPANEL = "EMPTYPANEL";
	private CreateActionPanel createActionPanel;
	private DeleteActionPanel deleteActionPanel;
	private EmptyActionPanel emptyActionPanel;
	private JToggleButton micBtn;

	protected Microphone microphone = new Microphone(AudioFileFormat.Type.WAVE);
	protected String file = "tmp";
	private FileKommanderMenu menuBar;

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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		frmFileKommander = new JFrame();
		frmFileKommander.setTitle("File Kommander");
		frmFileKommander.setResizable(false);
		frmFileKommander.setBounds(100, 100, 644, 407);
		frmFileKommander.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFileKommander.getContentPane().setLayout(null);

		inputTextFld = new JTextField();
		inputTextFld.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputTextFld.setBounds(6, 34, 500, 44);
		inputTextFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == java.awt.event.KeyEvent.VK_ENTER) {
					FileKommanderRun.analyseTextInput();
				}

			}
		});
		inputTextFld.setColumns(10);
		frmFileKommander.getContentPane().add(inputTextFld);

		runBtn = new JButton("");
		runBtn.setIcon(new ImageIcon(FileKommanderGUIV2.class
				.getResource("/com/poly/nlp/filekommander/views/icon/run.png")));
		runBtn.setBounds(516, 34, 51, 44);
		runBtn.setFocusPainted(false);
		runBtn.setMargin(new Insets(0, 0, 0, 0));
		runBtn.setContentAreaFilled(false);
		runBtn.setBorderPainted(true);
		runBtn.setOpaque(true);
		runBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileKommanderRun.analyseTextInput();
			}
		});
		frmFileKommander.getContentPane().add(runBtn);

		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setValue(0);
		progressBar.setBounds(6, 105, 622, 10);
		frmFileKommander.getContentPane().add(progressBar);
		BorderFactory.createLineBorder(Color.BLUE);

		informatinDisplayPanel = new JPanel();
		informatinDisplayPanel.setBounds(10, 126, 618, 242);
		frmFileKommander.getContentPane().add(informatinDisplayPanel);
		informatinDisplayPanel.setLayout(new CardLayout(0, 0));

		emptyActionPanel = new EmptyActionPanel();
		informatinDisplayPanel.add(emptyActionPanel, EMPTYPANEL);

		createActionPanel = new CreateActionPanel();
		informatinDisplayPanel.add(createActionPanel, CREATEPANEL);

		deleteActionPanel = new DeleteActionPanel();
		informatinDisplayPanel.add(deleteActionPanel, DELETEPANEL);

		CardLayout layout = (CardLayout) informatinDisplayPanel.getLayout();
		layout.show(informatinDisplayPanel, EMPTYPANEL);

		micBtn = new JToggleButton("");
		micBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				micAction(e);
			}
		});
		micBtn.setIcon(new ImageIcon(FileKommanderGUIV2.class
				.getResource("/com/poly/nlp/filekommander/views/icon/mic.png")));
		// micBtn.setBounds(516, 11, 51, 44);
		micBtn.setFocusPainted(false);
		micBtn.setMargin(new Insets(0, 0, 0, 0));
		micBtn.setContentAreaFilled(false);
		micBtn.setBorderPainted(true);
		micBtn.setOpaque(true);
		micBtn.setBounds(577, 34, 51, 44);
		frmFileKommander.getContentPane().add(micBtn);

		menuBar = new FileKommanderMenu();
		frmFileKommander.setJMenuBar(menuBar);

	}

	public void micAction(ActionEvent e) {
		if (micBtn.isSelected()) {
			recordSound();
		} else {
			recognizeSound();
		}
	}

	private void recordSound() {
		try {
			microphone.captureAudioToFile(file);
			micBtn.setIcon(new ImageIcon(
					FileKommanderGUIV2.class
					.getResource("/com/poly/nlp/filekommander/views/icon/mic_recording.png")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void recognizeSound() {
		microphone.close();
		micBtn.setIcon(new ImageIcon(
				FileKommanderGUIV2.class
				.getResource("/com/poly/nlp/filekommander/views/icon/mic_waiting.png")));
		new Thread(new RecognizeThread()).start();
	}

	public void updateInformation(GenericActionModel actionModel) {
		CardLayout layout = (CardLayout) informatinDisplayPanel.getLayout();
		if (actionModel instanceof CreateModel) {
			createActionPanel.updatePanelData((CreateModel) actionModel);
			layout.show(informatinDisplayPanel, CREATEPANEL);

		} else if (actionModel instanceof DeleteModel) {
			deleteActionPanel.updatePanelData((DeleteModel) actionModel);
			layout.show(informatinDisplayPanel, CREATEPANEL);
		}
		// informatinDisplayPanel.repaint();
	}

	public void displayErrorMessage(String message) {
		System.out.println(message);
	}

	public void clearErrorMessage() {
	}

	public synchronized JProgressBar getProgressBar() {
		return progressBar;
	}

	public JTextField getInputTextFld() {
		return inputTextFld;
	}

	public JButton getRunBtn() {
		return runBtn;
	}

	public JFrame getFrmFileKommander() {
		return frmFileKommander;
	}

	public JPanel getInformatinDisplayPanel() {
		return informatinDisplayPanel;
	}

	public CreateActionPanel getCreateActionPanel() {
		return createActionPanel;
	}

	public DeleteActionPanel getDeleteActionPanel() {
		return deleteActionPanel;
	}

	public JToggleButton getMicBtn() {
		return micBtn;
	}

	protected class RecognizeThread implements Runnable {

		@Override
		public void run() {
			Recognizer recognizer = new Recognizer();
			try {
				GoogleResponse googleResponse = recognizer
						.getRecognizedDataForWave(file);
				inputTextFld.setText(googleResponse.getResponse());
				System.out.println("Confidence :"
						+ googleResponse.getConfidence());
				micBtn.setIcon(new ImageIcon(
						FileKommanderGUIV2.class
						.getResource("/com/poly/nlp/filekommander/views/icon/mic.png")));
				// confidence.setText(googleResponse.getConfidence());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
