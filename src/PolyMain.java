

import gate.Gate;
import gate.creole.SerialAnalyserController;
//import gate.AnnotationSet;
import gate.Corpus;
import gate.Factory;
import static gate.util.persistence.PersistenceManager.loadObjectFromFile;

import java.io.File;
//import java.net.URL;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.HashSet;
//import java.util.Iterator;

public class PolyMain {
	//private static Pipeline pipeline;
	@SuppressWarnings("unused")
	private static SerialAnalyserController pipelineController;
	public static void main(String[] args)throws Exception
	{// jake new comment for neha
		File gateHome = new File("C:/Users/MixPool/ws/GatePractice/");
		if (Gate.getGateHome() == null)
			Gate.setGateHome(gateHome);
       
		// jakes comments 
		// hahaha 
		Gate.init();
		Gate.getCreoleRegister().registerDirectories(
						new File("C:/Users/MixPool/ws/GatePractice/" + "plugins", "ANNIE")
								.toURI().toURL());
		
		//Only if you are using this plugin
		Gate.getCreoleRegister().registerDirectories(
				new File("C:/Users/MixPool/ws/GatePractice/"+"plugins", "Tools")
						.toURI().toURL()); 
		//Only if you are using this plugin.
		Gate.getCreoleRegister().registerDirectories(
				new File("C:/Users/MixPool/ws/GatePractice/"+"plugins", "Tagger_NP_Chunking")
						.toURI().toURL()); 
		
		
		gate.CorpusController c = ((gate.CorpusController)loadObjectFromFile(new java.io.File("C:/nlpCourseF11/gateExercises/PolyTest/poly.gapp")));
		Corpus corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
		/****
		URL dir = new File("C:/nlpCourseF11/gateExercises/PolyTest/docs").toURI().toURL();
		corpus.populate(dir, null, "UTF-8", false); //set the encoding to whatever is the encoding of your files
		//c.getCorpus().populate(dir, null, "UTF-8", false);
		*****/
		//in your case the input string will come through the user interface
		String docStr = "Dr. Phyllis Frankl of the computer science department of NYU-Poly in New York City has been named provost. "+
		                "She will be replacing Dr. Kurt Becker who will become the Vice-President for miracles.";
		gate.Document doc = gate.Factory.newDocument(docStr);
		
		corpus.add(doc);
		c.setCorpus(corpus);
		c.execute();
		
		corpus.clear();
		
	      ArrayList<gate.Annotation> annList = new ArrayList<gate.Annotation>();
	      //Add whatever annotations you are interested in processing further
	      //to annList--questions, requests, instructions, etc.
	      annList.addAll(doc.getAnnotations().get("Question"));
	     
	    
	     
//REALLY important to release doc and coprpus after processing each request, question, instruction, etc., or 
//your program will quickly run out of heap space.	     
		      Factory.deleteResource(doc);
		      Factory.deleteResource(corpus);
		      
		    //Do your further processing on the annotations in annList (in a different class file, please)   
	}

}