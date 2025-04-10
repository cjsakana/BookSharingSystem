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
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"], input[type="email"], textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .radio-group {
            display: flex;
            gap: 15px;
        }
        .radio-option {
            display: flex;
            align-items: center;
        }
        .code-group {
            display: flex;
            gap: 10px;
        }
        .code-group input {
            flex: 1;
        }
        .code-group button {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .code-group button:disabled {
            background-color: #cccccc;
            cursor: not-allowed;
        }
        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #0b7dda;
        }
        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // 发送验证码
            $('#sendCodeBtn').click(function() {
                const email = $('#email').val();
                if (!email) {
                    alert('请输入邮箱地址');
                    return;
                }

                // 验证邮箱格式
                if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email)) {
                    alert('邮箱格式不正确');
                    return;
                }

                // 发送验证码请求
                $.ajax({
                    url: '${pageContext.request.contextPath}/user/sendCode',
                    type: 'POST',
                    data: {email: email},
                    success: function(response) {
                        if (response.success) {
                            alert('验证码已发送，请查收');
                            startCountdown();
                        } else {
                            alert(response.message || '验证码发送失败');
                        }
                    },
                    error: function() {
                        alert('服务器错误，请稍后再试');
                    }
                });
            });

            // 倒计时功能
            function startCountdown() {
                let seconds = 60;
                const btn = $('#sendCodeBtn');
                btn.prop('disabled', true);

                const timer = setInterval(function() {
                    seconds--;
                    btn.text(seconds + '秒后重试');

                    if (seconds <= 0) {
                        clearInterval(timer);
                        btn.prop('disabled', false);
                        btn.text('获取验证码');
                    }
                }, 1000);
            }

            // 表单提交验证
            $('#registerForm').submit(function(e) {
                const email = $('#email').val();
                const password = $('#password').val();
                const code = $('#code').val();
                const name = $('#name').val();

                if (!email || !password || !code || !name) {
                    alert('请填写所有必填项');
                    e.preventDefault();
                    return false;
                }

                // 可以添加更多验证逻辑
                return true;
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h1>用户注册</h1>
    <form id="registerForm" action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="form-group">
            <label for="email">邮箱*</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">密码*</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="code">验证码*</label>
            <div class="code-group">
                <input type="text" id="code" name="code" required>
                <button type="button" id="sendCodeBtn">获取验证码</button>
            </div>
        </div>

        <div class="form-group">
            <label for="name">昵称*</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label>性别*</label>
            <div class="radio-group">
                <div class="radio-option">
                    <input type="radio" id="female" name="sex" value="0" checked>
                    <label for="female">女</label>
                </div>
                <div class="radio-option">
                    <input type="radio" id="male" name="sex" value="1">
                    <label for="male">男</label>
                </div>
                <div class="radio-option">
                    <input type="radio" id="unknown" name="sex" value="2">
                    <label for="unknown">未知</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="signature">个性签名</label>
            <textarea id="signature" name="signature" placeholder="该用户很神秘，没有留下什么"></textarea>
        </div>

        <button type="submit" class="submit-btn">注册</button>
    </form>
</div>
</body>
</html>