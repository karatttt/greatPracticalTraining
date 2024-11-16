<template>
  <div class="app-container">
    <!-- 步骤条 -->
    <el-steps :active="activeStep" finish-status="success" class="mb20">
      <el-step title="确定PDR" />
      <el-step title="计算工作量AT" />
      <el-step title="计算综合造价ESDC" />
    </el-steps>

    <!-- 分步表单 -->
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <!-- 包裹所有步骤内容的根元素 -->
      <div v-if="activeStep === 0">
        <!-- 步骤1：确定PDR -->
        <el-form-item label="PDR" prop="pdr">
          <el-select v-model="form.pdr" placeholder="请选择PDR">
            <el-option label="PDR Option 1" value="option1" />
            <el-option label="PDR Option 2" value="option2" />
          </el-select>
        </el-form-item>
        <el-form-item label="SF" prop="sf">
          <el-input v-model="form.sf" placeholder="请输入SF" clearable />
        </el-form-item>
        <el-form-item label="BD" prop="bd">
          <el-input v-model="form.bd" placeholder="请输入BD" clearable />
        </el-form-item>
      </div>

      <div v-if="activeStep === 1">
        <!-- 步骤2：计算工作量AT -->
        <el-form-item label="AT" prop="at">
          <el-input v-model="form.at" placeholder="请输入工作量AT" clearable />
        </el-form-item>
        <el-form-item label="QR" prop="qr">
          <el-input v-model="form.qr" placeholder="请输入QR" clearable />
        </el-form-item>
        <el-form-item label="SL" prop="sl">
          <el-input v-model="form.sl" placeholder="请输入SL" clearable />
        </el-form-item>
      </div>

      <div v-if="activeStep === 2">
        <!-- 步骤3：计算综合造价ESDC -->
        <el-form-item label="综合造价 ESDC" prop="esdc">
          <el-input v-model="form.esdc" placeholder="请输入ESDC" clearable />
        </el-form-item>
        <el-form-item label="F" prop="f">
          <el-input v-model="form.f" placeholder="请输入F" clearable />
        </el-form-item>
        <el-form-item label="DNC" prop="dnc">
          <el-input v-model="form.dnc" placeholder="请输入DNC" clearable />
        </el-form-item>
        <el-form-item label="SDC" prop="sdc">
          <el-input v-model="form.sdc" placeholder="请输入SDC" clearable />
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
        pdr: null, // PDR
        sf: null, // SF
        bd: null, // BD
        at: null, // AT
        qr: null, // QR
        sl: null, // SL
        esdc: null, // ESDC
        f: null, // F
        dnc: null, // DNC
        sdc: null, // SDC
      },
      rules: {
        pdr: [{ required: true, message: "请选择PDR", trigger: "change" }],
        sf: [{ required: true, message: "请输入SF", trigger: "blur" }],
        bd: [{ required: true, message: "请输入BD", trigger: "blur" }],
        at: [{ required: true, message: "请输入工作量AT", trigger: "blur" }],
        qr: [{ required: true, message: "请输入QR", trigger: "blur" }],
        sl: [{ required: true, message: "请输入SL", trigger: "blur" }],
        esdc: [{ required: true, message: "请输入ESDC", trigger: "blur" }],
        f: [{ required: true, message: "请输入F", trigger: "blur" }],
        dnc: [{ required: true, message: "请输入DNC", trigger: "blur" }],
        sdc: [{ required: true, message: "请输入SDC", trigger: "blur" }],
      },
    };
  },
  methods: {
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
