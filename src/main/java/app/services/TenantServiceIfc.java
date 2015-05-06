package app.services;

import app.data.UserInfo;


public interface TenantServiceIfc {
	
	public boolean createTenant(String userId, String sdlcType);
	
	public UserInfo userSignup(UserInfo user);
	
	public UserInfo login(UserInfo user);
}
