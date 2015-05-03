package app.services;

import java.util.List;

import app.data.ColumnData;

public interface LookupServiceIfc {
	
	public String createLookupValue(String userId, String lookUpType,
			String projectId, List<ColumnData> values);

	public List<List<ColumnData>> getLookupValue(String userId,
			String lookUpType, String projectId);
}
