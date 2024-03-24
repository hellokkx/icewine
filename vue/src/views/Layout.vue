<template>
  <div>
    <!--头部区域-->
    <div style="height: 70px;line-height: 70px;background-color:white;margin-bottom: 2px;display: flex">

      <div style="width: 500px">
        <img src="@/assets/wine.jpg" alt="" style="width: 40px;margin-left: 30px;position: relative;top: 10px;left:20px">
        <span style="margin-left: 40px; font-size: 24px">冰酒销售管理系统</span>
      </div>

      <div style="flex: 1;text-align: right;margin-right: 20px">
        <el-dropdown size="medium">
          <span class="el-dropdown-link" style="cursor: pointer">
            {{ admin.name }}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top:-5px;">
            <el-dropdown-item>
              <div style="width: 50px;text-align: center;" @click="logout">
                退出
              </div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>


      </div>




    </div>

    <!--侧边栏和主体-->
    <div style="display: flex">
      <!--侧边栏-->
      <div
          style="width: 200px;min-height:calc(100vh - 62px); overflow: hidden;margin-right: 2px;background-color:white;">
        <el-menu :default-active="$route.path" router class="el-menu-demo">
          <!--通过路由router的方式进行页面切换-->
          <el-menu-item index="/home">
            <i class="el-icon-pie-chart"></i>
            <span>首页</span>
          </el-menu-item>

          <!--          user-->
          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/addUser">用户添加</el-menu-item>
            <el-menu-item index="/userList">用户列表</el-menu-item>
          </el-submenu>

          <!--          admin-->
          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-office-building"></i>
              <span>员工管理</span>
            </template>
            <el-menu-item index="/addAdmin">员工添加</el-menu-item>
            <el-menu-item index="/adminList">员工列表</el-menu-item>
          </el-submenu>

          <!--          book-->
          <el-submenu index="book">
            <template slot="title">
              <i class="el-icon-cold-drink"></i>
              <span>冰酒管理</span>
            </template>
            <el-menu-item index="/addWine">冰酒添加</el-menu-item>
            <el-menu-item index="/wineList">冰酒列表</el-menu-item>
          </el-submenu>

          <!--          category-->
          <el-submenu index="category">
            <template slot="title">
              <i class="el-icon-shopping-cart-1"></i>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/addOrder">订单添加</el-menu-item>
            <el-menu-item index="/orderList">订单列表</el-menu-item>
          </el-submenu>


          <!--          borrow-->
          <el-menu-item index="/data">
            <i class="el-icon-data-analysis"></i>数据</el-menu-item>

        </el-menu>


      </div>

      <!--主体-->
      <div style="flex: 1;background-color:white;background-color:white;padding: 10px;">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>

import Cookies from 'js-cookie';

export default {
  name: "Layout",
  data(){
    return{
      admin:Cookies.get('admin') ? JSON.parse(Cookies.get('admin')):{}
    }
  },
  methods:{
    logout(){
      //清除浏览器用户数据
      Cookies.remove('admin')
      this.$router.push('/login')
    },
  }
}
</script>

<style scoped>

</style>