package app.dao;

import app.data.UserInfo;


public interface TenantDaoIfc {

	public boolean createTenant(String userId, String sdlcType);

	public UserInfo userSignup(UserInfo user);

	public UserInfo login(UserInfo user);

}
