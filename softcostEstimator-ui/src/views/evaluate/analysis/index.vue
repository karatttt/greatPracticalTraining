<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="搜索" prop="searchQuery">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
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
        <el-col :span="24">
          <el-table v-loading="loading" :data="analysisList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="项目ID" align="center" prop="projectId" />
            <el-table-column label="项目名称" align="center" prop="projectName" />
            <el-table-column label="ILF" align="center" prop="ilf" />
            <el-table-column label="EIF" align="center" prop="eif" />
            <el-table-column label="EI" align="center" prop="ei" />
            <el-table-column label="EO" align="center" prop="eo" />
            <el-table-column label="EQ" align="center" prop="eq" />
            <el-table-column label="UFP" align="center" prop="ufp" />
            <el-table-column label="GSC" align="center" prop="gsc" />
            <el-table-column label="TCF" align="center" prop="tcf" />
            <el-table-column label="AFP" align="center" prop="afp" />
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
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleAIUpdate(scope.row)"
                  v-hasPermi="['evaluate:analysis:edit']"
                >AI生成</el-button>
              </template>
            </el-table-column>
          </el-table>
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
    <div>
      <!-- 用于显示 ECharts 的 div -->
      <div id="histogram" style="width: 100%; height: 600%;"></div>
    </div>
    <div id="myChart" style="width: 100%; height: 600%;"></div>

    <el-dialog :title="title" :visible.sync="open0" width="500px" append-to-body>
      <el-select
        v-model="value"
        placeholder="请选择未定评估项目"
        size="large"
        style="width: 240px"
      >
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAdd">添加</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改功能点分析对话框1 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">

        <el-form-item label="ILF" prop="ilf">
          <el-input v-model="form.ilf" placeholder="请输入ILF" />
        </el-form-item>
        <el-form-item label="EIF" prop="eif">
          <el-input v-model="form.eif" placeholder="请输入EIF" />
        </el-form-item>
        <el-form-item label="EI" prop="ei">
          <el-input v-model="form.ei" placeholder="请输入EI" />
        </el-form-item>
        <el-form-item label="EO" prop="eo">
          <el-input v-model="form.eo" placeholder="请输入EO" />
        </el-form-item>
        <el-form-item label="EQ" prop="eq">
          <el-input v-model="form.eq" placeholder="请输入EQ" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">下一页</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改功能点分析对话框2 -->
    <el-dialog :title="title" :visible.sync="open2" width="800px" append-to-body>
      <el-row>
        <el-col :span="12">
          <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="ILF复杂度" prop="valueILF">
              <el-rate
                v-model="valueILF"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="EIF复杂度" prop="valueEIF">
              <el-rate
                v-model="valueEIF"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="EI复杂度" prop="valueEI">
              <el-rate
                v-model="valueEI"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="EO复杂度" prop="valueEO">
              <el-rate
                v-model="valueEO"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="EQ复杂度" prop="valueEQ">
              <el-rate
                v-model="valueEQ"
                show-text>
              </el-rate>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12">
          <el-image :src="UFP_imageUrl">
          </el-image>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">下一页</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改功能点分析对话框3 -->
    <el-dialog :title="title" :visible.sync="open3" width="800px" append-to-body>
      <el-row>
        <el-col :span="12">
          <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="数据通信">
              <el-rate
                v-model="valueGSC1"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="分布式数据处理">
              <el-rate
                v-model="valueGSC2"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="性能">
              <el-rate
                v-model="valueGSC3"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="大业务量配置">
              <el-rate
                v-model="valueGSC4"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="交易处理率">
              <el-rate
                v-model="valueGSC5"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="在线数据输入">
              <el-rate
                v-model="valueGSC6"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="最终用户效率">
              <el-rate
                v-model="valueGSC7"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="在线更新">
              <el-rate
                v-model="valueGSC8"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="复杂处理">
              <el-rate
                v-model="valueGSC9"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="可复用性">
              <el-rate
                v-model="valueGSCA"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="易安装性">
              <el-rate
                v-model="valueGSCB"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="易操作性">
              <el-rate
                v-model="valueGSCC"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="多场地">
              <el-rate
                v-model="valueGSCD"
                show-text>
              </el-rate>
            </el-form-item>
            <el-form-item label="支持变更">
              <el-rate
                v-model="valueGSCCE"
                show-text>
              </el-rate>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12">
          <el-image :src="GSC_imageUrl">
          </el-image>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">完成</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- AI -->
    <el-dialog :title="title" :visible.sync="openAI" width="500px" append-to-body>
      <el-form ref="formAI" :model="formAI" :rules="rules" label-width="100px">
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormAI">提交</el-button>
        <el-button @click="cancelAI">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="openAI2" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="ILF" prop="ilf">
          <el-input v-model="form.ilf" placeholder="请输入ILF" />
        </el-form-item>
        <el-form-item label="EIF" prop="eif">
          <el-input v-model="form.eif" placeholder="请输入EIF" />
        </el-form-item>
        <el-form-item label="EI" prop="ei">
          <el-input v-model="form.ei" placeholder="请输入EI" />
        </el-form-item>
        <el-form-item label="EO" prop="eo">
          <el-input v-model="form.eo" placeholder="请输入EO" />
        </el-form-item>
        <el-form-item label="EQ" prop="eq">
          <el-input v-model="form.eq" placeholder="请输入EQ" />
        </el-form-item>
        <el-form-item label="UFP" prop="ufp">
          <el-input v-model="form.ufp" placeholder="请输入UFP" />
        </el-form-item>
        <el-form-item label="GSC" prop="gsc">
          <el-input v-model="form.gsc" placeholder="请输入GSC" />
        </el-form-item>
        <el-form-item label="TCF" prop="tcf">
          <el-input v-model="form.tcf" placeholder="请输入TCF" />
        </el-form-item>
        <el-form-item label="AFP" prop="afp">
          <el-input v-model="form.afp" placeholder="请输入AFP" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormAI2">确定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="openAdd1" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="项目ID" prop="projectId">
          <el-select v-model="form.projectId"  :disabled="!isEditable">
            <el-option
              v-for="item in projects"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="ILF" prop="ilf">
          <el-input v-model="form.ilf" placeholder="请输入ILF" />
        </el-form-item>
        <el-form-item label="EIF" prop="eif">
          <el-input v-model="form.eif" placeholder="请输入EIF" />
        </el-form-item>
        <el-form-item label="EI" prop="ei">
          <el-input v-model="form.ei" placeholder="请输入EI" />
        </el-form-item>
        <el-form-item label="EO" prop="eo">
          <el-input v-model="form.eo" placeholder="请输入EO" />
        </el-form-item>
        <el-form-item label="EQ" prop="eq">
          <el-input v-model="form.eq" placeholder="请输入EQ" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormAdd">下一页</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listAnalysis, getAnalysis,load, delAnalysis, addAnalysis, updateAnalysis,updateAnalysisAI,viewAnalysisAI,searchAnalysis } from "@/api/evaluate/analysis";
import * as echarts from 'echarts';
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/evaluate/project";
import project from "@/views/evaluate/project/index.vue";
import {update} from "script-ext-html-webpack-plugin/lib/elements";

export default {
  name: "Analysis",
  data() {
    return {
      //下拉框内容
      projects: [
      ],
      //控制是否可以修改
      isEditable:true,
      //AI
      textarea: "图书管理系统",
      doubleArray: [],
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
      open0:false,
      open: false,
      open2: false,
      open3:false,
      openAI:false,
      openAI2:false,
      openAdd1:false,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: ''
      },
      queryParamsAI:{
        pageNum: 1,
        pageSize: 10,
        textarea:"图书管理系统"
      },
      // 表单参数
      formAI:{
        textarea:"图书管理系统",
        projectId:null,
      },
      form: {
        projectId: null,
        projectName:null,
        ilf: null,
        eif: null,
        ei: null,
        eo: null,
        eq: null,
        ufp:null,
        gsc:null,
        tcf:null,
        afp:null,
      },
      // 表单校验
      rules: {
        ilf: [
          { required: true, message: "ILF不能为空", trigger: "blur" }
        ],
        eif: [
          { required: true, message: "EIF不能为空", trigger: "blur" }
        ],
        ei: [
          { required: true, message: "EI不能为空", trigger: "blur" }
        ],
        eo: [
          { required: true, message: "EO不能为空", trigger: "blur" }
        ],
        eq: [
          { required: true, message: "EQ不能为空", trigger: "blur" }
        ],
      },
      chart: null,
      //星星打分
      valueILF: null,
      valueEIF: null,
      valueEI: null,
      valueEO: null,
      valueEQ: null,
      //计算Gsc
      valueGSC1: null,
      valueGSC2: null,
      valueGSC3: null,
      valueGSC4: null,
      valueGSC5: null,
      valueGSC6: null,
      valueGSC7: null,
      valueGSC8: null,
      valueGSC9: null,
      valueGSCA: null,
      valueGSCB: null,
      valueGSCC: null,
      valueGSCD: null,
      valueGSCE: null,
      //图片地址
      UFP_imageUrl:require('@/assets/images/UFP.png'),
      GSC_imageUrl:require('@/assets/images/GSC.png'),
      //计算中间值
      valueILFTemp: null,
      valueEIFTemp: null,
      valueEITemp: null,
      valueEOTemp: null,
      valueEQTemp: null,
      options: [],
      // 当前选中的值
      value: null,
      //复选框
      formInline: {
        user: '',
        region: ''
      },
      //表格
      chartInstance: null, // 用于存储ECharts实例
      selectedRows: [],// 用于存储选中的行数据

    };
  },

  // 初始化图表
  mounted() {
    this.chartInstance = echarts.init(document.getElementById('myChart'));
    this.initChart();
    this.updateChart(); // 初始化图表
  },
  created() {
    this.getList();
  },
  methods: {
    //初始化表格
    updateChart(seriesData) {
      // 定义颜色数组
      const colors = ['#c23531', '#2f4554', '#61a0a8', '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074'];

      // 准备图表系列数据
      const series = seriesData.map((data, index) => ({
        data: data,
        type: 'bar',
        itemStyle: {
          color: colors[index % colors.length] // 循环使用颜色
        }
      }));

      // 更新图表实例的选项
      this.chartInstance.setOption({
        xAxis: {
          type: 'category',
          data: ['ILF', 'EIF', 'EI', 'EO', 'EQ', 'UFP', 'GSC', 'TCF', 'AFP']
        },
        yAxis: {
          type: 'value'
        },
        series: series // 更新系列数据
      });
    },
    /** 查询功能点分析列表 */
    getList() {
      this.loading = true;
      listAnalysis(this.queryParams).then(response => {
        this.analysisList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.updatechart();
      });
    },
    getSearch() {
      this.loading = true;
      searchAnalysis(this.queryParams)
        .then((response) => {
          const {rows, total} = response;
          this.analysisList = rows;
          this.total = total;
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
          this.$message.error("获取数据失败，请稍后重试！");
        });
    },
    getListAI() {
      this.loading = true;
      listAnalysis(this.queryParamsAI).then(response => {
        this.analysisList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    loadOptions() {
      load(this.queryParams)
        .then((response) => {
          console.log(response);  // 检查返回的响应
          this.options = response.map(item => ({
            value: item.project_id,
            label: item.name
          }));
        })
        .catch(() => {
          this.$message.error("加载未定评估项目失败");
        });
    },
    cancel() {
      this.open0 = false;
      this.open = false;
      this.open2 = false;
      this.open3 = false;
      this.reset();
    },
    cancelAI() {
      this.openAI = false;
    },
    // 表单重置
    reset() {
      this.form = {
        projectId: null,
        ilf: null,
        eif: null,
        ei: null,
        eo: null,
        eq: null,
        ufp: null,
        gsc: null,
        tcf: null,
        afp: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getSearch();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.projectName = "";
      this.getSearch();
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.projectId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
      this.selectedRows = selection; // 更新选中的行数据

      // 准备图表数据
      const seriesData = this.selectedRows.map(row => [
        row.ilf,
        row.eif,
        row.ei,
        row.eo,
        row.eq,
        row.ufp,
        row.gsc,
        row.tcf,
        row.afp
      ]);

      // 设置图表选项
      this.updateChart(seriesData);
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open0 = true;
      this.loadOptions();
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
    handleAIUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getAnalysis(projectId).then(response => {
        this.form = response.data;
        this.openAI = true;
        this.title = "AI生成报告";
      });
    },

    /**计算UFP*/
    calculateUFP() {
      //ILF分支
      if (this.valueILF == 1) {
        this.valueILFTemp = 7;
      } else if (this.valueILF == 2 || this.valueILF == 3) {
        this.valueILFTemp = 10;
      } else if (this.valueILF == 4 || this.valueILF == 5) {
        this.valueILFTemp = 15;
      } else {
        this.valueILFTemp = 0;
      }
      //EIF分支
      if (this.valueEIF == 1) {
        this.valueEIFTemp = 5;
      } else if (this.valueEIF == 2 || this.valueEIF == 3) {
        this.valueEIFTemp = 7;
      } else if (this.valueEIF == 4 || this.valueEIF == 5) {
        this.valueEIFTemp = 10;
      } else {
        this.valueEIFTemp = 0;
      }
      //EI分支
      if (this.valueEI == 1) {
        this.valueEITemp = 3;
      } else if (this.valueEI == 2 || this.valueEI == 3) {
        this.valueEITemp = 4;
      } else if (this.valueEI == 4 || this.valueEI == 5) {
        this.valueEITemp = 6;
      } else {
        this.valueEITemp = 0;
      }

      //EO分支
      if (this.valueEO == 1) {
        this.valueEOTemp = 4;
      } else if (this.valueEO == 2 || this.valueEO == 3) {
        this.valueEOTemp = 5;
      } else if (this.valueEO == 4 || this.valueEO == 5) {
        this.valueEOTemp = 7;
      } else {
        this.valueEOTemp = 0;
      }
      //EQ分支
      if (this.valueEQ == 1) {
        this.valueEQTemp = 3;
      } else if (this.valueEQ == 2 || this.valueEQ == 3) {
        this.valueEQTemp = 4;
      } else if (this.valueEQ == 4 || this.valueEQ == 5) {
        this.valueEQTemp = 6;
      } else {
        this.valueEQTemp = 0;
      }

      this.form.ufp = this.valueILFTemp * this.form.ilf +
        this.valueEIFTemp * this.form.eif + this.valueEITemp * this.form.ei +
        this.valueEOTemp * this.form.eo + this.valueEQTemp * this.form.eq;
    },
    submitAdd() {
      if (!this.value) {
        this.$message.warning("请先选择一个项目");
        return;
      }
      this.$message.success(`已选择项目：${this.value}`);

      this.form.projectId = this.value;
      console.log(this.form.projectId);
      addAnalysis(this.form).then(response => {
        this.$modal.msgSuccess("修改成功");
        this.getList();
        this.open0 = false;
      });
      // 这里可以继续触发跳转或其他业务逻辑
    },
    //计算GSC
    calculateGSC() {
      this.form.gsc =
        (this.valueGSC1 || 0) +
        (this.valueGSC2 || 0) +
        (this.valueGSC3 || 0) +
        (this.valueGSC4 || 0) +
        (this.valueGSC5 || 0) +
        (this.valueGSC6 || 0) +
        (this.valueGSC7 || 0) +
        (this.valueGSC8 || 0) +
        (this.valueGSC9 || 0) +
        (this.valueGSCA || 0) +
        (this.valueGSCB || 0) +
        (this.valueGSCC || 0) +
        (this.valueGSCD || 0) +
        (this.valueGSCE || 0);
    },

    //计算TCF
    calculateTCF() {
      this.form.tcf = 0.65 + 0.01 * this.form.gsc;
    },

    //计算AFP
    calculateAFP() {
      this.form.afp = this.form.ufp * this.form.tcf;
    },

    /** 提交AI按钮 */
    submitFormAI() {
      if (this.openAI == true) {
        this.$refs.formAI.validate((valid => {
          if (valid) {
            updateAnalysisAI(this.formAI.textarea).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openAI = false;
              this.getListAI();
              viewAnalysisAI()
                .then((response) => {
                  console.log("完整响应内容：", response);
                  this.doubleArray = response;
                  console.log(this.doubleArray);
                  this.form.ilf = this.doubleArray[0];
                  this.form.eif = this.doubleArray[1];
                  this.form.ei = this.doubleArray[2];
                  this.form.eo = this.doubleArray[3];
                  this.form.eq = this.doubleArray[4];
                  this.form.ufp = this.doubleArray[5];
                  this.form.gsc = this.doubleArray[6];
                  this.form.tcf = this.doubleArray[7];
                  this.form.afp = this.doubleArray[8];
                  this.openAI2 = true;
                });
            });
          }
        }));
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs.form.validate((valid) => {
          if (valid) {
            // if (this.form.projectId != null) {
            if (this.open == true) {
              updateAnalysis(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
                this.open2 = true;
              });
            } else if (this.open2 == true) {
              this.calculateUFP();
              updateAnalysis(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open2 = false;
                this.getList();
                this.open3 = true;
              });
            } else if (this.open3 == true) {
              this.calculateGSC();
              this.calculateTCF();
              this.calculateAFP();
              updateAnalysis(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open3 = false;
                this.getList();
              });
              // }
            }
          }
        }
      );
    },
    submitFormAI2() {
      this.$refs.form.validate((valid) => {

        if (valid) {
          if (this.form.projectId != null) {
            this.openAI2 = false;
            updateAnalysis(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openAI2 = false;
              this.getList();
            });
          }
        } else {
          console.log("sorry");
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除功能点分析编号为"' + projectIds + '"的数据项？').then(function () {
        return delAnalysis(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('evaluate/analysis/export', {
        ...this.queryParams
      }, `analysis_${new Date().getTime()}.xlsx`)
    },

    // 动态生成项目名称
    generateProjectNames() {
      return this.analysisList.map((_, index) => `项目${index + 1}`);
    },
// 初始化 ECharts 图表
    initChart() {
      const histogramElement = document.getElementById("histogram");
      this.chart = echarts.init(histogramElement);
    },
// 更新 ECharts 图表
    updatechart() {
      // 动态生成项目名称
      const projectNames = this.generateProjectNames();

      // 根据每个项目计算不同类型功能点的数量
      const ilfData = this.analysisList.map((item) => item.ilf || 0);
      const eifData = this.analysisList.map((item) => item.eif || 0);
      const eiData = this.analysisList.map((item) => item.ei || 0);
      const eoData = this.analysisList.map((item) => item.eo || 0);
      const eqData = this.analysisList.map((item) => item.eq || 0);
      const afpData = this.analysisList.map((item) => item.afp || 0); // 新增 AFP 数据

      // 配置柱状图
      const option = {
        title: {
          text: "功能点分析直方图",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        legend: {
          data: ["ILF", "EIF", "EI", "EO", "EQ", "AFP"], // 增加 AFP 图例
          bottom: "5%",
        },
        xAxis: {
          type: "category",
          data: projectNames, // 使用动态生成的项目名称
        },
        yAxis: {
          type: "value",
          name: "数量",
        },
        series: [
          {
            name: "ILF",
            type: "bar",
            data: ilfData,
            color: "#1f77b4",
          },
          {
            name: "EIF",
            type: "bar",
            data: eifData,
            color: "#ff7f0e",
          },
          {
            name: "EI",
            type: "bar",
            data: eiData,
            color: "#2ca02c",
          },
          {
            name: "EO",
            type: "bar",
            data: eoData,
            color: "#d62728",
          },
          {
            name: "EQ",
            type: "bar",
            data: eqData,
            color: "#9467bd",
          },
          {
            name: "AFP", // 新增 AFP 数据配置
            type: "bar",
            data: afpData,
            color: "#8c564b",
          },
        ],
      };

      // 更新图表
      this.chart.setOption(option);
    },
  }
};
</script>
