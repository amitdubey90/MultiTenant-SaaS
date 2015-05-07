package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import app.data.ProjectActivities;
import app.data.ProjectActivity;
import app.utilities.DatabaseConnection;
import app.utilities.DatabaseQueries;
import app.utilities.ServiceConstants;

@Repository
public class ProjectActivitiesDaoImpl implements ProjectActivitiesDaoIfc {

	public int createProjectActivity(String userId, String projType,
			int projId, ProjectActivities projActivity) {
		Connection dbCon = DatabaseConnection.getConnection();

		int recordId;
		dbCon = DatabaseConnection.getConnection();
		int counter = 1;
		PreparedStatement stmt = null;
		try {

			if (projType.equalsIgnoreCase(ServiceConstants.WATERFALL_MODEL)) {
				/**
				 * fields coming in request - projId, task name, task desc,
				 * start date, end date, resource id, resource name,status
				 **/
				stmt = dbCon
						.prepareCall(DatabaseQueries.CREATE_PROJECT_ACTIVITY_WATERFALL);
				recordId = insertUpdateProjectActivityForWaterfall(userId,
						projId, projActivity, dbCon, stmt, false);

			} else if (projType.equalsIgnoreCase(ServiceConstants.SCRUM_MODEL)) {
				/**
				 * fields coming in request - user id, 'Story',projId, story
				 * name, story points, story days, sprint id, sprint name,hours
				 * remaining,team id
				 **/
				stmt = dbCon
						.prepareCall(DatabaseQueries.CREATE_PROJECT_ACTIVITY_SCRUM);

				recordId = insertUpdateProjectActivityForScrum(userId, projId,
						projActivity, dbCon, stmt, false);

			} else {
				stmt = dbCon
						.prepareCall(DatabaseQueries.CREATE_PROJECT_ACTIVITY_KANBAN);
				recordId = insertUpdateProjectActivityForKanban(userId, projId,
						projActivity, dbCon, stmt, false);

			}

			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		return recordId;
	}

	private int insertUpdateProjectActivityForKanban(String userId, int projId,
			ProjectActivities projActivity, Connection dbCon,
			PreparedStatement stmt, boolean isUpdate) {

		ResultSet rs = null;

		int counter = 1;

		try {

			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, ServiceConstants.CARD);
			stmt.setInt(counter++, projId);
			List<ProjectActivity> record = projActivity.getColumns();
			for (ProjectActivity activity : record) {
				if (isUpdate) {
					if (activity.getColumnName().equalsIgnoreCase(
							ServiceConstants.GROUP_ID)
							|| activity.getColumnName().equalsIgnoreCase(
									ServiceConstants.RECORD_ID)) {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setInt(counter++,
								Integer.parseInt(activity.getColumnValue()));
					} else {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setString(counter++, activity.getColumnValue());
					}
				} else {
					if (activity.getColumnName().equalsIgnoreCase(
							ServiceConstants.GROUP_ID)) {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setInt(counter++,
								Integer.parseInt(activity.getColumnValue()));
					}

					else {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setString(counter++, activity.getColumnValue());
					}
				}

			}
			rs = stmt.executeQuery();
			rs.next();
			int recordId = rs.getInt(ServiceConstants.DATA_RECORD_ID);
			return recordId;
		} catch (SQLException e) {

			System.out
					.println("ProjectActivitesDAOImp:insertUpdateProjectActivityForKanban: SQL Exception");
			e.printStackTrace();
			return 0;
		}

	}

	private int insertUpdateProjectActivityForScrum(String userId, int projId,
			ProjectActivities projActivity, Connection dbCon,
			PreparedStatement stmt, boolean b) {

		ResultSet rs = null;

		int counter = 1;

		try {

			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, ServiceConstants.STORY);
			stmt.setInt(counter++, projId);
			List<ProjectActivity> record = projActivity.getColumns();
			for (ProjectActivity activity : record) {
				if (activity.getColumnName().equalsIgnoreCase(
						ServiceConstants.STORY_NAME)
						|| activity.getColumnName().equalsIgnoreCase(
								ServiceConstants.SPRINT_NAME)
						|| activity.getColumnName().equalsIgnoreCase("status")) {
					System.out.println(activity.getColumnName()
							+ " set to counter: " + counter);
					stmt.setString(counter++, activity.getColumnValue());
				} else {
					System.out.println(activity.getColumnName()
							+ " set to counter: " + counter);
					stmt.setInt(counter++,
							Integer.parseInt(activity.getColumnValue()));
				}

			}

			rs = stmt.executeQuery();
			rs.next();
			int recordId = rs.getInt(ServiceConstants.DATA_RECORD_ID);
			return recordId;

		} catch (SQLException e) {

			System.out
					.println("ProjectActivitesDAOImp:insertUpdateProjectActivityForScrum: SQL Exception");
			e.printStackTrace();
			return 0;
		}

	}

	private int insertUpdateProjectActivityForWaterfall(String userId,
			int projId, ProjectActivities projActivity, Connection dbCon,
			PreparedStatement stmt, boolean isUpdate) {

		ResultSet rs = null;
		// PreparedStatement stmt = null;
		int counter = 1;

		try {

			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, ServiceConstants.TASK);
			stmt.setInt(counter++, projId);
			List<ProjectActivity> record = projActivity.getColumns();
			for (ProjectActivity activity : record) {
				if (isUpdate) {
					if (activity.getColumnName().equalsIgnoreCase(
							ServiceConstants.RESOURCE_ID)
							|| activity.getColumnName().equalsIgnoreCase(
									ServiceConstants.RECORD_ID)) {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter + " value is:"
								+ activity.getColumnValue());
						stmt.setInt(counter++,
								Integer.parseInt(activity.getColumnValue()));
					} else {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setString(counter++, activity.getColumnValue());
					}
				} else {
					if (activity.getColumnName().equalsIgnoreCase(
							ServiceConstants.RESOURCE_ID)) {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setInt(counter++,
								Integer.parseInt(activity.getColumnValue()));
					}

					else {
						System.out.println(activity.getColumnName()
								+ " set to counter: " + counter);
						stmt.setString(counter++, activity.getColumnValue());
					}
				}

			}
			rs = stmt.executeQuery();
			rs.next();
			int recordId = rs.getInt("record_id");
			return recordId;
		} catch (SQLException e) {
			System.out
					.println("ProjectActivitesDAOImp:insertUpdateProjectActivityForWaterfall: SQL Exception");
			e.printStackTrace();
			return 0;
		}

	}

	public ProjectActivities getProjectActivites(String userId, String projId,
			String projType) {

		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String modelTable = null;

		dbCon = DatabaseConnection.getConnection();
		int counter = 1;
		try {
			stmt = dbCon.prepareStatement(DatabaseQueries.GET_TENANT_ID);
			stmt.setInt(counter++, Integer.parseInt(userId));
			if (projType.equalsIgnoreCase(ServiceConstants.WATERFALL_MODEL)) {
				modelTable = ServiceConstants.TASK;
			} else if (projType.equalsIgnoreCase(ServiceConstants.SCRUM_MODEL)) {
				modelTable = ServiceConstants.STORY;
			} else {
				modelTable = ServiceConstants.CARD;
			}
			stmt.setString(counter++, modelTable);
			rs = stmt.executeQuery();
			rs.next();
			int fieldId = 0;
			int tenantId = rs.getInt(ServiceConstants.TENANT_ID);
			counter = 1;
			stmt = dbCon
					.prepareStatement(DatabaseQueries.GET_FIELD_NAMES_FOR_TENANT);
			stmt.setInt(counter++, tenantId);
			rs = stmt.executeQuery();

			ProjectActivity projActivity = new ProjectActivity();
			List<ProjectActivity> list = new ArrayList<ProjectActivity>();
			while (rs.next()) {
				projActivity = new ProjectActivity();
				projActivity.setColumnName(rs
						.getString(ServiceConstants.FIELD_NAME));
				projActivity.setColumnValue(rs
						.getString(ServiceConstants.FIELD_ID));
				projActivity.setColumnType(rs.getString("field_type"));
				list.add(projActivity);
			}

			for (ProjectActivity activity : list) {

				if (activity.getColumnName().equalsIgnoreCase("project_id")) {
					fieldId = Integer.parseInt(activity.getColumnValue());
					break;
				}
			}
			stmt = dbCon
					.prepareStatement(DatabaseQueries.GET_INFO_FROM_TENANT_ID);
			counter = 1;
			List<List<ProjectActivity>> finalProjectActList = new ArrayList<List<ProjectActivity>>();
			ProjectActivities projActivities = new ProjectActivities();
			stmt.setInt(counter++, tenantId);
			stmt.setString(counter++, projId);
			stmt.setInt(counter++, fieldId);
			rs = stmt.executeQuery();
			ProjectActivity proj;
			List<ProjectActivity> colList = null;
			int prev = 0;
			int current = 0;

			while (rs.next()) {

				current = rs.getInt(ServiceConstants.RECORD_ID);
				if (current != prev) {
					prev = current;
					proj = new ProjectActivity();
					proj.setColumnName(ServiceConstants.RECORD_ID);
					proj.setColumnValue(String.valueOf(current));
					proj.setColumnType("int");
					colList = new ArrayList<ProjectActivity>();
					colList.add(proj);
				}
				for (ProjectActivity p : list) {
					if ((p.getColumnValue().equals(
							rs.getString(ServiceConstants.FIELD_ID)) && (current == prev))) {
						proj = new ProjectActivity();
						proj.setColumnName(p.getColumnName());
						proj.setColumnValue(rs
								.getString(ServiceConstants.VALUE));
						proj.setColumnType(p.getColumnType());
						colList.add(proj);
						if (colList.size() == list.size() + 1) {
							finalProjectActList.add(colList);
						}
					}
				}
			}

			DatabaseConnection.closeConnection(dbCon);
			projActivities.setProjActivitiesList(finalProjectActList);
			return projActivities;

		} catch (Exception e) {

		}
		return null;
	}

	public boolean deleteProjectActivity(String userId, String activityId,
			String projType) {
		// TODO Auto-generated method stub
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		dbCon = DatabaseConnection.getConnection();
		int counter = 1;
		try {
			stmt = dbCon
					.prepareStatement(DatabaseQueries.DELETE_ACTIVITY_RECORD);
			stmt.setInt(counter++, Integer.parseInt(activityId));
			stmt.executeUpdate();

			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int updateProjectActivity(String userId, int projId,
			String projType, ProjectActivities projectActivityData) {
		// TODO Auto-generated method stub
		Connection dbCon = DatabaseConnection.getConnection();
		dbCon = DatabaseConnection.getConnection();
		int recordId = 0;
		PreparedStatement stmt = null;
		try {
			if (projType.equalsIgnoreCase(ServiceConstants.WATERFALL_MODEL)) {
				/**
				 * fields coming in request - projId, task name, task desc,
				 * start date, end date, resource id, resource name
				 **/
				stmt = dbCon
						.prepareCall(DatabaseQueries.UPDATE_PROJECT_ACTIVITY_WATERFALL);
				recordId = insertUpdateProjectActivityForWaterfall(userId,
						projId, projectActivityData, dbCon, stmt, true);

			} else if (projType.equalsIgnoreCase(ServiceConstants.SCRUM_MODEL)) {
				/**
				 * fields coming in request - user id, 'Story',projId, story
				 * name, story points, story days, sprint id, sprint name,hours
				 * remaining,team id
				 **/
				stmt = dbCon
						.prepareCall(DatabaseQueries.UPDATE_PROJECT_ACTIVITY_SCRUM);
				recordId = insertUpdateProjectActivityForScrum(userId, projId,
						projectActivityData, dbCon, stmt, true);
			} else {
				stmt = dbCon
						.prepareCall(DatabaseQueries.UPDATE_PROJECT_ACTIVITY_KANBAN);
				recordId = insertUpdateProjectActivityForKanban(userId, projId,
						projectActivityData, dbCon, stmt, true);
			}
		} catch (SQLException e) {

		}
		return recordId;
	}
}
