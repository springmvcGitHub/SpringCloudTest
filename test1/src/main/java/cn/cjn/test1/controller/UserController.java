package cn.cjn.test1.controller;

import cn.cjn.test1.service.RestfulServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private RestfulServiceImpl restfulService;

    @Autowired
    @Qualifier(value = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier(value = "secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(5);

    int flag = 0;

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
    @RequestMapping(value = "getDbData", method = RequestMethod.GET)
    public List<Map<String, Object>> getDbData() {
        String sql = "select * from appuser ";
        List<Map<String, Object>> list = primaryJdbcTemplate.queryForList(sql);
        return list;
    }

//    /**
//     * 测试BlockingQueue
//     *
//     * @return
//     */
//    @RequestMapping(value = "testBQ", method = RequestMethod.POST)
//    @ResponseBody
//    public String testBQ() {
//
//        new Thread(() ->product(blockingQueue)).start();
//
//        new Thread(() ->cunsumer(blockingQueue)).start();
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("success", true);
//        return jsonObject.toString();
//    }
//
//    private void product(BlockingQueue<String> blockingQueue){
//        for (int i = 0; i <30 ; i++) {
////            try {
////                Thread.sleep(400);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            String userKey = UUID.randomUUID().toString().replace("-", "");
//            blockingQueue.offer(userKey);
//            System.out.println("--------size1:"+blockingQueue.size());
//        }
//
//    }
//
//    private void cunsumer(BlockingQueue<String> blockingQueue){
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < blockingQueue.size(); i++) {
//            String code = blockingQueue.poll();
//            System.out.println("---------:" + code + ",size:" + blockingQueue.size());
//        }
//    }

    @RequestMapping(value = "addAppuser2", method = RequestMethod.POST)
    @ResponseBody
    public String addAppuser2(String nickName) {
        String userKey = UUID.randomUUID().toString().replace("-", "");
        nickName = "\uD83C\uDF3C 妙盒子\uD83C\uDF3C";
        String insertSql = "INSERT appuser(nickName,userKey,create_date) VALUES('" + nickName + "','" + userKey + "',NOW()) ";
        int count = secondJdbcTemplate.update(insertSql);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    /**
     * 测试ribbon，测试数据库插入
     *
     * @return
     */
    @RequestMapping(value = "addAppuser", method = RequestMethod.POST)
    @ResponseBody
    public String addAppuser(String nickName) {
        String userKey = UUID.randomUUID().toString().replace("-", "");
        String insertSql = "INSERT appuser(nickName,userKey,create_date) VALUES('" + nickName + "','" + userKey + "',NOW()) ";
        int count = primaryJdbcTemplate.update(insertSql);
        //updateInvite(userKey);
        blockingQueue.offer(userKey);
        new Thread(() -> updateInvite()).start();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    private void updateInvite() {
        if (blockingQueue.size() == 0) {
            return;
        }
        String userKey = blockingQueue.poll();
        boolean flag = true;
        int failCount = 0;  //计数器，防止出现异常情况进入死循环
        while (flag) {
            String queryIdSql = "SELECT inviteCode FROM appuser_invite WHERE `status`=0 LIMIT 1 ";
            Map<String, Object> dataMap = primaryJdbcTemplate.queryForMap(queryIdSql);
            int inviteCode = 0;
            if (null != dataMap && null != dataMap.get("inviteCode")) {
                inviteCode = Integer.parseInt(String.valueOf(dataMap.get("inviteCode")));
            }
            //实时更新数据库，先把当前这个inviteCode状态改为占用状态，
            String updateInviteSql = "UPDATE appuser_invite SET update_date=NOW(),`status`=1 WHERE `status`=0 AND inviteCode='" + inviteCode + "'";
            int updateInviteFlag = primaryJdbcTemplate.update(updateInviteSql);
            int updateFlag = 0;
            if (updateInviteFlag > 0) {
                String updateSql = "UPDATE appuser SET inviteCode='" + inviteCode + "' WHERE userKey='" + userKey + "' ";
                updateFlag = primaryJdbcTemplate.update(updateSql);
            } else {
                logger.error("[--实时更新数据库邀请码表异常--],关键字:userKey_" + userKey + ",inviteCode:" + inviteCode + ",updateInviteSql:" + updateInviteSql);
            }

            if (updateFlag > 0) {
                flag = false;
            }
            if (updateInviteFlag == 0 || updateFlag == 0) {
                failCount++;
            }
            if (failCount == 10) {
                logger.error("[--更新用户邀请码异常--],关键字:userKey_" + userKey);
                flag = false;
            }
        }
    }

    @RequestMapping(value = "createInvite", method = RequestMethod.POST)
    @ResponseBody
    public String createInvite() {
        int count = 5000;
        String queryMaxIdSql = "SELECT MAX(id) AS maxId FROM appuser_invite ";
        Map<String, Object> map = primaryJdbcTemplate.queryForMap(queryMaxIdSql);
        int maxId = 1;
        if (null != map) {
            maxId = null == map.get("maxId") ? 1 : Integer.parseInt(String.valueOf(map.get("maxId")));
        }
        int resultCount = 0;
        for (int i = 0; i < count; i++) {
            int inviteCode = getInviteCode(String.valueOf(maxId));
            String insertSql = "INSERT appuser_invite(inviteCode,`status`,create_date) VALUES(" + inviteCode + ",0,NOW())";

            int flag = primaryJdbcTemplate.update(insertSql);
            if (flag > 0) {
                resultCount++;
            }
            maxId++;
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("totalCount", count);
        result.put("successCount", resultCount);
        return result.toString();
    }

    /**
     * 根据规则要求不能出现4，由于保持数据一致性，故将4替换成5，下次重新创建会选取最大值作为基数.....
     *
     * @param id
     * @return
     */
    private static int getInviteCode(String id) {
        int incId = 0;
        if (id.length() <= 7) {
            incId = Integer.parseInt(id) + 10000000;
        } else {
            incId = Integer.parseInt(id);
        }

        while (isCuteIncId(incId) || String.valueOf(incId).contains("4")) {
            incId = getIncIdNew(incId);
        }
        return incId;
    }

    /**
     * 根据规则要求不能出现4，由于保持数据一致性，故将4替换成5，下次重新创建会选取最大值作为基数.....
     *
     * @param curIncId
     * @return
     */
    private static int getIncIdNew(int curIncId) {
        String incIdStr = String.valueOf(curIncId);
        incIdStr = incIdStr.replace("4", "5");
        return Integer.parseInt(incIdStr) + 1;
    }

    /**
     * 判断incId是否为靓号,预留一部分号码，包括代码中的和19500101-20201230之间的所有年份+月+日的邀请码（生日邀请码）
     *
     * @return
     */
    private static boolean isCuteIncId(int incId) {
        boolean result = false;
        //逻辑待定
        if (incId == 11111111 || incId == 22222222 || incId == 33333333 || incId == 55555555 || incId == 66666666 ||
                incId == 77777777 || incId == 88888888 || incId == 99999999 || incId == 12345678 || incId == 87654321 ||
                incId == 66668888 || incId == 88886666 || incId == 18888888 || incId == 16666666) {
            result = true;
        } else {
            String incIdStr = String.valueOf(incId);
            String startFour = incIdStr.substring(0, 4);
            String midTwo = incIdStr.substring(4, 6);
            String endTwo = incIdStr.substring(6, 8);
            if (Integer.parseInt(startFour) >= 1950 && Integer.parseInt(startFour) <= 2020 &&
                    Integer.parseInt(midTwo) >= 1 && Integer.parseInt(midTwo) <= 12 &&
                    Integer.parseInt(endTwo) >= 1 && Integer.parseInt(endTwo) <= 31) {  //增强逻辑，减少date的处理量
                String sourceDate = startFour + "-" + midTwo + "-" + endTwo;
                try {
                    Date date = format.parse(sourceDate);
                    result = true;
                } catch (ParseException e) {
                    result = false;
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "getStr", method = RequestMethod.POST)
    @ResponseBody
    public String getStr(@RequestParam(value = "code") String code) {
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("------code"+code);
        return code;
    }
}
