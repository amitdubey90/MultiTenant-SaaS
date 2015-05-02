package app.dao;

import java.util.List;

import app.data.ProjectActivities;
import app.data.ProjectActivity;

public interface ProjectActivitiesDaoIfc {

	int createProjectActivity(String userId, String projType,
			int projId, ProjectActivities projActivity);

	List<List<ProjectActivity>> getProjectActivites(String userId, String projId,String projType);

	boolean deleteProjectActivity(String userId, String projId, String projType);

	int updateProjectActivity(String userId, int projId, String projType,
			ProjectActivities projectActivityData);

}
