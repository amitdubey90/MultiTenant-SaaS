package app.dao;

import app.data.ProjectActivities;

public interface ProjectActivitiesDaoIfc {

	int createProjectActivity(String userId, String projType,
			int projId, ProjectActivities projActivity);

	ProjectActivities getProjectActivites(String userId, String projId,String projType);

	boolean deleteProjectActivity(String userId, String projId, String projType);

	int updateProjectActivity(String userId, int projId, String projType,
			ProjectActivities projectActivityData);

}
