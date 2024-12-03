<div align="center">
  <img alt="Logo" src="https://i.ibb.co/RC5jjDh/GitHub.png" width="20%" />
  <h1>软件造价通softcostEstimator</h1>
</div>

<p align="center">
  <img alt="release" src="https://img.shields.io/badge/release-v0.0.1-brightgreen" />
  <img alt="python" src="https://img.shields.io/badge/java-v23.0.1-brightgreen" />
  <img alt="state" src="https://img.shields.io/badge/state-孵化-brightgreen" />
  <img alt="domain" src="https://img.shields.io/badge/domain-AI+软件造价评估-brightgreen" />
  <img alt="PRs welcome" src="https://img.shields.io/badge/PRs-welcome-brightgreen" />
  <img alt="docs" src="https://img.shields.io/badge/docs-latest-blue" />
  <img alt="code style" src="https://img.shields.io/badge/code%20style-black-000000" />
</p>

---

## 📘 项目介绍
该项目于 11 月 12 日启动，旨在构建一个基于若依后台管理系统的 AI+ 软件成本评估系统：软件造价通softcostEstimator，专注于租户和项目管理子系统、功能点分析子系统、造价综合评估子系统和报告生成与展示子系统四个子系统。

项目地址：
[伟大的实训](https://gitcode.com/zzq20226705/greatPracticalTraining.git)
[项目文档](https://gitcode.com/zzq20226705/greatPracticalTraining/overview)

本项目利用人工智能技术（特别是大语言模型中的多智能体编排）来优化项目展演过程，提供高效的功能点分析、造价综合评估和报告生成与展示，并使用阿里云数据库存储确保数据打通、功能连贯、风格一致。

---

## 🔧 主要功能
1. **租户和项目管理子系统：**
- 系统支持多租户环境，租户可以管理其团队成员（评估师、审核员）的角色和权限，确保数据的安全性和隐私性。
- 租户可以邀请团队成员加入项目，共同进行项目成本估算和管理，提高团队协作效率。
- 租户可以创建新的软件造价评估项目，并配置项目的基本信息，如项目名称、项目需求、时间等，并可以分配评估师和审核员。
2. **功能点分析子系统：**
- 评估师可以录入和管理软件项目的功能结构，为后续的造价评估提供基础数据。
- 评估师基于软件项目的功能需求，对软件规模进行度量。这通常包括识别内部逻辑文件（ILF）、外部接口文件（EIF）、外部输入（EI）、外部输出（EO）以及外部查询（EQ）等功能点，并根据预设的参数计算未调整的功能点数量。
- 参考GSC和规模变更调整因子两种方法，计算调整的功能点数。
3. **造价综合评估子系统：**
- 评估师基于行业基准数据或团队历史项目数据确定PDR取值。对于基于行业数据，评估师可以根据项目需求，以三点法确定PDF，对于基于团队历史项目，评估师可以查看其他项目的PDR取值（如果已经被评估），根据取值确定该项目的PDR；
- 考虑各种影响软件造价的因素（软件因素、开发因素和PDR等），评估师可以计算软件项目工作量。我们查阅了《软件研发成本度量规范》得到了确定一些软件开发因子的标准，评估师在评估时，可以根据项目需求选择对应的标准来确定因子，也可以自己根据需求进行手动调节，同时系统会自动进行中间变量的计算。
- 参考当年发布的行业区域人员成本，以及项目的风险和质量因素，评估师可以计算软件项目综合造价。我们将当年发布的行业区域人员成本存储起来，在评估的时候可以选择对应城市的人员成本，在风险评估方面，我们定义了项目可能会遇到的一些风险，评估师给一个数值，系统可以计算总风险的强度。
- 其他方面上，我们做了数据库的连接，在创建一个项目时，评估师的评估列表里同样会有新增加的待评估项目，未评估的项目会显示“去评估”，点击后跳转到评估页面，评估后显示已评估，评估师可以重新评估。
- 评估过程中评估师可以随时查看项目需求，根据需求进行各种变量的确定。
- ai加入，评估师可以将需求交给ai，借助ai进行因子的确定，为评估师提供一个参考。
4. **报告生成与展示子系统：**
- 评估师可以生成详细的软件造价评估报告（Markdown版），报告内容包括项目基本信息、功能点分析、调整因子设置、造价评估结果等。
- 审核员可以评审报告。
- 系统提供软件造价数据看板，租户可以通过图表、折线图等形式直观地查看各个评估项目的进度状态、评估结果、任务分配等数据。
- 项目报告使用AI生成，上传后，通过AI报告美化和导出，并生成图片。
- 可以进行AI智能问答，通过提问获取项目的信息。

---

## 🛠 技术特点
- **前后端分离**：提高开发效率，便于维护和扩展。
- **AI+**：集成人工智能技术，便于扩展与维护。
- **数据云端存储**：优化模型推理速度，提升数据安全性和可靠性。
- **自定义配置**：支持自定义评分标准与题目模板。

---

## 📞 交流方式
- **邮箱**：zzq18368326181@icloud.com
- **项目 Issues**：欢迎在 GitCode 提交问题与建议。

---

## 🚀 快速开始

### 1. **克隆项目**
   ```bash
   git clone https://gitcode.com/zzq20226705/greatPracticalTraining.git
   ```

### 2. **准备环境**
   ```bash
   JDK >= 1.8 (推荐1.8版本)
   Mysql >= 5.7.0 (推荐5.7版本)
   Maven >= 3.0
   ```
[点击配置阿里云在线数据库](#databas)

### 3. **安装依赖和Redis**
然后选择 Maven -> Existing Maven Projects，点击 Next> 按钮，选择工作目录，然后点击 Finish 按钮，即可成功导入。

安装Homebrew后，在终端执行以下命令**安装Redis**：
```bash
brew install redis
# 安装完成后，可以通过以下命令启动Redis服务：
brew services start redis
# 你也可以手动启动Redis服务：
redis-server /usr/local/etc/redis.conf
# 通过redis-cli命令测试Redis是否正常工作：
redis-cli ping
# 如果返回PONG，则表示Redis已成功安装并运行。
# 注意：以上步骤可能随着时间和操作系统的不同而有所变化，请确保你的Homebrew是最新版本，以提供最新的Redis安装包。
```
_**注意**：请在每次运行项目前检查redis运行状态，多数无法运行时由于redis没有运行_

<a id="databas"></a>
### 4. 连接数据库

为了确保数据打通、功能连贯、风格一致，我们使用了阿里云云端数据库连接。如果你使用的是 IntelliJ IDEA，可以按照以下步骤连接数据库：

1. **选择数据源**：
   - 数据源类型：MySQL

2. **配置数据库连接**：
   - 主机地址：`rm-2zesz8131iv74g50gqo.mysql.rds.aliyuncs.com`
   - 用户名：`bqy`
   - 密码：`Abc123456`
   - 架构：`main`

3. **测试连接**：
   - 点击“测试连接”按钮，确保连接成功。

### 5. **修改配置**
- 如果使用Mac需要修改application.yml文件路径profile，修改数据库连接，编辑resources目录下的application-druid.yml
- 如果使用Linux 提示表不存在，设置大小写敏感配置在/etc/my.cnf添加lower_case_table_names=1，重启MYSQL服务 
- 如果提示当前权限不足，无法写入文件请检查application.yml中的profile路径或logback.xml中的log.path路径是否有可读可写操作权限

### 6. **运行代码**
1. 打开项目运行softcostEstimator-admin/src/main/java/com/softcostEstimator/RuoYiApplication.java，出现如下图表示启动成功
```bash
   (♥◠‿◠)ﾉﾞ  SoftcostEstimator启动成功    ლ(´ڡ`ლ)ﾞ                  
 _   _ _____ _   _    ____ ____  _____    _  _____    ____ ____   _      ____   ___ ____  _  _   
| \ | | ____| | | |  / ___|  _ \| ____|  / \|_   _|  / ___|  _ \ / \    |___ \ / _ \___ \| || |  
|  \| |  _| | | | | | |  _| |_) |  _|   / _ \ | |   | |  _| |_) / _ \     __) | | | |__) | || |_ 
| |\  | |___| |_| | | |_| |  _ <| |___ / ___ \| |   | |_| |  __/ ___ \   / __/| |_| / __/|__   _|
|_| \_|_____|\___/   \____|_| \_\_____/_/   \_\_|    \____|_| /_/   \_\ |_____|\___/_____|  |_|  
                                                                                                 
```
2. 前端vue在/softcostEstimator-ui/package.json中的"dev": "vue-cli-service serve",
```
# 启动服务
npm run dev
```


### 7. **预览说明**
1. 打开浏览器，输入：(http://localhost:80 (opens new window)) （默认账户/密码 admin/admin123)
2. 若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明环境搭建成功

---

### 8. **其他说明**
1. 页面用到了element的组件，所以运行前需要安装element-plus
```bash
npm install element-plus
# 安装时请用国内镜像或者更换代理，不然会出现连接失败
```
---

## 🤝 贡献指南

### 组长贡献指南
1. 按照组长姓名创建分支，例如：zzq
2. 组长仅能上传自己的分支
3. 讨论整合后提交到main分支

#### 拉取分支指南（以bqy分支为例）
- 若要从远程仓库拉取 bqy 分支并切换到该分支，先确保本地仓库是最新的：
```bash
git fetch origin
```
- 拉取远程的 bqy 分支并切换到该分支:
```bash
git checkout -b bqy origin/bqy
```
- 若 bqy 分支已存在，则切换到该分支:
```bash
git checkout bqy
```

**注意**：请各组管理自己小组的分支，不要提交代码到其他小组的分支

### 组内贡献指南
1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request
（也可以组内都提交到组长分支）
---

## 🐞 问题反馈

如遇到问题，欢迎通过以下方式反馈：
- 提交 [Issue](https://gitcode.com/zzq20226705/AIQuestion/issues)
- 邮件联系：zzq18368326181@icloud.com

---

[//]: # (## 📜 许可证)

[//]: # (本项目采用 MIT 许可证 - 详情请参阅 [LICENSE]&#40;LICENSE&#41; 文件。)
[//]: # (---)



## 💬 致谢
- 感谢所有贡献者的付出。
- 特别感谢 [东北大学软件学院 软件产品构建课题组](http://sc.neu.edu.cn) 的支持。