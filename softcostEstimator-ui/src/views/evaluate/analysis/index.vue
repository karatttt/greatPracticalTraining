<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="ILF" prop="ILF">
        <el-input
          v-model="queryParams.ILF"
          placeholder="请输入ILF"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="EIF" prop="EIF">
        <el-input
          v-model="queryParams.EIF"
          placeholder="请输入EIF"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="EI" prop="EI">
        <el-input
          v-model="queryParams.EI"
          placeholder="请输入EI"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="EO" prop="EO">
        <el-input
          v-model="queryParams.EO"
          placeholder="请输入EO"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="EQ" prop="EQ">
        <el-input
          v-model="queryParams.EQ"
          placeholder="请输入EQ"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="UFP" prop="UFP">
        <el-input
          v-model="queryParams.UFP"
          placeholder="请输入UFP"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="GSC" prop="GSC">
        <el-input
          v-model="queryParams.GSC"
          placeholder="请输入GSC"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="TCF" prop="TCF">
        <el-input
          v-model="queryParams.TCF"
          placeholder="请输入TCF"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="AFP" prop="AFP">
        <el-input
          v-model="queryParams.AFP"
          placeholder="请输入AFP"
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
          v-hasPermi="['evaluate:analysis:add']"
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
          v-hasPermi="['evaluate:analysis:edit']"
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
          v-hasPermi="['evaluate:analysis:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['evaluate:analysis:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <div class="app-container" style="height: 100%;">
      <!-- 布局容器 -->
      <el-row>
        <!-- 表格部分 -->
        <el-col :span="20">
          <el-table v-loading="loading" :data="analysisList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="项目ID" align="center" prop="projectId" />
            <el-table-column label="ILF" align="center" prop="ILF" />
            <el-table-column label="EIF" align="center" prop="EIF" />
            <el-table-column label="EI" align="center" prop="EI" />
            <el-table-column label="EO" align="center" prop="EO" />
            <el-table-column label="EQ" align="center" prop="EQ" />
<!--            <el-table-column label="UFP" align="center" prop="UFP" />-->
<!--            <el-table-column label="GSC" align="center" prop="GSC" />-->
<!--            <el-table-column label="TCF" align="center" prop="TCF" />-->
            <el-table-column label="AFP" align="center" prop="AFP" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['evaluate:analysis:edit']"
                >修改</el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['evaluate:analysis:remove']"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
        <!-- 柱状图部分 -->
        <el-col :span="10">
          <div id="chart" style="width: 100%; height: 400px;"></div>
        </el-col>
        <el-col :span="10">
          <div id="chart" style="width: 100%; height: 400px;"></div>
        </el-col>

      </el-row>

    </div>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改功能点分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="ILF" prop="ILF">
          <el-input v-model="form.ILF" placeholder="请输入ILF" />
        </el-form-item>
        <el-form-item label="EIF" prop="EIF">
          <el-input v-model="form.EIF" placeholder="请输入EIF" />
        </el-form-item>
        <el-form-item label="EI" prop="EI">
          <el-input v-model="form.EI" placeholder="请输入EI" />
        </el-form-item>
        <el-form-item label="EO" prop="EO">
          <el-input v-model="form.EO" placeholder="请输入EO" />
        </el-form-item>
        <el-form-item label="EQ" prop="EQ">
          <el-input v-model="form.EQ" placeholder="请输入EQ" />
        </el-form-item>
        <el-form-item label="UFP" prop="UFP">
          <el-input v-model="form.UFP" placeholder="请输入UFP" />
        </el-form-item>
        <el-form-item label="GSC" prop="GSC">
          <el-input v-model="form.GSC" placeholder="请输入GSC" />
        </el-form-item>
        <el-form-item label="TCF" prop="TCF">
          <el-input v-model="form.TCF" placeholder="请输入TCF" />
        </el-form-item>
        <el-form-item label="AFP" prop="AFP">
          <el-input v-model="form.AFP" placeholder="请输入AFP" />
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
import { listAnalysis, getAnalysis, delAnalysis, addAnalysis, updateAnalysis } from "@/api/evaluate/analysis";
import * as echarts from 'echarts';

export default {
  name: "Analysis",
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
      showSearch: false,
      // 总条数
      total: 0,
      // 功能点分析表格数据
      analysisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
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
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ILF: [
          { required: true, message: "ILF不能为空", trigger: "blur" }
        ],
        EIF: [
          { required: true, message: "EIF不能为空", trigger: "blur" }
        ],
        EI: [
          { required: true, message: "EI不能为空", trigger: "blur" }
        ],
        EO: [
          { required: true, message: "EO不能为空", trigger: "blur" }
        ],
        EQ: [
          { required: true, message: "EQ不能为空", trigger: "blur" }
        ],
      },
      chart: null,

    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.initChart();
    this.generateData(); // 生成随机数据
  },
  methods: {
    /** 查询功能点分析列表 */
    getList() {
      this.loading = true;
      listAnalysis(this.queryParams).then(response => {
        this.analysisList = response.rows;
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加功能点分析";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getAnalysis(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改功能点分析";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateAnalysis(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnalysis(this.form).then(response => {
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
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除功能点分析编号为"' + projectIds + '"的数据项？').then(function() {
        return delAnalysis(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('evaluate/analysis/export', {
        ...this.queryParams
      }, `analysis_${new Date().getTime()}.xlsx`)
    },
    /** 生成柱状图 */
    initChart() {
      this.chart = echarts.init(document.getElementById('chart'));
      const option = {
        // 柱状图配置项
        xAxis: {
          type: 'category',
          data: ['ILF', 'EIF', 'EI', 'EO', 'EQ', 'UFP', 'GSC', 'TCF', 'AFP']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [120, 200, 150, 80, 70, 110, 130, 100, 90],
          type: 'bar'
        }]
      };
      this.chart.setOption(option);
    }
  }
};
</script>
