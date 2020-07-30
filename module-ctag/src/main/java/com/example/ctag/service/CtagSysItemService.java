package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagSysItemMapper;
import com.example.ctag.entity.CtagSysItem;
import org.springframework.stereotype.Service;


/**
 * 特征项Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagSysItemService extends ServiceImpl<CtagSysItemMapper, CtagSysItem>
{
    @Autowired
    private CtagSysItemMapper ctagSysItemMapper;

    /**
     * 查询特征项
     *
     * @param id 特征项ID
     * @return 特征项
     */

    public CtagSysItem selectCtagSysItemById(Long id)
    {
        return ctagSysItemMapper.selectCtagSysItemById(id);
    }

    /**
     * 查询特征项列表
     *
     * @param ctagSysItem 特征项
     * @return 特征项
     */

    public List<CtagSysItem> selectCtagSysItemList(CtagSysItem ctagSysItem)
    {
        return ctagSysItemMapper.selectCtagSysItemList(ctagSysItem);
    }

    /**
     * 新增特征项
     *
     * @param ctagSysItem 特征项
     * @return 结果
     */

    public int insertCtagSysItem(CtagSysItem ctagSysItem)
    {
        return ctagSysItemMapper.insertCtagSysItem(ctagSysItem);
    }

    /**
     * 修改特征项
     *
     * @param ctagSysItem 特征项
     * @return 结果
     */

    public int updateCtagSysItem(CtagSysItem ctagSysItem)
    {
        return ctagSysItemMapper.updateCtagSysItem(ctagSysItem);
    }

    /**
     * 批量删除特征项
     *
     * @param ids 需要删除的特征项ID
     * @return 结果
     */

    public int deleteCtagSysItemByIds(Long[] ids)
    {
        return ctagSysItemMapper.deleteCtagSysItemByIds(ids);
    }

    /**
     * 删除特征项信息
     *
     * @param id 特征项ID
     * @return 结果
     */

    public int deleteCtagSysItemById(Long id)
    {
        return ctagSysItemMapper.deleteCtagSysItemById(id);
    }
}
