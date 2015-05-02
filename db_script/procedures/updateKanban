CREATE DEFINER=`root`@`localhost` PROCEDURE `stpUpdateProjectActivityKanban`(
	IN user_id INT
    , IN model_table VARCHAR(45)
    , IN projectId INT
    , IN rId INT
    , IN cardNameInput VARCHAR(45)
    , IN cardStatus VARCHAR(45)
    , IN startDate VARCHAR(45)
    , IN endDate VARCHAR(45)
    , IN groupId INT
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
  
    
    REPEAT
    FETCH field_data INTO id, field_name, tenant_id;
		IF vDONE = 0 THEN
				CASE 
					WHEN field_name = 'project_id' THEN
						UPDATE tenant_data SET value = projectId WHERE (recordId = rId ) AND (fieldId = 1);
					WHEN field_name = 'card_name' THEN
						UPDATE tenant_data SET value = cardNameInput WHERE (recordId = rId) AND (fieldId = 2);
					WHEN field_name = 'status_lane' THEN
						UPDATE tenant_data SET value = cardStatus WHERE (recordId = rId) AND (fieldId = 3);
					WHEN field_name = 'start_date' THEN
						UPDATE tenant_data SET value = startDate WHERE (recordId = rId) AND (fieldId = 4);
					WHEN field_name = 'end_date' THEN
						UPDATE tenant_data SET value = endDate WHERE (recordId = rId) AND (fieldId = 5);
					WHEN field_name = 'group_id' THEN
						UPDATE tenant_data SET value = groupId WHERE (recordId = rId) AND (fieldId = 6);
					WHEN field_name = 'status' THEN
						UPDATE tenant_data SET value = statusInput WHERE (recordId = rId) AND (fieldId = 7);
				END CASE;
		END IF;
	UNTIL vDONE END REPEAT;
    
	CLOSE field_data;
END