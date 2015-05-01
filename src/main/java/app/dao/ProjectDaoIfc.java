package app.dao;

import java.util.List;

import app.data.ProjectData;

public interface ProjectDaoIfc {
	
	public String createProject(String userId,ProjectData projectData);
	
	public List<ProjectData> getProjects(String userId);
	
	public ProjectData getProjectDetails(String userId,String recordId);
	
	public boolean deleteProject(String userId,String recordId);
	
	public boolean updateProject(String userId, ProjectData projectData);


}
