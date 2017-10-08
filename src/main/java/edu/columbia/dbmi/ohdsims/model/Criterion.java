package edu.columbia.dbmi.ohdsims.model;

public class Criterion {
	private Integer conceptSetId;
	private String conceptSetName;
	private String domain;
	private boolean neg;
	private Integer beforeDays;
	private Integer afterDays;
	private Integer measurement_type;
	private Double measurement_value;
	private boolean isInitialEvent;
	private boolean isInclusionCriterion;
	
	public boolean isInclusionCriterion() {
		return isInclusionCriterion;
	}
	public void setInclusionCriterion(boolean isInclusionCriterion) {
		this.isInclusionCriterion = isInclusionCriterion;
	}
	public Integer getConceptSetId() {
		return conceptSetId;
	}
	public void setConceptSetId(Integer conceptSetId) {
		this.conceptSetId = conceptSetId;
	}
	public String getConceptSetName() {
		return conceptSetName;
	}
	public void setConceptSetName(String conceptSetName) {
		this.conceptSetName = conceptSetName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public boolean isNeg() {
		return neg;
	}
	public void setNeg(boolean neg) {
		this.neg = neg;
	}
	public Integer getBeforeDays() {
		return beforeDays;
	}
	public void setBeforeDays(Integer beforeDays) {
		this.beforeDays = beforeDays;
	}
	public Integer getAfterDays() {
		return afterDays;
	}
	public void setAfterDays(Integer afterDays) {
		this.afterDays = afterDays;
	}
	public Integer getMeasurement_type() {
		return measurement_type;
	}
	public void setMeasurement_type(Integer measurement_type) {
		this.measurement_type = measurement_type;
	}
	public Double getMeasurement_value() {
		return measurement_value;
	}
	public void setMeasurement_value(Double measurement_value) {
		this.measurement_value = measurement_value;
	}
	public boolean isInitialEvent() {
		return isInitialEvent;
	}
	public void setInitialEvent(boolean isInitialEvent) {
		this.isInitialEvent = isInitialEvent;
	}
	
	
}
