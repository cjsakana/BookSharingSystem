// ... existing code ...
import { apiFindArticle,apiArticleUnpublish } from '../request/article.js';

function renderArticleTable(articleList) {
    const tbody = document.getElementById('article-table-body');
    tbody.innerHTML = '';
    articleList.forEach(article => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td style="padding:10px 8px;text-align:center;">${article.id}</td>
        <td style="padding:10px 8px;text-align:center;">${article.title}</td>
        <td style="padding:10px 8px;text-align:center;">${article.username}</td>
        <td style="padding:10px 8px;text-align:center;">${article.updatedAt}</td>
        <td style="padding:10px 8px;text-align:center;">
          <button style="background:#ff2442;color:#fff;border:none;border-radius:8px;padding:4px 12px;cursor:pointer;" onclick="deleteArticle(${article.id})">删除</button>
        </td>
      `;
      tbody.appendChild(tr);
    });
}

function loadArticleList() {
    apiFindArticle({}).then(res => {

      renderArticleTable(res);
    });
}

function deleteArticle(articleId) {
    if(confirm('确定要删除该文章吗？')) {
        apiArticleUnpublish(articleId).then(() => {
        loadArticleList();
      });
    }
}

// 页面加载时获取文章列表
window.onload = loadArticleList;
window.deleteArticle = deleteArticle;