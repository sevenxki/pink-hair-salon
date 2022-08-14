<template>
  <div class="center">
    <el-row :gutter="100" class="row">
      <el-col :span="12" class="col">
        <!-- 待处理订单列表 -->
        <el-card class="card">
          <h3 align="center">待处理订单列表</h3>
          <ul
            class="infinite-list"
            v-infinite-scroll="getList"
            style="overflow: auto"
          >
            <li
              v-for="item in list"
              class="infinite-list-item"
              :key="item.orderId"
              @click="chooseOrder(item)"
              :class="{ choose: chooseId == item.orderId }"
            >
              <span class="one" :class="{ choose: chooseId == item.orderId }">{{
                item.orderId
              }}</span>
              <span class="two" :class="{ choose: chooseId == item.orderId }">{{
                item.hsProject.projectName
              }}</span>
              <span
                class="three"
                :class="{ choose: chooseId == item.orderId }"
                >{{ item.orderCreatetime }}</span
              >
            </li>
          </ul>
        </el-card>
      </el-col>
      <!-- 订单详情 -->
      <el-col :span="12" class="col">
        <el-card class="card">
          <h3 align="center">订单详情</h3>
          <div class="detail">
            <div class="order">
              <div class="order-name">
                <label>订单编号</label>
                <label>经手人</label>
                <label>具体项目</label>
                <label>护理级别</label>
                <label>服务技师</label>
                <label>服务发型师</label>
                <label>下单时间</label>
              </div>
              <div class="order-value" v-if="orderList.hsProject">
                <label>{{ orderList.orderId }}</label>
                <label>{{ orderList.userStaffName }}</label>
                <label>{{ orderList.hsProject.projectName }}</label>
                <label>{{ orderList.haircareName }}</label>
                <label>{{ orderList.hsStaff1.staffName }}</label>
                <label>{{ orderList.staffHairstylistName }}</label>
                <label>{{ orderList.orderCreatetime }}</label>
              </div>
              <div class="order-value" v-if="!orderList.hsProject"></div>
            </div>
            <div class="prize">
              <label>总价￥</label>
              <label class="total">{{ orderList.total }}</label>
            </div>
            <div class="button">
              <el-button
                type="primary"
                plain
                style="flex: 1"
                @click="handleEdit(orderList)"
                >修改订单</el-button
              >
              <el-button
                type="success"
                plain
                style="flex: 1"
                @click="handlePay(orderList)"
                >结账</el-button
              >
              <el-button
                type="info"
                plain
                style="flex: 1"
                @click="handleDelete(orderList)"
                >删除</el-button
              >
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 修改对话框 -->
    <el-dialog
      title="修改订单"
      width="500px"
      :visible.sync="isEdit"
      append-to-body
      center
    >
      <el-form :model="form" label-width="120px" v-if="form.hsProject">
        <!-- 数据编辑表单组件 -->
        <el-form-item label="订单编号">
          <el-input v-model="form.orderId" disabled />
        </el-form-item>
        <el-form-item label="经手人">
          <el-input v-model="form.userStaffName" disabled />
        </el-form-item>
        <el-form-item label="具体项目">
          <el-select v-model="form.projectId">
            <el-option
              v-for="item in projectOptions"
              :key="item.projectId"
              :label="item.projectName"
              :value="item.projectId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="护理级别">
          <el-select v-model="form.haircare">
            <el-option
              v-for="item in careOptions"
              :key="item.projectId"
              :label="item.projectName"
              :value="item.projectId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务技师">
          <el-select v-model="form.staffTechnicianId">
            <el-option
              v-for="item in techOptions"
              :key="item.staffId"
              :label="item.staffName"
              :value="item.staffId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务发型师">
          <el-select v-model="form.staffHairstylistId">
            <el-option
              v-for="item in hdOptions"
              :key="item.hsStaff.staffId"
              :label="item.hsStaff.staffName"
              :value="item.hsStaff.staffId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下单时间">
          <el-input v-model="form.orderCreatetime" disabled />
        </el-form-item>
      </el-form>
      <!-- 数据提交操作按钮 -->
      <div slot="footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 结账操作 -->
    <el-dialog
      title="结账单"
      :visible.sync="isPay"
      width="35%"
      :before-close="handleClose"
      center
    >
      <el-switch
        v-model="isCheap"
        active-text="选择优惠"
        inactive-text="直接结账"
      >
      </el-switch>
      <el-form :model="payParams">
        <el-form-item label="手机号码" v-if="isCheap">
          <el-input placeholder="请输入手机号码" v-model="telephone">
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="searchCheap"
            ></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="可选优惠" v-if="isCheap">
          <el-radio-group
            v-model="discount"
            size="small"
            v-if="JSON.stringify(cheapList) != '[]'"
            @change="handleSelectDiscount()"
          >
            <el-radio
              :label="item"
              border
              v-for="item in cheapList"
              :key="item.discountId"
              :disabled="isClock"
              >{{ item.discountName }}
              <span
                v-if="item.vipBalance != undefined"
                style="margin-left: 10px"
              >
                余额：{{ item.vipBalance }}</span
              >
              <span
                v-if="
                  item.vipHaircut != undefined || item.vipHaircare != undefined
                "
                style="margin-left: 10px"
              >
                剩余次数：{{ item.vipHaircut }}{{ item.vipHaircare }}</span
              ></el-radio
            >
            <el-tooltip
              :content="'状态: ' + value"
              placement="top"
              style="margin-top: 30px"
            >
              <el-switch
                v-model="value"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-value="锁定"
                inactive-value="未锁定"
                @change="clock($event)"
              >
              </el-switch>
            </el-tooltip>
          </el-radio-group>
          <span v-else style="font-weight: 800; color: black"
            >暂无更多优惠方案</span
          >
        </el-form-item>
        <el-form-item label="支付方式">
          <el-radio-group v-model="payParams.paymentMethod" size="medium">
            <el-radio-button label="0" :disabled="isVipCard">
              <svg class="icon-font" height="30" width="30">
                <use xlink:href="#icon-xianjinzhifu"></use>
              </svg>
            </el-radio-button>
            <el-radio-button label="1" :disabled="isVipCard"
              ><svg class="icon-font" height="30" width="30">
                <use xlink:href="#icon-weixinzhifu"></use></svg
            ></el-radio-button>
            <el-radio-button label="2" :disabled="isVipCard"
              ><svg class="icon-font" height="30" width="30">
                <use xlink:href="#icon-zhifubaozhifu"></use></svg
            ></el-radio-button>
            <el-radio-button label="3" :disabled="isVipCard"
              ><svg class="icon-font" height="30" width="30">
                <use xlink:href="#icon-yinhangkazhifu"></use></svg
            ></el-radio-button>
            <el-radio-button label="4" :disabled="!isVipCard"
              ><svg class="icon-font" height="30" width="30">
                <use xlink:href="#icon-ziyuan"></use>
              </svg>
            </el-radio-button>
          </el-radio-group>
        </el-form-item>
        <div class="prize">
          <label>总价￥</label>
          <label class="total">{{ payParams.totalPayment }}</label>
        </div>
      </el-form>
      <span slot="footer">
        <el-button @click="cancelPay">取 消</el-button>
        <el-button type="primary" @click="submitPayment">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  queryAllNoPayment,
  getHsOrder,
  updateOrder,
  queryAllProjectByKind,
  queryAllHairCare,
  queryAllHDByProject,
  queryTechnician,
  queryAllCheapByTelephone,
  queryPaymentByOrderId,
  getNewPrizeByDiscountId,
  payment,
  deleteByOrderId,
} from "@/api/checkout";
import { getHsKind } from "@/api/kind";
import { getHsProject } from "@/api/project";

export default {
  name: "HsCheckout",
  data() {
    return {
      // 项目列表
      projectOptions: [],
      // 护理列表
      careOptions: [],
      // 技师列表
      techOptions: [],
      // 发型师列表
      hdOptions: [],
      // 选中的订单编号
      chooseId: "",
      // 是否修改
      isEdit: false,
      // 是否结账
      isPay: false,
      // 是否选择优惠
      isCheap: false,
      // 预约订单列表
      list: [],
      // 选择的订单
      orderList: {
        haircare: undefined,
        haircareName: undefined,
        hsProject: {},
        hsStaff1: {},
        hsUser: {},
        orderCreatetime: undefined,
        orderId: undefined,
        projectId: undefined,
        staffHairstylistId: undefined,
        staffHairstylistName: undefined,
        staffTechnicianId: undefined,
        status: undefined,
        total: undefined,
        userId: undefined,
        userStaffName: undefined,
      },
      // 数据编辑表单
      form: {
        haircare: undefined,
        haircareName: undefined,
        hsProject: {},
        hsStaff1: {},
        hsUser: {},
        orderCreatetime: undefined,
        orderId: undefined,
        projectId: undefined,
        staffHairstylistId: undefined,
        staffHairstylistName: undefined,
        staffTechnicianId: undefined,
        status: undefined,
        total: undefined,
        userId: undefined,
        userStaffName: undefined,
      },
      // 类别名称
      kindName: "",
      // 具体项目名称
      projectName: "",
      // 用户的手机号
      telephone: undefined,
      // 是否选择了会员
      isVipCard: false,
      // 可选优惠
      cheapList: [],
      // 锁定选择的优惠
      value: "未锁定",
      // 是否进行锁定
      isClock: false,
      // 结账表单的前端数据
      discount: {},
      payParams: {
        paymentMethod: undefined,
        totalPayment: undefined,
      },
      // 结账的表单
      payForm: {
        paymentOrder: {},
        yy: undefined,
      },
    };
  },
  created() {
    this.getList();
  },
  watch: {
    isCheap() {
      if (this.isCheap == false) {
        this.telephone = undefined;
        this.isVipCard = false;
        this.cheapList = [];
        this.value = "未锁定";
        this.isClock = false;
        this.discount = {};
      }
    },
  },
  methods: {
    // 初始化数据加载
    getList() {
      // 获取待处理订单
      queryAllNoPayment().then((response) => {
        this.list = response;
        return new Promise((resolve, reject) => {
          this.getOneList(this.chooseId);
        });
      });
    },
    // 获取单条数据
    getOneList(orderId) {
      getHsOrder(orderId).then((response) => {
        this.orderList = response;
      });
    },
    // 选择订单
    chooseOrder(item) {
      this.getOneList(item.orderId);
      this.chooseId = item.orderId;
    },
    // 修改订单
    handleEdit(item) {
      if (!item.orderId) {
        this.$message({
          message: "请至少先选择一个订单",
          type: "warning",
        });
      } else {
        this.form = item;
        this.isEdit = true;
        // 先后顺序
        getHsKind(this.orderList.hsProject.kindId).then((response) => {
          this.kindName = response.kindName;
          return new Promise((resolve, reject) => {
            // 具体项目
            queryAllProjectByKind(this.kindName).then((response) => {
              this.projectOptions = response;
            });
          });
        });
        //护理级别
        queryAllHairCare().then((response) => {
          this.careOptions = response;
        });
        getHsProject(this.orderList.projectId).then((response) => {
          this.projectName = response.projectName;
          return new Promise((resolve, reject) => {
            //发型师
            queryAllHDByProject(this.projectName).then((response) => {
              this.hdOptions = response;
            });
          });
        });
        //技师
        queryTechnician().then((response) => {
          this.techOptions = response;
        });
      }
    },
    // 确定修改
    submitForm() {
      // 修改数据
      updateOrder(this.form).then((response) => {
        this.isEdit = false;
        this.getList();
        this.$message.success(response);
      });
    },
    // 取消修改
    cancel() {
      this.isEdit = false;
      this.resetForm();
    },

    // 数据编辑表单清空
    resetForm() {
      this.form = {
        haircare: undefined,
        haircareName: undefined,
        hsProject: {},
        hsStaff1: {},
        hsUser: {},
        orderCreatetime: undefined,
        orderId: undefined,
        projectId: undefined,
        staffHairstylistId: undefined,
        staffHairstylistName: undefined,
        staffTechnicianId: undefined,
        status: undefined,
        total: undefined,
        userId: undefined,
        userStaffName: undefined,
      };
    },
    // 处理关闭结账对话框
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          this.cancelPay();
        })
        .catch((_) => {});
    },
    // 点击删除按钮
    handleDelete(orderList) {
      if (!this.orderList.orderId) {
        this.$message({
          message: "请至少先选择一个订单",
          type: "warning",
        });
      } else {
        this.$confirm(
          '是否确认删除订单号为"' + orderList.orderId + '"的订单?',
          "警告",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          }
        )
          .then(function () {
            return deleteByOrderId(orderList.orderId);
          })
          .then(() => {
            // 重置
            this.chooseId = "";
            this.isCheap = false;
            this.isPay = false;
            this.telephone = undefined;
            this.isVipCard = false;
            this.cheapList = [];
            this.value = "未锁定";
            this.isClock = false;
            this.discount = {};
            this.payParams = {
              paymentMethod: undefined,
              totalPayment: undefined,
            };
            this.getList();
            this.$message.success("删除成功");
          })
          .catch(() => {
            this.$message.error("取消删除");
          });
      }
    },
    // 点击结账按钮
    handlePay(orderList) {
      if (!this.orderList.orderId) {
        this.$message({
          message: "请至少先选择一个订单",
          type: "warning",
        });
      } else {
        this.isPay = true;
        queryPaymentByOrderId(orderList.orderId).then((response) => {
          this.payForm = response;
          this.payParams.totalPayment = this.payForm.paymentOrder.totalPayment;
        });
      }
    },
    // 获取所有可选优惠
    searchCheap() {
      let query = {
        telephone: this.telephone,
        orderId: this.orderList.orderId,
      };
      queryAllCheapByTelephone(query).then((response) => {
        this.cheapList = response;
      });
    },
    // 处理选择的优惠
    handleSelectDiscount() {
      if (this.discount.discountName.indexOf("会员卡") != -1) {
        this.isVipCard = true;
        this.payParams.paymentMethod = 4;
      } else {
        this.isVipCard = false;
      }
    },
    // 切换锁定状态
    clock() {
      if (this.discount.discountId == undefined) {
        this.$message({
          message: "请选择再锁定！",
          type: "warning",
        });
        this.value = "未锁定";
      } else {
        this.isClock = !this.isClock;
        if (this.isClock == true) {
          // 获取优惠的总金额
          let query = {
            discountId: this.discount.discountId,
            orderId: this.orderList.orderId,
          };
          getNewPrizeByDiscountId(query).then((response) => {
            this.payParams.totalPayment = response;
          });
        } else {
          this.payParams.totalPayment = this.payForm.paymentOrder.totalPayment;
        }
      }
    },
    // 取消支付
    cancelPay() {
      // 重置
      this.isCheap = false;
      this.isPay = false;
      this.telephone = undefined;
      this.isVipCard = false;
      this.cheapList = [];
      this.value = "未锁定";
      this.isClock = false;
      this.discount = {};
      this.payParams = {
        paymentMethod: undefined,
        totalPayment: undefined,
      };
    },
    // 确定支付
    submitPayment() {
      if (JSON.stringify(this.cheapList) != "[]" && !this.isClock) {
        this.$message({
          message: "请锁定您所选择的优惠",
          type: "warning",
        });
      } else if (!this.payParams.paymentMethod) {
        this.$message({
          message: "请选择支付方式",
          type: "warning",
        });
      } else {
        let params = {
          orderId: this.orderList.orderId,
          paymentMethod: this.payParams.paymentMethod,
          phonePayment: this.telephone,
          price: this.payParams.totalPayment,
          discountId: this.discount.discountId || 0,
        };
        payment(params).then((response) => {
          // 重置
          this.chooseId = "";
          this.isCheap = false;
          this.isPay = false;
          this.telephone = undefined;
          this.isVipCard = false;
          this.cheapList = [];
          this.value = "未锁定";
          this.isClock = false;
          this.discount = {};
          this.payParams = {
            paymentMethod: undefined,
            totalPayment: undefined,
          };
          this.getList();
          this.$message.success("已结账");
        });
      }
    },
  },
};
</script>

<style scoped>
.center {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;
  width: 90%;
  height: 85%;
}
.row,
.col,
.card,
.card ul {
  height: 100%;
}
/* el-card内部有个body，用样式穿透设置高度 */
.card >>> .el-card__body {
  height: 100%;
}
/* 去掉默认样式 */
.card ul,
.item {
  padding: 0;
  margin: 0;
}
.card ul::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
.card li {
  display: flex;
  list-style: none;
  border: 2px solid #ebeef5;
  border-radius: 7px;
  height: 15%;
  margin-bottom: 17px;
  cursor: pointer;
}
span {
  text-align: center;
  width: 100%;
  margin: auto;
}
.one {
  flex: 0.6;
  font-size: 26px;
  font-weight: 700;
  color: rgb(66, 185, 131);
}
.two {
  flex: 1.9;
  font-size: 18px;
  font-weight: 700;
  color: rgb(43, 47, 58);
}
.three {
  flex: 1.1;
  font-size: 18px;
  font-weight: 700;
  color: rgb(41, 41, 136);
}
.choose {
  background-color: rgb(66, 185, 131);
  color: white !important;
}
.detail {
  display: flex;
  flex-direction: column;
  width: 80%;
  height: 100%;
  margin: 0 auto;
}
.order {
  display: flex;
  height: 65%;
  margin-top: 4%;
}
.prize {
  display: flex;
  height: 10%;
  margin-left: 60%;
  line-height: 10%;
}
.button {
  display: flex;
  height: 7%;
  text-align: center;
  min-width: 100%;
}
.order-name {
  display: flex;
  flex-direction: column;
  flex: 3;
  height: 100%;
}
.order-value {
  display: flex;
  flex-direction: column;
  flex: 7;
  height: 100%;
}
/* 两端对齐 */
.order-name > label {
  display: inline-block;
  width: 85%;
  text-indent: 1em;
  height: 13%;
  text-align: justify;
}
.order-value > label {
  display: inline-block;
  height: 13%;
  font-weight: 400;
  padding-left: 20%;
}
.order-name > label:after {
  display: inline-block;
  width: 100%;
  content: "";
  height: 0;
  font-size: 0;
}

.prize > label {
  flex-grow: 0;
  width: 50%;
  padding-top: 13%;
}
.total {
  font-size: 25px;
  color: rgb(66, 185, 131);
}
/* 对话框居中显示 */
.el-dialog__wrapper >>> .el-dialog {
  position: absolute;
  top: 50%;
  left: 50%;
  margin: 0 !important;
  transform: translate(-50%, -50%);
  max-height: calc(100% - 30px);
  max-width: calc(100% - 30px);
  display: flex;
  flex-direction: column;
}
.el-input,
.el-select {
  width: 80%;
}
.el-switch {
  padding-bottom: 10%;
}
.el-radio {
  display: block;
  width: 90%;
}
.el-radio.is-bordered + .el-radio.is-bordered {
  margin: 0px;
  margin-top: 10px;
}
/* 用fill属性直接设置会失效，因此采用深度 */
.el-radio-button
  >>> .el-radio-button__orig-radio:checked
  + .el-radio-button__inner {
  background-color: #f5f7fa;
  border-color: #e0e3e9;
  box-shadow: -1px 0 0 0 #e0e3e9;
}
</style>