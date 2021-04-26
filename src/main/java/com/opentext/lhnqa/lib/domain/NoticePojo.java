package com.opentext.lhnqa.lib.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticePojo {

	String subject;
	String body;
	Integer notice_frequency_id;
	String include_active_holds_in_release_notice;
	
	public NoticePojo() {
		this.subject = " Dear Custodian , you are under notice";
		this.body= "<p>Mr.Custodian, We are kind to inform you that your system is under survillenace </p>";
	}

	public NoticePojo(String subject,String body) {
		this.subject = subject;
		this.body = body;
	}
	
	public NoticePojo(String subject,String body, int notice_frequency_id) {
		this(subject,body);
		this.notice_frequency_id=notice_frequency_id;
	}
	
	public NoticePojo(int notice_frequency_id) {
		this();
		this.notice_frequency_id=notice_frequency_id;
	}
	
	public NoticePojo(String include_active_holds_in_release_notice) {
		this();
		this.include_active_holds_in_release_notice = include_active_holds_in_release_notice;
	}
	
	public NoticePojo(String subject,String body, String include_active_holds_in_release_notice) {
		this(subject,body);
		this.include_active_holds_in_release_notice = include_active_holds_in_release_notice;
	}

	public NoticePojo clearAllNoticeData() {
		subject=null;
		body=null;
		notice_frequency_id=null;
		include_active_holds_in_release_notice=null;
		return this;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getNotice_frequency_id() {
		return notice_frequency_id;
	}

	public void setNotice_frequency_id(Integer notice_frequency_id) {
		this.notice_frequency_id = notice_frequency_id;
	}

	public String getInclude_active_holds_in_release_notice() {
		return include_active_holds_in_release_notice;
	}

	public void setInclude_active_holds_in_release_notice(String include_active_holds_in_release_notice) {
		this.include_active_holds_in_release_notice = include_active_holds_in_release_notice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((include_active_holds_in_release_notice == null) ? 0
				: include_active_holds_in_release_notice.hashCode());
		result = prime * result + ((notice_frequency_id == null) ? 0 : notice_frequency_id.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoticePojo other = (NoticePojo) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (include_active_holds_in_release_notice == null) {
			if (other.include_active_holds_in_release_notice != null)
				return false;
		} else if (!include_active_holds_in_release_notice.equals(other.include_active_holds_in_release_notice))
			return false;
		if (notice_frequency_id == null) {
			if (other.notice_frequency_id != null)
				return false;
		} else if (!notice_frequency_id.equals(other.notice_frequency_id))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	
}
