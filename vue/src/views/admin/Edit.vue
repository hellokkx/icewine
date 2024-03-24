<template>
  <div style="width:80%">
    <div style="margin-left: 30px">
      <h3 style="margin-left:10px; margin-bottom: 30px">编辑管理员</h3>
      <div>
        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
          <el-form-item label="员工号" prop="id">
            <el-input v-model="form.id" disabled style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入员工姓名" style="width: 200px;margin-left: 10px"></el-input>
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
      <el-button type="primary" @click="save" style="margin-right: 10px">修改</el-button>
      <el-button @click="back" style="margin-left: 20px">取消</el-button>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";
export default {
  name: 'EditAdmin',
  data(){
    return{
      form:{},
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
    }
  },
  created() {
    const id=this.$route.query.id
    request.get("/admin/"+id).then(res=>{
      this.form=res.data
    })
  },
  methods:{
    save(){
      request.put('/admin/update',this.form).then(res =>{
        if(res.code==='200'){
          this.$notify.success('更新成功');
          this.$router.push("/adminList")
        }
        else{
          this.$notify.error(res.msg)
        }

      })
    },
    back(){
      this.$router.push("/adminList")
    }
  }
}
</script>


<!--el-form不显示-->


