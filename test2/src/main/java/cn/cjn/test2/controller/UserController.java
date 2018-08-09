package cn.cjn.test2.controller;

import cn.cjn.test2.feignInterface.FeignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/3 18:24
 */
@RestController
public class UserController {

    @Autowired
    private FeignInterface feignInterface;

    /**
     * ribbon测试
     * @param name
     * @return
     */
    @RequestMapping("/hello/{name}")
    public String test(@PathVariable String name) {
        return "Hi," + name;
    }

    @RequestMapping(value = "/getListData",method = RequestMethod.POST)
    public List getListData(@RequestBody String list){
        List data = new ArrayList();
//        if(null != list && list.size()>0){
//            for(int i=0;i<list.size();i++){
//                data.add(i,list.get(i));
//            }
//        }
        System.out.println("list:"+list);
        return data;
    }
    /**
     * feign测试
     * @return
     */
    @RequestMapping(value = "/getStr", method = RequestMethod.GET)
    public String getStr() {
        return feignInterface.getStr();
    }
}
