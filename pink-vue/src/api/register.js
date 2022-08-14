import request from "@/utils/request"

// 注册新用户
export function registerNew(data) {
  return request({
    url: "/auth/register",
    method: "get",
    params: data
  })
}

// 查询用户名是否已被使用
export function userRules(username) {
  return request({
    url: "/auth/username/" + username,
    method: "get"
  })
}
