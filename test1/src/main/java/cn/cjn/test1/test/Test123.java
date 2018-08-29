package cn.cjn.test1.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * Project: SrpingBootTest
 * Create User: TRS-chen
 * Create Time:2018/8/7 16:22
 */
public class Test123 {

    private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(5);

    private static BlockingQueue<Object> blockingQueue2 = new ArrayBlockingQueue<Object>(5);

    private static List<String> list = new ArrayList<>();

    private static List<String> vector = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
//        new Thread(()->comeIn()).start();
//        new Thread(() -> drink()).start();

//        new Thread(() -> comeIn1()).start();
//        new Thread(() -> comeIn1()).start();
//        new Thread(() -> drink1()).start();

//        new Thread(() -> comeIn2()).start();

        //testArrayList();
        testVector();
    }

    private static void comeIn() {
        for (int i = 0; i < 10; i++) {
            String drinkMan = "a" + i;
            System.out.println(drinkMan + "进来了.....,队列长度:"+blockingQueue.size());
            try {
                blockingQueue.put(drinkMan);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void drink() {
        for (int i = 0; i < 10; i++) {
            String drinkMan = "";
            try {
                drinkMan = blockingQueue.take();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("喝的时候呛着了......");
            }
            System.out.println(drinkMan + "喝了");
        }
    }

    private static void comeIn1() {
        for (int i = 0; i < 10; i++) {
            String drinkMan = "a" + i;
            System.out.println(drinkMan + "进来了.....");
            if (i < 6) {

            }
            list.add(drinkMan);
        }
    }

    private static void drink1() {
        for (int i = 0; i < 10; i++) {
            String drinkMan = "";
            try {
                drinkMan = list.get(i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("喝的时候呛着了......");
            }
            System.out.println(drinkMan + "喝了");
        }
    }

    private static void comeIn2() {
        System.out.println("放入前:"+blockingQueue2.size());
        try {
            blockingQueue2.put(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("放入后:"+blockingQueue2.size());
    }

    /**
     * 测试Vector的线性不安全
     * @throws InterruptedException
     */
    private static void testVector() throws InterruptedException {
        //测试ArrayList的线性不安全
        for (int i = 0; i < 100; i++) {
            new Thread(() -> addVector()).start();
        }
        Thread.sleep(5000);
        System.out.println("-------"+vector.size());
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < vector.size(); i++) {
            if(null == vector.get(i) || vector.get(i).equals("")){
                count1 ++;
            }else{
                count2 ++;
            }
        }
        System.out.println("count1:"+count1+",count2:"+count2);
    }
    private static void addVector(){
        for (int i = 0; i < 100; i++) {
            String drinkMan = "a" + i;
            System.out.println(drinkMan + "进来了.....");
            vector.add(drinkMan);
        }
    }

    /**
     * 测试ArrayList的线性不安全
     * @throws InterruptedException
     */
    private static void testArrayList() throws InterruptedException {
        //测试ArrayList的线性不安全
        for (int i = 0; i < 100; i++) {
            new Thread(() -> addArrayList()).start();
        }
        Thread.sleep(5000);
        System.out.println("-------"+list.size());
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < list.size(); i++) {
            if(null == list.get(i) || list.get(i).equals("")){
                count1 ++;
            }else{
                count2 ++;
            }
        }
        System.out.println("count1:"+count1+",count2:"+count2);
    }
    private static void addArrayList(){
        for (int i = 0; i < 100; i++) {
            String drinkMan = "a" + i;
            System.out.println(drinkMan + "进来了.....");
            list.add(drinkMan);
        }
    }
}
