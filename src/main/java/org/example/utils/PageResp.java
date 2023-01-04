package org.example.utils;

import java.util.List;

/**
 * @author Derek-huang
 */
public class PageResp<T> {

    private List<T> datas;

    private long totalCount = 0;
    private long totalPages = 0;
    private long currentPage = 1;
    private long pageSize = 20;
    private long startIndex = 1;

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}

