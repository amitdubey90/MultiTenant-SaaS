package app.data;

public class ProjectStatus {
	
	private int completedTasks;
	
	private int totalTasks;
	
	private int newTasks;
	
	private int inProgressTasks;
	
	private int percentComplt;

	public int getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public int getNewTasks() {
		return newTasks;
	}

	public void setNewTasks(int newTasks) {
		this.newTasks = newTasks;
	}

	public int getInProgressTasks() {
		return inProgressTasks;
	}

	public void setInProgressTasks(int inProgressTasks) {
		this.inProgressTasks = inProgressTasks;
	}

	public int getPercentComplt() {
		return percentComplt;
	}

	public void setPercentComplt(int percentComplt) {
		this.percentComplt = percentComplt;
	}
	
	

}
