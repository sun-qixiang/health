package com.healthya.dao;

import com.github.pagehelper.Page;
import com.healthya.entity.PageResult;
import com.healthya.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @Author SunqiXiang
 * @Date 2023/1/17 21:55
 * @Version 1.0
 */
public interface CheckGroupMapper {

//新增
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);
    //    分页查询
    public Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void deleteAssociation(Integer id);
    void edit(CheckGroup checkGroup);
    List<CheckGroup> findAll();
}
