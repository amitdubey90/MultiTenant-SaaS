package app.data;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private List<String> fieldList;
	private List<String> valueList;
	
	private String recordId;
	
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Data(){
		fieldList = new ArrayList<String>();
		valueList = new ArrayList<String>();
		recordId ="";
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	
}
