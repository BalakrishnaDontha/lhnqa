package com.opentext.lhnqa.lib.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagePojo {

	boolean has_more;
	int total_count;

	@JsonProperty("has-more")
	public boolean isHas_more() {
		return has_more;
	}

	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}

	@JsonProperty("total-count")
	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (has_more ? 1231 : 1237);
		result = prime * result + total_count;
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
		PagePojo other = (PagePojo) obj;
		if (has_more != other.has_more)
			return false;
		if (total_count != other.total_count)
			return false;
		return true;
	}

}
