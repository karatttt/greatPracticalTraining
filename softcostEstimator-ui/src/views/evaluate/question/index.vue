<template>
  <div class="app-container">
    <!-- 左侧报告展示 -->
    <div class="report-container">
      <h3>报告内容</h3>
      <div class="report-content">
        <!-- 可编辑文本区域 -->
        <textarea
          v-model="reportContent"
          class="editable-report"
          placeholder="AI软件造价通报告生成专家正在生成中，请耐心等待..."
        ></textarea>

        <!-- 生成报告按钮 -->
        <el-button
          v-model="reportContent"
          type="primary"
          class="generate-report-btn"
          @click="generateReport"
        >
          生成报告
        </el-button>
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
import { longPolling, send, createReport ,pollReport} from "@/api/evaluate/project";

export default {
  name: "Project",
  data() {
    return {
      messages: [], // 消息记录
      userInput: "你好", // 用户输入
      pollingActive: false, // 是否正在长轮询
      pollingActive2: false, // 是否正在长轮询
      isEnd: false, // 标记是否结束轮询
      isEnd2: false, // 标记是否结束轮询
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
          this.sendMessage();

          createReport(projectID);
          if (this.isEnd2 || !this.pollingActive2) {
            this.isEnd2 = false;
            this.pollingActive2 = true;
            this.polling2();
          }

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
    generateReport() {
      //console.error(reportContent);
      // this.download('evaluate/project/export', {
      //   ...this.reportContent
      // }, `report_${new Date().getTime()}.md`)
      if (!this.reportContent) {
        console.error("报告内容为空，无法生成文件！");
        return;
      }
      this.reportContent += "\n![图片](https://i.ibb.co/G0J0gD4/2024-11-15-15-08-34.png)"

      // 将 reportContent 内容转为 Blob 对象
      const blob = new Blob([this.reportContent], { type: "text/markdown;charset=utf-8" });

      // 创建文件名，格式为 report_<时间戳>.md
      const fileName = `report_${new Date().getTime()}.md`;

      // 创建一个下载链接并触发下载
      const link = document.createElement("a");
      link.href = URL.createObjectURL(blob);
      link.download = fileName;
      document.body.appendChild(link); // 必须将链接添加到 DOM 中才能触发点击
      link.click();
      document.body.removeChild(link); // 下载完成后移除链接
      URL.revokeObjectURL(link.href); // 释放 Blob 对象的 URL
    },
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
    async polling2() {
      try {
        const response = await pollReport();

        // 将数据追加到 reportContent
        this.reportContent += response.content; // 假设 response.data 是字符串

        if (response.end) {
          this.isEnd2 = true;
          this.pollingActive2 = false;
        } else if (this.pollingActive2) {
          this.polling2();
        }
      } catch (error) {
        console.error("长轮询失败:", error);
        if (this.pollingActive2) {
          setTimeout(this.polling, 5000);
        }
      }
    }
  },
};
</script>
<style scoped>
/* 总容器：左右布局 */
.app-container {
  display: flex;
  height: 90vh;
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

/* 可编辑文本区域 */
.editable-report {
  width: 100%;
  height: 300px;
  font-size: 14px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  resize: none; /* 禁止调整大小 */
  box-sizing: border-box;
  margin-bottom: 20px;
}

/* 可编辑文本区域 */
.editable-report {
  width: 100%; /* 宽度设置为容器的 100% */
  height: calc(100vh - 205px); /* 高度根据页面高度动态调整 */
  font-size: 16px; /* 字体调大，方便阅读 */
  padding: 20px; /* 内边距更宽敞 */
  border: 1px solid #ddd; /* 边框样式 */
  border-radius: 8px; /* 圆角边框 */
  resize: none; /* 禁止调整大小 */
  box-sizing: border-box; /* 包含内边距和边框 */
  margin-bottom: 20px; /* 底部留空间 */
  overflow-y: auto; /* 如果内容超出，显示滚动条 */
}

</style>
