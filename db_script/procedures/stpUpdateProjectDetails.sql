DROP procedure IF exists stpUpdateProjectDetails;
DELIMITER //
 
CREATE PROCEDURE `stpUpdateProjectDetails` (
	IN user_id INT
    , IN model_table VARCHAR(45)
    , IN project_name VARCHAR(45)
    , IN project_desc VARCHAR(45)
    , IN owner_name VARCHAR(45)
    , IN startdate VARCHAR(45)
    , IN enddate VARCHAR(45)
	, IN record_Id INT
)

BEGIN
	DECLARE vDONE INT DEFAULT 0;
    DECLARE id INT;
    DECLARE field_name VARCHAR(45);
    DECLARE tenant_id INT;
    
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
					WHEN field_name = 'project_name' THEN
						UPDATE tenant_data SET value = project_name where recordId = record_Id and fieldId =1;
					WHEN field_name = 'project_desc' THEN
						UPDATE tenant_data SET value = project_desc where recordId = record_Id and fieldId =2;
					WHEN field_name = 'project_owner' THEN
						UPDATE tenant_data SET value = owner_name where recordId = record_Id and fieldId =3;
					WHEN field_name = 'start_date' THEN
						UPDATE tenant_data SET value = startdate where recordId = record_Id and fieldId =4;
					WHEN field_name = 'end_date' THEN
						UPDATE tenant_data SET value = enddate where recordId = record_Id and fieldId =5;
				END CASE;
		END IF;
	UNTIL vDONE END REPEAT;
	CLOSE field_data;
END //
DELIMITER ;