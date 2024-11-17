<template>
  <div class="app-container">
    <!-- 表格部分 -->
    <el-table v-loading="loading" :data="productbudgetList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="项目ID" align="center" prop="productID" />

      <el-table-column label="评估工作量" align="center" prop="ae" />
<!--      <el-table-column label="PDR" align="center" prop="pdr" />
      <el-table-column label="SF" align="center" prop="sf" />
      <el-table-column label="BD" align="center" prop="bd" />
      <el-table-column label="AT" align="center" prop="at" />
      <el-table-column label="QR" align="center" prop="qr" />
      <el-table-column label="SL" align="center" prop="sl" />
      <el-table-column label="DT" align="center" prop="dt" />
      <el-table-column label="RSK" align="center" prop="rsk" />
      <el-table-column label="F" align="center" prop="f" />
      <el-table-column label="DNC" align="center" prop="dnc" />
      <el-table-column label="SDC" align="center" prop="sdc" />-->
      <el-table-column label="评估成本" align="center" prop="esdc" />

       <!-- 状态列 -->
       <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="Number(scope.row.esdc) > 0.0">
            已评估
            <a
              href="javascript:void(0)"
              @click="goToEvaluationPage(scope.row)"
              style="margin-left: 10px; color: #409EFF; cursor: pointer;"
            >
              重新评估
            </a>
          </span>
          <el-button
            v-else
            type="primary"
            size="mini"
            @click="goToEvaluationPage(scope.row)"
          >
            去评估
          </el-button>
        </template>
      </el-table-column>


<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['evaluate:productbudget:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['evaluate:productbudget:remove']"
          >删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listProductbudget, getProductbudget, delProductbudget } from "@/api/evaluate/productbudget";

export default {
  name: "Productbudget",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      total: 0,
      productbudgetList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询综合评估列表 */
    getList() {
      this.loading = true;
      listProductbudget(this.queryParams).then(response => {
        this.productbudgetList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.productID);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      // 修改逻辑
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const productIDs = row.productID || this.ids;
      this.$modal.confirm('是否确认删除综合评估编号为"' + productIDs + '"的数据项？').then(() => {
        return delProductbudget(productIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 去评估按钮点击，跳转到评估页面 */
    goToEvaluationPage(row) {
      // 跳转到评估页面
     // window.location.href = `http://localhost/evaluate/compute`;
     //window.location.href = `http://localhost/evaluate/compute?productID=${row.productID}`;
     this.$router.push({
        name: 'EvaluationCompute',
        query: { productID: row.productID }
      }).catch(err => {
        // 处理可能的导航错误，例如重复导航
        if (err.name !== 'NavigationDuplicated') {
          console.error(err);
        }
      })
    }
  }
};
</script>
