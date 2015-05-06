package app.services;

import app.data.ProjectActivities;

public interface ProjectActivitiesServiceIfc {
	public int createProjectActivity(String userId,String projType,int projId, ProjectActivities projActivity);

	public ProjectActivities getProjectActivites(String userId,String projId,String projType);

	public boolean deleteProjectActivity(String userId, String projId,String projType);

	public int updateProjectActivity(String userId, int projId,String projType, ProjectActivities projectActivityData);

}
