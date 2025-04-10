import request from "./base.js";

const findArticle = (queryParams) => {
    console.log(queryParams)
    return request("/article", "GET", null, queryParams)
}

export {
    findArticle
}