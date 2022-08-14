import request from "@/utils/request";

// 新增hsOrder
export function addHsOrder(data) {
  return request({
    url: "/hsOrder/placeOrder",
    method: "post",
    data: data
  })
}
// 通过类别查询hsProject列表
export function listHsProjectByKind(kindId) {
  return request({
    url: "/hsProject/" + kindId,
    method: "get",
  })
}
// 获取hsKind的name
export function queryAllKind() {
  return request({
    url: "/hsOrder/allKind",
    method: "get",
  })
}
// 获取hsProject的name
export function queryAllProjectNameByKind(kindName) {
  return request({
    url: "/hsOrder/queryProject/" + kindName,
    method: "get",
  })
}

// 获取护理的name
export function queryAllHairCareName() {
  return request({
    url: "/hsOrder/hairCare",
    method: "get",
  })
}

// 获取发型师的name
export function queryAllHDNameByProject(projectName) {
  return request({
    url: "/hsOrder/queryStaffByLevel/" + projectName,
    method: "get",
  })
}

// 获取技师的name
export function queryTechnician() {
  return request({
    url: "/hsOrder/queryTechnician/",
    method: "get",
  })
}

// 获取订单总价
export function getPrice(orderList) {
  return request({
    url: "/hsOrder/countTotal",
    method: "post",
    data: orderList
  })
}

// 获得饼状图数据
export function getPieData() {
  return request({
    url: '/hsOrder/pie',
    method: 'get'
  })
}
