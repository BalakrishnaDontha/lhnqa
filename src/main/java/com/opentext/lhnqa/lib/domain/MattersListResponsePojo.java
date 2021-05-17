package com.opentext.lhnqa.lib.domain;

public class MattersListResponsePojo {

	ListLinksPojo _links;
	PagePojo page;
	MattersEmbeddedListPojo _embedded;

	public ListLinksPojo get_links() {
		return _links;
	}
	public void set_links(ListLinksPojo _links) {
		this._links = _links;
	}
	public PagePojo getPage() {
		return page;
	}
	public void setPage(PagePojo page) {
		this.page = page;
	}
	public MattersEmbeddedListPojo get_embedded() {
		return _embedded;
	}
	public void set_embedded(MattersEmbeddedListPojo _embedded) {
		this._embedded = _embedded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_embedded == null) ? 0 : _embedded.hashCode());
		result = prime * result + ((_links == null) ? 0 : _links.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
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
		MattersListResponsePojo other = (MattersListResponsePojo) obj;
		if (_embedded == null) {
			if (other._embedded != null)
				return false;
		} else if (!_embedded.equals(other._embedded))
			return false;
		if (_links == null) {
			if (other._links != null)
				return false;
		} else if (!_links.equals(other._links))
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		return true;
	}




}
