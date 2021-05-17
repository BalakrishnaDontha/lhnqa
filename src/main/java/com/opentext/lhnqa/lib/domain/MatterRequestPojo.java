package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatterRequestPojo {

	String name;
	String number;
	String notes;
	String caption;
	String po_number;
	String case_number;
	String business_unit;
	String region;
	List<MatterContactPojo> matter_contacts_attributes;
	List<Long> custodians_ids;
	String email_display_name;
	String email_reply_to;
	String email_notification_text;
	Map<Long, Long> CustodianMatterCount;

	public MatterRequestPojo() {

		name = "QAMatter" + + new Random().nextInt();
		number = "405"+ new Random().nextInt(1000);
		notes = "This is generated from QA Automation suite";
		caption = "copy rights";
		po_number = "523316"+ new Random().nextInt(100);
		case_number = "LHN1"+ new Random().nextInt(1000);
		business_unit = "software";
		region = "India";
		matter_contacts_attributes = new ArrayList<MatterContactPojo>();
		matter_contacts_attributes.add(new MatterContactPojo());
		matter_contacts_attributes.add(new MatterContactPojo());
		custodians_ids = new ArrayList<Long>();
		email_display_name = "Matter Copy rights";
		email_reply_to = "copyrightsmat@otxyz.com";
		email_notification_text = "It is pleasure to inform all of you that, you are under matter.";
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

	public List<MatterContactPojo> getMatter_contacts_attributes() {
		return matter_contacts_attributes;
	}

	public void setMatter_contacts_attributes(List<MatterContactPojo> matter_contacts_attributes) {
		this.matter_contacts_attributes = matter_contacts_attributes;
	}

	public List<Long> getCustodians_ids() {
		return custodians_ids;
	}

	public void setCustodians_ids(List<Long> custodians_ids) {
		this.custodians_ids = custodians_ids;
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

	public void sortCustodians() {
		Collections.sort(this.custodians_ids);
	}

	public Map<Long, Long> getCustodianMatterCount() {
		return CustodianMatterCount;
	}

	public void setCustodianMatterCount(Map<Long, Long> custodianMatterCount) {
		CustodianMatterCount = custodianMatterCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((business_unit == null) ? 0 : business_unit.hashCode());
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((case_number == null) ? 0 : case_number.hashCode());
		result = prime * result + ((custodians_ids == null) ? 0 : custodians_ids.hashCode());
		result = prime * result + ((email_display_name == null) ? 0 : email_display_name.hashCode());
		result = prime * result + ((email_notification_text == null) ? 0 : email_notification_text.hashCode());
		result = prime * result + ((email_reply_to == null) ? 0 : email_reply_to.hashCode());
		result = prime * result + ((matter_contacts_attributes == null) ? 0 : matter_contacts_attributes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((po_number == null) ? 0 : po_number.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		MatterRequestPojo other = (MatterRequestPojo) obj;
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
		if (custodians_ids == null) {
			if (other.custodians_ids != null)
				return false;
		} else if (!custodians_ids.equals(other.custodians_ids))
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
		if (matter_contacts_attributes == null) {
			if (other.matter_contacts_attributes != null)
				return false;
		} else if (!matter_contacts_attributes.equals(other.matter_contacts_attributes))
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
		return true;
	}


}
