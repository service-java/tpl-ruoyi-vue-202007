package com.example.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.constant.HttpStatusConstants;
import com.example.common.constant.PageConstants;
import com.example.common.model.PageQuery;
import com.example.common.model.vo.PageVO;
import com.example.common.model.vo.ResponseVO;
import com.example.common.util.DateUtils;
import com.example.common.util.ServletUtils;
import com.example.common.util.StringUtils;
import com.example.common.util.sql.SqlUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageQuery pageQuery = this.buildPageRequest();
        Integer pageNum = pageQuery.getPageNum();
        Integer pageSize = pageQuery.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtils.escapeOrderBySql(pageQuery.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 封装分页对象
     */
    public static PageQuery buildPageRequest() {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(ServletUtils.getParameterToInt(PageConstants.PAGE_NUM));
        pageQuery.setPageSize(ServletUtils.getParameterToInt(PageConstants.PAGE_SIZE));
        pageQuery.setOrderByColumn(ServletUtils.getParameter(PageConstants.ORDER_BY_COLUMN));
        pageQuery.setIsAsc(ServletUtils.getParameter(PageConstants.IS_ASC));
        return pageQuery;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected PageVO getDataTable(IPage<?> page) {
        PageVO respData = new PageVO();
        respData.setCode(HttpStatusConstants.SUCCESS);
        respData.setMsg("查询成功");
        respData.setData(page.getRecords());
        respData.setTotal(page.getTotal());
        return respData;
    }

    protected PageVO getDataTable(List<?> list) {
        PageVO respData = new PageVO();
        respData.setCode(HttpStatusConstants.SUCCESS);
        respData.setMsg("查询成功");
        respData.setData(list);
        respData.setTotal(new PageInfo(list).getTotal());
        return respData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ResponseVO toAjax(int rows) {
        return rows > 0 ? ResponseVO.success() : ResponseVO.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}
