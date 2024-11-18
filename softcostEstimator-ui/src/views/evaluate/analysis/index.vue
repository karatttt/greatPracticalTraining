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
        <el-col :span="24">
          <el-table v-loading="loading" :data="analysisList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="项目ID" align="center" prop="projectId" />
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
        <el-button type="primary" @click="submitForm">下一页</el-button>
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
      open2: false,
      open3:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ilf: null,
        eif: null,
        ei: null,
        eo: null,
        eq: null,
        ufp: null,
        gsc: null,
        tcf: null,
        afp: null
      },
      // 表单参数
      form: {
        projectId: null,
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
      this.open2 = false;
      this.open3 = false;
      this.reset();
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
        ufp:null,
        gsc:null,
        tcf:null,
        afp:null,
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

    /**计算UFP*/
    calculateUFP(){
      //ILF分支
      if(this.valueILF == 1){
        this.valueILFTemp=7;
      }else if(this.valueILF == 2||this.valueILF == 3){
        this.valueILFTemp=10;
      }else if(this.valueILF == 4||this.valueILF == 5){
        this.valueILFTemp=15;
      }
      else{
        this.valueILFTemp=0;
      }
      //EIF分支
      if(this.valueEIF == 1){
        this.valueEIFTemp=5;
      }else if(this.valueEIF == 2||this.valueEIF == 3){
        this.valueEIFTemp=7;
      }else if(this.valueEIF == 4||this.valueEIF == 5){
        this.valueEIFTemp=10;
      }
      else{
        this.valueEIFTemp=0;
      }
      //EI分支
      if(this.valueEI == 1){
        this.valueEITemp=3;
      }else if(this.valueEI == 2||this.valueEI == 3){
        this.valueEITemp=4;
      }else if(this.valueEI == 4||this.valueEI == 5){
        this.valueEITemp=6;
      }else{
        this.valueEITemp=0;
      }

      //EO分支
      if(this.valueEO == 1){
        this.valueEOTemp=4;
      }else if(this.valueEO == 2||this.valueEO == 3){
        this.valueEOTemp=5;
      }else if(this.valueEO == 4||this.valueEO == 5){
        this.valueEOTemp=7;
      }else{
        this.valueEOTemp=0;
      }
      //EQ分支
      if(this.valueEQ == 1){
        this.valueEQTemp=3;
      }else if(this.valueEQ == 2||this.valueEQ == 3){
        this.valueEQTemp=4;
      }else if(this.valueEQ == 4||this.valueEQ == 5){
        this.valueEQTemp=6;
      }else{
        this.valueEQTemp=0;
      }

      this.form.ufp=this.valueILFTemp*this.form.ilf+
        this.valueEIFTemp*this.form.eif+this.valueEITemp*this.form.ei+
        this.valueEOTemp*this.form.eo+this.valueEQTemp*this.form.eq;
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
    calculateTCF(){
      this.form.tcf=0.65+0.01*this.form.gsc;
    },

    //计算AFP
    calculateAFP(){
      this.form.afp=this.form.ufp*this.form.tcf;
    },

    /** 提交按钮 */
    submitForm() {
        this.$refs.form.validate((valid)=> {
            if (valid) {
              if (this.form.projectId != null) {
                if(this.open == true){
                updateAnalysis(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                  this.open2 = true;
                });
                }else if(this.open2 == true){
                  this.calculateUFP();
                  updateAnalysis(this.form).then(response => {
                    this.$modal.msgSuccess("修改成功");
                    this.open2 = false;
                    this.getList();
                    this.open3 = true;
                  });
                }else if(this.open3 == true){
                  this.calculateGSC();
                  this.calculateTCF();
                  this.calculateAFP();
                  updateAnalysis(this.form).then(response => {
                    this.$modal.msgSuccess("修改成功");
                    this.open3 = false;
                    this.getList();
                  });
                }
              }
            }
          }
        );
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

  }
};
</script>
