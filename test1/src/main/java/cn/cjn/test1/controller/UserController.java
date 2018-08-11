package cn.cjn.test1.controller;

import cn.cjn.test1.service.RestfulServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Title:
 * Description:ribbon测试入口类
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/3 18:24
 */
@RestController
public class UserController {

    @Autowired
    private RestfulServiceImpl restfulService;
    @Autowired
    private  HttpServletRequest request;

    @GetMapping("/hello/{name}")
    public String test(@PathVariable String name) {
        return "name:" + name + ",msg:" + restfulService.getRestData(name);
    }

    @GetMapping("getListData")
    public String getListData() {
        return restfulService.getListData();
    }

//    @RequestMapping(name = "getStr")
//    public String getStr(){
//        return "hello123";
//    }
}
