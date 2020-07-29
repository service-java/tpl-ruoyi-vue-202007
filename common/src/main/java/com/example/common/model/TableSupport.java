package com.example.common.model;

import com.example.common.constant.PageConstants;
import com.example.common.util.ServletUtils;

/**
 * 表格数据处理
 *
 * @author ruoyi
 */
public class TableSupport {

    /**
     * 封装分页对象
     */
    public static PageQuery getPageDomain() {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(ServletUtils.getParameterToInt(PageConstants.PAGE_NUM));
        pageQuery.setPageSize(ServletUtils.getParameterToInt(PageConstants.PAGE_SIZE));
        pageQuery.setOrderByColumn(ServletUtils.getParameter(PageConstants.ORDER_BY_COLUMN));
        pageQuery.setIsAsc(ServletUtils.getParameter(PageConstants.IS_ASC));
        return pageQuery;
    }

    public static PageQuery buildPageRequest() {
        return getPageDomain();
    }
}
