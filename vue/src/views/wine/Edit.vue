<template>
  <div style="width:80%">
    <div style="margin-left: 30px">
      <h3 style="margin-left:10px; margin-bottom: 30px">编辑冰酒</h3>
      <div>
        <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px" style="width: 100%">
          <el-form-item label="名称" prop="winename">
            <el-input v-model="form.winename" disabled placeholder="请输入名称" style="width: 200px;margin-left: 10px"></el-input>
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

          <el-form-item label="库存数量" prop="num" style="margin-top: 10px">
            <el-input v-model="form.num" placeholder="请输入库存数量" style="width: 200px;margin-left: 10px"></el-input>
          </el-form-item>

          <el-form-item label="销量" prop="sold" style="margin-top: 10px">
            <el-input v-model="form.sold" placeholder="请输入库存数量" style="width: 200px;margin-left: 10px"></el-input>
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

          <el-form-item label="封面链接" prop="cover" style="margin-top: 10px">
            <el-input v-model="form.cover" placeholder="请输入封面图片" style="width: 200px;margin-left: 10px"></el-input>
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
  name: 'EditWine',
  data(){
    return{
      form:{},
      categories:[],
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
          {required: true, message: '请输入图书名称', trigger: 'blur'},
        ],
      },
    }
  },
  created() {
    // request.get('/category/tree').then(res=>{
    //   this.categories=res.data
    // })
    const id=this.$route.query.id
    request.get("/wine/"+id).then(res=>{
      this.form=res.data
      // if(this.form.category){
      //   this.form.categories=this.form.category.split(' > ')
      // }
    })
  },
  methods:{
    save(){
      request.put('/wine/update',this.form).then(res =>{
        if(res.code==='200'){
          this.$notify.success('更新成功');
          this.$router.push("/wineList")
        }
        else{
          this.$notify.error(res.msg)
        }
      })
    },
    back(){
      this.$router.push("/wineList")
    },
  }
}
</script>


<!--el-form不显示-->


