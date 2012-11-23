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

public class SWTWindow {
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SWTWindow window = new SWTWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		
		Shell shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(461, 107);
		shell.setText("File Kommander");
		
		text = new Text(shell, SWT.BORDER | SWT.SEARCH);
		text.setToolTipText("What do you want to do ?");
		text.setBounds(10, 12, 271, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.setImage(SWTResourceManager.getImage("C:\\Users\\jake\\Documents\\GitHub\\FileKommander\\src\\mic_w.png"));
		button.setBounds(287, 10, 28, 25);
		
		Button btnRunCommand = new Button(shell, SWT.FLAT);
		btnRunCommand.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		btnRunCommand.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRunCommand.setBounds(331, 10, 93, 25);
		btnRunCommand.setText("Run Command");
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.SMOOTH);
		progressBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		progressBar.setSelection(50);
		progressBar.setBounds(10, 41, 290, 7);
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
