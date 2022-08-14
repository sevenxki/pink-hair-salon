<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="24" :xs="24">
        <el-card class="box-card" shadow="hover" v-show="searchCard">
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
            <el-form-item label="会员姓名" prop="vipName">
              <el-input
                v-model="queryParams.vipName"
                placeholder="请输入会员姓名"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="联系方式" prop="vipPhone">
              <el-input
                v-model="queryParams.vipPhone"
                placeholder="请输入联系方式"
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

        <el-row :gutter="10" class="mb8">
          <el-col :span="3">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="small"
              @click="handleAdd"
              style="margin: 10px"
              >新增会员
            </el-button>
          </el-col>
        </el-row>

        <el-card
          class="box-card"
          shadow="0px"
          v-show="vipCard"
          style="
            padding: 20px;
            padding-left: 50px;
            padding-right: 50px;
            padding-bottom: 10px;
          "
        >
          <el-radio-group v-model="vipRadio" @change="chooseVip(vipRadio)">
            <el-radio-button
              plain
              v-for="(item, index) in hsVipList"
              :key="index"
              :label="item.vipPhone"
              style="
                margin: 20px;
                width: 230px;
                margin-bottom: 10px;
                margin-top: 5px;
              "
              align="center"
            >
              <div>
                <div style="margin: 10px; width: 150px" align="left">
                  会员姓名：{{ item.vipName }}
                  <icon
                    v-show="item.accumulationTotal < 500"
                    name="vipL1"
                    :w="70"
                  ></icon>
                  <icon
                    v-show="
                      item.accumulationTotal >= 500 &&
                      item.accumulationTotal < 1000
                    "
                    name="vipL2"
                    :w="70"
                  ></icon>
                  <icon
                    v-show="
                      item.accumulationTotal >= 1000 &&
                      item.accumulationTotal < 1500
                    "
                    name="vipL3"
                    :w="70"
                  ></icon>
                  <icon
                    v-show="
                      item.accumulationTotal >= 1500 &&
                      item.accumulationTotal < 2000
                    "
                    name="vipL4"
                    :w="70"
                  ></icon>
                  <icon
                    v-show="
                      item.accumulationTotal >= 2000 &&
                      item.accumulationTotal < 2500
                    "
                    name="vipL5"
                    :w="70"
                  ></icon>
                  <icon
                    v-show="item.accumulationTotal >= 2500"
                    name="vipL6"
                    :w="70"
                  ></icon>
                </div>
                <div style="margin: 10px; margin-bottom: 15px" align="left">
                  联系方式：{{ item.vipPhone }}
                </div>
                <div style="margin: 10px; margin-bottom: 15px" align="left">
                  会员卡余额：￥{{ item.vipBalance }}
                </div>
                <div style="margin: 10px; margin-bottom: 15px" align="left">
                  剪发剩余次数：{{ item.vipHaircut }}次
                </div>
                <div style="margin: 10px; margin-bottom: 15px" align="left">
                  护理剩余次数：{{ item.vipHaircare }}次
                </div>
              </div>
            </el-radio-button>
          </el-radio-group>
        </el-card>

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

    <!-- 新增会员 -->
    <el-dialog
      :title="title"
      :visible.sync="openNew"
      width="500px"
      top="60px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="120px"
        style="margin-left: 40px"
      >
        <!-- 数据编辑表单组件 -->
        <el-form-item label="会员姓名" prop="vipName" style="width: 330px">
          <el-input
            clearable
            v-model="form.vipName"
            placeholder="请输入会员姓名"
          />
        </el-form-item>
        <el-form-item label="会员性别" prop="vipGender">
          <el-select
            v-model="form.vipGender"
            placeholder="请选择"
            style="width: 210px"
          >
            <el-option
              v-for="item in genderTypeOptions"
              :key="item.id"
              :label="item.value"
              :value="item.id"
              @keyup.enter.native="handleQuery"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="vipPhone" style="width: 330px">
          <el-input
            clearable
            v-model="form.vipPhone"
            placeholder="请输入联系方式"
          />
        </el-form-item>
        <el-form-item label="会员生日" prop="vipBirthday">
          <el-date-picker
            v-model="form.vipBirthday"
            type="date"
            placeholder="选择日期"
            style="width: 210px"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="充值金额" prop="invest" style="width: 330px">
          <el-col span="15">
            <el-input
              v-model="form.vipBalance"
              placeholder="请输入充值金额"
              @change="giftAmountChange"
            />
          </el-col>
          <el-col span="9">
            <span v-show="gift" style="margin-left: 10px"> 赠￥</span>
            <span v-show="gift" class="money">{{ this.giftAmount }}</span>
          </el-col>
        </el-form-item>
        <el-form-item label="剪发团购" prop="haircutDiscount">
          <el-input-number
            size="small"
            v-model="haircutNum"
            @change="handleHaircutNum"
            :min="0"
            :max="10"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="护理团购" prop="haircutDiscount">
          <el-input-number
            size="small"
            v-model="haircareNum"
            @change="handleHaircareNum"
            :min="0"
            :max="10"
          ></el-input-number>
        </el-form-item>
      </el-form>

      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">支 付</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--  查看会员详情 -->
    <el-dialog
      :visible.sync="checkDetails"
      width="530px"
      top="60px"
      append-to-body
      v-model="vipRadio"
      @close="closeDialog"
    >
      <div align="center" style="margin: 15px">
        <span style="font-size: 30px">会员详情</span>
      </div>
      <div style="margin-top: 20px">
        <div
          style="
            margin: 30px;
            margin-left: 100px;
            border-width: 6px;
            border-color: #263445;
          "
        >
          <span style="font-size: 15px">会员姓名：</span>
          <span style="font-size: 20px; margin-left: 65px; color: #263445">{{
            hsVipList.vipName
          }}</span>
          <icon
            v-show="hsVipList.accumulationTotal < 500"
            name="vipL1"
            :w="70"
          ></icon>
          <icon
            v-show="
              hsVipList.accumulationTotal >= 500 &&
              hsVipList.accumulationTotal < 1000
            "
            name="vipL2"
            :w="70"
          ></icon>
          <icon
            v-show="
              hsVipList.accumulationTotal >= 1000 &&
              hsVipList.accumulationTotal < 1500
            "
            name="vipL3"
            :w="70"
          ></icon>
          <icon
            v-show="
              hsVipList.accumulationTotal >= 1500 &&
              hsVipList.accumulationTotal < 2000
            "
            name="vipL4"
            :w="70"
          ></icon>
          <icon
            v-show="
              hsVipList.accumulationTotal >= 2000 &&
              hsVipList.accumulationTotal < 2500
            "
            name="vipL5"
            :w="70"
          ></icon>
          <icon
            v-show="hsVipList.accumulationTotal >= 2500"
            name="vipL6"
            :w="70"
          ></icon>
        </div>
        <div
          style="
            margin: 30px;
            margin-left: 100px;
            border-width: 6px;
            border-color: #263445;
          "
        >
          <span style="font-size: 15px">会员性别：</span>
          <span style="font-size: 20px; margin-left: 74px; color: #263445">{{
            this.vipGenderShow
          }}</span>
        </div>
        <div style="margin: 30px; margin-left: 100px">
          <span style="font-size: 15px">联系方式：</span>
          <span style="font-size: 20px; margin-left: 43px; color: #263445">{{
            hsVipList.vipPhone
          }}</span>
        </div>
        <div style="margin: 30px; margin-left: 100px">
          <span style="font-size: 15px">会员生日：</span>
          <span style="font-size: 20px; margin-left: 38px; color: #263445">{{
            hsVipList.vipBirthday
          }}</span>
        </div>
        <div style="margin: 30px; margin-left: 100px">
          <span style="font-size: 15px">会员卡余额：</span>
          <span style="font-size: 15px; margin-left: 45px">￥</span>
          <span style="font-size: 20px; margin-left: 5px" class="money">{{
            hsVipList.vipBalance
          }}</span>
        </div>
        <div style="margin: 30px; margin-left: 100px">
          <span style="font-size: 15px">理发剩余次数：</span>
          <span style="font-size: 20px; margin-left: 45px; color: #263445">{{
            hsVipList.vipHaircut
          }}</span>
          <span style="font-size: 15px; margin-left: 5px">次</span>
        </div>
        <div style="margin: 30px; margin-left: 100px">
          <span style="font-size: 15px">护理剩余次数：</span>
          <span style="font-size: 20px; margin-left: 45px; color: #263445">{{
            hsVipList.vipHaircare
          }}</span>
          <span style="font-size: 15px; margin-left: 5px">次</span>
        </div>
      </div>
      <div align="center">
        <el-form>
          <el-form-item>
            <el-row>
              <el-button
                plain
                icon="el-icon-edit"
                @click="handleUpdate(vipRadio)"
                >修 改</el-button
              >
              <el-button
                plain
                icon="el-icon-delete"
                @click="handleDelete(vipRadio)"
                >删 除</el-button
              >
              <el-button
                plain
                icon="el-icon-bank-card"
                @click="handleInvest(vipRadio)"
                >充 值</el-button
              >
              <el-button
                plain
                icon="el-icon-shopping-cart-2"
                @click="handleDiscount(vipRadio)"
                >团 购</el-button
              >
            </el-row>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>

    <!--  更新会员信息 -->
    <el-dialog
      :title="title"
      :visible.sync="update"
      width="530px"
      top="60px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="120px"
        style="margin-left: 40px"
      >
        <!-- 数据编辑表单组件 -->
        <el-form-item label="会员姓名" prop="vipName" style="width: 330px">
          <el-input
            clearable
            v-model="form.vipName"
            placeholder="请输入会员姓名"
          />
        </el-form-item>
        <el-form-item label="会员性别" prop="vipGender">
          <el-select
            v-model="form.vipGender"
            placeholder="请选择"
            style="width: 210px"
          >
            <el-option
              v-for="item in genderTypeOptions"
              :key="item.id"
              :label="item.value"
              :value="item.id"
              @keyup.enter.native="handleQuery"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="vipPhone" style="width: 330px">
          <el-input v-model="form.vipPhone" :readonly="true" />
        </el-form-item>
        <el-form-item label="会员生日" prop="vipBirthday">
          <el-date-picker
            v-model="form.vipBirthday"
            type="date"
            placeholder="选择日期"
            style="width: 210px"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>

      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitUpdateForm">确定修改</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 充值 -->
    <el-dialog
      :title="title"
      :visible.sync="invest"
      width="530px"
      top="130px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="investRules"
        label-width="120px"
        style="margin-left: 40px"
      >
        <!-- 数据编辑表单组件 -->
        <el-form-item label="会员姓名" prop="vipName" style="width: 330px">
          <el-input v-model="form.vipName" :readonly="true" />
        </el-form-item>
        <el-form-item label="联系方式" prop="vipPhone" style="width: 330px">
          <el-input v-model="form.vipPhone" :readonly="true" />
        </el-form-item>
        <el-form-item label="充值金额" prop="vipBalance" style="width: 330px">
          <el-col span="15">
            <el-input
              v-model="form.vipBalance"
              placeholder="请输入充值金额"
              @change="giftAmountChange"
            />
          </el-col>
          <el-col span="9">
            <span v-show="gift" style="margin-left: 10px"> 赠￥</span>
            <span v-show="gift" class="money">{{ this.giftAmount }}</span>
          </el-col>
        </el-form-item>
      </el-form>
      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" @click="submitInvestForm">支 付</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 团购 -->
    <el-dialog
      :title="title"
      :visible.sync="discount"
      width="530px"
      top="130px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="discountRules"
        label-width="120px"
        style="margin-left: 40px"
      >
        <!-- 数据编辑表单组件 -->
        <el-form-item label="会员姓名" prop="vipName" style="width: 330px">
          <el-input clearable v-model="form.vipName" :readonly="true" />
        </el-form-item>
        <el-form-item label="联系方式" prop="vipPhone" style="width: 330px">
          <el-input v-model="form.vipPhone" :readonly="true" />
        </el-form-item>
        <el-form-item label="剪发团购" prop="haircutDiscount">
          <el-input-number
            size="small"
            v-model="haircutNum"
            @change="handleHaircutNum"
            :min="0"
            :max="10"
          ></el-input-number>
          <span style="color: rgba(31, 29, 28, 0.38); margin-left: 3px"
            >(剪发优惠卡：150元3次)</span
          >
        </el-form-item>
        <el-form-item label="护理团购" prop="haircutDiscount">
          <el-input-number
            size="small"
            v-model="haircareNum"
            @change="handleHaircareNum"
            :min="0"
            :max="10"
          ></el-input-number>
          <span style="color: rgba(31, 29, 28, 0.38); margin-left: 3px"
            >(护理优惠卡：200元3次)</span
          >
        </el-form-item>
      </el-form>
      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" @click="submitDiscountForm">支 付</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listHsVip,
  listHsVipByPage,
  listHsVipByPhone,
  addHsVip,
  updateHsVip,
  deleteHsVip,
  investHsVip,
  discountHsVip,
} from "@/api/member";

export default {
  name: "HsVip",
  data() {
    return {
      /** 设置各个内容的显示状态 */
      // card页面
      searchCard: true,
      vipCard: true,
      // 赠金
      gift: false,
      // 查看会员信息详情
      checkDetails: false,
      // 新增会员信息
      openNew: false,
      // 更新会员信息
      update: false,
      // 充值页面
      invest: false,
      // 团购页面
      discount: false,
      // 转换性别显示
      vipGenderShow: undefined,

      /** 初始值 */
      // 记录所选的会员手机号
      vipRadio: undefined,
      chooseVipPhone: "",

      // 会员级别标识
      vipLevelIcon: "",

      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // 弹出层标题
      title: "",

      // 会员累积购买优惠金额
      accumulation: "",
      // 充值赠送金额初始值
      giftAmount: 0,
      // 团购优惠数量初始值
      haircareNum: 0,
      haircutNum: 0,
      // 生日日期
      birthdayValue: "",
      // 性别类型
      genderTypeOptions: [
        { id: 1, value: "男" },
        { id: 2, value: "女" },
      ],
      // 充值优惠类型
      investTypeOptions: undefined,
      // 团购优惠类型
      discountTypeOptions: [
        { id: 1, value: "剪发优惠卡：150元3次" },
        { id: 2, value: "护理优惠卡：200元3次" },
      ],
      // 余额
      vipLevelBalance: "",

      // HsVip数据
      hsVipList: {
        vipName: "",
        vipGender: "",
        vipPhone: "",
        vipBirthday: "",
        vipBalance: "",
        vipHaircut: "",
        vipHaircare: "",
        accumulationId: "",
        accumulationTotal: "",
      },

      // 更新参数
      updateForm: {
        vipName: undefined,
        vipGender: undefined,
        vipPhone: undefined,
        vipBirthday: undefined,
        vipBalance: undefined,
        vipHaircut: undefined,
        vipHaircare: undefined,
      },

      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 8,
        vipName: undefined,
        vipGender: undefined,
        vipPhone: undefined,
        vipBirthday: undefined,
        vipBalance: undefined,
        vipHaircut: undefined,
        vipHaircare: undefined,
        accumulationId: undefined,
        accumulationTotal: undefined,
      },

      // 新增或修改会员数据编辑表单
      form: {
        vipName: undefined,
        vipGender: undefined,
        vipPhone: undefined,
        vipBirthday: undefined,
        vipBalance: undefined,
        vipHaircut: undefined,
        vipHaircare: undefined,
      },
      // 表单数据校验规则
      rules: {
        vipName: [
          { required: true, message: "会员姓名不能为空", trigger: "blur" },
        ],
        vipPhone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
        ],
        vipGender: [
          { required: true, message: "性别不能为空", trigger: "blur" },
        ]
      },
      // 充值数据校验规则
      investRules: {
        vipBalance: [
          { required: true, message: "充值金额不能为空", trigger: "blur" },
        ],
      },
      // 团购数据校验规则
      discountRules: {
        discount: [
          { required: true, message: "优惠选择不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询HsMember数据列表 */
    getList() {
      this.loading = true;
      listHsVipByPage(this.queryParams).then((response) => {
        this.hsVipList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;
        if (this.hsVipList.vipGender == 1) {
          this.vipGenderShow = "男";
        } else {
          this.vipGenderShow = "女";
        }
      });
    },

    /** 选择会员*/
    chooseVip(vipRadio) {
      this.checkDetails = true;
      this.title = "";
      listHsVipByPhone(vipRadio).then((response) => {
        this.hsVipList = response;
        this.loading = false;
      });
    },

    /** 动态显示赠送金额 */
    giftAmountChange() {
      if (this.form.vipBalance < 500) {
        this.giftAmount = 0;
      } else if (this.form.vipBalance >= 500 && this.form.vipBalance < 1000) {
        this.giftAmount = this.form.vipBalance * 0.1;
        this.giftAmount = this.giftAmount.toFixed(0);
      } else if (this.form.vipBalance >= 1000 && this.form.vipBalance < 2000) {
        this.giftAmount = this.form.vipBalance * 0.12;
        this.giftAmount = this.giftAmount.toFixed(0);
      } else {
        this.giftAmount = this.form.vipBalance * 0.15;
        this.giftAmount = this.giftAmount.toFixed(0);
      }
      this.gift = true;
    },

    /** 处理新增团购优惠 */
    handleHaircutNum(value) {
      this.form.vipHaircut = value;
    },
    handleHaircareNum(value) {
      this.form.vipHaircare = value;
    },

    /** 数据筛选搜索按钮 */
    handleQuery() {
      // console.log("query:" + this.queryParams.vipName);
      listHsVipByPage(this.queryParams).then((response) => {
        this.hsVipList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
      });
      this.resetQueryParams();
      this.$message.success("查询成功");
    },
    /** 数据筛选重置按钮 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 8,
        vipName: undefined,
        vipPhone: undefined,
      };
    },

    /** 数据编辑表单清空 */
    resetForm() {
      this.form = {
        vipName: undefined,
        vipGender: undefined,
        vipPhone: undefined,
        vipBirthday: undefined,
        vipBalance: undefined,
        vipHaircut: undefined,
        vipHaircare: undefined,
      };
      this.haircutNum = 0;
      this.haircareNum = 0;
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.resetForm();
      this.openNew = true;
      this.title = "新增会员";
    },
    /** 修改按钮操作 */
    handleUpdate(phone) {
      // this.checkDetails = false;
      this.update = true;
      this.title = "修改会员信息";
      this.form.vipPhone = phone;
      listHsVipByPhone(phone).then((response) => {
        this.form = response;
      });
    },

    /** 删除按钮操作 */
    handleDelete(phone) {
      this.$confirm('是否确认删除联系方式为"' + phone + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return deleteHsVip(phone);
        })
        .then(() => {
          this.checkDetails = false;
          this.getList();
          this.$message.success("删除成功");
        })
        .catch(() => {});
    },

    /** 充值操作 */
    handleInvest(phone) {
      this.invest = true;
      this.title = "充值";
      this.form.vipPhone = phone;
      listHsVipByPhone(phone).then((response) => {
        this.form.vipName = response.vipName;
      });
    },

    /** 团购操作 */
    handleDiscount(phone) {
      this.discount = true;
      this.title = "团购";
      this.form.vipPhone = phone;
      listHsVipByPhone(phone).then((response) => {
        this.form.vipName = response.vipName;
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

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        addHsVip(this.form).then((response) => {
          this.openNew = false;
          this.title = "";
          this.getList();
          this.resetForm();
          this.gift = false;
          this.$message.success(response);
        });
      });
    },
    /** 提交更改按钮 */
    submitUpdateForm: function () {
      this.$refs["form"].validate((valid) => {
        this.updateForm = this.form;
        updateHsVip(this.form).then((response) => {
          this.checkDetails = false;
          this.update = false;
          this.checkDetails = true;
          this.title = "";
          this.resetForm();
          this.gift = false;
          listHsVipByPhone(this.updateForm.vipPhone).then((response) => {
            this.hsVipList = response;
            this.loading = false;
            if (this.hsVipList.vipGender == 1) {
              this.vipGenderShow = "男";
            } else {
              this.vipGenderShow = "女";
            }
          });
          this.$message.success(response);
        });
      });
    },

    /** 充值数据提交按钮 */
    submitInvestForm: function () {
      this.$refs["form"].validate((valid) => {
        investHsVip(this.form).then((response) => {
          this.invest = false;
          this.checkDetails = false;
          this.title = "";
          this.getList();
          this.$message.success(response);
        });
      });
    },
    /** 团购数据提交按钮 */
    submitDiscountForm: function () {
      this.$refs["form"].validate((valid) => {
        discountHsVip(this.form).then((response) => {
          this.discount = false;
          this.checkDetails = false;
          this.title = "";
          this.resetForm();
          this.gift = false;
          this.getList();
          this.$message.success(response);
        });
      });
    },
    /** 数据编辑对话框取消按钮 */
    cancel() {
      this.openNew = false;
      this.invest = false;
      this.discount = false;
      this.update = false;
      this.gift = false;
      this.resetForm();
      // location.reload();
    },

    /** 关闭对话框 */
    closeDialog() {
      location.reload();
    },
  },
};
</script>

<style scoped>
.money {
  font-size: 15px;
  color: rgba(255, 36, 71, 0.94);
}
</style>
