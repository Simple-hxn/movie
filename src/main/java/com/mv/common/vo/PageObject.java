package com.mv.common.vo;
/**
 * VO:Value Object(值对象)
 * 1)当前页要呈现的记录
 * 2)分页信息
 * @Param<T>类上定义的泛型参数
 * 我们叫做类泛型
 */
import java.io.Serializable;
import java.util.List;

public class PageObject<T> implements Serializable{
    private static final long serialVersionUID = -4288938782658423221L;
    /**当前页的页码数*/
    private Integer pageCurrent = 1;
    /**页面大小*/
    private Integer pageSize = 8;
    /**总行数*/
    private Integer rowCount = 0;
    /**总页数*/
    private Integer pageCount = 0;
    /**当前页记录数*/
    private List<T> records;

    public Integer getPageCurrent() {
        return pageCurrent;
    }
    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getRowCount() {
        return rowCount;
    }
    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }
    public Integer getPageCount() {
        return pageCount = (rowCount - 1)/pageSize + 1;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    public List<T> getRecords() {
        return records;
    }
    public void setRecords(List<T> records) {
        this.records = records;
    }

}
