package edu.columbia.dbmi.ohdsims.tool;
import java.io.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

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
    		List<Template> templateObjList = new ArrayList<>();
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
            if (StringUtils.isEmpty(rawTempIdx)==false){
                String[] tempRel = rawTempRel.split(",");
                int priorEvtIdx = Integer.parseInt(tempRel[0]);
                tplt.setPriorEvtIdx(priorEvtIdx);
                int postEvtIdx = Integer.parseInt(tempRel[1]);
                tplt.setPostEvtIdx(postEvtIdx);
            } 

            //add analysis method            
            String rawAlsMtd = val[4].substring(1, val[4].length() - 1);
            String[] alsMtd = rawAlsMtd.split(",");
            String mtdType = alsMtd[0];
            String[] rawNumeratorIdx = alsMtd[1].substring(1, alsMtd[1].length()-1).split(";");        
//            for (int i=0;i<rawNumeratorIdx.length;i++) {
//            		System.out.println(rawNumeratorIdx[i]);
//            }
            
            int[] numeratorIdx = new int[rawNumeratorIdx.length];

            for (int i=0;i<rawNumeratorIdx.length; i++) {
            		try {
            			numeratorIdx[i] = Integer.parseInt(rawNumeratorIdx[i]);
            		} catch (NumberFormatException nfe) {
//            			System.out.println(nfe);
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
            if (mtdType.equals("ratio")) { // use the ratio method
            		method.setMethodName(mtdType);
            		method.setNumeratorIdx(numeratorIdx);
            		method.setDenominatorIdx(denominatorIdx);
            }
            tplt.setAnalysisMethod(method);
            templateObjList.add(tplt);
        }
        return templateObjList;
    }
}
