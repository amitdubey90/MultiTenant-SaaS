package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.TenantDaoIfc;

@Service
public class TenantServiceImpl implements TenantServiceIfc {

	@Autowired
	TenantDaoIfc dao;

	public boolean createTenant(String userId, String sdlcType) {
		return dao.createTenant(userId, sdlcType);
	}
}
