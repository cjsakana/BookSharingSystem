<%--
  Created by IntelliJ IDEA.
  User: sakana
  Date: 2025/4/4
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="search-container">
    <!-- 搜索框 -->
    <div class="search-box">
        <div style="margin-left: 10px">
            <svg t="1743824290694" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1783" width="16" height="16">
                <path d="M853.504 723.456L701.44 570.88c28.16-55.296 39.424-120.32 26.624-188.416C704.512 256 596.992 157.696 469.504 144.384c-186.88-18.944-343.552 137.728-324.096 324.608 12.8 128 111.616 235.008 237.568 259.072 68.608 12.8 133.12 1.536 188.416-26.624l152.064 152.576c35.84 35.84 94.208 35.84 130.048 0s35.84-94.208 0-130.56z m-415.232-64c-121.856 0-221.184-99.328-221.184-221.696s99.328-221.696 221.184-221.696 221.184 99.328 221.184 221.696-99.328 221.696-221.184 221.696z" p-id="1784" fill="#707070">
                </path>
            </svg>
        </div>

        <input type="text" id="searchInput" placeholder="搜索">
        <button id="searchBtn">
            搜索
        </button>
    </div>

    <!-- 分类标签 -->
    <div class="category-tags">
        <span class="tag active" data-category="rec">推荐</span>
        <span class="tag" data-category="emo">情感</span>
        <span class="tag" data-category="coa">成长</span>
        <span class="tag" data-category="soc">社会</span>
        <span class="tag" data-category="psy">心理</span>
        <span class="tag" data-category="sci">科幻</span>
        <span class="tag" data-category="phi">哲学</span>
        <span class="tag" data-category="his">历史</span>
        <span class="tag" data-category="oth">其他</span>
    </div>

    <!-- 搜索结果 -->
    <div class="search-results" id="searchResults">
        <div class="rankings" id="rankings"></div>
    </div>
</div>

<div class="article-container" id="articleContainer">
    <!-- 动态内容将通过AJAX加载 -->
</div>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
<script type="module" src="${pageContext.request.contextPath}/js/home.js"></script>

<style>
    /* 搜索容器样式 - 优化网格布局 */
    #articleContainer {
        width: 100%;
        max-height: 600px;
        overflow-y: scroll;
        padding: 10px;
        box-sizing: border-box;
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 15px;
        position: relative;
    }

    .article-item {
        padding: 15px;
        border-radius: 5px;
        display: flex;
        flex-direction: column;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        cursor: pointer;
        height: 310px; /* 固定高度确保统一 */
    }

    /* 文章封面样式 */
    .article-cover {
        width: 100%;
        height: 120px;
        background-color: #4CAF50;
        margin-bottom: 10px;
        object-fit: cover;
        border-radius: 4px;
    }

    /* 文章标题 - 两行显示，高度固定 */
    .article-title {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 8px;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
        height: 40px;
        line-height: 20px;
    }

    /* 作者信息区域 */
    .article-author {
        display: flex;
        align-items: center;
        margin-top: auto;
        padding-top: 10px;
    }

    /* 作者头像 */
    .author-avatar {
        width: 30px;
        height: 30px;
        border-radius: 50%;
        background-color: #4CAF50;
        margin-right: 10px;
        object-fit: cover;
    }

    /* 文章元信息 */
    .article-meta {
        display: flex;
        justify-content: space-between;
        color: #666;
        font-size: 12px;
        margin-top: 8px;
    }

    /* 加载指示器 */
    .loading-indicator {
        grid-column: 1 / -1;
        text-align: center;
        padding: 15px;
        color: #666;
        display: none; /* 默认隐藏 */
    }

    /* 没有更多内容提示 */
    .no-more-content {
        grid-column: 1 / -1;
        text-align: center;
        padding: 15px;
        color: #666;
        display: none; /* 默认隐藏 */
    }
</style>
