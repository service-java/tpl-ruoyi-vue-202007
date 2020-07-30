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
import com.example.ctag.entity.CtagSysItem;
import com.example.ctag.service.CtagSysItemService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 特征项Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/sysitem")
public class CtagSysItemController extends BaseController
{
    @Autowired
    private CtagSysItemService ctagSysItemService;

    /**
     * 查询特征项列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:sysitem:list')")
    @GetMapping("/list")
    public PageVO list(CtagSysItem ctagSysItem)
    {
        startPage();
        List<CtagSysItem> list = ctagSysItemService.selectCtagSysItemList(ctagSysItem);
        return getDataTable(list);
    }

    /**
     * 导出特征项列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:sysitem:export')")
    @Log(title = "特征项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagSysItem ctagSysItem)
    {
        List<CtagSysItem> list = ctagSysItemService.selectCtagSysItemList(ctagSysItem);
        ExcelUtil<CtagSysItem> util = new ExcelUtil<CtagSysItem>(CtagSysItem.class);
        return util.exportExcel(list, "sysitem");
    }

    /**
     * 获取特征项详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:sysitem:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagSysItemService.selectCtagSysItemById(id));
    }

    /**
     * 新增特征项
     */
    @PreAuthorize("@ss.hasPermi('ctag:sysitem:add')")
    @Log(title = "特征项", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagSysItem ctagSysItem)
    {
        return toAjax(ctagSysItemService.insertCtagSysItem(ctagSysItem));
    }

    /**
     * 修改特征项
     */
    @PreAuthorize("@ss.hasPermi('ctag:sysitem:edit')")
    @Log(title = "特征项", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagSysItem ctagSysItem)
    {
        return toAjax(ctagSysItemService.updateCtagSysItem(ctagSysItem));
    }

    /**
     * 删除特征项
     */
    @PreAuthorize("@ss.hasPermi('ctag:sysitem:remove')")
    @Log(title = "特征项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagSysItemService.deleteCtagSysItemByIds(ids));
    }
}
