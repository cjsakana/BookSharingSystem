import request from "./base.js";

const prefixURL = "/likes"

const apiLike = (requestBody) => {
    return request(prefixURL+"/like", "POST",  requestBody)
}

const apiUnliike = (requestBody) => {
    return request(prefixURL+"/unlike", "POST", requestBody)
}

const apiGetUserCount = (userid) => {
    return request(prefixURL+`/user/${userid}/count`, "GET")
}
const apiGetArticleCount = (articleId) => {
    return request(prefixURL+`/article/${articleId}/count`, "GET")
}
const apiIsLiked = (queryParams) => {
    return request(prefixURL+`/isLiked`, "GET",null,queryParams)
}
const apiList = (queryParams) => {
    return request(prefixURL+`/list`, "GET",null,queryParams)
}

export {
    apiLike,
    apiUnliike,
    apiGetUserCount,
    apiGetArticleCount,
    apiIsLiked,
    apiList
}