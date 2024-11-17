<!--<template>-->
<!--  <div class="app-container">-->
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="名称" prop="name">-->
<!--        <el-input-->
<!--          v-model="queryParams.name"-->
<!--          placeholder="请输入名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="createTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.createTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择创建时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="结束时间" prop="endTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.endTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择结束时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="评估员" prop="appraiserID">-->
<!--        <el-input-->
<!--          v-model="queryParams.appraiserID"-->
<!--          placeholder="请输入评估员"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="审核员" prop="auditorID">-->
<!--        <el-input-->
<!--          v-model="queryParams.auditorID"-->
<!--          placeholder="请输入审核员"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="租户" prop="tenantryID">-->
<!--        <el-input-->
<!--          v-model="queryParams.tenantryID"-->
<!--          placeholder="请输入租户"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['evaluate:project:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['evaluate:project:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['evaluate:project:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['evaluate:project:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

<!--    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="项目ID" align="center" prop="projectID" />-->
<!--      <el-table-column label="名称" align="center" prop="name" />-->
<!--      <el-table-column label="项目需求" align="center" prop="requireContent" />-->
<!--      <el-table-column label="创建时间" align="center" prop="createTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="结束时间" align="center" prop="endTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="评估员" align="center" prop="appraiserID" />-->
<!--      <el-table-column label="审核员" align="center" prop="auditorID" />-->
<!--      <el-table-column label="租户" align="center" prop="tenantryID" />-->
<!--      <el-table-column label="描述" align="center" prop="description" />-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['evaluate:project:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['evaluate:project:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    -->
<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

<!--    &lt;!&ndash; 添加或修改项目信息对话框 &ndash;&gt;-->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="名称" prop="name">-->
<!--          <el-input v-model="form.name" placeholder="请输入名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="项目需求">-->
<!--          <editor v-model="form.requireContent" :min-height="192"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="结束时间" prop="endTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.endTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择结束时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="评估员" prop="appraiserID">-->
<!--          <el-input v-model="form.appraiserID" placeholder="请输入评估员" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="审核员" prop="auditorID">-->
<!--          <el-input v-model="form.auditorID" placeholder="请输入审核员" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="租户" prop="tenantryID">-->
<!--          <el-input v-model="form.tenantryID" placeholder="请输入租户" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="描述" prop="description">-->
<!--          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { listProject, getProject, delProject, addProject, updateProject } from "@/api/evaluate/project";-->

<!--export default {-->
<!--  name: "Project",-->
<!--  data() {-->
<!--    return {-->
<!--      // 遮罩层-->
<!--      loading: true,-->
<!--      // 选中数组-->
<!--      ids: [],-->
<!--      // 非单个禁用-->
<!--      single: true,-->
<!--      // 非多个禁用-->
<!--      multiple: true,-->
<!--      // 显示搜索条件-->
<!--      showSearch: true,-->
<!--      // 总条数-->
<!--      total: 0,-->
<!--      // 项目信息表格数据-->
<!--      projectList: [],-->
<!--      // 弹出层标题-->
<!--      title: "",-->
<!--      // 是否显示弹出层-->
<!--      open: false,-->
<!--      // 查询参数-->
<!--      queryParams: {-->
<!--        pageNum: 1,-->
<!--        pageSize: 10,-->
<!--        name: null,-->
<!--        requireContent: null,-->
<!--        createTime: null,-->
<!--        endTime: null,-->
<!--        appraiserID: null,-->
<!--        auditorID: null,-->
<!--        tenantryID: null,-->
<!--        description: null-->
<!--      },-->
<!--      // 表单参数-->
<!--      form: {},-->
<!--      // 表单校验-->
<!--      rules: {-->
<!--        name: [-->
<!--          { required: true, message: "名称不能为空", trigger: "blur" }-->
<!--        ],-->
<!--        createTime: [-->
<!--          { required: true, message: "创建时间不能为空", trigger: "blur" }-->
<!--        ],-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    this.getList();-->
<!--  },-->
<!--  methods: {-->
<!--    /** 查询项目信息列表 */-->
<!--    getList() {-->
<!--      this.loading = true;-->
<!--      listProject(this.queryParams).then(response => {-->
<!--        this.projectList = response.rows;-->
<!--        this.total = response.total;-->
<!--        this.loading = false;-->
<!--      });-->
<!--    },-->
<!--    // 取消按钮-->
<!--    cancel() {-->
<!--      this.open = false;-->
<!--      this.reset();-->
<!--    },-->
<!--    // 表单重置-->
<!--    reset() {-->
<!--      this.form = {-->
<!--        projectID: null,-->
<!--        name: null,-->
<!--        requireContent: null,-->
<!--        createTime: null,-->
<!--        endTime: null,-->
<!--        appraiserID: null,-->
<!--        auditorID: null,-->
<!--        tenantryID: null,-->
<!--        description: null-->
<!--      };-->
<!--      this.resetForm("form");-->
<!--    },-->
<!--    /** 搜索按钮操作 */-->
<!--    handleQuery() {-->
<!--      this.queryParams.pageNum = 1;-->
<!--      this.getList();-->
<!--    },-->
<!--    /** 重置按钮操作 */-->
<!--    resetQuery() {-->
<!--      this.resetForm("queryForm");-->
<!--      this.handleQuery();-->
<!--    },-->
<!--    // 多选框选中数据-->
<!--    handleSelectionChange(selection) {-->
<!--      this.ids = selection.map(item => item.projectID)-->
<!--      this.single = selection.length!==1-->
<!--      this.multiple = !selection.length-->
<!--    },-->
<!--    /** 新增按钮操作 */-->
<!--    handleAdd() {-->
<!--      this.reset();-->
<!--      this.open = true;-->
<!--      this.title = "添加项目信息";-->
<!--    },-->
<!--    /** 修改按钮操作 */-->
<!--    handleUpdate(row) {-->
<!--      this.reset();-->
<!--      const projectID = row.projectID || this.ids-->
<!--      getProject(projectID).then(response => {-->
<!--        this.form = response.data;-->
<!--        this.open = true;-->
<!--        this.title = "修改项目信息";-->
<!--      });-->
<!--    },-->
<!--    /** 提交按钮 */-->
<!--    submitForm() {-->
<!--      this.$refs["form"].validate(valid => {-->
<!--        if (valid) {-->
<!--          if (this.form.projectID != null) {-->
<!--            updateProject(this.form).then(response => {-->
<!--              this.$modal.msgSuccess("修改成功");-->
<!--              this.open = false;-->
<!--              this.getList();-->
<!--            });-->
<!--          } else {-->
<!--            addProject(this.form).then(response => {-->
<!--              this.$modal.msgSuccess("新增成功");-->
<!--              this.open = false;-->
<!--              this.getList();-->
<!--            });-->
<!--          }-->
<!--        }-->
<!--      });-->
<!--    },-->
<!--    /** 删除按钮操作 */-->
<!--    handleDelete(row) {-->
<!--      const projectIDs = row.projectID || this.ids;-->
<!--      this.$modal.confirm('是否确认删除项目信息编号为"' + projectIDs + '"的数据项？').then(function() {-->
<!--        return delProject(projectIDs);-->
<!--      }).then(() => {-->
<!--        this.getList();-->
<!--        this.$modal.msgSuccess("删除成功");-->
<!--      }).catch(() => {});-->
<!--    },-->
<!--    /** 导出按钮操作 */-->
<!--    handleExport() {-->
<!--      this.download('evaluate/project/export', {-->
<!--        ...this.queryParams-->
<!--      }, `project_${new Date().getTime()}.xlsx`)-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->


<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <!-- 左侧内容（保持不变） -->
      <el-col :span="12">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <!-- 您的原始搜索表单内容... -->
        </el-form>
      </el-col>

      <!-- 右侧对话区域 -->
      <el-col :span="12">
        <div class="chat-container">
          <div class="chat-box">
            <!-- 消息展示 -->
            <div v-for="message in messages" :key="message.id" class="message" :class="message.from === 'user' ? 'user-message' : 'ai-message'">
              <p v-if="message.from === 'user'">{{ message.content }}</p>
              <p v-else>{{ message.content }}</p>
            </div>
          </div>
          <!-- 输入框和发送按钮 -->
          <el-input
            v-model="userInput"
            placeholder="请输入消息..."
            clearable
            @keyup.enter.native="sendMessage"
            class="chat-input"
          />
          <el-button type="primary" icon="el-icon-send" @click="sendMessage" class="send-button">发送</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { longPolling , send } from "@/api/evaluate/project"; // 引入长轮询函数

export default {
  name: "Project",
  data() {
    return {
      messages: [], // 消息记录
      userInput: '', // 用户输入
      pollingActive: false, // 是否正在长轮询
      isEnd: false, // 标记是否结束轮询
      currentAiMessageId: null, // 当前正在回复的 AI 消息的 ID
      userMsgData: {},
      // 原有内容
      showSearch: true,
      queryParams: {}
    };
  },

  methods: {
    // 用户发送消息
    sendMessage() {
      if (!this.userInput.trim()) {
        return; // 如果输入为空，不发送
      }

      // 将用户消息添加到聊天框
      this.messages.push({
        id: Date.now(),
        content: this.userInput,
        from: "user"
      });

      this.userMsgData.content = this.userInput
      send(this.userMsgData);

      // 清空输入框
      this.userInput = "";

      // 创建一个新的空 AI 消息对象
      const newAiMessage = {
        id: Date.now() + 1, // 唯一 ID
        content: "",
        from: "ai"
      };
      this.messages.push(newAiMessage);

      // 设置当前 AI 消息 ID
      this.currentAiMessageId = newAiMessage.id;

      // 如果轮询已停止，重新启动
      if (this.isEnd || !this.pollingActive) {
        this.isEnd = false; // 重置结束标记
        this.pollingActive = true; // 标记为轮询已激活
        this.polling(); // 开始轮询
      }
    },

    async polling() {
      try {
        // 发起长轮询请求
        const response = await longPolling();

        // 去掉返回内容的首尾多余空格
        let newMessageContent = response.content.trim();

        // 找到当前 AI 回复的消息
        const aiMessage = this.messages.find(
          (msg) => msg.id === this.currentAiMessageId
        );

        // 如果找到当前 AI 消息，则追加内容
        if (aiMessage) {
          aiMessage.content = `${aiMessage.content}${newMessageContent}`.trim(); // 逐步追加内容
        }

        // 检查是否结束轮询
        if (response.end) {
          this.isEnd = true; // 设置结束标记
          this.pollingActive = false; // 停止轮询
        } else if (this.pollingActive) {
          this.polling(); // 继续轮询
        }
      } catch (error) {
        console.error("长轮询失败:", error);

        // 如果轮询失败，稍后重试
        if (this.pollingActive) {
          setTimeout(this.polling, 5000);
        }
      }
    }
  }
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.chat-box {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  border: 1px solid #ccc;
  background-color: #f9f9f9;
  margin-bottom: 10px;
}

.message {
  margin: 10px 0;
}
.user-message {
  text-align: right; /* 消息容器右对齐 */
}
.user-message p {
  text-align: right;
  background-color: #e0f7fa; /* 用户消息背景颜色 */
  padding: 5px 10px;
  border-radius: 10px;
  display: inline-block;
  max-width: 70%;
  margin-left: auto; /* 将消息框向右对齐 */
}

.ai-message p {
  text-align: left;
  background-color: #f1f1f1;
  padding: 5px 10px;
  border-radius: 10px;
  display: inline-block;
  max-width: 70%;
}

.chat-input {
  margin-bottom: 10px;
}

.send-button {
  align-self: flex-end;
}
</style>
