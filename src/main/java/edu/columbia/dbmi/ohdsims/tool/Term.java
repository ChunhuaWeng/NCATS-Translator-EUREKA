package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Term {
	String term=null;
	ArrayList<String> termID=new ArrayList<>();
	ArrayList<String> domain=new ArrayList<>();
	boolean iniEvt=false; // default initial event status false
	boolean negation=false; // default negation status false
	String timeVal="0"; // default time 0
	String timeUnit="day"; //default unit day
	String timeRel=null; //before or after
	
	public void resetIniEvt() {
		this.iniEvt=false;
	}
	
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
