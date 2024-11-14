<template>
  <div class="app-container home">
    <el-col :sm="30" :lg="24" class="app-content">
    </el-col>
    <el-divider />
    <div align="center">
      <img alt="Logo" src="https://i.ibb.co/RC5jjDh/GitHub.png" width="20%" />
      <h1>软件造价通 SoftcostEstimator</h1>
    </div>

    <p align="center">
      <img alt="release" src="https://img.shields.io/badge/release-v0.0.1-brightgreen" />
      <img alt="java" src="https://img.shields.io/badge/java-v23.0.1-brightgreen" />
      <img alt="state" src="https://img.shields.io/badge/state-孵化-brightgreen" />
      <img alt="domain" src="https://img.shields.io/badge/domain-AI+软件造价评估-brightgreen" />
      <img alt="PRs welcome" src="https://img.shields.io/badge/PRs-welcome-brightgreen" />
      <img alt="docs" src="https://img.shields.io/badge/docs-latest-blue" />
      <img alt="code style" src="https://img.shields.io/badge/code%20style-black-000000" />
    </p>

    <section class="project-details">
      <h3>📘 项目介绍</h3>
      <p>
        该项目于 11 月 12 日启动，旨在构建一个基于若依后台管理系统的 AI+ 软件成本评估系统：软件造价通 SoftcostEstimator，专注于租户和项目管理、功能点分析、造价综合评估和报告生成与展示四个子系统。
      </p>
      <p>项目地址：<a href="https://gitcode.com/zzq20226705/greatPracticalTraining.git" target="_blank">伟大的实训</a></p>
      <p>项目文档：<a href="https://gitcode.com/zzq20226705/greatPracticalTraining/overview" target="_blank">项目文档</a></p>
    </section>

    <section class="main-features">
      <h3>🔧 主要功能</h3>
      <ol>
        <li><b>租户和项目管理子系统：</b> 支持多租户环境，确保数据的安全性和隐私性，提升协作效率。</li>
        <li><b>功能点分析子系统：</b> 评估师可以录入和管理软件项目的功能结构，提供基础数据。</li>
        <li><b>造价综合评估子系统：</b> 基于行业基准数据或历史项目数据，提供综合造价评估。</li>
        <li><b>报告生成与展示子系统：</b> 支持生成详细报告，并通过数据可视化展示进度和评估结果。</li>
      </ol>
    </section>

    <section class="tech-features">
      <h3>🛠 技术特点</h3>
      <ul>
        <li><b>前后端分离：</b> 提高开发效率，便于维护和扩展。</li>
        <li><b>AI+：</b> 集成人工智能技术，便于扩展与维护。</li>
        <li><b>数据云端存储：</b> 提升数据安全性和可靠性。</li>
      </ul>
    </section>

    <section class="contributing">
      <h3>🤝 贡献指南</h3>
      <p>欢迎贡献者按照以下方式提交贡献。</p>
      <ol>
        <li>Fork 本仓库</li>
        <li>创建特性分支</li>
        <li>提交更改并推送到分支</li>
        <li>提交 Pull Request</li>
      </ol>
    </section>
    <p>
      <el-button
        type="primary"
        size="mini"
        icon="el-icon-cloudy"
        plain
        @click="goTarget('https://gitcode.com/zzq20226705/greatPracticalTraining/overview')"
      >访问 Code</el-button>
      <el-button
        size="mini"
        icon="el-icon-s-home"
        plain
        @click="goTarget('https://gitcode.com/zzq20226705/')"
      >访问主页</el-button>
    </p>

  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      version: "3.8.8",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.app-container {
  padding: 20px;

  h2 {
    font-size: 26px;
    font-weight: 400;
    margin-top: 10px;
  }

  p {
    margin-top: 10px;
    font-size: 14px;
  }

  .project-details, .main-features, .tech-features, .contributing {
    margin-top: 20px;
    h3 {
      font-size: 22px;
      color: #333;
      margin-bottom: 10px;
    }
    p, ol, ul {
      font-size: 14px;
      margin-left: 10px;
    }
  }
}
</style>
