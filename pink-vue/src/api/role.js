import request from "@/utils/request"

// 分页查询
export function listHsRoleByPage(query) {
  return request({
    url: "/hsRole/page",
    method: "get",
    params: query
  })
}

// 查询角色
export function getHsRole(id) {
  return request({
    url: "/hsRole/" + id,
    method: "get"
  })
}

// 新增角色
export function addHsRole(data) {
  return request({
    url: "/hsRole",
    method: "post",
    data: data
  })
}

// 修改角色
export function updateHsRole(data) {
  return request({
    url: "/hsRole",
    method: "put",
    data: data
  })
}

// 删除角色
export function deleteHsRole(id) {
  return request({
    url: "/hsRole/delete/" + id,
    method: "delete"
  })
}