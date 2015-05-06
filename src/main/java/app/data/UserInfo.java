package app.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo {

	private String userId;
	private String firstname;
	private String lastname;
	private String password;
	private String address;
	private String email;
	private long phone;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", password=" + password
				+ ", address=" + address + ", email=" + email + ", phone="
				+ phone + "]";
	}

}
