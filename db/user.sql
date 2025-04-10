-- 如果数据库不存在则创建
CREATE DATABASE IF NOT EXISTS `bookSharingSystem`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `bookSharingSystem`;

-- 创建user表（如果不存在）
CREATE TABLE IF NOT EXISTS `user`
(
    `id`        INT          NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键自增',
    `createdAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updatedAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deletedAt` TIMESTAMP    NULL     DEFAULT NULL COMMENT '删除时间（软删除）',
    `name`      VARCHAR(50)  NOT NULL COMMENT '用户名',
    `email`     VARCHAR(100) NOT NULL COMMENT '用户邮箱',
    `password`  VARCHAR(255) NOT NULL COMMENT '密码（建议存储加密后的值）',
    `role`      INT          NOT NULL COMMENT '0为用户，1为管理员',
    `sex`       TINYINT(1)            DEFAULT NULL COMMENT '性别（0:女，1:男，2:未知）',
    `signature` VARCHAR(50)           DEFAULT NULL COMMENT '个性签名',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_email` (`email`) COMMENT '邮箱唯一索引',
    KEY `idx_deletedAt` (`deletedAt`) COMMENT '软删除查询索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表';