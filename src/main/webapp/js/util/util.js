const getTagName=(tagId)=> {
    const tags = {
        1: '情感',
        2: '成长',
        3: '社会',
        4: '心理',
        5: '科幻',
        6: '哲学',
        7: '历史',
        8: '其他'
    };
    return tags[tagId] || '其他';
}

// 格式化时间戳
const formatDate=(timestamp)=> {
    if (!timestamp) return '';
    const date = new Date(timestamp);
    return date.toLocaleDateString();
}

export {
    getTagName,
    formatDate
}