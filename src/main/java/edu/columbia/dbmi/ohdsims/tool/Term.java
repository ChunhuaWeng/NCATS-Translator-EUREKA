package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Term {
	ArrayList<String> termID;
	String term;
	ArrayList<String> domain;
	boolean iniEvt;
	boolean negation=false;
	String timeVal;
	String timeUnit;
	String timeRel=null; //before or after
	
	
	public void setTimeRel(String timeRel) {
		this.timeRel = timeRel;
	}
	
	public String getTimeRel() {
		return this.timeRel;
	}
	
    public void setTermID(String name, HashMap<String, ArrayList<String>> termConceptIDDict) {
    		this.termID = termConceptIDDict.get(name);
    }	
    
    public void setTermDomain(String name, HashMap<String, ArrayList<String>> termDomainDict) {
		this.domain = termDomainDict.get(name);
}	    
	
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	public String getTimeUnit() {
		return this.timeUnit;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getTerm() {
		return this.term;
	}
	
	public void setTimeVal(String timeVal) {
		this.timeVal = timeVal;
	}
	
	public String getTimeVal() {
		return this.timeVal;
	}
}
