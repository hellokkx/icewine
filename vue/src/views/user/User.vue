<template>
  <div style="margin-top: 10px;margin-right: 20px;margin-left:10px">
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 240px" placeholder="请输入姓名" v-model="params.username"></el-input>
      <el-input style="margin-left:5px; width: 240px" placeholder="请输入联系方式" v-model="params.tel"></el-input>
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
    <el-table :data="tableData" stripe>
      <el-table-column prop="uid" label="用户id" width="70"></el-table-column>
      <el-table-column prop="username" label="姓名"></el-table-column>
      <el-table-column prop="gen" label="性别" width="70"></el-table-column>
      <el-table-column prop="age" label="年龄"width="70"></el-table-column>
      <el-table-column prop="pos" label="等级"width="70"></el-table-column>
      <el-table-column prop="tel" label="手机号" width="100"></el-table-column>
      <el-table-column prop="ads" label="地址" width="150"></el-table-column>
      <el-table-column prop="birth" label="生日"></el-table-column>
      <el-table-column prop="time" label="创建时间"></el-table-column>

      <el-table-column label="操作" width="150">
        <template v-slot="scope">
          <!--          scope.row 就是当前行数据-->
          <el-button type="primary" style="font-size: 12px;padding: 8px 10px;" @click="$router.push('/editUser?id='+scope.row.uid)">编辑</el-button>

          <el-popconfirm
            style="margin-left: 10px"
            title="您确定删除这行数据吗？"
            @confirm="del(scope.row.uid)"
        >
          <el-button slot="reference" type="danger" style="font-size: 12px;padding: 8px 10px;">删除</el-button>
        </el-popconfirm>

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

  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";

export default {
  name: 'User',
  data(){
    return{
      tableData:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:8,
        username:'',
        tel:'',
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      // fetch('http://localhost:9090/user/list')
      //     .then(res=>res.json()).then(
      //         res=>{
      //           console.log(res)
      //           this.tableData=res
      //         })

      request.get('/user/page',{
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
        pageSize: 8,
        username:'',
        tel:'',
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      //点击分页按钮触发分页
      this.params.pageNum=pageNum
      this.load()
    },
    del(id){
      request.delete("/user/delete/"+id).then(res =>{
        if(res.code==='200'){
          this.$notify.success('删除成功')
          this.load()
        }
        else{
          this.$notify.error(res.msg)
        }
      })
    }

  }
}
</script>

<style scoped>

</style>