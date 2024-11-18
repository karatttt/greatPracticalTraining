<template>
  <div class="app-container">
    <el-steps :active="activeStep" finish-status="success" class="mb20">
      <el-step title="确定PDR" />
      <el-step title="计算工作量AE" />
      <el-step title="计算总造价" />
    </el-steps>

    <el-form ref="form" :model="form" :rules="rules" label-width="200px">
      <!-- 步骤1：确定PDR -->

      <div v-if="activeStep === 0">
        <!-- PDR 选择表格 -->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-table :data="pdrOptions" border style="width: 100%">
              <el-table-column prop="label" label="百分位" width="100" />
              <el-table-column prop="value" label="PDR (人时/功能点)" width="150" />
              <el-table-column label="适用场景">
                <template #default="scope">
                  <el-tooltip effect="dark" :content="scope.row.description" placement="top">
                    <span>{{ scope.row.description }}</span>
                  </el-tooltip>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>

        <!-- PDR 选择 -->
        <el-row :gutter="20" class="mt20">
          <el-col :span="8">
            <el-form-item label="PDR百分位选择" prop="pdrPercentile">
              <el-select v-model="form.pdrPercentile" placeholder="请选择PDR百分位" @change="updatePDRValue">
                <el-option label="P10 (1.79) - 乐观估计，适合高效团队和低风险项目" :value="1.79" />
                <el-option label="P25 (3.58) - 偏乐观估计，适合较成熟的开发环境" :value="3.58" />
                <el-option label="P50 (7.17) - 最可能值，适用于常规项目" :value="7.17" />
                <el-option label="P75 (12.99) - 偏悲观估计，适合高复杂度或较高风险项目" :value="12.99" />
                <el-option label="P90 (17.81) - 悲观估计，适合高风险和严苛质量要求" :value="17.81" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="PDR (人时/功能点)" prop="pdr">
              <el-input v-model="form.pdr" placeholder="请输入或通过选择自动填充" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 提示信息 -->
        <el-row class="mt20">
          <el-col :span="24">
            <el-alert
              title="提示：如何选择合适的PDR？"
              type="info"
              description="P10 是乐观估计，适用于高效团队或低风险项目；P50 是中性估计，适合常规项目；P90 是悲观估计，适用于高复杂度或高风险项目。请根据实际情况选择合适的值。"
              show-icon
              effect="dark"
            />
          </el-col>
        </el-row>
      </div>



      <!-- 步骤2：计算工作量AE -->
      <div v-if="activeStep === 1">
        <!-- SF -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="SF(规模调整因子)" prop="sf">
              <el-input v-model="form.sf" placeholder="请输入SF" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- BD -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="BD(业务领域调整因子)" prop="bdOption">
              <el-select v-model="form.bdOption" placeholder="请选择BD" @change="updateBDValue">
                <el-option label="政府（含公共管理和社会组织）" value="a" />
                <el-option label="信息技术、电信" value="b" />
                <el-option label="金融" value="c" />
                <el-option label="其他" value="d" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="" prop="bd">
              <el-input v-model="form.bd" placeholder="手动输入BD" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- AT -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="AT(应用类型调整因子)" prop="atOption">
              <el-select v-model="form.atOption" placeholder="请选择AT" @change="updateATValue">
                <el-option label="业务处理（办公自动化系统:人事、会计、工资、销售等经营管理及业务处理用软件等）" value="a" />
                <el-option label="应用集成（企业服务总线、应用集成等）" value="b" />
                <el-option label="科技（科学计算、模拟、统计等）" value="c" />
                <el-option label="多媒体（图形、影像、声音等多媒体应用领域:地理信息系统;教育和娱乐应用等）" value="d" />
                <el-option label="智能信息（自然语言处理、人工智能、专家系统等）" value="e" />
                <el-option label="系统（操作系统、数据库系统、集成开发环境、自动化开发/设计工具等）" value="f" />
                <el-option label="通信控制（通信协议、仿真、交换机软件、全球定位系统等）" value="g" />
                <el-option label="流程控制（生产管理、仪器控制、机器人控制、实时控制、嵌入式软件等）" value="h" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="" prop="at">
              <el-input v-model="form.at" placeholder="手动输入AT" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- QR -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="QR(质量特性调整因子)" >
              <el-select v-model="form.distributedProcessing" placeholder="请选择分布式处理调整因子" @change="calculateQR">
                <el-option label="没有明示对分布式处理的需求事项" :value="-1" />
                <el-option label="通过网络进行客户端/服务器及网络基础应用分布处理和传输" :value="0" />
                <el-option label="在多个服务器及处理器上同时相互执行应用中的处理功能" :value="1" />
              </el-select>

              <el-select v-model="form.performance" placeholder="请选择性能调整因子" @change="calculateQR">
                <el-option label="没有明示对性能的特别需求事项或活动，因此提供基本性能" :value="-1" />
                <el-option label="应答时间或处理率对高峰时间或所有业务时间来说都很重要，存在对连动系统结束处理时间的限制" :value="0" />
                <el-option label="为满足性能需求事项，要求设计阶段开始进行性能分析，或在设计、开发阶段使用分析工具" :value="1" />
              </el-select>

            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item>
              <el-select v-model="form.reliability" placeholder="请选择可靠性调整因子" @change="calculateQR">
                <el-option label="没有明示对分布式处理的需求事项" :value="-1" />
                <el-option label="通过网络进行客户端/服务器及网络基础应用分布处理和传输" :value="0" />
                <el-option label="在多个服务器及处理器上同时相互执行应用中的处理功能" :value="1" />
              </el-select>

              <el-select v-model="form.multiSite" placeholder="请选择多重站点调整因子" @change="calculateQR">
                <el-option label="没有明示对分布式处理的需求事项" :value="-1" />
                <el-option label="通过网络进行客户端/服务器及网络基础应用分布处理和传输" :value="0" />
                <el-option label="在多个服务器及处理器上同时相互执行应用中的处理功能" :value="1" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="" prop="qr">
              <el-input v-model="form.qr" placeholder="手动输入QR" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- SWF 中间变量展示 -->
        <el-form-item label="SWF(软件因素调整因子)" :prop="null">
          <el-input :value="form.swf" placeholder="SWF" disabled style="width: 220px;"/>
        </el-form-item>

        <!-- SL -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="SL(开发语言调整因子)" prop="slOption">
              <el-select v-model="form.slOption" placeholder="请选择SL" @change="updateSLValue">
                <el-option label="C及其他同级别语言/平台" value="a" />
                <el-option label="JAVA、C++、C#及其他同级别语言/平台" value="b" />
                <el-option label="PowerBuilder、ASP 及其他同级别语言/平台" value="c" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="" prop="sl">
              <el-input v-model="form.sl" placeholder="手动输入SL" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- DT -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="DT(开发团队调整因子)" prop="dtOption">
              <el-select v-model="form.dtOption" placeholder="请选择DT" @change="updateDTValue">
                <el-option label="为本行业开发过类似的项目" value="a" />
                <el-option label="为其他行业开发过类似的项目，或为本行业开发过不同但相关的项目" value="b" />
                <el-option label="没有同类项目的背景" value="c" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="" prop="dt">
              <el-input v-model="form.dt" placeholder="手动输入DT" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- RDF 中间变量展示 -->
        <el-form-item label="RDF(开发因素调整因子)" :prop="null">
          <el-input :value="form.rdf" placeholder="RDF" disabled style="width: 220px;"/>
        </el-form-item>

        <!-- AE 计算 -->
        <el-form-item label="AE(估算工作量)" :prop="null">
          <el-input :value="form.ae" placeholder="AE" disabled style="width: 220px;"/>
        </el-form-item>
      </div>

      <!-- 步骤3：计算总造价SDC -->
      <div v-if="activeStep === 2">
        <!-- F -->
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="F(人力成本费率)" prop="fOption">
              <el-select v-model="form.fOption" placeholder="请选择F" @change="updateFValue">
                <el-option label="a" value="a" />
                <el-option label="b" value="b" />
                <el-option label="c" value="c" />
                <el-option label="d" value="d" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="" prop="f">
              <el-input v-model="form.f" placeholder="手动输入F" clearable />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- DNC -->
        <el-row :gutter="20">
          <el-col>
            <el-form-item label="DNC(直接非人力成本)" prop="dnc">
              <el-input v-model="form.dnc" disabled style="width: 220px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="办公费" prop="officeExpense">
              <el-input v-model="form.officeExpense" placeholder="请输入办公费" clearable @change="calculateDNC"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="差旅费" prop="travelExpense">
              <el-input v-model="form.travelExpense" placeholder="请输入差旅费" clearable @change="calculateDNC"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="培训费" prop="trainingExpense">
              <el-input v-model="form.trainingExpense" placeholder="请输入培训费" clearable @change="calculateDNC"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="业务费" prop="businessExpense">
              <el-input v-model="form.businessExpense" placeholder="请输入业务费" clearable @change="calculateDNC"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="采购费" prop="purchaseExpense">
              <el-input v-model="form.purchaseExpense" placeholder="请输入采购费" clearable @change="calculateDNC"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="其他" prop="otherExpense">
              <el-input v-model="form.otherExpense" placeholder="请输入其他费用" clearable @change="calculateDNC"/>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 计算出的SDC -->
        <el-form-item label="SDC(软件开发成本)" :prop="null">
          <el-input :value="form.sdc" placeholder="SDC" disabled style="width: 220px;"/>
        </el-form-item>

        <!-- 风险类型列表 -->
        <el-row gutter="0" class="risk-row">
          <el-col :span="9" class="risk-col" v-for="(risk, index) in risks" :key="index">
            <el-form-item :label="risk.name" :prop="'risk' + index">
              <el-select
                v-model="risk.value"
                placeholder="请选择风险取值"
                class="risk-select"
                @change="updateOverallRisk"
                style="width: 220px;"
              >
                <el-option v-for="item in riskOptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 综合风险等级 -->
        <el-form-item label="整体风险等级" prop="overallRiskLevel">
          <el-input v-model="form.overallRiskLevel" placeholder="自动计算" disabled style="width: 220px;" />
        </el-form-item>

        <!-- RSK 自动计算 -->
        <el-form-item label="RSK(风险等级因子)" prop="rsk">
          <el-input v-model="form.rsk" placeholder="自动计算" disabled style="width: 220px;" />
        </el-form-item>

        <!-- ESDC计算 -->
        <el-form-item label="ESDC(调整后软件开发成本)" :prop="null">
          <el-input :value="form.esdc" placeholder="ESDC" disabled style="width: 220px;"/>
        </el-form-item>
      </div>
    </el-form>

    <!-- 步骤按钮 -->
    <div class="step-footer">
      <el-button :disabled="activeStep === 0" @click="prevStep">上一步</el-button>
      <el-button :disabled="activeStep === 2" type="primary" @click="nextStep">下一步</el-button>
      <el-button v-if="activeStep === 2" type="success" @click="submitForm">提交</el-button>
    </div>
  </div>
</template>


<script>
import {updateAnalysis} from "@/api/evaluate/analysis";
import {updateProductbudget} from "@/api/evaluate/productbudget";

export default {
  name: "CostEvaluation",
  data() {
    return {
      activeStep: 0, // 当前步骤
      form: {
        projectID:null,
        pdr: null,
        pdrPercentile: null, // PDR 百分位选择
        s:null,
        sfOption: null, // SF 情况,根据s，即功能点得到
        sf: null, // SF 数值
        bdOption: null, // BD 情况
        bd: null, // BD 数值
        atOption: null, // AT 情况
        at: null, // AT 数值
        //各种质量特性调整因子
        distributedProcessing:null,
        performance:null,
        reliability:null,
        multiSite:null,
        //
        qrOption: null, // QR 情况
        qr: null, // QR 数值
        swf: null,
        slOption: null, // SL 情况
        sl: null, // SL 数值
        dtOption: null, // DT 情况
        dt: null, // DT 数值
        rdf: null,
        ae: null,
        fOption: null, // F 情况
        f: null, // F 数值
        //各种费用
        officeExpense: 0,
        travelExpense: 0,
        trainingExpense: 0,
        businessExpense: 0,
        purchaseExpense: 0,
        otherExpense: 0,
        //
        dncOption: null, // DNC 情况
        dnc: null, // DNC 数值
        sdc: null,
        overallRiskLevel: '', // 综合风险等级（低、中、高）
        rsk: null,
        esdc: null,
      },
      pdrOptions: [
        { label: "P10", value: 1.79, description: "乐观估计，适用于高效团队或低风险项目" },
        { label: "P25", value: 3.58, description: "偏乐观估计，适用于成熟团队或低中风险项目" },
        { label: "P50", value: 7.17, description: "中性估计，适用于常规开发项目" },
        { label: "P75", value: 12.99, description: "偏悲观估计，适用于复杂或高风险项目" },
        { label: "P90", value: 17.81, description: "悲观估计，适用于高复杂度或严苛质量要求项目" },
      ],
      rules: {
        pdrOption: [{required: true, message: "请输入PDR", trigger: "blur"}],
        sfOption: [{required: true, message: "请输入SF", trigger: "blur"}],
        bdOption: [{required: true, message: "请输入BD", trigger: "blur"}],
        atOption: [{required: true, message: "请输入AT", trigger: "blur"}],
        qrOption: [{required: true, message: "请输入QR", trigger: "blur"}],
        slOption: [{required: true, message: "请输入SL", trigger: "blur"}],
        dtOption: [{required: true, message: "请输入DT", trigger: "blur"}],
        fOption: [{required: true, message: "请输入F", trigger: "blur"}],
        dncOption: [{required: true, message: "请输入DNC", trigger: "blur"}],
        rskOption: [{required: true, message: "请输入RSK", trigger: "blur"}],
      },
      // 风险类型
      risks: [
        { name: '进度风险', value: null },
        { name: '成本风险', value: null },
        { name: '质量风险', value: null },
        { name: '技术风险', value: null }
      ],
      // 风险取值选项
      riskOptions: [
        { value: 5, label: '5' },
        { value: 4, label: '4' },
        { value: 3, label: '3' },
        { value: 2, label: '2' },
        { value: 1, label: '1' }
      ]
    };
  },
  watch: {
    // 监听字段变化来动态计算 SWF, RDF 和 AE, ESDC
    "form.sf": "calculateSWF",
    "form.bd": "calculateSWF",
    "form.at": "calculateSWF",
    "form.qr": "calculateSWF",
    "form.sl": "calculateRDF",
    "form.dt": "calculateRDF",
    "form.f": "calculateSDC",
    "form.dnc": "calculateSDC",
    "form.rsk": "calculateESDC",
    'form.officeExpense': 'calculateDNC',
    'form.travelExpense': 'calculateDNC',
    'form.trainingExpense': 'calculateDNC',
    'form.businessExpense': 'calculateDNC',
    'form.purchaseExpense': 'calculateDNC',
    'form.otherExpense': 'calculateDNC'
  },
  methods: {
    // 计算 QR
    calculateQR() {
      const qr = (this.form.distributedProcessing + this.form.performance + this.form.reliability + this.form.multiSite)*0.025+1;
      console.log(this.form.distributedProcessing+this.form.performance+this.form.reliability+this.form.multiSite)
      this.form.qr = this.formatDecimal(qr);  // 格式化为四位小数
      this.calculateAE(); // 更新 AE
    },
    // 更新 PDR 值
    updatePDRValue(newValue) {
      this.form.pdr = newValue; // 自动填充 PDR 值
    },
    // 计算 SWF
    calculateSWF() {
      const swf = (this.form.sf || 0) * (this.form.bd || 0) * (this.form.at || 0) * (this.form.qr || 0);
      this.form.swf = this.formatDecimal(swf);  // 格式化为四位小数
      this.calculateAE(); // 更新 AE
    },
    // 计算 RDF
    calculateRDF() {
      const rdf = (this.form.sl || 0) * (this.form.dt || 0);
      this.form.rdf = this.formatDecimal(rdf);  // 格式化为四位小数
      this.calculateAE(); // 更新 AE
    },
    // 计算 AE
    calculateAE() {
      const ae = (this.form.swf || 0) * (this.form.rdf || 0) * (this.form.pdr || 0);
      this.form.ae = this.formatDecimal(ae);  // 格式化为四位小数
    },
    //计算DNC
    calculateDNC() {
        const dnc=this.form.officeExpense +
        this.form.travelExpense +
        this.form.trainingExpense +
        this.form.businessExpense +
        this.form.purchaseExpense +
        this.form.otherExpense;
        this.form.dnc=this.formatDecimal(dnc)
    },
    // 计算 SDC
    //SDC公式暂时这么写
    calculateSDC() {
      const sdc = (this.form.at || 0) * (this.form.f || 0) + (this.form.dnc || 0);
      this.form.sdc = this.formatDecimal(sdc);  // 格式化为四位小数
    },

    // 更新综合风险等级
    updateOverallRisk() {
      const riskValues = this.risks.map((risk) => risk.value);

      // 确保所有风险值有效
      if (riskValues.some((value) => value == null)) {
        this.form.overallRiskLevel = '';
        this.form.rsk = '';
        return;
      }

      // 计算综合风险等级（示例：取最大值）
      const maxRiskValue = Math.max(...riskValues);

      let overallRiskLevel = '';
      if (maxRiskValue === 5 || maxRiskValue === 4) {
        overallRiskLevel = '高';
      } else if (maxRiskValue === 3) {
        overallRiskLevel = '中';
      } else if (maxRiskValue === 2 || maxRiskValue === 1) {
        overallRiskLevel = '低';
      }
      this.form.overallRiskLevel = overallRiskLevel;

      // 根据综合风险等级计算 RSK
      this.updateRSK(overallRiskLevel);
    },
    // 根据综合风险等级计算 RSK
    updateRSK(overallRiskLevel) {
      // 定义综合风险等级对应的 RSK 因子范围
      const riskLevelFactors = {
        高: [1.3, 1.4],
        中: [1.1, 1.2],
        低: [1.0, 1.0]
      };

      const factorRange = riskLevelFactors[overallRiskLevel];
      if (factorRange) {
        // 简单逻辑：取范围的中间值作为 RSK
        this.form.rsk = ((factorRange[0] + factorRange[1]) / 2).toFixed(1);
      } else {
        this.form.rsk = '';
      }
    },

    // 计算 ESDC
    calculateESDC() {
      const esdc = (this.form.sdc || 0) * (this.form.rsk || 0);
      this.form.esdc = this.formatDecimal(esdc);  // 格式化为四位小数
    },
    // 格式化为四位小数
    formatDecimal(value) {
      return parseFloat(value).toFixed(4);  // 保留四位小数
    },

    updateBDValue(newValue) {
      // 根据 BD 选择情况更新数值
      if (newValue === 'a') {
        this.form.bd = 0.93;
      } else if (newValue === 'b') {
        this.form.bd = 1.02;
      } else if (newValue === 'c') {
        this.form.bd = 2.62;
      } else if (newValue === 'd') {
        this.form.bd = 1;
      }
    },
    updateATValue(newValue) {
      // 根据 AT 选择情况更新数值
      if (newValue === 'a') {
        this.form.at = 1;
      } else if (newValue === 'b') {
        this.form.at = 1.2;
      } else if (newValue === 'c') {
        this.form.at = 1.2;
      } else if (newValue === 'd') {
        this.form.at = 1.3;
      } else if (newValue === 'e') {
        this.form.at = 1.7;
      } else if (newValue === 'f') {
        this.form.at = 1.7;
      } else if (newValue === 'g') {
        this.form.at = 1.9;
      } else if (newValue === 'h') {
        this.form.at = 2;
      }
    },
    updateSLValue(newValue) {
      // 根据 SL 选择情况更新数值
      if (newValue === 'a') {
        this.form.sl = 1.5;
      } else if (newValue === 'b') {
        this.form.sl = 1;
      } else if (newValue === 'c') {
        this.form.sl = 0.6;
      }
    },
    updateDTValue(newValue) {
      // 根据 DT 选择情况更新数值
      if (newValue === 'a') {
        this.form.dt = 0.8;
      } else if (newValue === 'b') {
        this.form.dt = 1;
      } else if (newValue === 'c') {
        this.form.dt = 1.2;
      }
    },
    updateFValue(newValue) {
      // 根据 F 选择情况更新数值
      if (newValue === 'a') {
        this.form.f = 4;
      } else if (newValue === 'b') {
        this.form.f = 8;
      } else if (newValue === 'c') {
        this.form.f = 12;
      } else if (newValue === 'd') {
        this.form.f = 15;
      }
    },
    // 下一步
    nextStep() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.activeStep < 2) {
            this.activeStep += 1;
          }
        }
      });
    },
    // 上一步
    prevStep() {
      if (this.activeStep > 0) {
        this.activeStep -= 1;
      }
    },

    // 准备表单数据，只保留需要提交的字段
    prepareFormData() {
      const {projectID,
        pdr, sf, bd, at, qr,
        swf, sl, dt, rdf, ae, f, dnc,
        sdc, rsk, esdc
      } = this.form;
      // 构建需要提交的数据对象
      return {
        projectID,
        pdr,
        sf,
        bd,
        at,
        qr,
        sl,
        dt,
        f,
        dnc,
        rsk,
        sdc,
        esdc
      };
    },
    // 提交表单
    submitForm() {
      const urlParams = new URLSearchParams(window.location.search);
      const projectid= urlParams.get('projectID'); // 获取参数 projectID
      const submitData = this.prepareFormData();
      submitData.projectID=projectid;
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 提交逻辑
          if (projectid != null) {
            updateProductbudget(submitData).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              window.location.href = `http://localhost/evaluate/productbudget`;
              //这里可以继续增加跳转
            });
            }
          console.log(submitData);
          this.$message.success("提交成功");
        } else {
          this.$message.error("请完善表单信息");
        }
      });
    },
  },
};
</script>

<style scoped>
.app-container {
  padding: 20px;
}

.step-footer {
  margin-top: 20px;
  text-align: center;
}
.mt20 {
  margin-top: 20px;
}
.el-steps {
  margin-bottom: 20px;
}
</style>
