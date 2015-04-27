package app.utilities;

public final class DatabaseQueries {
	
	public static String GET_TENANT_ID = "select tenantId from tenant where userId = ? and modelTable = ?";
	
	public static String GET_FIELDS_FOR_PROJECT = "select fieldId,field_name from tenant_field  where tenantId = ?";
	
	public static String CREATE_PROJECT = "call stpInsertProjectDetails(?,?,?,?,?,?,?)";
	
	/*CALL stpInsertUserFieldValues(1,'Project', 'test1', 'test2', 'test3', 'test4', 'test5');*/


}
