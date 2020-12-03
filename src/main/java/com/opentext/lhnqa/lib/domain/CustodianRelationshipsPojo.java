package com.opentext.lhnqa.lib.domain;

public class CustodianRelationshipsPojo {

	MattersDataPojo matters;
	LegalHoldsDataPojo legal_holds;
	CustodiansLegalHoldsDataPojo custodians_legal_holds;
	CustodiansDataPojo custodians;
	UserDataPojo user;
	
	public MattersDataPojo getMatters() {
		return matters;
	}
	public void setMatters(MattersDataPojo matters) {
		this.matters = matters;
	}
	public LegalHoldsDataPojo getLegal_holds() {
		return legal_holds;
	}
	public void setLegal_holds(LegalHoldsDataPojo legal_holds) {
		this.legal_holds = legal_holds;
	}
	public CustodiansLegalHoldsDataPojo getCustodians_legal_holds() {
		return custodians_legal_holds;
	}
	public void setCustodians_legal_holds(CustodiansLegalHoldsDataPojo custodians_legal_holds) {
		this.custodians_legal_holds = custodians_legal_holds;
	}
	public CustodiansDataPojo getCustodians() {
		return custodians;
	}
	public void setCustodians(CustodiansDataPojo custodians) {
		this.custodians = custodians;
	}
	public UserDataPojo getUser() {
		return user;
	}
	public void setUser(UserDataPojo user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custodians == null) ? 0 : custodians.hashCode());
		result = prime * result + ((custodians_legal_holds == null) ? 0 : custodians_legal_holds.hashCode());
		result = prime * result + ((legal_holds == null) ? 0 : legal_holds.hashCode());
		result = prime * result + ((matters == null) ? 0 : matters.hashCode());
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
		CustodianRelationshipsPojo other = (CustodianRelationshipsPojo) obj;
		if (custodians == null) {
			if (other.custodians != null)
				return false;
		} else if (!custodians.equals(other.custodians))
			return false;
		if (custodians_legal_holds == null) {
			if (other.custodians_legal_holds != null)
				return false;
		} else if (!custodians_legal_holds.equals(other.custodians_legal_holds))
			return false;
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
