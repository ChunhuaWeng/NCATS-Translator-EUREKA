package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Term_bk1022 {
	public String term=null;
	public ArrayList<String> termID=new ArrayList<>();
	public List<String> domain;
	public boolean iniEvt=false; // default initial event status false
	public boolean negation=false; // default negation status false
	public String timeVal="0"; // default time 0
	public String timeUnit="day"; //default unit day
	public String timeRel=null; //before or after
	
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
    
    public void setTermDomain(List<String> domain) {
		this.domain = domain;
    }
    
    public void getTermDomain() {
    		for(String x:this.domain) {
    			System.out.println(x);
    		}
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
