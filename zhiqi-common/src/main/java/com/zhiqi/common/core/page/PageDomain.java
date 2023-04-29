package com.zhiqi.common.core.page;

import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.common.utils.StringUtils;

/**
 * 分页数据
 *
 * @author RyuJung
 * @since 2023/4/14-23:05
 */
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    public static PageDomain getPageDomainFromRequest() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        pageDomain.setReasonable(ServletUtils.getParameterToBool(REASONABLE));
        return pageDomain;
    }

    /**
     * 从请求中获取分页相关的信息，包括：
     * 当前记录起始索引、每页显示记录数、排序列、排序的方向、分页参数合理化
     *
     * @return
     */
    public static PageDomain build() {
        return getPageDomainFromRequest();
    }

    private Integer pageNum;
    private Integer pageSize;
    private String orderByColumn;

    private String isAsc = "asc";
    private Boolean reasonable = true;

    public String getOrderBy() {
        if (StringUtils.isNotEmpty(orderByColumn)) {
            return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
        }
        return "";
    }

    public void setIsAsc(String isAsc) {
        if (StringUtils.isEmpty(isAsc)) {
            return;
        }
        // 兼容前端排序类型
        if ("ascending".equals(isAsc)) {
            isAsc = "desc";
        } else if ("descending".equals(isAsc)) {
            isAsc = "asc";
        }
        this.isAsc = isAsc;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }
}
