package com.echo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlCustomer;
import com.echo.pojo.zjlModelView;
import com.echo.pojo.zjlRole;
import com.echo.service.ZjlRoleService;
import com.echo.service.impl.ZjlAccountServiceImpl;
import com.echo.service.impl.ZjlResourceShowServiceImpl;
import com.echo.service.impl.ZjlRoleServiceImpl;
import com.echo.utils.ZjlRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("role")
public class ZjlRoleController {

    @Autowired
    private ZjlRoleServiceImpl zjlRoleService;

    @Autowired
    private ZjlResourceShowServiceImpl zjlResourceShowService;

    @Autowired
    private ZjlAccountServiceImpl zjlAccountService;

    //如果是返回信息给前端人员使用
    @RequestMapping("toList")
    public String toRoleModel() {
        return "role/roleList";
    }

    @RequestMapping("list")
    @ResponseBody
    public R<HashMap<String, Object>> RoleList(String roleName, Long page, Long limit) {
        LambdaQueryWrapper<zjlRole> query = Wrappers.<zjlRole>lambdaQuery();

        query.like(StringUtils.isNotBlank(roleName), zjlRole::getRoleName, roleName)
                .orderByDesc(zjlRole::getRoleId);

        Page<zjlRole> Rolepage = new Page<>(page, limit);
        Page<zjlRole> zjlRoles = zjlRoleService.page(Rolepage, query);

        return ZjlRUtils.getCountRecords(zjlRoles);
    }

    @RequestMapping("toAdd")
    public String toRoleAdd() {
        return "role/roleAdd";
    }

    @GetMapping("models")
    @ResponseBody
    public R<List<zjlModelView>> modelList() {
        return R.ok(zjlResourceShowService.showModel());
    }


    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody zjlRole zjlRole) {


        //将共性的功能抽取成方法的快捷键
        boolean sucess = zjlRoleService.addRole(zjlRole);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toUpdate/{id}")
    public String toRoleUpdate(@PathVariable Long id, Model model) {
        //根据id查询客户的信息
        zjlRole role = zjlRoleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleUpdate";
    }

    @GetMapping("updateModels/{roleId}")
    @ResponseBody
    public R<List<zjlModelView>> updateModels(@PathVariable Long roleId) {
        List<zjlModelView> zjlModelViews = zjlResourceShowService.lookUpdateModels(roleId);

        return R.ok(zjlModelViews);
    }

    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody zjlRole zjlRole){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean success= zjlRoleService.updateRole(zjlRole);

        return ZjlRUtils.rightOrnot(success);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        Integer count = zjlAccountService.lambdaQuery().eq(zjlAccount::getZjlRoleId, id).count();
        if (count >1){
            return R.failed("你当前登录的账号里面含有这个权限,你不能删除你自己的权限");
        }

        boolean sucess = zjlRoleService.removeById(id);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toDetail/{id}")
    public String toCustomerDetail(@PathVariable Long id, Model model){
        //根据id查询客户的信息
        zjlRole role = zjlRoleService.getById(id);
        model.addAttribute("role",role);
        return "role/roleDetail";
    }
}
