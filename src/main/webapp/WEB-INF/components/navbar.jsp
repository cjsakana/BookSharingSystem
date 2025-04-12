<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String currentPageTmp = request.getParameter("page") == null ? "home" : request.getParameter("page");

%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">

<div class="left-navbar">
    <img src="${pageContext.request.contextPath}/images/logo.png" style="width: 100%">
    <a href="index.jsp?page=home" class="nav-item <%= "home".equals(currentPageTmp) ? "active" : "" %>">
        <div class="nav-item-content">
            <i class="fas fa-home nav-icon"></i>
            <span class="nav-text">发现</span>
        </div>
    </a>

    <a href="index.jsp?page=publish" class="nav-item <%= "publish".equals(currentPageTmp) ? "active" : "" %>">
        <div class="nav-item-content">
            <i class="fas fa-plus-circle nav-icon"></i>
            <span class="nav-text">发布</span>
        </div>
    </a>
    <a href="index.jsp?page=notification" class="nav-item <%= "notification".equals(currentPageTmp) ? "active" : "" %>">
        <div class="nav-item-content">
            <i class="fas fa-bell nav-icon"></i>
            <span class="nav-text">通知</span>
        </div>
    </a>
    <a href="index.jsp?page=profile" class="nav-item <%= "profile".equals(currentPageTmp) ? "active" : "" %>">
        <div class="nav-item-content" id="avatar-container">
            <i class="fas fa-user nav-icon"></i>
            <span class="nav-text">我</span>
        </div>
    </a>

    <div class="more-container">
        <div class="nav-item" id="moreButton">
            <div class="nav-item-content">
                <i class="fas fa-ellipsis-h nav-icon"></i>
                <span class="nav-text">更多</span>
            </div>
        </div>

        <div class="more-menu" id="moreMenu">
            <div class="more-item" id="aboutButton">关于</div>
            <div class="more-item" id="logoutButton">退出登录</div>
        </div>
    </div>
</div>



<!-- 关于弹窗 -->
<div class="modal-overlay" id="aboutModal">
    <div class="modal-content">
        <span class="modal-close" id="closeModal">&times;</span>
        <h3>关于广软图书分享系统</h3>
        <p>广软图书分享系统是一个图书分享平台，发现好书，陶冶情操。</p>
        <p>版本: 0.4.0</p>
    </div>
</div>

<script>
    const container = document.getElementById('avatar-container');

     // 从 localStorage 读取 userData
    const userData = localStorage.getItem('userData');

    if (userData) {
        try {
            const { avatar } = JSON.parse(userData);

            // 2. 如果存在头像URL，替换默认图标
            if (avatar?.trim()) {
                // 移除默认图标
                const icon = container.querySelector('.fa-user');
                if (icon) icon.remove();

                // 插入头像图片
                const img = document.createElement('img');
                img.src = avatar;
                img.className = 'user-avatar';
                img.alt = '用户头像';
                img.onerror = () => {
                    img.src = 'default-avatar.png'; // 加载失败时显示默认图
                };

                // 将头像插入到文字前
                container.insertBefore(img, container.querySelector('.nav-text'));
            }
        } catch (e) {
            console.error('解析本地存储失败:', e);
        }
    }


    // 更多菜单控制
    const moreButton = document.getElementById('moreButton');
    const moreMenu = document.getElementById('moreMenu');
    const aboutButton = document.getElementById('aboutButton');
    const logoutButton = document.getElementById('logoutButton');
    const aboutModal = document.getElementById('aboutModal');
    const closeModal = document.getElementById('closeModal');

    // 点击更多按钮
    moreButton.addEventListener('click', function(e) {
        e.stopPropagation();
        moreMenu.classList.toggle('show');
    });

    // 点击关于
    aboutButton.addEventListener('click', function() {
        moreMenu.classList.remove('show');
        aboutModal.classList.add('show');
    });

    // 点击退出登录
    logoutButton.addEventListener('click', function() {
        //
        // window.location.href = 'logout.jsp';
    });

    // 关闭弹窗
    closeModal.addEventListener('click', function() {
        aboutModal.classList.remove('show');
    });

    // 点击外部关闭菜单和弹窗
    document.addEventListener('click', function() {
        moreMenu.classList.remove('show');
    });

    // 阻止弹窗内容点击冒泡
    aboutModal.addEventListener('click', function(e) {
        e.stopPropagation();
    });

    document.querySelector('.modal-content').addEventListener('click', function(e) {
        e.stopPropagation();
    });
</script>