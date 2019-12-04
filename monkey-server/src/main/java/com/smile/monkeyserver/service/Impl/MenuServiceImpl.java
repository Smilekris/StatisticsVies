package com.smile.monkeyserver.service.Impl;

import com.smile.monkeyserver.enity.Menu;
import com.smile.monkeyserver.mapper.MenuMapper;
import com.smile.monkeyserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @ClassName MenuServiceImpl
 * @Author kris
 * @Date 2019/11/29
 **/

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menus = menuMapper.menus();
        return menus;
    }

    @Override
    public Integer addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    /**
     * Knuth-Durstenfeld Shuffle算法-在Fisher-Yates Shuffle基础上修改
     * 1.假设有数组n
     * 2.还剩k个没选择，则从[0,k)之间选取一个p，就是取k中第p个
     * 3.重复第二步
     * <p>
     * Knuth-Durstenfeld Shuffle算法对Fisher-Yates Shuffle进行优化，把O(n*n)空间复杂度优化至O(n)
     *
     * @return
     */
    @Override
    public List<Menu> randomMenus() {
        List<Menu> menus = menuMapper.menus();
        Integer selectNum = menus.size() / 3;
        Menu[] menuArrs =  menus.toArray(new Menu[menus.size()]);
        Integer pointer = 0;
        int length = menuArrs.length;
        List<Menu> results = new ArrayList<>();
        while (selectNum > pointer) {
            int randomPoint = ThreadLocalRandom.current().nextInt(0,  length- pointer);
            Menu menu = null;
            menu = menuArrs[randomPoint];
            menuArrs[randomPoint] = menuArrs[length-1-pointer];
            menuArrs[length-1-pointer] = menu;
            results.add(menu);
            pointer++;
        }
        return results;
    }

}
