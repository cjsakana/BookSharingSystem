async function request(url, method = 'GET', requestBody = null, queryParams = null) {
    // 从本地存储获取token
    // const token = localStorage.getItem('Authorization') || '';
    // 从 cookie 里自动携带

    const baseURL = "/api"
    try {
        // 处理查询参数
        let fullUrl = baseURL + url;

        if (queryParams) {
            console.log(111)
            const queryString = new URLSearchParams(queryParams).toString();
            fullUrl += (url.includes('?') ? '&' : '?') + queryString;
        }

        const options = {
            method: method.toUpperCase(),
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': `Bearer ${token}`
            }
        };

        // 如果是GET/HEAD请求，不添加body
        if (requestBody && options.method !== 'GET') {
            options.body = JSON.stringify(requestBody);
        }
        console.log(fullUrl)
        const response = await fetch(fullUrl, options);

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            throw {
                status: response.status,
                statusText: response.statusText,
                ...errorData
            };
        }

        return await response.json();
    } catch (error) {
        console.error('Request failed:', error);
        throw error;
    }
}

export default request