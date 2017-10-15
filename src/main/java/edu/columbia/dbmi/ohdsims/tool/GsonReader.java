package edu.columbia.dbmi.ohdsims.tool;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class GsonReader {
    public List<String> rec = new ArrayList<String>();
    public String sep = System.getProperty( "line.separator" );
	private BufferedReader br;

    public void read(String FileName) throws IOException {
        String line;
        br = new BufferedReader(new FileReader( FileName ));
        while ((line=br.readLine())!=null){
            this.rec.add( line );
        }
    }
    
    public static void main(String[] args) throws IOException, ParseException {
    		GsonReader jr = new GsonReader();
    		jr.readJson("/Users/wei/Dropbox/Research/biotranslator_hackathon/raw_templates.json");
    }

    public List<Template> readJson(String FileName) throws IOException, ParseException {
		List<Template> templateObjList = new ArrayList<>(); // Class Template is for all question templates

		Gson gson = new Gson();
		String rawCont = gson.toJson(new FileReader(FileName));
		System.out.println(rawCont);
		
//		
//    		JSONParser parser = new JSONParser();    		
//		JSONArray templateList = (JSONArray) parser.parse(new FileReader(FileName));
//    		
//		for (Object rec:templateList) {
//			Template tplt = new Template();
//			
//			JSONObject root = (JSONObject) rec;
//			String template = (String) root.get("template");
////			System.out.println("template: "+template);
//			tplt.setTemplate(template);
//
//			List<Long> rawMajorConceptIndex = (List<Long>) root.get("majorConceptIndex");
//			List<Integer> majorConceptIndex = new ArrayList<Integer>();
//			
//			for (int i=0; i<rawMajorConceptIndex.size();i++) {
////				System.out.println("current index of conceptIndex: "+i);
//				System.out.println("ck0: "+rawMajorConceptIndex.get(i));
//				System.out.println("ck1 "+Integer.getInteger(rawMajorConceptIndex.get(i).toString()));
//				majorConceptIndex.add(Integer.getInteger(rawMajorConceptIndex.get(i).toString()));
//			}
//			for (Integer x:majorConceptIndex) {
//				System.out.println(x);
//			}
//			tplt.setMajorConceptIdx(majorConceptIndex);
//						
//			//timeRelationIndex
//			JSONObject rawTime = (JSONObject) root.get("timeIndex");
//			JSONArray rawTimeValIdx = (JSONArray) rawTime.get("timeValueIndex");
//			if (rawTimeValIdx!=null) {
//				long timeValIdx = (long) rawTimeValIdx.get(0);
//				tplt.setTimeValIdx((int) timeValIdx);
////				System.out.println(tplt.getTimeValIdx());				
//			} 
//			JSONArray rawTimeUnitIdx = (JSONArray) rawTime.get("timeUnitIndex");
//			if (rawTimeUnitIdx!=null) {
//				long timeUnitIdx = (long) rawTimeUnitIdx.get(0);
//				tplt.setTimeUnitIdx((int) timeUnitIdx);
////				System.out.println(tplt.getTimeUnitIdx());
//			}
//			JSONArray rawTimeRelIdx = (JSONArray) rawTime.get("timeRelIndex");
//			if (rawTimeRelIdx!=null) {
//				long timeRelIdx = (long) rawTimeRelIdx.get(0);
//				tplt.setTimeRelIdx((int) timeRelIdx); 
////				System.out.println(tplt.getTimeRelIdx());
//			}
//			JSONArray rawPriorIdx = (JSONArray) rawTime.get("priorEventIndex");
//			if (rawPriorIdx!=null) {
//				long priorEvtIdx = (long) rawPriorIdx.get(0);
//				tplt.setPriorEvtIdx((int) priorEvtIdx);
//			}
//			JSONArray rawPostIdx = (JSONArray) rawTime.get("postEventIndex");
//			if (rawPostIdx!=null) {
//				long postEvtIdx = (long) rawPostIdx.get(0);
//				tplt.setPostEvtIdx((int) postEvtIdx);
//			}			
//			
//	        // iterating address Map
////	        Iterator<Map.Entry> itr1 = rawTime.entrySet().iterator();
////	        while (itr1.hasNext()) {
////	            Map.Entry pair = itr1.next();
////	            System.out.println(pair.getKey() + " : " + pair.getValue());
////	        }	
//			
//			// method
//			AnalysisMethod alsMtd = new AnalysisMethod();
//			JSONObject rawMethod = (JSONObject) root.get("method");
//			String methodType = (String) rawMethod.get("methodType");
////			System.out.println(methodType);
//			alsMtd.setMethodName(methodType);
//			if (rawMethod.containsKey("numeratorIndex")) {
//				List<Integer> rawNumeratorIndex = (List<Integer>) rawMethod.get("numeratorIndex");
//				alsMtd.setNumeratorIdx(rawNumeratorIndex);
//			}
////			System.out.println(alsMtd.numeratorIdx);		
//			if (rawMethod.containsKey("denominatorIndex")) {
//				List<Integer> rawDenominatorIdx = (List<Integer>) rawMethod.get("denominatorIndex");
//				alsMtd.setDenominatorIdx(rawDenominatorIdx);
//			}
////			System.out.println(alsMtd.denominatorIdx);
//			if (rawMethod.containsKey("rankCutoffIndex")) {
//				long rankCutoffIdx = (long) rawMethod.get("rankCutoffIndex");
//				alsMtd.setRankCutoffIdx((int) rankCutoffIdx);
//			}
////			System.out.println(alsMtd.getRankCutoffIdx());
//			tplt.setAnalysisMethod(alsMtd);
////			for (String entry:rawMethod.keySet()) {
////				System.out.println(entry);
////				System.out.println(rawMethod.get(entry));
////			}
//			
//			// domain
////			JSONObject rawDomain = (JSONObject) root.get("domain");
//			HashMap<String, List<Integer>> rawDomain = (HashMap) root.get("domain");
//			for (String domain:rawDomain.keySet()) {
//				JSONArray conceptIdx = (JSONArray) rawDomain.get(domain);
//				for (int i=0; i<conceptIdx.size();i++) {
//					long idx=(long) conceptIdx.get(i);
//					int idxInt = (int) idx;
//					String idxStr = Integer.toString(idxInt);
//					tplt.setDomain(idxStr, domain);
//				}
//			}
//			templateObjList.add(tplt);
//		}
		return templateObjList;        
    }
}
