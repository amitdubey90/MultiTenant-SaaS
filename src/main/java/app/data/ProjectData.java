package app.data;

import java.util.List;

public class ProjectData {
	
	private String projectName;	
	private String projectDesc;
	private String owner;
	private String startDate;
	private String endDate;
	private String recordId;
	
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	} 
	
	public void fillValues(Data data){
		List<String> list = data.getValueList();
		this.recordId = data.getRecordId();
		if(list.size()>0){
		this.projectName = list.get(0);
		this.projectDesc = list.get(1);
		this.owner = list.get(2);
		this.startDate = list.get(3);
		this.endDate = list.get(4);
		}
	}
	public void print(){
		System.out.println(projectName+" "+projectDesc+" "+owner+" "+startDate+" "+endDate);
	}
	

}
