import request from "./base.js";

const prefixURL = "/user"
const login = (data) => {
    return request(prefixURL + "/login", "POST", data)
}

const register = (data)=>{
    return request(prefixURL+"/register","POST",data)
}

const updateUserInfo = (data)=>{
    return request(prefixURL,"PUT",data)
}
const updatePassword = (data)=>{
    return request(prefixURL+"/ResetPassword","PUT",data)
}
const logout = ()=>{
    return request(prefixURL,"DELETE")
}
const getUserInfo = ()=>{
    return request(prefixURL,"GET")
}

const apiAdminLogin = (data) => {
    return request(prefixURL + "/admin/login", "POST", data)
}
const apiOnlineCount = ()=>{
    return request(prefixURL+"/onlineCount","GET")
}

const apiGetUserAll = (query)=>{
    return request(prefixURL+"/all","GET",null,query)
}

export {
    login,
    register,
    updateUserInfo,
    updatePassword,
    logout,
    getUserInfo,
    apiAdminLogin,
    apiOnlineCount,
    apiGetUserAll
}