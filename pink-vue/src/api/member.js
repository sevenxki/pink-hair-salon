import request from "@/utils/request";

// 查询hsVip列表
export function listHsVip(query) {
  return request({
    url: "/hsVip/list",
    method: "get",
    params: query
  })
}

// 分页查询hsVip列表
export function listHsVipByPage(query) {
  return request({
    url: "/hsVip/page",
    method: "get",
    params: query
  })
}

// 通过联系方式查询hsVip信息
export function listHsVipByPhone(phone) {
  return request({
    url: "/hsVip/queryAllByPhone/" + phone,
    method: "get",
  })
}

// 新增hsVip
export function addHsVip(data) {
  return request({
    url: "/hsVip/addNewVip",
    method: "post",
    data: data
  })
}

// 修改hsVip
export function updateHsVip(data) {
  return request({
    url: "/hsVip/edit",
    method: "put",
    data: data
  })
}

// 删除hsVip
export function deleteHsVip(phone) {
  return request({
    url: "/hsVip/deleteVip/" + phone,
    method: "delete"
  })
}

// 充值
export function investHsVip(data) {
  return request({
    url: "/hsVip/recharge",
    method: "post",
    data: data
  })
}

// 团购
export function discountHsVip(data) {
  return request({
    url: "/hsVip/group",
    method: "post",
    data: data
  })
}


