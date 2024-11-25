package com.healthya.dao;

import com.github.pagehelper.Page;
import com.healthya.entity.PageResult;
import com.healthya.entity.QueryPageBean;
import com.healthya.pojo.CheckItem;

import java.util.List;

/**
 * @Author SunqiXiang
 * @Date 2023/1/14 15:30
 * @Version 1.0
 */
public interface CheckItemDao {
    //    插入
    public void add(CheckItem checkItem);

    //按条件查询
    public Page<CheckItem> selectByCondition(String queryString);

    //根据id删除
    public void delete(Integer id);
    public long findCountByCheckItemId(Integer checkItemId);

//    根据id查询数据

    public CheckItem findById(Integer id);

//    编辑

    public void edit(CheckItem checkItem);

    //    查询所有

    public List<CheckItem> findAll();
}
