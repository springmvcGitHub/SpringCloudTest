package cn.cjn.test2.feignInterface;

import cn.cjn.testApi.apiInterface.HelloServiceRemoteApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 * Copyright: 2018 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/8 18:41
 */
@FeignClient(value = "s1/testA")
@Component
public interface FeignInterface extends HelloServiceRemoteApi {

}
