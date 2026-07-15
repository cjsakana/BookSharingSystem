import {apiGetArticleById} from "./request/article.js";
import {apiLike,apiUnliike,apiIsLiked} from "./request/likes.js";

$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    // 文章id
    const id = urlParams.get('id');

    let articleData={}
    // 点赞
    let isLiked = false;
    let likeCount = articleData.likes;


    apiGetArticleById(id).then(res=>{
        console.log(res);
        
        articleData=res.data.data
        // 渲染文章数据
        $('#articleCover').attr('src', articleData.cover);
        $('#articleTitle').text(articleData.title);
        $('#authorAvatar').attr('src', articleData.userAvatar);
        $('#authorName').text(articleData.username);
        $('#bookName').text('书名: '+articleData.bookName);
        $('#isbn').text('ISBN号: '+articleData.isbn);
        $('#articleTags').text('标签: ' + articleData.tagName);
        $('#likeCount').text(articleData.likes + '人赞同了该文章');

        // 使用marked解析Markdown
        $('#articleContent').html(marked.parse(articleData.content));

        likeCount = articleData.likes;
    })



    // 高亮代码块
    // document.querySelectorAll('pre code').forEach((block) => {
    //     hljs.highlightElement(block);
    // });


    apiIsLiked({articleId:id}).then(res=>{
        console.log(res);
        
        isLiked = res.data.data
        if (isLiked) {
            $('#likeBtn').addClass('liked').text('👍 已点赞');
        } else {
            $('#likeBtn').removeClass('liked').text('👍 点赞');
        }
    })
    $('#likeBtn').click(function () {
        isLiked = !isLiked;

        if (isLiked) {
            likeCount++;
            apiLike(id)
            $(this).addClass('liked').text('👍 已点赞');
        } else {
            likeCount--;
            apiUnliike(id)
            $(this).removeClass('liked').text('👍 点赞');
        }

        $('#likeCount').text(likeCount + '人赞同了该文章');

    });
});