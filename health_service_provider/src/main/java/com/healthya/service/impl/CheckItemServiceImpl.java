package com.healthya.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.healthya.dao.CheckItemDao;
import com.healthya.entity.PageResult;
import com.healthya.entity.QueryPageBean;
import com.healthya.pojo.CheckItem;
import com.healthya.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author SunqiXiang
 * @Date 2023/1/14 15:25
 * @Version 1.0
 * 检查项服务
 */

@Service(interfaceClass = CheckItemService.class)   //暴露服务接口
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
//新增
    @Autowired
    private CheckItemDao checkItemDao;
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

//    分页查询
//    private Integer currentPage;//页码
//    private Integer pageSize;//每页记录数
//    private String queryString;//查询条件
    public PageResult findPage(QueryPageBean pageBean) {

        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();
        PageHelper.startPage(currentPage, pageSize); //分页查询助手
        if (pageBean.getQueryString() != null) {
            currentPage=1;
        }
        Page<CheckItem> checkItems = checkItemDao.selectByCondition(pageBean.getQueryString());
        long total = checkItems.getTotal();
        List<CheckItem> rows = checkItems.getResult();
        return new PageResult(total,rows);
    }
//根据id删除
//删除
public void delete(Integer id) throws RuntimeException{
    //查询当前检查项是否和检查组关联
    long count = checkItemDao.findCountByCheckItemId(id);
    if(count > 0){
        //当前检查项被引用，不能删除
        throw new RuntimeException("当前检查项被引用，不能删除");
    }
    checkItemDao.delete(id);
}

    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public List<CheckItem> findAll() {
        List<CheckItem> checkItemList = checkItemDao.findAll();
        return checkItemList;
    }
}

