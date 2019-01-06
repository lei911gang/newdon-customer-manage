package com.newdon;

import com.github.wxiaoqi.merge.EnableAceMerge;
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
        SpringApplication.run(NewdonApplication.class, args);
    }
}
