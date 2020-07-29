package com.example.demo.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.DemoMisc;

/**
 * 示例模块Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface DemoMiscMapper extends BaseMapper<DemoMisc>
{
    /**
     * 查询示例模块
     *
     * @param id 示例模块ID
     * @return 示例模块
     */
    public DemoMisc selectDemoMiscById(Long id);

    /**
     * 查询示例模块列表
     *
     * @param demoMisc 示例模块
     * @return 示例模块集合
     */
    public List<DemoMisc> selectDemoMiscList(DemoMisc demoMisc);

    /**
     * 新增示例模块
     *
     * @param demoMisc 示例模块
     * @return 结果
     */
    public int insertDemoMisc(DemoMisc demoMisc);

    /**
     * 修改示例模块
     *
     * @param demoMisc 示例模块
     * @return 结果
     */
    public int updateDemoMisc(DemoMisc demoMisc);

    /**
     * 删除示例模块
     *
     * @param id 示例模块ID
     * @return 结果
     */
    public int deleteDemoMiscById(Long id);

    /**
     * 批量删除示例模块
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDemoMiscByIds(Long[] ids);
}
