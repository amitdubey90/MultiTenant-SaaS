package app.services;

import java.util.List;

import app.data.ProjectData;
import app.data.ProjectStatus;

public interface ProjectServiceIfc {
	
	public String createProject(String userId,ProjectData projectData);
	
	public List<ProjectData> getProjects(String userId);
	
	public ProjectData getProjectDetails(String userId,String recordId);
	
	public boolean deleteProject(String userId,String recordId);
	
	public boolean updateProject(String userId, ProjectData projectData);
	
	public ProjectStatus getProjectStatus(String userId,String recordId,String sdlc);

}
