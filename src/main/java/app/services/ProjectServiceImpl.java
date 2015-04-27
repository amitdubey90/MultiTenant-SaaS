package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.ProjectDaoIfc;
import app.data.ProjectData;

@Service("projectService")
public class ProjectServiceImpl implements ProjectServiceIfc{
	
	@Autowired
	ProjectDaoIfc projectDao;

	public boolean createProject(String userId,ProjectData projectData) {
		// TODO Auto-generated method stub
		return projectDao.createProject(userId,projectData);
	}

	public List<ProjectData> getProjects(String userId) {
		// TODO Auto-generated method stub
		return projectDao.getProjects(userId);
	}

	public ProjectData getProjectDetails(String userId,
			String recordId) {
		// TODO Auto-generated method stub
		return projectDao.getProjectDetails(userId, recordId);
	}

	public boolean deleteProject(String userId,String recordId) {
		// TODO Auto-generated method stub
		return projectDao.deleteProject(userId, recordId);
	}

	public boolean updateProject(String userId, ProjectData projectData) {
		// TODO Auto-generated method stub
		return projectDao.updateProject(userId, projectData);
	}

}
