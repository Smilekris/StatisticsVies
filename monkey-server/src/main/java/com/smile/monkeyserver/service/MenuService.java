package com.smile.monkeyserver.service;

import com.smile.monkeyserver.enity.Menu;

import java.util.List;

/**
 * @Author kris
 */
public interface MenuService {

    public List<Menu> getMenuList();

    public Integer addMenu(Menu menu);

    public List<Menu> randomMenus();
}
