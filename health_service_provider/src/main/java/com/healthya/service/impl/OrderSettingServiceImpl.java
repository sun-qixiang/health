package com.healthya.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.healthya.dao.OrderSettingDao;
import com.healthya.pojo.OrderSetting;
import com.healthya.service.OrderSettingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author sqx
 * @Date 2023/3/18 15:06
 * @Description: 预约设置
 **/

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{



    @Autowired
    private OrderSettingDao orderSettingDao;


    /**
     * excel上传，保存数据,批量添加
     * @param orderSettingList
     */
    @Override
    public void add(List<OrderSetting> orderSettingList) {
        if (orderSettingList != null && orderSettingList.size() > 0){
            for (OrderSetting orderSetting : orderSettingList) {
                //检查此数据(日期)是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0){
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else {
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }


    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     * @param date
     * @return
     */
    @Override
    public List<Map> getOrderSettingByMonth(String date) {//2019-3
        String dateBegin = date + "-1"; //2023-3-1
        String dateEnd = date + "-31"; //2023-3-31
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate()); //获得日期(几号)
            orderSettingMap.put("number",orderSetting.getNumber()); //可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations()); //已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }


    /**
     * 据指定日期修改可预约人数
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0){
            //当前日期已经进行过了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}

