package com.opentext.lhnqa.lib.domain;

import java.util.List;

public class CustodiansEmbeddedListPojo {

	List<CustodianResponsePojo> custodians;

	public List<CustodianResponsePojo> getCustodians() {
		return custodians;
	}

	public void setCustodians(List<CustodianResponsePojo> custodians) {
		this.custodians = custodians;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custodians == null) ? 0 : custodians.hashCode());
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
		CustodiansEmbeddedListPojo other = (CustodiansEmbeddedListPojo) obj;
		if (custodians == null) {
			if (other.custodians != null)
				return false;
		} else if (!custodians.equals(other.custodians))
			return false;
		return true;
	}


}
