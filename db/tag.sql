-- 如果数据库不存在则创建
CREATE DATABASE IF NOT EXISTS `bookSharingSystem`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `bookSharingSystem`;

-- 创建tag表（如果不存在）
CREATE TABLE IF NOT EXISTS `tag`
(
    `id`   INT         NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID，主键自增',
    `name` varchar(20) NOT NULL  COMMENT '标签名'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='分享书籍表';