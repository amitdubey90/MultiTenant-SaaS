package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import app.data.ColumnData;
import app.utilities.DatabaseConnection;
import app.utilities.DatabaseQueries;

/**
 * @author amit
 *
 */
@Repository
public class LookupDaoImpl implements LookupDaoIfc {

	
	public String createLookupValue(String userId, String lookUpType,
			String projectId, List<ColumnData> values) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = null;
		String sql = null;
		Map<String, Integer> fieldNameMap = new HashMap<String, Integer>();

		try {
			conn = DatabaseConnection.getConnection();
			sql = DatabaseQueries.GET_TABLES_FOR_SDLC;
			pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, lookUpType);
			rs = pst.executeQuery();

			int tableId = -1;
			int colIdx = 1;

			while (rs.next()) {
				tableId = rs.getInt("tenantid");
				fieldNameMap.put(rs.getString("field_name").toLowerCase(),
						rs.getInt("fieldid"));
			}

			int recordId = -1;

			sql = DatabaseQueries.GET_MAX_RECORDID;
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next())
				recordId = rs.getInt(1) + 1;

			if (recordId != -1 && tableId != -1) {
				conn.setAutoCommit(false);
				sql = "";
				pst = conn.prepareStatement(sql);

				for (ColumnData cd : values) {
					colIdx = 1;
					pst.setInt(colIdx++, recordId);
					pst.setInt(colIdx++, tableId);
					pst.setInt(colIdx++,
							fieldNameMap.get(cd.getColumnName().toLowerCase()));
					pst.setString(colIdx++, cd.getValue());
					pst.addBatch();
				}
				colIdx = 1;
				pst.setInt(colIdx++, recordId);
				pst.setInt(colIdx++, tableId);
				pst.setInt(colIdx++, fieldNameMap.get("project_id"));
				pst.setString(colIdx++, projectId);
				pst.addBatch();

				pst.executeBatch();
				conn.commit();
				result = String.valueOf(recordId);
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} /*
		 * catch (IOException ie) { ie.printStackTrace(); }
		 */
		return result;
	}

	public List<List<ColumnData>> getLookupValue(String userId,
			String lookUpType, String projectId) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		List<List<ColumnData>> resultList = new ArrayList<List<ColumnData>>();
		try {
			conn = DatabaseConnection.getConnection();
			sql = DatabaseQueries.GET_LOOKUP_VALUES;

			pst = conn.prepareStatement(sql);

			int colIdx = 1;

			pst.setString(colIdx++, userId);
			pst.setString(colIdx++, lookUpType);
			pst.setString(colIdx++, projectId);

			rs = pst.executeQuery();

			List<ColumnData> tempList = null;
			ColumnData cd = null;
			int prevRecordId = -1;
			int currRecordId = -1;
			while (rs.next()) {
				currRecordId = rs.getInt("recordId");
				if (prevRecordId != currRecordId) {
					tempList = new ArrayList<ColumnData>();
					resultList.add(tempList);
				}
				cd = new ColumnData();
				cd.setColumnName(rs.getString("field_name"));
				cd.setValue(rs.getString("value"));
				tempList.add(cd);

				prevRecordId = currRecordId;

			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}

		return resultList;
	}
}
