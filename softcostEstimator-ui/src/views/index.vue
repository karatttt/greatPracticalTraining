<template>
  <div class="app-container home">
    <el-col :sm="30" :lg="24" style="padding-left: 20px;padding-top: 20px">
      <h2>软件造价通softcostEstimator</h2>
      <p>
        该项目于 11 月 12 日启动，旨在构建一个基于若依后台管理系统的 AI+ 软件成本评估系统：软件造价通softcostEstimator，专注于租户和项目管理子系统、功能点分析子系统、造价综合评估子系统和报告生成与展示子系统四个子系统。 本项目利用人工智能技术（特别是大语言模型中的多智能体编排）来优化项目展演过程，提供高效的功能点分析、造价综合评估和报告生成与展示，并使用阿里云数据库存储确保数据打通、功能连贯、风格一致。
      </p>
      <p>
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-cloudy"
          plain
          @click="goTarget('https://gitcode.com/zzq20226705/greatPracticalTraining/overview')"
        >访问Code</el-button
        >
        <el-button
          size="mini"
          icon="el-icon-s-home"
          plain
          @click="goTarget('http://ruoyi.vip')"
        >访问主页</el-button
        >
      </p>
    </el-col>
    <el-divider />
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.8"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

