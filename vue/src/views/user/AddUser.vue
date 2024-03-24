<template>
  <div style="width:80%">
    <div style="margin-left: 10px">
      <h3 style="padding-left:20px; margin-bottom: 30px">新增用户</h3>
      <div>
        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
          <el-form-item label="姓名" prop="username">
            <el-input v-model="form.username" placeholder="请输入姓名" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="form.age" placeholder="请输入年龄" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gen">
            <el-select v-model="form.gen" placeholder="请选择性别" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in sexoption"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="等级">
            <el-select v-model="form.pos" placeholder="请选择级别" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in position"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="生日" prop="birth">
            <el-date-picker
                style="width: 87%;margin-left: 10px"
                v-model="form.birth"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请输入生日">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="联系方式" prop="phone">
            <el-input v-model="form.tel" placeholder="请输入电话" style="width: 190px;margin-left: 0px"></el-input>
          </el-form-item>
          <el-form-item label="地址">
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
  name: 'AddUser',
  data() {

    const checkPhone= (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
          callback(new Error('合法的手机号:11位,非"11"或"12"开头'));
      }
      callback()
    };

    const checkAge= (rule, value, callback) => {
      if (!/^[0-9]+$/.test(value)) {
        callback(new Error('请输入数字值'));
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
      position: [{
        value: '普通用户',
        label: '普通用户'
      }, {
        value: 'VIP',
        label: 'VIP'
      },{
        value: '尊贵VIP',
        label: '尊贵VIP'
      }],
      rules: {
        username: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        tel: [
          {validator: checkPhone, trigger: 'blur'}
        ],
        age:[
          {validator: checkAge, trigger: 'blur'}
        ]
      }

    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/user/save', this.form).then(res => {
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


