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

import app.data.ColumnMetaData;
import app.utilities.DatabaseConnection;
import app.utilities.DatabaseQueries;

@Repository
public class TenantDaoImpl implements TenantDaoIfc {
	public boolean createTenant(String userId, String sdlcType) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = null;

		try {
			Map<String, List<ColumnMetaData>> tableColMap = new HashMap<String, List<ColumnMetaData>>();
			// List<ColumnMetaData> colList = new ArrayList<ColumnMetaData>();
			// Set<String> tables = new HashSet<String>()
			conn = DatabaseConnection.getConnection();

			sql = DatabaseQueries.GET_TENANT_TABLES;
			pst = conn.prepareStatement(sql);
			pst.setString(1, sdlcType);
			rs = pst.executeQuery();

			String tableName = null;
			int modelId = -1;
			int colIdx = 1;
			ColumnMetaData col = null;
			int tenantId = -1;

			while (rs.next()) {
				modelId = rs.getInt("modelid");
				tableName = rs.getString("table_name");
				if (tableColMap.containsKey(tableName)) {
					tableColMap.get(tableName).add(
							new ColumnMetaData(rs.getString("field_Name"), rs
									.getString("field_Type")));
				} else {
					col = new ColumnMetaData(rs.getString("field_Name"),
							rs.getString("field_Type"));
					List<ColumnMetaData> list = new ArrayList<ColumnMetaData>();
					list.add(col);
					tableColMap.put(tableName, list);
				}
			}
			//System.out.println(">>>>>>>" + modelId);
			if (modelId != -1) {

				sql = DatabaseQueries.GET_MAX_TENANT_ID;
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				if (rs.next()) {
					tenantId = rs.getInt(1) + 1;
				}

				conn.setAutoCommit(false);

				sql = DatabaseQueries.INSERT_TENANT_TABLES;
				pst = conn.prepareStatement(sql);
				sql = DatabaseQueries.INSERT_TENANT_FIELDS;
				PreparedStatement pst2 = conn.prepareStatement(sql);

				for (String key : tableColMap.keySet()) {
					colIdx = 1;
					pst.setInt(colIdx++, tenantId);
					pst.setString(colIdx++, userId);
					pst.setInt(colIdx++, modelId);
					pst.setString(colIdx++, key);
					pst.addBatch();

					List<ColumnMetaData> cols = tableColMap.get(key);
					for (ColumnMetaData c : cols) {
						colIdx = 1;
						pst2.setInt(colIdx++, tenantId);
						pst2.setString(colIdx++, c.getColumnName());
						pst2.setString(colIdx, c.getColumnType());
						pst2.addBatch();
					}

					tenantId += 1;
				}

				pst.executeBatch();
				pst2.executeBatch();
				conn.commit();

				result = true;
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}

		return result;
	}
}
