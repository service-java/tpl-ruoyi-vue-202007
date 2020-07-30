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
import com.example.ctag.entity.CtagSysMatch;
import com.example.ctag.service.CtagSysMatchService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 特征项匹配Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/match")
public class CtagSysMatchController extends BaseController
{
    @Autowired
    private CtagSysMatchService ctagSysMatchService;

    /**
     * 查询特征项匹配列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:match:list')")
    @GetMapping("/list")
    public PageVO list(CtagSysMatch ctagSysMatch)
    {
        startPage();
        List<CtagSysMatch> list = ctagSysMatchService.selectCtagSysMatchList(ctagSysMatch);
        return getDataTable(list);
    }

    /**
     * 导出特征项匹配列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:match:export')")
    @Log(title = "特征项匹配", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagSysMatch ctagSysMatch)
    {
        List<CtagSysMatch> list = ctagSysMatchService.selectCtagSysMatchList(ctagSysMatch);
        ExcelUtil<CtagSysMatch> util = new ExcelUtil<CtagSysMatch>(CtagSysMatch.class);
        return util.exportExcel(list, "match");
    }

    /**
     * 获取特征项匹配详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:match:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagSysMatchService.selectCtagSysMatchById(id));
    }

    /**
     * 新增特征项匹配
     */
    @PreAuthorize("@ss.hasPermi('ctag:match:add')")
    @Log(title = "特征项匹配", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagSysMatch ctagSysMatch)
    {
        return toAjax(ctagSysMatchService.insertCtagSysMatch(ctagSysMatch));
    }

    /**
     * 修改特征项匹配
     */
    @PreAuthorize("@ss.hasPermi('ctag:match:edit')")
    @Log(title = "特征项匹配", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagSysMatch ctagSysMatch)
    {
        return toAjax(ctagSysMatchService.updateCtagSysMatch(ctagSysMatch));
    }

    /**
     * 删除特征项匹配
     */
    @PreAuthorize("@ss.hasPermi('ctag:match:remove')")
    @Log(title = "特征项匹配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagSysMatchService.deleteCtagSysMatchByIds(ids));
    }
}
