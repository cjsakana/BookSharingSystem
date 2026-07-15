<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/5/18
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #ff2442;
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 50px;
            padding: 0 20px;
        }
        .header h2 {
          margin: 0;
          padding: 0;
        }
    </style>
</head>
<body>
  <div class="header">
    <button onclick="window.history.back()" style="background-color:#609af2;border:none;border-radius:18px;color:#fff;padding:8px 18px;font-size:1rem;cursor:pointer;transition:background 0.2s;margin-right:16px;">返回</button>
    <h2>用户管理</h2>
  </div>
  
  <div style="padding: 32px;">
    <table style="width:100%;border-collapse:collapse;background:#fff;border-radius:12px;box-shadow:0 2px 8px rgba(0,0,0,0.06);overflow:hidden;">
      <thead style="background:#f7f7f7;">
        <tr>
          <th style="padding:12px 8px;border-bottom:1px solid #eee;">用户ID</th>
          <th style="padding:12px 8px;border-bottom:1px solid #eee;">用户名</th>
          <th style="padding:12px 8px;border-bottom:1px solid #eee;">邮箱</th>
          <th style="padding:12px 8px;border-bottom:1px solid #eee;">操作</th>
        </tr>
      </thead>
      <tbody id="user-table-body">
        <!-- 这里由JS动态渲染用户数据 -->
      </tbody>
    </table>
  </div>
</body>
</html>
<script type="module" async src="${pageContext.request.contextPath}/js/admin/userManage.js"></script>