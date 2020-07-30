package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagSysItem;

/**
 * 特征项Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagSysItemMapper extends BaseMapper<CtagSysItem>
{
    /**
     * 查询特征项
     *
     * @param id 特征项ID
     * @return 特征项
     */
    public CtagSysItem selectCtagSysItemById(Long id);

    /**
     * 查询特征项列表
     *
     * @param ctagSysItem 特征项
     * @return 特征项集合
     */
    public List<CtagSysItem> selectCtagSysItemList(CtagSysItem ctagSysItem);

    /**
     * 新增特征项
     *
     * @param ctagSysItem 特征项
     * @return 结果
     */
    public int insertCtagSysItem(CtagSysItem ctagSysItem);

    /**
     * 修改特征项
     *
     * @param ctagSysItem 特征项
     * @return 结果
     */
    public int updateCtagSysItem(CtagSysItem ctagSysItem);

    /**
     * 删除特征项
     *
     * @param id 特征项ID
     * @return 结果
     */
    public int deleteCtagSysItemById(Long id);

    /**
     * 批量删除特征项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagSysItemByIds(Long[] ids);
}
