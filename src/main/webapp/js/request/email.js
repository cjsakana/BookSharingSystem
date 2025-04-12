import request from "./base.js";

const prefixURL = "/email"

const sendVerifiedCode = (data) => {
    return request(prefixURL + "/sendVerifiedCode", "POST", data)
}

export {
    sendVerifiedCode
}