import {findArticle} from "./request/article.js";
import {formatDate, getTagName} from "./util/util.js";



document.addEventListener('DOMContentLoaded', function () {

    // 获取DOM元素
    const searchInput = document.getElementById('searchInput');
    const searchBtn = document.getElementById('searchBtn');
    const searchResults = document.getElementById('searchResults');
    const rankings = document.getElementById('rankings');
    const tags = document.querySelectorAll('.tag');
    const container = document.getElementById('articleContainer');

    // 当前选中的分类
    let currentCategory = 'rec';
    let page = 1
    // 滚动加载相关变量
    let isLoading = false;
    let hasMore = true;
    let lastScrollTime = 0;

    window.onload = function () {
        performSearch();
    };

    // 点击搜索框时显示热门搜索
    searchInput.addEventListener('focus', function () {
        showHotSearches();
    });

    // 点击搜索按钮
    searchBtn.addEventListener('click', function () {
        performSearch();
    });

    // 按回车键搜索
    searchInput.addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
            performSearch();
        }
    });

    // 点击分类标签
    tags.forEach(tag => {
        tag.addEventListener('click', function () {
            // 移除所有标签的active类
            tags.forEach(t => t.classList.remove('active'));
            // 给当前点击的标签添加active类
            this.classList.add('active');
            // 更新当前分类
            currentCategory = this.dataset.category;

            performSearch();
        });
    });

    // 点击文档其他区域关闭搜索结果
    document.addEventListener('click', function (e) {
        if (!e.target.closest('.search-container')) {
            searchResults.style.display = 'none';
        }
    });



    // 处理搜索结果
    function handleSearchResult(results, isAppend = false) {
        // 隐藏加载指示器
        hideLoading();

        if (!isAppend) {
            container.innerHTML = '';
            hasMore = true;
        }

        if (results.length === 0) {
            if (!isAppend) {
                container.innerHTML = '<p>没有找到相关文章</p>';
            } else {
                showNoMoreContent();
            }
            return;
        }

        // 创建文档片段提高性能
        const fragment = document.createDocumentFragment();

        results.forEach(article => {
            const tag = getTagName(article.tag);
            const updatedAt = formatDate(article.updatedAt);

            const articleItem = document.createElement('div');
            articleItem.className = 'article-item';

            // 文章链接
            const articleLink = document.createElement('a');
            articleLink.href = `/article/${article.id}`;
            articleLink.style.textDecoration = 'none';
            articleLink.style.color = 'inherit';

            // 作者链接
            const authorLink = document.createElement('a');
            authorLink.href = `/user/${article.userId}`;
            authorLink.style.textDecoration = 'none';
            authorLink.style.color = 'inherit';

            // 构建文章内容
            articleLink.innerHTML = `
                ${article.cover ? `<img src="${article.cover}" class="article-cover" alt="文章封面">` : '<div class="article-cover"></div>'}
                <div class="article-title">${article.title}</div>
            `;

            // 构建作者信息
            const authorInfo = document.createElement('div');
            authorInfo.className = 'article-author';
            authorInfo.innerHTML = `
                <img src="${article.userAvatar || 'default-avatar.jpg'}" class="author-avatar" alt="作者头像">
                <span>${article.username || '匿名用户'}</span>
            `;

            // 将作者信息放入作者链接
            authorLink.appendChild(authorInfo);

            // 构建元信息
            const metaInfo = document.createElement('div');
            metaInfo.className = 'article-meta';
            metaInfo.innerHTML = `
                <span class="article-tag">${tag}</span>
                <span>${updatedAt}</span>
                <span>❤️ ${article.likes || 0}</span>
            `;

            // 组装所有元素
            articleItem.appendChild(articleLink);
            articleItem.appendChild(authorLink);
            articleItem.appendChild(metaInfo);

            fragment.appendChild(articleItem);
        });

        container.appendChild(fragment);

        // 检查是否还有更多内容
        hasMore = results.length >= 12; // 假设每页10条
        if(!hasMore){
            showNoMoreContent();
        }
    }

    // 显示加载指示器
    function showLoading() {
        const container = document.getElementById('articleContainer');
        let loader = container.querySelector('.loading-indicator');

        if (!loader) {
            loader = document.createElement('div');
            loader.className = 'loading-indicator';
            loader.textContent = '加载中...';
            container.appendChild(loader);
        }

        loader.style.display = 'block';
    }

    // 隐藏加载指示器
    function hideLoading() {
        const loader = document.querySelector('.loading-indicator');
        if (loader) loader.style.display = 'none';
    }

    // 显示没有更多内容
    function showNoMoreContent() {
        const container = document.getElementById('articleContainer');
        let noMore = container.querySelector('.no-more-content');

        if (!noMore) {
            noMore = document.createElement('div');
            noMore.className = 'no-more-content';
            noMore.textContent = '没有更多内容了';
            container.appendChild(noMore);
        }

        noMore.style.display = 'block';
    }


    container.addEventListener('scroll', handleScroll);
    // 优化后的滚动处理函数
    function handleScroll() {
        const now = Date.now();
        // 添加简单的节流控制（100ms间隔）
        if (now - lastScrollTime < 100) return;
        lastScrollTime = now;

        checkScrollPosition();
    }

    // 精确检查滚动位置
    function checkScrollPosition() {
        // 确保容器有正确的高度
        if (container.scrollHeight <= container.clientHeight) {
            return;
        }

        // 检查是否滚动到接近底部（距离底部200px时触发）
        const scrollThreshold = 200;
        const isNearBottom = container.scrollTop + container.clientHeight >=
            container.scrollHeight - scrollThreshold;

        if (isNearBottom && !isLoading && hasMore) {
            loadMoreArticles();
        }
    }

    // 加载更多文章（优化版）
    async function loadMoreArticles() {
        try {
            isLoading = true;
            showLoading();

            // 获取当前时间戳用于防止重复请求
            const requestTime = Date.now();
            page+=1
            // 模拟API请求
            const newResults = await findArticle({
                query: searchInput.value.trim(),
                category: currentCategory,
                page: page,
                _: requestTime // 防止缓存
            });

            // 处理结果
            handleSearchResult(newResults, true);

            // 检查是否还有更多内容
            hasMore = newResults.length >= 12; // 假设每页10条

        } catch (error) {
            console.error('加载失败:', error);
            // 失败后3秒自动重试
            setTimeout(() => {
                if (!isLoading && hasMore) {
                    loadMoreArticles();
                }
            }, 3000);
        } finally {
            isLoading = false;
            hideLoading();
        }
    }

    // 执行搜索函数
    function performSearch() {
        let query = searchInput.value.trim();
        isLoading = true;
        showLoading();

        console.log({query, currentCategory,page})
        // 模拟API请求延迟
        setTimeout(() => {
            findArticle({query, currentCategory,page}).then(res => {
                handleSearchResult(res);
                isLoading = false;
            }).catch(() => {
                isLoading = false;
                hideLoading();
            });
        }, 300);
    }

    // 显示热门搜索
    function showHotSearches() {
        const hotSearches = [
            {rank: 1, title: "春季穿搭", content: "当前最热门的搜索"},
            {rank: 2, title: "减肥食谱", content: "热门健康饮食"},
            {rank: 3, title: "旅游攻略", content: "五一假期热门目的地"},
            {rank: 4, title: "编程学习", content: "热门IT技能"},
            {rank: 5, title: "健身教程", content: "居家锻炼方法"}
        ];

        rankings.innerHTML = '';
        hotSearches.forEach(item => {
            const div = document.createElement('div');
            div.className = 'ranking-item';
            div.innerHTML = `
                <div class="rank-number">${item.rank}</div>
                <div class="rank-content">
                    <div class="rank-title">${item.title}</div>
                </div>
            `;
            rankings.appendChild(div);
        });
        searchResults.style.display = 'block';
    }


    // 显示结果
    function displayResults(results) {
        rankings.innerHTML = '';

        if (results.length === 0) {
            rankings.innerHTML = '<p>没有找到相关结果</p>';
        } else {
            console.log(results)
        }

        searchResults.style.display = 'block';
    }
});