package com.opentext.lhnqa.lib.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opentext.lhnqa.api.lib.testcases.ReferenceData;

public class MatterResponsePojo {

	long id;
	String name;
	String number;
	String notes;
	String caption;
	String po_number;
	String case_number;
	String business_unit;
	String region;
	List<MatterContactPojo> matter_contacts;
	String email_display_name;
	String email_reply_to;
	String email_notification_text;
	String created_at;
	String updated_at;
	MattersLinksPojo _links;


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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getPo_number() {
		return po_number;
	}
	public void setPo_number(String po_number) {
		this.po_number = po_number;
	}
	public String getCase_number() {
		return case_number;
	}
	public void setCase_number(String case_number) {
		this.case_number = case_number;
	}
	public String getBusiness_unit() {
		return business_unit;
	}
	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public List<MatterContactPojo> getMatter_contacts() {
		return matter_contacts;
	}
	public void setMatter_contacts(List<MatterContactPojo> matter_contacts) {
		this.matter_contacts = matter_contacts;
	}
	public String getEmail_display_name() {
		return email_display_name;
	}
	public void setEmail_display_name(String email_display_name) {
		this.email_display_name = email_display_name;
	}
	public String getEmail_reply_to() {
		return email_reply_to;
	}
	public void setEmail_reply_to(String email_reply_to) {
		this.email_reply_to = email_reply_to;
	}
	public String getEmail_notification_text() {
		return email_notification_text;
	}
	public void setEmail_notification_text(String email_notification_text) {
		this.email_notification_text = email_notification_text;
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
	public MattersLinksPojo get_links() {
		return _links;
	}
	public void set_links(MattersLinksPojo _links) {
		this._links = _links;
	}

	public Date convertDateFormat(String rawDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(ReferenceData.TIMESTAMP_DEFAULT_FORMAT);
		return formatter.parse(rawDate);
	}

	public Date fetchCreatedAtInDateFormat() throws ParseException {
		return convertDateFormat(created_at);
	}

	public Date fetchUpdatedAtInDateFormat() throws ParseException {
		return convertDateFormat(updated_at);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_links == null) ? 0 : _links.hashCode());
		result = prime * result + ((business_unit == null) ? 0 : business_unit.hashCode());
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((case_number == null) ? 0 : case_number.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((email_display_name == null) ? 0 : email_display_name.hashCode());
		result = prime * result + ((email_notification_text == null) ? 0 : email_notification_text.hashCode());
		result = prime * result + ((email_reply_to == null) ? 0 : email_reply_to.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((matter_contacts == null) ? 0 : matter_contacts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((po_number == null) ? 0 : po_number.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		MatterResponsePojo other = (MatterResponsePojo) obj;
		if (_links == null) {
			if (other._links != null)
				return false;
		} else if (!_links.equals(other._links))
			return false;
		if (business_unit == null) {
			if (other.business_unit != null)
				return false;
		} else if (!business_unit.equals(other.business_unit))
			return false;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (case_number == null) {
			if (other.case_number != null)
				return false;
		} else if (!case_number.equals(other.case_number))
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (email_display_name == null) {
			if (other.email_display_name != null)
				return false;
		} else if (!email_display_name.equals(other.email_display_name))
			return false;
		if (email_notification_text == null) {
			if (other.email_notification_text != null)
				return false;
		} else if (!email_notification_text.equals(other.email_notification_text))
			return false;
		if (email_reply_to == null) {
			if (other.email_reply_to != null)
				return false;
		} else if (!email_reply_to.equals(other.email_reply_to))
			return false;
		if (id != other.id)
			return false;
		if (matter_contacts == null) {
			if (other.matter_contacts != null)
				return false;
		} else if (!matter_contacts.equals(other.matter_contacts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (po_number == null) {
			if (other.po_number != null)
				return false;
		} else if (!po_number.equals(other.po_number))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		return true;
	}



}
