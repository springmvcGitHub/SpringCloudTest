package cn.cjn.test2.feignInterface;

import cn.cjn.testApi.apiInterface.HelloServiceRemoteApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/8 18:41
 */
@FeignClient(value = "s1/testA")
@Component
public interface FeignInterface extends HelloServiceRemoteApi {

}
