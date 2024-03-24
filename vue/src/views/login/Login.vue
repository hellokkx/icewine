<template>

  <div class="c1" style="height: 100vh;overflow: hidden;position: relative">

    <el-card class="cover" v-if="loginAdmin.id">
      <slide-verify :l="42"
                    :r="10"
                    :w="310"
                    :h="155"
                    :accuracy="6"
                    slider-text="向右滑动"
                    @success="onSuccess"
                    @fail="onFail"
                    @refresh="onRefresh"
      ></slide-verify>
    </el-card>

    <div style="width: 400px;height: 400px;background-color:white;
    border-radius:10px;margin:150px auto; margin-right:650px; padding: 40px 40px">
      <div style="margin-top: 30px; margin-bottom:35px; text-align: center;
      font-size: 30px;font-weight: bold;color: #dc6926">
        登录
      </div>

      <el-form :model="admin" :rules="rules" ref="loginForm">
        <el-form-item prop="name">
          <el-input placeholder="请输入账号" prefix-icon="el-icon-user" size="medium" v-model="admin.name"></el-input>
        </el-form-item>

        <el-form-item prop="pwd">
          <el-input placeholder="请输入密码" show-password style="margin-top:5px" prefix-icon="el-icon-lock" size="medium" v-model="admin.pwd"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 100%;margin-top:10px" size="medium" type="warning" plain @click="login">登录</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie';

export default {
  name: "Login",
  data(){
    return{
      admin:{},
      loginAdmin:{},
      rules: {
        name: [
          {required: true, message: '请输入员工姓名', trigger: 'blur'},
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'},

        ],
      }
    }
  },
  methods:{
    login(){
      this.$refs['loginForm'].validate((valid) => {

        if(valid){
          request.post('/admin/login',this.admin).then(res =>{
            if(res.code==='200'){
              this.loginAdmin=res.data
            }
            else{
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },
    //-------------------滑块--------------------
    onSuccess(){
      this.$notify.success("登录成功")
      if(this.loginAdmin!==null){
        Cookies.set('admin',JSON.stringify(this.loginAdmin))
      }
      if(this.loginAdmin.prior!=="1") {
        this.$router.push('/')
      }
      else{
        this.$router.push('/ad')
      }
    },
    onFail(){
      this.msg = ''
    },
    onRefresh(){
      console.log('refresh')
    }
  }
}
</script>

<style scoped>

  .c1{
    background-image: url("winebgc.jpg");
    /* 背景图垂直、水平均居中 */
    background-position: center center;
    /* 背景图不平铺 */
    background-repeat: no-repeat;
    /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
    /*background-attachment: fixed;*/
    /* 让背景图基于容器大小伸缩 */
    background-size: cover;
    /*background-color: #ecf5ff;*/
  }
  .cover{
    width: fit-content;
    background-color: white;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    z-index: 1000;
  }
</style>