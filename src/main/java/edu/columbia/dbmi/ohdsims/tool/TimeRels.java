package edu.columbia.dbmi.ohdsims.tool;

import java.util.List;

public class TimeRels {

    String timeVal;
    String timeUnit;
    String priorEvent;
    String postEvent;
    List<String> tempList;

    public void getData(List<String> tempList) {
        this.tempList = tempList;
    }

    public void setTimeVal() {
        this.timeVal = this.tempList.get(0);
    }

    public void setTimeUnit() {
        this.timeUnit = this.tempList.get(1);
    }

    public void setPriorEvent() {
        this.priorEvent = this.tempList.get(2);
    }

    public void setPostEvent() {
        this.postEvent = this.tempList.get(3);
    }
}
