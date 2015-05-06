package app.data;



import java.util.ArrayList;
import java.util.List;

import app.data.Data;


public class ProjectActivities {

	
	

	List<ProjectActivity> columns;
	List<List<ProjectActivity>> projActivitiesList;
	public List<List<ProjectActivity>> getProjActivitiesList() {
		return projActivitiesList;
	}

	public void setProjActivitiesList(List<List<ProjectActivity>> projActivitiesList) {
		this.projActivitiesList = projActivitiesList;
	}

	private int activityId; 
	private boolean status;
	
	public ProjectActivities(){
		columns = new ArrayList<ProjectActivity>();
		projActivitiesList = new ArrayList<List<ProjectActivity>>();
	}

	public List<ProjectActivity> getColumns() {
		return columns;
	}

	public void setColumns(List<ProjectActivity> columns) {
		this.columns = columns;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public boolean isStatus(){
		return status;
	}
	public void setStatus(boolean status){
		this.status=status;
	}
	
	public void fillValues(Data data,String modelTable){
	}
	
}
