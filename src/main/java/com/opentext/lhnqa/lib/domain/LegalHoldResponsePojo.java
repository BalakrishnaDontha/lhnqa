package com.opentext.lhnqa.lib.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LegalHoldResponsePojo {

	long id;
	long matter_id;
	String name;
	String hold_description;
	boolean draft;
	String response_due_date;
	String created_at;
	String updated_at;
	String status;
	LHLinksPojo _links;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHold_description() {
		return hold_description;
	}

	public void setHold_description(String hold_description) {
		this.hold_description = hold_description;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public String getResponse_due_date() {
		return response_due_date;
	}

	public void setResponse_due_date(String response_due_date) {
		this.response_due_date = response_due_date;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public LHLinksPojo get_links() {
		return _links;
	}

	public void set_links(LHLinksPojo _links) {
		this._links = _links;
	}

	public long getMatter_id() {
		return matter_id;
	}

	public void setMatter_id(long matter_id) {
		this.matter_id = matter_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date convertDateFormat(String rawDate) throws ParseException {
		final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.parse(rawDate);
	}

	public Date getResponseDueDateDateFormat() throws ParseException {
		return convertDateFormat(response_due_date);
	}

	public Date getCreatedAtDateFormat() throws ParseException {
		return convertDateFormat(created_at);
	}

	public Date getUpdatedAtDateFormat() throws ParseException {
		return convertDateFormat(updated_at);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + (draft ? 1231 : 1237);
		result = prime * result + ((hold_description == null) ? 0 : hold_description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (matter_id ^ (matter_id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((response_due_date == null) ? 0 : response_due_date.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
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
		LegalHoldResponsePojo other = (LegalHoldResponsePojo) obj;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (draft != other.draft)
			return false;
		if (hold_description == null) {
			if (other.hold_description != null)
				return false;
		} else if (!hold_description.equals(other.hold_description))
			return false;
		if (id != other.id)
			return false;
		if (matter_id != other.matter_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (response_due_date == null) {
			if (other.response_due_date != null)
				return false;
		} else if (!response_due_date.equals(other.response_due_date))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		return true;
	}

	
}