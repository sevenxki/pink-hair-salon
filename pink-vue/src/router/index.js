import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/register',
    component: () => import('@/views/register/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '店铺概况', icon: 'dashboard' }
    }]
  },

  {
    path: '/base',
    component: Layout,
    redirect: '/base/kind',
    name: 'Base',
    meta: { title: '基础数据', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'kind',
        name: 'Kind',
        component: () => import('@/views/kind/index'),
        meta: { title: '项目类别', icon: 'table' }
      },
      {
        path: 'project',
        name: 'Project',
        component: () => import('@/views/project/index'),
        meta: { title: '项目管理', icon: 'el-icon-scissors' }
      },

      {
        path: 'history',
        name: 'History',
        component: () => import('@/views/history/index'),
        meta: { title: '历史订单', icon: 'tree' }
      }
    ]
  },
]

// 异步路由：需要进行角色的判断
//asyncRoutes 是动态路由，需要动态权限判断
export const asyncRoutes = [
  {
    path: '/service',
    component: Layout,
    redirect: '/service',
    name: 'Service',
    meta: { title: '业务管理', icon: 'el-icon-monitor', roles: ['店长', '收银员'] },
    children: [
      {
        path: 'order',
        name: 'Order',
        component: () => import('@/views/order/index'),
        meta: { title: '项目预约', icon: 'el-icon-s-order', roles: ['店长', '收银员'] }
      },
      {
        path: 'checkout',
        name: 'Checkout',
        component: () => import('@/views/checkout/index'),
        meta: { title: '前台收银', icon: 'el-icon-wallet', roles: ['店长', '收银员'] }
      }
    ]
  },

  {
    path: '/',
    component: Layout,
    children: [
      {
        path: 'member',
        name: 'Member ',
        component: () => import('@/views/member/index'),
        meta: { title: '会员管理', icon: 'el-icon-s-custom', roles: ['店长', '收银员'] }
      }
    ]
  },
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: 'role',
        name: 'Role ',
        component: () => import('@/views/role/index'),
        meta: { title: '角色管理', icon: 'el-icon-user', roles: ['店长'] }
      }
    ]
  },
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: 'staff',
        name: 'Staff ',
        component: () => import('@/views/staff/index'),
        meta: { title: '员工管理', icon: 'form', roles: ['店长'] }
      }
    ]
  },
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: 'self',
        name: 'Self ',
        component: () => import('@/views/self/index'),
        meta: { title: '个人中心', icon: 'form', roles: ['店长', '收银员', '发型师', '技师'] }
      }
    ]
  },
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// 解决路由命名重复的问题
router.$addRoutes = params => {
  router.matcher = new Router({
    routes: router.options.routes
　　// 关键代码
  }).matcher;
  router.addRoutes(params);
};

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
