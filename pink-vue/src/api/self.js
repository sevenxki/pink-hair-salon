import request from "@/utils/request"

export function getMyself(username) {
  return request({
    url: "/hsUser/tk",
    method: "get",
    params: { username }
  })
}

export function updateSelf(data) {
  return request({
    url: "/hsUser",
    method: "put",
    data: data
  })
}

// 验证身份
export function authentication(data) {
  return request({
    url: "/hsUser/validated",
    method: "get",
    params: data
  })
}

// 修改密码
export function updatePassword(data) {
  return request({
    url: "/hsUser/up",
    method: "get",
    params: data
  })
}