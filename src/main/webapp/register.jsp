<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/9
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书分享系统 - 用户注册</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            background-image: url("https://mysys.seig.edu.cn/static/img/login-background.f9f49138.jpg");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 400px;
            background-color: rgba(255, 255, 255, 0.7);
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            border: 1px solid #e0e0e0; /* 添加细边框 */
        }

        .header {
            position: relative; /* 建立相对定位上下文 */
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 15px;
        }

        .back-btn {
            position: absolute; /* 绝对定位 */
            left: 0; /* 固定在左侧 */
            display: inline-flex;
            align-items: center;
            padding: 5px 10px;
            background: none;
            border: none;
            color: #666;
            font-size: 14px;
            cursor: pointer;
        }

        .back-btn:hover {
            color: #333;
        }

        h2 {
            margin: 0;
            font-weight: 500;
            text-align: center;
            flex-grow: 1;
        }

        .form-group {
            margin-bottom: 12px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #555;
            font-size: 14px;
        }

        input[type="text"], input[type="password"], input[type="email"], textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
            height: 36px;
        }

        textarea {
            height: 80px;
            resize: vertical;
        }

        .radio-group {
            display: flex;
            gap: 12px;
            margin-top: 2px;
        }

        .radio-option {
            display: flex;
            align-items: center;
            font-size: 13px;
        }

        .radio-option input {
            margin-right: 5px;
        }

        .code-group {
            display: flex;
            gap: 8px;
        }

        .code-group input {
            flex: 1;
        }

        .code-group button {
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 13px;
        }

        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            margin-top: 8px;
        }

        .back-btn {
            display: flex;
            justify-content: center;
            align-items: center;
            width: auto;
            height: 28px;
            background-color: transparent;
            color: #666;
            border: none;
            border-radius: 50%;
            font-size: 18px;
            cursor: pointer;
        }
    </style>

</head>
<%--<script type="module" async src="${pageContext.request.contextPath}/js/register.js"></script>--%>
<body>
<script type="module" src="${pageContext.request.contextPath}/js/register.js"></script>

<div class="container">
    <div class="header">
        <button type="button" id="backBtn" class="back-btn" title="返回"> &lt; 返回</button>
        <h2>用户注册</h2>
    </div>
    <div id="registerForm">
        <div class="form-group">
            <label for="email">邮箱 *</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">密码 *</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="code">验证码 *</label>
            <div class="code-group">
                <input type="text" id="code" name="code" required>
                <button type="button" id="sendCodeBtn">获取验证码</button>
            </div>
        </div>

        <div class="form-group">
            <label for="name">昵称 *</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label>性别 *</label>
            <div class="radio-group">
                <div class="radio-option">
                    <input type="radio" id="female" name="sex" value="0">
                    <label for="female">女</label>
                </div>
                <div class="radio-option">
                    <input type="radio" id="male" name="sex" value="1">
                    <label for="male">男</label>
                </div>
                <div class="radio-option">
                    <input type="radio" id="unknown" name="sex" value="2" checked>
                    <label for="unknown">未知</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="signature">个性签名</label>
            <textarea id="signature" name="signature" placeholder="该用户很神秘，没有留下什么"></textarea>
        </div>

        <button type="button" class="submit-btn" id="submitBtn">注册</button>
    </div>
</div>
</body>
</html>