<template>
  <div style="width:500px">
    <div style="margin-left: 10px">
      <h3 style="padding-left:20px; margin-bottom: 30px">新增订单</h3>
      <div>

        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
          <el-form-item label="用户编号" prop="uid">
            <el-input v-model="form.uid" placeholder="请输入用户编号" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

          <el-form-item label="配送/支付" prop="pay">
            <el-input v-model="form.pay" placeholder="请输入配送/支付方式" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

          <el-form-item label="总价格" prop="price">
            <el-input v-model="form.price" placeholder="请输入总价格" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

        </el-form>
      </div>
    </div>

    <div style="margin-left: 80px;margin-top: 20px">
      <el-button type="primary" plain @click="save" style="margin-right: 10px">提交</el-button>
      <el-button type="info" plain @click="reset" style="margin-left: 20px">重置</el-button>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'AddCategory',
  data() {

    const checkPhone= (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
          callback(new Error('合法的手机号:11位,非"11"或"12"开头'));
      }
      callback()
    };


    return {
      form: {},
      sexoption: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }],
      rules: {
        name: [
          {required: true, message: '请输入分类名称', trigger: 'blur'},
        ],
      }

    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/order/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功');
              this.form={}
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })

    },
    reset() {
      this.form={}
    }
  }
}
</script>


<!--el-form不显示-->


