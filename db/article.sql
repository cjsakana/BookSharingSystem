-- 如果数据库不存在则创建
CREATE DATABASE IF NOT EXISTS `bookSharingSystem`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `bookSharingSystem`;

-- 创建article表（如果不存在）
CREATE TABLE IF NOT EXISTS `article`
(
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '书籍ID，主键自增',
    `createdAt`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updatedAt`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deletedAt`   TIMESTAMP    NULL     DEFAULT NULL COMMENT '删除时间（软删除）',
    `bookName`    VARCHAR(100) NOT NULL COMMENT '书籍名称',
    `isbn`        VARCHAR(20)           DEFAULT NULL COMMENT 'ISBN号',
    `cover`       VARCHAR(255)          DEFAULT NULL COMMENT '书籍封面URL',
    `title`       VARCHAR(100) NOT NULL COMMENT '文章标题',
    `content`     TEXT         NOT NULL COMMENT '文章内容',
    `userId`      INT          NOT NULL COMMENT '文章作者ID',
    `tag`         INT          NOT NULL COMMENT '书类型',
    `likes` INT DEFAULT 0 COMMENT '点赞数',
    `isPublished` TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '是否已发布（0:未发布，1:已发布）',
    PRIMARY KEY (`id`),
    KEY `idx_deletedAt` (`deletedAt`) COMMENT '软删除查询索引',
    KEY `idx_tag` (`tag`) COMMENT '书类型'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='分享书籍表';

# 全文索引，正常es，暂时不考虑，除非es搞不出来
# ALTER TABLE article ADD FULLTEXT INDEX `ft_content` (`content`);

INSERT INTO `article` (`bookName`, `isbn`, `cover`, `title`, `content`, `userId`, `tag`, `isPublished`, `createdAt`,
                       `updatedAt`)
VALUES ('三体', '9787536692930', 'https://example.com/covers/santi.jpg', '《三体》中的宇宙社会学思考',
        '《三体》系列展示了宇宙中可能存在的黑暗森林法则...', 101, 1, 1, '2023-01-15 09:30:00', '2023-01-20 14:25:00'),
       ('活着', '9787506365437', 'https://example.com/covers/huozhe.jpg', '《活着》中的人生哲学',
        '余华的《活着》通过福贵的一生展现了生命的韧性...', 102, 2, 1, '2023-02-10 11:20:00', '2023-02-15 16:40:00'),
       ('人类简史', '9787508647357', NULL, '从《人类简史》看认知革命', '赫拉利提出的认知革命改变了人类的发展轨迹...', 103,
        3, 0, '2023-03-05 14:15:00', '2023-03-05 14:15:00'),
       ('小王子', '9787020042494', 'https://example.com/covers/xiaowangzi.jpg', '《小王子》的成人童话世界',
        '这本写给大人的童话蕴含着深刻的哲学思考...', 104, 4, 1, '2023-04-18 10:45:00', '2023-04-20 09:30:00'),
       ('白夜行', '9787544258609', NULL, '东野圭吾《白夜行》的叙事艺术', '小说中复杂的叙事结构和人物关系令人叹服...', 105,
        5, 1, '2023-05-22 16:20:00', '2023-05-25 11:15:00'),
       ('Python编程从入门到实践', '9787115428028', 'https://example.com/covers/python.jpg', 'Python学习心得分享',
        '这本书是Python初学者的绝佳选择...', 106, 6, 1, '2023-06-30 13:10:00', '2023-07-05 08:45:00'),
       ('百年孤独', '9787544258975', 'https://example.com/covers/bainiangudu.jpg', '马尔克斯的魔幻现实主义',
        '布恩迪亚家族七代人的兴衰展现了拉美的历史...', 107, 2, 0, '2023-07-12 15:30:00', '2023-07-12 15:30:00'),
       ('时间简史', '9787535732309', NULL, '霍金《时间简史》读后感', '这本书让我对宇宙和时间的理解更加深入...', 108, 3, 1,
        '2023-08-08 09:15:00', '2023-08-10 14:20:00'),
       ('围城', '9787020090006', 'https://example.com/covers/weicheng.jpg', '《围城》中的现代人困境',
        '钱钟书笔下的方鸿渐形象极具代表性...', 109, 2, 1, '2023-09-19 11:40:00', '2023-09-22 10:30:00'),
       ('明朝那些事儿', '9787506341271', NULL, '通俗历史写作的典范', '这本书让历史变得生动有趣...', 110, 7, 1,
        '2023-10-25 14:50:00', '2023-10-28 16:15:00');