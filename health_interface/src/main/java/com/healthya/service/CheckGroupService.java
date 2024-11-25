package com.healthya.service;

import com.healthya.entity.PageResult;
import com.healthya.pojo.CheckGroup;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author SunqiXiang
 * @Date 2023/1/17 21:46
 * @Version 1.0
 */
public interface CheckGroupService {

//    新增
    public void add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds);
//    分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);
    public List<CheckGroup> findAll();

}
