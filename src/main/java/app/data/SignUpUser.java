package app.data;

public class SignUpUser {

	 private int user_id;
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



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	  return "User [user_id=" + user_id + ",firstname=" + firstname + ", lastname="
	    + lastname +",password="+password+ "]";
	 }
	
}
