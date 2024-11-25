package com.healthya.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.healthya.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author sqx
 * @Date 2023/3/18 15:04
 * @Description: 预约设置服务接口
 **/
@Service
public interface OrderSettingService {


    /**
     * excel文件上传，保存数据
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList);


    /**
     *根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     * @param date
     * @return
     */
    List<Map> getOrderSettingByMonth(String date);//参数格式为：2019-03


    /**
     * 据指定日期修改可预约人数
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);

}
