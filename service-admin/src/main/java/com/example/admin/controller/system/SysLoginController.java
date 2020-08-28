package com.example.admin.controller.system;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.constant.CommonConstants;
import com.example.common.model.vo.ResponseVO;
import com.example.common.model.entity.SysMenu;
import com.example.common.model.entity.SysUser;
import com.example.common.model.LoginBody;
import com.example.common.model.LoginUser;
import com.example.common.util.ServletUtils;
import com.example.framework.security.service.SysLoginService;
import com.example.framework.security.service.SysPermissionService;
import com.example.framework.security.service.TokenService;
import com.example.system.service.SysMenuService;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResponseVO login(@RequestBody LoginBody loginBody) {
        ResponseVO ajax = ResponseVO.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(CommonConstants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public ResponseVO getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        ResponseVO ajax = ResponseVO.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResponseVO getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return ResponseVO.success(menuService.buildMenus(menus));
    }
}
