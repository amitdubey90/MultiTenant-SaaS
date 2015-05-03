package app.data;

public class ColumnData {

	private String columnName;
	private String value;

	public ColumnData() {
		super();
		//NOOP
	}

	public ColumnData(String columnName, String value) {
		this.columnName = columnName;
		this.value = value;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
