package com.newdon;

import com.github.wxiaoqi.merge.EnableAceMerge;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Rlserim
 * @create 2018/8/30 16:12
 * @desc
 **/
@EnableAceMerge
@SpringBootApplication
public class NewdonApplication {
    public static void main(String[] args) {
//        SpringApplication.run(NewdonApplication.class, args);
        String str = "zhangsan";
        String s = Base64.encodeBase64String(str.getBytes());
        System.out.println(s);
        byte[] bytes = Base64.decodeBase64(s);
        String s1 = new String(bytes);
        System.out.println(s1);
    }
}
