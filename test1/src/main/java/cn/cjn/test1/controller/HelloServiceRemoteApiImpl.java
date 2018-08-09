package cn.cjn.test1.controller;

import cn.cjn.testApi.apiInterface.HelloServiceRemoteApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 * Copyright: 2018 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/9 10:08
 */
@RestController
public class HelloServiceRemoteApiImpl implements HelloServiceRemoteApi {

    @Override
    public String getStr() {
        return "hello world!";
    }
}
