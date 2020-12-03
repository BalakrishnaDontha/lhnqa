package com.opentext.lhnqa.lib.domain;

public class CustodianResponsePojo {

	CustodianResponseDataPojo data;

	public CustodianResponseDataPojo getData() {
		return data;
	}

	public void setData(CustodianResponseDataPojo data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		CustodianResponsePojo other = (CustodianResponsePojo) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
