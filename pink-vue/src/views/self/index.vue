<template>
  <div>
    <el-card>
      <p class="title">工资栏</p>
      <p>
        <b>日期</b><span>{{ form.year }}年{{ form.month }}月</span>
      </p>
      <p>
        <b>月累计基本工资</b><span>￥{{ form.basicWage }}</span>
      </p>
      <p>
        <b>月累计总提成</b><span>￥{{ form.totalCommission }}</span>
      </p>
      <p v-if="form.bonus == 0 || form.bonus == undefined ? false : true">
        <b>月发放奖励</b><span>￥{{ form.bonus }}</span>
      </p>
      <p
        v-if="form.deduction == 0 || form.deduction == undefined ? false : true"
      >
        <b>月扣除工资</b><span>￥{{ form.deduction }}</span>
      </p>
      <p>
        <b>月累计总工资</b><span>￥{{ form.totalWages }}</span>
      </p>
    </el-card>
    <el-card>
      <p class="title">个人信息</p>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- 数据编辑表单组件 -->
        <el-form-item label="账号名称" prop="username">
          <label v-if="!isEdit">{{ form.username }}</label>
          <el-input
            v-if="isEdit"
            v-model="form.username"
            placeholder="请输入账号名称"
          />
        </el-form-item>
        <el-form-item label="员工工号" prop="staffNumber" disabled>
          <label v-if="!isEdit">{{ form.staffNumber }}</label>
          <el-input
            v-if="isEdit"
            v-model="form.staffNumber"
            placeholder="请输入员工工号"
          />
        </el-form-item>
        <el-form-item label="员工姓名" prop="staffName">
          <label v-if="!isEdit">{{ form.staffName }}</label>
          <el-input
            v-if="isEdit"
            v-model="form.staffName"
            placeholder="请输入员工姓名"
          />
        </el-form-item>
        <el-form-item label="员工性别" prop="gender">
          <label v-if="!isEdit">{{ form.gender == 0 ? "男" : "女" }}</label>
          <el-select
            v-if="isEdit"
            v-model="form.gender"
            placeholder="请选择员工性别"
          >
            <el-option
              v-for="item in genderOptions"
              :key="item.gender"
              :label="item.genderName"
              :value="item.gender"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="员工角色" prop="roleId">
          <label v-if="!isEdit">{{
            form.roleId == 1
              ? "店长"
              : form.roleId == 2
              ? "发型师"
              : form.roleId == 3
              ? "技师"
              : "收银员"
          }}</label>
          <el-select
            v-if="isEdit"
            v-model="form.roleId"
            placeholder="请选择员工角色"
            disabled
          >
            <el-option
              v-for="item in roleOptions"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="员工等级" prop="level" v-if="form.roleId == 2">
          <label v-if="!isEdit">{{
            form.roleId == 1
              ? "高级设计师"
              : form.roleId == 2
              ? "资深设计师"
              : "技术总监"
          }}</label>
          <el-select
            v-if="isEdit"
            v-model="form.level"
            placeholder="请选择员工等级"
            disabled
          >
            <el-option
              v-for="item in levelOptions"
              :key="item.levelId"
              :label="item.levelName"
              :value="item.levelId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="phoneNumber">
          <label v-if="!isEdit">{{ form.phoneNumber }}</label>
          <el-input
            v-if="isEdit"
            v-model="form.phoneNumber"
            placeholder="请输入联系方式"
          />
        </el-form-item>
        <el-form-item label="生日日期" prop="birthday">
          <label v-if="!isEdit">{{ form.birthday }}</label>
          <el-date-picker
            v-if="isEdit"
            v-model="form.birthday"
            type="date"
            placeholder="请选择生日日期"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div class="button">
        <el-button
          type="info"
          plain
          @click="edit"
          style="width: 18%; margin-right: 3%"
          >编 辑</el-button
        >
        <el-button
          type="success"
          plain
          @click="save"
          style="width: 18%; margin-right: 3%"
          >保 存</el-button
        >
        <el-button
          type="primary"
          plain
          @click="changePassword"
          style="width: 18%"
          >修改密码</el-button
        >
      </div>
    </el-card>
  </div>
</template>

<script>
/** 导入axios Api */
import {
  getMyself,
  updateSelf,
  authentication,
  updatePassword,
} from "@/api/self";
import { getAllRole, getAllLevel } from "@/api/staff";
import store from "@/store";
export default {
  data() {
    return {
      // 是否点击编辑按钮
      isEdit: false,
      // 数据显示表单
      form: {},
      // 可选性别
      genderOptions: [
        { gender: 0, genderName: "男" },
        { gender: 1, genderName: "女" },
      ],
      // 可选角色
      roleOptions: [],
      // 可选等级
      levelOptions: [],
      // 表单数据校验规则
      rules: {
        username: [
          { required: true, message: "账号名称不能为空", trigger: "blur" },
        ],
        staffNumber: [
          { required: true, message: "员工工号不能为空", trigger: "blur" },
        ],
        staffName: [
          { required: true, message: "员工姓名不能为空", trigger: "blur" },
        ],
        gender: [
          { required: true, message: "员工性别不能为空", trigger: "blur" },
        ],
        roleId: [
          { required: true, message: "员工角色不能为空", trigger: "blur" },
        ],
        level: [
          { required: true, message: "员工等级不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getInformation();
    getAllRole().then((response) => {
      this.roleOptions = response;
    });
    getAllLevel().then((response) => {
      this.levelOptions = response;
    });
  },
  methods: {
    getInformation() {
      getMyself(store.state.user.name).then((response) => {
        this.form = response;
      });
    },
    // 编辑
    edit() {
      this.isEdit = true;
    },
    // 保存
    save() {
      if (this.isEdit == true) {
        this.isEdit = false;
        updateSelf(this.form)
          .then((response) => {
            this.getInformation();
            this.$message.success(response);
          })
          .catch((error) => {
            this.getInformation();
            if (error.response) {
              this.$message.warning(error.response.data.message);
            }
          });
      } else {
        this.$message.warning("请先编辑再保存");
      }
    },
    // 修改密码
    changePassword() {
      this.$prompt("请输入旧密码", "身份验证", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /[a-zA-Z0-9-*/+.~!@#$%^&*()]{6,20}$/,
        inputErrorMessage: "密码格式不正确",
      })
        .then(({ value }) => {
          // 验证身份
          var data = {
            username: store.state.user.name,
            password: value,
          };
          authentication(data)
            .then((response) => {
              if (response) {
                this.$prompt("请输入新密码", "修改密码", {
                  confirmButtonText: "确定",
                  cancelButtonText: "取消",
                  inputPattern: /[a-zA-Z0-9-*/+.~!@#$%^&*()]{6,20}$/,
                  inputErrorMessage: "密码格式不正确",
                })
                  .then(({ value }) => {
                    // 修改密码
                    var data = {
                      username: store.state.user.name,
                      password: value,
                    };
                    updatePassword(data)
                      .then((response) => {
                        this.$message.success(response);
                      })
                      .catch((error) => {
                        console.log(error);
                        this.$message.error("修改密码失败！");
                      });
                  })
                  .catch(() => {
                    this.$message({
                      type: "info",
                      message: "取消修改密码",
                    });
                  });
              } else {
                this.$message.error("身份验证失败！");
              }
            })
            .catch((error) => {
              this.$message.error("身份验证出错了！");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
  },
};
</script>

<style scoped>
.el-card {
  width: 60%;
  margin: auto;
  margin-top: 50px;
  margin-bottom: 50px;
}

.el-form {
  padding-left: 20%;
}

.el-form-item label {
  display: inline-block;
  width: 60%;
  text-indent: 1em;
  border-bottom: 1px solid blueviolet;
}

.button {
  text-align: center;
}

.title {
  color: rgb(88, 119, 185);
  font-size: 16px;
  font-weight: 800;
  text-align: center;
  margin-bottom: 40px;
  padding-left: 0 !important;
}

.el-card p {
  padding-left: 35%;
}

.el-card b {
  display: inline-block;
  width: 21%;
  text-align: justify;
  text-align-last: justify;
  font-size: 14px;
  margin-right: 3em;
}

.el-card span {
  color: #606266;
  font-size: 15px;
}

.el-input,
.el-select {
  width: 60%;
}
</style>