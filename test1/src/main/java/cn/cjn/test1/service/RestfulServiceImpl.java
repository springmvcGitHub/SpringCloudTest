package cn.cjn.test1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RestTemplate restTemplate;

    public String getRestData(String name) {
        String url = "http://S2/testB/hello/" + name;
        String resultMsg = restTemplate.getForObject(url, String.class);
        return resultMsg;
    }

    public String getListData(HttpServletRequest request) {
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(i, "a" + i);
        }

        String url = "http://S2/testB/getListData";

        MultiValueMap<String,Object> parameters = new LinkedMultiValueMap<String,Object>();
        parameters.add("username", "aa");
        parameters.add("password", list);

        ResponseEntity<String> response = restTemplate.postForEntity( url, parameters , String.class );
        String body = response.getBody();
        return body;
    }
}
