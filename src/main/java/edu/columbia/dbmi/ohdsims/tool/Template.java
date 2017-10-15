package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class Template {
	
	public String template;
//	public List<Integer> majorConceptIdx; 
	public int[] majorConceptIdx; 	
	public List<Integer> timeConceptIdx;
	public Integer timeValIdx=null;
	public Integer timeUnitIdx=null;
	public Integer timeRelIdx=null;
	public Integer priorEvtIdx=null;
	public Integer postEvtIdx=null;
	public String timeRelKeyword;
	public String priorEvt;
	public String postEvt;
	public AnalysisMethod method = new AnalysisMethod();
	public LinkedHashMap<String, String> idxDomainDict = new LinkedHashMap<>(); 
	
	public void setDomain(String idx, String domain) {
		this.idxDomainDict.put(idx,  domain);
	}
	
	public void getDomain() {
		for (Entry<String, String> entry : this.idxDomainDict.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    System.out.println(key+": "+value);
		}		
	}
	
	public void setAnalysisMethod(AnalysisMethod method) {
		this.method = method;
	}
	
	public void setTimeRelIdx(int timeRelIdx) {
		this.timeRelIdx = timeRelIdx;
	}
	
	public int getTimeRelIdx() {
		return this.timeRelIdx;
	}
	
	public void setTimeValIdx(int timeValIdx) {
		this.timeValIdx = timeValIdx;
	}
	
	public int getTimeValIdx() {
		return this.timeValIdx;
	}
	
	public void setTimeUnitIdx(int timeUnitIdx) {
		this.timeUnitIdx = timeUnitIdx;
	}
	
	public int getTimeUnitIdx() {
		return this.timeUnitIdx;
	}
	
	public void setPriorEvtIdx(int priorEvtIdx) {
		this.priorEvtIdx = priorEvtIdx;
	}
	
	public void setPostEvtIdx(int postEvtIdx) {
		this.postEvtIdx = postEvtIdx;
 	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String getTemplate() {
		return this.template;
	}
	
	public void setMajorConceptIdx(int[] majorConceptIdx) {
		this.majorConceptIdx = majorConceptIdx;
	}
	
	public int[] getMajorConceptIdx() {
		return this.majorConceptIdx;
	}
}
