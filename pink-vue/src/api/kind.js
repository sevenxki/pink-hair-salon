import request from "@/utils/request"

// 查询hsKind列表
export function listHsKind(query) {
  return request({
    url: "/hsKind/list",
    method: "get",
    params: query
  })
}

// 分页查询hsKind列表
export function listHsKindByPage(query) {
  return request({
    url: "/hsKind/page",
    method: "get",
    params: query
  })
}

// 查询hsKind
export function getHsKind(id) {
  return request({
    url: "/hsKind/" + id,
    method: "get"
  })
}

// 新增hsKind
export function addHsKind(data) {
  return request({
    url: "/hsKind",
    method: "post",
    data: data
  })
}

// 修改hsKind
export function updateHsKind(data) {
  return request({
    url: "/hsKind",
    method: "put",
    data: data
  })
}

// 删除hsKind
export function deleteHsKind(id) {
  return request({
    url: "/hsKind/delete/" + id,
    method: "delete"
  })
}
