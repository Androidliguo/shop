package com.lg.shop.test;

import com.lg.shop.util.DaoUtil;

public class BaseTest {
    public BaseTest(){
    	DaoUtil.diObject(this);
    }
}
