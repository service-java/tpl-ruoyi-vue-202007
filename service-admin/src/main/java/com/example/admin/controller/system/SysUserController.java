package com.example.admin.controller.system;

import com.example.common.annotation.Log;
import com.example.common.constant.UserConstants;
import com.example.common.base.BaseController;
import com.example.common.model.vo.ResponseVO;
import com.example.common.model.entity.SysRole;
import com.example.common.model.entity.SysUser;
import com.example.common.model.LoginUser;
import com.example.common.model.vo.PageVO;
import com.example.common.enums.BusinessTypeEnums;
import com.example.common.util.SecurityUtils;
import com.example.common.util.ServletUtils;
import com.example.common.util.StringUtils;
import com.example.common.util.poi.ExcelUtils;
import com.example.framework.security.service.TokenService;
import com.example.system.service.SysPostService;
import com.example.system.service.SysRoleService;
import com.example.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPostService postService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public PageVO list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessTypeEnums.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public ResponseVO export(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtils<SysUser> util = new ExcelUtils<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessTypeEnums.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public ResponseVO importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtils<SysUser> util = new ExcelUtils<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return ResponseVO.success(message);
    }

    @GetMapping("/importTemplate")
    public ResponseVO importTemplate() {
        ExcelUtils<SysUser> util = new ExcelUtils<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public ResponseVO getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        ResponseVO ajax = ResponseVO.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(ResponseVO.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessTypeEnums.INSERT)
    @PostMapping
    public ResponseVO add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return ResponseVO.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return ResponseVO.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return ResponseVO.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessTypeEnums.UPDATE)
    @PutMapping
    public ResponseVO edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return ResponseVO.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return ResponseVO.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessTypeEnums.DELETE)
    @DeleteMapping("/{userIds}")
    public ResponseVO remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessTypeEnums.UPDATE)
    @PutMapping("/resetPwd")
    public ResponseVO resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessTypeEnums.UPDATE)
    @PutMapping("/changeStatus")
    public ResponseVO changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
}
