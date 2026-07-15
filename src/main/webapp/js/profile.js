import { apiFindUserArticle,apiArticleDel } from "./request/article.js";
import { apiGetUserCount } from "./request/likes.js";
import { formatDate } from "./util/util.js";


// 当页面加载完成后执行
document.addEventListener('DOMContentLoaded', function () {
    const container = document.getElementById('articleContainer');
    let page = 1;
    const size = 10;
    let hasMore = true;
    let isLoading = false;

    // 加载用户信息
    loadUserInfo();

    // 加载第一页
    loadUserArticles(page);

    // 加载更多按钮
    const loadMoreBtn = document.createElement('button');
    loadMoreBtn.textContent = '加载更多';
    loadMoreBtn.style.display = 'block';
    loadMoreBtn.style.margin = '20px auto';
    loadMoreBtn.style.padding = '8px 24px';
    loadMoreBtn.style.background = '#2196F3';
    loadMoreBtn.style.color = '#fff';
    loadMoreBtn.style.border = 'none';
    loadMoreBtn.style.borderRadius = '4px';
    loadMoreBtn.style.cursor = 'pointer';
    loadMoreBtn.onclick = function() {
        if (!isLoading && hasMore) {
            page++;
            loadUserArticles(page, true);
        }
    };

    // 从本地存储中获取用户数据
    function loadUserInfo() {
        const userData = JSON.parse(localStorage.getItem('userData'));
        
        // 检查是否存在用户数据
        if (userData) {
            document.getElementById('username').textContent = userData.name;
            document.getElementById('uid').textContent = "uid：" + userData.id;


            const sexText = userData.sex === 0 ? '女' : (userData.sex === 1 ? '男' : '保密');
            document.getElementById('sex').textContent = `性别：${sexText}`;
        
            document.getElementById('signature').textContent = 
                `个性签名：${userData.signature || '这个人很懒，什么都没有留下'}`;

            apiGetUserCount(userData.id).then(res => {
                document.getElementById('likes').textContent = `获赞数：${res.data.data}`;
            })
        } else {
            console.log('未找到用户数据，请确保用户已登录');
        }
    }

    // 加载用户文章
    function loadUserArticles(pageNum = 1, append = false) {
        isLoading = true;
        if (!append) {
            showLoading();
        } else {
            loadMoreBtn.disabled = true;
            loadMoreBtn.textContent = '加载中...';
        }

        apiFindUserArticle({ page: pageNum, size }).then(articles => {
            hideLoading();
            isLoading = false;

            if (!articles || articles.length === 0) {
                hasMore = false;
                if (!append) {
                    showEmptyContent();
                }
                loadMoreBtn.style.display = 'none';
                return;
            }

            renderArticles(articles, append);

            // 判断是否还有更多
            if (articles.length < size) {
                hasMore = false;
                loadMoreBtn.style.display = 'none';
            } else {
                hasMore = true;
                loadMoreBtn.style.display = 'block';
                loadMoreBtn.disabled = false;
                loadMoreBtn.textContent = '加载更多';
            }

            // 如果是第一页且有数据，插入按钮
            if (!append && hasMore) {
                container.parentNode.appendChild(loadMoreBtn);
            }
        }).catch(err => {
            console.error('加载文章失败:', err);
            isLoading = false;
            hideLoading();
            showEmptyContent('加载失败，请稍后重试');
            loadMoreBtn.style.display = 'none';
        });
    }

    // 渲染文章列表
    function renderArticles(articles, append = false) {
        if (!append) {
            container.innerHTML = '';
        }
        articles.forEach(article => {
            const articleItem = document.createElement('div');
            articleItem.className = 'article-item';

            // 文章链接
            const articleLink = document.createElement('a');
            articleLink.href = `/index.jsp?page=article&&id=${article.id}`;
            articleLink.style.textDecoration = 'none';
            articleLink.style.color = 'inherit';

            // 构建文章内容
            articleLink.innerHTML = `
                ${article.cover ? `<img src="${article.cover}" class="article-cover" alt="文章封面">` : '<div class="article-cover"></div>'}
                <div class="article-title">${article.title}</div>
            `;

            // 构建元信息
            const metaInfo = document.createElement('div');
            metaInfo.className = 'article-meta';
            metaInfo.innerHTML = `
                <span>${article.tagName || '未分类'}</span>
                <span>${formatDate(article.updatedAt)}</span>
                <span>❤️ ${article.likes || 0}</span>
            `;

            // 新增：状态、编辑、删除
            const actionBar = document.createElement('div');
            actionBar.style.marginTop = '10px';
            actionBar.style.display = 'flex';
            actionBar.style.alignItems = 'center';
            actionBar.style.gap = '10px';

            // 状态文本
            const statusSpan = document.createElement('span');
            statusSpan.textContent = article.isPublished === 1 ? '已发布' : '未发布';
            statusSpan.style.color = article.isPublished === 1 ? '#4CAF50' : '#f44336';

            // 编辑按钮
            const editBtn = document.createElement('button');
            editBtn.textContent = '编辑';
            editBtn.style.padding = '2px 10px';
            editBtn.style.background = '#2196F3';
            editBtn.style.color = '#fff';
            editBtn.style.border = 'none';
            editBtn.style.borderRadius = '3px';
            editBtn.style.cursor = 'pointer';
            editBtn.onclick = function() {
                window.location.href = `/index.jsp?page=publish&&id=${article.id}`;
            };

            // 删除按钮
            const delBtn = document.createElement('button');
            delBtn.textContent = '删除';
            delBtn.style.padding = '2px 10px';
            delBtn.style.background = '#f44336';
            delBtn.style.color = '#fff';
            delBtn.style.border = 'none';
            delBtn.style.borderRadius = '3px';
            delBtn.style.cursor = 'pointer';
            delBtn.onclick = function() {
                if(confirm('确定要删除这篇文章吗？')) {
                    apiArticleDel(article.id).then(() => {
                        loadUserArticles(page);
                    })
                    alert('请实现删除接口');
                }
            };

            actionBar.appendChild(statusSpan);
            actionBar.appendChild(editBtn);
            actionBar.appendChild(delBtn);

            // 组装所有元素
            articleItem.appendChild(articleLink);
            articleItem.appendChild(metaInfo);
            articleItem.appendChild(actionBar);

            container.appendChild(articleItem);
        });
    }

    // 显示加载指示器
    function showLoading() {
        container.innerHTML = '<div class="loading-indicator">加载中...</div>';
    }

    // 隐藏加载指示器
    function hideLoading() {
        const loader = container.querySelector('.loading-indicator');
        if (loader) loader.remove();
    }

    // 显示空内容提示
    function showEmptyContent(message = '你还没有发布任何内容哦') {
        container.innerHTML = `<div class="no-more-content">${message}</div>`;
    }
});