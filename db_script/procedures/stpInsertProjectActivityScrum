CREATE DEFINER=`root`@`localhost` PROCEDURE `stpInsertProjectActivityScrum`(
	IN user_id INT
    , IN model_table VARCHAR(45)
    , IN projIdInput INT
    , IN storyNameInput VARCHAR(45)
    , IN storyPointsInput INT
    , IN storyDays INT
    , IN sprintId INT
    , IN sprintName VARCHAR(45)
    , IN hoursRemaining INT
    , IN teamId INT
    , IN statusInput VARCHAR(45)
 
)
BEGIN
	DECLARE vDONE INT DEFAULT 0;
    DECLARE id INT;
    DECLARE field_name VARCHAR(45);
    DECLARE tenant_id INT;
    DECLARE record_id INT;
    
	DECLARE field_data CURSOR FOR 
		SELECT 
			tf.fieldId
			, tf.field_name
            , t.tenantId
		FROM 
			tenant_field tf
			, tenant t
			, user_info ui
		WHERE
			ui.userId = t.userId
			AND t.tenantId = tf.tenantId
			AND ui.userId = user_id
			AND t.modelTable = model_table;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET vDONE = 1;
	OPEN field_data;
    SET vDONE = 0;
    SELECT IFNULL(MAX(recordId),0) FROM tenant_data INTO record_id ;
    SET record_id = record_id + 1;
    
    REPEAT
    FETCH field_data INTO id, field_name, tenant_id;
		IF vDONE = 0 THEN
				CASE 
                WHEN field_name = 'project_id' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, projIdInput);
					WHEN field_name = 'story_name' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, storyNameInput);
					WHEN field_name = 'story_points' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, storyPointsInput);
					WHEN field_name = 'story_days' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, storyDays);
					WHEN field_name = 'sprint_id' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, sprintId);
					WHEN field_name = 'sprint_name' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, sprintName);
					WHEN field_name = 'hours_remaining' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, hoursRemaining);
					WHEN field_name = 'team_id' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, teamId);
					WHEN field_name = 'status' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, statusInput);
				END CASE;
		END IF;
	UNTIL vDONE END REPEAT;
    SELECT record_id;
	CLOSE field_data;
END