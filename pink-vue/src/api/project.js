import request from "@/utils/request"

// 查询hsProject列表
export function listHsProject(query) {
  return request({
    url: "/hsProject/list",
    method: "get",
    params: query
  })
}

// 分页查询hsProject列表
export function listHsProjectByPage(query) {
  return request({
    url: "/hsProject/page",
    method: "get",
    params: query
  })
}

// 通过金额查询hsProject
export function getHsProjectByPrice(price) {
  return request({
    url: "/hsProject/" + price,
    method: "get"
  })
}


// 查询hsProject
export function getHsProject(id) {
  return request({
    url: "/hsProject/" + id,
    method: "get"
  })
}

// 新增hsProject
export function addHsProject(data) {
  return request({
    url: "/hsProject",
    method: "post",
    data: data
  })
}

// 修改hsProject
export function updateHsProject(data) {
  return request({
    url: "/hsProject",
    method: "put",
    data: data
  })
}

// 删除hsProject
export function deleteHsProject(id) {
  return request({
    url: "/hsProject/delete/" + id,
    method: "delete"
  })
}
