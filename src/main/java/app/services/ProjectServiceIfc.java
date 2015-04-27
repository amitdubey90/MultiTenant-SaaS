package app.services;

import java.util.List;

import app.data.ProjectData;

public interface ProjectServiceIfc {
	
	public boolean createProject(String userId,ProjectData projectData);
	
	public List<ProjectData> getProjects(String userId);
	
	public ProjectData getProjectDetails(String userId,String recordId);
	
	public boolean deleteProject(String userId,String recordId);
	
	public boolean updateProject(String userId, ProjectData projectData);

}
