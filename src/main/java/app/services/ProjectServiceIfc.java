package app.services;

import java.util.List;

import app.data.Data;
import app.data.ProjectData;
import app.data.ProjectStatus;
import app.data.SprintStatus;

public interface ProjectServiceIfc {
	
	public String createProject(String userId,ProjectData projectData);
	
	public List<Data> getProjects(String userId);
	
	public Data getProjectDetails(String userId,String recordId);
	
	public boolean deleteProject(String userId,String recordId);
	
	public boolean updateProject(String userId, ProjectData projectData);
	
	public ProjectStatus getProjectStatus(String userId,String recordId,String sdlc);
	
	public SprintStatus getScrumProjStatus(String userId,String recordId);

}
