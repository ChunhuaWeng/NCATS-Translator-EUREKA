package edu.columbia.dbmi.ohdsims.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.columbia.dbmi.ohdsims.tool.CohortCreation;

@Controller
@RequestMapping("/cohort")
public class CohortController {
	
	@RequestMapping("/createCohortByConceptID")
	@ResponseBody
	public String createCohortByOneConcept()
	{
		String conceptid1="";
		String conceptid2="";
		int relation=0;
		
		//CohortCreation.generateSQL(conceptid1, conceptid2, 1);
		
		return null;
	}
	
	
	
}
