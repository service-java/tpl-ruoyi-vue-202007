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
import com.example.ctag.entity.CtagProvince;
import com.example.ctag.service.CtagProvinceService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 省别Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/province")
public class CtagProvinceController extends BaseController
{
    @Autowired
    private CtagProvinceService ctagProvinceService;

    /**
     * 查询省别列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:province:list')")
    @GetMapping("/pageProvince")
    public PageVO pageProvince(CtagProvince ctagProvince)
    {
        startPage();
        List<CtagProvince> list = ctagProvinceService.selectCtagProvinceList(ctagProvince);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('ctag:province:list')")
    @GetMapping("/listProvince")
    public List<CtagProvince> listProvince() {
        List<CtagProvince> list = ctagProvinceService.list();
        return list;
    }

    /**
     * 导出省别列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:province:export')")
    @Log(title = "省别", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagProvince ctagProvince)
    {
        List<CtagProvince> list = ctagProvinceService.selectCtagProvinceList(ctagProvince);
        ExcelUtil<CtagProvince> util = new ExcelUtil<CtagProvince>(CtagProvince.class);
        return util.exportExcel(list, "province");
    }

    /**
     * 获取省别详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:province:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagProvinceService.selectCtagProvinceById(id));
    }

    /**
     * 新增省别
     */
    @PreAuthorize("@ss.hasPermi('ctag:province:add')")
    @Log(title = "省别", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagProvince ctagProvince)
    {
        return toAjax(ctagProvinceService.insertCtagProvince(ctagProvince));
    }

    /**
     * 修改省别
     */
    @PreAuthorize("@ss.hasPermi('ctag:province:edit')")
    @Log(title = "省别", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagProvince ctagProvince)
    {
        return toAjax(ctagProvinceService.updateCtagProvince(ctagProvince));
    }

    /**
     * 删除省别
     */
    @PreAuthorize("@ss.hasPermi('ctag:province:remove')")
    @Log(title = "省别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagProvinceService.deleteCtagProvinceByIds(ids));
    }
}
