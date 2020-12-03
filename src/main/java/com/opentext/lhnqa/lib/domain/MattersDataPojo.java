package com.opentext.lhnqa.lib.domain;

import java.util.Arrays;

public class MattersDataPojo {

	BasicDataPojo[] data;

	public BasicDataPojo[] getData() {
		return data;
	}

	public void setData(BasicDataPojo[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
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
		MattersDataPojo other = (MattersDataPojo) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}



}
