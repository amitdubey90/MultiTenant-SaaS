DROP TABLE IF EXISTS `tenant_data`;
DROP TABLE IF EXISTS `tenant_field`;
DROP TABLE IF EXISTS `tenant_model_info`;
DROP TABLE IF EXISTS `tenant`;
DROP TABLE IF EXISTS `user_info`;
DROP TABLE IF EXISTS `sdlcmodel`;

CREATE TABLE `sdlcmodel` (
  `modelId` int(11) NOT NULL AUTO_INCREMENT,
  `modelType` varchar(45) NOT NULL,
  PRIMARY KEY (`modelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sdlcmodel` VALUES (1,'waterfall'),(2,'kanban'),(3,'scrum');

CREATE TABLE `sdlc_tables` (
  `sdlc_id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL,
  `table_name` varchar(100) NOT NULL,
  KEY `fk_sdlc_id_ref_idx` (`sdlc_id`),
  CONSTRAINT `fk_sdlc_id_ref` FOREIGN KEY (`sdlc_id`) REFERENCES `sdlcmodel` (`modelId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sdlc_tables` VALUES (1,1,'project'),(1,2,'task'),(1,3,'resource')
,(2,1,'project'), (2,4,'card'),(2,5,'group')
,(3,1,'project'),(3,6,'sprint'), (3,7,'team'), (3,8,'story');

CREATE TABLE `sdlc_fields` (
  `table_id` int(11) NOT NULL,
  `field_name` varchar(45) NOT NULL,
  `field_type` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sdlc_fields` VALUES 
(1,'project_id','int'),(1,'project_name','varchar')
,(1,'project_desc','varchar'),(1,'project_owner','varchar')
,(1,'start_date','date'),(1,'end_date','date')

,(2,'project_id','int')
,(2,'task_name','varchar'),(2,'task_desc','varchar')
,(2,'start_date','date'),(2,'end_date','date'),(2,'resource_id','lookup')
,(2,'resource_name', 'varchar'), (2, 'status', 'varchar')

,(3,'resource_id','int'),(3,'resource_name','varchar'),(3,'project_id','int')

,(4,'project_id','int'), (4,'card_name','varchar')
,(4,'status_lane','varchar'), (4,'start_date','date')
,(4,'end_date','date'), (4,'group_id','int')
,(4,'status','varchar')

,(5,'group_id','int'),(5,'group_name','varchar'),(5,'project_id','int')

,(6,'sprint_id','int'),(6,'sprint_name','varchar'),(6,'project_id','int')

,(7,'team_id','int'),(7,'team_name','varchar'),(7, 'team_size','int')
,(7,'project_id','int')

,(8,'story_points','int'),(8,'story_name','varchar'),(8,'project_id','int')
,(8,'story_days','int'),(8,'sprint_id','int'),(8,'sprint_name','varchar')
,(8,'hours_remaining','int'),(8,'team_id','int'),(8,'status','varchar');


CREATE TABLE `user_info` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `Email` varchar(45) NOT NULL,
  `phone` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`userId`,`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tenant` (
  `tenantId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `modelId` int(11) DEFAULT NULL,
  `modelTable` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tenantId`),
  KEY `userId_idx` (`userId`),
  KEY `modelId_idx` (`modelId`),
  CONSTRAINT `model_Id` FOREIGN KEY (`modelId`) REFERENCES `sdlcmodel` (`modelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`userId`) REFERENCES `user_info` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tenant_field` (
  `tenantId` int(11) NOT NULL AUTO_INCREMENT,
  `fieldId` int(11) NOT NULL,
  `field_name` varchar(45) DEFAULT NULL,
  `field_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tenantId`,`fieldId`),
  CONSTRAINT `tenantId` FOREIGN KEY (`tenantId`) REFERENCES `tenant` (`tenantId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tenant_model_info` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `modelId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`modelId`),
  KEY `modelId_idx` (`modelId`),
  CONSTRAINT `modelId` FOREIGN KEY (`modelId`) REFERENCES `sdlcmodel` (`modelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user_info` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tenant_data` (
  `recordId` int(11) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `fieldId` int(11) NOT NULL,
  `value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`recordId`,`tenantId`,`fieldId`),
  KEY `tenant_id_idx` (`tenantId`),
  KEY `field_id_idx` (`fieldId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

