package app.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.data.Data;
import app.data.ProjectData;
import app.data.ProjectStatus;
import app.utilities.DatabaseConnection;
import app.utilities.DatabaseQueries;

@Service("projectDao")
public class ProjectDaoImpl implements ProjectDaoIfc{

	public String createProject(String userId,ProjectData projectData) {
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		projectData.print();
		//fetching tenant_id from tenant table
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		String recordId =null;
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
			recordId = rs.getString("record_id");
			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		return recordId;
	}

	public List<Data> getProjects(String userId) {
		// TODO Auto-generated method stub
		//List<ProjectData> projects = new ArrayList<ProjectData>();
		List<Data> list =null;
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//fetching tenant_id from tenant table
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		try {
			stmt = dbCon.prepareStatement(DatabaseQueries.GET_TENANT_ID);
			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, "Project");
			
			rs = stmt.executeQuery();
			rs.next();
			int tenantId = rs.getInt("tenantId");
			counter=1;
			//get projects for tenantId
			
			stmt = dbCon.prepareStatement(DatabaseQueries.GET_PROJECTS);
			stmt.setInt(counter++,tenantId);
			
			rs = stmt.executeQuery();
			int current = 0;
			int previous =0;
			list = new ArrayList<Data>();
			Data projectData=new Data();
			while(rs.next()){
				current = rs.getInt("recordId");
				if(current!=previous){
					previous = current;
					projectData = new Data();
					projectData.setRecordId(String.valueOf(current));
					list.add(projectData);
				}
				projectData.getValueList().add(rs.getString("value"));
				projectData.getFieldList().add(rs.getString("field_name"));
			}
			
			/*for(Data data:list){
				ProjectData pData = new ProjectData();
				pData.fillValues(data);
				//projects.add(pData);
			}*/
			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Data getProjectDetails(String userId,
			String recordId) {
		// TODO Auto-generated method stub
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Data data = null;
		//fetching tenant_id from tenant table
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		try {
			stmt = dbCon.prepareStatement(DatabaseQueries.GET_DATA_BY_RECORD_ID);
			stmt.setInt(counter++, Integer.parseInt(recordId));	
			rs = stmt.executeQuery();
			data=new Data();
			while(rs.next()){
				data.setRecordId(String.valueOf(rs.getInt("recordId")));
				data.getValueList().add(rs.getString("value"));
				data.getFieldList().add(rs.getString("field_name"));
			}
			
			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public boolean deleteProject(String userId, String recordId) {
		// TODO Auto-generated method stub
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		try {
			stmt = dbCon.prepareStatement(DatabaseQueries.DELETE_DATA_BY_RECORD_ID);
			stmt.setInt(counter++, Integer.parseInt(recordId));	
			stmt.executeUpdate();

			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateProject(String userId, ProjectData projectData) {
		// TODO Auto-generated method stub
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		projectData.print();
		//fetching tenant_id from tenant table
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		try {
			stmt = dbCon.prepareCall(DatabaseQueries.UPDATE_PROJECT);
			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, "Project");
			stmt.setString(counter++, projectData.getProjectName());
			stmt.setString(counter++, projectData.getProjectDesc());
			stmt.setString(counter++, projectData.getOwner());
			stmt.setString(counter++, projectData.getStartDate());
			stmt.setString(counter++, projectData.getEndDate());
			stmt.setInt(counter++, Integer.parseInt(projectData.getRecordId()));
			stmt.executeQuery();
			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public List<Data> getProjectStatusWaterfall(String userId,
			String recordId) {
		// TODO Auto-generated method stub
		List<Data> list =null;
		
		Connection dbCon = DatabaseConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//fetching tenant_id from tenant table
		dbCon = DatabaseConnection.getConnection();
		int counter =1;
		try {
			stmt = dbCon.prepareStatement(DatabaseQueries.GET_TENANT_ID);
			stmt.setInt(counter++, Integer.parseInt(userId));
			stmt.setString(counter++, "Task");
			
			rs = stmt.executeQuery();
			rs.next();
			int tenantId = rs.getInt("tenantId");
			counter=1;
			//get projects for tenantId
			
			stmt = dbCon.prepareStatement(DatabaseQueries.GET_TASKS_FOR_PROJECT);
			stmt.setInt(counter++,tenantId);
			stmt.setInt(counter++,Integer.parseInt(recordId));
			
			rs = stmt.executeQuery();
			int current = 0;
			int previous =0;
			list = new ArrayList<Data>();
			Data projectData=new Data();
			while(rs.next()){
				current = rs.getInt("recordId");
				if(current!=previous){
					previous = current;
					projectData = new Data();
					projectData.setRecordId(String.valueOf(current));
					list.add(projectData);
				}
				projectData.getValueList().add(rs.getString("value"));
				projectData.getFieldList().add(rs.getString("field_name"));
			}
	
			DatabaseConnection.closeConnection(dbCon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Data> getProjectStatusKanban(String userId, String recordId) {
		// TODO Auto-generated method stub
		return getProjectStatusWaterfall(userId,recordId);
	}

	public ProjectStatus getProjectStatusScrum(String userId, String recordId) {
		// TODO Auto-generated method stub
		return null;
	}

}
