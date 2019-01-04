package com.newdon.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  Rlserim
 * @create  2018/1/12 9:33
 * @desc
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int status = 200;
    private String msg;
}
