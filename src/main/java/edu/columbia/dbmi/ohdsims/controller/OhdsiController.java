package edu.columbia.dbmi.ohdsims.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.columbia.dbmi.ohdsims.util.APIUtil;
import edu.columbia.dbmi.ohdsims.util.WebUtil;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

	@RequestMapping("/main")
	public String showmain() {
		//
		
		return "main";
	}
	
	@RequestMapping("/cohort")
	public String showcohort() {
		//
		return "cohort";
	}
	
	@RequestMapping("/parse")
	@ResponseBody
	public String parseSentence(HttpSession httpSession, HttpServletRequest request, String sentence)
			throws Exception {
		System.out.println(sentence);	
		return null;
	}
	
	@RequestMapping("/sites")
	public String showsites() {
		//
		return "sites";
	}
	@RequestMapping("/results")
	public String showresults() {
		//
		return "results";
	}


	@RequestMapping("/ft2query")
	public String showft2query() {
		String jumpUrl = applicationProps.getProperty("nermodel");
		String metalite = applicationProps.getProperty("metalite");
		System.out.println("metalite="+metalite);
		return "freetext2sql";
	}

	
	@RequestMapping("/reformulate")
	public String reformulate() {
		System.out.println("~~!!@#!@#~~");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}

	@RequestMapping("/conceptset")
	public String showConceptSet() {
		//
		return "conceptSetPage";
	}


	@RequestMapping("/welcome")
	public String showwelcome() {
		//
		return "welcome";
	}

	
	@RequestMapping("/generateSQL")
	@ResponseBody
	public String getallConceptSets(HttpSession httpSession) throws Exception {//Map<String, Object>
		System.out.println("conceptsets=" + 123);
		
		Map<String, Object> map = new HashMap<String, Object>();
		int inlength = 0;
		JSONObject jcs = new JSONObject();
		JSONArray totalconceptsetarr=new JSONArray();
		totalconceptsetarr.add(APIUtil.querybyconceptcetid(1232133));
		JSONArray jaa = totalconceptsetarr;
		JSONArray janull = new JSONArray();
		JSONObject jonull = new JSONObject();
		JSONObject initialevent = APIUtil.anyVisitforInitialEvent();
		JSONObject jofirst = new JSONObject();
		jofirst.accumulate("Type", "First");
		jcs.accumulate("ConceptSets", jaa);
		jcs.accumulate("PrimaryCriteria", initialevent);//any visit
		JSONObject joaddc = APIUtil.setAdditionalCriteria();//Set additional criteria
		jcs.accumulate("AdditionalCriteria", joaddc);
		jcs.accumulate("QualifiedLimit", jofirst);
		jcs.accumulate("ExpressionLimit", jofirst);
		jcs.accumulate("InclusionRules", janull);
		jcs.accumulate("CensoringCriteria", janull);
		System.out.println(jcs);
		httpSession.setAttribute("jsonresult", jcs.toString());
		return jcs.toString();
	}


}
