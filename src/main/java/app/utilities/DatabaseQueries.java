package app.utilities;

public final class DatabaseQueries {
	
	public static String GET_TENANT_ID = "select tenantId from tenant where userId = ? and modelTable = ?";
	
	public static String GET_FIELDS_FOR_PROJECT = "select fieldId,field_name from tenant_field  where tenantId = ?";
	
	public static String GET_PROJECTS = "select * from tenant_data where tenantId = ?";
	
	public static String CREATE_PROJECT = "call stpInsertProjectDetails(?,?,?,?,?,?,?)";
	
	public static String GET_DATA_BY_RECORD_ID = "select * from tenant_data where recordId = ?";
	
	public static String DELETE_DATA_BY_RECORD_ID = "delete from tenant_data where recordId=?";
	
	public static String UPDATE_PROJECT = "CALL stpUpdateProjectDetails(?,?,?,?,?,?,?,?)";
	
	public static String SIGNUP_USER = "insert into user_info (FirstName, LastName, Address,Email,phone,password) values (?,?,?,?,?,?)";
	
	public static String GET_USER_ID = "SELECT userId FROM user_info where Email = ?";
	
	public static String GET_USER_INFO_BY_MAIL = "Select Email,password from user_info where Email = ?";
	
	/*CALL stpInsertUserFieldValues(1,'Project', 'test1', 'test2', 'test3', 'test4', 'test5');*/
	// Queries for Project Activities - Start
	
		/**fields coming in request -  user id, 'Task',projId, task name, task desc, start date, end date, resource id, resource name status**/
		public static String CREATE_PROJECT_ACTIVITY_WATERFALL = "call stpInsertProjectActivity(?,?,?,?,?,?,?,?,?,?)";
		
		/**fields coming in request -  user id, 'Story',projId, story name, story points, story days, sprint id, sprint name,hours remaining,team id status**/
		public static String CREATE_PROJECT_ACTIVITY_SCRUM = "call stpInsertProjectActivityScrum(?,?,?,?,?,?,?,?,?,?,?)";
		
		/** fields coming in request - user id, 'Card', proj Id, card name, status lane, start date, end date, group id status**/
		public static String CREATE_PROJECT_ACTIVITY_KANBAN = "call stpInsertProjectActivityKanban(?,?,?,?,?,?,?,?,?)";
		
		public static String GET_INFO_FROM_TENANT_ID = "select * from tenant_data where recordId in (select recordId from tenant_data where tenantId= ? and value=? and fieldId=?)";

		public static String DELETE_ACTIVITY_RECORD = "delete from tenant_data where recordId=?";

	/**fields coming in request -  user id, 'Task',projId, task name, task desc, start date, end date, resource id, resource name status**/
		public static String UPDATE_PROJECT_ACTIVITY_WATERFALL = "call stpUpdateProjectActivity(?,?,?,?,?,?,?,?,?,?,?)";
	/**fields coming in request -  user id, 'Story',projId, story name, story points, story days, sprint id, sprint name,hours remaining,team id status**/
		public static String UPDATE_PROJECT_ACTIVITY_SCRUM = "call stpUpdateProjectActivityScrum(?,?,?,?,?,?,?,?,?,?,?,?)";
	/** fields coming in request - user id, 'Card', proj Id, card name, status lane, start date, end date, group id status**/	
		public static String UPDATE_PROJECT_ACTIVITY_KANBAN = "call stpUpdateProjectActivityKanban(?,?,?,?,?,?,?,?,?,?)";
		
		public static final String GET_FIELD_NAMES_FOR_TENANT = "select * from tenant_field where tenantId=?";
		
		// Queries for Project Activities - End

}
