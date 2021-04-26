package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.List;

public class LHLinksPojo {

	HrefPojo self;
	HrefPojo user;
	HrefPojo custodians;
	HrefPojo hold_notice;
	HrefPojo acknowledgement_reminder_notice;
	HrefPojo hold_reminder_notice_hold;
	HrefPojo escalation_notice;
	HrefPojo release_notice;
	HrefPojo history;
	HrefPojo stats;
	HrefPojo matter;

	public HrefPojo getSelf() {
		return self;
	}
	public void setSelf(HrefPojo self) {
		this.self = self;
	}
	public HrefPojo getUser() {
		return user;
	}
	public void setUser(HrefPojo user) {
		this.user = user;
	}
	public HrefPojo getCustodians() {
		return custodians;
	}
	public void setCustodians(HrefPojo custodians) {
		this.custodians = custodians;
	}
	public HrefPojo getHold_notice() {
		return hold_notice;
	}
	public void setHold_notice(HrefPojo hold_notice) {
		this.hold_notice = hold_notice;
	}
	public HrefPojo getAcknowledgement_reminder_notice() {
		return acknowledgement_reminder_notice;
	}
	public void setAcknowledgement_reminder_notice(HrefPojo acknowledgement_reminder_notice) {
		this.acknowledgement_reminder_notice = acknowledgement_reminder_notice;
	}
	public HrefPojo getHold_reminder_notice_hold() {
		return hold_reminder_notice_hold;
	}
	public void setHold_reminder_notice_hold(HrefPojo hold_reminder_notice_hold) {
		this.hold_reminder_notice_hold = hold_reminder_notice_hold;
	}
	public HrefPojo getEscalation_notice() {
		return escalation_notice;
	}
	public void setEscalation_notice(HrefPojo escalation_notice) {
		this.escalation_notice = escalation_notice;
	}
	public HrefPojo getRelease_notice() {
		return release_notice;
	}
	public void setRelease_notice(HrefPojo release_notice) {
		this.release_notice = release_notice;
	}
	public HrefPojo getHistory() {
		return history;
	}
	public void setHistory(HrefPojo history) {
		this.history = history;
	}
	public HrefPojo getStats() {
		return stats;
	}
	public void setStats(HrefPojo stats) {
		this.stats = stats;
	}
	public HrefPojo getMatter() {
		return matter;
	}
	public void setMatter(HrefPojo matter) {
		this.matter = matter;
	}

	public int getLinksCount() {
		return getClass().getDeclaredFields().length;
	}

	public List<String> obtainAllLinks(){
		List<String>links = new ArrayList<String>();
		links.add(getSelf().getHref());
		links.add(getUser().getHref());
		links.add(getCustodians()==null?null:getCustodians().getHref());
		links.add(getHold_notice()==null?null:getHold_notice().getHref());
		links.add(getAcknowledgement_reminder_notice()==null?null:getAcknowledgement_reminder_notice().getHref());
		links.add(getHold_reminder_notice_hold()==null?null:getHold_reminder_notice_hold().getHref());
		links.add(getEscalation_notice()==null?null:getEscalation_notice().getHref());
		links.add(getRelease_notice()==null?null:getRelease_notice().getHref());
		//links.add(getHistory().getHref());
		links.add(getStats().getHref());
		links.add(getMatter().getHref());
		return links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acknowledgement_reminder_notice == null) ? 0 : acknowledgement_reminder_notice.hashCode());
		result = prime * result + ((custodians == null) ? 0 : custodians.hashCode());
		result = prime * result + ((escalation_notice == null) ? 0 : escalation_notice.hashCode());
		result = prime * result + ((history == null) ? 0 : history.hashCode());
		result = prime * result + ((hold_notice == null) ? 0 : hold_notice.hashCode());
		result = prime * result + ((hold_reminder_notice_hold == null) ? 0 : hold_reminder_notice_hold.hashCode());
		result = prime * result + ((matter == null) ? 0 : matter.hashCode());
		result = prime * result + ((release_notice == null) ? 0 : release_notice.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		LHLinksPojo other = (LHLinksPojo) obj;
		if (acknowledgement_reminder_notice == null) {
			if (other.acknowledgement_reminder_notice != null)
				return false;
		} else if (!acknowledgement_reminder_notice.equals(other.acknowledgement_reminder_notice))
			return false;
		if (custodians == null) {
			if (other.custodians != null)
				return false;
		} else if (!custodians.equals(other.custodians))
			return false;
		if (escalation_notice == null) {
			if (other.escalation_notice != null)
				return false;
		} else if (!escalation_notice.equals(other.escalation_notice))
			return false;
		if (history == null) {
			if (other.history != null)
				return false;
		} else if (!history.equals(other.history))
			return false;
		if (hold_notice == null) {
			if (other.hold_notice != null)
				return false;
		} else if (!hold_notice.equals(other.hold_notice))
			return false;
		if (hold_reminder_notice_hold == null) {
			if (other.hold_reminder_notice_hold != null)
				return false;
		} else if (!hold_reminder_notice_hold.equals(other.hold_reminder_notice_hold))
			return false;
		if (matter == null) {
			if (other.matter != null)
				return false;
		} else if (!matter.equals(other.matter))
			return false;
		if (release_notice == null) {
			if (other.release_notice != null)
				return false;
		} else if (!release_notice.equals(other.release_notice))
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}




}
