package app.services;

import java.util.List;

import app.data.ProjectActivities;
import app.data.ProjectActivity;

public interface ProjectActivitiesServiceIfc {
	public int createProjectActivity(String userId,String projType,int projId, ProjectActivities projActivity);

	public List<List<ProjectActivity>> getProjectActivites(String userId,String projId,String projType);

	public boolean deleteProjectActivity(String userId, String projId,String projType);

	public int updateProjectActivity(String userId, int projId,String projType, ProjectActivities projectActivityData);

}
