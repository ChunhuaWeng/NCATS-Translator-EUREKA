package edu.columbia.dbmi.ohdsims.tool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import edu.columbia.dbmi.ohdsims.pojo.Concept;
import edu.columbia.dbmi.ohdsims.pojo.Criterion;
import edu.columbia.dbmi.ohdsims.util.ATLASUtil;
import edu.columbia.dbmi.ohdsims.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;




public class CohortCreationbak {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		Set<Integer> conceptid1=new HashSet<Integer>();
//		Set<Integer> conceptid2=new HashSet<Integer>();
//		conceptid1.add(201826);
//		//conceptid1.add(316866);
//		
//		//conceptid2.add(111123);
//		//conceptid2.add(111346);
//		
//		
//		//---
//		.String[] test1=generateConceptSet(conceptid1,conceptid2,1);
		//String x=generateConceptSetByOneConcept(201826);
		//System.out.println("here="+x);
		String id=createConceptSetByOneConcpetId("type 2 diabetes",201826);
		System.out.println(id);
//		HttpUtil.doPut("http://api.ohdsi.org/WebAPI/conceptset/"+id+"/items",x);
		//String conceptSetId1="923390";
		
		//generateCohortSQL(null);
		//HttpUtil.doPut("http://api.ohdsi.org/WebAPI/conceptset/"+conceptSetId1+"/items",test1[0]);
		//String conceptSetId2="923390";
		//HttpUtil.doPut("http://api.ohdsi.org/WebAPI/conceptset/"+conceptSetId1+"/items",test1[0]);
		//----
		List<Criterion> criteria =new ArrayList<Criterion>();
		Criterion c=new Criterion();
		c.setConceptSetId(123123);
		c.setConceptSetName("1123");
		c.setInitialEvent(true);
		c.setDomain("Condition");
		criteria.add(c);
		//c.setNeg();
		generateCohortSQL(criteria);
		
	}
	
	public static String createConceptSetByOneConcpetId(String conceptSetname,Integer conceptId){
		String expression=generateConceptSetByOneConcept(conceptId);
		long t1=System.currentTimeMillis();  
		JSONObject jo=new JSONObject();
		jo.accumulate("name", "eureka"+" "+conceptSetname+t1);
		jo.accumulate("id", 23333);
		System.out.println("====>"+jo);
		String result=HttpUtil.doPost("http://api.ohdsi.org/WebAPI/conceptset/", jo.toString());
		JSONObject rejo=JSONObject.fromObject(result);
		System.out.println(result);
		HttpUtil.doPut("http://api.ohdsi.org/WebAPI/conceptset/"+rejo.getString("id")+"/items",expression);
	
		return rejo.getString("id");
	}
	
	public static String generateCohortSQL(List<Criterion> criteria) throws IOException{
		JSONArray conceptSetArr=new JSONArray();
		//JSONArray ja = JSONArray.fromObject(conceptJson);
		//System.out.println(ATLASUtil.querybyconceptSetid(923390));
		
		//Create conceptSets
		int initindex=-1;
		for(int i=0;i<criteria.size();i++){	
			conceptSetArr.add(criteria.get(i).getConceptSetId());	
			//criteria.get(i).setConceptSetId(conceptSetid);
			if(criteria.get(i).isInitialEvent()){
				initindex=i;
			}
		}
		
		//create cohort JSON
		
		JSONObject jcs = new JSONObject();
		JSONObject initialevent =new JSONObject();
		if(initindex==-1){
			initialevent = setAnyVisitforInitialEvent();//Set inital event
		}else{
			initialevent = setAnyVisitAsInitialEvent(criteria.get(initindex).getDomain(),criteria.get(initindex).getConceptSetId());//Set inital event
		}
		JSONObject joaddc = setAdditionalCriteria(criteria);//Set additional criteria
		JSONObject jofirst = new JSONObject();
		JSONArray janull = new JSONArray();
		
		jofirst.accumulate("Type", "First");
		jcs.accumulate("ConceptSets", conceptSetArr);
		jcs.accumulate("PrimaryCriteria", initialevent);
		jcs.accumulate("AdditionalCriteria", joaddc);
		jcs.accumulate("QualifiedLimit", jofirst);
		jcs.accumulate("ExpressionLimit", jofirst);
		jcs.accumulate("InclusionRules", janull);
		jcs.accumulate("CensoringCriteria", janull);
		System.out.println(jcs);
		
		String results;
		JSONObject resultjson;
		//JSON to SQL
		
//		//insert to ATLAS
		long t1=System.currentTimeMillis();  
		HashMap<String,String> map=new HashMap<String,String>();
		String cohortname="eureka"+t1;
		map.put("name", cohortname);
		map.put("expressionType", "SIMPLE_EXPRESSION");
		map.put("expression",jcs.toString());
		results=HttpUtil.doPost("http://api.ohdsi.org/WebAPI/cohortdefinition/", JSONObject.fromObject(map).toString());
		resultjson=JSONObject.fromObject(results);
		System.out.println("cohortId="+resultjson.get("id"));
		
		//http://api.ohdsi.org/WebAPI/cohortdefinition/sql
		
		//generate SQL template
		JSONObject jo=new JSONObject();
		jo.accumulate("expression", jcs.toString());
		//System.out.println("jo="+jo);
		results=HttpUtil.doPost("http://api.ohdsi.org/WebAPI/cohortdefinition/sql", jo.toString());
		//System.out.println("sql="+results);
		resultjson=JSONObject.fromObject(results);
		//System.out.println("SQL template="+resultjson.get("templateSql"));
		
		//SQL template -> different SQLs
		JSONObject sqljson=new JSONObject();
		sqljson.accumulate("SQL", resultjson.get("templateSql"));
		sqljson.accumulate("targetdialect", "postgresql");
		//System.out.println("sqljson="+sqljson.toString());
		results=HttpUtil.doPost("http://api.ohdsi.org/WebAPI/sqlrender/translate", sqljson.toString());
		System.out.println("postgresql sql="+results);
		resultjson=JSONObject.fromObject(results);
		System.out.println(resultjson.get("targetSQL"));
		//JSON 
		
		String sqlresult=(String) resultjson.get("targetSQL");
		return sqlresult;
	}
	
	public static String[] generateConceptSet(Set<Integer> conceptid1,Set<Integer> conceptid2,int type){
		//type 1 concept 1  VS. concept 1 and concept 2
		//[{"conceptId":201826,"isExcluded":0,"includeDescendants":1,"includeMapped":1},{"conceptId":316866,"isExcluded":0,"includeDescendants":1,"includeMapped":1}]
		JSONArray conceptSet1=new JSONArray();
		JSONArray conceptSet2=new JSONArray();
		for(Integer str:conceptid1){
			JSONObject jo=formatOneitem(str);
			conceptSet1.add(jo);
		}
		for(Integer str:conceptid2){
			JSONObject jo=formatOneitem(str);
			conceptSet2.add(jo);
		}
		System.out.println("conceptSet1="+conceptSet1.toString());
		System.out.println("conceptSet2="+conceptSet2.toString());
		String[] result=new String[2];
		result[0]=conceptSet1.toString();
		result[1]=conceptSet2.toString();
		return result;
	}
	
	public static String generateConceptSetByOneConcept(Integer conceptId){
		//type 1 concept 1  VS. concept 1 and concept 2
		//[{"conceptId":201826,"isExcluded":0,"includeDescendants":1,"includeMapped":1},{"conceptId":316866,"isExcluded":0,"includeDescendants":1,"includeMapped":1}]
		JSONArray conceptSet=new JSONArray();
		JSONObject jo=formatOneitem(conceptId);
		conceptSet.add(jo);		
		System.out.println("conceptSet="+conceptSet.toString());	
		return conceptSet.toString();
	}
	
	
	
	public static JSONObject formatOneitem(Integer conceptId){
		JSONObject jo=new JSONObject();
		try {
			jo.put("conceptId", conceptId);
			jo.put("isExcluded", 0);
			jo.put("includeDescendants", 1);//
			jo.put("includeMapped", 1);//
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;
	}
	
	public static JSONObject setAdditionalCriteria(List<Criterion> criteria){	
		int conceptsetindex=0;
		JSONObject additionalcriteria=new JSONObject();
		JSONArray jarr=new JSONArray();
		for (int x = 0; x < criteria.size(); x++) {
			if(criteria.get(x).isInitialEvent()==false){
				jarr.add(setCriteriaUnit(criteria.get(x).getConceptSetId(),criteria.get(x).isNeg(),criteria.get(x).isInclusionCriterion(),criteria.get(x).getDomain(),1));
				conceptsetindex++;
			}
		}
		additionalcriteria.accumulate("Type", "ALL");
		additionalcriteria.accumulate("CriteriaList", jarr);
		JSONArray janull=new JSONArray();
		additionalcriteria.accumulate("DemographicCriteriaList", janull);
		additionalcriteria.accumulate("Groups", janull);
		return additionalcriteria;
	}
	
	
	/**
	 * inclusion :flag = true ; exclusion : flag=false;
	 * neg
	 * temporal 1 has history of 
	 * 
	 * */
	
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
	public static JSONObject setAnyVisitforInitialEvent(){
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
	
	public static JSONObject setAnyVisitAsInitialEvent(String type,Integer conceptId){
		JSONObject anycondition=new JSONObject();
		JSONArray criteriaList=new JSONArray();
//		JSONObject conditionOccurrence=new JSONObject();
//		JSONObject jnull=new JSONObject();
//		conditionOccurrence.accumulate("ConditionOccurrence", jnull);
		JSONObject criteriatype = new JSONObject();
		JSONObject codesetId = new JSONObject();
		codesetId.accumulate("CodesetId", conceptId);
		if (type.equals("Condition")) {
			criteriatype.accumulate("VisitOccurrence", codesetId);
		} else if (type.equals("Drug")) {
			criteriatype.accumulate("DrugExposure", codesetId);
		} else if (type.equals("Observation")) {
			criteriatype.accumulate("Observation", codesetId);
		} else if (type.equals("Procedure_Device")) {
			criteriatype.accumulate("DeviceExposure", codesetId);
		}
		criteriaList.add(criteriatype);
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

	public static Integer createConceptByConceptName(String word,String domain) throws UnsupportedEncodingException, ClientProtocolException, IOException{
		Integer conceptId=0;
		//the most related one
		
		System.out.println("word=" + word);
		System.out.println("domain=" + domain);
		
		List<Concept> econceptlist = ATLASUtil.searchConceptByNameAndDomain(word, domain);
		
		
		String expression=generateConceptSetByConcepts(econceptlist);
		long t1=System.currentTimeMillis();  
		JSONObject jo=new JSONObject();
		jo.accumulate("name", word+"_auto_"+t1);
		jo.accumulate("id", 23333);
		System.out.println("====>"+jo);
		String result=HttpUtil.doPost("http://api.ohdsi.org/WebAPI/conceptset/", jo.toString());
		JSONObject rejo=JSONObject.fromObject(result);
		System.out.println(result);
		HttpUtil.doPut("http://api.ohdsi.org/WebAPI/conceptset/"+rejo.getString("id")+"/items",expression);		
		return Integer.valueOf(rejo.getString("id"));
	}
	
	public static String generateConceptSetByConcepts(List<Concept> concepts){
		JSONArray conceptSet=new JSONArray();
		for(Concept c:concepts){
			JSONObject jo=formatOneitem(c.getCONCEPT_ID());
			conceptSet.add(jo);		
		}
		return conceptSet.toString();
	}
	

}
