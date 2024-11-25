package com.healthya.service;

import com.healthya.entity.PageResult;
import com.healthya.pojo.Setmeal;


import java.util.List;
import java.util.Map;

/**
 * @Author sqx
 * @Date 2023/3/16 20:36
 * @Description: 体检套餐服务接口
 **/
public interface SetmealService {

    /**
     * 新增套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);


    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);


    /**
     * 查询所有套餐信息
     * @return
     */
    List<Setmeal> findAll();


    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    Setmeal findById(int id);


    /**
     * 套餐占比统计
     * @return
     */
    List<Map<String, Object>> findSetmealCount();

}
