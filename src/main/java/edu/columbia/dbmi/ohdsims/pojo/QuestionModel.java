package edu.columbia.dbmi.ohdsims.pojo;

public class QuestionModel {
	
	private String content;
	private String questionid;
	private String cohortlist;
	private String testtype;
	private String username;
	private String orgnizer;
	private String sitename;
	private Integer requestid;
	
	
	
	
	
	public Integer getRequestid() {
		return requestid;
	}
	public void setRequestid(Integer requestid) {
		this.requestid = requestid;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	public String getOrgnizer() {
		return orgnizer;
	}
	public void setOrgnizer(String orgnizer) {
		this.orgnizer = orgnizer;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCohortlist() {
		return cohortlist;
	}
	public void setCohortlist(String cohortlist) {
		this.cohortlist = cohortlist;
	}
	public String getTesttype() {
		return testtype;
	}
	public void setTesttype(String testtype) {
		this.testtype = testtype;
	}
	
	

}
