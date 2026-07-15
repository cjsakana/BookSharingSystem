import request from "./base.js";

const prefixURL = "/user"
const login = (data) => {
    return request(prefixURL + "/login", "POST", data)
}

const register = (data)=>{
    return request(prefixURL+"/register","POST",data)
}

export {
    login,
    register
}