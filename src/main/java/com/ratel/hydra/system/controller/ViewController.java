package com.ratel.hydra.system.controller;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.utils.WebUtil;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

//    @GetMapping("/")
//    public String redirectIndex(){
//        return  "redirect:/index";
//    }

    @GetMapping("/index")
    public String index(ModelAndView model){

         return "index";
    }

    @GetMapping("/login")
    public String login(ModelAndView model){

         return "hydra/views/login";
    }

    @GetMapping("/view/role/add/{id}")
    public String roleAddView(@PathVariable("id") Long id,ModelAndView view){
        view.addObject("data",roleService.getById(id));
        return  "view/system/role/edit";
    }
}
