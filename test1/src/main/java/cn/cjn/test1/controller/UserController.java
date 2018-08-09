package cn.cjn.test1.controller;

import cn.cjn.test1.service.RestfulServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 * Copyright: 2018 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/3 18:24
 */
@RestController
public class UserController {

//    @Autowired
//    private RestfulServiceImpl restfulService;

//    @GetMapping("/hello/{name}")
//    public String test(@PathVariable String name) {
//        return "name:" + name + ",msg:" + restfulService.getRestData(name);
//    }

    @RequestMapping(name = "getStr")
    public String getStr(){
        return "hello123";
    }
}
