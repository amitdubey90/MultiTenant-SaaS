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


CREATE TABLE `user_info` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `First Name` varchar(45) NOT NULL,
  `Last Name` varchar(45) NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Email` varchar(45) NOT NULL,
  `phone` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`userId`,`First Name`,`Last Name`,`Email`)
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

