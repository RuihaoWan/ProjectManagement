drop DATABASE proj_data;
CREATE DATABASE proj_data character set utf8;

use proj_data;

-- USER TABLE
DROP TABLE IF EXISTS `proj_user`;
CREATE TABLE `proj_user` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `username` varchar(128) NOT NULL DEFAULT '',
                           `token` varchar(256) NOT NULL DEFAULT '',
                           `position` varchar(256) NOT NULL DEFAULT '',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


-- TASK TABLE
DROP TABLE IF EXISTS `proj_task`;
CREATE TABLE `proj_task` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `stage_id` bigint(20) NOT NULL DEFAULT '0',
                           `project_id` bigint(20) NOT NULL DEFAULT '0',
                           `name` varchar(256) NOT NULL DEFAULT '',
                           `desc` varchar(256) NOT NULL DEFAULT '',
                           `start_time` datetime NOT NULL DEFAULT  '1970-01-01 00:00:00',
                           `end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                           `url` varchar(256) NOT NULL DEFAULT '',
                           `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                           `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


-- STAGE TABLE
DROP TABLE IF EXISTS `proj_stage`;
CREATE TABLE `proj_stage` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `project_id` bigint(20) NOT NULL DEFAULT '0',
                           `stage_name` varchar(48) NOT NULL DEFAULT '',
                           `unit_status` tinyint(4) NOT NULL DEFAULT '0',
                           `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                           `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


-- PROJECT TABEL
DROP TABLE IF EXISTS `proj_project`;
CREATE TABLE `proj_project` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `creator_id` bigint(20) NOT NULL DEFAULT '0',
                               `project_name` varchar(256) NOT NULL DEFAULT '',
                               `project_status` tinyint(4) NOT NULL DEFAULT '0',
                               `start_date` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                               `end_date` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                               `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                               `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


-- TYPE & TASK RELATIONSHIP TABLE
DROP TABLE IF EXISTS `proj_task_type`;
CREATE TABLE `proj_task_type` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `task_id` bigint(20) NOT NULL DEFAULT '0',
                                 `type` varchar(256) NOT NULL DEFAULT '0',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY (`task_id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


-- USER & TASK RELATIONSHIP TABLE
DROP TABLE IF EXISTS `task_users`;
CREATE TABLE `task_user` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `task_id` bigint(20) NOT NULL DEFAULT '0',
                                   `user_id` bigint(20) NOT NULL DEFAULT '0',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY(`task_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
