<template>
  <div style="margin-top: 10px;margin-right: 20px;margin-left:10px">
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 240px" placeholder="请输入订单编号" v-model="params.oid"></el-input>
      <el-input style="width: 240px;margin-left: 10px" placeholder="请输入用户编号" v-model="params.uid"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">
        <i class=" el-icon-search"></i>
        <span>搜索</span>
      </el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">
        <i class=" el-icon-refresh"></i>
        <span>重置</span>
      </el-button>
    </div>

    <!--stripe显示斑马纹-->
    <el-table :data="tableData" stripe row-key="oid" default-expand-all>
      <el-table-column prop="oid" label="订单编号" width="80"></el-table-column>
      <el-table-column prop="pay" label="配送/支付"></el-table-column>
      <el-table-column prop="time" label="下单时间"></el-table-column>
      <el-table-column prop="uid" label="用户编号" width="80"></el-table-column>
      <el-table-column prop="price" label="总价格"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>

      <el-table-column label="操作" width="250" align="left" header-align="50px">
        <template v-slot="scope">
          <el-button slot="reference" plain type="primary" @click="$router.push('/detail?oid='+scope.row.oid)">查看详情</el-button>

<!--          <el-button type="success" v-if="!scope.row.pid" @click="handleAdd(scope.row)" style="margin-left:5px">添加二级分类</el-button>-->

          <el-button type="success" style="margin-left: 5px"plain @click="$router.push('/editOrder?id='+scope.row.oid)">修改订单</el-button>

        </template>
      </el-table-column>

    </el-table>

    <div style="margin-top: 20px">
      <el-pagination
          background
          :current_page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>


<!--    <el-dialog title="添加二级分类" :visible.sync="dialogFormVisible" width="30%">-->
<!--      <el-form :model="form" :inline="true" label-width="100px" :rules="rules" ref="formRef">-->
<!--        <el-form-item label="分类名称" prop="name">-->
<!--          <el-input v-model="form.name" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注" prop="remark">-->
<!--          <el-input v-model="form.remark" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button @click="dialogFormVisible = false">取 消</el-button>-->
<!--        <el-button type="primary" @click="save">确 定</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
    

  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'OrderList',
  data(){
    return{
      admin:Cookies.get('admin')?JSON.parse(Cookies.get('admin')):{},
      tableData:[],
      total:0,
      // dialogFormVisible:false,
      form:{},
      pid:null,
      params:{
        pageNum:1,
        pageSize:10,
        name:'',
      },
      rules:{
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    
    load(){
      request.get('/order/page',{
        params: this.params
      }).then(res=>{
        if(res.code === '200'){
          this.tableData=res.data.list
          this.total=res.data.total
        }
      })
    },
    reset(){  //重置
      this.params={
        pageNum:1,
        pageSize:10,
        username:'',
        phone:'',
        email:''
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      //点击分页按钮触发分页
      this.params.pageNum=pageNum
      this.load()
    },
    del(id){
      request.delete("/order/delete/"+id).then(res =>{
        if(res.code==='200'){
          this.$notify.success('删除成功')
          this.load()
        }
        else{
          this.$notify.error(res.msg)
        }
      })
    },

    handleAdd(row){
      //将当前行的id作为二级分类的pid
      this.pid=row.id
      this.dialogFormVisible=true
    },
    save() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          //给二级分类赋值pid
          this.form.pid=this.pid
          request.post('/order/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增二级分类成功');
              this.form={}
              this.dialogFormVisible=false
              this.load()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },

  }
}
</script>

<style scoped>

</style>