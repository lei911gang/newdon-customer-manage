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
public class Result implements Serializable {
    private Integer status;
    private String message;
    private Object data;
    private long total;
    private boolean success;


    public static Result build(Integer status, String message, boolean success) {
        return new Result(status,message,null,0,success);
    }

    public static Result build(Integer status, String message, Object data) {
        boolean falg = true;
        if (!CommonConstants.SUCCESS_CODE.equals(status)) {
            falg = false;
        }
        return new Result(status,message,data,0,falg);
    }

    public static Result build(Integer status, String message,long total, boolean success) {
        return new Result(status,message,null,total,success);
    }

    public static Result build(Integer status, String message,Object data,long total ,boolean success) {
        return new Result(status,message,data,total,success);
    }
}
