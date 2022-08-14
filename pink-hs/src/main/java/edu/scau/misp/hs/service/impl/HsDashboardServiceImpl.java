package edu.scau.misp.hs.service.impl;

import com.alibaba.fastjson.annotation.JSONField;
import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.HsAccumulationService;
import edu.scau.misp.hs.service.HsDashboardService;
import edu.scau.misp.hs.service.HsPaymentOrderService;
import edu.scau.misp.hs.util.DateUtils;
import edu.scau.misp.hs.vo.DashboardVo;
import edu.scau.misp.hs.vo.model.HsOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("hsDashboardService")
public class HsDashboardServiceImpl implements HsDashboardService {
    @Autowired
    private HsPaymentOrderMapper hsPaymentOrderMapper;
    @Autowired
    private HsGrouporderMapper hsGrouporderMapper;
    @Autowired
    private HsVipMapper hsVipMapper;
    @Autowired
    private HsAccumulationMapper hsAccumulationMapper;
    @Autowired
    private HsGrouporderDiscountMapper hsGrouporderDiscountMapper;
    @Autowired
    private HsOrderMapper hsOrderMapper;
    @Autowired
    private HsProjectMapper hsProjectMapper;
    @Autowired
    private HsStaffMapper hsStaffMapper;

    /**
     * 计算总会员页面消费
     *
     * @param
     * @return 实例对象
     */
    @Override
    public Double CumulativeTotal(HsAccumulation hsAccumulation){
        List<HsAccumulation> hsAccumulationList= this.hsAccumulationMapper.queryAll(hsAccumulation);
        Double total = 0.00;
        for(HsAccumulation hsAccumulation1:hsAccumulationList){
            total=total+hsAccumulation1.getAccumulationTotal();
        }
        return total;
    }
    /**
     * 计算普通消费总额
     *
     * @param
     * @return 实例对象
     */
    @Override
    public Double CumulativeTotalOrder(HsPaymentOrder hsPaymentOrder){
        List<HsPaymentOrder> hsPaymentOrderList= this.hsPaymentOrderMapper.all();
        Double total = 0.00;
        for(HsPaymentOrder hsPaymentOrder1:hsPaymentOrderList){
            total=total+hsPaymentOrder1.getTotalPayment();
        }
        return total;
    }

    /**
     * 会员卡余额充值总值、购买理发卡总值、购买护理卡总值
     *
     * @param
     * @return 实例对象
     */
    @Override
    public Double[] queryAllAmount(){
        List<HsGrouporderDiscount> hsGrouporderDiscountList1= this.hsGrouporderDiscountMapper.queryVipList();
        Double total1 = 0.00;
        for(HsGrouporderDiscount HsGrouporderDiscount1:hsGrouporderDiscountList1){
            total1=total1+HsGrouporderDiscount1.getAmount();
        }

        List<HsGrouporderDiscount> hsGrouporderDiscountList2= this.hsGrouporderDiscountMapper.queryHairCutList();
        Double total2 = 0.00;
        for(HsGrouporderDiscount HsGrouporderDiscount2:hsGrouporderDiscountList2){
            total2=total2+HsGrouporderDiscount2.getAmount();
        }

        List<HsGrouporderDiscount> hsGrouporderDiscountList3= this.hsGrouporderDiscountMapper.queryHairCareList();
        Double total3 = 0.00;
        for(HsGrouporderDiscount HsGrouporderDiscount3:hsGrouporderDiscountList3){
            total3=total3+HsGrouporderDiscount3.getAmount();
        }
        Double[] total={total1,total2,total3};
        return total;
    }

    /**
     * 按每个月计算普通订单的总金额
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> countTotalByMonth(){
        List<DashboardVo> dashboardVoList = new ArrayList<>();

        HsPaymentOrder hsPaymentOrder = new HsPaymentOrder();
        List<HsPaymentOrder> hsPaymentOrderList = this.hsPaymentOrderMapper.all();

        for(HsPaymentOrder hsPaymentOrder1:hsPaymentOrderList){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(hsPaymentOrder1.getTime());
            boolean i = false;
            for(DashboardVo DashboardVo:dashboardVoList){
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(DashboardVo.getTime());
                if(calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)  ){
                    i = true;
                    DashboardVo.setTotal(hsPaymentOrder1.getTotalPayment()+DashboardVo.getTotal());
                }
            }
            if (i==false){
                DashboardVo dashboardVo1 = new DashboardVo();
                dashboardVo1.setTime(hsPaymentOrder1.getTime());
                dashboardVo1.setTotal(hsPaymentOrder1.getTotalPayment());
                dashboardVoList.add(dashboardVo1);
            }
        }
        return dashboardVoList;
    }

    @JSONField(format="yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    Date time1;
    @JSONField(format="yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    Date time2;

    /**
     * 按每个月计算优惠团购的总金额
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> countAmountByMonth(){
        //遍历订单表
        //把订单表的每一个年与月份取出来
        //看新创建的dashboard的list有没有这个月份
        //遍历dashboard的list
        //如果有就给金额加上
        //if 其中一个dashboard实体的get月份 == 订单月份
        // 那这一个dashboard实体的set（金额+get金额)
        //如果没有就新增一个月份

        List<DashboardVo> dashboardVoList = new ArrayList<>();

        HsGrouporder hsGrouporder = new HsGrouporder();
        List<HsGrouporder> hsGrouporderList = this.hsGrouporderMapper.queryAll(hsGrouporder);

        for(HsGrouporder hsGrouporder1:hsGrouporderList){
            time1 = hsGrouporder1.getGrouporderDatetime();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(hsGrouporder1.getGrouporderDatetime());                   //放入Date类型数据
            //如果有相同的为fal那布尔值改为true用以标识，如果布尔值最后se，那说明要新增
            boolean i = false;
            for(DashboardVo DashboardVo:dashboardVoList){
                time2=DashboardVo.getTime();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(DashboardVo.getTime());                   //放入Date类型数据
                if(calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)  ){
                    i = true;
                    DashboardVo.setTotal(hsGrouporder1.getGrouporderTotal()+DashboardVo.getTotal());
                }
            }
            if (i==false){
                DashboardVo dashboardVo1 = new DashboardVo();
                dashboardVo1.setTime(hsGrouporder1.getGrouporderDatetime());
                dashboardVo1.setTotal(hsGrouporder1.getGrouporderTotal());
                dashboardVoList.add(dashboardVo1);
            }
        }

//        List dateList = new ArrayList<>();//存时间
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");//时间格式
//        Calendar calendar = Calendar.getInstance();
//        for (int i = 1; i < 10; i++) {
//            dateList.add(simpleDateFormat.format(calendar.getTime()));
//            calendar.add(Calendar.MONTH, -1);
//        }
//        System.out.println(dateList.toString());
//
//        HsGrouporder hsGrouporder = new HsGrouporder();
//        List<HsGrouporder> hsGrouporderList = this.hsGrouporderMapper.queryAll(hsGrouporder);
//        for(HsGrouporder hsGrouporder1:hsGrouporderList){
//            Calendar calendar1 = Calendar.getInstance();
//            calendar1.setTime(hsGrouporder1.getGrouporderDatetime());
//        }

//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(DashboardVo.getTime());
//        System.out.println(calendar1.MONTH);
//        System.out.println(calendar2.MONTH);
//        System.out.println(calendar1.YEAR);
//        System.out.println(calendar2.YEAR);
//        if(calendar1.MONTH == calendar2.MONTH && calendar1.YEAR == calendar2.YEAR )

        return dashboardVoList;
    }


    /**
     * 本月的总金额、散客营业额、会员营业额、完成订单数、现有会员
     *
     * @param
     * @return
     */
    @Override
    public DashboardVo thisMonth(){
        HsPaymentOrder hsPaymentOrder = new HsPaymentOrder();
        List<HsPaymentOrder> hsPaymentOrderList = this.hsPaymentOrderMapper.all();
        HsGrouporder hsGrouporder = new HsGrouporder();
        List<HsGrouporder> hsGrouporderList = this.hsGrouporderMapper.queryAll(hsGrouporder);
        DashboardVo dashboardVo = new DashboardVo();
        Double a = 0.0;
        Double b = 0.0;
        Double c = 0.0;
        int d = 0;
        int e = 0;
        //取现在的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.getNowDate());
        for(HsPaymentOrder hsPaymentOrder1:hsPaymentOrderList){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(hsPaymentOrder1.getTime());
            if(calendar1.get(Calendar.MONTH) ==calendar.get(Calendar.MONTH)){
                a = a + hsPaymentOrder1.getTotalPayment();
                d=d+1;
            }
        }
        dashboardVo.setOrdinaryTotal(a);
        for(HsGrouporder hsGrouporder1:hsGrouporderList){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(hsGrouporder1.getGrouporderDatetime());
            if(calendar1.get(Calendar.MONTH) ==calendar.get(Calendar.MONTH)){
                b = b + hsGrouporder1.getGrouporderTotal();
                d=d+1;
            }
        }
        dashboardVo.setMemberTotal(b);
        c = a + b ;
        dashboardVo.setTotal(c);
        dashboardVo.setOrderQuantity(d);
        HsVip hsVip = new HsVip();
        List<HsVip> hsVipList = this.hsVipMapper.queryAll(hsVip);
        for(HsVip hsVip1:hsVipList){
            e = e + 1;
        }
        dashboardVo.setNewMember(e);
        return dashboardVo;
    }



    /**
     * 按月的总的门店业绩
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> allTotal(){
        DashboardVo dashboardVo = new DashboardVo();
        List<DashboardVo> dashboardVoList = countAmountByMonth();//会员
        List<DashboardVo> dashboardVoList1 = countTotalByMonth();//普通

        //用新的list存会员
        //普通列表每一个和新的match，如果存在就加和，如果不存在就新建
        List<DashboardVo> allTotal = dashboardVoList;

        for(DashboardVo dashboardVo1:dashboardVoList1){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dashboardVo1.getTime());
            boolean i = false;
            for(DashboardVo dashboardVo2:allTotal){
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(dashboardVo2.getTime());
                if(calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)  ){
                    i = true;
                    dashboardVo2.setTotal(dashboardVo2.getTotal()+dashboardVo1.getTotal());
                }
            }
            if (i==false){
                DashboardVo dashboardVo3 = new DashboardVo();
                dashboardVo3.setTime(dashboardVo1.getTime());
                dashboardVo3.setTotal(dashboardVo1.getTotal());
                allTotal.add(dashboardVo3);
            }
        }
        return allTotal;
    }



//    /**
//     * 按月的总的门店业绩 现有月的扩充
//     *
//     * @param
//     * @return
//     */
//    @Override
//    public List<DashboardVo> allMonthTotal(){
//        List<DashboardVo> dashboardVoList = new ArrayList<>();
//
//        List<DashboardVo> dashboardVoLists = allTotal();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(DateUtils.getNowDate());
//
//        List<Date> dateList = new ArrayList<>();
//        for(DashboardVo dashboardVo:dashboardVoList){
//            dateList.add(dashboardVo.getTime());
//        }
//
//        List<Double> time = new ArrayList<>();
//        for(Date date:dateList){
//            long times=date.getTime();
//            time.add(Double.valueOf(times));
//            System.out.println("12"+Double.valueOf(times));
//        }
//        System.out.println("最小值: " + Collections.min(time));
//        Double min = Collections.min(time);
//
//
////        long times = date.getTime(); 时间戳
//
//
////        Map<String,List<Integer>> map = new HashMap<>();
////        Set<Map.Entry<String,List<Integer>>> entries = map.entrySet();
////        for(Map.Entry<String,List<Integer>> entry:entries){
////            String key = entry.getKey();
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        String time1 = simpleDateFormat.format(min);
//        String time2 = simpleDateFormat.format(DateUtils.getNowDate());
//        List<Date> list = getMonthBetweenDate(time1,time2);
//
//        List<DashboardVo> dashboardVoList1 = new ArrayList<>();
//        for(Date date1:list){
//            DashboardVo dashboard = new DashboardVo();
//            dashboard.setTime(date1);
//            dashboardVoList1.add(dashboard);
//        }
//
//        for(DashboardVo dashboardVo1:dashboardVoLists){
//            Calendar calendar1 = Calendar.getInstance();
//            calendar1.setTime(dashboardVo1.getTime());
//            for(DashboardVo dashboardVo2:dashboardVoList1){
//                Calendar calendar2 = Calendar.getInstance();
//                calendar2.setTime(dashboardVo2.getTime());
//                if(calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)  ){
//                    dashboardVo2.setTotal(dashboardVo1.getTotal());
//                }
//            }
//        }
//
//        return dashboardVoList1;
//    }

    /**
          * 按月的总的门店业绩 现有月的扩充
          *
          * @param
          * @return
          */
    @Override
    public List<DashboardVo> allMonthTotal(){
        List<DashboardVo> dashboardVoList = new ArrayList<>();
        List<DashboardVo> dashboardVoList1 = allTotal();
        Date dateMin = DateUtils.getNowDate();
        for(DashboardVo dashboardVo:dashboardVoList1){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dashboardVo.getTime());
            int a = calendar.get(Calendar.MONTH);
            int a1 = calendar.get(Calendar.YEAR);
//            System.out.println("a a1="+a+"&"+a1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dateMin);
            int b = calendar1.get(Calendar.MONTH);
            int b1 = calendar1.get(Calendar.YEAR);
            if(a1 < b1){
                dateMin = dashboardVo.getTime();
            }
            if(a1==b1){
                if(a<b){
                    dateMin = dashboardVo.getTime();
                }
            }
//            System.out.println(dateMin);
        }
        List<Calendar> Date = getMonthBetweenDate(dateMin,DateUtils.getNowDate());



        List<Date> Date1 = new ArrayList<>();
        for(Calendar date:Date){
//            System.out.println(date);
            Date date2 = date.getTime();
//            System.out.println(date2);
            Date1.add(date2);
        }
        for(Date date:Date1){
//            System.out.println(date);
        }

        List<DashboardVo> dashboardVoList2 = new ArrayList<>();
        for(Date date1:Date1){
            DashboardVo dashboardVo = new DashboardVo();
            dashboardVo.setTime(date1);
            dashboardVo.setTotal(0.0);
            dashboardVoList2.add(dashboardVo);
        }

        for(DashboardVo dashboardVo1:dashboardVoList1) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dashboardVo1.getTime());
            int a = calendar.get(Calendar.MONTH);
            int a1 = calendar.get(Calendar.YEAR);
            for(DashboardVo dashboardVo2:dashboardVoList2) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(dashboardVo2.getTime());
                int b = calendar1.get(Calendar.MONTH);
                int b1 = calendar1.get(Calendar.YEAR);
                if(a == b && a1 == b1){
                    dashboardVo2.setTotal(dashboardVo1.getTotal()+dashboardVo2.getTotal());
                }
            }
        }


        return dashboardVoList2;
    }


    /**
     * 按月的总的门店业绩的时间条
     *
     * @param
     * @return
     */
    @Override
    public String[] allMonthTotalTime(){
        List<DashboardVo> dashboardVoList = allMonthTotal();
        int m=0;
        int n=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        String[] a = new String[m];
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n<m) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                a[n] = sdf.format(dashboardVo.getTime());
                n++;
            }
        }
        return a;
    }

    /**
     * 按月的总的门店业绩的总额条
     *
     * @param
     * @return
     */
    @Override
    public Double[] allMonthTotalTotal(){
        List<DashboardVo> dashboardVoList = allMonthTotal();
        int m=0;
        int n=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        Double[] a = new Double[m];
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n<m) {
                a[n]=dashboardVo.getTotal();
                n++;
            }
        }
        return a;
    }

    /**
     * 获取两个日期之间的所有月份 (年月)
     *
     * @param startTime
     * @param endTime
     * @return：YYYY-MM
     */
    public static List<Date> getMonthBetweenDate(String startTime, String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        // 声明保存日期集合
        List<Date> list = new ArrayList<Date>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime()<=endDate.getTime()){
                // 把日期添加到集合
                list.add(DateUtils.parseDate(sdf.format(startDate)));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.MONTH, 1);
                // 获取增加后的日期
                startDate=calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取两个时间的所有月份
     * @param start
     * @param end
     * @return
     */
    public static  List<Calendar> getMonthBetweenDate(Date start,Date end){
        if(start.getTime()>end.getTime()){
            return null;
        }
        Calendar min=Calendar.getInstance();
        Calendar max=Calendar.getInstance();
        min.setTime(start);
        min.set(min.get(Calendar.YEAR),min.get(Calendar.MONTH),1);
        max.setTime(end);
        max.set(max.get(Calendar.YEAR),max.get(Calendar.MONTH),2);
        Calendar curr=min;
        List<Calendar> dateList2=new ArrayList<>();
        while (curr.before(max)) {
            Calendar calendar=Calendar.getInstance();
            calendar.set(curr.get(Calendar.YEAR),curr.get(Calendar.MONTH),1,0,0,0);
            dateList2.add(calendar);
            curr.add(Calendar.MONTH,1);
        }
        return dateList2;
    }



    /**
     * 近半年总的门店业绩
     *
     * @param
     * @return
     */
    @Override
    public  List<DashboardVo> halfYear() {
        //按月份找到所有的金额：
        //用现在的时间找到month
        //month和6比较 大于6就可以 减6循环开始match
        //month小于6 减到最后用12减
        //用另一个list新的能够放进去的年份

        List<DashboardVo> dashboardVoList = new ArrayList<>();

        List<DashboardVo> dashboardVoList1 = allMonthTotal();//全部月的list
        //现在的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.getNowDate());
        int a = calendar.get(Calendar.MONTH);
        int a1 = calendar.get(Calendar.YEAR);
//        System.out.println("a="+a);
//        System.out.println("a1="+a1);
        //看是不是大于等于六月

        // 如果是大于等于六月
        // 那就遍历全部月的list
        // 一个个看是不是在半年内的一个月
        // 如果是 就加入新的list
        // 如果不是 就到下一个循环
        if(a >= 5){
            for(DashboardVo dashboardVo1:dashboardVoList1){
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(dashboardVo1.getTime());
                int b = calendar1.get(Calendar.MONTH);
                int b1 = calendar1.get(Calendar.YEAR);
                int i ;
                if(a1 == b1){
                    for( i = 0 ; i < 5 ; i++) {
                        if (b == a-i) {
                            dashboardVoList.add(dashboardVo1);
                        }
                    }
                }
            }
        }
        // 如果没有大于六月 那就要看它的年份是不是本年且月份是不是小于当前月
        // 如果是前一年的 用6来减当前月 那么要比较的月数是12-（6-当前月）+1
        if(a < 5){
            for(DashboardVo dashboardVo1:dashboardVoList1){
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(dashboardVo1.getTime());
                int b = calendar1.get(Calendar.MONTH);
                int b1 = calendar1.get(Calendar.YEAR);
                if(a1 == b1){
                    int j;
                    for(j=0 ;j<a+1;j++){
//                        System.out.println("j="+j);
                        if(b == a-j){
//                            System.out.println("b="+b);
                            dashboardVoList.add(dashboardVo1);
                        }else continue;;
                    }
                }else if(b1 == a1-1){
                    // 如果是前一年的 用6来减当前月 那么要比较的月数是12-（6-当前月）+1
                    // 如果是5 要比较12月 如果是4 要比较12与11月 ...... 如果是1 要比较 12、11、10、9、8
                    int k;
                    for(k=0;k<=6-a;k++){
                        if(b == 13-k){
                            dashboardVoList.add(dashboardVo1);
                        }
                    }
                }
            }
        }
        return dashboardVoList;
    }

    /**
     * 按月的半年门店业绩的时间条
     *
     * @param
     * @return
     */
    @Override
    public String[] halfYearTime(){
        List<DashboardVo> dashboardVoList = halfYear();
        int m=0;
        int n=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        String[] a = new String[m];
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n<m) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                a[n] = sdf.format(dashboardVo.getTime());
                n++;
            }
        }
        return a;
    }

    /**
     * 按月的半年门店业绩的总额条
     *
     * @param
     * @return
     */
    @Override
    public Double[] halfYearTotal(){
        List<DashboardVo> dashboardVoList = halfYear();
        int m=0;
        int n=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        Double[] a = new Double[m];
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n<m) {
                a[n]=dashboardVo.getTotal();
                n++;
            }
        }
        return a;
    }


    /**
     * 近n天总的门店会员业绩
     *
     * @param
     * @return
     */
    public List<DashboardVo> nearVip(int m,int n) {
        Date date1 = DateUtils.getNowDate();
        Date date2 = DateUtils.rollDay(date1,-m);

        Calendar calendar00 = Calendar.getInstance();
        Date date222 = DateUtils.rollDay(date1,-m+1);
        calendar00.setTime(date222);
        calendar00.set(Calendar.SECOND,0); //这是将当天的【秒】设置为0
        calendar00.set(Calendar.MINUTE,0); //这是将当天的【分】设置为0
        calendar00.set(Calendar.HOUR_OF_DAY,0); //这是将当天的【时】设置为0

        Date date22 = calendar00.getTime();

        Date date3 = DateUtils.rollDay(date2,-n);

        Calendar calendar11 = Calendar.getInstance();
        calendar11.setTime(date3);
        calendar11.set(Calendar.SECOND,0); //这是将当天的【秒】设置为0
        calendar11.set(Calendar.MINUTE,0); //这是将当天的【分】设置为0
        calendar11.set(Calendar.HOUR_OF_DAY,0); //这是将当天的【时】设置为0

        Date date33 = calendar11.getTime();

        List<HsGrouporder> hsGrouporderList = this.hsGrouporderMapper.queryTime(date33,date22);

        List<DashboardVo> dashboardVoList = new ArrayList<>();
        for(HsGrouporder hsGrouporder:hsGrouporderList){
            DashboardVo dashboardVo = new DashboardVo();
            dashboardVo.setTotal(hsGrouporder.getGrouporderTotal());
            System.out.println(hsGrouporder.getGrouporderTotal());
            dashboardVo.setDay(hsGrouporder.getGrouporderDatetime());
            dashboardVoList.add(dashboardVo);
        }

        List<DashboardVo> dashboardVoList1 = new ArrayList<>();

        for(int i=0;i<n;i++){
            Date resultDate1 = DateUtils.rollDay(date2,-i);
            DashboardVo dashboardVo = new DashboardVo();
            dashboardVo.setDay(resultDate1);
            dashboardVo.setTotal(0.0);
            dashboardVoList1.add(dashboardVo);
        }


        for(DashboardVo dashboardVo1:dashboardVoList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dashboardVo1.getDay());
            int a = calendar.get(Calendar.MONTH);
            int a1 = calendar.get(Calendar.YEAR);
            int a2 = calendar.get(Calendar.DATE);
            for(DashboardVo dashboardVo2:dashboardVoList1) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(dashboardVo2.getDay());
                int b = calendar1.get(Calendar.MONTH);
                int b1 = calendar1.get(Calendar.YEAR);
                int b2 = calendar1.get(Calendar.DATE);
                if(a == b && a1 == b1 && a2 == b2){
                    Double m1 = 0.0;
                    Double m2 = 0.0;
                    if(dashboardVo1.getTotal()!=null) m1 =dashboardVo1.getTotal();
                    if(dashboardVo2.getTotal()!=null) m2 =dashboardVo2.getTotal();
                    dashboardVo2.setTotal(m1+m2+0.0);
                }
            }
        }
        return dashboardVoList1;
    }

    /**
     * 近n天总的门店散客业绩
     *
     * @param
     * @return
     */
    public List<DashboardVo> nearOrdinary(int m,int n) {
        Date date1 = DateUtils.getNowDate();
        Date date2 = DateUtils.rollDay(date1,-m);

        Calendar calendar00 = Calendar.getInstance();
        Date date222 = DateUtils.rollDay(date1,-m+1);
        calendar00.setTime(date222);
        calendar00.set(Calendar.SECOND,0); //这是将当天的【秒】设置为0
        calendar00.set(Calendar.MINUTE,0); //这是将当天的【分】设置为0
        calendar00.set(Calendar.HOUR_OF_DAY,0); //这是将当天的【时】设置为0

        Date date22 = calendar00.getTime();

        Date date3 = DateUtils.rollDay(date2,-n);

        Calendar calendar11 = Calendar.getInstance();
        calendar11.setTime(date3);
        calendar11.set(Calendar.SECOND,0); //这是将当天的【秒】设置为0
        calendar11.set(Calendar.MINUTE,0); //这是将当天的【分】设置为0
        calendar11.set(Calendar.HOUR_OF_DAY,0); //这是将当天的【时】设置为0

        Date date33 = calendar11.getTime();

        List<HsPaymentOrder> hsPaymentOrderList = this.hsPaymentOrderMapper.queryDay(date33,date22);

        List<DashboardVo> dashboardVoList = new ArrayList<>();
        for(HsPaymentOrder hsPaymentOrder:hsPaymentOrderList){
            DashboardVo dashboardVo = new DashboardVo();
            dashboardVo.setTotal(hsPaymentOrder.getTotalPayment());
//            System.out.println(hsPaymentOrder.getTotalPayment());
            dashboardVo.setDay(hsPaymentOrder.getTime());
            dashboardVoList.add(dashboardVo);
        }

        List<DashboardVo> dashboardVoList1 = new ArrayList<>();

        for(int i=0;i<n;i++){
            Date resultDate1 = DateUtils.rollDay(date2,-i);
            DashboardVo dashboardVo = new DashboardVo();
            dashboardVo.setDay(resultDate1);
            dashboardVo.setTotal(0.0);
            dashboardVoList1.add(dashboardVo);
        }


        for(DashboardVo dashboardVo1:dashboardVoList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dashboardVo1.getDay());
            int a = calendar.get(Calendar.MONTH);
            int a1 = calendar.get(Calendar.YEAR);
            int a2 = calendar.get(Calendar.DATE);
            for(DashboardVo dashboardVo2:dashboardVoList1) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(dashboardVo2.getDay());
                int b = calendar1.get(Calendar.MONTH);
                int b1 = calendar1.get(Calendar.YEAR);
                int b2 = calendar1.get(Calendar.DATE);
//                System.out.println("a+b+a1+b1+a2+b2="+a+b+a1+b1+a2+b2);
                if(a == b && a1 == b1 && a2 == b2){
                    Double m1 = 0.0;
                    Double m2 = 0.0;
                    if(dashboardVo1.getTotal()!=null) m1 =dashboardVo1.getTotal();
                    if(dashboardVo2.getTotal()!=null) m2 =dashboardVo2.getTotal();
                    dashboardVo2.setTotal(m1+m2+0.0);
                }
            }
        }
        return dashboardVoList1;
    }

    /**
     * 近n天总的门店业绩
     *
     * @param
     * @return
     */
    public List<DashboardVo> near(int m,int n) {
        DashboardVo dashboardVo = new DashboardVo();
        List<DashboardVo> dashboardVoList = nearVip(m,n);//会员
        List<DashboardVo> dashboardVoList1 = nearOrdinary(m,n);//普通

        //用新的list存会员
        //普通列表每一个和新的match，如果存在就加和，如果不存在就新建
        List<DashboardVo> near = dashboardVoList;

        for (DashboardVo dashboardVo1 : dashboardVoList1) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dashboardVo1.getDay());
            boolean i = false;
            for (DashboardVo dashboardVo2 : near) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(dashboardVo2.getDay());
                if (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE)) {
                    i = true;
                    dashboardVo2.setTotal(dashboardVo2.getTotal() + dashboardVo1.getTotal());
                }
            }
            if (i == false) {
                DashboardVo dashboardVo3 = new DashboardVo();
                dashboardVo3.setDay(dashboardVo1.getDay());
                dashboardVo3.setTotal(dashboardVo1.getTotal());
                near.add(dashboardVo3);
            }
        }

        for(DashboardVo dashboardVo1:near){
//            System.out.println(dashboardVo1);
        }
        return near;
    }

    /**
     * 近7天总的门店业绩
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> nearSeven() {
        List<DashboardVo> nearSeven = near(0,7);
        return nearSeven;
    }

    /**
     * 近7天总的门店业绩的时间条
     *
     * @param
     * @return
     */
    @Override
    public String[] nearSevenTime(){
        List<DashboardVo> dashboardVoList = nearSeven();
        int m=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        String[] a = new String[m];
        int n=m-1;
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n>=0) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                a[n] = sdf.format(dashboardVo.getDay());
                n--;
            }
        }
        return a;
    }

    /**
     * 近7天总的门店业绩的总额条
     *
     * @param
     * @return
     */
    @Override
    public Double[] nearSevenTotal(){
        List<DashboardVo> dashboardVoList = nearSeven();
        int m=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        Double[] a = new Double[m];
        int n=m-1;
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n>=0) {
                a[n]=dashboardVo.getTotal();
                n--;
            }
        }
        return a;
    }

    /**
     * 近三十天总的门店业绩(6个点)
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> nearMonth() {
        List<DashboardVo> dashboardVoList = near(0,5);
        Double total1=0.0;
        DashboardVo dashboardVo1 = new DashboardVo();
        for(DashboardVo dashboardVo:dashboardVoList){
            total1=total1+dashboardVo.getTotal();
        }
//        System.out.println("total1="+total1);
        dashboardVo1.setTotal(total1);
        dashboardVo1.setName("前0-5天");
        List<DashboardVo> dashboardVoList1 = near(5,5);
        Double total2=0.0;
        DashboardVo dashboardVo2 = new DashboardVo();
        for(DashboardVo dashboardVo:dashboardVoList1){
            total2=total2+dashboardVo.getTotal();
        }
//        System.out.println("total2="+total2);
        dashboardVo2.setTotal(total2);
        dashboardVo2.setName("前6-10天");
        List<DashboardVo> dashboardVoList2 = near(10,5);
        Double total3=0.0;
        DashboardVo dashboardVo3 = new DashboardVo();
        for(DashboardVo dashboardVo:dashboardVoList2){
            total3=total3+dashboardVo.getTotal();
//            System.out.println("getTotal3="+dashboardVo.getTotal());
        }
//        System.out.println("total3="+total3);
        dashboardVo3.setTotal(total3);
        dashboardVo3.setName("前11-15天");
        List<DashboardVo> dashboardVoList3 = near(15,5);
        Double total4=0.0;
        DashboardVo dashboardVo4 = new DashboardVo();
        for(DashboardVo dashboardVo:dashboardVoList3){
            total4=total4+dashboardVo.getTotal();
//            System.out.println("getTotal4="+dashboardVo.getTotal());
        }
//        System.out.println("total4="+total4);
        dashboardVo4.setTotal(total4);
        dashboardVo4.setName("前16-20天");
        List<DashboardVo> dashboardVoList4 = near(20,5);
        Double total5=0.0;
        DashboardVo dashboardVo5 = new DashboardVo();
        for(DashboardVo dashboardVo:dashboardVoList4){
            total5=total5+dashboardVo.getTotal();
//            System.out.println("getTotal5="+dashboardVo.getTotal());
        }
//        System.out.println("total5="+total5);
        dashboardVo5.setTotal(total5);
        dashboardVo5.setName("前21-25天");
        List<DashboardVo> dashboardVoList5 = near(25,5);
        Double total6=0.0;
        DashboardVo dashboardVo6 = new DashboardVo();
        for(DashboardVo dashboardVo:dashboardVoList5){
            total6=total6+dashboardVo.getTotal();
//            System.out.println("getTotal6="+dashboardVo.getTotal());
        }
//        System.out.println("total6="+total6);
        dashboardVo6.setTotal(total6);
        dashboardVo6.setName("前25-26天");

        List<DashboardVo> dashboardVos = new ArrayList<>();
        dashboardVos.add(dashboardVo6);
        dashboardVos.add(dashboardVo5);
        dashboardVos.add(dashboardVo4);
        dashboardVos.add(dashboardVo3);
        dashboardVos.add(dashboardVo2);
        dashboardVos.add(dashboardVo1);
        return dashboardVos;
    }

    /**
     * 近三十天总的门店业绩的时间条
     *
     * @param
     * @return
     */
    @Override
    public String[] nearMonthTime(){
        List<DashboardVo> dashboardVoList = nearMonth();
        int m=0;
        int n=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        String[] a = new String[m];
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n<m) {
                a[n]=String.valueOf(dashboardVo.getName());
                n++;
            }
        }
        return a;
    }

    /**
     * 近7天总的门店业绩的总额条
     *
     * @param
     * @return
     */
    @Override
    public Double[] nearMonthTotal(){
        List<DashboardVo> dashboardVoList = nearMonth();
        int m=0;
        int n=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        Double[] a = new Double[m];
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n<m) {
                a[n]=dashboardVo.getTotal();
                n++;
            }
        }
        return a;
    }




    /**
     * 会员数
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> vipNumber() {
        List<DashboardVo> dashboardVoList = new ArrayList<>();
        HsAccumulation hsAccumulation = new HsAccumulation();
        Integer a1=0;
        Integer a2=0;
        Integer a3=0;
        Integer a4=0;
        Integer a5=0;
        Integer a6=0;
        List<HsAccumulation> hsAccumulationList = this.hsAccumulationMapper.queryAll(hsAccumulation);
        for(HsAccumulation hsAccumulation1:hsAccumulationList){
            Double money=hsAccumulation1.getAccumulationTotal();
            if(money<500){
                a1=a1+1;
            }
            if(500<=money && money<1000){
                a2=a2+1;
            }
            if(1000<=money && money<1500){
                a3=a3+1;
            }
            if(1500<=money && money<2000){
                a4=a4+1;
            }
            if(2000<=money && money<2500){
                a5=a5+1;
            }
            if(money >= 2500){
                a6=a6+1;
            }

        }
        DashboardVo dashboardVo = new DashboardVo();
        dashboardVo.setValue(a1);
        dashboardVo.setName("白银会员Lv1");

        DashboardVo dashboardVo1 = new DashboardVo();
        dashboardVo1.setValue(a2);
        dashboardVo1.setName("黄金会员Lv2");

        DashboardVo dashboardVo2 = new DashboardVo();
        dashboardVo2.setValue(a3);
        dashboardVo2.setName("铂金会员Lv3");

        DashboardVo dashboardVo3 = new DashboardVo();
        dashboardVo3.setValue(a4);
        dashboardVo3.setName("钻石会员Lv4");

        DashboardVo dashboardVo4 = new DashboardVo();
        dashboardVo4.setValue(a5);
        dashboardVo4.setName("至尊会员Lv5");

        DashboardVo dashboardVo5 = new DashboardVo();
        dashboardVo5.setValue(a6);
        dashboardVo5.setName("黑金会员Lv6");

        dashboardVoList.add(dashboardVo);
        dashboardVoList.add(dashboardVo1);
        dashboardVoList.add(dashboardVo2);
        dashboardVoList.add(dashboardVo3);
        dashboardVoList.add(dashboardVo4);
        dashboardVoList.add(dashboardVo5);
        return dashboardVoList;
    }


    /**
     * 员工业绩
     *
     * @param
     * @return
     */
    @Override
    public List<DashboardVo> performance() {
        List<DashboardVo> dashboardVoList = new ArrayList<>();

        //先再payment的order里面找到所有本月已经支付了的orderId
        //当月
        Date date1 = DateUtils.getNowDate();
        Date date2 = DateUtils.rollDay(date1,-30);
        Calendar calendar00 = Calendar.getInstance();
        calendar00.setTime(date2);
        calendar00.set(Calendar.SECOND,0); //这是将当天的【秒】设置为0
        calendar00.set(Calendar.MINUTE,0); //这是将当天的【分】设置为0
        calendar00.set(Calendar.HOUR_OF_DAY,0); //这是将当天的【时】设置为0
        calendar00.set(Calendar.DATE,1); //这是将当天月的【日】设置为1
        Date date22 = calendar00.getTime();

        List<HsPaymentOrder> hsPaymentOrderList = this.hsPaymentOrderMapper.queryDay(date22,date1);
        //通过orderId在order里找到所有list
        //现在这些list就是已经支付的所有list
        //现在这个所有的list要一点点全部附进新的order的model里面

//        orderId;
//        projectId;
//        projectName;
//        projectPrice;
//        staffHairstylistId;
//        staffHairstylistName;
//        staffTechnicianId;
//        staffTechnicianName;
//        haircare;
        List<HsOrderModel> hsOrderModelList = new ArrayList<>();
        for(HsPaymentOrder hsPaymentOrder:hsPaymentOrderList){
            HsOrderModel hsOrderModel = new HsOrderModel();
            hsOrderModel.setOrderId(hsPaymentOrder.getOrderId());
            hsOrderModelList.add(hsOrderModel);
        }
        for(HsOrderModel hsOrderModel:hsOrderModelList){
            HsOrder hsOrder = this.hsOrderMapper.queryById(hsOrderModel.getOrderId());
            hsOrderModel.setProjectId(hsOrder.getHsProject().getProjectId());
            hsOrderModel.setProjectName(hsOrder.getHsProject().getProjectName());
            hsOrderModel.setProjectPrice(hsOrder.getHsProject().getProjectPrice());
            hsOrderModel.setStaffHairstylistId(hsOrder.getStaffHairstylistId());
            hsOrderModel.setStaffHairstylistName(this.hsStaffMapper.queryById(hsOrder.getStaffHairstylistId()).getStaffName());
            hsOrderModel.setStaffTechnicianId(hsOrder.getStaffTechnicianId());
            hsOrderModel.setStaffTechnicianName(this.hsStaffMapper.queryById(hsOrder.getStaffTechnicianId()).getStaffName());
            hsOrderModel.setHaircare(hsOrder.getHaircare());
        }

        //把所有员工的名字排进DashboardVo的list当中
        //再将DasboardVo的东西填入

        List<HsStaff> hsStaffList = this.hsStaffMapper.allStaff();
        for(HsStaff hsStaff:hsStaffList){
            DashboardVo dashboardVo = new DashboardVo();
            dashboardVo.setStaffName(hsStaff.getStaffName());
            dashboardVo.setStaffNumber(0);
            dashboardVo.setTotal(0.0);
            dashboardVoList.add(dashboardVo);
        }

        //        projectPrice;
        //        staffHairstylistName;
        //        staffTechnicianName;
        //        haircare

        //staffname o
        //total o
        //staffNumber  o
        //staffPerformance
        //level
        for(HsOrderModel hsOrderModel:hsOrderModelList){
            Double a = hsOrderModel.getProjectPrice() ;
            String b = hsOrderModel.getStaffHairstylistName();
            String c = hsOrderModel.getStaffTechnicianName();
            Integer d = hsOrderModel.getHaircare();
            for(DashboardVo dashboardVo:dashboardVoList){
                String a1 = dashboardVo.getStaffName() ;
//                System.out.println("dashboardVo.getStaffName()="+dashboardVo.getStaffName());
//                System.out.println("a1="+a1+"hhhhh");
//                System.out.println("hsOrderModel.getStaffHairstylistName()="+hsOrderModel.getStaffHairstylistName());
//                System.out.println("b="+b+"hhhhh");
                if(a1.equals(b)){
                    dashboardVo.setTotal(dashboardVo.getTotal()+a);
                    dashboardVo.setStaffNumber(dashboardVo.getStaffNumber()+1);
//                    System.out.println("dashboardVo.getStaffNumber()="+dashboardVo.getStaffNumber());
                }
                if(a1.equals(c)){
                    if(d == 5) {
                        dashboardVo.setTotal(dashboardVo.getTotal() + 100.00);
                        dashboardVo.setStaffNumber(dashboardVo.getStaffNumber() + 1);
                    }
                }
            }
        }
        //数据标准化
        //找到最大值与最小值
        Double max1 = dashboardVoList.get(0).getTotal();
        Double min1 = dashboardVoList.get(0).getTotal() ;
        Integer max2 = dashboardVoList.get(0).getStaffNumber() ;
        Integer min2 = dashboardVoList.get(0).getStaffNumber() ;
        for(DashboardVo dashboardVo:dashboardVoList){
            Double a = dashboardVo.getTotal() ;
            Integer b = dashboardVo.getStaffNumber() ;
//            System.out.println("a="+a);
//            System.out.println("b="+b);
            if(a>max1){
                max1 = a;
            }
            if(a<min1){
                min1 = a;
            }
            if(b>max2){
                max2 = b;
            }
            if(b<min2){
                min2 = b;
            }
        }
        //新数据=（原数据-最小值）/（最大值-最小值）
        for(DashboardVo dashboardVo:dashboardVoList){
            Double a = (dashboardVo.getTotal()-min1 )/(max1-min1);
            Double b = Double.valueOf((dashboardVo.getStaffNumber()-min2)/(max2-min2));
            DecimalFormat df   = new DecimalFormat("######0.00");
            Double c = (0.4*a+0.6*b)*1000;
            String d = df.format(c);
            Double e = Double.valueOf(d);
            dashboardVo.setStaffPerformance(e);
        }
        dashboardVoList.sort(Comparator.comparingDouble(DashboardVo::getStaffPerformance));

        List<DashboardVo> dashboardVoList1 = new ArrayList<>();
        int m=0;
        for(DashboardVo dashboardVo:dashboardVoList){
            dashboardVo.setLevel(m+1);
            m=m+1;
        }
        if (m > 5) {
            for(int i=0;i<5;i++){
                dashboardVoList1.add(dashboardVoList.get(i));
            }
        }else{
            for(int i=0;i<m;i++){
                dashboardVoList1.add(dashboardVoList.get(i));
            }
        }


        return dashboardVoList1;
    }

    /**
     * 员工业绩--name
     *
     * @param
     * @return
     */
    @Override
    public String[] performanceName() {
        List<DashboardVo> dashboardVoList = performance();
        int m=0;

        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        String[] a = new String[m];
        int n=m-1;
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n>=0) {
                a[n]=dashboardVo.getStaffName();
                n--;
            }
        }

        return a;

    }

    /**
     * 员工业绩--总额
     *
     * @param
     * @return
     */
    @Override
    public Double[] performanceTotal() {
        List<DashboardVo> dashboardVoList = performance();
        int m=0;
        for (DashboardVo dashboardVo:dashboardVoList){
            m=m+1;
        }
        Double[] a = new Double[m];
        int n=m-1;
        for (DashboardVo dashboardVo:dashboardVoList){
            if(n>=0) {
                a[n]=dashboardVo.getStaffPerformance();
                n--;
            }
        }

        return a;
    }

}
