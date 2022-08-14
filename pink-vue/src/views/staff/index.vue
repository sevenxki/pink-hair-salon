<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="24" :xs="24">
        <el-card shadow="hover">
          <!-- 数据筛选表单 -->
          <el-form
            ref="queryForm"
            :model="queryParams"
            :inline="true"
            align="center"
            label-width="100px"
            class="center"
          >
            <!-- 数据筛选表单组件 -->
            <el-form-item label="员工姓名">
              <el-input
                v-model="queryParams.staffName"
                placeholder="请输入员工姓名"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="员工工号">
              <el-input
                v-model="queryParams.staffNumber"
                placeholder="请输入员工工号"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>

            <!-- 数据筛选操作按钮 -->
            <el-form-item>
              <el-button
                type="primary"
                icon="el-icon-search"
                size="mini"
                @click="handleQuery"
                >搜索
              </el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
                >重置
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>

        <el-row :gutter="10">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="small"
            @click="handleAdd"
            style="margin: 10px"
            >新增员工
          </el-button>
          <el-button
            type="success"
            icon="el-icon-view"
            size="small"
            @click="handleAll"
            style="margin: 10px"
            >显示过去所有数据
          </el-button>
        </el-row>
        <!-- 添加修改员工对话框 -->
        <el-dialog
          :title="title"
          :visible.sync="open"
          width="500px"
          append-to-body
          center
        >
          <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <!-- 数据编辑表单组件 -->
            <el-form-item label="员工工号" prop="staffNumber">
              <el-input
                v-model="form.staffNumber"
                placeholder="请输入员工工号"
              />
            </el-form-item>
            <el-form-item label="员工姓名" prop="staffName">
              <el-input v-model="form.staffName" placeholder="请输入员工姓名" />
            </el-form-item>
            <el-form-item label="员工性别" prop="gender">
              <el-select v-model="form.gender" placeholder="请选择员工性别">
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
              <el-select v-model="form.roleId" placeholder="请选择员工角色">
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
              <el-select v-model="form.level" placeholder="请选择员工等级">
                <el-option
                  v-for="item in levelOptions"
                  :key="item.level"
                  :label="item.levelName"
                  :value="item.level"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="联系方式" prop="phoneNumber">
              <el-input
                v-model="form.phoneNumber"
                placeholder="请输入联系方式"
              />
            </el-form-item>
            <el-form-item label="生日日期" prop="birthday">
              <el-date-picker
                v-model="form.birthday"
                type="date"
                placeholder="请选择生日日期"
                value-format="yyyy-MM-dd"
              >
              </el-date-picker>
            </el-form-item>
          </el-form>
          <!-- 数据提交操作按钮 -->
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>

        <el-card class="box-card" shadow="hover" v-loading="loading">
          <div class="container">
            <div class="row">
              <!-- 展示列表 -->
              <div
                v-show="staffList.length"
                class="card"
                v-for="staff in staffList"
                :key="staff.staffId"
              >
                <div class="detail">
                  <div class="detail-name">
                    <label>员工姓名</label>
                    <label>员工工号</label>
                    <label>年份</label>
                    <label>月份</label>
                    <label>月累计基本工资</label>
                    <label>月累计总提成</label>
                    <label
                      v-if="
                        staff.hsSalary.bonus == 0 || staff.hsSalary.bonus == undefined
                          ? false
                          : true
                      "
                      >月发放奖励</label
                    >
                    <label
                      v-if="
                        staff.hsSalary.deduction == 0 || staff.hsSalary.deduction == undefined
                          ? false
                          : true
                      "
                      >月扣除工资</label
                    >
                    <label>月累计总工资</label>
                    <label>发放状态</label>
                  </div>
                  <div
                    class="detail-value"
                    :class="
                      staff.hsSalary.status == 1 ? 'circle-green' : 'circle-red'
                    "
                  >
                    <label>{{ staff.hsSalary.hsStaff.staffName }}</label>
                    <label>{{ staff.hsSalary.hsStaff.staffNumber }}</label>
                    <label>{{ staff.year }}年</label>
                    <label>{{ staff.month }}月</label>
                    <label>￥ {{ staff.hsSalary.hsWageRules.basicWage }}</label>
                    <label>￥ {{ staff.hsSalary.totalCommission }}</label>
                    <label
                      v-if="
                        staff.hsSalary.bonus == 0 || staff.hsSalary.bonus == undefined
                          ? false
                          : true
                      "
                      >￥ {{ staff.hsSalary.bonus }}</label
                    >
                    <label
                      v-if="
                        staff.hsSalary.deduction == 0 || staff.hsSalary.deduction == undefined
                          ? false
                          : true
                      "
                      >￥ {{ staff.hsSalary.deduction }}</label
                    >
                    <label>￥ {{ staff.hsSalary.totalWages }}</label>
                    <label>{{
                      staff.hsSalary.status == 1 ? "已发放" : "未发放"
                    }}</label>
                  </div>
                </div>
                <div class="cover">
                  <el-button size="small" plain @click="handleUpdate(staff)"
                    >修改</el-button
                  >
                  <el-button size="small" plain @click="handleDelete(staff)"
                    >删除记录</el-button
                  >
                  <el-button size="small" plain @click="handleDeleteAll(staff)"
                    >删除员工</el-button
                  >
                  <el-button size="small" plain @click="reward(staff)"
                    >发放奖励</el-button
                  >
                  <el-button size="small" plain @click="dedute(staff)"
                    >扣除工资</el-button
                  >
                  <el-button size="small" plain @click="payWage(staff)"
                    >工资结算</el-button
                  >
                </div>
              </div>
            </div>
          </div>
        </el-card>

        <!-- 奖惩工资对话框 -->
        <el-dialog
          :title="titleWage"
          :visible.sync="openWage"
          width="500px"
          append-to-body
          center
        >
          <el-form
            ref="formWage"
            :model="formWage"
            :rules="rulesWage"
            label-width="120px"
          >
            <el-form-item label="金 额" prop="bonus">
              <el-input v-model="formWage.bonus" placeholder="请输入金额" />
            </el-form-item>
            <el-form-item label="理 由" prop="reason">
              <el-input
                v-model="formWage.reason"
                type="textarea"
                :rows="2"
                placeholder="请输入奖惩理由"
                style="width: 280px"
              />
            </el-form-item>
            <el-form-item label="经手人" prop="staffName">
              <el-input v-model="staff" disabled />
            </el-form-item>
          </el-form>
          <!-- 数据提交操作按钮 -->
          <div slot="footer">
            <el-button type="primary" @click="submitFormWage">确 定</el-button>
            <el-button @click="cancelWage">取 消</el-button>
          </div>
        </el-dialog>

        <!-- 数据分页加载 -->
        <el-pagination
          :current-page="pageNum"
          :page-sizes="[8, 16, 32]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          align="center"
          style="margin: 10px"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          @pagination="getList"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import "@/assets/css/bootstrap.css";
import {
  staffListByPage,
  staffListOnMonth,
  payHsStaff,
  getAllRole,
  getAllLevel,
  getHsStaff,
  addHsStaff,
  updateHsStaff,
  deleteHsStaff,
  deleteHsStaffAll,
  getReward,
  getDedute,
} from "@/api/staff";
import store from "@/store";
export default {
  data() {
    return {
      // 经手人
      staff: store.state.user.staffName || "lucy",
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // 是否显示弹出层
      open: false,
      // 是否显示奖惩工资弹出层
      openWage: false,
      // 弹出层标题
      title: "",
      // 奖惩工资弹出层标题
      titleWage: "",
      // 展示数据
      staffList: [],
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 8,
        staffName: undefined,
        staffNumber: undefined,
      },
      // 本月或全部的标识符
      flag: 1,
      // 数据编辑表单
      form: {},
      // 奖惩工资数据编辑表单
      formWage: {},
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
        phoneNumber: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
        ],
      },
      rulesWage: {
        bonus: [
          {
            pattern: /^\d+(\.\d{1,2})?$/,
            required: true,
            message: "金额格式不正确",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getList();
    getAllRole().then((response) => {
      this.roleOptions = response;
    });
    getAllLevel().then((response) => {
      this.levelOptions = response;
    });
  },
  methods: {
    // 获取数据列表
    getList() {
      this.loading = true;
      staffListOnMonth(this.queryParams).then((response) => {
        this.staffList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;
        this.flag = 1;
      });
    },
    // 处理分页
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      if (this.flag == 1) {
        this.getList();
      } else if (this.flag == 2) {
        this.handleAll();
      }
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      if (this.flag == 1) {
        this.getList();
      } else if (this.flag == 2) {
        this.handleAll();
      }
    },
    // 显示所有历史数据
    handleAll() {
      this.loading = true;
      staffListByPage(this.queryParams).then((response) => {
        this.staffList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;
        this.flag = 2;
      });
    },
    // 数据编辑对话框取消按钮
    cancel() {
      this.open = false;
      this.resetForm();
    },

    cancelWage() {
      this.openWage = false;
      this.resetFormWage();
    },

    // 数据编辑表单清空
    resetForm() {
      this.form = {
        staffNumber: undefined,
        staffName: undefined,
        gender: undefined,
        roleId: undefined,
        level: undefined,
        phoneNumber: undefined,
        birthday: undefined,
      };
    },

    resetFormWage() {
      this.formWage = {
        bonus: undefined,
        reason: undefined,
        salaryId: undefined,
      };
    },

    // 数据筛选参数重置按钮
    resetQueryParams() {
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 8;
    },

    // 数据筛选搜索按钮
    handleQuery() {
      this.resetQueryParams();
      this.handleAll();
      this.$message.success("查询成功");
    },

    // 数据筛选重置按钮
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 8,
        staffName: undefined,
        staffNumber: undefined,
      };
      this.getList();
    },
    // 新增按钮操作
    handleAdd() {
      this.resetForm();
      this.open = true;
      this.title = "新增员工";
    },

    // 修改按钮操作
    handleUpdate(staff) {
      this.resetForm();
      getHsStaff(staff.hsSalary.hsStaff.staffId).then((response) => {
        this.form = response;
        this.open = true;
        this.title = "修改员工信息";
      });
    },

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.title == "修改员工信息") {
            updateHsStaff(this.form).then((response) => {
              this.open = false;
              this.title = "";
              if (this.flag == 1) {
                this.getList();
              } else if (this.flag == 2) {
                this.handleAll();
              }
              this.$message.success(response);
            });
          } else {
            addHsStaff(this.form).then((response) => {
              this.open = false;
              this.title = "";
              if (this.flag == 1) {
                this.getList();
              } else if (this.flag == 2) {
                this.handleAll();
              }
              this.$message.success(response);
            });
          }
        }
      });
    },

    submitFormWage: function () {
      this.$refs["formWage"].validate((valid) => {
        if (valid) {
          if (this.titleWage == "发放奖励") {
            getReward(this.formWage).then((response) => {
              this.openWage = false;
              this.titleWage = "";
              if (this.flag == 1) {
                this.getList();
              } else if (this.flag == 2) {
                this.handleAll();
              }
              this.$message.success(response);
            });
          } else {
            getDedute(this.formWage).then((response) => {
              this.openWage = false;
              this.titleWage = "";
              if (this.flag == 1) {
                this.getList();
              } else if (this.flag == 2) {
                this.handleAll();
              }
              this.$message.success(response);
            });
          }
        }
      });
    },

    // 删除按钮操作
    handleDelete(staff) {
      this.$confirm(
        '是否确认删除员工姓名为"' + staff.staffName + '"的当前数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          deleteHsStaff(staff.hsSalary.salaryId);
          return this.$message.success("删除成功");
        })
        .then(() => {
          if (this.flag == 1) {
            this.getList();
          } else if (this.flag == 2) {
            this.handleAll();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    handleDeleteAll(staff) {
      this.$confirm(
        '是否确认删除员工姓名为"' + staff.staffName + '"的所有数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          deleteHsStaffAll(staff.hsSalary.hsStaff.staffId);
          return this.$message.success("删除成功");
        })
        .then(() => {
          if (this.flag == 1) {
            this.getList();
          } else if (this.flag == 2) {
            this.handleAll();
          }
        })
        .catch((error) => {
          console.log(error);
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    // 发放奖励
    reward(staff) {
      if (staff.settleStatus == 1) {
        this.$message.error("该员工工资已发放，不可再操作！");
      } else {
        this.resetFormWage();
        this.openWage = true;
        this.titleWage = "发放奖励";
        // 补充一些字段
        this.formWage.salaryId = staff.hsSalary.salaryId;
        this.formWage.username = store.state.user.name;
      }
    },

    // 扣除工资
    dedute(staff) {
      if (staff.settleStatus == 1) {
        this.$message.error("该员工工资已发放，不可再操作！");
      } else {
        this.resetFormWage();
        this.openWage = true;
        this.titleWage = "扣除工资";
        // 补充一些字段
        this.formWage.salaryId = staff.hsSalary.salaryId;
        this.formWage.username = store.state.user.name;
      }
    },

    // 工资结算
    payWage(staff) {
      if (staff.settleStatus == 1) {
        this.$message.error("该员工已经结算过了，不可重复操作！");
      } else {
        const h = this.$createElement;
        this.$msgbox({
          title: "工资单",
          message: h("div", null, [
            h(
              "p",
              { style: "color: teal" },
              "请确定 " +
                staff.staffName +
                " " +
                staff.year +
                "年" +
                staff.month +
                "月的工资单"
            ),
            h("br", null, null),
            h("p", null, "基本工资：￥" + staff.hsSalary.hsWageRules.basicWage),
            h("p", null, "月总提成：￥" + staff.hsSalary.totalCommission),
            h("p", null, "月奖励：￥" + staff.hsSalary.bonus),
            h("p", null, "月扣除：￥" + staff.hsSalary.deduction),
            h("p", null, "月总工资：￥" + staff.hsSalary.totalWages),
          ]),
          showCancelButton: true,
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        })
          .then(() => {
            if (staff.hsSalary.status == 1) {
              return this.$message.error("不可重复进行结算！");
            } else {
              payHsStaff(staff.hsSalary.salaryId);
              return this.$message.success("结算成功");
            }
          })
          .then(() => {
            if (this.flag == 1) {
              this.getList();
            } else if (this.flag == 2) {
              this.handleAll();
            }
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消结算",
            });
          });
      }
    },
  },
};
</script>

<style scoped>
.center,
.el-pagination {
  display: flex;
  justify-content: center;
  align-items: center;
}
.album {
  min-height: 50rem; /* Can be removed; just added for demo purposes */
  padding-top: 3rem;
  padding-bottom: 3rem;
  background-color: #f7f7f7;
}
.card {
  position: relative;
  float: left;
  width: 23%;
  padding: 0.75rem;
  margin-bottom: 2rem;
  margin-right: 2rem;
  border: 1px solid #efefef;
  text-align: center;
}
.cover {
  display: none;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(181, 197, 202, 0.452);
}
.cover > .el-button {
  width: 40%;
  margin: 0;
  margin-top: 10px;
}
.cover > .el-button:first-child {
  margin-top: 0px;
}
.card:hover .cover {
  display: flex;
}
.detail {
  display: flex;
  height: 250px;
}
.detail-name {
  display: flex;
  flex-direction: column;
  flex: 5;
  height: 100%;
}
.detail-value {
  display: flex;
  flex-direction: column;
  flex: 5;
  height: 100%;
}
/* 两端对齐 */
.detail-name > label {
  display: inline-block;
  text-indent: 1em;
  text-align: justify;
}
.detail-value > label {
  display: inline-block;
  font-weight: 400;
}
.circle-green label:last-child {
  color: #67c23a;
  font-weight: 800;
}
.circle-green label:last-child::before {
  display: inline-block;
  content: "";
  width: 13px;
  height: 13px;
  vertical-align: middle;
  margin-right: 3px;
  background: #f0f9eb;
  border-radius: 50%;
  border: 1px solid #67c23a;
}
.circle-red label:last-child {
  color: #f56c6c;
  font-weight: 800;
}
.circle-red label:last-child::before {
  display: inline-block;
  content: "";
  width: 13px;
  height: 13px;
  vertical-align: middle;
  margin-right: 3px;
  background: #fef0f0;
  border-radius: 50%;
  border: 1px solid #f56c6c;
}
.el-dialog .el-select,
.el-dialog .el-input {
  width: 280px;
}
</style>

<style>
/* 垂直居中 */
.el-dialog {
  display: flex;
  display: -ms-flex; /* 兼容IE */
  flex-direction: column;
  -ms-flex-direction: column; /* 兼容IE */
  margin: 0 !important;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-height: calc(100% - 30px);
  max-width: calc(100% - 30px);
}
.el-dialog .el-dialog__body {
  max-height: 100%;
  flex: 1;
  -ms-flex: 1 1 auto; /* 兼容IE */
  overflow-y: auto;
  overflow-x: hidden;
}

.el-dialog__wrapper {
  /*隐藏ie和edge中遮罩的滚动条*/
  overflow: hidden;
}
</style>
