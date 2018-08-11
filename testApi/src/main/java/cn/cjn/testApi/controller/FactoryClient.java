package cn.cjn.testApi.controller;

import cn.cjn.testApi.pojo.Fruit;

import java.lang.reflect.Method;

/**
 * Title:
 * Description:工厂模式及多态
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/11 13:46
 */
public class FactoryClient {

    public static void main(String[] args) throws Exception {
        String className = "cn.cjn.testApi.pojo.Apple";
        String method = "setType";
        String param = "good";
        Fruit fruit = getFruitFactory(className, method, param);
        System.out.println("" + fruit);
    }


    public static Fruit getFruitFactory(String className, String method1, String param) throws Exception {
        Class<?> class1 = Class.forName(className);
        Fruit fruit = (Fruit) class1.newInstance();
        Method[] methods = class1.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(method1)) {
                method.invoke(fruit, param);
            }
        }
        return fruit;
    }
}
