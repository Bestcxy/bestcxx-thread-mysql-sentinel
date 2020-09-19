
Create Table
CREATE TABLE `index_stu` (  `id` bigint(20) NOT NULL auto_increment,  `type` varchar(32) NOT NULL,  `status` int(1) NOT NULL,  `create_time` datetime NOT NULL,  `update_time` datetime NOT NULL,  PRIMARY KEY  (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8