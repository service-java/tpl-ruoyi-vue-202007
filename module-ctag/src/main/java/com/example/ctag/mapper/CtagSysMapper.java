package com.example.ctag.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagSys;
import org.apache.ibatis.annotations.Param;

/**
 * 特征体系Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagSysMapper extends BaseMapper<CtagSys> {
    /**
     * 查询特征体系
     *
     * @param id 特征体系ID
     * @return 特征体系
     */
    public CtagSys selectCtagSysById(Long id);

    /**
     * 查询特征体系列表
     *
     * @param ctagSys 特征体系
     * @return 特征体系集合
     */
    public List<CtagSys> selectCtagSysList(CtagSys ctagSys);

    /**
     * 新增特征体系
     *
     * @param ctagSys 特征体系
     * @return 结果
     */
    public int insertCtagSys(CtagSys ctagSys);

    /**
     * 修改特征体系
     *
     * @param ctagSys 特征体系
     * @return 结果
     */
    public int updateCtagSys(CtagSys ctagSys);

    /**
     * 删除特征体系
     *
     * @param id 特征体系ID
     * @return 结果
     */
    public int deleteCtagSysById(Long id);

    /**
     * 批量删除特征体系
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagSysByIds(Long[] ids);

    public String getMaxSysCode(@Param("type") Integer type);
}
