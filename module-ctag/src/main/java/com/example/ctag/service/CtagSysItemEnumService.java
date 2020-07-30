package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagSysItemEnumMapper;
import com.example.ctag.entity.CtagSysItemEnum;
import org.springframework.stereotype.Service;


/**
 * 特征项枚举Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagSysItemEnumService extends ServiceImpl<CtagSysItemEnumMapper, CtagSysItemEnum>
{
    @Autowired
    private CtagSysItemEnumMapper ctagSysItemEnumMapper;

    /**
     * 查询特征项枚举
     *
     * @param id 特征项枚举ID
     * @return 特征项枚举
     */

    public CtagSysItemEnum selectCtagSysItemEnumById(Long id)
    {
        return ctagSysItemEnumMapper.selectCtagSysItemEnumById(id);
    }

    /**
     * 查询特征项枚举列表
     *
     * @param ctagSysItemEnum 特征项枚举
     * @return 特征项枚举
     */

    public List<CtagSysItemEnum> selectCtagSysItemEnumList(CtagSysItemEnum ctagSysItemEnum)
    {
        return ctagSysItemEnumMapper.selectCtagSysItemEnumList(ctagSysItemEnum);
    }

    /**
     * 新增特征项枚举
     *
     * @param ctagSysItemEnum 特征项枚举
     * @return 结果
     */

    public int insertCtagSysItemEnum(CtagSysItemEnum ctagSysItemEnum)
    {
        return ctagSysItemEnumMapper.insertCtagSysItemEnum(ctagSysItemEnum);
    }

    /**
     * 修改特征项枚举
     *
     * @param ctagSysItemEnum 特征项枚举
     * @return 结果
     */

    public int updateCtagSysItemEnum(CtagSysItemEnum ctagSysItemEnum)
    {
        return ctagSysItemEnumMapper.updateCtagSysItemEnum(ctagSysItemEnum);
    }

    /**
     * 批量删除特征项枚举
     *
     * @param ids 需要删除的特征项枚举ID
     * @return 结果
     */

    public int deleteCtagSysItemEnumByIds(Long[] ids)
    {
        return ctagSysItemEnumMapper.deleteCtagSysItemEnumByIds(ids);
    }

    /**
     * 删除特征项枚举信息
     *
     * @param id 特征项枚举ID
     * @return 结果
     */

    public int deleteCtagSysItemEnumById(Long id)
    {
        return ctagSysItemEnumMapper.deleteCtagSysItemEnumById(id);
    }
}
