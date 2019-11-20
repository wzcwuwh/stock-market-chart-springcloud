drop database if exists ibm_stock_market_chart;
create database ibm_stock_market_chart;

-- user table
create table if not exists `ibm_stock_market_chart`.`_user`(
 `user_id` bigint(20) not null auto_increment comment 'user_id',
 `username` varchar(128) not null default '' comment 'username',
 `password` varchar(128) not null default '' comment 'password',
 `contact_no` varchar(128) default '' comment 'contact_no',
 `reg_code` varchar(128) default '' comment 'reg_code',
 `user_type` varchar(128) default '' comment 'user_type',
 `_active` tinyint(1) default 0 comment 'active',
 `confirmed_signup` tinyint(1) default 0 comment 'confirmed_signup',
 `reset_pwd` tinyint(1) default 0 comment 'reset_pwd',
 `reset_pwd_date` datetime not null default '1970-01-01 08:00:00' comment 'reset_pwd_date',
 `create_date` datetime not null default '1970-01-01 08:00:00' comment 'create_date',
 `update_date` datetime not null default '1970-01-01 08:00:00' comment 'update_date',
 primary key(`user_id`),
 unique key `key_username_name` (`username`)
)engine=innodb auto_increment=6 default charset=utf8 row_format=compact comment='user table';

-- company table
create table if not exists `ibm_stock_market_chart`.`company`(
 `company_name` varchar(128) not null default '' comment 'company_name',
 `turnover` bigint(20) not null default 0 comment 'turnover',
 `CEO` varchar(128) not null default '' comment 'CEO',
 `board_of_directors` varchar(128) default '' comment 'board of directors',
 `listed_in_stock_exchanges` varchar(128) default '' comment 'listed in stock exchanges',
 `sector` varchar(128) default '' comment 'sector',
 `brief_writeup` varchar(128) default '' comment 'about companies services/products,etc',
 `stock_code` varchar(128) not null default '' comment 'stock code',
 `create_date` datetime not null default '1970-01-01 08:00:00' comment 'create_date',
 `update_date` datetime not null default '1970-01-01 08:00:00' comment 'update_date',
 primary key(`company_name`),
 unique key `key_stock_code` (`stock_code`)
)engine=innodb auto_increment=6 default charset=utf8 row_format=compact comment='company table';

-- stock price detail table
create table if not exists `ibm_stock_market_chart`.`stock_price_detail`(
 `company_code` varchar(128) not null default '' comment 'company_code',
 `stock_exchange` varchar(128) not null default '' comment 'stock_exchange',
 `current_price` bigint(20) not null default 0 comment 'current price',
 `_date` date not null default '1970-01-01' comment '_date',
 `_time` time not null default '08:00:00' comment '_time',
 `create_date` datetime not null default '1970-01-01 08:00:00' comment 'create_date',
 `update_date` datetime not null default '1970-01-01 08:00:00' comment 'update_date',
 unique key `key_stock_price_detail` (`company_code`,`stock_exchange`,`current_price`,`_date`,`_time`)
)engine=innodb auto_increment=6 default charset=utf8 row_format=compact comment='stock price detail table';

-- IPOs planned
create table if not exists `ibm_stock_market_chart`.`IPOs_planned`(
 `IPO_id` bigint(20) not null auto_increment comment 'IPO_id',
 `company_name` varchar(128) not null default '' comment 'company_name',
 `stock_exchange` varchar(128) not null default '' comment 'stock_exchange',
 `price_per_share` bigint(20) not null default 0 comment 'price_per_share',
 `total_no_of_shares` bigint(20) not null default 0 comment 'total_no_of_shares',
 `open_date_time` datetime not null default '1970-01-01 08:00:00' comment 'open_date_time',
 `remarks` varchar(128) default '' comment 'remarks',
 `create_date` datetime not null default '1970-01-01 08:00:00' comment 'create_date',
 `update_date` datetime not null default '1970-01-01 08:00:00' comment 'update_date',
 primary key(`IPO_id`),
 unique key `key_ipo_planned` (`company_name`,`stock_exchange`,`price_per_share`,`total_no_of_shares`,`open_date_time`)
)engine=innodb auto_increment=6 default charset=utf8 row_format=compact comment='IPOs planned table';

-- sector
create table if not exists `ibm_stock_market_chart`.`sector`(
 `sector_id` bigint(20) not null auto_increment comment 'IPO_id',
 `sector_name` varchar(128) not null default '' comment 'sector_name',
 `brief` varchar(128) not null default '' comment 'stock_exchange',
 `create_date` datetime not null default '1970-01-01 08:00:00' comment 'create_date',
 `update_date` datetime not null default '1970-01-01 08:00:00' comment 'update_date',
 primary key(`sector_id`),
 unique key `key_sector_name` (`sector_name`)
)engine=innodb auto_increment=6 default charset=utf8 row_format=compact comment='sector table';

-- stock exchange
create table if not exists `ibm_stock_market_chart`.`sector`(
 `stock_exchange_id` bigint(20) not null auto_increment comment 'stock_exchange_id',
 `stock_exchange_name` varchar(128) not null default '' comment 'stock_exchange_name',
 `brief` varchar(128) not null default '' comment 'brief',
 `contact_addr` varchar(128) not null default '' comment 'contact_addr',
 `remarks` varchar(128) default '' comment 'remarks',
 `create_date` datetime not null default '1970-01-01 08:00:00' comment 'create_date',
 `update_date` datetime not null default '1970-01-01 08:00:00' comment 'update_date',
 primary key(`stock_exchange_id`),
 unique key `key_sector_name` (`stock_exchange_name`)
)engine=innodb auto_increment=6 default charset=utf8 row_format=compact comment='stock exchange table';