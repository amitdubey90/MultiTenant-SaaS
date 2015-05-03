package app.dao;

import java.util.List;

import app.data.ColumnData;

public interface LookupDaoIfc {
	
	public String createLookupValue(String userId, String lookUpType,
			String projectId, List<ColumnData> values);

	public List<List<ColumnData>> getLookupValue(String userId,
			String lookUpType, String projectId);
}
