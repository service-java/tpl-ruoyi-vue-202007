package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagOssProject;

/**
 * 工程与OSS文件匹配Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagOssProjectMapper extends BaseMapper<CtagOssProject>
{
    /**
     * 查询工程与OSS文件匹配
     *
     * @param id 工程与OSS文件匹配ID
     * @return 工程与OSS文件匹配
     */
    public CtagOssProject selectCtagOssProjectById(Long id);

    /**
     * 查询工程与OSS文件匹配列表
     *
     * @param ctagOssProject 工程与OSS文件匹配
     * @return 工程与OSS文件匹配集合
     */
    public List<CtagOssProject> selectCtagOssProjectList(CtagOssProject ctagOssProject);

    /**
     * 新增工程与OSS文件匹配
     *
     * @param ctagOssProject 工程与OSS文件匹配
     * @return 结果
     */
    public int insertCtagOssProject(CtagOssProject ctagOssProject);

    /**
     * 修改工程与OSS文件匹配
     *
     * @param ctagOssProject 工程与OSS文件匹配
     * @return 结果
     */
    public int updateCtagOssProject(CtagOssProject ctagOssProject);

    /**
     * 删除工程与OSS文件匹配
     *
     * @param id 工程与OSS文件匹配ID
     * @return 结果
     */
    public int deleteCtagOssProjectById(Long id);

    /**
     * 批量删除工程与OSS文件匹配
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagOssProjectByIds(Long[] ids);
}
