package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.ProjectActivitiesDaoIfc;
import app.data.ProjectActivities;

@Service("projectActivities")
public class ProjectActivitiesServiceImpl implements
		ProjectActivitiesServiceIfc {

	@Autowired
	ProjectActivitiesDaoIfc activitiesDao;

	public int createProjectActivity(String userId, String projType,
			int projId, ProjectActivities projActivity) {

		return activitiesDao.createProjectActivity(userId, projType, projId,
				projActivity);
	}

	public ProjectActivities getProjectActivites(String userId,
			String projId, String projType) {

		return activitiesDao.getProjectActivites(userId, projId, projType);
	}

	public boolean deleteProjectActivity(String userId, String activityId,
			String projType) {

		return activitiesDao.deleteProjectActivity(userId, activityId, projType);
	}

	public int updateProjectActivity(String userId, int projId,
			String projType, ProjectActivities projectActivityData) {

		return activitiesDao.updateProjectActivity(userId, projId, projType,
				projectActivityData);
	}

}
