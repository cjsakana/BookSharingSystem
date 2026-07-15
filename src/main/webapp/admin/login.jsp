<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/5/17
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .login-container {
            width: 350px;
            margin: 100px auto;
            padding: 30px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 24px;
        }
        .form-group {
          margin-bottom: 15px;
        }

        .form-group label {
          display: block;
          margin-bottom: 5px;
          font-weight: 500;
        }

        .form-group input {
          width: 100%;
          padding: 8px 12px;
          border: 1px solid #ddd;
          border-radius: 4px;
          box-sizing: border-box;
        }

        .login-btn {
          width: 100%;
          padding: 10px;
          background-color: #1890ff;
          color: white;
          border: none;
          border-radius: 4px;
          cursor: pointer;
          font-size: 16px;
          margin-top: 10px;
        }

        .login-btn:hover {
          background-color: #40a9ff;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>管理员登录</h2>

    <div id="loginForm">
      <div class="form-group">
        <label for="email">邮箱</label>
        <input type="text" id="email" name="email" required placeholder="请输入邮箱">
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <input type="password" id="password" name="password" required placeholder="请输入密码">
      </div>
      <button type="button" class="login-btn" id="loginButton">登 录</button>
    </div>

</div>
<script type="module" async src="${pageContext.request.contextPath}/js/admin/login.js"></script>
</body>
</html>
