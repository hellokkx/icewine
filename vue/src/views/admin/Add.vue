<template>
  <div style="width:80%">
    <div style="margin-left: 10px">
      <h3 style="padding-left:20px; margin-bottom: 30px">新增管理员</h3>
      <div>
        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px" style="width: 100%">

          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入员工姓名" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pwd">
            <el-input v-model="form.pwd" placeholder="请输入密码" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="form.sex" placeholder="请选择性别" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in sexoption"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="form.age" placeholder="请输入年龄" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="职位" prop="pos">
            <el-input v-model="form.pos" placeholder="请输入职位" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="权限" prop="prior">
            <el-select v-model="form.prior" placeholder="请选择权限级别" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in prior"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="form.tel" placeholder="请输入电话" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="ads">
            <el-input v-model="form.ads" placeholder="请输入地址" style="width: 200px;margin-left: 10px"></el-input>
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
  name: 'AddAdmin',
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
      prior: [{
        value: '1',
        label: '1'
      }, {
        value: '2',
        label: '2'
      },{
        value: '3',
        label: '3'
      }],
      rules: {
        name: [
          {required: true, message: '请输入员工姓名', trigger: 'blur'},
        ],
        tel: [
          {validator: checkPhone, trigger: 'blur'}
        ],
      }

    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/admin/save', this.form).then(res => {
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


