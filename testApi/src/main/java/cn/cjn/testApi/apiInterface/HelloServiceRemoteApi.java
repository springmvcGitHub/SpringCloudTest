package cn.cjn.testApi.apiInterface;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/9 10:00
 */
@RequestMapping("/hello-service-remote")
public interface HelloServiceRemoteApi {

    @RequestMapping(method = RequestMethod.GET, value = "/getStr")
    String getStr();
}
