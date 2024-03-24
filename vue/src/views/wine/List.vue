<template>
  <div style="margin-top: 10px;margin-right: 20px;margin-left:10px">
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 300px;margin-left: 15px" placeholder="请输入冰酒名称" v-model="params.winename"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class=" el-icon-search"></i><span>搜索</span>
      </el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i
          class=" el-icon-refresh"></i><span>重置</span></el-button>
    </div>

    <!--stripe显示斑马纹-->
    <el-table :data="tableData" stripe row-key="wid" default-expand-all>
      <el-table-column prop="wid" label="编号" width="50px"></el-table-column>
      <el-table-column prop="winename" label="冰酒名称"></el-table-column>
      <el-table-column prop="category" label="类型"></el-table-column>
      <el-table-column prop="num" label="库存数量"></el-table-column>
      <el-table-column prop="sold" label="销量"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column prop="cover" label="图片">
        <template v-slot="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"/>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>



      <el-table-column label="操作" width="140" align="left" header-align="50px">
        <template v-slot="scope">
          <el-button type="primary" style="font-size: 12px;padding: 8px 10px;" @click="$router.push('/editWine?id='+scope.row.wid)">编辑</el-button>

          <el-popconfirm
              style="margin-left: 5px"
              title="您确定删除这行数据吗？"
              @confirm="del(scope.row.wid)"
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
import Cookies from "js-cookie";
import {options} from "axios";

export default {
  name: 'WineList',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 5,
        name: '',
      },
    }
  },
  created() {
    this.load()
  },
  methods: {
    options,

    load() {
      request.get('/wine/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset() {  //重置
      this.params = {
        pageNum: 1,
        pageSize: 5,
        name: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      //点击分页按钮触发分页
      this.params.pageNum = pageNum
      this.load()
    },
    del(wid) {
      request.delete("/wine/delete/" + wid).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },

  }
}
</script>

<style scoped>

</style>