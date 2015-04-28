package app.utilities;

public final class DatabaseQueries {
	
	public static String GET_TENANT_ID = "select tenantId from tenant where userId = ? and modelTable = ?";
	
	public static String GET_FIELDS_FOR_PROJECT = "select fieldId,field_name from tenant_field  where tenantId = ?";
	
	public static String GET_PROJECTS = "select * from tenant_data where tenantId = ?";
	
	public static String CREATE_PROJECT = "call stpInsertProjectDetails(?,?,?,?,?,?,?)";
	
	public static String GET_DATA_BY_RECORD_ID = "select * from tenant_data where recordId = ?";
	
	public static String DELETE_DATA_BY_RECORD_ID = "delete from tenant_data where recordId=?";
	
	public static String UPDATE_PROJECT = "CALL stpUpdateProjectDetails(?,?,?,?,?,?,?,?)";
	
	/*CALL stpInsertUserFieldValues(1,'Project', 'test1', 'test2', 'test3', 'test4', 'test5');*/


}
