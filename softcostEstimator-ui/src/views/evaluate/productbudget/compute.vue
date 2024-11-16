<template>
  <div class="app-container">
    <el-steps :active="activeStep" finish-status="success" class="mb20">
      <el-step title="确定PDR" />
      <el-step title="计算工作量AE" />
      <el-step title="计算总造价SDC" />
    </el-steps>

    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <!-- 步骤1：确定PDR -->
      <div v-if="activeStep === 0">
        <el-form-item label="PDR" prop="pdr">
          <el-input v-model="form.pdr" placeholder="请输入PDR" clearable />
        </el-form-item>
      </div>

      <!-- 步骤2：计算工作量AE -->
      <div v-if="activeStep === 1">
        <!-- SF -->
        <el-form-item label="SF" prop="sfOption">
          <el-select v-model="form.sfOption" placeholder="请选择SF" @change="updateSFValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <!-- 这里绑定的是 SF 数值 -->
          <el-input v-model.number="form.sf" placeholder="手动输入SF" clearable />
        </el-form-item>

        <!-- BD -->
        <el-form-item label="BD" prop="bdOption">
          <el-select v-model="form.bdOption" placeholder="请选择BD" @change="updateBDValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.bd" placeholder="手动输入BD" clearable />
        </el-form-item>

        <!-- AT -->
        <el-form-item label="AT" prop="atOption">
          <el-select v-model="form.atOption" placeholder="请选择AT" @change="updateATValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.at" placeholder="手动输入AT" clearable />
        </el-form-item>

        <!-- QR -->
        <el-form-item label="QR" prop="qrOption">
          <el-select v-model="form.qrOption" placeholder="请选择QR" @change="updateQRValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.qr" placeholder="手动输入QR" clearable />
        </el-form-item>

        <!-- SWF 中间变量展示 -->
        <el-form-item label="SWF" :prop="null">
          <el-input :value="form.swf" placeholder="SWF" disabled />
        </el-form-item>

        <!-- SL -->
        <el-form-item label="SL" prop="slOption">
          <el-select v-model="form.slOption" placeholder="请选择SL" @change="updateSLValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.sl" placeholder="手动输入SL" clearable />
        </el-form-item>

        <!-- DT -->
        <el-form-item label="DT" prop="dtOption">
          <el-select v-model="form.dtOption" placeholder="请选择DT" @change="updateDTValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.dt" placeholder="手动输入DT" clearable />
        </el-form-item>

        <!-- RDF 中间变量展示 -->
        <el-form-item label="RDF" :prop="null">
          <el-input :value="form.rdf" placeholder="RDF" disabled />
        </el-form-item>

        <!-- AE 计算 -->
        <el-form-item label="AE" :prop="null">
          <el-input :value="form.ae" placeholder="AE" disabled />
        </el-form-item>
      </div>

      <!-- 步骤3：计算总造价SDC -->
      <div v-if="activeStep === 2">
        <!-- F -->
        <el-form-item label="F" prop="fOption">
          <el-select v-model="form.fOption" placeholder="请选择F" @change="updateFValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.f" placeholder="手动输入F" clearable />
        </el-form-item>

        <!-- DNC -->
        <el-form-item label="DNC" prop="dncOption">
          <el-select v-model="form.dncOption" placeholder="请选择DNC" @change="updateDNCValue">
            <el-option label="a" value="a" />
            <el-option label="b" value="b" />
            <el-option label="c" value="c" />
            <el-option label="d" value="d" />
          </el-select>
          <el-input v-model.number="form.dnc" placeholder="手动输入DNC" clearable />
        </el-form-item>

        <!-- 计算出的SDC -->
        <el-form-item label="SDC" :prop="null">
          <el-input :value="form.sdc" placeholder="SDC" disabled />
        </el-form-item>

        <el-form-item label="RSK" prop="rsk">
          <el-input v-model="form.rsk" placeholder="请输入RSK" clearable />
        </el-form-item>

        <!-- ESDC计算 -->
        <el-form-item label="ESDC" :prop="null">
          <el-input :value="form.esdc" placeholder="ESDC" disabled />
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
export default {
  name: "CostEvaluation",
  data() {
    return {
      activeStep: 0, // 当前步骤
      form: {
        pdr: null,
        sfOption: null, // SF 情况
        sf: null, // SF 数值
        bdOption: null, // BD 情况
        bd: null, // BD 数值
        atOption: null, // AT 情况
        at: null, // AT 数值
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
        dncOption: null, // DNC 情况
        dnc: null, // DNC 数值
        sdc: null,
        rsk: null,
        esdc: null,
      },
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
  },
  methods: {
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
    // 计算 SDC
    //SDC公式暂时这么写
    calculateSDC() {
      const sdc = (this.form.at || 0) * (this.form.f || 0) + (this.form.dnc || 0);
      this.form.sdc = this.formatDecimal(sdc);  // 格式化为四位小数
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

    updateSFValue(newValue) {
      // 根据 SF 选择情况更新数值
      if (newValue === 'a') {
        this.form.sf = 1;
      } else if (newValue === 'b') {
        this.form.sf = 10;
      } else if (newValue === 'c') {
        this.form.sf = 20;
      } else if (newValue === 'd') {
        this.form.sf = 30;
      }
    },
    updateBDValue(newValue) {
      // 根据 BD 选择情况更新数值
      if (newValue === 'a') {
        this.form.bd = 5;
      } else if (newValue === 'b') {
        this.form.bd = 10;
      } else if (newValue === 'c') {
        this.form.bd = 15;
      } else if (newValue === 'd') {
        this.form.bd = 20;
      }
    },
    updateATValue(newValue) {
      // 根据 AT 选择情况更新数值
      if (newValue === 'a') {
        this.form.at = 3;
      } else if (newValue === 'b') {
        this.form.at = 7;
      } else if (newValue === 'c') {
        this.form.at = 8;
      } else if (newValue === 'd') {
        this.form.at = 12;
      }
    },
    updateQRValue(newValue) {
      // 根据 QR 选择情况更新数值
      if (newValue === 'a') {
        this.form.qr = 10;
      } else if (newValue === 'b') {
        this.form.qr = 15;
      } else if (newValue === 'c') {
        this.form.qr = 25;
      } else if (newValue === 'd') {
        this.form.qr = 30;
      }
    },
    updateSLValue(newValue) {
      // 根据 SL 选择情况更新数值
      if (newValue === 'a') {
        this.form.sl = 4;
      } else if (newValue === 'b') {
        this.form.sl = 8;
      } else if (newValue === 'c') {
        this.form.sl = 12;
      } else if (newValue === 'd') {
        this.form.sl = 15;
      }
    },
    updateDTValue(newValue) {
      // 根据 DT 选择情况更新数值
      if (newValue === 'a') {
        this.form.dt = 9;
      } else if (newValue === 'b') {
        this.form.dt = 10;
      } else if (newValue === 'c') {
        this.form.dt = 18;
      } else if (newValue === 'd') {
        this.form.dt = 20;
      }
    },
    updateFValue(newValue) {
      // 根据 SL 选择情况更新数值
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
    updateDNCValue(newValue) {
      // 根据 SL 选择情况更新数值
      if (newValue === 'a') {
        this.form.dnc = 4;
      } else if (newValue === 'b') {
        this.form.dnc = 8;
      } else if (newValue === 'c') {
        this.form.dnc = 12;
      } else if (newValue === 'd') {
        this.form.dnc = 15;
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
    // 提交表单
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 提交逻辑
          console.log(this.form);
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

.el-steps {
  margin-bottom: 20px;
}
</style>
