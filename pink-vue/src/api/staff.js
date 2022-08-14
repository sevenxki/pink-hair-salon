import request from '@/utils/request'


// 分页获取所有员工
export function staffListByPage(query) {
    return request({
        url: "/hsSalary/swv",
        method: "get",
        params: query
    })
}

// 只看本月的
export function staffListOnMonth(query) {
    return request({
        url: "/hsSalary/month",
        method: "get",
        params: query
    })
}

// 工资结算
export function payHsStaff(salaryId) {
    return request({
        url: "/hsSalary/settle/" + salaryId,
        method: "get"
    })
}

// 获取所有角色
export function getAllRole() {
    return request({
        url: "/hsStaff/role",
        method: "get"
    })
}

// 获取所有等级
export function getAllLevel() {
    return request({
        url: "/hsStaff/level/2",
        method: "get"
    })
}

// 获取单个员工具体信息
export function getHsStaff(staffId) {
    return request({
        url: "/hsStaff/" + staffId,
        method: "get"
    })
}

// 添加员工
export function addHsStaff(data) {
    return request({
        url: "/hsStaff",
        method: "post",
        data: data
    })
}

// 修改员工
export function updateHsStaff(data) {
    return request({
        url: "/hsStaff",
        method: "put",
        data: data
    })
}

// 删除员工记录
export function deleteHsStaff(id) {
    return request({
        url: "/hsSalary/delete/" + id,
        method: "delete"
    })
}

// 删除该员工
export function deleteHsStaffAll(id) {
    return request({
        url: "/hsStaff/delete/" + id,
        method: "delete"
    })
}

// 发放奖励
export function getReward(data) {
    return request({
        url: "/hsBonusDeduction/add",
        method: "get",
        params: data
    })
}

// 扣除工资
export function getDedute(data) {
    return request({
        url: "/hsBonusDeduction/sub",
        method: "get",
        params: data
    })
}