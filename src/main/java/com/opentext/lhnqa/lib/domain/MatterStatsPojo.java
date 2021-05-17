package com.opentext.lhnqa.lib.domain;

public class MatterStatsPojo {

	long id;
	String stats_timestamp;
	long custodians;
	long active_legal_holds;
	long released_legal_holds;
	MatterStatsLinksPojo _links;

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
	public long getCustodians() {
		return custodians;
	}
	public void setCustodians(long custodians) {
		this.custodians = custodians;
	}
	public long getActive_legal_holds() {
		return active_legal_holds;
	}
	public void setActive_legal_holds(long active_legal_holds) {
		this.active_legal_holds = active_legal_holds;
	}
	public long getReleased_legal_holds() {
		return released_legal_holds;
	}
	public void setReleased_legal_holds(long released_legal_holds) {
		this.released_legal_holds = released_legal_holds;
	}
	public MatterStatsLinksPojo get_links() {
		return _links;
	}
	public void set_links(MatterStatsLinksPojo _links) {
		this._links = _links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_links == null) ? 0 : _links.hashCode());
		result = prime * result + (int) (active_legal_holds ^ (active_legal_holds >>> 32));
		result = prime * result + (int) (custodians ^ (custodians >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (released_legal_holds ^ (released_legal_holds >>> 32));
		result = prime * result + ((stats_timestamp == null) ? 0 : stats_timestamp.hashCode());
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
		MatterStatsPojo other = (MatterStatsPojo) obj;
		if (_links == null) {
			if (other._links != null)
				return false;
		} else if (!_links.equals(other._links))
			return false;
		if (active_legal_holds != other.active_legal_holds)
			return false;
		if (custodians != other.custodians)
			return false;
		if (id != other.id)
			return false;
		if (released_legal_holds != other.released_legal_holds)
			return false;
		if (stats_timestamp == null) {
			if (other.stats_timestamp != null)
				return false;
		} else if (!stats_timestamp.equals(other.stats_timestamp))
			return false;
		return true;
	}


}
