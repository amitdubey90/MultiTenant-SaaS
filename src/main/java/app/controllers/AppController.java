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

import app.data.ProjectData;
import app.services.ProjectServiceIfc;

@RestController
public class AppController {

	@Autowired
	ProjectServiceIfc projectService;

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
	boolean createProject(@PathVariable String userId,
			@RequestBody ProjectData projectData) {

		return projectService.createProject(userId, projectData);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.GET, value = "/getprojects/{userId}")
	public @ResponseBody
	List<ProjectData> getProjects(@PathVariable String userId) {

		return projectService.getProjects(userId);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(method = RequestMethod.GET, value = "/getprojectdetails/{userId}/{recordId}")
	public @ResponseBody
	ProjectData getProjectDetails(@PathVariable String userId,
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
	ProjectData updateProject(@PathVariable String userId,
			@RequestBody ProjectData projectData) {
		projectService.updateProject(userId, projectData);
		return projectService.getProjectDetails(userId,
				projectData.getRecordId());

	}
}
