package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagOssProjectMapper;
import com.example.ctag.entity.CtagOssProject;
import org.springframework.stereotype.Service;


/**
 * 工程与OSS文件匹配Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagOssProjectService extends ServiceImpl<CtagOssProjectMapper, CtagOssProject>
{
    @Autowired
    private CtagOssProjectMapper ctagOssProjectMapper;

    /**
     * 查询工程与OSS文件匹配
     *
     * @param id 工程与OSS文件匹配ID
     * @return 工程与OSS文件匹配
     */

    public CtagOssProject selectCtagOssProjectById(Long id)
    {
        return ctagOssProjectMapper.selectCtagOssProjectById(id);
    }

    /**
     * 查询工程与OSS文件匹配列表
     *
     * @param ctagOssProject 工程与OSS文件匹配
     * @return 工程与OSS文件匹配
     */

    public List<CtagOssProject> selectCtagOssProjectList(CtagOssProject ctagOssProject)
    {
        return ctagOssProjectMapper.selectCtagOssProjectList(ctagOssProject);
    }

    /**
     * 新增工程与OSS文件匹配
     *
     * @param ctagOssProject 工程与OSS文件匹配
     * @return 结果
     */

    public int insertCtagOssProject(CtagOssProject ctagOssProject)
    {
        return ctagOssProjectMapper.insertCtagOssProject(ctagOssProject);
    }

    /**
     * 修改工程与OSS文件匹配
     *
     * @param ctagOssProject 工程与OSS文件匹配
     * @return 结果
     */

    public int updateCtagOssProject(CtagOssProject ctagOssProject)
    {
        return ctagOssProjectMapper.updateCtagOssProject(ctagOssProject);
    }

    /**
     * 批量删除工程与OSS文件匹配
     *
     * @param ids 需要删除的工程与OSS文件匹配ID
     * @return 结果
     */

    public int deleteCtagOssProjectByIds(Long[] ids)
    {
        return ctagOssProjectMapper.deleteCtagOssProjectByIds(ids);
    }

    /**
     * 删除工程与OSS文件匹配信息
     *
     * @param id 工程与OSS文件匹配ID
     * @return 结果
     */

    public int deleteCtagOssProjectById(Long id)
    {
        return ctagOssProjectMapper.deleteCtagOssProjectById(id);
    }
}
