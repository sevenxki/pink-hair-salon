import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

// 新增roles属性
// 添加set_roles的函数
// 对于getInfo的action在函数中需要获取后台传递过来的roles参数


const getDefaultState = () => {
  return {
    token: getToken(),
    name: localStorage.getItem('name') ? JSON.parse(localStorage.getItem('name')) : '',
    staffName: localStorage.getItem('staffName') ? JSON.parse(localStorage.getItem('staffName')) : '',
    avatar: localStorage.getItem('avatar') ? JSON.parse(localStorage.getItem('avatar')) : '',
    roles: localStorage.getItem('roles') ? JSON.parse(localStorage.getItem('roles')) : []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_STAFFNAME: (state, staffName) => {
    state.staffName = staffName
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response;
        if (!data) {
          return reject('验证失败，请重新登录！')
        }
        const { name, staffName, roles, avatar } = data
        localStorage.setItem('name', JSON.stringify(name))
        localStorage.setItem('staffName', JSON.stringify(staffName) || "")
        localStorage.setItem('roles', JSON.stringify(roles))
        localStorage.setItem('avatar', JSON.stringify(avatar))
        if (!roles || roles.length <= 0) {
          reject('您不是有效的身份！')
        }
        // 将页面级的操作权限存在localStorage里
        if (roles.includes('店长')) {
          localStorage.setItem('permissions', JSON.stringify(['add', 'editAndDelete']));
        } else if (roles.includes('收银员') || roles.includes('技师') || roles.includes('发型师')) {
          localStorage.setItem('permissions', JSON.stringify([]));
        }
        commit('SET_NAME', name)
        commit('SET_STAFFNAME', staffName)
        commit('SET_ROLES', roles)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        localStorage.removeItem('permissions')
        localStorage.removeItem('name')
        localStorage.removeItem('staffName')
        localStorage.removeItem('roles')
        localStorage.removeItem('avatar')
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      localStorage.removeItem('permissions')
      localStorage.removeItem('name')
      localStorage.removeItem('staffName')
      localStorage.removeItem('roles')
      localStorage.removeItem('avatar')
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

