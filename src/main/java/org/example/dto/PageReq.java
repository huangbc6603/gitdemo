package org.example.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Derek-huang
 */
public class PageReq {
    @ApiModelProperty("起始位置，偏移位置")
    private int startIndex;
    @ApiModelProperty("每页显示数量")
    private int pageSize;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
