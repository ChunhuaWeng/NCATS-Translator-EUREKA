package edu.columbia.dbmi.ohdsims.tool;

import java.util.HashSet;
import java.util.Set;

import edu.columbia.dbmi.ohdsims.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;




public class CohortCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Integer> conceptid1=new HashSet<Integer>();
		Set<Integer> conceptid2=new HashSet<Integer>();
		conceptid1.add(201826);
		conceptid1.add(316866);
		
		conceptid2.add(111123);
		conceptid2.add(111346);
		String test1=generateSQL(conceptid1,conceptid2,1);
		System.out.println(test1);
		HttpUtil.doPut("http://api.ohdsi.org/WebAPI/conceptset/923390/items",test1);	
	}
	
	public static String generateSQL(Set<Integer> conceptid1,Set<Integer> conceptid2,int type){
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
		
		return conceptSet1.toString();
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

}
