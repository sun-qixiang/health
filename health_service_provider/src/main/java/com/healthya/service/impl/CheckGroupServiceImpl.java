package com.healthya.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.healthya.dao.CheckGroupMapper;
import com.healthya.entity.PageResult;
import com.healthya.pojo.CheckGroup;
import com.healthya.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author SunqiXiang
 * @Date 2023/1/17 21:51
 * @Version 1.0
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper groupMapper;

    //添加检查组合，同时需要设置检查组合和检查项的关联关系
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        groupMapper.add(checkGroup);
        this.CheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }
//分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = groupMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
//根据id查询检查组
    public CheckGroup findById(Integer id) {
        return groupMapper.findById(id);
    }

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return groupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑检查组，同时需要更新和检查项的关联关系
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //根据检查组id删除中间表数据（清理原有关联关系）
        groupMapper.deleteAssociation(checkGroup.getId());
        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        this.CheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        //更新检查组基本信息
        groupMapper.edit(checkGroup);
    }

    public List<CheckGroup> findAll() {
      return  groupMapper.findAll();

    }

    //设置检查组合和检查项的关联关系
    public void CheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<String,Integer>();
                map.put("checkgroup_id",checkGroupId);
                map.put("checkitem_id",checkitemId);
                groupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
