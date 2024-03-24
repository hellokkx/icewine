<template>
  <div style="margin:30px">
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-button style="margin-left: 5px" plain @click="$router.push('orderList')">
        <i class=""></i>
        <span>返回</span>
      </el-button>
    </div>

    <el-descriptions title="用户信息" style="margin: 20px">
      <el-descriptions-item label="用户名">吴彦祖</el-descriptions-item>
      <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
      <el-descriptions-item label="居住地">深圳市</el-descriptions-item>
      <el-descriptions-item label="备注">
        <el-tag size="small">学校</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="联系地址">广东省深圳市南山区粤海街道深圳大学</el-descriptions-item>
    </el-descriptions>

      <!--stripe显示斑马纹-->
      <el-table :data="table" row-key="did" show-summary :summary-method="getSummaries" border>
        <el-table-column prop="oid" label="订单编号"></el-table-column>
        <el-table-column prop="name" label="商品名称"></el-table-column>
        <el-table-column prop="wid" label="商品编号"></el-table-column>
        <el-table-column prop="price" label="商品单价">
          <template slot-scope="scope">
            {{ fun(scope.row.price) }}
          </template>
        </el-table-column>
        <el-table-column prop="num" label="商品数量"></el-table-column>
        <el-table-column prop="totalprice" label="总价格" show-summary>
          <template slot-scope="scope">
            {{ fun(scope.row.totalprice) }}
          </template>
        </el-table-column>

      </el-table>


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
      table:[],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      const id=this.$route.query.oid
      request.get('/detail/list/'+id).then(res=>{
        if(res.code === '200'){
          this.table=res.data
        }
      })
    },
    fun(val){
        return Number(val).toFixed(2);
    },

    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '总价格';
          return;
        }
        if(index !== 5){return;}
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          sums[index] += ' 元';
        } else {
          sums[index] = 'N/A';
        }
      });

      return sums;
    }
  }



}
</script>

<style scoped>

</style>