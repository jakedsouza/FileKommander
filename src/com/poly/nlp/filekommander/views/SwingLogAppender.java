//package com.poly.nlp.filekommander.views;
//
//import javax.swing.JTextArea;
//
//import org.apache.log4j.AppenderSkeleton;
//import org.apache.log4j.PatternLayout;
//import org.apache.log4j.spi.LoggingEvent;
//
//import com.poly.nlp.filekommander.FileKommanderRun;
//
//public class SwingLogAppender extends AppenderSkeleton  {
//
//	PatternLayout patternLayout ;
//	
//	public SwingLogAppender(PatternLayout patternLayout) {
//		this.patternLayout = patternLayout ;
//	}
//
//	
//	@Override
//	public void close() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean requiresLayout() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	protected void append(LoggingEvent arg0) {
//		JTextArea jTextArea = FileKommanderRun.getGuiv2().getLogTextArea() ;
//		String text = patternLayout.format(arg0); 
//		jTextArea.append(patternLayout.format(arg0));
//		int x;
//		jTextArea.selectAll();
//		x = jTextArea.getSelectionEnd();
//		jTextArea.select(x,x);
//	//	jTextArea.setCaretPosition(jTextArea.getCaretPosition()+text.length());
//		FileKommanderRun.getGuiv2().getFrmFileKommander().paintAll(FileKommanderRun.getGuiv2().getFrmFileKommander().getGraphics());
//		//myTextArea.append(myPatternLayout.format(loggingEvent));		
//	}
//	
//}
