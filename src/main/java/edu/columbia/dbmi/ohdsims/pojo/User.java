package edu.columbia.dbmi.ohdsims.pojo;

public class User {
private String username;
private String pwd;
private Integer researcher;
private Integer dataowner;
private Integer question;
private Integer project;
private String realname;
private String institute;
private String contact;


public Integer getQuestion() {
	return question;
}
public void setQuestion(Integer question) {
	this.question = question;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public Integer getResearcher() {
	return researcher;
}
public void setResearcher(Integer researcher) {
	this.researcher = researcher;
}
public Integer getDataowner() {
	return dataowner;
}
public void setDataowner(Integer dataowner) {
	this.dataowner = dataowner;
}
public Integer getProject() {
	return project;
}
public void setProject(Integer project) {
	this.project = project;
}
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
public String getInstitute() {
	return institute;
}
public void setInstitute(String institute) {
	this.institute = institute;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}

}
