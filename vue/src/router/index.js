import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Layout from '../views/Layout.vue'
import Cookies from "js-cookie";


Vue.use(VueRouter)

const routes = [
    //----------------------1_Login-------------------------------
  {
    path: '/login',
    name: 'Login',
    component: ()=>import('@/views/login/Login.vue'),
  },
    //------------------------1_Layout-------------------------
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/home',
    children:[
      //------------------------2_HOME-------------------------
      {path: 'home', name: 'Home', component: HomeView},
      // ---------------------2_User-----------------------------
      {path: 'userList', name: 'UserList', component: () => import('@/views/user/User.vue')},
      {path: 'addUser', name: 'AddUser', component: () => import('@/views/user/AddUser.vue')},
      {path: 'editUser', name: 'EditUser', component: () => import('@/views/user/EditUser.vue')},

      // ---------------------2_Admin-----------------------------
      {path: 'adminList', name: 'AdminList', component: () => import('@/views/admin/List.vue')},
      {path: 'addAdmin', name: 'AddAdmin', component: () => import('@/views/admin/Add.vue')},
      {path: 'editAdmin', name: 'EditAdmin', component: () => import('@/views/admin/Edit.vue')},

      // ---------------------2_Order-----------------------------
      {path: 'orderList', name: 'OrderList', component: () => import('@/views/order/List.vue')},
      {path: 'addOrder', name: 'AddOrder', component: () => import('@/views/order/Add.vue')},
      {path: 'editOrder', name: 'EditOrder', component: () => import('@/views/order/Edit.vue')},
      {path: 'detail', name: 'Detail', component: () => import('@/views/order/Detail.vue')},

      // ---------------------2_Wine-----------------------------
      {path: 'wineList', name: 'WineList', component: () => import('@/views/wine/List.vue')},
      {path: 'addWine', name: 'AddWine', component: () => import('@/views/wine/Add.vue')},
      {path: 'editWine', name: 'EditWine', component: () => import('@/views/wine/Edit.vue')},

      // ---------------------2_Borrow-----------------------------
      {path: 'data', name: 'DataList', component: () => import('@/views/Data.vue')},

    ]
  },
  //------------------------1_Layout-------------------------
  {
    path: '/ad',
    name: 'Layout1',
    component: ()=>import('@/views/Layout1.vue'),
    redirect:'/home',
    children:[
      //------------------------2_HOME-------------------------
      {path: 'home', name: 'Home', component: HomeView},
      // ---------------------2_User-----------------------------
      {path: 'userList', name: 'UserList', component: () => import('@/views/user/User.vue')},
      {path: 'addUser', name: 'AddUser', component: () => import('@/views/user/AddUser.vue')},
      {path: 'editUser', name: 'EditUser', component: () => import('@/views/user/EditUser.vue')},


      // ---------------------2_Order-----------------------------
      {path: 'orderList', name: 'OrderList', component: () => import('@/views/order/List.vue')},
      {path: 'addOrder', name: 'AddOrder', component: () => import('@/views/order/Add.vue')},
      {path: 'editOrder', name: 'EditOrder', component: () => import('@/views/order/Edit.vue')},
      {path: 'detail', name: 'Detail', component: () => import('@/views/order/Detail.vue')},

      // ---------------------2_Wine-----------------------------
      {path: 'wineList', name: 'WineList', component: () => import('@/views/wine/List.vue')},
      {path: 'addWine', name: 'AddWine', component: () => import('@/views/wine/Add.vue')},
      {path: 'editWine', name: 'EditWine', component: () => import('@/views/wine/Edit.vue')},

      // ---------------------2_Borrow-----------------------------
      {path: 'data', name: 'DataList', component: () => import('@/views/Data.vue')},

    ]
  },
  {
    path:"*",
    component:()=>import('@/views/404.vue')
  }

]



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  if(to.path==='/login') next()
  // const admin=Cookies.get("admin")
  // if(!admin && to.pa th!=='/login') return next("/login") //强制退回到登录页面
  next()
})



export default router
