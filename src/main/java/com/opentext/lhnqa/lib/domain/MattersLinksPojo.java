package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.List;

public class MattersLinksPojo {

	HrefPojo self;
	HrefPojo user;
	HrefPojo stats;
	HrefPojo folder;
	HrefPojo custodians;
	HrefPojo legal_holds;


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
	public HrefPojo getStats() {
		return stats;
	}
	public void setStats(HrefPojo stats) {
		this.stats = stats;
	}
	public HrefPojo getFolder() {
		return folder;
	}
	public void setFolder(HrefPojo folder) {
		this.folder = folder;
	}
	public HrefPojo getCustodians() {
		return custodians;
	}
	public void setCustodians(HrefPojo custodians) {
		this.custodians = custodians;
	}

	public HrefPojo getLegal_holds() {
		return legal_holds;
	}
	public void setLegal_holds(HrefPojo legal_holds) {
		this.legal_holds = legal_holds;
	}

	public List<String> obtainAllLinks() {
		List<String>links = new ArrayList<String>();
		links.add(getSelf().getHref());
		links.add(getUser().getHref());
		links.add(getStats().getHref());
		links.add(getFolder()==null?null:getFolder().getHref());
		links.add(getCustodians()==null?null:getCustodians().getHref());
		links.add(getLegal_holds()==null?null:getLegal_holds().getHref());
		return links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custodians == null) ? 0 : custodians.hashCode());
		result = prime * result + ((folder == null) ? 0 : folder.hashCode());
		result = prime * result + ((legal_holds == null) ? 0 : legal_holds.hashCode());
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
		MattersLinksPojo other = (MattersLinksPojo) obj;
		if (custodians == null) {
			if (other.custodians != null)
				return false;
		} else if (!custodians.equals(other.custodians))
			return false;
		if (folder == null) {
			if (other.folder != null)
				return false;
		} else if (!folder.equals(other.folder))
			return false;
		if (legal_holds == null) {
			if (other.legal_holds != null)
				return false;
		} else if (!legal_holds.equals(other.legal_holds))
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
