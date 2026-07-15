<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/13
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章展示</title>
    <!-- 引入必要的库 -->
    <%--    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>--%>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--    <!-- 引入Markdown解析器 -->--%>
    <%--    <script src="https://cdn.jsdelivr.net/npm/marked@4.0.0/marked.min.js"></script>--%>
    <%--    <!-- 引入代码高亮 -->--%>
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/styles/github.min.css">--%>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/highlight.min.js"></script>--%>

    <!-- 引入必要的库 -->
    <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>
    <link href="${pageContext.request.contextPath}/css/libs/bootstrap.min.css" rel="stylesheet">
    <!-- 引入Markdown解析器 -->
    <script src="${pageContext.request.contextPath}/js/libs/marked.min.js"></script>
    <!-- 引入代码高亮 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/libs/github.min.css">
    <script src="${pageContext.request.contextPath}/js/libs/highlight.min.js"></script>


    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #ececec;
            margin-left: 10%;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            display: flex;
            flex-direction: column;
            background-color: white;;
        }

        .article-header {
            margin-bottom: 30px;
        }

        .article-title {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 15px;
            line-height: 1.3;
        }

        .article-user {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            font-size: 0.9rem;
            color: #666;
        }

        .author-avatar {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .author-name {
            font-weight: 500;
            color: #333;
            margin-right: 15px;
        }

        .article-meta {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            font-size: 0.9rem;
            color: #666;
        }

        .book-name,
        .isbn,
        .article-tags {
            font-size: 0.9rem;
            color: #666;
            /* display: flex; */
            align-items: center;
            margin-right: 15px; /* 添加右侧间距 */
        }
        
        .article-meta {
            /* display: flex; */
            align-items: center;
            margin-bottom: 20px;
            font-size: 0.9rem;
            color: #666;
            flex-wrap: wrap; /* 允许在小屏幕上换行 */
        }


        .article-content {
            margin-top: 20px;
        }

        .article-content img {
            max-width: 100%;
            border-radius: 4px;
        }

        .article-cover {
            width: 100%;
            max-height: 400px;
            object-fit: cover;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        .like-btn {
            background-color: #f0f0f0;
            border: none;
            border-radius: 4px;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s;
            font-size: 0.9rem;
            float: right; /* 新增：使按钮靠右 */
            clear: both; /* 确保浮动不影响其他元素 */
        }


        .like-btn:hover {
            background-color: #e0e0e0;
        }

        .like-btn.liked {
            background-color: #1890ff;
            color: white;
        }

        .like-btn-container {
            margin-top: 30px;
            display: flex;
            align-items: center;
            justify-content: flex-end; /* 新增：使按钮靠右 */
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 文章封面图 -->
    <img id="articleCover" class="article-cover" src="" alt="文章封面">

    <!-- 文章标题 -->
    <h1 id="articleTitle" class="article-title"></h1>

    <!-- 文章元信息 -->
    <div class="article-user">
        <img id="authorAvatar" class="author-avatar" src="" alt="作者头像">
        <span id="authorName" class="author-name"></span>
    </div>

    <div id="bookName" class="book-name"></div>
    <div id="isbn" class="isbn"></div>
    <div id="articleTags" class="article-tags"></div>

    <!-- 文章内容 -->
    <div id="articleContent" class="article-content"></div>

    <!-- 点赞按钮 -->
    <div class="like-btn-container">
        <button id="likeBtn" class="like-btn">👍 点赞</button>
        <span id="likeCount"  style="margin-left:10px;"></span>
    </div>
</div>


<script type="module" src="${pageContext.request.contextPath}/js/article.js"></script>
</body>
</html>