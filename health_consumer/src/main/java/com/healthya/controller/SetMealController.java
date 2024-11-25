package com.healthya.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.healthya.constant.MessageConstant;
import com.healthya.constant.RedisConstant;
import com.healthya.entity.PageResult;
import com.healthya.entity.QueryPageBean;
import com.healthya.entity.Result;
import com.healthya.pojo.Setmeal;
import com.healthya.service.SetmealService;
import com.healthya.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @Author SunqiXiang
 * @Date 2023/4/21 23:57
 * @Version 1.0
 * 套餐管理
 */

@RestController
@RequestMapping("/setmeal")
public class SetMealController {



    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;
    /**
     * 图片上传
     * @param imgFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam(value = "imgFile") MultipartFile imgFile){
        try {
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf);
            //使用UUID随机生成文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiNiuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);

            //将上传图片名称存入Redis,基于Redis的Set集合存储
           jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //图片上传失败
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }


    /**
     * 新增
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        } catch (Exception e) {
            //新增套餐失败
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }


    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

}
