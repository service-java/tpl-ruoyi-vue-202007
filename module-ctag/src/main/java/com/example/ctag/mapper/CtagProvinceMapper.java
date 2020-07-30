package com.example.ctag.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ctag.entity.CtagProvince;

/**
 * 省别Mapper接口
 *
 * @author example
 * @date 2020-07-29
 */
public interface CtagProvinceMapper extends BaseMapper<CtagProvince>
{
    /**
     * 查询省别
     *
     * @param id 省别ID
     * @return 省别
     */
    public CtagProvince selectCtagProvinceById(Long id);

    /**
     * 查询省别列表
     *
     * @param ctagProvince 省别
     * @return 省别集合
     */
    public List<CtagProvince> selectCtagProvinceList(CtagProvince ctagProvince);

    /**
     * 新增省别
     *
     * @param ctagProvince 省别
     * @return 结果
     */
    public int insertCtagProvince(CtagProvince ctagProvince);

    /**
     * 修改省别
     *
     * @param ctagProvince 省别
     * @return 结果
     */
    public int updateCtagProvince(CtagProvince ctagProvince);

    /**
     * 删除省别
     *
     * @param id 省别ID
     * @return 结果
     */
    public int deleteCtagProvinceById(Long id);

    /**
     * 批量删除省别
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCtagProvinceByIds(Long[] ids);
}
