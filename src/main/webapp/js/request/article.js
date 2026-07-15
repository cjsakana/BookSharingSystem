import request from "./base.js";

const prefixURL = "/article"

const apiFindArticle = (queryParams) => {
    return request(prefixURL, "GET", null, queryParams)
}

const apiFindUserArticle = (queryParams) => {
    return request(prefixURL+"/getUserArticle", "GET", null, queryParams)
}

const apiGetArticleById = (id) => {
    return request(prefixURL + `/${id}`, "GET")
}

const apiCreateArticle= (requestBody) => {
    return request(prefixURL, "POST", requestBody)
}

const apiUpdateArticle= (id,requestBody) => {
    return request(prefixURL+`/${id}`, "PUT", requestBody)
}

const apiRecommendArticle= () => {
    return request(prefixURL+`/recommend`, "GET")
}

const apiArticleUnpublish= (articleId) => {
    return request(prefixURL+`/${articleId}/unpublish`, "PUT")
}
const apiArticleDel= (articleId) => {
    return request(prefixURL+`/${articleId}`, "DELETE")
}

export {
    apiFindArticle,
    apiGetArticleById,
    apiCreateArticle,
    apiFindUserArticle,
    apiUpdateArticle,
    apiRecommendArticle,
    apiArticleUnpublish,
    apiArticleDel
}