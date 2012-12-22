package com.poly.nlp.filekommander.views;

import javax.sound.sampled.AudioFileFormat;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
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
import com.poly.nlp.filekommander.views.models.ExistsModel;
import com.poly.nlp.filekommander.views.models.GenericActionModel;
import com.poly.nlp.filekommander.views.models.OpenModel;
import com.poly.nlp.filekommander.views.models.RenameModel;
import com.poly.nlp.filekommander.views.models.StatsModel;
import com.poly.nlp.filekommander.views.panels.CreateActionPanel;
import com.poly.nlp.filekommander.views.panels.DeleteActionPanel;
import com.poly.nlp.filekommander.views.panels.EmptyActionPanel;
import com.poly.nlp.filekommander.views.panels.ExistActionPanel;
import com.poly.nlp.filekommander.views.panels.OpenActionPanel;
import com.poly.nlp.filekommander.views.panels.RenameActionPanel;
import com.poly.nlp.filekommander.views.panels.StatsActionPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class FileKommanderGUIV2 {

	private JFrame frmFileKommander;
	private JTextField inputTextFld;
	private JProgressBar progressBar;
	private JButton runBtn;
	private JPanel informatinDisplayPanel;

	private String CREATEPANEL = "CREATEPANEL";
	private String DELETEPANEL = "DELETEPANEL";
	private String RENAMEPANEL = "RENAMEPANEL";
	private String STATSPANEL = "STATSPANEL";
	private String EXISTSPANEL = "EXISTSPANEL";
	private String OPENPANEL = "OPENPANEL";

	private String EMPTYPANEL = "EMPTYPANEL";
	private CreateActionPanel createActionPanel;
	private DeleteActionPanel deleteActionPanel;
	private RenameActionPanel renameActionPanel;
	private StatsActionPanel statsActionPanel;
	private ExistActionPanel existActionPanel ;
	private OpenActionPanel openActionPanel ;

	private EmptyActionPanel emptyActionPanel;
	
	private JToggleButton micBtn;

	protected Microphone microphone = new Microphone(AudioFileFormat.Type.WAVE);
	protected String file = "tmp";
	private FileKommanderMenu menuBar;
	private JLabel errorMessageLabel;

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
		frmFileKommander.setBounds(100, 100, 644, 467);
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
		progressBar.setBounds(6, 6, 622, 10);
		frmFileKommander.getContentPane().add(progressBar);
		BorderFactory.createLineBorder(Color.BLUE);

		informatinDisplayPanel = new JPanel();
		informatinDisplayPanel.setBounds(6, 82, 618, 242);
		frmFileKommander.getContentPane().add(informatinDisplayPanel);
		informatinDisplayPanel.setLayout(new CardLayout(0, 0));

		emptyActionPanel = new EmptyActionPanel();
		informatinDisplayPanel.add(emptyActionPanel, EMPTYPANEL);

		createActionPanel = new CreateActionPanel();
		informatinDisplayPanel.add(createActionPanel, CREATEPANEL);

		deleteActionPanel = new DeleteActionPanel();
		informatinDisplayPanel.add(deleteActionPanel, DELETEPANEL);

		renameActionPanel = new RenameActionPanel();
		informatinDisplayPanel.add(renameActionPanel, RENAMEPANEL);

		statsActionPanel = new StatsActionPanel();
		informatinDisplayPanel.add(statsActionPanel, STATSPANEL);
		
		openActionPanel = new OpenActionPanel();
		informatinDisplayPanel.add(openActionPanel, OPENPANEL);
		
		existActionPanel = new ExistActionPanel();
		informatinDisplayPanel.add(existActionPanel, EXISTSPANEL);

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
		
		JPanel errorMessagePanel = new JPanel();
		errorMessagePanel.setBounds(6, 343, 622, 67);
		frmFileKommander.getContentPane().add(errorMessagePanel);
		errorMessagePanel.setLayout(null);
		
		errorMessageLabel = new JLabel("");
		errorMessageLabel.setForeground(Color.RED);
		errorMessageLabel.setBounds(6, 6, 610, 55);
		errorMessagePanel.add(errorMessageLabel);

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
			showLayout(CREATEPANEL);
		} else if (actionModel instanceof DeleteModel) {
			deleteActionPanel.updatePanelData((DeleteModel) actionModel);
			showLayout(DELETEPANEL);
		}else if (actionModel instanceof RenameModel) {
			renameActionPanel.updatePanelData((RenameModel) actionModel);
			showLayout(RENAMEPANEL);
		}else if (actionModel instanceof StatsModel) {
			statsActionPanel.updatePanelData((StatsModel) actionModel);
			layout.show(informatinDisplayPanel, STATSPANEL);
		}else if (actionModel instanceof ExistsModel) {
			existActionPanel.updatePanelData((ExistsModel) actionModel);
			layout.show(informatinDisplayPanel, EXISTSPANEL);
		}else if (actionModel instanceof OpenModel) {
			openActionPanel.updatePanelData((OpenModel) actionModel);
			layout.show(informatinDisplayPanel, OPENPANEL);
		}
		informatinDisplayPanel.repaint();
	}

	/**
	 * Displays a specific layout in the information panel
	 * 
	 * @param layoutName
	 */
	public void showLayout(String layoutName) {
		CardLayout layout = (CardLayout) informatinDisplayPanel.getLayout();
		layout.show(informatinDisplayPanel, layoutName);
	}

	public void displayErrorMessage(String message) {
		this.errorMessageLabel.setText(message);
		System.out.println(message);
	}

	public void clearErrorMessage() {
	}
	
	public void reset() {
		inputTextFld.setText("");
		errorMessageLabel.setText("");
		showLayout(EMPTYPANEL);
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
	
	public JLabel getErrorMessageLabel() {
		return errorMessageLabel;
	}
	public RenameActionPanel getRenameActionPanel() {
		return renameActionPanel;
	}
}
