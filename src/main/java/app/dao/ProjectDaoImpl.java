package app.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import app.data.ProjectData;
import app.utilities.DatabaseConnection;
import app.utilities.DatabaseQueries;

@Service("projectDao")
public class ProjectDaoImpl implements ProjectDaoIfc{

	public boolean createProject(String userId,ProjectData projectData) {
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		projectData.print();
		//fetching tenant_id from tenant table
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		try {
			stmt = dbCon.prepareCall(DatabaseQueries.CREATE_PROJECT);
			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, "Project");
			stmt.setString(counter++, projectData.getProjectName());
			stmt.setString(counter++, projectData.getProjectDesc());
			stmt.setString(counter++, projectData.getOwner());
			stmt.setString(counter++, projectData.getStartDate());
			stmt.setString(counter++, projectData.getEndDate());
			rs = stmt.executeQuery();
			rs.next();
			String recordId = rs.getString("record_id");
			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	public List<ProjectData> getProjects(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProjectData getProjectDetails(String userId,
			String recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteProject(String userId, String recordId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateProject(String userId, ProjectData projectData) {
		// TODO Auto-generated method stub
		return false;
	}

}
