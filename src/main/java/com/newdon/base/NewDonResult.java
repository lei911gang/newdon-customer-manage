package com.newdon.base;
import com.newdon.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @ClassName Result
 * @Auther: Dong
 * @Date: 2019/1/5 1:39
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDonResult implements Serializable {
    private Integer status;
    private String message;
    private Object data;
    private long total;
    private boolean success;
    private Double sum;
    private Double average;


    public static NewDonResult build(Integer status, String message, boolean success) {
        return new NewDonResult(status,message,null,0,success,null,null);
    }

    public static NewDonResult build(Integer status, String message, Object data) {
        boolean falg = true;
        if (!CommonConstants.SUCCESS_CODE.equals(status)) {
            falg = false;
        }
        return new NewDonResult(status,message,data,0,falg,null,null);
    }
    public static NewDonResult build(Integer status, String message, Object data,Double sum,Double average) {
        boolean falg = true;
        if (!CommonConstants.SUCCESS_CODE.equals(status)) {
            falg = false;
        }
        return new NewDonResult(status,message,data,0,falg,sum,average);
    }

    public static NewDonResult build(Integer status, String message, long total, boolean success) {
        return new NewDonResult(status,message,null,total,success,null,null);
    }

    public static NewDonResult build(Integer status, String message, Object data, long total , boolean success) {
        return new NewDonResult(status,message,data,total,success,null,null);
    }
}
