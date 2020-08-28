package com.example.admin.controller.system;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.common.annotation.Log;
import com.example.common.properties.CommonProperties;
import com.example.common.base.BaseController;
import com.example.common.model.vo.ResponseVO;
import com.example.common.model.entity.SysUser;
import com.example.common.model.LoginUser;
import com.example.common.enums.BusinessTypeEnums;
import com.example.common.util.SecurityUtils;
import com.example.common.util.ServletUtils;
import com.example.common.util.file.FileUploadUtils;
import com.example.framework.security.service.TokenService;
import com.example.system.service.SysUserService;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    public ResponseVO profile() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        ResponseVO ajax = ResponseVO.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessTypeEnums.UPDATE)
    @PutMapping
    public ResponseVO updateProfile(@RequestBody SysUser user) {
        if (userService.updateUserProfile(user) > 0) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            // 更新缓存用户信息
            loginUser.getUser().setNickName(user.getNickName());
            loginUser.getUser().setPhonenumber(user.getPhonenumber());
            loginUser.getUser().setEmail(user.getEmail());
            loginUser.getUser().setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return ResponseVO.success();
        }
        return ResponseVO.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessTypeEnums.UPDATE)
    @PutMapping("/updatePwd")
    public ResponseVO updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return ResponseVO.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return ResponseVO.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return ResponseVO.success();
        }
        return ResponseVO.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessTypeEnums.UPDATE)
    @PostMapping("/avatar")
    public ResponseVO avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            String avatar = FileUploadUtils.upload(CommonProperties.getAvatarPath(), file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
                ResponseVO ajax = ResponseVO.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return ResponseVO.error("上传图片异常，请联系管理员");
    }
}
