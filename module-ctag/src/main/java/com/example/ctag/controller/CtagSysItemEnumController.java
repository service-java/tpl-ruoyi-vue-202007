package com.example.ctag.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.annotation.Log;
import com.example.common.base.BaseController;
import com.example.common.model.vo.ResponseVO;
import com.example.common.enums.BusinessType;
import com.example.ctag.entity.CtagSysItemEnum;
import com.example.ctag.service.CtagSysItemEnumService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 特征项枚举Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/enum")
public class CtagSysItemEnumController extends BaseController
{
    @Autowired
    private CtagSysItemEnumService ctagSysItemEnumService;

    /**
     * 查询特征项枚举列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:enum:list')")
    @GetMapping("/list")
    public PageVO list(CtagSysItemEnum ctagSysItemEnum)
    {
        startPage();
        List<CtagSysItemEnum> list = ctagSysItemEnumService.selectCtagSysItemEnumList(ctagSysItemEnum);
        return getDataTable(list);
    }

    /**
     * 导出特征项枚举列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:enum:export')")
    @Log(title = "特征项枚举", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagSysItemEnum ctagSysItemEnum)
    {
        List<CtagSysItemEnum> list = ctagSysItemEnumService.selectCtagSysItemEnumList(ctagSysItemEnum);
        ExcelUtil<CtagSysItemEnum> util = new ExcelUtil<CtagSysItemEnum>(CtagSysItemEnum.class);
        return util.exportExcel(list, "enum");
    }

    /**
     * 获取特征项枚举详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:enum:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagSysItemEnumService.selectCtagSysItemEnumById(id));
    }

    /**
     * 新增特征项枚举
     */
    @PreAuthorize("@ss.hasPermi('ctag:enum:add')")
    @Log(title = "特征项枚举", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagSysItemEnum ctagSysItemEnum)
    {
        return toAjax(ctagSysItemEnumService.insertCtagSysItemEnum(ctagSysItemEnum));
    }

    /**
     * 修改特征项枚举
     */
    @PreAuthorize("@ss.hasPermi('ctag:enum:edit')")
    @Log(title = "特征项枚举", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagSysItemEnum ctagSysItemEnum)
    {
        return toAjax(ctagSysItemEnumService.updateCtagSysItemEnum(ctagSysItemEnum));
    }

    /**
     * 删除特征项枚举
     */
    @PreAuthorize("@ss.hasPermi('ctag:enum:remove')")
    @Log(title = "特征项枚举", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagSysItemEnumService.deleteCtagSysItemEnumByIds(ids));
    }
}
