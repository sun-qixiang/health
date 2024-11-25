package com.healthya.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.healthya.constant.MessageConstant;
import com.healthya.entity.PageResult;
import com.healthya.entity.QueryPageBean;
import com.healthya.entity.Result;
import com.healthya.pojo.CheckItem;
import com.healthya.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author SunqiXiang
 * @Date 2023/1/14 14:55
 * @Version 1.0
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference   //去zookeeper中查找服务
    private CheckItemService checkItemService;

    @RequestMapping("/add.do")
    public Result add(
            @RequestBody
                    CheckItem checkItem) {
        try { //服务调用成功
            checkItemService.add(checkItem);
            System.out.println("服务调用成功");
        } catch (Exception e) { //服务调用失败
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

//    分页查询

    @RequestMapping("/findPage.do")
    public PageResult findPage(
            @RequestBody
                    QueryPageBean pageBean) {
        PageResult page = checkItemService.findPage(pageBean);
        return page;
    }

    //    删除
    @RequestMapping("delete")
    public Result delete(Integer id) {
        try {
            checkItemService.delete(id);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //    按id查询数据
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);

        } catch (Exception e) {
            e.printStackTrace();//服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //编辑
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }


//    查询所有
    @RequestMapping("/findAll.do")
    public Result findAll() {
        try {
            List<CheckItem> checkItemList = checkItemService.findAll();
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS,checkItemList);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }

}
