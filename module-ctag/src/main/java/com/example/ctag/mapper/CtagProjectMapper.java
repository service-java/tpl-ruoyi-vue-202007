package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagProject;

/**
 * 特征标记工程Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagProjectMapper extends BaseMapper<CtagProject>
{
    /**
     * 查询特征标记工程
     *
     * @param id 特征标记工程ID
     * @return 特征标记工程
     */
    public CtagProject selectCtagProjectById(Long id);

    /**
     * 查询特征标记工程列表
     *
     * @param ctagProject 特征标记工程
     * @return 特征标记工程集合
     */
    public List<CtagProject> selectCtagProjectList(CtagProject ctagProject);

    /**
     * 新增特征标记工程
     *
     * @param ctagProject 特征标记工程
     * @return 结果
     */
    public int insertCtagProject(CtagProject ctagProject);

    /**
     * 修改特征标记工程
     *
     * @param ctagProject 特征标记工程
     * @return 结果
     */
    public int updateCtagProject(CtagProject ctagProject);

    /**
     * 删除特征标记工程
     *
     * @param id 特征标记工程ID
     * @return 结果
     */
    public int deleteCtagProjectById(Long id);

    /**
     * 批量删除特征标记工程
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagProjectByIds(Long[] ids);
}
