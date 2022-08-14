import request from "@/utils/request"

// 分页查询历史订单列表
export function listHsHistoryByPage(query) {
  return request({
    url: "/hsPaymentOrder/page",
    method: "get",
    params: query
  })
}

// 删除历史订单
export function deleteHsHistory(id) {
  return request({
    url: "/hsPaymentOrder/delete/" + id,
    method: "delete"
  })
}
