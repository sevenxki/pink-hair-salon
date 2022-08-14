import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/register'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      // 在addRoutes()之后第一次访问被添加的路由会白屏，这是因为刚刚addRoutes()就立刻访问被添加的路由
      // 然而此时addRoutes()没有执行结束，因而找不到刚刚被添加的路由导致白屏。因此需要从新访问一次路由才行。
      // 此时就要使用next({ …to, replace: true })来确保addRoutes()时动态添加的路由已经被完全加载上去。
      // next({ …to, replace: true })中的replace: true只是一个设置信息，告诉VUE本次操作后，不能通过浏览器后退按钮，返回前一个路由。

      // 通过getInfo判断用户是否获得了权限角色
      const hasRoles = store.getters.roles && store.getters.roles.length > 0;
      if (hasRoles) {
        // 如果是刷新
        if (store.state.permission.routes.length == 0) {
          // 根据角色生成可访问的路由
          const accessRoutess = await store.dispatch('permission/generateRoutes', store.getters.roles);
          // 动态添加路由
          router.$addRoutes(accessRoutess);
          // 404页面重定向必须放在最后面！否则一刷新和输入地址 就出问题跳404页面！
          // 因为addRoutes动态添加路由还没完成，页面就从静态找了。
          // 解决：当动态路由表添加进去路由以后，再将404路由也动态添加进去！
          router.options.routes.push({ path: '*', redirect: '/404', hidden: true })
          next({ ...to, replace: true });
        } else {
          next()
        }
      } else {
        try {
          // get user info
          // 注意：roles必须是数组形式，如['admin'] or ,['developer','editor']
          const { roles } = await store.dispatch('user/getInfo')
          // 根据角色生成可访问的路由
          const accessRoutes = await store.dispatch('permission/generateRoutes', roles);
          // 动态添加路由
          router.$addRoutes(accessRoutes);
          // 404页面重定向必须放在最后面！否则一刷新和输入地址 就出问题跳404页面！
          // 因为addRoutes动态添加路由还没完成，页面就从静态找了。
          // 解决：当动态路由表添加进去路由以后，再将404路由也动态添加进去！
          router.options.routes.push({ path: '*', redirect: '/404', hidden: true })

          // 确保addRoutes完成
          // 设置替换为true 这样导航不会留下历史记录
          next({ ...to, replace: true });
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }

})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
