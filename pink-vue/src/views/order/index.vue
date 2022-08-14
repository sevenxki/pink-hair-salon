<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="24" :xs="24">
        <el-card class="box-card" shadow="hover" v-show="kindCard">
          <div slot="header" class="clearfix">
            <span>选择服务项目类别</span>
          </div>
          <el-row>
            <el-radio-group v-model="kindRadio" @change="chooseKind">
              <el-radio-button
                v-for="(item, index) in hsKindList"
                :key="index"
                :label="item.kindName"
                style="margin-right: 10px"
                :disabled="btnstatus1"
              >
                {{ item.kindName }}
              </el-radio-button>
            </el-radio-group>
          </el-row>
        </el-card>

        <el-card class="box-card" shadow="hover" v-show="projectCard">
          <div slot="header" class="clearfix">
            <span>选择具体服务项目</span>
          </div>
          <el-row>
            <el-radio-group v-model="projectRadio" @change="chooseProject">
              <el-radio-button
                v-for="(item, index) in hsProjectList1"
                :key="index"
                :label="item.projectName"
                style="margin-right: 10px"
                :disabled="btnstatus2"
              >
                {{ item.projectName }}
              </el-radio-button>
            </el-radio-group>
          </el-row>
        </el-card>

        <el-card class="box-card" shadow="hover" v-show="haircareCard">
          <div slot="header" class="clearfix">
            <span>选择护理级别</span>
          </div>
          <el-row>
            <el-radio-group v-model="haircareRadio" @change="chooseHaircare">
              <el-radio-button
                v-for="(item, index) in hsProjectList2"
                :key="index"
                :label="item.projectName"
                style="margin-right: 10px"
                :disabled="btnstatus3"
              >
                {{ item.projectName }}
              </el-radio-button>
            </el-radio-group>
          </el-row>
        </el-card>

        <el-card class="box-card" shadow="hover" v-show="hairdresserCard">
          <div slot="header" class="clearfix">
            <span>选择发型师</span>
          </div>
          <el-row>
            <el-row>
              <el-radio-group v-model="hdRadio" @change="chooseHD">
                <el-radio-button
                  v-for="(item, index) in hsHDList"
                  :key="index"
                  :label="item.hsStaff.staffName"
                  style="margin-right: 10px"
                  :disabled="btnstatus4"
                >
                  {{ item.hsStaff.staffName }}
                </el-radio-button>
              </el-radio-group>
            </el-row>
          </el-row>
        </el-card>

        <el-card class="box-card" shadow="hover" v-show="technicianCard">
          <div slot="header" class="clearfix">
            <span>选择技师</span>
          </div>
          <el-row>
            <el-row>
              <el-radio-group v-model="techRadio" @change="chooseTech">
                <el-radio-button
                  v-for="(item, index) in hsTechList"
                  :key="index"
                  :label="item.staffName"
                  style="margin-right: 10px"
                  :disabled="btnstatus5"
                >
                  {{ item.staffName }}
                </el-radio-button>
              </el-radio-group>
            </el-row>
          </el-row>
        </el-card>

        <!-- 数据提交操作按钮 -->
        <div align="center">
          <!--          <el-button type="primary" icon="el-icon-check" @click="submitForm">提 交</el-button>-->
          <el-button icon="el-icon-refresh" @click="resetQuery"
            >重 置</el-button
          >
        </div>
      </el-col>
    </el-row>

    <el-dialog
      :title="title"
      :visible.sync="open"
      width="500px"
      top="30px"
      append-to-body
    >
      <div align="center" style="margin: 15px">
        <span style="font-size: 30px">订单详情</span>
      </div>
      <div style="margin-top: 20px">
        <div
          style="
            margin: 60px;
            margin-left: 130px;
            border-width: 6px;
            border-color: #263445;
          "
        >
          <span style="font-size: 15px">服务项目：</span>
          <span style="font-size: 20px; margin-left: 13px; color: #263445">{{
            kindRadio
          }}</span>
        </div>
        <div style="margin: 60px; margin-left: 130px">
          <span style="font-size: 15px">产品级别：</span>
          <span style="font-size: 20px; margin-left: 15px; color: #263445">{{
            projectRadio
          }}</span>
        </div>
        <div style="margin: 60px; margin-left: 130px">
          <span style="font-size: 15px">发型师：</span>
          <span style="font-size: 20px; margin-left: 30px; color: #263445">{{
            hdRadio
          }}</span>
        </div>
        <div style="margin: 60px; margin-left: 130px">
          <span style="font-size: 15px">技师：</span>
          <span style="font-size: 20px; margin-left: 45px; color: #263445">{{
            techRadio
          }}</span>
        </div>
      </div>
      <div style="margin-right: 100px">
        <div align="right" style="margin-top: 45px">
          <span style="font-size: 15px">总价：￥</span>
          <span style="font-size: large"
            ><strong>{{ this.total }}</strong></span
          >
        </div>
      </div>
      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" icon="el-icon-check" @click="submit"
          >提 交</el-button
        >
        <el-button icon="el-icon-refresh" @click="resetQuery">重 置</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addHsOrder,
  queryAllKind,
  queryAllProjectNameByKind,
  queryAllHairCareName,
  queryAllHDNameByProject,
  queryTechnician,
  getPrice,
} from "@/api/order";

export default {
  name: "HsOrder",
  data() {
    return {
      open: false,
      // 弹出层标题
      title: "",
      // 数据编辑表单
      form: {},
      total: "",

      // 初始化查询订单总价参数
      kind: "",
      project: "",
      haircare: "",
      hd: "",
      tech: "",
      staffName: "",

      // 初始化v-model参数
      kindRadio: "",
      projectRadio: "",
      haircareRadio: "",
      hdRadio: "",
      techRadio: "",

      // 设置各个button显示状态
      btnstatus1: false,
      btnstatus2: false,
      btnstatus3: false,
      btnstatus4: false,
      btnstatus5: false,

      // 设置各个card的显示状态
      kindCard: true,
      projectCard: false,
      haircareCard: false,
      hairdresserCard: false,
      technicianCard: false,

      /** 查询订单总价参数*/
      orderList: [
        { value: this.kind },
        { value: this.project },
        { value: this.haircare },
        { value: this.hd },
        { value: this.tech },
      ],

      /** 提交订单参数*/
      submitOrder: [
        { value: this.project },
        { value: this.haircare },
        { value: this.hd },
        { value: this.tech },
        { value: this.total },
        { value: this.staffName},
      ],

      hsKindList: [],
      hsProjectList1: [],
      hsProjectList2: [],
      hsHDList: [],
      hsTechList: [],
    };
  },
  created() {
    queryAllKind().then((response) => {
      this.hsKindList = response;
    });
  },
  methods: {
    /** 点击类别按钮*/
    chooseKind: function () {
      this.btnstatus1 = true;
      this.loading = true;
      this.projectCard = true;
      this.$set(this.orderList, 0, this.kindRadio);
      queryAllProjectNameByKind(this.kindRadio).then((response) => {
        //找类别下对应的项目
        this.hsProjectList1 = response;
        this.loading = false;
      });
    },

    /** 点击项目按钮*/
    chooseProject: function () {
      this.btnstatus2 = true;
      this.loading = true;
      this.haircareCard = true;
      this.staffName = this.$store.state.user.staffName;
      console.log("this.staffName"+this.staffName)
      this.$set(this.orderList, 1, this.projectRadio);
      this.$set(this.submitOrder, 0, this.projectRadio);
      this.$set(this.submitOrder,5, this.staffName);
      queryAllHairCareName().then((response) => {
        //找护理级别
        this.hsProjectList2 = response;
        this.loading = false;
      });
    },

    /** 点击护理级别按钮*/
    chooseHaircare: function () {
      this.btnstatus3 = true;
      this.loading = true;
      this.hairdresserCard = true;
      this.$set(this.orderList, 2, this.haircareRadio);
      this.$set(this.submitOrder, 1, this.haircareRadio);
      queryAllHDNameByProject(this.projectRadio).then((response) => {
        //选发型师
        this.hsHDList = response;
        this.loading = false;
      });
    },

    /** 点击发型师按钮*/
    chooseHD: function () {
      this.btnstatus4 = true;
      this.loading = true;
      this.technicianCard = true;
      this.$set(this.orderList, 3, this.hdRadio);
      this.$set(this.submitOrder, 2, this.hdRadio);
      queryTechnician().then((response) => {
        //选技师
        this.hsTechList = response;
        this.loading = false;
      });
    },

    /** 点击技师按钮*/
    chooseTech: function () {
      this.loading = true;
      this.open = true;
      this.title = "";
      this.$set(this.orderList, 4, this.techRadio);
      this.$set(this.submitOrder, 3, this.techRadio);
      // console.log("orderList" + this.orderList);
      getPrice(this.orderList).then((response) => {
        //获取总价
        this.total = response;
        this.loading = false;
        this.$set(this.submitOrder, 4, this.total);
      });
    },

    /** 数据提交按钮 */
    submit: function () {
      addHsOrder(this.submitOrder).then((response) => {
        this.open = false;
        this.title = "";
        // this.getList()
        this.$message.success(response);
        window.location.reload(); //刷新页面
      });
    },

    /** 数据筛选重置按钮 */
    resetQuery() {
      window.location.reload(); //刷新页面
    },
  },
};
</script>

<style scoped>
.el-card {
  margin-bottom: 20px;
}
</style>
