<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="ILF">
        <el-input
          v-model="queryParams.ILF"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="EIF">
        <el-input
          v-model="queryParams.EIF"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="EI">
        <el-input
          v-model="queryParams.EI"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="EO">
        <el-input
          v-model="queryParams.EO"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="EQ">
        <el-input
          v-model="queryParams.EQ"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="UFP">
        <el-input
          v-model="queryParams.UFP"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="GSC">
        <el-input
          v-model="queryParams.GSC"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="TCF">
        <el-input
          v-model="queryParams.TCF"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="AFP">
        <el-input
          v-model="queryParams.AFP"
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
          v-hasPermi="['point_analysis:project:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['point_analysis:project:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['point_analysis:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['point_analysis:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="projectId" />
      <el-table-column label="${comment}" align="center" prop="ILF" />
      <el-table-column label="${comment}" align="center" prop="EIF" />
      <el-table-column label="${comment}" align="center" prop="EI" />
      <el-table-column label="${comment}" align="center" prop="EO" />
      <el-table-column label="${comment}" align="center" prop="EQ" />
      <el-table-column label="${comment}" align="center" prop="UFP" />
      <el-table-column label="${comment}" align="center" prop="GSC" />
      <el-table-column label="${comment}" align="center" prop="TCF" />
      <el-table-column label="${comment}" align="center" prop="AFP" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['point_analysis:project:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['point_analysis:project:remove']">删除</el-button>
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

    <!-- 添加或修改Function_point_analysis对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="projectRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="ILF">
          <el-input v-model="form.ILF" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="EIF">
          <el-input v-model="form.EIF" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="EI">
          <el-input v-model="form.EI" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="EO">
          <el-input v-model="form.EO" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="EQ">
          <el-input v-model="form.EQ" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="UFP">
          <el-input v-model="form.UFP" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="GSC">
          <el-input v-model="form.GSC" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="TCF">
          <el-input v-model="form.TCF" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="AFP">
          <el-input v-model="form.AFP" placeholder="请输入${comment}" />
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

<script setup name="Project">
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/point_analysis/project";

const { proxy } = getCurrentInstance();

const projectList = ref([]);
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
    ILF: null,
    EIF: null,
    EI: null,
    EO: null,
    EQ: null,
    UFP: null,
    GSC: null,
    TCF: null,
    AFP: null
  },
  rules: {
    ILF: [
      { required: true, message: "$comment不能为空", trigger: "blur" }
    ],
    EIF: [
      { required: true, message: "$comment不能为空", trigger: "blur" }
    ],
    EI: [
      { required: true, message: "$comment不能为空", trigger: "blur" }
    ],
    EO: [
      { required: true, message: "$comment不能为空", trigger: "blur" }
    ],
    EQ: [
      { required: true, message: "$comment不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询Function_point_analysis列表 */
function getList() {
  loading.value = true;
  listProject(queryParams.value).then(response => {
    projectList.value = response.rows;
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
    projectId: null,
    ILF: null,
    EIF: null,
    EI: null,
    EO: null,
    EQ: null,
    UFP: null,
    GSC: null,
    TCF: null,
    AFP: null
  };
  proxy.resetForm("projectRef");
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
  ids.value = selection.map(item => item.projectId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加Function_point_analysis";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _projectId = row.projectId || ids.value
  getProject(_projectId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改Function_point_analysis";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["projectRef"].validate(valid => {
    if (valid) {
      if (form.value.projectId != null) {
        updateProject(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProject(form.value).then(response => {
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
  const _projectIds = row.projectId || ids.value;
  proxy.$modal.confirm('是否确认删除Function_point_analysis编号为"' + _projectIds + '"的数据项？').then(function() {
    return delProject(_projectIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('point_analysis/project/export', {
    ...queryParams.value
  }, `project_${new Date().getTime()}.xlsx`)
}

getList();
</script>
