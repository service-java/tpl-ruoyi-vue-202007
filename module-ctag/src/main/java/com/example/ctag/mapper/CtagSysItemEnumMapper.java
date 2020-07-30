package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagSysItemEnum;

/**
 * 特征项枚举Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagSysItemEnumMapper extends BaseMapper<CtagSysItemEnum>
{
    /**
     * 查询特征项枚举
     *
     * @param id 特征项枚举ID
     * @return 特征项枚举
     */
    public CtagSysItemEnum selectCtagSysItemEnumById(Long id);

    /**
     * 查询特征项枚举列表
     *
     * @param ctagSysItemEnum 特征项枚举
     * @return 特征项枚举集合
     */
    public List<CtagSysItemEnum> selectCtagSysItemEnumList(CtagSysItemEnum ctagSysItemEnum);

    /**
     * 新增特征项枚举
     *
     * @param ctagSysItemEnum 特征项枚举
     * @return 结果
     */
    public int insertCtagSysItemEnum(CtagSysItemEnum ctagSysItemEnum);

    /**
     * 修改特征项枚举
     *
     * @param ctagSysItemEnum 特征项枚举
     * @return 结果
     */
    public int updateCtagSysItemEnum(CtagSysItemEnum ctagSysItemEnum);

    /**
     * 删除特征项枚举
     *
     * @param id 特征项枚举ID
     * @return 结果
     */
    public int deleteCtagSysItemEnumById(Long id);

    /**
     * 批量删除特征项枚举
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagSysItemEnumByIds(Long[] ids);
}
