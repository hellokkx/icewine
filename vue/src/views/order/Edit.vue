<template>
  <div style="width:500px">
    <div style="margin-left: 30px">
      <h3 style="margin-left:10px; margin-bottom: 30px">编辑订单</h3>
      <div>

        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
          <el-form-item label="订单编号" prop="oid">
            <el-input v-model="form.oid" disabled placeholder="请输入订单编号" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

          <el-form-item label="状态" prop="status">
            <el-select v-model="form.status" placeholder="请选择是否上架" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in statusoption"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="配送/支付" prop="pay">
            <el-input v-model="form.pay" placeholder="请输入配送/支付情况" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

          <el-form-item label="用户编号" prop="uid">
            <el-input v-model="form.uid" disabled placeholder="请输入用户编号" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

          <el-form-item label="总价格" prop="price">
            <el-input v-model="form.price" placeholder="请输入总价格" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>


        </el-form>

      </div>
    </div>

    <div style="margin-left: 80px;margin-top: 20px">
      <el-button type="primary" @click="save" style="margin-right: 10px">修改</el-button>
      <el-button @click="back" style="margin-left: 20px">取消</el-button>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";
export default {
  name: 'EditOrder',
  data(){
    return{
      form:{},
      rules: {
        name: [
          {required: true, message: '请输入分类名称', trigger: 'blur'},
        ],
      },
      statusoption: [{
        value: '已完成',
        label: '已完成'
      }, {
        value: '未完成',
        label: '未完成'
      }],
    }
  },
  created() {
    const id=this.$route.query.id
    request.get("/order/"+id).then(res=>{
      this.form=res.data
    })
  },
  methods:{
    save(){
      request.put('/order/update',this.form).then(res =>{
        if(res.code==='200'){
          this.$notify.success('更新成功');
          this.$router.push("/orderList")
        }
        else{
          this.$notify.error(res.msg)
        }

      })
    },
    back(){
      this.$router.push("/orderList")
    }
  }
}
</script>


<!--el-form不显示-->


