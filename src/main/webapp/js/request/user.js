import request from "./base.js";

const prefixURL = "/user"
const login = (data) => {
    return request(prefixURL + "/login", "POST", data)
}

export {
    login
}