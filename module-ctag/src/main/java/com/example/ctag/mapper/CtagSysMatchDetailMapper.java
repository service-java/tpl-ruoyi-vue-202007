package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagSysMatchDetail;

/**
 * 特征项匹配详情Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagSysMatchDetailMapper extends BaseMapper<CtagSysMatchDetail>
{
    /**
     * 查询特征项匹配详情
     *
     * @param id 特征项匹配详情ID
     * @return 特征项匹配详情
     */
    public CtagSysMatchDetail selectCtagSysMatchDetailById(Long id);

    /**
     * 查询特征项匹配详情列表
     *
     * @param ctagSysMatchDetail 特征项匹配详情
     * @return 特征项匹配详情集合
     */
    public List<CtagSysMatchDetail> selectCtagSysMatchDetailList(CtagSysMatchDetail ctagSysMatchDetail);

    /**
     * 新增特征项匹配详情
     *
     * @param ctagSysMatchDetail 特征项匹配详情
     * @return 结果
     */
    public int insertCtagSysMatchDetail(CtagSysMatchDetail ctagSysMatchDetail);

    /**
     * 修改特征项匹配详情
     *
     * @param ctagSysMatchDetail 特征项匹配详情
     * @return 结果
     */
    public int updateCtagSysMatchDetail(CtagSysMatchDetail ctagSysMatchDetail);

    /**
     * 删除特征项匹配详情
     *
     * @param id 特征项匹配详情ID
     * @return 结果
     */
    public int deleteCtagSysMatchDetailById(Long id);

    /**
     * 批量删除特征项匹配详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagSysMatchDetailByIds(Long[] ids);
}
