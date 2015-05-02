package app.data;

public class ColumnMetaData {

	private String columnName;
	private String columnType;
	private int tenantId;

	
	public ColumnMetaData(String columnName, String columnType) {
		this.columnName = columnName;
		this.columnType = columnType;
	}
	
	public ColumnMetaData(String columnName, String columnType, int tenantId) {
		this.columnName = columnName;
		this.columnType = columnType;
		this.tenantId = tenantId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

}
