package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagSysMatchMapper;
import com.example.ctag.entity.CtagSysMatch;
import org.springframework.stereotype.Service;


/**
 * 特征项匹配Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagSysMatchService extends ServiceImpl<CtagSysMatchMapper, CtagSysMatch>
{
    @Autowired
    private CtagSysMatchMapper ctagSysMatchMapper;

    /**
     * 查询特征项匹配
     *
     * @param id 特征项匹配ID
     * @return 特征项匹配
     */

    public CtagSysMatch selectCtagSysMatchById(Long id)
    {
        return ctagSysMatchMapper.selectCtagSysMatchById(id);
    }

    /**
     * 查询特征项匹配列表
     *
     * @param ctagSysMatch 特征项匹配
     * @return 特征项匹配
     */

    public List<CtagSysMatch> selectCtagSysMatchList(CtagSysMatch ctagSysMatch)
    {
        return ctagSysMatchMapper.selectCtagSysMatchList(ctagSysMatch);
    }

    /**
     * 新增特征项匹配
     *
     * @param ctagSysMatch 特征项匹配
     * @return 结果
     */

    public int insertCtagSysMatch(CtagSysMatch ctagSysMatch)
    {
        return ctagSysMatchMapper.insertCtagSysMatch(ctagSysMatch);
    }

    /**
     * 修改特征项匹配
     *
     * @param ctagSysMatch 特征项匹配
     * @return 结果
     */

    public int updateCtagSysMatch(CtagSysMatch ctagSysMatch)
    {
        return ctagSysMatchMapper.updateCtagSysMatch(ctagSysMatch);
    }

    /**
     * 批量删除特征项匹配
     *
     * @param ids 需要删除的特征项匹配ID
     * @return 结果
     */

    public int deleteCtagSysMatchByIds(Long[] ids)
    {
        return ctagSysMatchMapper.deleteCtagSysMatchByIds(ids);
    }

    /**
     * 删除特征项匹配信息
     *
     * @param id 特征项匹配ID
     * @return 结果
     */

    public int deleteCtagSysMatchById(Long id)
    {
        return ctagSysMatchMapper.deleteCtagSysMatchById(id);
    }
}
