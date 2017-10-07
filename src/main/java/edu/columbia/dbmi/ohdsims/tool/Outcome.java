package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class Outcome {

    String template;
    LinkedHashMap<String, List<String>> majorConceptIDs = new LinkedHashMap<>();
    TimeRels timeRels = new TimeRels();
    AnalysisMethod method = new AnalysisMethod(); // analysis method class

//    // load concept-domain dict file
//    String csvFile = "/Users/weiwei/Dropbox/Research/biotranslator_hackathon/eureka_local/src/edu/columbia/dbmi/ohdsi/CONCEPT.csv";
//    CSVreader csvreader = new CSVreader();
//    HashMap<String, ArrayList<String>> termConceptIDDict = csvreader.csv2dict(csvFile);

    public void setMajorConceptIDs(List<String> entityList, HashMap<String, ArrayList<String>> termConceptIDDict) {
        for (String entity : entityList) {
            this.majorConceptIDs.put(entity, termConceptIDDict.get(entity));
//            System.out.println(entity);
//            System.out.println(termConceptIDDict.get(entity));
        }
    }

    public void setTimeRels(List<String> tempList){
        if (tempList.size()>0){
            this.timeRels.getData(tempList);
            this.timeRels.setTimeVal();
            this.timeRels.setTimeUnit();
            this.timeRels.setPriorEvent();
            this.timeRels.setPostEvent();
        }
    }

    public void setMethods(List<String> methodList) {
        if (methodList.size()>0) {}
        this.method.getData(methodList);
        this.method.setMethodName();
        this.method.setNumerator();
        this.method.setDenominator();

    }

}