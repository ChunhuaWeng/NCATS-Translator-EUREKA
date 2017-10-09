package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.List;

public class Template {
	
	public String template;
	public int[] majorConceptIdx; 
	public Integer timeValIdx=null;
	public Integer timeUnitIdx=null;
	public Integer timeRelIdx=null;
	public Integer priorEvtIdx=null;
	public Integer postEvtIdx=null;
	public String timeRelKeyword;
	public String priorEvt;
	public String postEvt;
	public AnalysisMethod method = new AnalysisMethod();
	
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
