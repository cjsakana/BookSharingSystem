async function request(url, method = 'GET', requestBody = null, queryParams = null) {
    // 从本地存储获取token
    // const token = localStorage.getItem('Authorization') || '';
    // 从 cookie 里自动携带

    const baseURL = "/api"
    try {
        // 处理查询参数
        let fullUrl = baseURL + url;

        if (queryParams) {
            const queryString = new URLSearchParams(queryParams).toString();
            fullUrl += (url.includes('?') ? '&' : '?') + queryString;
        }

        const options = {
            method: method.toUpperCase(),
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': `Bearer ${token}`
            },
            credentials: 'include' 
        };

        // 如果是GET/HEAD请求，不添加body
        if (requestBody && options.method !== 'GET') {
            options.body = JSON.stringify(requestBody);
        }
        console.log(fullUrl)
        
        // 创建 AbortController 用于超时控制
        const controller = new AbortController();
        const timeoutId = setTimeout(() => controller.abort(), 10000); // 10秒超时

        // 添加 signal 到 options
        options.signal = controller.signal;

        const response = await fetch(fullUrl, options);
        clearTimeout(timeoutId); // 清除超时计时器

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
        // 处理超时错误
        if (error.name === 'AbortError') {
            throw new Error('请求超时，请稍后重试');
        }
        throw error;
    }
}

export default request