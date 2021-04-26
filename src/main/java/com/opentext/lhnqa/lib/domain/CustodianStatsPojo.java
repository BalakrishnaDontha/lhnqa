package com.opentext.lhnqa.lib.domain;

public class CustodianStatsPojo {

	long id;
	String stats_timestamp;
	long total_legal_holds;
	long  active_legal_holds;
	long total_matters;
	CustodianStatsLinksPojo _links;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStats_timestamp() {
		return stats_timestamp;
	}
	public void setStats_timestamp(String stats_timestamp) {
		this.stats_timestamp = stats_timestamp;
	}
	public long getTotal_legal_holds() {
		return total_legal_holds;
	}
	public void setTotal_legal_holds(long total_legal_holds) {
		this.total_legal_holds = total_legal_holds;
	}
	public long getActive_legal_holds() {
		return active_legal_holds;
	}
	public void setActive_legal_holds(long active_legal_holds) {
		this.active_legal_holds = active_legal_holds;
	}
	public long getTotal_matters() {
		return total_matters;
	}
	public void setTotal_matters(long total_matters) {
		this.total_matters = total_matters;
	}
	public CustodianStatsLinksPojo get_links() {
		return _links;
	}
	public void set_links(CustodianStatsLinksPojo _links) {
		this._links = _links;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_links == null) ? 0 : _links.hashCode());
		result = prime * result + (int) (active_legal_holds ^ (active_legal_holds >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((stats_timestamp == null) ? 0 : stats_timestamp.hashCode());
		result = prime * result + (int) (total_legal_holds ^ (total_legal_holds >>> 32));
		result = prime * result + (int) (total_matters ^ (total_matters >>> 32));
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
		CustodianStatsPojo other = (CustodianStatsPojo) obj;
		if (_links == null) {
			if (other._links != null)
				return false;
		} else if (!_links.equals(other._links))
			return false;
		if (active_legal_holds != other.active_legal_holds)
			return false;
		if (id != other.id)
			return false;
		if (stats_timestamp == null) {
			if (other.stats_timestamp != null)
				return false;
		} else if (!stats_timestamp.equals(other.stats_timestamp))
			return false;
		if (total_legal_holds != other.total_legal_holds)
			return false;
		if (total_matters != other.total_matters)
			return false;
		return true;
	}
	


}
