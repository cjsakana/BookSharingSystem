import request from "./base.js";

const findArticle = (queryParams) => {
    console.log(queryParams)
    return request("/article", "GET", null, queryParams)
}

const getArticleById = (id) => {
    return request("/" + id, "GET")
}

const createArticle= (requestBody) => {
    console.log(queryParams)
    return request("", "POST", requestBody)
}

export {
    findArticle,
    getArticleById,
    createArticle
}