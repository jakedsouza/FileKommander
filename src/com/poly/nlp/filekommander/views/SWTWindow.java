package com.poly.nlp.filekommander.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.poly.nlp.filekommander.FileKommanderRun;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;

public class SWTWindow {
	private DataBindingContext m_bindingContext;
	private Text text;
	private String textInput; 
	private String textOutput ; 
	private int progressValue ;
	private CLabel outputLbl;
	private ProgressBar progressBar;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					SWTWindow window = new SWTWindow();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		
		Shell shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(498, 197);
		shell.setText("File Kommander");
		
		text = new Text(shell, SWT.BORDER | SWT.SEARCH);
		text.setToolTipText("What do you want to do ?");
		text.setBounds(10, 12, 271, 23);
		
		Button micBtn = new Button(shell, SWT.NONE);
		micBtn.setImage(SWTResourceManager.getImage("C:\\Users\\jake\\Documents\\GitHub\\FileKommander\\src\\mic_w.png"));
		micBtn.setBounds(287, 10, 38, 25);
		
		Button btnRunCommand = new Button(shell, SWT.FLAT);
		btnRunCommand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				textInput = text.getText();
				FileKommanderRun.getKommander().setUserInputText(textInput);
				FileKommanderRun.getKommander().run();
			}
		});
		btnRunCommand.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		btnRunCommand.setBounds(331, 10, 93, 25);
		btnRunCommand.setText("Run Command");
		
		progressBar = new ProgressBar(shell, SWT.SMOOTH);
		progressBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		progressBar.setBounds(10, 41, 290, 7);
		
		outputLbl = new CLabel(shell, SWT.NONE);
		outputLbl.setBounds(10, 55, 271, 94);
		//outputLabel.setText("");
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @return the textInput
	 */
	public String getTextInput() {
		return textInput;
	}

	/**
	 * @param textInput the textInput to set
	 */
	public void setTextInput(String textInput) {
		this.textInput = textInput;
	}
	
	public int getProgressBarSelection() {
		return progressBar.getSelection();
	}
	public void setProgressBarSelection(int selection) {
		progressBar.setSelection(selection);
	}
	public String getOutputLblText() {
		return outputLbl.getText();
	}
	public void setOutputLblText(String text_1) {
		outputLbl.setText(text_1);
	}
}
