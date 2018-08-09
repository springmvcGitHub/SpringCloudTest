package cn.cjn.testApi.apiInterface;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title:
 * Description:
 * Copyright: 2018 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/9 10:00
 */
@RequestMapping("/hello-service-remote")
public interface HelloServiceRemoteApi {

    @RequestMapping(method = RequestMethod.GET, value = "/getStr")
    String getStr();
}
