<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="24" :xs="24">
        <!-- 数据筛选表单 -->
        <el-form
          ref="queryForm"
          :model="queryParams"
          :inline="true"
          label-width="100px"
          class="center"
        >
          <!-- 数据筛选表单组件 -->
          <el-form-item label="会员帐号">
            <el-input
              v-model="queryParams.phonePayment"
              placeholder="请输入会员电话号码"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="付款时间">
            <el-date-picker
              v-model="queryParams.time"
              type="date"
              placeholder="选择付款时间"
              value-format="yyyy-MM-dd"
              clearable
              @keyup.enter.native="handleQuery"
            >
            </el-date-picker>
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
        <!-- 数据表格 -->
        <el-table v-loading="loading" :data="hsHistoryList">
          <!-- 数据表格列 -->
          <el-table-column
            label="ID"
            align="center"
            prop="paymentOrderId"
            width="50px"
          />
          <el-table-column
            label="经手人姓名"
            align="center"
            prop="hsOrder.userStaffName"
          />
          <el-table-column
            label="具体项目"
            align="center"
            prop="hsOrder.hsProject.projectName"
          />
          <el-table-column
            label="护理级别"
            align="center"
            prop="hsOrder.haircareName"
          />
          <el-table-column
            label="发型师姓名"
            align="center"
            prop="hsOrder.staffHairstylistName"
          />
          <el-table-column
            label="技师姓名"
            align="center"
            prop="hsOrder.hsStaff1.staffName"
          />
          <el-table-column
            label="会员帐号"
            align="center"
            prop="phonePayment"
            width="110px"
          />
          <el-table-column
            label="优惠方式"
            align="center"
            prop="discountName"
            :formatter="discountFormat"
          />
          <el-table-column
            label="付款方式"
            align="center"
            prop="paymentMethod"
            :formatter="paymentFormat"
          />
          <el-table-column
            label="下单时间"
            align="center"
            prop="hsOrder.orderCreatetime"
            width="100px"
          />
          <el-table-column
            label="付款时间"
            align="center"
            prop="time"
            width="100px"
          />
          <el-table-column
            label="订单总金额"
            align="center"
            prop="hsOrder.total"
          />
          <el-table-column
            label="结账总金额"
            align="center"
            prop="totalPayment"
          />
          <!-- 数据行操作按钮 -->
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
            v-if="editAndDelete"
          >
            <template slot-scope="scope">
              <el-button
                size="small"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 数据分页加载 -->
        <el-pagination
          :current-page="pageNum"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          @pagination="getList"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
/** 导入axios Api */
import { listHsHistoryByPage, deleteHsHistory } from "@/api/history";
export default {
  name: "HsHistory",
  data() {
    return {
      editAndDelete: true,
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // HsHistory表格数据
      hsHistoryList: [],
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        phonePayment: undefined,
        time: undefined,
      },
    };
  },
  created() {
    this.getList();
    if (
      !(
        localStorage.getItem("permissions") &&
        JSON.parse(localStorage.getItem("permissions")).includes(
          "editAndDelete"
        )
      )
    ) {
      this.editAndDelete = false;
    }
  },
  methods: {
    /** 查询历史订单数据列表 */
    getList() {
      this.loading = true;
      // console.log(this.queryParams);
      listHsHistoryByPage(this.queryParams).then((response) => {
        // console.log(this.queryParams);
        this.hsHistoryList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;
      });
    },
    /** 处理分页 */
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },

    /** 数据编辑对话框取消按钮 */
    cancel() {
      this.open = false;
      this.resetForm();
    },

    /** 数据筛选参数重置按钮 */
    resetQueryParams() {
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 5;
    },

    /** 数据筛选搜索按钮 */
    handleQuery() {
      this.resetQueryParams();
      this.getList();
      this.$message.success("查询成功");
    },

    /** 数据筛选重置按钮 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 5,
        phonePayment: undefined,
        time: undefined,
      };
      this.getList();
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm(
        '是否确认删除ID为"' + row.paymentOrderId + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return deleteHsHistory(row.paymentOrderId);
        })
        .then(() => {
          this.getList();
          this.$message.success("删除成功");
        })
        .catch(() => {});
    },
    // 优惠方式格式化
    discountFormat(row) {
      if (row.useVip == 1) {
        return "会员卡";
      } else if (row.useDiscount == 2) {
        return "剪发卡";
      } else if (row.useDiscount == 3) {
        return "护理卡";
      } else {
        return "无";
      }
    },
    // 支付方式格式化
    paymentFormat(row) {
      if (row.paymentMethod == 0) {
        return "现金支付";
      } else if (row.paymentMethod == 1) {
        return "微信支付";
      } else if (row.paymentMethod == 2) {
        return "支付宝支付";
      } else if (row.paymentMethod == 3) {
        return "银行卡支付";
      } else if (row.paymentMethod == 4) {
        return "会员卡支付";
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
</style>