<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/4
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<style>
    body{
        background-color: #ececec;
    }
    .notice-container{
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f5f5f5;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }
    .notice-item{
        text-align: center;
        font-size: 24px;
        font-weight: bold;
        color: #333;
        margin-bottom: 15px;
    }
    .likes{
        margin-top: 10px;
        overflow-y: auto;
        padding-right: 10px;
        height: 78vh;
    }
</style>
<body>

<div class="notice-container">
    <div class="notice-item">
        点赞
    </div>
    <hr >
    <div id="likes-container" class="likes">
<%--        点赞列表 --%>
    </div>
</div>

</body>
</html>

<script>
    // 更新示例数据，添加时间戳
    const likesData = [
        {
            user: {
                id: "user001",
                avatar: "https://randomuser.me/api/portraits/women/44.jpg",
                name: "张美丽"
            },
            article: {
                id: "article101",
                title: "《三体》中的宇宙社会学思考",
                cover: "https://picsum.photos/300/200?random=1"
            },
            timestamp: Date.now() - 3600000 // 1小时前
        },
        {
            user: {
                id: "user002",
                avatar: "https://randomuser.me/api/portraits/men/32.jpg",
                name: "王小明"
            },
            article: {
                id: "article102",
                title: "《活着》中的人生哲学",
                cover: "https://picsum.photos/300/200?random=2"
            },
            timestamp: Date.now() - 86400000 // 1天前
        },
        {
            user: {
                id: "user003",
                avatar: "https://randomuser.me/api/portraits/women/68.jpg",
                name: "李小红"
            },
            article: {
                id: "article103",
                title: "东野圭吾《白夜行》的叙事艺术",
                cover: "https://picsum.photos/300/200?random=3"
            },
            timestamp: Date.now() - 300000 // 5分钟前
        }
    ];

    // 时间格式化函数
    function formatTimeAgo(timestamp) {
        const now = Date.now();
        const diff = now - timestamp;

        const minutes = Math.floor(diff / 60000);
        if (minutes < 60) {
            return minutes.toString()+`分钟前`;
        }

        const hours = Math.floor(diff / 3600000);
        if (hours < 24) {
            return hours.toString()+`小时前`;
        }

        const days = Math.floor(diff / 86400000);
        console.log(days)
        return days.toString()+`天前`;
    }

    // 修改渲染函数
    function renderLikes() {
        const container = document.getElementById('likes-container');

        likesData.forEach(like => {
            const likeElement = document.createElement('div');
            likeElement.className = 'like-item';
            likeElement.style.display = 'flex';
            likeElement.style.alignItems = 'center';
            likeElement.style.marginBottom = '15px';
            likeElement.style.padding = '12px';
            likeElement.style.backgroundColor = '#fff';
            likeElement.style.borderRadius = '8px';
            likeElement.style.boxShadow = '0 1px 3px rgba(0,0,0,0.1)';

            // 1. 头像部分（左）
            const avatarContainer = document.createElement('div');
            avatarContainer.style.marginRight = '15px';
            avatarContainer.style.cursor = 'pointer';
            avatarContainer.onclick = () => goToProfile(like.user.id);

            const avatarImg = document.createElement('img');
            avatarImg.src = like.user.avatar;
            avatarImg.style.width = '50px';
            avatarImg.style.height = '50px';
            avatarImg.style.borderRadius = '50%';
            avatarImg.style.objectFit = 'cover';
            avatarContainer.appendChild(avatarImg);

            // 2. 信息部分（中）
            const infoContainer = document.createElement('div');
            infoContainer.style.flex = '1';
            infoContainer.style.minWidth = '0';

            // 用户名和时间
            const userLine = document.createElement('div');
            userLine.style.display = 'flex';
            userLine.style.alignItems = 'center';
            userLine.style.marginBottom = '5px';

            const username = document.createElement('div');
            username.textContent = like.user.name;
            username.style.fontWeight = 'bold';
            username.style.marginRight = '10px';
            username.style.cursor = 'pointer';
            username.onclick = () => goToProfile(like.user.id);

            const timeAgo = document.createElement('div');
            timeAgo.textContent = formatTimeAgo(like.timestamp);
            timeAgo.style.fontSize = '12px';
            timeAgo.style.color = '#999';

            userLine.appendChild(username);
            userLine.appendChild(timeAgo);

            // 文章标题（带分隔符）
            const titleContainer = document.createElement('div');
            titleContainer.style.display = 'flex';
            titleContainer.style.alignItems = 'center';

            const separator = document.createElement('span');
            separator.textContent = '|';
            separator.style.marginRight = '8px';
            separator.style.color = '#ccc';

            const articleTitle = document.createElement('div');
            articleTitle.textContent = like.article.title;
            articleTitle.style.fontSize = '14px';
            articleTitle.style.color = '#666';
            articleTitle.style.whiteSpace = 'nowrap';
            articleTitle.style.overflow = 'hidden';
            articleTitle.style.textOverflow = 'ellipsis';

            titleContainer.appendChild(separator);
            titleContainer.appendChild(articleTitle);

            infoContainer.appendChild(userLine);
            infoContainer.appendChild(titleContainer);

            // 3. 封面部分（右）
            const coverContainer = document.createElement('div');
            coverContainer.style.marginLeft = '15px';
            coverContainer.style.cursor = 'pointer';
            coverContainer.onclick = () => goToArticle(like.article.id);

            const articleCover = document.createElement('img');
            articleCover.src = like.article.cover;
            articleCover.style.width = '80px';
            articleCover.style.height = '60px';
            articleCover.style.objectFit = 'cover';
            articleCover.style.borderRadius = '4px';

            coverContainer.appendChild(articleCover);

            // 组装所有部分
            likeElement.appendChild(avatarContainer);
            likeElement.appendChild(infoContainer);
            likeElement.appendChild(coverContainer);

            container.appendChild(likeElement);
        });
    }

    window.onload = renderLikes;
</script>
