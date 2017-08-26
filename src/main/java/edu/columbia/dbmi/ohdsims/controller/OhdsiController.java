package edu.columbia.dbmi.ohdsims.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.columbia.dbmi.ohdsims.util.WebUtil;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

@Controller
@RequestMapping("/ohdsi")
public class OhdsiController {
	@Autowired
	private Properties applicationProps;
	
	

	public Properties getApplicationProps() {
		return applicationProps;
	}

	public void setApplicationProps(Properties applicationProps) {
		this.applicationProps = applicationProps;
	}

	final static String WORD2VEC = "/Users/yuanchi/Downloads/GoogleNews-vectors-negative300.bin";

	@RequestMapping("/main")
	public String showmain() {
		//
		return "main";
	}


	@RequestMapping("/ft2query")
	public String showft2query() {
		String jumpUrl = applicationProps.getProperty("nermodel");
		String metalite = applicationProps.getProperty("metalite");
		System.out.println("metalite="+metalite);
		return "freetext2sql";
	}

	@RequestMapping("/addtable")
	public String showaddtable() {
		//
		return "addtable";
	}

	@RequestMapping("/conceptset")
	public String showConceptSet() {
		//
		return "conceptSetPage";
	}

	@RequestMapping("/word2vecbib")
	public String showword2vecbib() {
		//
		return "word2vecbib";
	}

	@RequestMapping("/eliresult")
	public String showeliresult() {
		//
		return "eliieresult";
	}

	// @RequestMapping("/getct")
	// @ResponseBody
	// public String payOrder(HttpSession httpSession, HttpServletRequest
	// request, String nctid) throws Exception {
	// // String resultString = null;
	// String url = "https://clinicaltrials.gov/show/" + nctid +
	// "?displayxml=true";
	// String response = WebUtil.getCTByNctid(nctid);
	// String criteria = WebUtil.parse(response);
	// // System.out.println(criteria);
	// //Using stanfordNLP to do sentence segment
	// Properties properties = new Properties();
	// properties.setProperty("annotators", "tokenize, ssplit, parse");
	// StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
	// List<CoreMap> sentences =
	// pipeline.process(criteria).get(CoreAnnotations.SentencesAnnotation.class);
	// StringBuffer sb = new StringBuffer();
	// for (CoreMap sentence : sentences) {
	//
	// if(sentence.toString().toLowerCase().contains("inclusion criteria:")){
	// sb.append("inclusion criteria:");
	// sb.append("\n");
	// sb.append(sentence.toString().substring("inclusion criteria:".length()));
	// sb.append("\n");
	// }
	// else if(sentence.toString().toLowerCase().contains("exclusion
	// criteria:")){
	// sb.append("exclusion criteria:");
	// sb.append("\n");
	// sb.append(sentence.toString().substring("exclusion criteria:".length()));
	// sb.append("\n");
	//
	// }
	// else{
	// sb.append(sentence.toString());
	// sb.append("\n");
	// }
	//
	// // System.out.println(sentence.toString());
	// }
	//
	// return sb.toString();
	// }

	@RequestMapping("/format")
	@ResponseBody
	public String formatFreetext(HttpSession httpSession, HttpServletRequest request, String freetextinput)
			throws Exception {
		// String resultString = null;

		String criteria = freetextinput;
		criteria = criteria.replaceAll("\\s+", " ");
		// System.out.println(criteria);
		// Using stanfordNLP to do sentence segment
		// Properties properties = new Properties();
		// properties.setProperty("annotators", "tokenize, ssplit, parse");
		// StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
		// List<CoreMap> sentences =
		// pipeline.process(criteria).get(CoreAnnotations.SentencesAnnotation.class);
		// StringBuffer sb = new StringBuffer();
		// for (CoreMap sentence : sentences) {
		// sb.append(sentence.toString());
		// sb.append("\n");
		// // System.out.println(sentence.toString());
		// }
		// return sb.toString();
		return "";
	}

	@RequestMapping("/execeli")
	@ResponseBody
	public String executeEliIE(HttpSession httpSession, HttpServletRequest request, String freetextinput)
			throws Exception {

		// WebUtil.Write2File("/Users/yuanchi/Documents/git/EliIE/Tempfile/TempFile.txt",
		// freetextinput);
		// Properties props = new Properties();
		// props.put("python.home","path to the Lib folder");
		// props.put("python.console.encoding", "UTF-8"); // Used to prevent:
		// console: Failed to install '':
		// java.nio.charset.UnsupportedCharsetException: cp0.
		// props.put("python.security.respectJavaAccessibility", "false");
		// //don't respect java accessibility, so that we can access protected
		// members on subclasses
		// props.put("python.import.site","false");
		//
		// Properties preprops = System.getProperties();
		// PythonInterpreter interpreter = new PythonInterpreter();
		// interpreter.execfile("/Users/yuanchi/Documents/git/EliIE/my_utils.py");
		return "1";
	}

	@RequestMapping("/getjson")
	public String getJSON(HttpSession httpSession, HttpServletRequest request, String freetextinput) throws Exception {
		String[] args = new String[] { "-props", "NER.prop", "-train", "" };
		// CRFClassifier.main(args);
		return "getjson";
	}


}
