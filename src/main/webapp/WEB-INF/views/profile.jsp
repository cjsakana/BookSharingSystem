<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/4
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
        }

        body {
            background-color: #f5f5f5;
            color: #333;
            line-height: 1.5;
            padding: 15px;
        }

        .profile-container {
            /*max-width: 600px;*/
            margin: 0 auto;
            overflow-y: auto;
            background-color: #fff;
            border-radius: 12px;
            min-height: 90vh;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
        }

        .profile-header {
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        .profile-avatar {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            margin-right: 15px;
            background-color: #f0f0f0;
        }


        .profile-info {
            flex: 1;
        }

        .username {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 5px;
            display: flex;
            align-items: center;
        }

        .user-id-ip {
            font-size: 12px;
            color: #999;
            margin-bottom: 8px;
        }

        .bio {
            font-size: 14px;
            color: #666;
            margin-bottom: 8px;
        }

        .likes-count {
            font-size: 14px;
            color: #666;
        }

        .profile-tabs {
            display: flex;
            border-bottom: 1px solid #f0f0f0;
        }

        .tab-button {
            flex: 1;
            padding: 12px 0;
            text-align: center;
            font-size: 15px;
            color: #666;
            background: none;
            border: none;
            position: relative;
        }

        .tab-button.active {
            color: #ff2442;
            font-weight: 600;
        }

        .tab-button.active::after {
            content: "";
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 2px;
            background-color: #ff2442;
        }

        .empty-content {
            padding: 60px 20px;
            text-align: center;
            color: #999;
            font-size: 15px;
        }
        .user-sex {
            font-size: 14px;
            color: #666;
            margin-bottom: 8px;
        }
        
        /* 文章容器样式 */
        .article-container {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 15px;
            position: relative;
        }

        .article-item {
            padding: 15px;
            border-radius: 5px;
            display: flex;
            flex-direction: column;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            cursor: pointer;
            height: 300px;
        }

        /* 文章封面样式 */
        .article-cover {
            width: 100%;
            height: 120px;
            background-color: #f0f0f0;
            margin-bottom: 10px;
            object-fit: cover;
            border-radius: 4px;
        }

        /* 文章标题 - 两行显示，高度固定 */
        .article-title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 8px;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
            height: 40px;
            line-height: 20px;
        }

        /* 文章元信息 */
        .article-meta {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            color: #666;
            font-size: 12px;
            margin-top: 8px;
        }
        
        /* 加载指示器 */
        .loading-indicator {
            grid-column: 1 / -1;
            text-align: center;
            padding: 15px;
            color: #666;
        }

        /* 没有更多内容提示 */
        .no-more-content {
            grid-column: 1 / -1;
            text-align: center;
            padding: 15px;
            color: #666;
        }
    </style>
</head>
<body>


<div class="profile-container">
    <div class="profile-header">
        <div style="display:flex;flex-direction: row">
            <img src="https://img2.baidu.com/it/u=3839186672,3565557071&fm=253&fmt=auto&app=120&f=JPEG?w=667&h=500"
                 class="profile-avatar"></img>
            <div class="profile-info">
                <h1 class="username" id="username"></h1>
                <p class="user-id-ip" id="uid"></p>
                <p class="user-sex" id="sex"></p>
                <p class="bio" id="signature"></p>
                <p class="likes-count" id="likes"></p>
            </div>
        </div>

    </div>

    <div class="profile-tabs">
        <button class="tab-button active">笔记</button>
    </div>

    <div id="articleContainer" class="article-container">
    </div>
</div>
</body>
<script  type="module" src="${pageContext.request.contextPath}/js/profile.js"></script>
</html>
