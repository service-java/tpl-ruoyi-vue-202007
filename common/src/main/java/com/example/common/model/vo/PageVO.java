package com.example.common.model.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author ruoyi
 */
public class PageVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> data;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;

    /**
     * 表格数据对象
     */
    public PageVO()
    {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public PageVO(List<?> list, int total)
    {
        this.data = list;
        this.total = total;
    }

    public PageVO(IPage<?> page) {
        this.data = page.getRecords();
        this.total = page.getTotal();
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<?> getData()
    {
        return data;
    }

    public void setData(List<?> data)
    {
        this.data = data;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
