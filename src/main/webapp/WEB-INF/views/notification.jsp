<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/4
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<style>
    body{
        background-color: #ececec;
    }
    .notice-container{
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f5f5f5;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }
    .notice-item{
        text-align: center;
        font-size: 24px;
        font-weight: bold;
        color: #333;
        margin-bottom: 15px;
    }
    .likes{
        margin-top: 10px;
        overflow-y: auto;
        padding-right: 10px;
        height: 78vh;
    }
</style>
<body>

<div class="notice-container">
    <div class="notice-item">
        点赞
    </div>
    <hr >
    <div id="likes-container" class="likes">
<%--        点赞列表 --%>
    </div>
</div>

</body>
</html>

<script  type="module" src="${pageContext.request.contextPath}/js/notification.js"></script>
