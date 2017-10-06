package edu.columbia.dbmi.ohdsims.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class APIUtil {
	public static JSONObject querybyconceptcetid(int conceptsetid){
		JSONObject jot=new JSONObject();
    	String re2=HttpUtil.doGet("http://api.ohdsi.org/WebAPI/conceptset/"+conceptsetid);
    	JSONObject jore2=JSONObject.fromObject(re2);
    	jot.accumulateAll(jore2);
    	String re3=HttpUtil.doGet("http://api.ohdsi.org/WebAPI/conceptset/"+conceptsetid+"/expression");
    	JSONObject expression=JSONObject.fromObject(re3);
    	jot.accumulate("expression",expression);
    	return jot;
	}
	//----
	public static JSONObject anyVisitforInitialEvent(){
		JSONObject anycondition=new JSONObject();
		JSONArray criteriaList=new JSONArray();
		JSONObject conditionOccurrence=new JSONObject();
		JSONObject jnull=new JSONObject();
		conditionOccurrence.accumulate("VisitOccurrence", jnull);
		criteriaList.add(conditionOccurrence);
		anycondition.accumulate("CriteriaList", criteriaList);
		JSONObject observationWindow=new JSONObject();
		observationWindow.accumulate("PriorDays", 0);
		observationWindow.accumulate("PostDays", 0);
		anycondition.accumulate("ObservationWindow", observationWindow);
		JSONObject primaryCriteriaLimit=new JSONObject();
		primaryCriteriaLimit.accumulate("Type", "First");
		anycondition.accumulate("PrimaryCriteriaLimit", primaryCriteriaLimit);
		return anycondition;
	}
	//----
	public static JSONObject setAdditionalCriteria(){	
		JSONObject additionalcriteria=new JSONObject();
		JSONArray jarr=new JSONArray();
	    //123123 conceptset id
		jarr.add(setCriteriaUnit(0,false,true,"Condition",1));
		//jarr.add(setCriteriaUnit(conceptsetindex,))
		
		additionalcriteria.accumulate("Type", "ALL");
		additionalcriteria.accumulate("CriteriaList", jarr);
		JSONArray janull=new JSONArray();
		additionalcriteria.accumulate("DemographicCriteriaList", janull);
		additionalcriteria.accumulate("Groups", janull);
		return additionalcriteria;
	}
	
	//----
	public static JSONObject setCriteriaUnit(int index,boolean neg,boolean flag,String type,int temporal){
		JSONObject criteriaunit = new JSONObject();
		JSONObject occurrence = new JSONObject();
		JSONObject startwindow = new JSONObject();
		JSONObject criteria = new JSONObject();
		criteria = setCriteria(type, index);
		if (neg^flag == true) {
			occurrence = setOccurrence(2, 1);	
		}else {
			occurrence = setOccurrence(0, 0);
		}
		startwindow = setTemporalWindow(temporal);
		criteriaunit.accumulate("Criteria", criteria);
		criteriaunit.accumulate("StartWindow", startwindow);
		criteriaunit.accumulate("Occurrence", occurrence);
		return criteriaunit;
	}
	
	public static JSONObject setCriteria(String type, int conceptsetid) {
		JSONObject criteriatype = new JSONObject();
		JSONObject codesetId = new JSONObject();
		codesetId.accumulate("CodesetId", conceptsetid);
		if (type.equals("Condition")) {
			criteriatype.accumulate("ConditionOccurrence", codesetId);
		} else if (type.equals("Drug")) {
			criteriatype.accumulate("DrugExposure", codesetId);
		} else if (type.equals("Observation")) {
			criteriatype.accumulate("Observation", codesetId);
		} else if (type.equals("Procedure_Device")) {
			criteriatype.accumulate("DeviceExposure", codesetId);
		}
		return criteriatype;
	}
	
	public static JSONObject setOccurrence(int type, int count) {
		JSONObject jsonob = new JSONObject();
		jsonob.accumulate("Type", type);
		jsonob.accumulate("Count", count);
		return jsonob;
	}

	/**
	 * index 1: has history of 
	 * index 2: has 
	 * */
	public static JSONObject setTemporalWindow(int index) {
		JSONObject window = new JSONObject();
		JSONObject start = new JSONObject();
		JSONObject end = new JSONObject();
		if(index ==1){
			start.accumulate("Coeff", -1);
			// start.accumulate("Count", 0);
			end.accumulate("Coeff", "1");
			// end.accumulate("Count", 0);
			//end.accumulate("Days", "0");	
		}
		window.accumulate("Start", start);
		window.accumulate("End", end);
		return window;
	}
	
	

}
