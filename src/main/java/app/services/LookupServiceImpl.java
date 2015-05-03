package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.LookupDaoIfc;
import app.data.ColumnData;

@Service
public class LookupServiceImpl implements LookupServiceIfc {

	@Autowired
	LookupDaoIfc dao;
	
	public String createLookupValue(String userId, String lookUpType,
			String projectId, List<ColumnData> values) {
		return dao.createLookupValue(userId, lookUpType, projectId, values);
	}

	public List<List<ColumnData>> getLookupValue(String userId,
			String lookUpType, String projectId) {
		return dao.getLookupValue(userId, lookUpType, projectId);
	}

}
