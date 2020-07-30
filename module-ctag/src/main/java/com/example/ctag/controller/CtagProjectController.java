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
import com.example.ctag.entity.CtagProject;
import com.example.ctag.service.CtagProjectService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 特征标记工程Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/project")
public class CtagProjectController extends BaseController
{
    @Autowired
    private CtagProjectService ctagProjectService;

    /**
     * 查询特征标记工程列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:project:list')")
    @GetMapping("/list")
    public PageVO list(CtagProject ctagProject)
    {
        startPage();
        List<CtagProject> list = ctagProjectService.selectCtagProjectList(ctagProject);
        return getDataTable(list);
    }

    /**
     * 导出特征标记工程列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:project:export')")
    @Log(title = "特征标记工程", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagProject ctagProject)
    {
        List<CtagProject> list = ctagProjectService.selectCtagProjectList(ctagProject);
        ExcelUtil<CtagProject> util = new ExcelUtil<CtagProject>(CtagProject.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 获取特征标记工程详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:project:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagProjectService.selectCtagProjectById(id));
    }

    /**
     * 新增特征标记工程
     */
    @PreAuthorize("@ss.hasPermi('ctag:project:add')")
    @Log(title = "特征标记工程", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagProject ctagProject)
    {
        return toAjax(ctagProjectService.insertCtagProject(ctagProject));
    }

    /**
     * 修改特征标记工程
     */
    @PreAuthorize("@ss.hasPermi('ctag:project:edit')")
    @Log(title = "特征标记工程", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagProject ctagProject)
    {
        return toAjax(ctagProjectService.updateCtagProject(ctagProject));
    }

    /**
     * 删除特征标记工程
     */
    @PreAuthorize("@ss.hasPermi('ctag:project:remove')")
    @Log(title = "特征标记工程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagProjectService.deleteCtagProjectByIds(ids));
    }
}
