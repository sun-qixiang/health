package com.healthya.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.healthya.entity.PageResult;
import com.healthya.entity.QueryPageBean;
import com.healthya.pojo.CheckItem;

import java.util.List;

/**
 * @Author SunqiXiang
 * @Date 2023/1/14 15:02
 * @Version 1.0
 * 服务接口
 */

public interface CheckItemService {

    //    新增
    public void add(CheckItem checkItem);

//    分页查询
    public PageResult findPage(QueryPageBean pageBean);

    //    删除
    public void delete(Integer id);

//    根据id查询数据

    public CheckItem findById(Integer id);

//    编辑

    public void edit(CheckItem checkItem);


//    查询所有

    public List<CheckItem> findAll();

}
