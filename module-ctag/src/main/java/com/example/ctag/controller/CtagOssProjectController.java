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
import com.example.ctag.entity.CtagOssProject;
import com.example.ctag.service.CtagOssProjectService;
import com.example.common.util.poi.ExcelUtil;
import com.example.common.model.vo.PageVO;

/**
 * 工程与OSS文件匹配Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/ctag/ossproject")
public class CtagOssProjectController extends BaseController
{
    @Autowired
    private CtagOssProjectService ctagOssProjectService;

    /**
     * 查询工程与OSS文件匹配列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:ossproject:list')")
    @GetMapping("/list")
    public PageVO list(CtagOssProject ctagOssProject)
    {
        startPage();
        List<CtagOssProject> list = ctagOssProjectService.selectCtagOssProjectList(ctagOssProject);
        return getDataTable(list);
    }

    /**
     * 导出工程与OSS文件匹配列表
     */
    @PreAuthorize("@ss.hasPermi('ctag:ossproject:export')")
    @Log(title = "工程与OSS文件匹配", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseVO export(CtagOssProject ctagOssProject)
    {
        List<CtagOssProject> list = ctagOssProjectService.selectCtagOssProjectList(ctagOssProject);
        ExcelUtil<CtagOssProject> util = new ExcelUtil<CtagOssProject>(CtagOssProject.class);
        return util.exportExcel(list, "ossproject");
    }

    /**
     * 获取工程与OSS文件匹配详细信息
     */
    @PreAuthorize("@ss.hasPermi('ctag:ossproject:query')")
    @GetMapping(value = "/{id}")
    public ResponseVO getInfo(@PathVariable("id") Long id)
    {
        return ResponseVO.success(ctagOssProjectService.selectCtagOssProjectById(id));
    }

    /**
     * 新增工程与OSS文件匹配
     */
    @PreAuthorize("@ss.hasPermi('ctag:ossproject:add')")
    @Log(title = "工程与OSS文件匹配", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseVO add(@RequestBody CtagOssProject ctagOssProject)
    {
        return toAjax(ctagOssProjectService.insertCtagOssProject(ctagOssProject));
    }

    /**
     * 修改工程与OSS文件匹配
     */
    @PreAuthorize("@ss.hasPermi('ctag:ossproject:edit')")
    @Log(title = "工程与OSS文件匹配", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseVO edit(@RequestBody CtagOssProject ctagOssProject)
    {
        return toAjax(ctagOssProjectService.updateCtagOssProject(ctagOssProject));
    }

    /**
     * 删除工程与OSS文件匹配
     */
    @PreAuthorize("@ss.hasPermi('ctag:ossproject:remove')")
    @Log(title = "工程与OSS文件匹配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseVO remove(@PathVariable Long[] ids)
    {
        return toAjax(ctagOssProjectService.deleteCtagOssProjectByIds(ids));
    }
}
