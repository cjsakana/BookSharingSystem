import {apiList} from "./request/likes.js";

let page=1
let pageSize=10
let likesData=[]


// 时间格式化函数
function formatTimeAgo(timestamp) {
    const now = Date.now();
    const diff = now - timestamp;

    const minutes = Math.floor(diff / 60000);
    if (minutes < 60) {
        return minutes.toString()+`分钟前`;
    }

    const hours = Math.floor(diff / 3600000);
    if (hours < 24) {
        return hours.toString()+`小时前`;
    }

    const days = Math.floor(diff / 86400000);
    console.log(days)
    return days.toString()+`天前`;
}

let isLoading = false; // 防止重复加载
let hasMore = true;    // 是否还有更多数据

function loadLikes() {
    if (isLoading || !hasMore) return;
    isLoading = true;
    // const flagTime = Date.now() - 3600000;
    const flagTime = 0;
    apiList({createdAt: flagTime, page: page, size: pageSize}).then(res => {
        const newData = res.data.data;
        if (newData.length < pageSize) {
            hasMore = false;
            if (newData.length === 0) {
                return;
            }
        }
        likesData.push(...newData);
        
        renderLikes();
        page++;
    }).finally(() => {
        isLoading = false;
    });
}

// 修改渲染函数，先清空再渲染
function renderLikes() {
    const container = document.getElementById('likes-container');
    container.innerHTML = ""; // 清空
    likesData.forEach(like => {
        const likeElement = document.createElement('div');
        likeElement.className = 'like-item';
        likeElement.style.display = 'flex';
        likeElement.style.alignItems = 'center';
        likeElement.style.marginBottom = '15px';
        likeElement.style.padding = '12px';
        likeElement.style.backgroundColor = '#fff';
        likeElement.style.borderRadius = '8px';
        likeElement.style.boxShadow = '0 1px 3px rgba(0,0,0,0.1)';

        // 1. 头像部分（左）
        const avatarContainer = document.createElement('div');
        avatarContainer.style.marginRight = '15px';
        avatarContainer.style.cursor = 'pointer';
        // avatarContainer.onclick = () => goToProfile(like.userId);

        const avatarImg = document.createElement('img');
        avatarImg.src = like.avatar;
        avatarImg.style.width = '50px';
        avatarImg.style.height = '50px';
        avatarImg.style.borderRadius = '50%';
        avatarImg.style.objectFit = 'cover';
        avatarContainer.appendChild(avatarImg);

        // 2. 信息部分（中）
        const infoContainer = document.createElement('div');
        infoContainer.style.flex = '1';
        infoContainer.style.minWidth = '0';

        // 用户名和时间
        const userLine = document.createElement('div');
        userLine.style.display = 'flex';
        userLine.style.alignItems = 'center';
        userLine.style.marginBottom = '5px';

        const username = document.createElement('div');
        username.textContent = like.username;
        username.style.fontWeight = 'bold';
        username.style.marginRight = '10px';
        username.style.cursor = 'pointer';
        // username.onclick = () => goToProfile(like.userId);

        const timeAgo = document.createElement('div');
        timeAgo.textContent = formatTimeAgo(like.createdAt);
        timeAgo.style.fontSize = '12px';
        timeAgo.style.color = '#999';

        userLine.appendChild(username);
        userLine.appendChild(timeAgo);

        // 文章标题（带分隔符）
        const titleContainer = document.createElement('div');
        titleContainer.style.display = 'flex';
        titleContainer.style.alignItems = 'center';

        const separator = document.createElement('span');
        separator.textContent = '|';
        separator.style.marginRight = '8px';
        separator.style.color = '#ccc';

        const articleTitle = document.createElement('div');
        articleTitle.textContent = like.title;
        articleTitle.style.fontSize = '14px';
        articleTitle.style.color = '#666';
        articleTitle.style.whiteSpace = 'nowrap';
        articleTitle.style.overflow = 'hidden';
        articleTitle.style.textOverflow = 'ellipsis';

        titleContainer.appendChild(separator);
        titleContainer.appendChild(articleTitle);

        infoContainer.appendChild(userLine);
        infoContainer.appendChild(titleContainer);

        // 3. 封面部分（右）
        const coverContainer = document.createElement('div');
        coverContainer.style.marginLeft = '15px';
        coverContainer.style.cursor = 'pointer';
        coverContainer.onclick = () => goToArticle(like.articleId);

        const articleCover = document.createElement('img');
        articleCover.src = like.cover;
        articleCover.style.width = '80px';
        articleCover.style.height = '60px';
        articleCover.style.objectFit = 'cover';
        articleCover.style.borderRadius = '4px';

        coverContainer.appendChild(articleCover);

        // 组装所有部分
        likeElement.appendChild(avatarContainer);
        likeElement.appendChild(infoContainer);
        likeElement.appendChild(coverContainer);

        container.appendChild(likeElement);
    });
}

function goToArticle(articleId) {
    window.location.href = `/index.jsp?page=article&&id=${articleId}`;
}

// 监听滚动事件
window.addEventListener('scroll', () => {
    // 距离底部200px时加载
    console.log(document.body.offsetHeight);
    
    if ((window.innerHeight + window.scrollY) >= (document.body.offsetHeight - 300)) {
        loadLikes();
    }
});

window.onload = function() {
    loadLikes();
    // 监听 likes 容器的滚动
    const likesContainer = document.getElementById('likes-container');
    likesContainer.addEventListener('scroll', function() {
        if (likesContainer.scrollTop + likesContainer.clientHeight >= likesContainer.scrollHeight - 100) {
            loadLikes();
        }
    });
};
