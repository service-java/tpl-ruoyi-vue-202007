package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagSysMatch;

/**
 * 特征项匹配Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagSysMatchMapper extends BaseMapper<CtagSysMatch>
{
    /**
     * 查询特征项匹配
     *
     * @param id 特征项匹配ID
     * @return 特征项匹配
     */
    public CtagSysMatch selectCtagSysMatchById(Long id);

    /**
     * 查询特征项匹配列表
     *
     * @param ctagSysMatch 特征项匹配
     * @return 特征项匹配集合
     */
    public List<CtagSysMatch> selectCtagSysMatchList(CtagSysMatch ctagSysMatch);

    /**
     * 新增特征项匹配
     *
     * @param ctagSysMatch 特征项匹配
     * @return 结果
     */
    public int insertCtagSysMatch(CtagSysMatch ctagSysMatch);

    /**
     * 修改特征项匹配
     *
     * @param ctagSysMatch 特征项匹配
     * @return 结果
     */
    public int updateCtagSysMatch(CtagSysMatch ctagSysMatch);

    /**
     * 删除特征项匹配
     *
     * @param id 特征项匹配ID
     * @return 结果
     */
    public int deleteCtagSysMatchById(Long id);

    /**
     * 批量删除特征项匹配
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagSysMatchByIds(Long[] ids);
}
