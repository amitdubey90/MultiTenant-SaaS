package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.TenantDaoIfc;
import app.data.UserInfo;

@Service
public class TenantServiceImpl implements TenantServiceIfc {

	@Autowired
	TenantDaoIfc dao;

	public boolean createTenant(String userId, String sdlcType) {
		return dao.createTenant(userId, sdlcType);
	}

	public UserInfo userSignup(UserInfo user) {
		return dao.userSignup( user);
	}

	public UserInfo login(UserInfo user) {
		return dao.login( user);
	}
}
