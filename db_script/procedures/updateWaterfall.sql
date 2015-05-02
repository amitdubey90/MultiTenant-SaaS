CREATE DEFINER=`root`@`localhost` PROCEDURE `stpUpdateProjectActivity`(
	IN user_id INT
    , IN model_table VARCHAR(45)
    , IN projectId INT
    , IN rId INT
    , IN tasNameInput VARCHAR(45)
    , IN taskDescInput VARCHAR(45)
    , IN startdate VARCHAR(45)
    , IN enddate VARCHAR(45)
    , IN resourceIdInput INT
    , IN resourceNameInput VARCHAR(45)
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
  /*  SELECT IFNULL(MAX(recordId),0) FROM tenant_data INTO record_id ;
    SET record_id = record_id + 1;*/
    
    REPEAT
    FETCH field_data INTO id, field_name, tenant_id;
		IF vDONE = 0 THEN
				CASE 
					WHEN field_name = 'project_id' THEN
						UPDATE tenant_data SET value = projectId WHERE recordId = rId  AND fieldId = 1;
					WHEN field_name = 'task_name' THEN
						UPDATE tenant_data SET value = tasNameInput WHERE recordId = rId AND fieldId = 2;
					WHEN field_name = 'task_desc' THEN
						UPDATE tenant_data SET value = taskDescInput WHERE recordId = rId AND fieldId = 3;
					WHEN field_name = 'start_date' THEN
						UPDATE tenant_data SET value = startdate WHERE recordId = rId AND fieldId = 4;
					WHEN field_name = 'end_date' THEN
						UPDATE tenant_data SET value = enddate WHERE recordId = rId AND fieldId = 5;
					WHEN field_name = 'resource_id' THEN
						UPDATE tenant_data SET value = resourceIdInput WHERE recordId = rId AND fieldId = 6;
					WHEN field_name = 'resource_name' THEN
						UPDATE tenant_data SET value = resourceNameInput WHERE recordId = rId AND fieldId = 7;
					WHEN field_name = 'status' THEN
						UPDATE tenant_data SET value = statusInput WHERE recordId = rId AND fieldId = 8;
				END CASE;
		END IF;
	UNTIL vDONE END REPEAT;
    
	CLOSE field_data;
END