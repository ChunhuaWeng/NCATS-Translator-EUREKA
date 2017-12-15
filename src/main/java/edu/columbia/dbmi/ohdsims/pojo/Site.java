package edu.columbia.dbmi.ohdsims.pojo;

public class Site {
	private Integer id;
	private String siteName;
	private Integer patientCount;
	private String desc;
	private Integer status;
	private String email;
	private String contact;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(Integer patientCount) {
		this.patientCount = patientCount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
