import request from "@/utils/request";

// 获取所有待处理订单
export function queryAllNoPayment() {
  return request({
    url: "/hsOrder/noPayment",
    method: "get",
  })
}

// 获取单条数据
export function getHsOrder(id) {
  return request({
    url: "/hsOrder/find/" + id,
    method: "get",
  })
}

// 修改待处理订单
export function updateOrder(data) {
  return request({
    url: "/hsOrder",
    method: "put",
    data: data
  })
}

// 获取全部具体项目
export function queryAllProjectByKind(kindName) {
  return request({
    url: "/hsOrder/qPA/" + kindName,
    method: "get",
  })
}

// 获取护理
export function queryAllHairCare() {
  return request({
    url: "/hsOrder/aHairCare",
    method: "get",
  })
}

// 获取发型师
export function queryAllHDByProject(projectName) {
  return request({
    url: "/hsOrder/qb/" + projectName,
    method: "get",
  })
}

// 获取技师
export function queryTechnician() {
  return request({
    url: "/hsOrder/allQueryTechnician/",
    method: "get",
  })
}

// 根据手机号码获得可选优惠
export function queryAllCheapByTelephone(query) {
  return request({
    url: "/hsPaymentOrder/vo",
    method: "get",
    params: query
  })
}

// 根据订单号获得账单
export function queryPaymentByOrderId(orderId) {
  return request({
    url: "/hsPaymentOrder/add/" + orderId,
    method: "get",
  })
}

// 根据优惠方案ID和账单号更新总金额
export function getNewPrizeByDiscountId(query) {
  return request({
    url: "/hsPaymentOrder/dt",
    method: "get",
    params: query
  })
}

// 确定支付
export function payment(query) {
  return request({
    url: "/hsPaymentOrder/payMethod",
    method: "get",
    params: query
  })
}

// 删除订单
export function deleteByOrderId(id) {
  return request({
    url: "/hsOrder/delete/" + id,
    method: "delete"
  })
}