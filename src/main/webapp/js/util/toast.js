/**
 * Toast 轻提示组件
 * 功能：显示短暂提示信息，2.5秒后自动消失
 * 使用方式：
 *   Toast.show('普通提示')
 *   Toast.err('错误提示')  // 红色文字
 */

// 创建样式
const style = document.createElement('style');
style.textContent = `
  .toast-message {
    position: fixed;
    top: 20px;
    left: 50%;
    transform: translateX(-50%);
    z-index: 9999;
    padding: 12px 24px;
    border-radius: 4px;
    background-color: rgba(255, 255, 255, 0.8);
    color: blue;
    font-size: 14px;
    animation: fadeInOut 2.5s ease forwards;
    box-shadow: 0 2px 10px rgba(0,0,0,0.2);
  }
  
  .toast-message.err {
    color: red;
  }

  @keyframes fadeInOut {
    0% { opacity: 0; top: 0; }
    10% { opacity: 1; top: 20px; }
    90% { opacity: 1; top: 20px; }
    100% { opacity: 0; top: 0; }
  }
`;
document.head.appendChild(style);

// Toast 对象
const Toast = {
    /**
     显示普通Toast提示
     * @param {string} message 提示内容
     */
    show: function (message) {
        this._showToast(message);
    },

    /**
     * 显示错误Toast提示（红色文字）
     * @param {string} message 提示内容
     */
    err: function (message) {
        this._showToast(message, true);
    },

    /**
     * 内部方法：显示Toast
     * @param {string} message 提示内容
     * @param {boolean} isError 是否为错误提示
     */
    _showToast: function (message, isError = false) {
        // 创建弹窗元素
        const toast = document.createElement('div');
        toast.className = 'toast-message' + (isError ? ' err' : '');
        toast.textContent = message;

        // 添加到body
        document.body.appendChild(toast);

        // 动画结束后自动移除
        setTimeout(() => {
            toast.remove();
        }, 2500);
    }
};

// 导出Toast对象
export default Toast;