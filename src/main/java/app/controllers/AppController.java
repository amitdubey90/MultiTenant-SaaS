package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.data.Data;
import app.data.ProjectActivities;
import app.data.ProjectActivity;
import app.data.ProjectData;
import app.data.ProjectStatus;
import app.services.ProjectActivitiesServiceIfc;
import app.services.ProjectServiceIfc;

@RestController
public class AppController {

	@Autowired
	ProjectServiceIfc projectService;
	@Autowired
	ProjectActivitiesServiceIfc projectActivities;
	

	/*
	 * Services for Project management : 1)create project - done 2)update
	 * project - 3)delete project - done 4)getProjects - done 5)get project
	 * details - done
	 */

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody
	ProjectData hello(@RequestParam("input") String input) {
		ProjectData data = new ProjectData();
		data.setProjectDesc("sample project");
		data.setProjectName("Jvalant");
		return data;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value = "/createproject/{userId}")
	public @ResponseBody
	String createProject(@PathVariable String userId,
			@RequestBody ProjectData projectData) {

		return projectService.createProject(userId, projectData);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.GET, value = "/getprojects/{userId}")
	public @ResponseBody
	List<Data> getProjects(@PathVariable String userId) {

		return projectService.getProjects(userId);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.GET, value = "/getprojectdetails/{userId}/{recordId}")
	public @ResponseBody
	Data getProjectDetails(@PathVariable String userId,
			@PathVariable String recordId) {

		return projectService.getProjectDetails(userId, recordId);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteproject/{userId}/{recordId}")
	public @ResponseBody
	boolean deleteProject(@PathVariable String userId,
			@PathVariable String recordId) {

		return projectService.deleteProject(userId, recordId);
	}

	//@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.PUT, value = "/updateproject/{userId}")
	public @ResponseBody
	Data updateProject(@PathVariable String userId,
			@RequestBody ProjectData projectData) {
		projectService.updateProject(userId, projectData);
		return projectService.getProjectDetails(userId,
				projectData.getRecordId());

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getprojectstatus/{userId}/{recordId}/{sdlc}")
	public @ResponseBody
	ProjectStatus getProjectStatus(@PathVariable String userId,
			@PathVariable String recordId,
			@PathVariable String sdlc) {
		
		return projectService.getProjectStatus(userId,recordId,sdlc);

	}
	

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value = "/createprojectActivity/{userId}/{projType}/{projId}")
	public @ResponseBody ProjectActivities createProjectActivity(
			@PathVariable String userId, @PathVariable String projType,
			@PathVariable int projId,
			@RequestBody ProjectActivities projActivity) {
		 
		int activityId= projectActivities.createProjectActivity(userId, projType, projId,
				projActivity);
		ProjectActivities activities = new ProjectActivities();
		activities.setActivityId(activityId);
		return activities;
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.GET, value = "/getprojectActivities/{userId}/{projId}/{projType}")
	public @ResponseBody List<List<ProjectActivity>> getProjectActivites(
			@PathVariable String userId, @PathVariable String projType,
			@PathVariable String projId) {
		return projectActivities.getProjectActivites(userId, projId, projType);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteprojectActivity/{userId}/{activityId}/{projType}")
	public @ResponseBody ProjectActivities deleteProjectActivites(
			@PathVariable String userId, @PathVariable String projType,
			@PathVariable String activityId) {
		ProjectActivities activities = new ProjectActivities();
		boolean status = projectActivities.deleteProjectActivity(userId, activityId, projType);
		activities.setStatus(status);
		return activities;
	}

	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateprojectActivity/{userId}/{projId}/{projType}")
	public @ResponseBody ProjectActivities updateProjectActivites(
			@PathVariable String userId, @PathVariable int projId, @PathVariable String projType,
			@RequestBody ProjectActivities projActivity) {
		
		int activityId = projectActivities.updateProjectActivity(userId, projId, projType,
				projActivity);
		ProjectActivities activities = new ProjectActivities();
		activities.setActivityId(activityId);
		return activities;
	}
	
	
}
