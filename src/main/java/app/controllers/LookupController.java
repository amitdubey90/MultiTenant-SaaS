package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.data.ColumnData;
import app.services.LookupServiceIfc;

public class LookupController {

	@Autowired
	LookupServiceIfc service;

	@RequestMapping(method = RequestMethod.POST, value = "/createLookup/{lookUpType}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ColumnData createLookupValue(
			@RequestParam String userId, @PathVariable String lookUpType,
			@RequestParam String projectId, @RequestBody List<ColumnData> values) {
		String recordId = service.createLookupValue(userId, lookUpType,
				projectId, values);
		ColumnData result = new ColumnData();
		result.setColumnName("recordId");
		result.setValue(recordId);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getLookup/{lookUpType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<List<ColumnData>> getLookupValues(
			@RequestParam String userId, @PathVariable String lookUpType,
			@RequestParam String projectId) {

		return service.getLookupValue(userId, lookUpType, projectId);

	}
}
