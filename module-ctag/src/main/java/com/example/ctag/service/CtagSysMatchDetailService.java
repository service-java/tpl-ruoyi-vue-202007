package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagSysMatchDetailMapper;
import com.example.ctag.entity.CtagSysMatchDetail;
import org.springframework.stereotype.Service;


/**
 * 特征项匹配详情Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagSysMatchDetailService extends ServiceImpl<CtagSysMatchDetailMapper, CtagSysMatchDetail>
{
    @Autowired
    private CtagSysMatchDetailMapper ctagSysMatchDetailMapper;

    /**
     * 查询特征项匹配详情
     *
     * @param id 特征项匹配详情ID
     * @return 特征项匹配详情
     */

    public CtagSysMatchDetail selectCtagSysMatchDetailById(Long id)
    {
        return ctagSysMatchDetailMapper.selectCtagSysMatchDetailById(id);
    }

    /**
     * 查询特征项匹配详情列表
     *
     * @param ctagSysMatchDetail 特征项匹配详情
     * @return 特征项匹配详情
     */

    public List<CtagSysMatchDetail> selectCtagSysMatchDetailList(CtagSysMatchDetail ctagSysMatchDetail)
    {
        return ctagSysMatchDetailMapper.selectCtagSysMatchDetailList(ctagSysMatchDetail);
    }

    /**
     * 新增特征项匹配详情
     *
     * @param ctagSysMatchDetail 特征项匹配详情
     * @return 结果
     */

    public int insertCtagSysMatchDetail(CtagSysMatchDetail ctagSysMatchDetail)
    {
        return ctagSysMatchDetailMapper.insertCtagSysMatchDetail(ctagSysMatchDetail);
    }

    /**
     * 修改特征项匹配详情
     *
     * @param ctagSysMatchDetail 特征项匹配详情
     * @return 结果
     */

    public int updateCtagSysMatchDetail(CtagSysMatchDetail ctagSysMatchDetail)
    {
        return ctagSysMatchDetailMapper.updateCtagSysMatchDetail(ctagSysMatchDetail);
    }

    /**
     * 批量删除特征项匹配详情
     *
     * @param ids 需要删除的特征项匹配详情ID
     * @return 结果
     */

    public int deleteCtagSysMatchDetailByIds(Long[] ids)
    {
        return ctagSysMatchDetailMapper.deleteCtagSysMatchDetailByIds(ids);
    }

    /**
     * 删除特征项匹配详情信息
     *
     * @param id 特征项匹配详情ID
     * @return 结果
     */

    public int deleteCtagSysMatchDetailById(Long id)
    {
        return ctagSysMatchDetailMapper.deleteCtagSysMatchDetailById(id);
    }
}
