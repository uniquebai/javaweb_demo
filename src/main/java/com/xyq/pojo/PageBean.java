package com.xyq.pojo;

import java.util.List;

/**
 * @author cwtt
 * 分页查询的JavaBean
 */
public class PageBean<T> {
    /**
     * 用于记录总文章数
     */
    private int totalCount;
    /**
     * 当前页的文章集合
     */
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
