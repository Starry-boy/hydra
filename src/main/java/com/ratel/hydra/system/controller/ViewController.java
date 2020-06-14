package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ratel
 * @date 2020-05-25
 */
@Slf4j
@Controller
@RequestMapping
public class ViewController extends BaseController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String redirectIndex(){
        return  WebUtil.getRedirectUrl("/index");
    }

    @GetMapping("/index")
    public String index(ModelAndView model){
         return "index";
    }

    @GetMapping("/login")
    public String login(ModelAndView model){

         return "/login";
    }

    @GetMapping("/view/role/{id}")
    public ModelAndView roleGetById(@PathVariable("id") Long id, ModelAndView view){
        view.addObject("data",roleService.baseGetById(id));
        view.setViewName("view/system/role/edit");
        return  view;
    }

    @GetMapping("/view/role/add")
    public String roleAddView(){
        return  "view/system/role/edit/add";
    }

    @GetMapping("/view/user/{id}")
    public ModelAndView userGetById(@PathVariable("id") Long id, ModelAndView view){
        view.addObject("data",userService.baseGetById(id));
        view.setViewName("view/system/user/edit");
        return  view;
    }

    @GetMapping("/view/user/add")
    public String userAddView(){
        return  "view/system/role/user/add";
    }
}