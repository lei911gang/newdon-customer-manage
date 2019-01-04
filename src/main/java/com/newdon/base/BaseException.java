package com.newdon.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  Rlserim
 * @create  2018/9/3 11:02
 * @desc
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private int status = 200;
    private String msg;
}
