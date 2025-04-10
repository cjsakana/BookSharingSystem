import {login} from "./request/user.js";

document.addEventListener('DOMContentLoaded', function () {
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const loginBtn = document.getElementById('loginButton');

    // 绑定点击事件
    if (loginBtn) {
        loginBtn.addEventListener('click', async function() {
            await submitLogin();
        });
    }
    window.onload = function () {
        const token = getCookie('token');
        if (!token) {
            document.getElementById('loginModal').style.display = 'flex';
        }
    };

    // 检查是否有token，如果没有则显示登录弹窗
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
    }


    async function submitLogin() {
        const email = emailInput.value.trim();
        const password = passwordInput.value;

        // 验证输入
        if (!email || !password) {
            showMessage('error', '请输入邮箱和密码');
            return;
        }

        // 禁用按钮防止重复提交
        loginBtn.disabled = true;
        loginBtn.textContent = '登录中...';

        try {
            // 等待登录请求完成
            const res = await login({
                email: email,
                password: password
            });

            // 处理响应
            if (res.code === 200 && res.data.code===200) {
                // 登录成功
                showMessage('success', '登录成功');

                // 设置token到cookie
                // document.cookie = `token=${res.data.token}; path=/; max-age=${7 * 24 * 60 * 60}`;

                // 2秒后刷新页面
                setTimeout(() => {
                    window.location.reload();
                }, 2000);
            } else {
                // 登录失败
                const errorMsg = res.data.msg || '登录失败，请重试';
                showMessage('error', errorMsg);
                passwordInput.value = ''; // 清空密码框
            }
        } catch (error) {
            console.error('登录请求出错:', error);
            showMessage('error', '网络错误，请稍后重试');
        } finally {
            // 恢复按钮状态
            loginBtn.disabled = false;
            loginBtn.textContent = '登 录';
        }
    }

// 显示消息提示的函数
    function showMessage(type, message) {
        // 移除旧的消息提示
        const oldMessage = document.getElementById('login-message');
        if (oldMessage) {
            oldMessage.remove();
        }

        // 创建消息元素
        const messageDiv = document.createElement('div');
        messageDiv.id = 'login-message';
        messageDiv.textContent = message;
        messageDiv.style.padding = '10px';
        messageDiv.style.margin = '10px 0';
        messageDiv.style.borderRadius = '4px';
        messageDiv.style.textAlign = 'center';

        // 根据类型设置样式
        if (type === 'success') {
            messageDiv.style.backgroundColor = '#f6ffed';
            messageDiv.style.border = '1px solid #b7eb8f';
            messageDiv.style.color = '#52c41a';
        } else {
            messageDiv.style.backgroundColor = '#fff2f0';
            messageDiv.style.border = '1px solid #ffccc7';
            messageDiv.style.color = '#ff4d4f';
        }

        // 插入到登录表单中
        const loginForm = document.getElementById('loginForm');
        loginForm.insertBefore(messageDiv, loginForm.firstChild);

        // 3秒后自动消失
        if (type === 'success') {
            setTimeout(() => {
                messageDiv.remove();
            }, 2000);
        } else {
            setTimeout(() => {
                messageDiv.remove();
            }, 3000);
        }
    }
})
