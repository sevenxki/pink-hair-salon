export default {
    bind(el, bindings) {
        // 获取权限
        const permissions = localStorage.getItem('permissions') && JSON.parse(localStorage.getItem('permissions'));
        // 进行权限的判断
        const needPermission = bindings.value;
        const hasPermission = permissions.includes(needPermission)
        // 如果没有权限的话
        if (!hasPermission) {
            // 直接移除
            setTimeout(() => {
                el.parentNode.removeChild(el);
            }, 0)
        }
    }
}