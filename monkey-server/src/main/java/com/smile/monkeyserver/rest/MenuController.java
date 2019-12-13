package com.smile.monkeyserver.rest;

import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.enity.Menu;
import com.smile.monkeyserver.exception.MonkeyException;
import com.smile.monkeyserver.service.MenuService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MenuController
 * @Author kris
 * @Date 2019/12/12
 **/
@RestController
@RequestMapping("/big")
@Api(tags = {"big什么接口"},value = "big什么接口")
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public ResponseResult menu(){
        List<Menu> menuList = menuService.getMenuList();

        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(menuList);
    }

    @PostMapping("/menu")
    public ResponseResult addMenu(@RequestBody Menu menu){
        if(null == menu){
            throw new MonkeyException(ResponseResult.getFAIL(),"菜单为空");
        }
        if(null == menu.menuName){
            throw new MonkeyException(ResponseResult.getFAIL(),"菜单名为空");
        }

        menu.setCreateTime(new Date());
        Integer addMenuNum = menuService.addMenu(menu);
        if(addMenuNum > 0){
            return ResponseResult.ResultHelper.successInstance().setMsg("ok");
        }else {
            throw new MonkeyException(ResponseResult.getFAIL(),"添加菜单失败");
        }
    }

    @GetMapping("/random-menus")
    public ResponseResult randomMenu(){
        List<Menu> menuList = menuService.randomMenus();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(menuList);
    }
}
