<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/4
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>文章编辑</title>
    <meta charset="UTF-8">

    <!-- 引入jQuery -->
    <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>

    <!-- 引入Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/libs/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/libs/bootstrap.bundle.min.js"></script>

    <!-- 引入Font Awesome图标库 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/libs/all.min.css">

    <!-- 引入简化版Markdown编辑器 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/libs/easymde.min.css">
    <script src="${pageContext.request.contextPath}/js/libs/easymde.min.js"></script>


    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #ececec;
        }

        .editor-container {
            margin-top: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .editor-footer {
            margin-top: 20px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        /* 让编辑器随内容增长 */
        .EasyMDEContainer .CodeMirror,
        .EasyMDEContainer .CodeMirror-scroll {
            min-height: 300px;
        }

        .upload-area {
            border: 2px dashed #ddd;
            border-radius: 5px;
            padding: 20px;
            text-align: center;
            cursor: pointer;
            margin-bottom: 20px;
            position: relative;
        }

        .upload-area:hover {
            border-color: #aaa;
        }

        .upload-icon {
            font-size: 48px;
            color: #6c757d;
            margin-bottom: 10px;
        }

        #image-upload {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            opacity: 0;
            cursor: pointer;
        }

        .formatting-help {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-top: 20px;
            display: none;
        }

        .formatting-help h5 {
            margin-bottom: 15px;
        }

        .formatting-example {
            margin-bottom: 10px;
        }

        /* 一定要用它去覆盖掉父组件的样式，不然会多出来*/
        .nav-container {
            width: 0;
        }


        .publish-container {
            margin-left: 12%;
        }

        .back-btn{
            width: 100%;
            background-color: #f8f9fa;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            border-radius: 5px;
            padding: 10px;
        }

    </style>
</head>
<body>


<div class="publish-container">
    <div class="back-btn">
        <button onclick="confirmBack()" style="background: none; border: none; color: #000000; cursor: pointer; font-size: 16px;">
            &lt; 返回上一页
        </button>
    </div>

    <form id="article-form" class="editor-container">
        <!-- 图片上传区域 -->
        <div class="upload-area">
            <div class="upload-icon">
                <i class="fas fa-cloud-upload-alt"></i>
            </div>
            <div>点击上传图片</div>
            <input type="file" id="image-upload" accept="image/*">
        </div>

        <!-- 表单字段 -->
        <div class="form-group">
            <label for="title" class="form-label">文章标题</label>
            <input type="text" id="title" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="bookName" class="form-label">书名</label>
            <input type="text" id="bookName" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="isbn" class="form-label">ISBN（可选）</label>
            <input type="text" id="isbn" class="form-control">
        </div>

        <div class="form-group">
            <label for="tag" class="form-label">标签</label>
            <select id="tag" class="form-select">
                <option value=1>情感</option>
                <option value=2>成长</option>
                <option value=2>社会</option>
                <option value=4>心理</option>
                <option value=5>科幻</option>
                <option value=6>哲学</option>
                <option value=7>历史</option>
                <option value=8>其他</option>
            </select>
        </div>

        <div class="form-group">
            <label for="markdown-content" class="form-label">正文</label>
            <textarea id="markdown-content"></textarea>
        </div>

        <!-- 格式帮助区域 -->
        <div class="formatting-help" id="formatting-help">
            <h5><i class="fas fa-info-circle me-2"></i>常用格式示例</h5>
            <div class="formatting-example">
                <strong><i class="fas fa-heading me-1"></i>标题:</strong> 在行首输入1-6个#号，后面加空格
                <div class="text-muted">示例: # 一级标题</div>
            </div>
            <div class="formatting-example">
                <strong><i class="fas fa-bold me-1"></i>加粗:</strong> 选中文字后点击工具栏的<b>B</b>按钮
                <div class="text-muted">示例: 这是<strong>重要</strong>内容</div>
            </div>
            <div class="formatting-example">
                <strong><i class="fas fa-list-ul me-1"></i>列表:</strong> 使用工具栏的列表按钮或输入 - 或 1.
                <div class="text-muted">示例:
                    - 项目一<br>
                    - 项目二
                </div>
            </div>
            <div class="formatting-example">
                <strong><i class="fas fa-link me-1"></i>链接:</strong> 选中文字后点击工具栏的链接按钮
                <div class="text-muted">示例: [百度](https://www.baidu.com)</div>
            </div>
            <div class="formatting-example">
                <strong><i class="fas fa-quote-right me-1"></i>引用:</strong> 在行首输入 > 加空格
                <div class="text-muted">示例: > 这是引用内容</div>
            </div>
        </div>

        <div class="editor-footer">
            <!-- 添加帮助按钮 -->
            <button type="button" id="help-btn" class="btn btn-outline-info me-auto">
                <i class="fas fa-question-circle me-1"></i>格式帮助
            </button>
            <button type="button" id="save-btn" class="btn btn-outline-primary">
                <i class="fas fa-save me-1"></i>保存
            </button>
            <button type="button" id="submit-btn" class="btn btn-primary">
                <i class="fas fa-paper-plane me-1"></i>提交
            </button>
        </div>
    </form>
</div>

<script type="module" src="${pageContext.request.contextPath}/js/publish.js"></script>
</body>
</html>