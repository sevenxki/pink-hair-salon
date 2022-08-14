<template>
  <el-card style="width: 100%">
    <div>
      <el-row>
        <el-col :span="13">
          <!-- 店铺概况 -->
          <el-card>
            <div align="center">
              <div class="dataIntro" style="background-color: #9ac4ff">
                <span style="color: #ffffff; font-size: 13px"
                  >本月总计营业额</span
                >
                <div style="color: #ffffff; padding-top: 10px">
                  ￥ {{ this.dataIntroList.total }}
                </div>
              </div>
              <div class="dataIntro" style="background-color: #ff96ab">
                <span style="color: #ffffff; font-size: 13px"
                  >本月散客营业额</span
                >
                <div style="color: #ffffff; padding-top: 10px">
                  ￥ {{ this.dataIntroList.ordinaryTotal }}
                </div>
              </div>
              <div class="dataIntro" style="background-color: #ab7ae8">
                <span style="color: #ffffff; font-size: 13px"
                  >本月会员营业额</span
                >
                <div style="color: #ffffff; padding-top: 10px">
                  ￥ {{ this.dataIntroList.memberTotal }}
                </div>
              </div>
              <div class="dataIntro" style="background-color: #6edc6a">
                <span style="color: #ffffff; font-size: 13px"
                  >本月完成订单数</span
                >
                <div style="color: #ffffff; padding-top: 10px">
                  {{ this.dataIntroList.orderQuantity }} 笔
                </div>
              </div>
              <div class="dataIntro" style="background-color: #f8aa5f">
                <span style="color: #ffffff; font-size: 13px">现有会员</span>
                <div style="color: #ffffff; padding-top: 10px">
                  {{ this.dataIntroList.newMember }} 个
                </div>
              </div>
            </div>
          </el-card>
          <!-- 门店业绩 -->
          <el-card style="margin-top: 17px">
            <div align="center">
              <div
                class="pie-class"
                ref="orderMonth"
                v-show="currentMonth"
                style="
                  width: 600px;
                  height: 330px;
                  margin: 10px;
                  display: inline-block;
                "
              ></div>
              <div
                class="pie-class"
                ref="orderWeek"
                v-show="currentWeek"
                style="
                  width: 600px;
                  height: 330px;
                  margin: 10px;
                  display: inline-block;
                "
              ></div>
              <div
                class="pie-class"
                ref="orderHalfYear"
                v-show="currentHalfYear"
                style="
                  width: 600px;
                  height: 330px;
                  margin: 10px;
                  display: inline-block;
                "
              ></div>
              <div
                class="pie-class"
                ref="orderTotal"
                v-show="currentTotal"
                style="
                  width: 600px;
                  height: 330px;
                  margin: 10px;
                  display: inline-block;
                "
              ></div>
            </div>
            <div align="center">
              <el-row>
                <el-radio-group v-model="lineDate" @change="chooseLineDate">
                  <el-radio-button
                    v-for="(item, index) in chooseDateList"
                    :key="index"
                    :label="item.value"
                    style="margin-right: 10px"
                    :disabled="btnstatus1"
                  >
                    {{ item.value }}
                  </el-radio-button>
                </el-radio-group>
              </el-row>
            </div>
          </el-card>
        </el-col>
        <el-col :span="11">
          <!-- 会员等级 -->
          <el-card style="margin-left: 23px">
            <div
              class="pie-class"
              ref="member"
              style="
                width: 600px;
                height: 200px;
                margin: 10px;
                display: inline-block;
              "
            ></div>
          </el-card>
          <!-- 员工业绩排行 -->
          <el-card style="margin-left: 23px; margin-top: 10px">
            <div
              class="pie-class"
              ref="staff"
              style="
                width: 500px;
                height: 240px;
                margin: 10px;
                display: inline-block;
              "
            ></div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import {
  getIntroData,
  getMemberPieData,
  getTotalTimeLineData,
  getTotalTotalLineData,
  getWeekTimeLineData,
  getWeekTotalLineData,
  getMonthTimeLineData,
  getMonthTotalLineData,
  getHalfYearTimeLineData,
  getHalfYearTotalLineData,
  getStaffNameAchvData,
  getStaffTotalAchvData,
} from "@/api/dashboard";
export default {
  name: "Dashboard",
  data() {
    return {
      btnstatus1: false,

      // 显示门店业绩
      currentWeek: false,
      currentMonth: false,
      currentHalfYear: false,
      currentTotal: true,

      // 初始化门店业绩时间选择
      lineDate: "近三十天",

      chooseDateList: [
        { value: "总 计" },
        { value: "近半年" },
        { value: "近三十天" },
        { value: "近七天" },
      ],

      // 门店业绩概况五个数据
      dataIntroList: {
        total: "",
        ordinaryTotal: "",
        memberTotal: "",
        orderQuantity: "",
        newMember: "",
      },

      weekTimeLineData: "",
      weekTotalLineData: "",
      monthTimeLineData: "",
      monthTotalLineData: "",
      halfYearTimeLineData: "",
      halfYearTotalLineData: "",
      totalTimeLineData: "",
      totalTotalLineData: "",
      staffNameData: "",
      staffTotalData: "",
    };
  },
  created() {},
  mounted() {
    // 门店业绩
    getIntroData().then((response) => {
      this.dataIntroList = response;
    });

    // 本周业绩折线图
    this.getWeek();
    // 本月业绩折线图
    this.getMonth();
    // 近半年业绩折线图
    this.getHalfYear();
    // 总计业绩折线图
    this.getTotal();
    // 员工等级饼图
    this.getStaff();

    // 会员等级饼图
    getMemberPieData().then((response) => {
      const memberData = response;
      this.initEchartsMember(memberData);
    });
  },
  methods: {
    getWeek() {
      getWeekTimeLineData().then((responseTime) => {
        this.weekTimeLineData = responseTime;
      });
      getWeekTotalLineData().then((responseTotal) => {
        this.weekTotalLineData = responseTotal;
        this.initEchartsPfWeek(this.weekTimeLineData, this.weekTotalLineData);
      });
    },
    getMonth() {
      getMonthTimeLineData().then((responseTime) => {
        this.monthTimeLineData = responseTime;
      });
      getMonthTotalLineData().then((responseTotal) => {
        this.monthTotalLineData = responseTotal;
        this.initEchartsPfMonth(
          this.monthTimeLineData,
          this.monthTotalLineData
        );
      });
    },
    getHalfYear() {
      getHalfYearTimeLineData().then((responseTime) => {
        this.halfYearTimeLineData = responseTime;
      });
      getHalfYearTotalLineData().then((responseTotal) => {
        this.halfYearTotalLineData = responseTotal;
        this.initEchartsPfHalfYear(
          this.halfYearTimeLineData,
          this.halfYearTotalLineData
        );
      });
    },
    getTotal() {
      getTotalTimeLineData().then((responseTime) => {
        this.totalTimeLineData = responseTime;
      });
      getTotalTotalLineData().then((responseTotal) => {
        this.totalTotalLineData = responseTotal;
        this.initEchartsPfTotal(
          this.totalTimeLineData,
          this.totalTotalLineData
        );
      });
    },

    getStaff() {
      getStaffNameAchvData().then((responseName) => {
        this.staffNameData = responseName;
      });
      getStaffTotalAchvData().then((responseTotal) => {
        this.staffTotalData = responseTotal;
        this.initEchartsStaff(this.staffNameData, this.staffTotalData);
      });
    },
    // initEchartsMember() {
    initEchartsMember(memberData) {
      var myChart = this.$echarts.init(this.$refs.member);
      var option = {
        title: {
          text: "会员等级",
          subtext: "按累积消费金额",
          left: "left",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
          top: "bottom",
        },
        series: [
          {
            name: "",
            type: "pie",
            radius: "50%",
            // data: [
            //   { value: 10, name: '白银会员Lv1' },
            //   { value: 40, name: '黄金会员Lv2' },
            //   { value: 25, name: '铂金会员Lv3' },
            //   { value: 10, name: '钻石会员Lv4' },
            //   { value: 15, name: '至尊会员Lv5' },
            //   { value: 15, name: '黑金会员Lv6' },
            //
            // ],
            data: memberData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
      option && myChart.setOption(option);
    },

    // initEchartsPfWeek() {
    initEchartsPfWeek(weekTimeLineData, weekTotalLineData) {
      var myChart = this.$echarts.init(this.$refs.orderWeek);
      var option = {
        title: {
          text: "门店业绩",
          subtext: "总营业额",
          left: "left",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#9dbcfa",
            },
          },
        },
        xAxis: {
          type: "category",
          // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          data: weekTimeLineData,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            // smooth: true,
            data: weekTotalLineData,
            // data: [200, 200, 200, 200, 200, 200, 200],
            type: "line",
          },
        ],
        legend: {
          orient: "vertical",
          left: "left",
          top: "bottom",
        },
      };
      option && myChart.setOption(option);
    },

    // initEchartsPfMonth() {
    initEchartsPfMonth(monthTimeLineData, monthTotalLineData) {
      var myChart = this.$echarts.init(this.$refs.orderMonth);
      var option = {
        title: {
          text: "门店业绩",
          subtext: "总营业额",
          left: "left",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#9dbcfa",
            },
          },
        },
        xAxis: {
          type: "category",
          // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          data: monthTimeLineData,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: monthTotalLineData,
            // data: [100, 100, 100, 100, 100, 100, 100],
            type: "line",
          },
        ],
        legend: {
          orient: "vertical",
          left: "left",
          top: "bottom",
        },
      };
      option && myChart.setOption(option);
    },

    // initEchartsPfHalfYear() {
    initEchartsPfHalfYear(halfYearTimeLineData, halfYearTotalLineData) {
      var myChart = this.$echarts.init(this.$refs.orderHalfYear);
      var option = {
        title: {
          text: "门店业绩",
          subtext: "总营业额",
          left: "left",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#9dbcfa",
            },
          },
        },
        xAxis: {
          type: "category",
          // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          data: halfYearTimeLineData,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: halfYearTotalLineData,
            // data: [300, 300, 300, 300, 300, 300, 300],
            type: "line",
          },
        ],
        legend: {
          orient: "vertical",
          left: "left",
          top: "bottom",
        },
      };
      option && myChart.setOption(option);
    },

    // initEchartsPfTotal() {
    initEchartsPfTotal(totalTimeLineData, totalTotalLineData) {
      var myChart = this.$echarts.init(this.$refs.orderTotal);
      var option = {
        title: {
          text: "门店业绩",
          subtext: "总营业额",
          left: "left",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#9dbcfa",
            },
          },
        },
        xAxis: {
          type: "category",
          // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          data: totalTimeLineData,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: totalTotalLineData,
            // data: [1250.0,800.0,2150.0,3850.0,22556.0],
            type: "line",
          },
        ],
        legend: {
          orient: "vertical",
          left: "left",
          top: "bottom",
        },
      };
      option && myChart.setOption(option);
    },

    // initEchartsStaff() {
    initEchartsStaff(staffNameData, staffTotalData) {
      var myChart = this.$echarts.init(this.$refs.staff);
      var option;
      option = {
        title: {
          text: "员工业绩排行榜",
          subtext: "按总计营业额排行前五名",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        legend: {},
        grid: {
          left: "0%",
          // right: '4%',
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "value",
          boundaryGap: [0, 0.01],
        },
        yAxis: {
          type: "category",
          // data: ['Tony', 'Jacky', 'Terry', 'Mike', 'John']
          data: staffNameData,
        },
        series: [
          {
            name: "本月总计营业额",
            type: "bar",
            // data: [5000, 3489, 9034, 4970, 1744],
            data: staffTotalData,
            itemStyle: {
              color: "#8884d7",
            },
          },
        ],
      };
      option && myChart.setOption(option);
    },

    chooseLineDate() {
      // console.log("chooseLineDate:" + this.lineDate);
      if (this.lineDate == "总 计") {
        this.currentWeek = false;
        this.currentMonth = false;
        this.currentHalfYear = false;
        this.currentTotal = true;
      } else if (this.lineDate == "近半年") {
        this.currentWeek = false;
        this.currentMonth = false;
        this.currentHalfYear = true;
        this.currentTotal = false;
      } else if (this.lineDate == "近三十天") {
        this.currentWeek = false;
        this.currentMonth = true;
        this.currentHalfYear = false;
        this.currentTotal = false;
      } else {
        this.currentWeek = true;
        this.currentMonth = false;
        this.currentHalfYear = false;
        this.currentTotal = false;
      }
    },
  },
};
</script>

<style>
.dataIntro {
  width: 18%;
  height: 70px;
  border-radius: 4px;
  border-width: thick;
  padding: 10px;
  display: inline-block;
  margin: 5px;
}
</style>
