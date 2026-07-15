import { updateUserInfo, updatePassword, logout, getUserInfo } from "./request/user.js";

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
moreButton.addEventListener('click', function (e) {
    e.stopPropagation();
    moreMenu.classList.toggle('show');
});

// 点击关于
aboutButton.addEventListener('click', function () {
    moreMenu.classList.remove('show');
    aboutModal.classList.add('show');
});

// 点击退出登录
logoutButton.addEventListener('click', function () {
    localStorage.clear()
    logout();
    window.location.href = 'index.jsp';
});

// 关闭弹窗
closeModal.addEventListener('click', function () {
    aboutModal.classList.remove('show');
});

// 点击外部关闭菜单和弹窗
document.addEventListener('click', function () {
    moreMenu.classList.remove('show');
});

// 阻止弹窗内容点击冒泡
aboutModal.addEventListener('click', function (e) {
    e.stopPropagation();
});

document.querySelector('.modal-content').addEventListener('click', function (e) {
    e.stopPropagation();
});

const editProfileButton = document.getElementById('editProfileButton');
const editProfileModal = document.getElementById('editProfileModal');
const closeEditProfileModal = document.getElementById('closeEditProfileModal');
const editProfileForm = document.getElementById('navbar-editProfileForm');

// 点击修改个人信息
editProfileButton.addEventListener('click', function () {
    moreMenu.classList.remove('show');
    // 预填充表单
    const userData = localStorage.getItem('userData');
    if (userData) {
        try {
            const { name, sex, signature } = JSON.parse(userData);
            console.log(name, sex, signature);

            document.getElementById('navbar-name').value = name || '';

            // const sexValue = sex === 0 ? "女" : (sex === 1 ? "男" : "保密");
            document.getElementById('navbar-sex').value = sex;

            document.getElementById('navbar-signature').value = signature || '';
        } catch (e) {
            document.getElementById('navbar-name').value = '';
            document.getElementById('navbar-sex').value = '保密';
            document.getElementById('navbar-signature').value = '';
        }
    }
    editProfileModal.classList.add('show');
});

// 关闭修改个人信息弹窗
closeEditProfileModal.addEventListener('click', function () {
    editProfileModal.classList.remove('show');
});

// 提交修改个人信息表单
editProfileForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const name = document.getElementById('navbar-name').value.trim();
    const sex = parseInt(document.getElementById('navbar-sex').value, 10);
    const signature = document.getElementById('navbar-signature').value.trim();

    updateUserInfo({ name, sex, signature }).then(res => {
        localStorage.setItem('userData', JSON.stringify(res.data.data));
        window.location.reload();
    })
    alert('个人信息已保存！');
    editProfileModal.classList.remove('show');
});

// 点击外部关闭弹窗
editProfileModal.addEventListener('click', function (e) {
    e.stopPropagation();
});

// 修改密码弹窗相关
const changePasswordButton = document.getElementById('changePasswordButton');
const changePasswordModal = document.getElementById('changePasswordModal');
const closeChangePasswordModal = document.getElementById('closeChangePasswordModal');
const changePasswordForm = document.getElementById('navbar-changePasswordForm');

// 点击“修改密码”按钮，显示弹窗
changePasswordButton.addEventListener('click', function () {
    moreMenu.classList.remove('show');
    changePasswordModal.classList.add('show');
});

// 关闭“修改密码”弹窗
closeChangePasswordModal.addEventListener('click', function () {
    changePasswordModal.classList.remove('show');
});

// 点击弹窗外部关闭弹窗
changePasswordModal.addEventListener('click', function (e) {
    e.stopPropagation();
});
changePasswordModal.querySelector('.modal-content').addEventListener('click', function (e) {
    e.stopPropagation();
});

// 提交修改密码表单
changePasswordForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const oldPassword = document.getElementById('navbar-oldPassword').value.trim();
    const newPassword = document.getElementById('navbar-newPassword').value.trim();
    const confirmPassword = document.getElementById('navbar-confirmPassword').value.trim();

    if (!oldPassword || !newPassword || !confirmPassword) {
        alert('请填写所有字段');
        return;
    }
    if (newPassword !== confirmPassword) {
        alert('两次输入的新密码不一致');
        return;
    }

    updatePassword({ oldPassword, newPassword }).then(res => {
        if (res.data.code===200) {
            alert('密码修改成功，请重新登录');
            localStorage.clear();
            window.location.href = 'index.jsp';
        } else {
            alert(res.data.data.message ? res.data.message : '密码修改失败');
        }
    }).catch(() => {
        alert('密码修改失败，请重试');
    });
    changePasswordModal.classList.remove('show');
});
