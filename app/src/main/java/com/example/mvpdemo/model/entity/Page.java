package com.example.mvpdemo.model.entity;

import java.util.List;

/**
 * 分页的实体
 * Created by Administrator on 2017/4/15.
 */

public class Page<T> {
    /**
     * "pageSize": 10,
     * "totalPage": 408,
     * "totalRecord": 4079,
     * "currentPage": 1,
     * "data":[]
     */
    private int pageSize;
    private int totalPage;
    private int totalRecord;
    private int currentPage;
    private List<T> data;

    private Page(Builder builder) {
        pageSize = builder.pageSize;
        totalPage = builder.totalPage;
        totalRecord = builder.totalRecord;
        currentPage = builder.currentPage;
        data = builder.data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public static class Builder<T> {
        private int pageSize;
        private int totalPage;
        private int totalRecord;
        private int currentPage;
        private List<T> data;

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public Builder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public Builder<T> totalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
            return this;
        }

        public Builder<T> currentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }
    }
}
