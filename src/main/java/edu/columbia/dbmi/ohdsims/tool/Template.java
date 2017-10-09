package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.List;

public class Template {
	
	String template;
	int[] majorConceptIdx; 
	Integer timeValIdx=null;
	Integer timeUnitIdx=null;
	Integer timeRelIdx=null;
	Integer priorEvtIdx=null;
	Integer postEvtIdx=null;
	String timeRelKeyword;
	String priorEvt;
	String postEvt;
	AnalysisMethod method = new AnalysisMethod();
	
	public void setAnalysisMethod(AnalysisMethod method) {
		this.method = method;
	}
	
	public void setTimeRelIdx(int timeRelIdx) {
		this.timeRelIdx = timeRelIdx;
	}
	
	public int getTimeRelIdx(int timeRelIdx) {
		return this.timeRelIdx;
	}
	
	public void setTimeValIdx(int timeValIdx) {
		this.timeValIdx = timeValIdx;
	}
	
	public void setTimeUnitIdx(int timeUnitIdx) {
		this.timeUnitIdx = timeUnitIdx;
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
