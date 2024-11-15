<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="PDR" prop="PDR">
        <el-input
          v-model="queryParams.PDR"
          placeholder="请输入PDR"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SF" prop="SF">
        <el-input
          v-model="queryParams.SF"
          placeholder="请输入SF"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="BD" prop="BD">
        <el-input
          v-model="queryParams.BD"
          placeholder="请输入BD"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="AT" prop="AT">
        <el-input
          v-model="queryParams.AT"
          placeholder="请输入AT"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="QR" prop="QR">
        <el-input
          v-model="queryParams.QR"
          placeholder="请输入QR"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SL" prop="SL">
        <el-input
          v-model="queryParams.SL"
          placeholder="请输入SL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="DT" prop="DT">
        <el-input
          v-model="queryParams.DT"
          placeholder="请输入DT"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="RSK" prop="RSK">
        <el-input
          v-model="queryParams.RSK"
          placeholder="请输入RSK"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="F" prop="F">
        <el-input
          v-model="queryParams.F"
          placeholder="请输入F"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="DNC" prop="DNC">
        <el-input
          v-model="queryParams.DNC"
          placeholder="请输入DNC"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SDC" prop="SDC">
        <el-input
          v-model="queryParams.SDC"
          placeholder="请输入SDC"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ESDC" prop="ESDC">
        <el-input
          v-model="queryParams.ESDC"
          placeholder="请输入ESDC"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- 添加 tenantryID 字段 -->
      <el-form-item label="租户ID" prop="tenantryID">
        <el-input
          v-model="queryParams.tenantryID"
          placeholder="请输入租户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['evaluate:productbudget:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['evaluate:productbudget:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['evaluate:productbudget:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['evaluate:productbudget:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productbudgetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目ID" align="center" prop="productID" />
      <el-table-column label="PDR" align="center" prop="pdr" />
      <el-table-column label="SF" align="center" prop="sf" />
      <el-table-column label="BD" align="center" prop="bd" />
      <el-table-column label="AT" align="center" prop="at" />
      <el-table-column label="QR" align="center" prop="qr" />
      <el-table-column label="SL" align="center" prop="sl" />
      <el-table-column label="DT" align="center" prop="dt" />
      <el-table-column label="RSK" align="center" prop="rsk" />
      <el-table-column label="F" align="center" prop="f" />
      <el-table-column label="DNC" align="center" prop="dnc" />
      <el-table-column label="SDC" align="center" prop="sdc" />
      <el-table-column label="ESDC" align="center" prop="esdc" />
      <el-table-column label="租户ID" align="center" prop="tenantryID" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
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
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改综合评估对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="PDR" prop="pdr">
          <el-input v-model="form.pdr" placeholder="请输入PDR" />
        </el-form-item>
        <el-form-item label="SF" prop="sf">
          <el-input v-model="form.sf" placeholder="请输入SF" />
        </el-form-item>
        <el-form-item label="BD" prop="bd">
          <el-input v-model="form.bd" placeholder="请输入BD" />
        </el-form-item>
        <el-form-item label="AT" prop="at">
          <el-input v-model="form.at" placeholder="请输入AT" />
        </el-form-item>
        <el-form-item label="QR" prop="qr">
          <el-input v-model="form.qr" placeholder="请输入QR" />
        </el-form-item>
        <el-form-item label="SL" prop="sl">
          <el-input v-model="form.sl" placeholder="请输入SL" />
        </el-form-item>
        <el-form-item label="DT" prop="dt">
          <el-input v-model="form.dt" placeholder="请输入DT" />
        </el-form-item>
        <el-form-item label="RSK" prop="rsk">
          <el-input v-model="form.rsk" placeholder="请输入RSK" />
        </el-form-item>
        <el-form-item label="F" prop="f">
          <el-input v-model="form.f" placeholder="请输入F" />
        </el-form-item>
        <el-form-item label="DNC" prop="dnc">
          <el-input v-model="form.dnc" placeholder="请输入DNC" />
        </el-form-item>
        <el-form-item label="SDC" prop="sdc">
          <el-input v-model="form.sdc" placeholder="请输入SDC" />
        </el-form-item>
        <el-form-item label="ESDC" prop="esdc">
          <el-input v-model="form.esdc" placeholder="请输入ESDC" />
        </el-form-item>
        <el-form-item label="租户ID" prop="tenantryID">
          <el-input v-model="form.tenantryID" placeholder="请输入租户ID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProductbudget, getProductbudget, delProductbudget, addProductbudget, updateProductbudget } from "@/api/evaluate/productbudget";

export default {
  name: "Productbudget",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 综合评估表格数据
      productbudgetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pdr: null,
        sf: null,
        bd: null,
        at: null,
        qr: null,
        sl: null,
        dt: null,
        rsk: null,
        f: null,
        dnc: null,
        sdc: null,
        esdc: null,
        tenantryID: null // 保持 productID 和 tenantryID 大写，因为它们原本就是大写
      },
      form: {
        productID: null,
        pdr: null,
        sf: null,
        bd: null,
        at: null,
        qr: null,
        sl: null,
        dt: null,
        rsk: null,
        f: null,
        dnc: null,
        sdc: null,
        esdc: null,
        tenantryID: null
      },

      // 表单校验
      rules: {}
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        productID: null,
        pdr: null,
        sf: null,
        bd: null,
        at: null,
        qr: null,
        sl: null,
        dt: null,
        rsk: null,
        f: null,
        dnc: null,
        sdc: null,
        esdc: null,
        tenantryID: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.productID);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加综合评估";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const productID = row.productID || this.ids;
      getProductbudget(productID).then(response => {
        this.form = { ...response.data };  // 确保字段名一致
        this.open = true;
        this.title = "修改综合评估";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.productID != null) {
            updateProductbudget(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProductbudget(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const productIDs = row.productID || this.ids;
      this.$modal.confirm('是否确认删除综合评估编号为"' + productIDs + '"的数据项？').then(function() {
        return delProductbudget(productIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('evaluate/productbudget/export', {
        ...this.queryParams
      }, `productbudget_${new Date().getTime()}.xlsx`);
    }
  }
};
</script>
