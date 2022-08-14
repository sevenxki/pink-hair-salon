import request from "@/utils/request";

// 获得门店业绩概况五个数据
export function getIntroData() {
  return request({
    url: '/hsDashboard/thisMonth',
    method: 'get'
  })
}

// 获得近半年门店业绩数据
export function getHalfYearLineData() {
  return request({
    url: '/hsDashboard/halfYear',
    method: 'get'
  })
}
// 获得近半年门店业绩数据-time
export function getHalfYearTimeLineData() {
  return request({
    url: '/hsDashboard/halfYearTime',
    method: 'get'
  })
}
// 获得近半年门店业绩数据-total
export function getHalfYearTotalLineData() {
  return request({
    url: '/hsDashboard/halfYearTotal',
    method: 'get'
  })
}

// 获得近7天门店业绩数据
export function getWeekLineData() {
  return request({
    url: '/hsDashboard/nearSeven',
    method: 'get'
  })
}
// 获得近7天门店业绩数据-time
export function getWeekTimeLineData() {
  return request({
    url: '/hsDashboard/nearSevenTime',
    method: 'get'
  })
}
// 获得近7天门店业绩数据-total
export function getWeekTotalLineData() {
  return request({
    url: '/hsDashboard/nearSevenTotal',
    method: 'get'
  })
}

// 获得近30天门店业绩数据
export function getMonthLineData() {
  return request({
    url: '/hsDashboard/nearMonth',
    method: 'get'
  })
}

// 获得近30天门店业绩数据-time
export function getMonthTimeLineData() {
  return request({
    url: '/hsDashboard/nearMonthTime',
    method: 'get'
  })
}

// 获得近30天门店业绩数据-total
export function getMonthTotalLineData() {
  return request({
    url: '/hsDashboard/nearMonthTotal',
    method: 'get'
  })
}


// 获得总计门店业绩数据
export function getTotalLineData() {
  return request({
    url: '/hsDashboard/allTotal',
    method: 'get'
  })
}

// 获得总计门店业绩数据-time
export function getTotalTimeLineData() {
  return request({
    url: '/hsDashboard/allMonthTotalTime',
    method: 'get'
  })
}

// 获得总计门店业绩数据-total
export function getTotalTotalLineData() {
  return request({
    url: '/hsDashboard/allMonthTotalTotal',
    method: 'get'
  })
}

// 获得会员等级饼图
export function getMemberPieData() {
  return request({
    url: '/hsDashboard/vipNumber',
    method: 'get'
  })
}

// 获得员工今日业绩排行榜
export function getStaffAchvData() {
  return request({
    url: '/hsDashboard/performance',
    method: 'get'
  })
}

// 获得员工今日业绩排行榜-name
export function getStaffNameAchvData() {
  return request({
    url: '/hsDashboard/performanceName',
    method: 'get'
  })
}

// 获得员工今日业绩排行榜-total
export function getStaffTotalAchvData() {
  return request({
    url: '/hsDashboard/performanceTotal',
    method: 'get'
  })
}


