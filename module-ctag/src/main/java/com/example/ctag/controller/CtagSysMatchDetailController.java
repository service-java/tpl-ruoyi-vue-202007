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
import com.example.ctag.entity.CtagSysMatchDetail;
import com.example.ctag.service.CtagSysMatchDetailService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 特征项匹配详情Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/detail")
public class CtagSysMatchDetailController extends BaseController
{
    @Autowired
    private CtagSysMatchDetailService ctagSysMatchDetailService;

    /**
     * 查询特征项匹配详情列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:detail:list')")
    @GetMapping("/list")
    public PageVO list(CtagSysMatchDetail ctagSysMatchDetail)
    {
        startPage();
        List<CtagSysMatchDetail> list = ctagSysMatchDetailService.selectCtagSysMatchDetailList(ctagSysMatchDetail);
        return getDataTable(list);
    }

    /**
     * 导出特征项匹配详情列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:detail:export')")
    @Log(title = "特征项匹配详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagSysMatchDetail ctagSysMatchDetail)
    {
        List<CtagSysMatchDetail> list = ctagSysMatchDetailService.selectCtagSysMatchDetailList(ctagSysMatchDetail);
        ExcelUtil<CtagSysMatchDetail> util = new ExcelUtil<CtagSysMatchDetail>(CtagSysMatchDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 获取特征项匹配详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:detail:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagSysMatchDetailService.selectCtagSysMatchDetailById(id));
    }

    /**
     * 新增特征项匹配详情
     */
    @PreAuthorize("@ss.hasPermi('ctag:detail:add')")
    @Log(title = "特征项匹配详情", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagSysMatchDetail ctagSysMatchDetail)
    {
        return toAjax(ctagSysMatchDetailService.insertCtagSysMatchDetail(ctagSysMatchDetail));
    }

    /**
     * 修改特征项匹配详情
     */
    @PreAuthorize("@ss.hasPermi('ctag:detail:edit')")
    @Log(title = "特征项匹配详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagSysMatchDetail ctagSysMatchDetail)
    {
        return toAjax(ctagSysMatchDetailService.updateCtagSysMatchDetail(ctagSysMatchDetail));
    }

    /**
     * 删除特征项匹配详情
     */
    @PreAuthorize("@ss.hasPermi('ctag:detail:remove')")
    @Log(title = "特征项匹配详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagSysMatchDetailService.deleteCtagSysMatchDetailByIds(ids));
    }
}
