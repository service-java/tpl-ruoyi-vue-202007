package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.core.domain.entity.SysDictData;
import com.example.common.utils.DictUtils;
import com.example.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDictDataService extends ServiceImpl<SysDictDataMapper, SysDictData>  {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */

    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */

    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */

    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */

    public int deleteDictDataByIds(Long[] dictCodes) {
        int row = dictDataMapper.deleteDictDataByIds(dictCodes);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */

    public int insertDictData(SysDictData dictData) {
        int row = dictDataMapper.insertDictData(dictData);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */

    public int updateDictData(SysDictData dictData) {
        int row = dictDataMapper.updateDictData(dictData);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }
}
