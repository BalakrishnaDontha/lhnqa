package com.opentext.lhnqa.lib.domain;

import java.util.List;

public class MattersEmbeddedListPojo {

	List<MatterResponsePojo> matters;

	public List<MatterResponsePojo> getMatters() {
		return matters;
	}

	public void setMatters(List<MatterResponsePojo> matters) {
		this.matters = matters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matters == null) ? 0 : matters.hashCode());
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
		MattersEmbeddedListPojo other = (MattersEmbeddedListPojo) obj;
		if (matters == null) {
			if (other.matters != null)
				return false;
		} else if (!matters.equals(other.matters))
			return false;
		return true;
	}


}
