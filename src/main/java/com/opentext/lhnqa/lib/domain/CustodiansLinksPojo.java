package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.List;

public class CustodiansLinksPojo {

	HrefPojo self;
	HrefPojo legal_holds;
	HrefPojo stats;
	HrefPojo matters;
	
	
	public HrefPojo getSelf() {
		return self;
	}
	public void setSelf(HrefPojo self) {
		this.self = self;
	}
	public HrefPojo getLegal_holds() {
		return legal_holds;
	}
	public void setLegal_holds(HrefPojo legal_holds) {
		this.legal_holds = legal_holds;
	}
	public HrefPojo getStats() {
		return stats;
	}
	public void setStats(HrefPojo stats) {
		this.stats = stats;
	}
	public HrefPojo getMatters() {
		return matters;
	}
	public void setMatters(HrefPojo matters) {
		this.matters = matters;
	}
	
	public List<String> obtainAllLinks() {
		List<String>links = new ArrayList<String>();
		links.add(getSelf().getHref());
		links.add(getStats().getHref());
		links.add(getLegal_holds()==null?null:getLegal_holds().getHref());
		links.add(getMatters()==null?null:getMatters().getHref());
		return links;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((legal_holds == null) ? 0 : legal_holds.hashCode());
		result = prime * result + ((matters == null) ? 0 : matters.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
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
		CustodiansLinksPojo other = (CustodiansLinksPojo) obj;
		if (legal_holds == null) {
			if (other.legal_holds != null)
				return false;
		} else if (!legal_holds.equals(other.legal_holds))
			return false;
		if (matters == null) {
			if (other.matters != null)
				return false;
		} else if (!matters.equals(other.matters))
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
		return true;
	}
	
	
	
}
