package edu.columbia.dbmi.ohdsims.tool;
import java.io.*;
import java.util.*;
//import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
//import org.json.simple.JSONObject;
//import org.json.JSONException;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;

public class Reader {
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

    public List<Template> parseTemplate() {
    		List<Template> templateObjList = new ArrayList<>(); // Class Template is for all question templates
        for (String sent:this.rec) {    		
        		Template tplt = new Template();
            String[] val = sent.split("##");
            
            // get the question template
            String pat = val[0];
            tplt.setTemplate(pat);
//            System.out.println("question template: "+tplt.getTemplate());
            
            // get the indices of major concepts
            String rawIdx = val[1].substring(1, val[1].length() - 1);
            int[] majorConceptIdx = Arrays.stream(rawIdx.split(",")).mapToInt(Integer::parseInt).toArray();
            tplt.setMajorConceptIdx(majorConceptIdx);
                        
            // add time info
            String rawTempIdx = val[2].substring(1, val[2].length() - 1); 
            if (StringUtils.isEmpty(rawTempIdx)==false){
                String[] tempIdx = rawTempIdx.split(",");
                int timeRelIdx = Integer.parseInt(tempIdx[0]); //e.g., before, after
                tplt.setTimeRelIdx(timeRelIdx);
                int timeValIdx = Integer.parseInt(tempIdx[1]);
                tplt.setTimeValIdx(timeValIdx);
                int timeUnitIdx = Integer.parseInt(tempIdx[2]);
                tplt.setTimeUnitIdx(timeUnitIdx);
            } 

            //add time relation
            String rawTempRel = val[3].substring(1, val[3].length() - 1);
            if (StringUtils.isEmpty(rawTempRel)==false){
                String[] tempRel = rawTempRel.split(",");
                HashMap<String, String> evtIdxTimePt = new HashMap<>();
                for (String pair:tempRel) {
                		String[]splitPair = pair.substring(1, pair.length()-1).split(";");
                		evtIdxTimePt.put(splitPair[0], splitPair[1]);
                }
                for (String x:evtIdxTimePt.keySet()) {
                		if ("0".equals(evtIdxTimePt.get(x))) {
                			int iniEvtIdx = Integer.parseInt(x);
//                			System.out.println("iniEvtIdx: "+iniEvtIdx);
                			tplt.setIniEvtIdx(iniEvtIdx);
                		} else if ("1".equals(evtIdxTimePt.get(x))) {
                			int postEvtIdx = Integer.parseInt(x);
//                			System.out.println("postEvtIdx: "+postEvtIdx);
                			tplt.setPostEvtIdx(postEvtIdx);
                		} else if ("-1".equals(evtIdxTimePt.get(x))) {
                			int priorEvtIdx = Integer.parseInt(x);
                			tplt.setPriorEvtIdx(priorEvtIdx);
//                			System.out.println("priorEvtIdx: "+priorEvtIdx);
                		} else {
                			
                		}
                }
//                int priorEvtIdx = Integer.parseInt(tempRel[0]);
//                tplt.setPriorEvtIdx(priorEvtIdx);
//                int postEvtIdx = Integer.parseInt(tempRel[1]);
//                tplt.setPostEvtIdx(postEvtIdx);
//                System.out.println("init evt: "+tplt.iniEvtIdx);
//                System.out.println("prior evt: "+tplt.priorEvtIdx);
//                System.out.println("post evt: "+tplt.postEvtIdx);                
            } 

            
            // add domain info
            String rawDomain = val[5].substring(1, val[5].length()-1);
//            System.out.println(rawDomain);
            String[] rawDomainList = rawDomain.split(",");
            for (String rawPair:rawDomainList) {
	        		String[] conceptDomainPair = rawPair.substring(1, rawPair.length()-1).split(";");
	        		String majorIdx = conceptDomainPair[0];
	        		String majorDomain = conceptDomainPair[1];
	        		tplt.setDomain(majorIdx, majorDomain);
            }
            
            //add analysis method
            String rawAlsMtd = val[4].substring(1, val[4].length() - 1);
            String[] alsMtd = rawAlsMtd.split(",");
            String mtdType = alsMtd[0];
            
            // if this is a ratio question
            if (mtdType.equals("ratio")) {
                // the numerator and denominator is only for the ratio method
                String[] rawNumeratorIdx = alsMtd[1].substring(1, alsMtd[1].length()-1).split(";");        
                int[] numeratorIdx = new int[rawNumeratorIdx.length];

                for (int i=0;i<rawNumeratorIdx.length; i++) {
                		try {
                			numeratorIdx[i] = Integer.parseInt(rawNumeratorIdx[i]);
                		} catch (NumberFormatException nfe) {
                			System.out.println(nfe);
                		}
                }
                String[] rawDenominatorIdx = alsMtd[2].substring(1, alsMtd[2].length() - 1).split(";");
                int[] denominatorIdx = new int[rawDenominatorIdx.length];
                for (int i=0;i<rawDenominatorIdx.length; i++) {
    	        		try {
    	        			denominatorIdx[i] = Integer.parseInt(rawDenominatorIdx[i]);
    	        		} catch (NumberFormatException nfe) {
    	        		}
                }
                AnalysisMethod method = new AnalysisMethod();    
            		method.setMethodName(mtdType);
            		method.setNumeratorIdx(numeratorIdx);
            		method.setDenominatorIdx(denominatorIdx);
                tplt.setAnalysisMethod(method);
                templateObjList.add(tplt);                
            } else if (mtdType.equals("entity")) {
            		// need entity and an associated integer
            		String rankIdx = alsMtd[1];
//            		String entityIdx = alsMtd[2];
                AnalysisMethod method = new AnalysisMethod();    
            		method.setMethodName(mtdType);
            		method.setRankCutoffIdx(Integer.parseInt(rankIdx));
//            		method.setEntityIdx(Integer.parseInt(entityIdx));
                tplt.setAnalysisMethod(method);
                templateObjList.add(tplt);
            } else if (mtdType.equals("dist")) {
            		// need entity
//            		String entityIdx = alsMtd[1];
            		AnalysisMethod method = new AnalysisMethod();
            		method.setMethodName(mtdType);
//            		method.setEntityIdx(Integer.parseInt(entityIdx));
            		tplt.setAnalysisMethod(method);
            		templateObjList.add(tplt);
            } else if (mtdType.equals("gender_ratio")) {
//            		String entityIdx = alsMtd[1];
            		AnalysisMethod method = new AnalysisMethod();
            		method.setMethodName(mtdType);
//            		method.setEntityIdx(Integer.parseInt(entityIdx));
            		tplt.setAnalysisMethod(method);
            		templateObjList.add(tplt);            		
            } else if (mtdType.equals("count")){
            		AnalysisMethod method = new AnalysisMethod();
            		method.setMethodName(mtdType);
            		tplt.setAnalysisMethod(method);
            		templateObjList.add(tplt);                 		
            } else {
            	
            }
        }
        
        return templateObjList;
    }
}
