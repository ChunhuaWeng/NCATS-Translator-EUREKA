package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AnalysisMethod {
    String methodName;
    int[] numeratorIdx;
    int[] denominatorIdx;
    List<String> numerator = new ArrayList<>();
    List<String> denominator = new ArrayList<>();
//    String denominator;
    List<String> methodList;

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


