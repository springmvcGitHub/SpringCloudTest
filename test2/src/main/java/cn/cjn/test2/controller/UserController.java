package cn.cjn.test2.controller;

import cn.cjn.test2.feignInterface.FeignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private FeignInterface feignInterface;

    /**
     * ribbon测试
     * @param name
     * @return
     */
//    @RequestMapping("/hello/{name}")
//    public String test(@PathVariable String name) {
//        return "Hi," + name;
//    }

    /**
     * feign测试
     * @return
     */
    @RequestMapping(value = "/getStr", method = RequestMethod.GET)
    public String getStr() {
        return feignInterface.getStr();
    }
}
