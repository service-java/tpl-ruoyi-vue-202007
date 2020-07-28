package com.example.demo.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.DemoMiscMapper;
import com.example.demo.entity.DemoMisc;


/**
 * 示例模块Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class DemoMiscService extends ServiceImpl<DemoMiscMapper, DemoMisc>
{
    @Autowired
    private DemoMiscMapper demoMiscMapper;

    /**
     * 查询示例模块
     *
     * @param id 示例模块ID
     * @return 示例模块
     */

    public DemoMisc selectDemoMiscById(Long id)
    {
        return demoMiscMapper.selectDemoMiscById(id);
    }

    /**
     * 查询示例模块列表
     *
     * @param demoMisc 示例模块
     * @return 示例模块
     */

    public List<DemoMisc> selectDemoMiscList(DemoMisc demoMisc)
    {
        return demoMiscMapper.selectDemoMiscList(demoMisc);
    }

    /**
     * 新增示例模块
     *
     * @param demoMisc 示例模块
     * @return 结果
     */

    public int insertDemoMisc(DemoMisc demoMisc)
    {
        return demoMiscMapper.insertDemoMisc(demoMisc);
    }

    /**
     * 修改示例模块
     *
     * @param demoMisc 示例模块
     * @return 结果
     */

    public int updateDemoMisc(DemoMisc demoMisc)
    {
        return demoMiscMapper.updateDemoMisc(demoMisc);
    }

    /**
     * 批量删除示例模块
     *
     * @param ids 需要删除的示例模块ID
     * @return 结果
     */

    public int deleteDemoMiscByIds(Long[] ids)
    {
        return demoMiscMapper.deleteDemoMiscByIds(ids);
    }

    /**
     * 删除示例模块信息
     *
     * @param id 示例模块ID
     * @return 结果
     */

    public int deleteDemoMiscById(Long id)
    {
        return demoMiscMapper.deleteDemoMiscById(id);
    }
}
