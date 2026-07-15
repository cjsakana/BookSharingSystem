import { apiGetUserAll } from '../request/user.js';

function renderUserTable(userList) {
    const tbody = document.getElementById('user-table-body');
    tbody.innerHTML = '';
    userList.forEach(user => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td style="padding:10px 8px;text-align:center;">${user.id}</td>
        <td style="padding:10px 8px;text-align:center;">${user.name}</td>
        <td style="padding:10px 8px;text-align:center;">${user.email}</td>
        <td style="padding:10px 8px;text-align:center;">
          <button style="background:#ff2442;color:#fff;border:none;border-radius:8px;padding:4px 12px;cursor:pointer;" onclick="deleteUser(${user.id})">删除</button>
        </td>
      `;
      tbody.appendChild(tr);
    });
  }

  function loadUserList() {
    // 假设 getUserList() 返回 Promise，数据格式为 { data: [...] }
    apiGetUserAll().then(res => {
      renderUserTable(res.data.data);
    });
  }

  // 删除用户示例（你需要实现 deleteUser 方法和后端接口）
  function deleteUser(userId) {
    if(confirm('确定要删除该用户吗？')) {
    //   deleteUserById(userId).then(() => {
        loadUserList();
    //   });
    }
  }

  // 页面加载时获取用户列表
  window.onload = loadUserList;