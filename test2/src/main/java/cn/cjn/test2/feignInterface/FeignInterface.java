package cn.cjn.test2.feignInterface;

import cn.cjn.test2.controller.FeignFailure;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/8 18:41
 */
@FeignClient(value = "s1/testA",fallback = FeignFailure.class)
@Component
public interface FeignInterface {

    @RequestMapping(method = RequestMethod.POST, value = "/user/getStr")
    String getStr(@RequestParam("code") String code);
}
