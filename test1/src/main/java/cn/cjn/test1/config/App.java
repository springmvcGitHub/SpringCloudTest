package cn.cjn.test1.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Title:
 * Description:
 * Copyright: 2018 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/4 18:10
 */
@SpringBootApplication
@ComponentScan(basePackages={"cn.cjn.test1"})
@EnableEurekaClient
@EnableFeignClients
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
}
