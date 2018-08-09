package cn.cjn.test1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/7 18:04
 */
@Service
public class RestfulServiceImpl {

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public String getRestData(String name){
//        String url = "http://SPRINGTESTB/testB/hello/"+name;
//        String resultMsg = restTemplate.getForObject(url,String.class);
//        return resultMsg;
//    }
}
