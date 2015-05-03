package app.dao;

import java.util.List;

import app.data.Data;
import app.data.ProjectData;
import app.data.ProjectStatus;

public interface ProjectDaoIfc {
	
	public String createProject(String userId,ProjectData projectData);
	
	public List<Data> getProjects(String userId);
	
	public Data getProjectDetails(String userId,String recordId);
	
	public boolean deleteProject(String userId,String recordId);
	
	public boolean updateProject(String userId, ProjectData projectData);
	
	public List<Data> getProjectStatusWaterfall(String userId, String recordId);
	
	public List<Data> getProjectStatusKanban(String userId, String recordId);
	
	public ProjectStatus getProjectStatusScrum(String userId, String recordId);


}
