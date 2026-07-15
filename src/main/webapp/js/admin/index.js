import { apiOnlineCount,logout } from '../request/user.js';


function welcome() {
    const userData = localStorage.getItem('userData');
    const welcomeText = document.getElementById('welcomeText');

    if (userData) {
        try {
            const { name } = JSON.parse(userData);
            
            // 设置欢迎语
            if (welcomeText && name) {
                // 获取当前小时
                const hour = new Date().getHours();

                // 根据不同时间段设置不同的问候语
                let greeting;
                if (hour >= 5 && hour < 12) {
                    greeting = "早上好";
                } else if (hour >= 12 && hour < 18) {
                    greeting = "下午好";
                } else {
                    greeting = "晚上好";
                }
                welcomeText.innerHTML = `${greeting}，${name}！`;
            }
        } catch (e) {
            console.error('解析本地存储失败:', e);
        }
    } else {
        alert("请先登录");
        window.location.href = "/admin/login.jsp";
    }
}



function handleGetOnlineCount() {
    const onlineCount = document.getElementById('onlineCount');

    apiOnlineCount().then(res => {
        onlineCount.innerHTML = `<span style="font-weight:bold;">当前在线人数：</span>${res.data.data}`;
    }).catch(() => {
        onlineCount.innerHTML = `<span style="font-weight:bold;">当前在线人数：</span>-1`;
    });
}

window.onload = function () {
    welcome();
    handleGetOnlineCount();
};


function logou(){
    logout();
    localStorage.removeItem('userData');
    window.location.href = "/admin/login.jsp";
}