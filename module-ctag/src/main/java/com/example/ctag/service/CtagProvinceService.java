package com.example.ctag.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ctag.mapper.CtagProvinceMapper;
import com.example.ctag.entity.CtagProvince;
import org.springframework.stereotype.Service;


/**
 * 省别Service业务层处理
 *
 * @author example
 * @date 2020-07-29
 */
@Service
public class CtagProvinceService extends ServiceImpl<CtagProvinceMapper, CtagProvince>
{
    @Autowired
    private CtagProvinceMapper ctagProvinceMapper;

    /**
     * 查询省别
     *
     * @param id 省别ID
     * @return 省别
     */

    public CtagProvince selectCtagProvinceById(Long id)
    {
        return ctagProvinceMapper.selectCtagProvinceById(id);
    }

    /**
     * 查询省别列表
     *
     * @param ctagProvince 省别
     * @return 省别
     */

    public List<CtagProvince> selectCtagProvinceList(CtagProvince ctagProvince)
    {
        return ctagProvinceMapper.selectCtagProvinceList(ctagProvince);
    }

    /**
     * 新增省别
     *
     * @param ctagProvince 省别
     * @return 结果
     */

    public int insertCtagProvince(CtagProvince ctagProvince)
    {
        return ctagProvinceMapper.insertCtagProvince(ctagProvince);
    }

    /**
     * 修改省别
     *
     * @param ctagProvince 省别
     * @return 结果
     */

    public int updateCtagProvince(CtagProvince ctagProvince)
    {
        return ctagProvinceMapper.updateCtagProvince(ctagProvince);
    }

    /**
     * 批量删除省别
     *
     * @param ids 需要删除的省别ID
     * @return 结果
     */

    public int deleteCtagProvinceByIds(Long[] ids)
    {
        return ctagProvinceMapper.deleteCtagProvinceByIds(ids);
    }

    /**
     * 删除省别信息
     *
     * @param id 省别ID
     * @return 结果
     */

    public int deleteCtagProvinceById(Long id)
    {
        return ctagProvinceMapper.deleteCtagProvinceById(id);
    }
}
