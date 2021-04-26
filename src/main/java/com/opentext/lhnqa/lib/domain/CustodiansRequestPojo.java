package com.opentext.lhnqa.lib.domain;

import java.util.Random;

public class CustodiansRequestPojo {

	String first_name;
	String last_name;
	String email;
	String phone;
	String title;
	String employee_id;
	String employee_type;
	String employee_status;
	String department;
	String location;
	String function;
	String business;
	String country;
	String supervisor_email;
	String supervisor_name;
	String delegate_email;
	String delegate_name;

	public CustodiansRequestPojo() {
		first_name="QAAUTOCustodian";
		last_name = ""+ new Random().nextInt();
		email = "QAAutomation@otxyz.com";
		phone = "0405236560";
		title="Mr.";
		location = "Hyderabad";
		department = "Information Techonology";
		country = "India";
		function = "Software Development";
		supervisor_name = "Manager " + new Random().nextInt();
		supervisor_email = "ManagerEmail@otxyz.com";
		delegate_name = "Delegated " + new Random().nextInt();
		delegate_email = "DelegatorEmail@otxyz.com";
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}

	public String getEmployee_status() {
		return employee_status;
	}

	public void setEmployee_status(String employee_status) {
		this.employee_status = employee_status;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSupervisor_email() {
		return supervisor_email;
	}

	public void setSupervisor_email(String supervisor_email) {
		this.supervisor_email = supervisor_email;
	}

	public String getSupervisor_name() {
		return supervisor_name;
	}

	public void setSupervisor_name(String supervisor_name) {
		this.supervisor_name = supervisor_name;
	}

	public String getDelegate_email() {
		return delegate_email;
	}

	public void setDelegate_email(String delegate_email) {
		this.delegate_email = delegate_email;
	}

	public String getDelegate_name() {
		return delegate_name;
	}

	public void setDelegate_name(String delegate_name) {
		this.delegate_name = delegate_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((business == null) ? 0 : business.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((delegate_email == null) ? 0 : delegate_email.hashCode());
		result = prime * result + ((delegate_name == null) ? 0 : delegate_name.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employee_id == null) ? 0 : employee_id.hashCode());
		result = prime * result + ((employee_status == null) ? 0 : employee_status.hashCode());
		result = prime * result + ((employee_type == null) ? 0 : employee_type.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((supervisor_email == null) ? 0 : supervisor_email.hashCode());
		result = prime * result + ((supervisor_name == null) ? 0 : supervisor_name.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		CustodiansRequestPojo other = (CustodiansRequestPojo) obj;
		if (business == null) {
			if (other.business != null)
				return false;
		} else if (!business.equals(other.business))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (delegate_email == null) {
			if (other.delegate_email != null)
				return false;
		} else if (!delegate_email.equals(other.delegate_email))
			return false;
		if (delegate_name == null) {
			if (other.delegate_name != null)
				return false;
		} else if (!delegate_name.equals(other.delegate_name))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		if (employee_status == null) {
			if (other.employee_status != null)
				return false;
		} else if (!employee_status.equals(other.employee_status))
			return false;
		if (employee_type == null) {
			if (other.employee_type != null)
				return false;
		} else if (!employee_type.equals(other.employee_type))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (supervisor_email == null) {
			if (other.supervisor_email != null)
				return false;
		} else if (!supervisor_email.equals(other.supervisor_email))
			return false;
		if (supervisor_name == null) {
			if (other.supervisor_name != null)
				return false;
		} else if (!supervisor_name.equals(other.supervisor_name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
