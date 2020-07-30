package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagProjectMapper;
import com.example.ctag.entity.CtagProject;
import org.springframework.stereotype.Service;


/**
 * 特征标记工程Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagProjectService extends ServiceImpl<CtagProjectMapper, CtagProject>
{
    @Autowired
    private CtagProjectMapper ctagProjectMapper;

    /**
     * 查询特征标记工程
     *
     * @param id 特征标记工程ID
     * @return 特征标记工程
     */

    public CtagProject selectCtagProjectById(Long id)
    {
        return ctagProjectMapper.selectCtagProjectById(id);
    }

    /**
     * 查询特征标记工程列表
     *
     * @param ctagProject 特征标记工程
     * @return 特征标记工程
     */

    public List<CtagProject> selectCtagProjectList(CtagProject ctagProject)
    {
        return ctagProjectMapper.selectCtagProjectList(ctagProject);
    }

    /**
     * 新增特征标记工程
     *
     * @param ctagProject 特征标记工程
     * @return 结果
     */

    public int insertCtagProject(CtagProject ctagProject)
    {
        return ctagProjectMapper.insertCtagProject(ctagProject);
    }

    /**
     * 修改特征标记工程
     *
     * @param ctagProject 特征标记工程
     * @return 结果
     */

    public int updateCtagProject(CtagProject ctagProject)
    {
        return ctagProjectMapper.updateCtagProject(ctagProject);
    }

    /**
     * 批量删除特征标记工程
     *
     * @param ids 需要删除的特征标记工程ID
     * @return 结果
     */

    public int deleteCtagProjectByIds(Long[] ids)
    {
        return ctagProjectMapper.deleteCtagProjectByIds(ids);
    }

    /**
     * 删除特征标记工程信息
     *
     * @param id 特征标记工程ID
     * @return 结果
     */

    public int deleteCtagProjectById(Long id)
    {
        return ctagProjectMapper.deleteCtagProjectById(id);
    }
}
