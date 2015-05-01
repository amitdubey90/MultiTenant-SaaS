DROP procedure IF exists stpInsertProjectDetails;
DELIMITER //
 
CREATE PROCEDURE `stpInsertProjectDetails` (
	IN user_id INT
    , IN model_table VARCHAR(45)
    , IN project_name VARCHAR(45)
    , IN project_desc VARCHAR(45)
    , IN owner_name VARCHAR(45)
    , IN startdate VARCHAR(45)
    , IN enddate VARCHAR(45)
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
					WHEN field_name = 'project_name' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, project_name);
					WHEN field_name = 'project_desc' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, project_desc);
					WHEN field_name = 'project_owner' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, owner_name);
					WHEN field_name = 'start_date' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, startdate);
					WHEN field_name = 'end_date' THEN
						INSERT INTO tenant_data (recordId, tenantId, fieldId, value) VALUES
							(record_id, tenant_id, id, enddate);
				END CASE;
		END IF;
	UNTIL vDONE END REPEAT;
    SELECT record_id;
	CLOSE field_data;
END //
DELIMITER ;