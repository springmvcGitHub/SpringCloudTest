package cn.cjn.test1.controller;

import cn.cjn.testApi.apiInterface.HelloServiceRemoteApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
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
