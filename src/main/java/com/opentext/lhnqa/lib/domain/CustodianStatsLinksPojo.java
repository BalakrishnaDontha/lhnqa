package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.List;

public class CustodianStatsLinksPojo {

	HrefPojo self;
	HrefPojo custodian;

	
	public HrefPojo getSelf() {
		return self;
	}

	public void setSelf(HrefPojo self) {
		this.self = self;
	}


	public HrefPojo getCustodian() {
		return custodian;
	}

	public void setCustodian(HrefPojo custodian) {
		this.custodian = custodian;
	}

	public List<String> obtainAllLinks() {
		List<String>links = new ArrayList<String>();
		links.add(getSelf().getHref());
		links.add(getCustodian().getHref());
		return links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custodian == null) ? 0 : custodian.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
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
		CustodianStatsLinksPojo other = (CustodianStatsLinksPojo) obj;
		if (custodian == null) {
			if (other.custodian != null)
				return false;
		} else if (!custodian.equals(other.custodian))
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		return true;
	}
	
	
	
}
