package com.healthya;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * @Author SunqiXiang
 * @Date 2023/2/1 18:13
 * @Version 1.0
 * 实现七牛云本地文件操作
 *
 */
public class testQiNiuYun {

    @Test
    public void testUpload() {//文件上传

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "Se_F9RzTntP_Rz8kQ8GGd1sNmt7i-qvw_NCEyYHA";
        String secretKey = "AKc2ppDJwOeZK0t4qiE2RYcRmdW3A-XFC7KFj669";
        String bucket = "gugu-health";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "F:\\2022\\javaweb\\资料-传智健康项目\\day04\\资源\\图片资源\\03a36073-a140-4942-9b9b-712cecb144901.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "haha.jpg";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

//实现七牛云删除操作
    @Test
    public void testDel() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...删除凭证
        String accessKey = "Se_F9RzTntP_Rz8kQ8GGd1sNmt7i-qvw_NCEyYHA";
        String secretKey = "AKc2ppDJwOeZK0t4qiE2RYcRmdW3A-XFC7KFj669";
        String bucket = "health-check1";
        String key = "haha.jpg";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
