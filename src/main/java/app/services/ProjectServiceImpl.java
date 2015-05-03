package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.ProjectDaoIfc;
import app.data.Data;
import app.data.ProjectData;
import app.data.ProjectStatus;

@Service("projectService")
public class ProjectServiceImpl implements ProjectServiceIfc{
	
	@Autowired
	ProjectDaoIfc projectDao;

	public String createProject(String userId,ProjectData projectData) {
		// TODO Auto-generated method stub
		return projectDao.createProject(userId,projectData);
	}

	public List<Data> getProjects(String userId) {
		// TODO Auto-generated method stub
		return projectDao.getProjects(userId);
	}

	public Data getProjectDetails(String userId,
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

	public ProjectStatus getProjectStatus(String userId, String recordId,
			String sdlc) {
		// TODO Auto-generated method stub
		if("waterfall".equalsIgnoreCase(sdlc)){
			List<Data> list =  projectDao.getProjectStatusWaterfall(userId, recordId);
			ProjectStatus status = new ProjectStatus();
			for(Data data:list){
				status.setTotalTasks(status.getTotalTasks()+1);
				for(int i=0;i<data.getFieldList().size();i++){
					if(data.getFieldList().get(i).equalsIgnoreCase("status")){
						if(data.getValueList().get(i).equalsIgnoreCase("Done")){
							status.setCompletedTasks(status.getCompletedTasks()+1);
						}
					}
				}
			}
			status.setPercentComplt((status.getCompletedTasks()*100)/status.getTotalTasks());
			return status;
		}
		else if("kanban".equalsIgnoreCase(sdlc)){

			List<Data> list =  projectDao.getProjectStatusKanban(userId, recordId);
			ProjectStatus status = new ProjectStatus();
			for(Data data:list){
				status.setTotalTasks(status.getTotalTasks()+1);
				for(int i=0;i<data.getFieldList().size();i++){
					if(data.getFieldList().get(i).equalsIgnoreCase("status")){
						if(data.getValueList().get(i).equalsIgnoreCase("Done")){
							status.setCompletedTasks(status.getCompletedTasks()+1);
						}
						else if(data.getValueList().get(i).equalsIgnoreCase("New")){
							status.setNewTasks(status.getNewTasks()+1);
						}
						else if(data.getValueList().get(i).equalsIgnoreCase("In Progress")){
							status.setInProgressTasks(status.getInProgressTasks()+1);
						}
					}
				}
			}
			
			return status;
		}
		else if("scrum".equalsIgnoreCase(sdlc))
			return projectDao.getProjectStatusScrum(userId, recordId); 
		else
		return new ProjectStatus();
	}

}
