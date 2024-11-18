<template>
  <div class="app-container">
    <!-- 左侧报告展示 -->
    <div class="report-container">
      <h3>报告内容</h3>
      <div class="report-content">
        <!-- 判断 reportContent 是否为空，如果为空则显示 "生成中..." -->
        <pre v-if="!reportContent">生成中...</pre>
        <pre v-else>{{ reportContent }}</pre>
      </div>
    </div>


    <!-- 右侧聊天界面 -->
    <div class="chat-container">
      <!-- 消息展示区域 -->
      <div class="chat-box">
        <div
          v-for="message in messages"
          :key="message.id"
          class="message"
          :class="message.from === 'user' ? 'user-message' : 'ai-message'"
        >
          <p>{{ message.content }}</p>
        </div>
      </div>

      <!-- 输入框与发送按钮 -->
      <div class="input-container">
        <el-input
          v-model="userInput"
          placeholder="请输入消息..."
          clearable
          @keyup.enter.native="sendMessage"
          class="chat-input"
        />
        <el-button type="primary" icon="el-icon-send" @click="sendMessage" class="send-button">发送</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { longPolling, send, createReport } from "@/api/evaluate/project";

export default {
  name: "Project",
  data() {
    return {
      messages: [], // 消息记录
      userInput: "", // 用户输入
      pollingActive: false, // 是否正在长轮询
      isEnd: false, // 标记是否结束轮询
      currentAiMessageId: null, // 当前正在回复的 AI 消息的 ID
      userMsgData: {}, // 用户消息数据
      reportContent: "", // 存储报告内容
    };
  },
  async mounted() {
    // 从 localStorage 中读取 projectID
    const projectID = localStorage.getItem("projectID");
    if (projectID) {
      this.projectID = projectID;

      // 获取报告内容并赋值
      try {
        createReport(projectID).then(response => {
          this.reportContent = response.content; // 假设返回的内容在 res.content 中
        });
        // const res = await createReport(projectID);
        // this.reportContent = res.content; // 假设返回的内容在 res.content 中
      } catch (error) {
        console.error("获取报告失败:", error);
      }
    } else {
      console.error("无法获取 projectID！");
    }
  },
  methods: {
    sendMessage() {
      if (!this.userInput.trim()) return;

      // 添加用户消息
      this.messages.push({
        id: Date.now(),
        content: this.userInput,
        from: "user",
      });

      this.userMsgData.content = this.userInput;
      send(this.userMsgData);

      // 清空输入框
      this.userInput = "";

      // 添加 AI 回复占位
      const newAiMessage = {
        id: Date.now() + 1,
        content: "",
        from: "ai",
      };
      this.messages.push(newAiMessage);

      this.currentAiMessageId = newAiMessage.id;

      // 启动轮询
      if (this.isEnd || !this.pollingActive) {
        this.isEnd = false;
        this.pollingActive = true;
        this.polling();
      }
    },
    async polling() {
      try {
        const response = await longPolling();
        let newMessageContent = response.content.trim();

        const aiMessage = this.messages.find(
          (msg) => msg.id === this.currentAiMessageId
        );

        if (aiMessage) {
          aiMessage.content = `${aiMessage.content}${newMessageContent}`.trim();
        }

        if (response.end) {
          this.isEnd = true;
          this.pollingActive = false;
        } else if (this.pollingActive) {
          this.polling();
        }
      } catch (error) {
        console.error("长轮询失败:", error);
        if (this.pollingActive) {
          setTimeout(this.polling, 5000);
        }
      }
    },
  },
};
</script>
<style scoped>
/* 总容器：左右布局 */
.app-container {
  display: flex;
  height: 100vh;
  background-color: #f3f4f6;
  font-family: "Arial", sans-serif;
}

/* 左侧报告容器 */
.report-container {
  flex: 1; /* 左侧占比 */
  padding: 20px;
  border-right: 1px solid #ddd;
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

.report-content {
  white-space: pre-wrap; /* 保留换行 */
  font-size: 14px;
  line-height: 1.6;
  color: #333;
  background-color: #fff;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

/* 右侧聊天容器 */
.chat-container {
  flex: 1; /* 右侧占比 */
  display: flex;
  flex-direction: column;
  border-left: 1px solid #ddd;
  background-color: #fff;
  overflow: hidden;
}

.chat-box {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background-color: #fafafa;
  display: flex;
  flex-direction: column;
}

/* 通用消息样式 */
.message {
  margin: 10px 0;
  padding: 10px;
  max-width: 70%;
  word-wrap: break-word;
  border-radius: 8px;
}

/* 用户消息：右对齐 */
.user-message {
  align-self: flex-end;
  background-color: #e0f7fa;
  text-align: left;
}

/* AI 消息：左对齐 */
.ai-message {
  align-self: flex-start;
  background-color: #f1f1f1;
  text-align: left;
}

/* 输入框和发送按钮 */
.input-container {
  display: flex;
  padding: 10px;
  border-top: 1px solid #e0e0e0;
  background-color: #f9f9f9;
}

.chat-input {
  flex: 1;
  margin-right: 10px;
}

.send-button {
  flex-shrink: 0;
}
</style>
