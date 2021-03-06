package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AnalysisMethod {
    public String methodName;
//    public List<Integer> numeratorIdx;
//    public List<Integer> denominatorIdx;    
    public int[] numeratorIdx;
    public int[] denominatorIdx;
    public List<String> numerator = new ArrayList<>();
    public List<String> denominator = new ArrayList<>();
//    String denominator;
    public List<String> methodList;
    
    // for entity question
    public int rankCutoffIdx;
    
    public void setRankCutoffIdx(Integer topRankIdx) {
    		this.rankCutoffIdx = topRankIdx;
    }
    
    public Integer getRankCutoffIdx() {
    		return this.rankCutoffIdx;
    }

    public void getData(List<String> methodList){
        this.methodList = methodList;
    }

    public void setMethodName(String name) {
        this.methodName = name;
    }

    public void setNumeratorIdx(int[] numeratorIdx) {
    		this.numeratorIdx = numeratorIdx;
    }
    
    public void setDenominatorIdx(int[] denominatorIdx) {
    		this.denominatorIdx = denominatorIdx;
    }
    
    public void setNumerator(LinkedHashMap<String, List<String>> termConceptDict) {
//        this.numerator.add(this.methodList.get(1));
    		this.numerator.addAll(termConceptDict.get(this.methodList.get(1)));
    		this.denominator.addAll( termConceptDict.get(this.methodList.get(2)));
//		System.out.println(this.methodList.get(1));
//    		System.out.println(termConceptDict.get(this.methodList.get(1)));
    }

    public void setDenominator(LinkedHashMap<String, List<String>> termConceptDict) {
//        this.denominator.add( this.methodList.get(2));
//        this.denominator.add( this.methodList.get(1));
        this.denominator.addAll( termConceptDict.get(this.methodList.get(1)));        
//        System.out.println(this.methodList.get(2));
//        System.out.println(termConceptDict.get(this.methodList.get(2)));
//        System.out.println(this.methodList.get(1));
//		System.out.println(termConceptDict.get(this.methodList.get(1)));        
    }
}


