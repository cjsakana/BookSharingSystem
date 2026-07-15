<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String currentPageTmp = request.getParameter("page") == null ? "home" : request.getParameter("page");

%>

<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libs/https_cdnjs.cloudflare.com_ajax_libs_font-awesome_6.4.0_css_all.min.css">--%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
            <div class="more-item" id="editProfileButton">修改个人信息</div>
            <div class="more-item" id="changePasswordButton">修改密码</div>
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

<!-- 修改个人信息弹窗 -->
<div class="modal-overlay" id="editProfileModal">
    <div class="modal-content">
        <span class="modal-close" id="closeEditProfileModal">&times;</span>
        <h3>修改个人信息</h3>
        <form id="navbar-editProfileForm">
            <label for="navbar-name">昵称：</label>
            <input type="text" id="navbar-name" name="navbar-name"><br><br>
            <label for="navbar-sex">性别：</label>
            <select id="navbar-sex" name="navbar-sex">
                <option value="0">女</option>
                <option value="1">男</option>
                <option value="2">保密</option>
            </select><br><br>
            <label for="navbar-signature">个性签名：</label>
            <input type="text" id="navbar-signature" name="navbar-signature"><br><br>
            <button type="submit">保存</button>
        </form>
    </div>
</div>

<!-- 修改密码弹窗 -->
<div class="modal-overlay" id="changePasswordModal">
    <div class="modal-content">
        <span class="modal-close" id="closeChangePasswordModal">&times;</span>
        <h3>修改密码</h3>
        <form id="navbar-changePasswordForm">
            <label for="navbar-oldPassword">旧密码：</label>
            <input type="password" id="navbar-oldPassword" name="navbar-oldPassword"><br><br>
            <label for="navbar-newPassword">新密码：</label>
            <input type="password" id="navbar-newPassword" name="navbar-newPassword"><br><br>
            <label for="navbar-confirmPassword">确认新密码：</label>
            <input type="password" id="navbar-confirmPassword" name="navbar-confirmPassword"><br><br>
            <button type="submit">保存</button>
        </form>
    </div>
</div>

<script type="module" src="${pageContext.request.contextPath}/js/navbar.js"></script>
