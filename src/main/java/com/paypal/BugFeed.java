package com.paypal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BugFeed {
	
	@JsonProperty("project_name")
	private String projectName;
	
	@JsonProperty("developeremail")
	private String developerEmail;
	
	@JsonProperty("pkey")
	private String bugId;
	
	@JsonProperty("verifieremail")
	private String verifierEmail;
	
	@JsonProperty("issue_summary")
	private String issueDesc;
	
	@JsonProperty("issue_priority")
	private String issuePriority;
	
	@JsonProperty("issue_status")
	private String issueStatus;
	

	public String getDeveloperemail() {
		return developerEmail;
	}

	public void setDeveloperemail(String developeremail) {
		this.developerEmail = developeremail;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getVerifierEmail() {
		return verifierEmail;
	}

	public void setVerifierEmail(String verifierEmail) {
		this.verifierEmail = verifierEmail;
	}

	public String getIssueDesc() {
		return issueDesc;
	}

	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}

	@Override
	public String toString() {
		return "BugFeed [projectName=" + projectName + ", developerEmail="
				+ developerEmail + ", bugId=" + bugId + ", verifierEmail="
				+ verifierEmail + ", issueDesc=" + issueDesc
				+ ", issuePriority=" + issuePriority + ", issueStatus="
				+ issueStatus + "]\n";
	}

	public String getIssuePriority() {
		return issuePriority;
	}

	public void setIssuePriority(String issuePriority) {
		this.issuePriority = issuePriority;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	
	
	
}
