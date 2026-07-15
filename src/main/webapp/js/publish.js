import {apiCreateArticle, apiGetArticleById, apiUpdateArticle} from './request/article.js'

function confirmBack() {
    if (confirm("确定要返回吗？未保存的文章内容将会丢失！")) {
        window.history.back(); // 返回上一页
    }
}

$(function () {
    // 解析 URL 参数
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    let cover = ''

    // 初始化简易Markdown编辑器
    var easyMDE = new EasyMDE({
        element: document.getElementById('markdown-content'),
        autoDownloadFontAwesome: false,
        spellChecker: false,
        toolbar: [
            "bold", "italic", "heading", "|",
            "quote", "unordered-list", "ordered-list", "|",
            "link", "|",
            "preview", "|",
            {
                name: "guide",
                action: function showHelp() {
                    $('#formatting-help').toggle();
                },
                className: "fas fa-question-circle",
                title: "格式帮助"
            }
        ],
        placeholder: "请输入文章正文...",
        minHeight: "300px",
        autosave: {
            enabled: false
        }
    });

    // 如果有id，获取文章详情并赋值到表单
    if (id) {
        apiGetArticleById(id).then(res => {

            const data = res.data.data;
            console.log(typeof data.tag);

            $('#bookName').val(data.bookName || '');
            $('#title').val(data.title || '');
            $('#isbn').val(data.isbn || '');
            $('#tag').val(`${data.tag}` || '');
            easyMDE.value(data.content || '');
            // 如果有封面图片
            if (data.cover) {
                cover = data.cover
                $('.upload-area').html(
                    '<img src="' + data.cover + '" class="img-thumbnail" style="max-height: 200px;">' +
                    '<input type="file" id="image-upload" accept="image/*" style="display:none;">'
                );
            }
        });
    }

    // 图片上传处理
    $('#image-upload').change(function (e) {
        if (e.target.files.length > 0) {
            var file = e.target.files[0];
            // 这里可以添加图片上传逻辑
            console.log('已选择图片:', file);

            // 显示图片预览
            var reader = new FileReader();
            reader.onload = function (event) {
                $('.upload-area').html(
                    '<img src="' + event.target.result + '" class="img-thumbnail" style="max-height: 200px;">' +
                    '<input type="file" id="image-upload" accept="image/*" style="display:none;">' +
                    '<div class="mt-2"><button class="btn btn-sm btn-secondary" onclick="$(\'.upload-area input\').click()">更换图片</button></div>'
                );

                // 重新绑定change事件
                $('.upload-area input').change(function (e) {
                    if (e.target.files.length > 0) {
                        var newFile = e.target.files[0];
                        uploadToServer(newFile);
                    }
                });

                // 上传到服务器
                uploadToServer(file);
            };
            reader.readAsDataURL(file);
        }
    });

    // 上传到服务器的函数
    function uploadToServer(file) {
        var formData = new FormData();
        formData.append('cover', file);  // 修改参数名为'cover'，与后端对应

        $.ajax({
            url: 'http://localhost:9090/api/article/uploadCover',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,  // 使用false，让浏览器自动设置正确的Content-Type
            xhrFields: {
                withCredentials: true
            },
            success: function (response) {
                if (response && response.data.code === 200) {
                    cover = response.data.data
                    console.log('图片上传成功:', cover);

                    $('#cover').val(response.data);
                } else {
                    alert('上传失败：' + (response.msg || '未知错误'));
                }
            },
            error: function (xhr, status, error) {
                alert('服务器错误：' + error);
            }
        });
    }


    // 帮助按钮点击事件
    $('#help-btn').click(function () {
        $('#formatting-help').toggle();
    });

    // 保存按钮点击事件 (isPublished = 0)
    $('#save-btn').click(function () {
        submitForm(0);
    });

    // 提交按钮点击事件 (isPublished = 1)
    $('#submit-btn').click(function () {
        submitForm(1);
    });


    // 表单提交函数
    function submitForm(isPublished) {
        var formData = {
            bookName: $('#bookName').val(),
            title: $('#title').val(),
            cover: cover,
            content: easyMDE.value(),
            tag: parseInt($('#tag').val(), 10),
            isbn: $('#isbn').val(),
            isPublished: isPublished
        };
        // console.log(formData)

        // 验证必填字段
        if (!formData.title) {
            alert('请输入书名');
            return;
        }

        if (!formData.content) {
            alert('请输入正文内容');
            return;
        }


        // 显示加载状态
        var submitBtn = $('#submit-btn');
        var originalText = submitBtn.html();
        submitBtn.prop('disabled', true).html('<i class="fas fa-spinner fa-spin me-1"></i>处理中...');

        if (id) {
            // 更新文章
            apiUpdateArticle(id, formData)
                .then(response => {
                    // 显示成功消息
                    alert(isPublished ? '文章提交成功！' : '文章已保存为草稿！');
                })
                .catch(error => {
                    alert('操作失败: ' + (error.message || '服务器错误'));
                })
                .finally(() => {
                    // 恢复按钮状态
                    submitBtn.prop('disabled', false).html(originalText);
                });
        } else {
            apiCreateArticle(formData)
                .then(response => {
                    // 显示成功消息
                    alert(isPublished ? '文章提交成功！' : '文章已保存为草稿！');
                })
                .catch(error => {
                    alert('操作失败: ' + (error.message || '服务器错误'));
                })
                .finally(() => {
                    // 恢复按钮状态
                    submitBtn.prop('disabled', false).html(originalText);
                });
        }
    }
});