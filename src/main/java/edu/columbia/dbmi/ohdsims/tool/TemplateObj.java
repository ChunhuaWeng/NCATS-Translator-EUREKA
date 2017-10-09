package edu.columbia.dbmi.ohdsims.tool;

import java.util.ArrayList;
import java.util.List;

//what percent of:what percent of (.*) patients (have|use|take) (.*) after (.*) (year|years) of diagnosis\?
// ##[1,3]##[4,5]##[1,3]##[ratio,3,1]
public class TemplateObj {
    String rawTemp;
    String[] rawTempList;

    String rawMainConceptIdx;
    String rawTimeConceptIdx;
    String rawTimeRels;
    String rawMethod;

    String template;
    String[] mainConceptIdx;
    String[] timeConceptIdx;

    List<String> timeRels = new ArrayList<>();
    String method;
    String[] analysisTgt; // analysis targets

    // get a raw template
    public void getRawTemp(String rawTemp) {
        this.rawTemp = rawTemp;
        this.rawTempList = this.rawTemp.split("##");
    }

    // analyze the current template
    public void analyzeTemp() {
        this.rawTemp = this.rawTempList[0];
        this.rawMainConceptIdx = this.rawTempList[1];
        this.rawTimeConceptIdx = this.rawTempList[2];
        this.rawTimeRels = this.rawTempList[3];
        this.rawMethod = this.rawTempList[4];
    }

    // set the template
    public void setTemplate() {
        this.template = this.rawTemp.split(":")[1];
    }

    // set main concepts
    public void setMainConcepts() {
        this.mainConceptIdx = this.rawMainConceptIdx.split(",");
        for (String idx: this.mainConceptIdx) {

        }

    }
}
