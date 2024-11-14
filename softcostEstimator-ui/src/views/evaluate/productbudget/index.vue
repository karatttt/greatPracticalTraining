<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="PDR">
        <el-input
          v-model="queryParams.PDR"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="SF">
        <el-input
          v-model="queryParams.SF"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="BD">
        <el-input
          v-model="queryParams.BD"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="AT">
        <el-input
          v-model="queryParams.AT"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="QR">
        <el-input
          v-model="queryParams.QR"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="SL">
        <el-input
          v-model="queryParams.SL"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="DT">
        <el-input
          v-model="queryParams.DT"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="RSK">
        <el-input
          v-model="queryParams.RSK"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="F">
        <el-input
          v-model="queryParams.F"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="DNC">
        <el-input
          v-model="queryParams.DNC"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="SDC">
        <el-input
          v-model="queryParams.SDC"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="ESDC">
        <el-input
          v-model="queryParams.ESDC"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['evaluate:productbudget:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['evaluate:productbudget:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['evaluate:productbudget:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['evaluate:productbudget:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productbudgetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="productID" />
      <el-table-column label="${comment}" align="center" prop="PDR" />
      <el-table-column label="${comment}" align="center" prop="SF" />
      <el-table-column label="${comment}" align="center" prop="BD" />
      <el-table-column label="${comment}" align="center" prop="AT" />
      <el-table-column label="${comment}" align="center" prop="QR" />
      <el-table-column label="${comment}" align="center" prop="SL" />
      <el-table-column label="${comment}" align="center" prop="DT" />
      <el-table-column label="${comment}" align="center" prop="RSK" />
      <el-table-column label="${comment}" align="center" prop="F" />
      <el-table-column label="${comment}" align="center" prop="DNC" />
      <el-table-column label="${comment}" align="center" prop="SDC" />
      <el-table-column label="${comment}" align="center" prop="ESDC" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['evaluate:productbudget:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['evaluate:productbudget:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改造价评估对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="productbudgetRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="PDR">
          <el-input v-model="form.PDR" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="SF">
          <el-input v-model="form.SF" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="BD">
          <el-input v-model="form.BD" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="AT">
          <el-input v-model="form.AT" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="QR">
          <el-input v-model="form.QR" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="SL">
          <el-input v-model="form.SL" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="DT">
          <el-input v-model="form.DT" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="RSK">
          <el-input v-model="form.RSK" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="F">
          <el-input v-model="form.F" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="DNC">
          <el-input v-model="form.DNC" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="SDC">
          <el-input v-model="form.SDC" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="ESDC">
          <el-input v-model="form.ESDC" placeholder="请输入${comment}" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Productbudget">
import { listProductbudget, getProductbudget, delProductbudget, addProductbudget, updateProductbudget } from "@/api/evaluate/productbudget";

const { proxy } = getCurrentInstance();

const productbudgetList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    PDR: null,
    SF: null,
    BD: null,
    AT: null,
    QR: null,
    SL: null,
    DT: null,
    RSK: null,
    F: null,
    DNC: null,
    SDC: null,
    ESDC: null
  },
  rules: {
    productID: [
      { required: true, message: "$comment不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询造价评估列表 */
function getList() {
  loading.value = true;
  listProductbudget(queryParams.value).then(response => {
    productbudgetList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    productID: null,
    PDR: null,
    SF: null,
    BD: null,
    AT: null,
    QR: null,
    SL: null,
    DT: null,
    RSK: null,
    F: null,
    DNC: null,
    SDC: null,
    ESDC: null
  };
  proxy.resetForm("productbudgetRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.productID);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加造价评估";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _productID = row.productID || ids.value
  getProductbudget(_productID).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改造价评估";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["productbudgetRef"].validate(valid => {
    if (valid) {
      if (form.value.productID != null) {
        updateProductbudget(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProductbudget(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _productIDs = row.productID || ids.value;
  proxy.$modal.confirm('是否确认删除造价评估编号为"' + _productIDs + '"的数据项？').then(function() {
    return delProductbudget(_productIDs);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('evaluate/productbudget/export', {
    ...queryParams.value
  }, `productbudget_${new Date().getTime()}.xlsx`)
}

getList();
</script>
