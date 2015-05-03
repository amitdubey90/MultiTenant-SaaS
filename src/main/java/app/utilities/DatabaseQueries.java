package app.utilities;

public final class DatabaseQueries {

	public static String GET_TENANT_ID = "select tenantId from tenant where userId = ? and modelTable = ?";

	public static String GET_FIELDS_FOR_PROJECT = "select fieldId,field_name from tenant_field  where tenantId = ?";

	public static String GET_PROJECTS = "select d.recordId, f.field_name, d.value FROM tenant_data d, tenant_field f where f.tenantId = ? and d.tenantId = ? and d.fieldId = f.fieldId";

	public static String CREATE_PROJECT = "call stpInsertProjectDetails(?,?,?,?,?,?,?)";

	public static String GET_DATA_BY_RECORD_ID = "select d.recordId, f.field_name, d.value FROM tenant_data d, tenant_field f where f.tenantId = ? and d.tenantId = ? and d.fieldId = f.fieldId and d.recordId =?";

	public static String DELETE_DATA_BY_RECORD_ID = "delete from tenant_data where recordId=?";

	public static String UPDATE_PROJECT = "CALL stpUpdateProjectDetails(?,?,?,?,?,?,?,?)";

	public static String SIGNUP_USER = "insert into user_info (FirstName, LastName, Address,Email,phone,password) values (?,?,?,?,?,?)";

	public static String GET_USER_ID = "SELECT userId FROM user_info where Email = ?";

	public static String GET_USER_INFO_BY_MAIL = "Select Email,password from user_info where Email = ?";
	
	public static String GET_TASKS_FOR_PROJECT = "SELECT d.recordId,f.field_name,d.value FROM tenant_data d, tenant_field f WHERE d.fieldId = f.fieldId and f.tenantId = ? and recordId IN (SELECT recordId FROM tenant_data WHERE value = ?)";

	/*
	 * CALL stpInsertUserFieldValues(1,'Project', 'test1', 'test2', 'test3',
	 * 'test4', 'test5');
	 */
	// Queries for Project Activities - Start

	/**
	 * fields coming in request - user id, 'Task',projId, task name, task desc,
	 * start date, end date, resource id, resource name status
	 **/
	public static String CREATE_PROJECT_ACTIVITY_WATERFALL = "call stpInsertProjectActivity(?,?,?,?,?,?,?,?,?,?)";

	/**
	 * fields coming in request - user id, 'Story',projId, story name, story
	 * points, story days, sprint id, sprint name,hours remaining,team id status
	 **/
	public static String CREATE_PROJECT_ACTIVITY_SCRUM = "call stpInsertProjectActivityScrum(?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * fields coming in request - user id, 'Card', proj Id, card name, status
	 * lane, start date, end date, group id status
	 **/
	public static String CREATE_PROJECT_ACTIVITY_KANBAN = "call stpInsertProjectActivityKanban(?,?,?,?,?,?,?,?,?)";

	public static String GET_INFO_FROM_TENANT_ID = "select * from tenant_data where recordId in (select recordId from tenant_data where tenantId= ? and value=? and fieldId=?)";

	public static String DELETE_ACTIVITY_RECORD = "delete from tenant_data where recordId=?";

	/**
	 * fields coming in request - user id, 'Task',projId, task name, task desc,
	 * start date, end date, resource id, resource name status
	 **/
	public static String UPDATE_PROJECT_ACTIVITY_WATERFALL = "call stpUpdateProjectActivity(?,?,?,?,?,?,?,?,?,?,?)";
	/**
	 * fields coming in request - user id, 'Story',projId, story name, story
	 * points, story days, sprint id, sprint name,hours remaining,team id status
	 **/
	public static String UPDATE_PROJECT_ACTIVITY_SCRUM = "call stpUpdateProjectActivityScrum(?,?,?,?,?,?,?,?,?,?,?,?)";
	/**
	 * fields coming in request - user id, 'Card', proj Id, card name, status
	 * lane, start date, end date, group id status
	 **/
	public static String UPDATE_PROJECT_ACTIVITY_KANBAN = "call stpUpdateProjectActivityKanban(?,?,?,?,?,?,?,?,?,?)";

	public static final String GET_FIELD_NAMES_FOR_TENANT = "select * from tenant_field where tenantId=?";

	// Queries for Project Activities - End

	// TENANT QUERIES - START
	public static final String GET_TENANT_TABLES = "SELECT ST.TABLE_ID, ST.TABLE_NAME, "
			+ "SF.FIELD_NAME, SF.FIELD_TYPE, M.MODELID FROM "
			+ "SDLC_TABLES ST, SDLC_FIELDS SF, SDLCMODEL M WHERE ST.SDLC_ID = M.MODELID "
			+ "AND ST.TABLE_ID = SF.TABLE_ID AND M.MODELTYPE = ?";

	public static final String INSERT_TENANT_TABLES = "INSERT INTO TENANT (TENANTID, USERID, MODELID, MODELTABLE) "
			+ "VALUES (?, ?, ?, ?)";

	public static final String INSERT_TENANT_FIELDS = "INSERT INTO TENANT_FIELD (TENANTID, FIELD_NAME, FIELD_TYPE) "
			+ "VALUES (?, ?, ?)";

	public static final String GET_MAX_TENANT_ID = "SELECT MAX(TENANTID) FROM TENANT";
	// TENANT QUERIES - END

	// LOOKUP QUERIES START
	public static final String GET_TABLES_FOR_SDLC = "SELECT T.TENANTID, TF.FIELDID, TF.FIELD_NAME, TF.FIELD_TYPE FROM "
			+ "TENANT T, TENANT_FIELD TF WHERE  USERID = ? AND MODELTABLE = ? AND T.TENANTID = TF.TENANTID";
	
	public static final String GET_MAX_RECORDID = "SELECT MAX(RECORDID) FROM TENANT_DATA";
	
	public static final String INSERT_TENANT_DATA = "INSERT INTO TENANT_DATA (RECORDID, TENANTID, FIELDID, VALUE) "
			+ "VALUES (?, ?, ?, ?)";
	
	public static final String GET_LOOKUP_VALUES = "SELECT tf.field_name, td.value, td.recordId FROM tenant_data td, tenant_field tf "
			+ "WHERE td.fieldId = tf.fieldId AND recordId IN "
			+ "( SELECT td.recordId FROM tenant t , tenant_field tf , tenant_data td "
			+ "WHERE t.tenantId = tf.tenantId AND t.userId = ? AND td.fieldId = tf.fieldId "
			+ "AND lower(t.modelTable) = ? AND tf.field_name = 'project_id' "
			+ "AND td.value = ?) order by td.recordId";
	// LOOKUP QUERIES END

}
