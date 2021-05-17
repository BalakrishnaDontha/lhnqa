package com.opentext.lhnqa.lib.domain;

import java.util.ArrayList;
import java.util.List;

public class MatterStatsLinksPojo {

	HrefPojo self;
	HrefPojo matter;

	public HrefPojo getSelf() {
		return self;
	}
	public void setSelf(HrefPojo self) {
		this.self = self;
	}
	public HrefPojo getMatter() {
		return matter;
	}
	public void setMatter(HrefPojo matter) {
		this.matter = matter;
	}

	public List<String> obtainAllLinks() {
		List<String>links = new ArrayList<String>();
		links.add(getSelf().getHref());
		links.add(getMatter().getHref());
		return links;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matter == null) ? 0 : matter.hashCode());
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
		MatterStatsLinksPojo other = (MatterStatsLinksPojo) obj;
		if (matter == null) {
			if (other.matter != null)
				return false;
		} else if (!matter.equals(other.matter))
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		return true;
	}



}
