-- 如果数据库不存在则创建
CREATE DATABASE IF NOT EXISTS `bookSharingSystem`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `bookSharingSystem`;

-- 创建likes表（如果不存在）
CREATE TABLE IF NOT EXISTS `likes`
(
    `id`        INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID，主键自增',
    `createdAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updatedAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deletedAt` TIMESTAMP    NULL     DEFAULT NULL COMMENT '删除时间（软删除）',
    `userId`    INT NOT NULL COMMENT '点赞用户ID',
    `articleId` INT NOT NULL COMMENT '文章ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='点赞表';