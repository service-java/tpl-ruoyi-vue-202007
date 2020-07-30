package com.example.ctag.controller;

import com.example.common.annotation.Log;
import com.example.common.base.BaseController;
import com.example.common.enums.BusinessType;
import com.example.common.enums.CommonEnums;
import com.example.common.model.vo.PageVO;
import com.example.common.model.vo.ResponseVO;
import com.example.common.util.poi.ExcelUtil;
import com.example.ctag.entity.CtagProvince;
import com.example.ctag.entity.CtagSys;
import com.example.ctag.mapper.CtagSysMapper;
import com.example.ctag.service.CtagProvinceService;
import com.example.ctag.service.CtagSysService;
import com.example.ctag.util.CtagBizUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 特征体系Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/sys")
public class CtagSysController extends BaseController
{
    @Autowired
    private CtagSysService ctagSysService;

    @Autowired
    private CtagSysMapper ctagSysMapper;

    @Autowired
    private CtagProvinceService ctagProvinceService;

    /**
     * 查询特征体系列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:sys:list')")
    @GetMapping("/list")
    public PageVO list(CtagSys ctagSys)
    {
        startPage();
        List<CtagSys> list = ctagSysService.selectCtagSysList(ctagSys);
        return getDataTable(list);
    }

    /**
     * 导出特征体系列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:sys:export')")
    @Log(title = "特征体系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagSys ctagSys)
    {
        List<CtagSys> list = ctagSysService.selectCtagSysList(ctagSys);
        ExcelUtil<CtagSys> util = new ExcelUtil<CtagSys>(CtagSys.class);
        return util.exportExcel(list, "sys");
    }

    /**
     * 获取特征体系详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:sys:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagSysService.selectCtagSysById(id));
    }

    /**
     * 新增特征体系
     */
    @PreAuthorize("@ss.hasPermi('ctag:sys:add')")
    @Log(title = "特征体系", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagSys ctagSys)
    {

        // 填充信息
        CtagProvince ctagProvince = ctagProvinceService.selectCtagProvinceById(ctagSys.getProvinceId());
        ctagSys.setProvinceName(ctagProvince.getName());

        String maxCode = ctagSysMapper.getMaxSysCode(CommonEnums.CtagSysType.PM.getValue());
        if (maxCode != null) {
            String maxCodePlus = CtagBizUtils.getMaxCode(maxCode);
            ctagSys.setSysCode(maxCodePlus);
        }

        return toAjax(ctagSysService.insertCtagSys(ctagSys));
    }

    /**
     * 修改特征体系
     */
    @PreAuthorize("@ss.hasPermi('ctag:sys:edit')")
    @Log(title = "特征体系", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagSys ctagSys)
    {
        return toAjax(ctagSysService.updateCtagSys(ctagSys));
    }

    /**
     * 删除特征体系
     */
    @PreAuthorize("@ss.hasPermi('ctag:sys:remove')")
    @Log(title = "特征体系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagSysService.deleteCtagSysByIds(ids));
    }
}
