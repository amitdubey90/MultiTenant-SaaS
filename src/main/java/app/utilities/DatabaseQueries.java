package app.utilities;

public final class DatabaseQueries {

	public static String GET_TENANT_ID = "select tenantId from tenant where userId = ? and modelTable = ?";

	public static String GET_FIELDS_FOR_PROJECT = "select fieldId,field_name from tenant_field  where tenantId = ?";

	public static String GET_PROJECTS = "select d.recordId, f.field_name, d.value FROM tenant_data d, tenant_field f where f.tenantId = ? and d.tenantId = ? and d.fieldId = f.fieldId";

	public static String CREATE_PROJECT = "call stpInsertProjectDetails(?,?,?,?,?,?,?)";

	public static String GET_DATA_BY_RECORD_ID = "select d.recordId, f.field_name, d.value FROM tenant_data d, tenant_field f where f.tenantId = ? and d.tenantId = ? and d.fieldId = f.fieldId and d.recordId =?";

	public static String DELETE_DATA_BY_RECORD_ID = "delete from tenant_data where recordId=?";

	public static String UPDATE_PROJECT = "CALL stpUpdateProjectDetails(?,?,?,?,?,?,?,?)";

	public static String SIGNUP_USER = "insert into user_info (firstname, lastname, address,email,phone,password) values (?,?,?,?,?,?)";

	public static String GET_USER_ID = "SELECT userId FROM user_info where Email = ?";

	public static String GET_USER_INFO_BY_MAIL = "Select Email,userId from user_info where Email = ? and password = ?";
	
	public static String GET_TASKS_FOR_PROJECT = "SELECT d.recordId,f.field_name,d.value FROM tenant_data d, tenant_field f WHERE d.fieldId = f.fieldId and f.tenantId = ? and recordId IN (SELECT recordId FROM tenant_data WHERE value = ?)";
	
	public static String GET_MODEL_TYPE_BY_USER_ID = "select modelType from sdlcmodel where modelId in (select distinct(modelId) from tenant where userId = ?)";

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
	public static final String GET_TENANT_TABLES = "select st.table_id, st.table_name, "
			+ "sf.field_name, sf.field_type, m.modelid from "
			+ "sdlc_tables st, sdlc_fields sf, sdlcmodel m where st.sdlc_id = m.modelid "
			+ "and st.table_id = sf.table_id and m.modeltype = ?";

	public static final String INSERT_TENANT_TABLES = "insert into tenant (tenantid, userid, modelid, modeltable) "
			+ "values (?, ?, ?, ?)";

	public static final String INSERT_TENANT_FIELDS = "insert into tenant_field (tenantid, field_name, field_type) "
			+ "values (?, ?, ?)";

	public static final String GET_MAX_TENANT_ID = "select max(tenantid) from tenant";
	// tenant queries - end

	// lookup queries start
	public static final String GET_TABLES_FOR_SDLC = "select t.tenantid, tf.fieldid, tf.field_name, tf.field_type from "
			+ "tenant t, tenant_field tf where  userid = ? and modeltable = ? and t.tenantid = tf.tenantid";
	
	public static final String GET_MAX_RECORDID = "select max(recordid) from tenant_data";
	
	public static final String INSERT_TENANT_DATA = "insert into tenant_data (recordid, tenantid, fieldid, value) "
			+ "values (?, ?, ?, ?)";
	
	public static final String GET_LOOKUP_VALUES = "select tf.field_name, td.value, td.recordid from tenant_data td, tenant_field tf "
			+ "where td.fieldid = tf.fieldid and recordid in "
			+ "( select td.recordid from tenant t , tenant_field tf , tenant_data td "
			+ "where t.tenantid = tf.tenantid and t.userid = ? and td.fieldid = tf.fieldid "
			+ "and lower(t.modeltable) = ? and tf.field_name = 'project_id' "
			+ "and td.value = ?) order by td.recordid";
	// LOOKUP QUERIES END

}
