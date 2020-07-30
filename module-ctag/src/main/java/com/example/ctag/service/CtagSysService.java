package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagSysMapper;
import com.example.ctag.entity.CtagSys;
import org.springframework.stereotype.Service;


/**
 * 特征体系Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagSysService extends ServiceImpl<CtagSysMapper, CtagSys>
{
    @Autowired
    private CtagSysMapper ctagSysMapper;

    /**
     * 查询特征体系
     *
     * @param id 特征体系ID
     * @return 特征体系
     */

    public CtagSys selectCtagSysById(Long id)
    {
        return ctagSysMapper.selectCtagSysById(id);
    }

    /**
     * 查询特征体系列表
     *
     * @param ctagSys 特征体系
     * @return 特征体系
     */

    public List<CtagSys> selectCtagSysList(CtagSys ctagSys) {
        return ctagSysMapper.selectCtagSysList(ctagSys);
    }

    /**
     * 新增特征体系
     *
     * @param ctagSys 特征体系
     * @return 结果
     */

    public int insertCtagSys(CtagSys ctagSys)
    {
        return ctagSysMapper.insertCtagSys(ctagSys);
    }

    /**
     * 修改特征体系
     *
     * @param ctagSys 特征体系
     * @return 结果
     */

    public int updateCtagSys(CtagSys ctagSys)
    {
        return ctagSysMapper.updateCtagSys(ctagSys);
    }

    /**
     * 批量删除特征体系
     *
     * @param ids 需要删除的特征体系ID
     * @return 结果
     */

    public int deleteCtagSysByIds(Long[] ids)
    {
        return ctagSysMapper.deleteCtagSysByIds(ids);
    }

    /**
     * 删除特征体系信息
     *
     * @param id 特征体系ID
     * @return 结果
     */

    public int deleteCtagSysById(Long id)
    {
        return ctagSysMapper.deleteCtagSysById(id);
    }
}
