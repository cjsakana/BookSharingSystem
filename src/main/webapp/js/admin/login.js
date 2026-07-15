import { apiAdminLogin } from '../request/user.js';
const loginBtn = document.getElementById('loginButton');
// 绑定点击事件
if (loginBtn) {
    loginBtn.addEventListener('click', async function () {
        await loginSubmit();
    });
}

async function loginSubmit() {
    // 这里可以做前端校验，校验通过后提交
    var email = document.getElementById("email").value.trim();
    var password = document.getElementById("password").value.trim();
    if (email === "" || password === "") {
        alert("邮箱和密码不能为空！");
        return false; // 阻止提交
    }
    // 禁用按钮防止重复提交
    loginBtn.disabled = true;
    loginBtn.textContent = '登录中...';

    apiAdminLogin({email: email,password: password}).then(res => {

        if (res.data.code === 200) {
            alert("登录成功！");
            localStorage.setItem('userData', JSON.stringify(res.data.data))

            window.location.href = "/admin/index.jsp"; 
        } else {
            alert("登录失败！");
            // 恢复按钮状态
            loginBtn.disabled = false;
            loginBtn.textContent = '登 录';
        }
    });
    return true; // 允许提交
}