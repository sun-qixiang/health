package com.healthya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author SunqiXiang
 * @Date 2023/1/12 18:02
 * @Version 1.0
 * 封装分页返回结果
 */
public class PageResult implements Serializable {
    private Long total;//总记录数
    private List rows;//当前页结果
    public PageResult(Long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}
