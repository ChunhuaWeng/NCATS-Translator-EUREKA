package edu.columbia.dbmi.ohdsims.tool;

import java.util.List;

public class AnalysisMethod {
    String methodName;
    String numerator;
    String denominator;
    List<String> methodList;

    public void getData(List<String> methodList){
        this.methodList = methodList;
    }

    public void setMethodName() {
        this.methodName = this.methodList.get(0);
    }

    public void setNumerator() {
        this.numerator = this.methodList.get(1);
    }

    public void setDenominator() {
        this.denominator = this.methodList.get(2);
    }
}


