package cn.cjn.test1.controller;

import cn.cjn.test1.service.RestfulServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:ribbon测试入口类
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/3 18:24
 */
@RestController
public class UserController {

    @Autowired
    private RestfulServiceImpl restfulService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 测试ribbon，消费者入口
     *
     * @param name
     * @return
     */
    @GetMapping("/hello/{name}")
    public String test(@PathVariable String name) {
        return "name:" + name + ",msg:" + restfulService.getRestData(name);
    }

    /**
     * 测试ribbon，消费者入口
     *
     * @return
     */
    @GetMapping("getListData")
    public String getListData() {
        return restfulService.getListData();
    }

    /**
     * 测试ribbon，测试数据库链接
     *
     * @return
     */
    @RequestMapping(name = "getDbData")
    public List<Map<String,Object>> getDbData() {
        String sql = "select * from appuser ";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
