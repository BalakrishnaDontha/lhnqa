package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opentext.lhnqa.api.lib.utils.Filebuilder;

public class LegalHoldRequestPojo {

	boolean draft;
	String name;
	String response_due_date;
	String hold_description;
	List<Long> custodians;
	NoticePojo hold_notice_attributes;
	NoticePojo acknowledgement_reminder_notice_attributes;
	NoticePojo hold_reminder_notice_attributes;
	NoticePojo escalation_notice_attributes;
	NoticePojo release_notice_attributes;
	@JsonIgnore
	List<Filebuilder> allAttachments  = new ArrayList<Filebuilder>();
	
	public LegalHoldRequestPojo() {
		draft=true;
		name="QA Auto LH " + new Random().nextInt();
		Calendar calender = Calendar.getInstance();
		response_due_date = (calender.get(Calendar.YEAR) + 1 )+ ""+ "-" + calender.get(Calendar.MONTH)  + "-" + calender.get(Calendar.DATE) ;
		hold_description = "QA Automation Legal hold";
		hold_notice_attributes = new NoticePojo();
		acknowledgement_reminder_notice_attributes = new NoticePojo(1);
		hold_reminder_notice_attributes = new NoticePojo(3);
		escalation_notice_attributes = new NoticePojo();
		release_notice_attributes = new NoticePojo("release_include_active");
		custodians = new ArrayList<Long>();
		custodians.add(175L);
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponse_due_date() {
		return response_due_date;
	}

	public void setResponse_due_date(String response_due_date) {
		this.response_due_date = response_due_date;
	}

	public String getHold_description() {
		return hold_description;
	}

	public void setHold_description(String hold_description) {
		this.hold_description = hold_description;
	}

	public List<Long> getCustodians() {
		return custodians;
	}

	public void setCustodians(List<Long> custodians) {
		this.custodians = custodians;
	}

	public NoticePojo getHold_notice_attributes() {
		return hold_notice_attributes;
	}

	public void setHold_notice_attributes(NoticePojo hold_notice_attributes) {
		this.hold_notice_attributes = hold_notice_attributes;
	}

	public NoticePojo getAcknowledgement_reminder_notice_attributes() {
		return acknowledgement_reminder_notice_attributes;
	}

	public void setAcknowledgement_reminder_notice_attributes(NoticePojo acknowledgement_reminder_notice_attributes) {
		this.acknowledgement_reminder_notice_attributes = acknowledgement_reminder_notice_attributes;
	}

	public NoticePojo getHold_reminder_notice_attributes() {
		return hold_reminder_notice_attributes;
	}

	public void setHold_reminder_notice_attributes(NoticePojo hold_reminder_notice_attributes) {
		this.hold_reminder_notice_attributes = hold_reminder_notice_attributes;
	}

	public NoticePojo getEscalation_notice_attributes() {
		return escalation_notice_attributes;
	}

	public void setEscalation_notice_attributes(NoticePojo escalation_notice_attributes) {
		this.escalation_notice_attributes = escalation_notice_attributes;
	}

	public NoticePojo getRelease_notice_attributes() {
		return release_notice_attributes;
	}

	public void setRelease_notice_attributes(NoticePojo release_notice_attributes) {
		this.release_notice_attributes = release_notice_attributes;
	}

	public LegalHoldRequestPojo attachDocumentsToLHN(Filebuilder files) {
		allAttachments.add(files);
		return this;
	}
	
	public List<Filebuilder> obtainAllattachmentsOfLHN(){
		return allAttachments;
	}
}
