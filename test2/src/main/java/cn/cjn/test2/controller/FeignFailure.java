package cn.cjn.test2.controller;

import cn.cjn.test2.feignInterface.FeignInterface;
import org.springframework.stereotype.Component;

/**
 * @Author: 东星耀阳
 * @Description:
 * @Date: 2018-08-30
 */
@Component
public class FeignFailure implements FeignInterface {
    @Override
    public String getStr() {
        return "我断路了~~~~~";
    }
}
