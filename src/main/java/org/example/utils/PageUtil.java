package org.example.utils;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Derek-huang
 */
public class PageUtil<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // 总记录数
    private int totalCount;
    // 每页记录数
    private int pageSize;
    // 总页数
    private int totalPage;
    // 当前页数
    private int currPage;
    // 列表数据
    private transient List<T> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtil(List<T> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 返回分页信息
     *
     * @param list
     * @return
     */
    public static <T> PageResp<T> getPageInfo(List<T> list) {
        PageInfo<T> tempInfo = new PageInfo<>(list);
        long total = tempInfo.getTotal();
        PageResp<T> pageInfo = new PageResp<>();
        pageInfo.setStartIndex(tempInfo.getStartRow());
        pageInfo.setPageSize(tempInfo.getPageSize());
        pageInfo.setCurrentPage(tempInfo.getPageNum());
        pageInfo.setTotalPages(tempInfo.getPages());

        pageInfo.setTotalCount(total);
        pageInfo.setDatas(list);

        return pageInfo;
    }

    /**
     * 分页返回，带排序字段
     *
     * @param list
     * @return PageInfo<T>
     * @author rongfa.wu
     */
    public static <T> PageResp<T> getPageInfoOrder(List<T> list) {
        PageInfo<T> tempInfo = new PageInfo<>(list);
        long total = tempInfo.getTotal();

        PageResp<T> pageInfo = new PageResp<>();
        pageInfo.setStartIndex(tempInfo.getStartRow());
        pageInfo.setPageSize(tempInfo.getPageSize());
        pageInfo.setCurrentPage(tempInfo.getPageNum());
        pageInfo.setTotalPages(tempInfo.getPages());

        pageInfo.setTotalCount(total);
        pageInfo.setDatas(list);
        return pageInfo;
    }

    /**
     * 封装pageHelper查询出的分页信息
     *
     * @param pageHelplist page Helper查询出的list
     * @param resultList   需要结果类型的List
     * @return PageResp
     */
    public static <T, R> PageResp<R> getPageResp(List<T> pageHelplist, List<R> resultList) {
        PageInfo<T> tempInfo = new PageInfo<>(pageHelplist);
        PageResp<R> pageResp = new PageResp<>();

        long total = tempInfo.getTotal();

        pageResp.setStartIndex(tempInfo.getStartRow());
        pageResp.setPageSize(tempInfo.getPageSize());
        pageResp.setCurrentPage(tempInfo.getPageNum());
        pageResp.setTotalPages(tempInfo.getPages());

        pageResp.setTotalCount(total);
        pageResp.setDatas(resultList);

        return pageResp;
    }

    /**
     * 封装pageHelper查询出的分页信息
     *
     * @param total      总数
     * @param resultList 需要结果类型的List
     * @return PageResp
     */
    public static <R> PageResp<R> getPageResp(int total, List<R> resultList) {
        PageResp<R> pageResp = new PageResp<>();
        pageResp.setDatas(resultList);
        pageResp.setTotalCount(total);

        return pageResp;
    }

    public static <T> PageResp<T> getPageResp(PageInfo<T> info) {
        long total = info.getTotal();
        PageResp<T> pageInfo = new PageResp<>();
        pageInfo.setStartIndex(info.getStartRow());
        pageInfo.setPageSize(info.getPageSize());
        pageInfo.setCurrentPage(info.getPageNum());
        pageInfo.setTotalPages(info.getPages());

        pageInfo.setTotalCount(total);
        pageInfo.setDatas(info.getList());
        return pageInfo;
    }

}
