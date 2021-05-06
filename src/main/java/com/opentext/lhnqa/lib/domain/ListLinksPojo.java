package com.opentext.lhnqa.lib.domain;

public class ListLinksPojo {

	HrefPojo self;
	HrefPojo next;

	public HrefPojo getSelf() {
		return self;
	}

	public void setSelf(HrefPojo self) {
		this.self = self;
	}

	public HrefPojo getNext() {
		return next;
	}

	public void setNext(HrefPojo next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
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
		ListLinksPojo other = (ListLinksPojo) obj;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		return true;
	}

}
