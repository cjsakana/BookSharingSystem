<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/5/17
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台首页</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #ff2442;
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 50px;
            padding: 0 20px;
        }
        .header h2 {
          margin: 0;
          padding: 0;
        }
        .header button {
          background-color: #609af2;
          border: none;
          border-radius: 18px;
          color: #fff;
          padding: 8px 18px;
          font-size: 1rem;
          cursor: pointer;
          transition: background 0.2s;
        }
        .header button:hover {
          background-color: #4e90f2;
        }
        .grid-menu {
            display: grid;
            grid-template-columns: repeat(2, 120px);
            gap: 32px;
            justify-content: center;
            margin-top: 32px;
        }
        .grid-item {
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 24px 0 16px 0;
            cursor: pointer;
            transition: box-shadow 0.2s, transform 0.2s;
        }
        .grid-item:hover {
            box-shadow: 0 4px 16px rgba(96,154,242,0.18);
            transform: translateY(-2px) scale(1.04);
        }
        .icon-bg {
            width: 48px;
            height: 48px;
            border-radius: 50%;
            background-color: #609af2;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 12px;
            font-size: 2rem;
            color: #fff;
        }
        .grid-label {
            font-size: 1.1rem;
            color: #333;
            font-weight: 500;
        }
    </style>
</head>
<body>
    <div class="header">
      <h2 id="welcomeText"></h2>
      <button type="submit" onclick="logout">退出登录</button>
    </div>
    <h2 id="onlineCount"></h2>
    <br>
    <div class="grid-menu">
        <div class="grid-item" onclick="window.location.href='/admin/userManage.jsp'">
            <div class="icon-bg">👤</div>
            <div class="grid-label">用户管理</div>
        </div>
        <div class="grid-item" onclick="window.location.href='/admin/articleManage.jsp'">
            <div class="icon-bg">📝</div>
            <div class="grid-label">文章管理</div>
        </div>
    </div>
</body>
<script type="module" async src="${pageContext.request.contextPath}/js/admin/index.js"></script>
</html>
