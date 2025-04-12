<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  String currentPage = request.getParameter("page") == null ? "home" : request.getParameter("page");
  String contentPage = "/WEB-INF/views/" + currentPage + ".jsp";
%>

<!DOCTYPE html>
<html>
<head>
  <title>广软图书分享系统</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: 'PingFang SC', 'Microsoft YaHei', sans-serif;
      background-color: #f5f5f5;
    }

    .container {
      display: flex;
      height: 100vh;
    }

    .nav-container {
      width: 12%;
      background-color: #f8f9fa;
      flex-shrink: 0;
    }
    .content-container {
      flex: 1;
      padding: 20px;
      overflow-y: auto;
    }

    /* 登录弹窗样式 */
    .login-modal {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
      z-index: 1000;
      justify-content: center;
      align-items: center;
    }

    .login-content {
      background-color: white;
      padding: 30px;
      border-radius: 8px;
      width: 350px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    }

    .login-title {
      text-align: center;
      margin-bottom: 20px;
      color: #333;
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

    .register-link {
      text-align: center;
      margin-top: 15px;
    }

    .register-link a {
      color: #1890ff;
      text-decoration: none;
    }

    .register-link a:hover {
      text-decoration: underline;
    }

    /* 登录消息提示样式 */
    .login-message {
      padding: 10px;
      margin: 10px 0;
      border-radius: 4px;
      text-align: center;
    }

    .login-message.success {
      background-color: #f6ffed;
      border: 1px solid #b7eb8f;
      color: #52c41a;
    }

    .login-message.error {
      background-color: #fff2f0;
      border: 1px solid #ffccc7;
      color: #ff4d4f;
    }
  </style>

</head>

<script type="module" async src="${pageContext.request.contextPath}/js/login.js"></script>


<body>

<!-- 登录弹窗 -->
<div id="loginModal" class="login-modal">
  <div class="login-content">
    <h2 class="login-title">用户登录</h2>

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

    <div class="register-link">
      还没有账号？<a href="${pageContext.request.contextPath}/register.jsp">立即注册</a>
    </div>
  </div>
</div>

<div class="container">
  <div class="nav-container">
    <%@ include file="/WEB-INF/components/navbar.jsp" %>
  </div>
  <div class="content-container">
    <jsp:include page="<%= contentPage %>"/>
  </div>
</div>


</body>
</html>