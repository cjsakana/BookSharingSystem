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

    </style>
</head>
<body>


<div class="profile-container">
    <div class="profile-header">
        <div style="display:flex;flex-direction: row">
            <img src="https://img2.baidu.com/it/u=3839186672,3565557071&fm=253&fmt=auto&app=120&f=JPEG?w=667&h=500" class="profile-avatar"></img>
            <div class="profile-info">
                <h1 class="username">魚</h1>
                <p class="user-id-ip">uid：1</p>
                <p class="bio">该用户很神秘，没有留下什么</p>
                <p class="likes-count">0获赞</p>
            </div>
        </div>

    </div>

    <div class="profile-tabs">
        <button class="tab-button active">笔记</button>
    </div>

    <div class="empty-content">
        <p>你还没有发布任何内容哦</p>
    </div>
</div>
</body>
</html>
