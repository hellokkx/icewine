<template>
  <div style="width:500px">
    <div style="margin-left: 10px">
      <h3 style="padding-left:20px; margin-bottom: 30px">新增冰酒</h3>
      <div>
        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px" style="width: 100%">
          <el-form-item label="名称" prop="winename">
            <el-input v-model="form.winename" placeholder="请输入名称" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>


          <el-form-item label="分类" prop="category">
            <el-select v-model="form.category" placeholder="请选择状态" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in category"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

<!--          <el-form-item label="出版日期" prop="publishdate">-->
<!--            <el-date-picker-->
<!--                style="width: 95%"-->
<!--                v-model="form.publishdate"-->
<!--                type="date"-->
<!--                value-format="yyyy-MM-dd"-->
<!--                placeholder="请选择出版日期">-->
<!--            </el-date-picker>-->
<!--          </el-form-item>-->

          <el-form-item label="库存数量" prop="num" style="margin-top: 10px">
            <el-input-number v-model="form.num" :min="1" :max="1000" label="库存数量" style="margin-left: 10px;width: 150px"></el-input-number>
          </el-form-item>


          <el-form-item label="是否上架" prop="status" style="margin-top: 10px">
            <el-select v-model="form.status" placeholder="请选择是否上架" style="width: 200px;margin-left: 10px">
              <el-option
                  v-for="item in statusoption"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="封面链接" prop="cover" style="margin-top: 15px">
            <el-input v-model="form.cover" placeholder="请输入封面图片" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

        </el-form>
      </div>
    </div>

    <div style="margin-left: 80px;margin-top: 20px">
      <el-button type="primary" plain @click="save" style="margin-left: 80px;margin-right: 10px">提交</el-button>
      <el-button type="info" plain @click="reset" style="margin-left: 20px">重置</el-button>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'AddWine',
  data() {
    return {
      form: {},
      statusoption: [{
        value: '已上架',
        label: '已上架'
      }, {
        value: '未上架',
        label: '未上架'
      }],

      category: [{
        value: '滞销',
        label: '滞销'
      }, {
        value: '一般',
        label: '一般'
      }, {
        value: '普通',
        label: '普通'
      }, {
        value: '畅销',
        label: '畅销'
      }, {
        value: '热门',
        label: '热门'
      }],

      rules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
        ],
        num: [
          {required: true, message: '请输入库存数量', trigger: 'blur'},
        ],
      }

    }
  },
  created() {
    // request.get('/category/tree').then(res=>{
    //   this.categories=res.data
    // })
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/wine/save', this.form).then(res => {
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
    },
    handleChange(val){
      console.log(val)
    },
  }
}
</script>


<!--el-form不显示-->


