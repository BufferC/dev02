package com.fc.vo;

import java.util.List;

// 分页信息类，包含了分页相关的参数
public class PageInfo<T> {
    // 总页数
    private int pageCount;
    // 总数据量
    private int totalCount;
    // 每页显示多少条
    private int pageSize;
    // 当前页
    private int pageNo;
    // 当前页中的所有内容
    private List<T> list;

    public PageInfo() {
    }

    public PageInfo(int totalCount, int pageSize, int pageNo, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.list = list;

        if (totalCount % pageSize == 0) {
            this.pageCount = totalCount / pageSize;
        } else {
            this.pageCount = totalCount / pageSize + 1;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageCount=" + pageCount +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", list=" + list +
                '}';
    }
}
